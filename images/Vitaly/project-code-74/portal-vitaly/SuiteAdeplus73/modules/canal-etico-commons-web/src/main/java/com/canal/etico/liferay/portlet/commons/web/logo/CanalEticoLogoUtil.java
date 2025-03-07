package com.canal.etico.liferay.portlet.commons.web.logo;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.*;
import java.util.HashMap;

public class CanalEticoLogoUtil {

    private static Log _log = LogFactoryUtil.getLog(CanalEticoLogoUtil.class);

    public static DLFileEntry saveLogo(long groupId, long userId, String description, String parentFolderName, String folderName, String fileName, File logo, ServiceContext serviceContext){

        DLFileEntry dlFileEntry = null;

        if(Validator.isNull(logo)){
            return null;
        }

        try {

            long repositoryId = groupId;

            InputStream is = new FileInputStream(logo);

            DLFolder dlParentFolder = getDLFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,parentFolderName);
            if (Validator.isNull(dlParentFolder)) {
                dlParentFolder = createDLFolder(groupId, userId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, parentFolderName, description, serviceContext);
            }

            DLFolder dlCompanyFolder = null;
            if (Validator.isNotNull(dlParentFolder)) {
                dlCompanyFolder = getDLFolder(groupId, dlParentFolder.getFolderId(), folderName);
                if (Validator.isNull(dlCompanyFolder)) {
                    dlCompanyFolder = createDLFolder(groupId, userId, dlParentFolder.getFolderId(), folderName, description, serviceContext);
                }
            }else{
                return null;
            }

            long fileEntryTypeId = dlCompanyFolder.getDefaultFileEntryTypeId();
            String changeLog = description;
            String mimeType = MimeTypesUtil.getContentType(logo);

            serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

            dlFileEntry = DLFileEntryLocalServiceUtil.addFileEntry(
                    userId,
                    groupId,
                    repositoryId,
                    dlCompanyFolder.getFolderId(),
                    fileName,
                    mimeType,
                    fileName,
                    description,
                    changeLog,
                    fileEntryTypeId,
                    null,
                    logo,
                    is,
                    logo.length(),
                    serviceContext);

            //Change mode of Draft to Approved
            DLFileEntryLocalServiceUtil.updateStatus(userId, dlFileEntry.getFileVersion().getFileVersionId(), WorkflowConstants.STATUS_APPROVED, serviceContext, new HashMap<String, Serializable>());

            setFilePermissionGuest(dlFileEntry.getCompanyId(), dlFileEntry.getPrimaryKey());

        } catch (Exception e) {
            _log.error(e);
        }

