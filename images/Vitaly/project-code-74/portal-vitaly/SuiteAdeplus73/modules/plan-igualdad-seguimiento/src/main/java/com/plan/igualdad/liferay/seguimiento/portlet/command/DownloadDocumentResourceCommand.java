package com.plan.igualdad.liferay.seguimiento.portlet.command;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ParamUtil;
import com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys;

import java.io.InputStream;
import java.io.OutputStream;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PlanIgualdadSeguimientoPortletKeys.PLANIGUALDADSEGUIMIENTO,
                "mvc.command.name=/planigualdad/downloadDocument"
        },
        service = MVCResourceCommand.class
)
public class DownloadDocumentResourceCommand extends BaseMVCResourceCommand{

	private static Log _log = LogFactoryUtil.getLog(DownloadDocumentResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		
		Long idFile = ParamUtil.getLong(resourceRequest, PlanIgualdadSeguimientoPortletKeys.FILE_ID);
		_log.debug("Descargando documento [" +idFile+"]");
		FileEntry entry = DLAppServiceUtil.getFileEntry(idFile);
		
		resourceResponse.setContentType(entry.getExtension());
        resourceResponse.setProperty("Content-disposition", "attachment; filename=" + entry.getFileName());
        resourceResponse.setProperty("Cache-Control", "no-cache");
        
        try (InputStream is = entry.getContentStream()) {
            OutputStream outputStream = resourceResponse.getPortletOutputStream();
            IOUtils.copy(is, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            _log.error(e, e);
            throw e;
        }
        _log.debug("Descargado documento [" +idFile+"]");
	}

}
