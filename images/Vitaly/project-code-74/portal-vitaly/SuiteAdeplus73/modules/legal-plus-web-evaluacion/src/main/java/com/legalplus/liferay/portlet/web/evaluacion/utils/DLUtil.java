package com.legalplus.liferay.portlet.web.evaluacion.utils;

import com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DLUtil {

    private static final String EMPRESAS = "EMPRESAS";
    private static final String EMPRESA = "EMPRESA_";
    private static final String EVALUACION = "EVALUACION_";

    public static long updateFile(PortletRequest portletRequest, UploadRequest uploadRequest, ThemeDisplay themeDisplay,
                                       String compId, String evaluacionId, Long version, Long fileId, String fileName) throws PortletException {
        try {

            if (!Validator.isBlank(fileName)) {

                if (fileId != 0) {
                    DLAppServiceUtil.deleteFileEntry(fileId);
                }

                File file = uploadRequest.getFile(WebEvaluacionPortletKeys.FICHERO);
                long size = uploadRequest.getSize(WebEvaluacionPortletKeys.FICHERO);
                long repositoryId = themeDisplay.getScopeGroupId();
                String mimeType = MimeTypesUtil.getContentType(file);
                String folderName = EMPRESA + compId;
                String fileTitle = EVALUACION + evaluacionId + StringPool.UNDERLINE + version;

                Folder parent = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, EMPRESAS);
                Folder folder = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), parent.getFolderId(), folderName);
                ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), portletRequest);
                InputStream is = new FileInputStream( file );

                FileEntry fileEntry = DLAppServiceUtil.addFileEntry(repositoryId, folder.getFolderId(), fileName, mimeType,
                        fileTitle, StringPool.BLANK, StringPool.BLANK, is, size, serviceContext);

                return fileEntry.getFileEntryId();
            }

            return 0;
        } catch (PortalException | FileNotFoundException e) {
            e.printStackTrace();
            throw new PortletException(e);
        }

    }

}