        return dlFileEntry;
    }

    private static DLFolder createDLFolder(long groupId, long userId, long parentFolderId, String folderName, String description, ServiceContext serviceContext){

        long repositoryId = groupId;    //repository id is same as groupId
        boolean hidden = false;         // true if you want to hidden the folder
        boolean mountPoint = false;     // mountPoint which is a boolean specifying whether the folder is a facade for mounting a third-party repository

        DLFolder dlFolder = null;

        try {

            dlFolder = DLFolderLocalServiceUtil.addFolder(
                    userId,
                    groupId,
                    repositoryId,
                    mountPoint,
                    parentFolderId,
                    folderName,
                    description,
                    hidden,
                    serviceContext);

            setFolderPermission(dlFolder.getCompanyId(), dlFolder.getPrimaryKey());

        } catch (PortalException e) {
            _log.error(e);
        } catch (Exception e) {
            _log.error(e);
        }
        return dlFolder;
    }

    private static DLFolder getDLFolder(long groupId, long parentFolderId, String folderName){
        try {
            return DLFolderLocalServiceUtil.fetchFolder(groupId, parentFolderId, folderName);
        } catch (Exception e) {
            _log.error(e);
        }
        return null;
    }

    private static void setFolderPermission(long companyId, long primKey){

        try {
            Role role = RoleLocalServiceUtil.fetchRole(companyId, RoleConstants.GUEST);

            if(Validator.isNotNull(role)) {

                ResourcePermission resourcePermission = ResourcePermissionLocalServiceUtil.createResourcePermission(CounterLocalServiceUtil.increment(ResourcePermission.class.getName()));

                resourcePermission.setNew(true);
                resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
                resourcePermission.setCompanyId(companyId);
                resourcePermission.setName(DLFolder.class.getName());
                resourcePermission.setPrimKey(Long.toString(primKey));
                resourcePermission.setRoleId(role.getRoleId());
                resourcePermission.setActionIds(1);

                ResourcePermissionLocalServiceUtil.addResourcePermission(resourcePermission);

            }

        } catch (Exception e) {
            _log.error("Error setting folder permission.");
        }

    }

    private static void setFilePermissionGuest(long companyId, long primKey){

        try {
            Role role = RoleLocalServiceUtil.fetchRole(companyId, RoleConstants.GUEST);

            if(Validator.isNotNull(role)) {

                ResourcePermission resourcePermission = ResourcePermissionLocalServiceUtil.createResourcePermission(CounterLocalServiceUtil.increment(ResourcePermission.class.getName()));

                resourcePermission.setNew(true);
                resourcePermission.setScope(ResourceConstants.SCOPE_INDIVIDUAL);
                resourcePermission.setCompanyId(companyId);
                resourcePermission.setName(DLFileEntry.class.getName());
                resourcePermission.setPrimKey(Long.toString(primKey));
                resourcePermission.setRoleId(role.getRoleId());
                resourcePermission.setActionIds(1);

                ResourcePermissionLocalServiceUtil.addResourcePermission(resourcePermission);

            }

        } catch (Exception e) {
            _log.error("Error setting file permission.", e);
        }

    }

    public static void deleteFile(long fileEntryId){
        if(fileEntryId > 0) {
            try {

                DLFileEntry dlFileEntryDelete = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
                DLAppLocalServiceUtil.deleteFileEntry(dlFileEntryDelete.getFileEntryId());

            } catch (PortalException e) {
                _log.error("Error deleting logo.");
            }
        }
    }




    public static DLFileEntry saveLogo(long groupId, long userId, String cif, String fileName, File logo, ServiceContext serviceContext){

        DLFileEntry dlFileEntry = null;

        if(Validator.isNull(logo)){
            return dlFileEntry;
        }

        try {

            long repositoryId = groupId;

            InputStream is = new FileInputStream(logo);

            DLFolder dlParentFolder = getDLFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,"Logos");
            if (Validator.isNull(dlParentFolder)) {
                dlParentFolder = createDLFolder(groupId, userId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Logos", "Almacenar logos de las empresas.", serviceContext);
            }

            DLFolder dlCompanyFolder = null;
            if (Validator.isNotNull(dlParentFolder)) {
                dlCompanyFolder = getDLFolder(groupId, dlParentFolder.getFolderId(), cif);
                if (Validator.isNull(dlCompanyFolder)) {
                    dlCompanyFolder = createDLFolder(groupId, userId, dlParentFolder.getFolderId(), cif, "Logos de la empresa: " + cif, serviceContext);
                }
            }else{
                return null;
            }

            long fileEntryTypeId = dlCompanyFolder.getDefaultFileEntryTypeId();
            String changeLog = "CompId: " + cif;
            String description = "CompId: " + cif;
            String mimeType = MimeTypesUtil.getContentType(logo);

            DLFileEntry dlFileEntryBefore = DLFileEntryLocalServiceUtil.fetchFileEntryByFileName(groupId, dlCompanyFolder.getFolderId(), fileName);
            if(Validator.isNotNull(dlFileEntryBefore)) {
                deleteFile(dlFileEntryBefore.getFileEntryId());
            }

            dlFileEntry = DLFileEntryLocalServiceUtil.addFileEntry(
                    userId,
                    groupId,
                    repositoryId,
                    dlCompanyFolder.getFolderId(),
                    fileName,
                    mimeType,
                    fileName,
                    description,
                    changeLog,
                    fileEntryTypeId,
                    null,
                    logo,
                    is,
                    logo.length(),
                    serviceContext);

            //Change mode of Draft to Approved
            DLFileEntryLocalServiceUtil.updateFileEntry(
                    userId,
                    dlFileEntry.getFileEntryId(),
                    fileName,
                    MimeTypesUtil.getContentType(logo),
                    fileName,
                    description,
                    changeLog,
                    DLVersionNumberIncrease.NONE,
                    dlFileEntry.getFileEntryTypeId(),
                    null,
                    logo,
                    null,
                    logo.length(),
                    serviceContext);

            //Change mode of Draft to Approved
            DLFileEntryLocalServiceUtil.updateStatus(userId, dlFileEntry.getFileVersion().getFileVersionId(), WorkflowConstants.STATUS_APPROVED, serviceContext, new HashMap<String, Serializable>());

            setFilePermissionGuest(dlFileEntry.getCompanyId(), dlFileEntry.getPrimaryKey());

        } catch (FileNotFoundException e) {
            _log.error("No se ha seleccionado fichero.");
        } catch (Exception e) {
            _log.error(e);
        }

        return dlFileEntry;
    }

    public static DLFileEntry savePolitica(long groupId, long userId, String idComp, String fileName, File logo, ServiceContext serviceContext,String tipo){

        DLFileEntry dlFileEntry = null;

        if(Validator.isNull(logo)){
            return dlFileEntry;
        }

        try {

            long repositoryId = groupId;

            InputStream is = new FileInputStream(logo);

            DLFolder dlParentFolder = getDLFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,tipo);
            if (Validator.isNull(dlParentFolder)) {
                dlParentFolder = createDLFolder(groupId, userId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, tipo, "Almacenar Politicas de las empresas.", serviceContext);
            }

            DLFolder dlCompanyFolder = null;
            if (Validator.isNotNull(dlParentFolder)) {
                dlCompanyFolder = getDLFolder(groupId, dlParentFolder.getFolderId(), idComp);
                if (Validator.isNull(dlCompanyFolder)) {
                    dlCompanyFolder = createDLFolder(groupId, userId, dlParentFolder.getFolderId(), idComp, tipo+" de la empresa: " + idComp, serviceContext);
                }
            }else{
                return null;
            }

            long fileEntryTypeId = dlCompanyFolder.getDefaultFileEntryTypeId();
            String changeLog = "CompId: " + idComp;
            String description = "CompId: " + idComp;
            String mimeType = MimeTypesUtil.getContentType(logo);

            DLFileEntry dlFileEntryBefore = DLFileEntryLocalServiceUtil.fetchFileEntryByFileName(groupId, dlCompanyFolder.getFolderId(), fileName);
            if(Validator.isNotNull(dlFileEntryBefore)) {
                deleteFile(dlFileEntryBefore.getFileEntryId());
            }

            dlFileEntry = DLFileEntryLocalServiceUtil.addFileEntry(
                    userId,
                    groupId,
                    repositoryId,
                    dlCompanyFolder.getFolderId(),
                    fileName,
                    mimeType,
                    fileName,
                    description,
                    changeLog,
                    fileEntryTypeId,
                    null,
                    logo,
                    is,
                    logo.length(),
                    serviceContext);

            //Change mode of Draft to Approved
            DLFileEntryLocalServiceUtil.updateFileEntry(
                    userId,
                    dlFileEntry.getFileEntryId(),
                    fileName,
                    MimeTypesUtil.getContentType(logo),
                    fileName,
                    description,
                    changeLog,
                    DLVersionNumberIncrease.NONE,
                    dlFileEntry.getFileEntryTypeId(),
                    null,
                    logo,
                    null,
                    logo.length(),
                    serviceContext);

            //Change mode of Draft to Approved
            DLFileEntryLocalServiceUtil.updateStatus(userId, dlFileEntry.getFileVersion().getFileVersionId(), WorkflowConstants.STATUS_APPROVED, serviceContext, new HashMap<String, Serializable>());

            setFilePermissionGuest(dlFileEntry.getCompanyId(), dlFileEntry.getPrimaryKey());

        } catch (FileNotFoundException e) {
            _log.error("No se ha seleccionado fichero.");
        } catch (Exception e) {
            _log.error(e);
        }

        return dlFileEntry;
    }




}
