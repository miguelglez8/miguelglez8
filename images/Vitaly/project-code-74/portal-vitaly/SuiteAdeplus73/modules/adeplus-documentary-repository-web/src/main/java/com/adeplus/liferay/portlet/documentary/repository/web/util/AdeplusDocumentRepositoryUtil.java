package com.adeplus.liferay.portlet.documentary.repository.web.util;

import com.adeplus.liferay.portlet.documentary.repository.web.constants.AdeplusDocumentaryRepositoryPortletKeys;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.AdeplusDocumentaryRepositoryPortlet;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.DocumentRepositoryDTO;
import com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany;
import com.legalplus.liferay.portlet.legalplus.manager.service.ConsultorCompanyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Informes;
import com.plan.igualdad.liferay.portlet.manager.service.InformesLocalServiceUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

public class AdeplusDocumentRepositoryUtil {

	private static Log _log = LogFactoryUtil.getLog(AdeplusDocumentRepositoryUtil.class);
	
	/**
	 * 
	 * @param themeDisplay
	 * @return
	 */
	public static Folder getFolder(ThemeDisplay themeDisplay, String parentFolderConfiguration, String nameFolder){
		Folder folder = null;
		try {
			Folder parentFolder =DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,  parentFolderConfiguration);
			
			folder = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), parentFolder.getFolderId(), nameFolder);
			
		} catch (Exception e) {	}
		return folder;
	}
	
	/**
	 * Crear subcarpeta en la carpeta guardada en la configuracion del portlet
	 * @param themeDisplay
	 * @return
	 */
	public static Folder createFolder(ThemeDisplay themeDisplay, ActionRequest actionRequest, String parentFolderConfiguration, String compId){
		Folder folder = null;

		try {
			UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(AdeplusDocumentaryRepositoryPortlet.class.getName(), uploadRequest);

			Folder parentFolder = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,  parentFolderConfiguration);
			
			folder =DLAppServiceUtil.addFolder(themeDisplay.getScopeGroupId(), parentFolder.getFolderId(), compId , "", serviceContext);
			
		} catch (Exception e) {	
			_log.error("ERROR: " , e);
		}
		return folder;
	}
	
	/**
	 * 
	 * @param themeDisplay
	 * @return				String parentFolder = actionRequest.getPreferences().getValue(AdeplusDocumentaryRepositoryPortletKeys.FOLDER_FILES, StringPool.BLANK);

	 */
	public static List<DocumentRepositoryDTO> getFiles(ThemeDisplay themeDisplay, String applicationConfiguration, String parentFolderConfiguration, String compId){
		List<DocumentRepositoryDTO> documents = new ArrayList<>();
		
 		List<FileEntry> filesEntries = new ArrayList<>();
 		Folder folder =getFolder(themeDisplay, parentFolderConfiguration, compId);
 		
 		if(Validator.isNotNull(folder)) {
 			ArrayList<Long> consultors = new ArrayList<Long>();
 			boolean isConsultor = Boolean.FALSE;
 			try {
	 			filesEntries = DLAppServiceUtil.getFileEntries(themeDisplay.getScopeGroupId(), folder.getFolderId());

				List<ConsultorCompany> listConsultors = ConsultorCompanyLocalServiceUtil.fetchByCompId(Long.parseLong(compId));

				if(Validator.isNotNull(listConsultors) && listConsultors.size()>0) {
					for(ConsultorCompany consultor: listConsultors) {
						consultors.add(consultor.getUserId());
						if(consultor.getUserId()==themeDisplay.getUserId()) {
							isConsultor = Boolean.TRUE;
						}
					}
				}
 			} catch (PortalException e) {
 				_log.error("ERROR: " , e);
 			}

			if(Validator.isNotNull(filesEntries) && !filesEntries.isEmpty()) {
				for(FileEntry file : filesEntries) {
					Boolean permissDelete = Boolean.FALSE;
					int createForConsultor = 0;
					if(file.getUserId()== themeDisplay.getUserId() || isConsultor) {
						permissDelete = Boolean.TRUE;
					}
					
					if(Validator.isNotNull(consultors) && !consultors.isEmpty()) {
						for(Long consultor : consultors) {
							if(consultor == file.getUserId()) {
								createForConsultor =  1;
							}
						}
					}
					
					DocumentRepositoryDTO document = getDocumentRepositoryDTO(file, permissDelete, createForConsultor);
					documents.add(document);
					
				}
			}
 		}
 		 		
 		List<Informes> listInformes = InformesLocalServiceUtil.findInformesByComp(Long.parseLong(compId));

 		if(Validator.isNotNull(listInformes)) {
 			for(Informes informe : listInformes) {
 				DocumentRepositoryDTO document = new DocumentRepositoryDTO();
 				
 				User user= null;
				try {
					user = UserLocalServiceUtil.getUser(informe.getUserId());
				} catch (PortalException e) {
					_log.error("ERROR: ", e);
				}
 				
 				document.setDocumentId(informe.getInformeId());
 				document.setDocumentName(informe.getTipoInforme());
 				document.setUserId(informe.getUserId());
 				document.setIsConsultor(1);
 				document.setIsInforme(Boolean.TRUE);
 				
 				int totalVersion = InformesLocalServiceUtil.countVersionsInforme(Long.parseLong(compId), informe.getVersionId(), informe.getTipoInforme());
 				document.setLastVersion(totalVersion==informe.getVersionInforme() ? 1 : 0);
 				if(Validator.isNotNull(user)) {
 					document.setUserName(user.getFirstName() + " " + user.getLastName());
 				}
 				
 				String dateUpload = new SimpleDateFormat("dd/MM/yyyy").format(informe.getCreateDate());
 				document.setDate(dateUpload);
 				
 				documents.add(document);
 			}
 		}
 		
		return documents;
	}
	
	/**
	 * 
	 * @param fileEntryId
	 */
	public static void deleteFile(Long fileEntryId) {
		try {
			DLAppServiceUtil.deleteFileEntry(fileEntryId);
			_log.debug("Documento ["+fileEntryId + "] eliminado correctamente" );
		} catch (PortalException e) {
			_log.error("ERROR: ", e);
		}
	}
	
	/**
	 * 
	 * @param file
	 * @param userId
	 * @return
	 */
	private static DocumentRepositoryDTO getDocumentRepositoryDTO(FileEntry file, Boolean permissDelete, int createForConsultor) {
		DocumentRepositoryDTO documentRepositoryDTO = new DocumentRepositoryDTO();
		
		documentRepositoryDTO.setDocumentId(file.getFileEntryId());
		documentRepositoryDTO.setDocumentName(file.getTitle());
		documentRepositoryDTO.setUserId(file.getUserId());
		documentRepositoryDTO.setUserName(file.getUserName());
		documentRepositoryDTO.setObservations(file.getDescription());
		documentRepositoryDTO.setIsInforme(Boolean.FALSE);
		documentRepositoryDTO.setLastVersion(1);
		
		String dateUpload = new SimpleDateFormat("dd/MM/yyyy").format(file.getCreateDate());
		documentRepositoryDTO.setDate(dateUpload);
		documentRepositoryDTO.setPermissDelete(permissDelete);
		documentRepositoryDTO.setIsConsultor(createForConsultor);
		
		return documentRepositoryDTO;
	}
	
	/**
	 * 
	 * @param actionRequest
	 * @param themeDisplay
	 * @param compId
	 * @param renderUrl
	 * @return
	 */
	public static String redirectURL(ActionRequest actionRequest, ThemeDisplay themeDisplay, Long compId, String renderUrl) {
        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
        PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        redirectURL.setParameter("mvcRenderCommandName", renderUrl);
        redirectURL.setParameter(AdeplusDocumentaryRepositoryPortletKeys.COMPID_PARAM, String.valueOf(compId));
        return redirectURL.toString();
    }
}
