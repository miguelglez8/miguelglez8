package com.legalplus.liferay.portlet.web.evaluacion.portlet.command;

import com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ParamUtil;
import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.InputStream;
import java.io.OutputStream;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebEvaluacionPortletKeys.WEBEVALUACION,
                "mvc.command.name=descargarDocumento"
        },
        service = MVCResourceCommand.class
)
public class DownloadDocumentResourceCommand extends BaseMVCResourceCommand  {

    private static Log _log = LogFactoryUtil.getLog(DownloadDocumentResourceCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long id = ParamUtil.getLong(resourceRequest, "id");

        FileEntry entry = DLAppServiceUtil.getFileEntry(id);

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
    }
}
