package com.canal.etico.liferay.portlet.commons.web.file;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class CanalEticoDocumentLibraryUtil {

    private static Log _log = LogFactoryUtil.getLog(CanalEticoDocumentLibraryUtil.class);

    public static DLFileEntry saveFile(long groupId, long userId, String description, String parentFolderName, String folderName, String fileName, File file, ServiceContext serviceContext){

        DLFileEntry dlFileEntry = null;

        try {

            //Create folder for denuncia.
            DLFolder dlCompanyFolder = getOrCreateDLFolderForFile(groupId, userId, parentFolderName, folderName, description, serviceContext);

            if(Validator.isNull(file)){
                return null;
            }
            if(!file.exists()){
                return null;
            }

            long repositoryId = groupId;

            InputStream is = new FileInputStream(file);

            long fileEntryTypeId = dlCompanyFolder.getDefaultFileEntryTypeId();
            String changeLog = description;
            String mimeType = MimeTypesUtil.getContentType(file);

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
                    file,
                    is,
                    file.length(),
                    serviceContext);

            //Change mode of Draft to Approved
            DLFileEntryLocalServiceUtil.updateStatus(userId, dlFileEntry.getFileVersion().getFileVersionId(), WorkflowConstants.STATUS_APPROVED, serviceContext, new HashMap<String, Serializable>());

        } catch (Exception e) {
            _log.error(e);
        }

        return dlFileEntry;
    }

    private static DLFolder getOrCreateDLFolderForFile(long groupId, long userId, String parentFolderName, String folderName, String description, ServiceContext serviceContext){

        //Get default parent folder in document library.
        DLFolder dlParentFolder = getDLFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, parentFolderName);
        if (Validator.isNull(dlParentFolder)) {
            dlParentFolder = createDLFolder(groupId, userId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, parentFolderName, description, serviceContext);
        }

        //Get folder for file.
        DLFolder dlCompanyFolder = null;
        if (Validator.isNotNull(dlParentFolder)) {
            dlCompanyFolder = getDLFolder(groupId, dlParentFolder.getFolderId(), folderName);
            if (Validator.isNull(dlCompanyFolder)) {
                dlCompanyFolder = createDLFolder(groupId, userId, dlParentFolder.getFolderId(), folderName, description, serviceContext);
            }
        }else{
            return null;
        }
        return dlCompanyFolder;
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

    public static DLFolder getDLFolder(long groupId, long parentFolderId, String folderName){
        try {
            return DLFolderLocalServiceUtil.fetchFolder(groupId, parentFolderId, folderName);
        } catch (Exception e) {
            _log.error(e);
        }
        return null;
    }

    private static DLFolder getDefaultParentFolder(long groupId, String parentFolderName){
        //Get default parent folder in document library.
        return getDLFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, parentFolderName);
    }

    private static void setFolderPermission(long companyId, long primKey){

        try {
            Role role = RoleLocalServiceUtil.fetchRole(companyId, RoleConstants.USER);

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


    public static void deleteDLFolderContent(long groupId, String parentFolderName, String folderName){
        try {

            DLFolder defaultParentFolder = getDefaultParentFolder(groupId, parentFolderName);

            DLFolder dlFolder = DLFolderLocalServiceUtil.fetchFolder(groupId, defaultParentFolder.getFolderId(), folderName);

            List<FileEntry> fileEntries = DLAppServiceUtil.getFileEntries(dlFolder.getRepositoryId(), dlFolder.getFolderId());

            for(FileEntry fe:fileEntries){

                try {
                    DLAppLocalServiceUtil.deleteFileEntry(fe.getFileEntryId());
                } catch (PortalException e) {
                    _log.error("Error deleting file.");
                }
            }

        } catch (Exception e) {
            _log.error(e);
        }

    }

    public static void deleteFile(long fileEntryId){
        if(fileEntryId > 0) {
            try {

                DLFileEntry dlFileEntryDelete = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
                DLAppLocalServiceUtil.deleteFileEntry(dlFileEntryDelete.getFileEntryId());

            } catch (PortalException e) {
                _log.error("Error deleting file.");
            }
        }
    }

}
