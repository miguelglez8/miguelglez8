<%@ include file="/init.jsp" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado"%>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.GrupoEmpresa" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.GrupoEmpresaLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.document.library.kernel.service.DLAppLocalServiceUtil" %>
<%@ page import="org.apache.commons.io.IOUtils" %>
<%@ page import="com.liferay.portal.kernel.repository.model.FileEntry" %>

<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.io.IOException" %>



<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%

long idCom = ParamUtil.getLong(request,"idCom", 0);


Comunicado comunicado =ComunicadoLocalServiceUtil.fetchComunicado(idCom);
String downloadURL="";
String downloadURLProcedimiento="";
long compId= ParamUtil.getLong(request,"compId", 0);
Comp comp = CompLocalServiceUtil.fetchComp(compId);
String urlId = comp.getUrlId();
String base64Image = "";
String urlImagen="";

String nombreEmpresa = "";
boolean traeLogo=true;
if(comunicado.getIdGrupoEmpresa()>=1){
    traeLogo=false;
}

if(Validator.isNotNull(comp)){

    if(comunicado.getIdGrupoEmpresa()>1){
        GrupoEmpresa grupoEmpresa=GrupoEmpresaLocalServiceUtil.fetchGrupoEmpresa(comunicado.getIdGrupoEmpresa());
        if(Validator.isNotNull(grupoEmpresa)){
            nombreEmpresa=grupoEmpresa.getEntidad();
        }else{
            nombreEmpresa=comunicado.getNombreEmpresa();
        }
    }else{
        com.adeplus.liferay.portlet.suite.manager.model.Comp compania = null;
        compania = com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil.fetchComp(comp.getCompId());
        nombreEmpresa = compania.getName();
    }

    if(!Validator.isBlank(comp.getPoliticaFileId())){
    	        DLFileEntry dlFileEntryPolitica = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(comp.getPoliticaFileId()));

    	        if(Validator.isNotNull(dlFileEntryPolitica)){
                    long idPolitica=dlFileEntryPolitica.getFileEntryId();
                    long idPoliticaGrupo=dlFileEntryPolitica.getGroupId();
                     downloadURL = "/documents/" + dlFileEntryPolitica.getGroupId() +"/"+ dlFileEntryPolitica.getFolderId() + "/"
                                           + dlFileEntryPolitica.getTitle() + "/" + dlFileEntryPolitica.getUuid();
                }
            }
            if(!Validator.isBlank(comp.getProcedimientoFileId())){
                DLFileEntry dlFileEntryProcedimiento = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(comp.getProcedimientoFileId()));

                if(Validator.isNotNull(dlFileEntryProcedimiento)){
                     downloadURLProcedimiento = "/documents/" + dlFileEntryProcedimiento.getGroupId() +"/"+ dlFileEntryProcedimiento.getFolderId() + "/"
                                               + dlFileEntryProcedimiento.getTitle() + "/" + dlFileEntryProcedimiento.getUuid();
                }
            }

            com.adeplus.liferay.portlet.suite.manager.model.Comp cmp = com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil.fetchComp(compId);

            if(Validator.isNotNull(cmp) && Validator.isNotNull(cmp.getLogo())){
                DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(cmp.getLogo()));
                urlImagen = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
                            dlFileEntry.getFolderId() + "/" + dlFileEntry.getTitle();
                FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dlFileEntry.getFileEntryId());
                InputStream inputStream = fileEntry.getContentStream();
                byte[] bytes = IOUtils.toByteArray(inputStream);
                base64Image = Base64.getEncoder().encodeToString(bytes);
            }

}
%>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">


<script>
	Liferay.Loader.define._amd = Liferay.Loader.define.amd;
	Liferay.Loader.define.amd = false;
</script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.flash.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.html5.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.print.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"></script>
<script>
	Liferay.Loader.define.amd = Liferay.Loader.define._amd;
</script>

<div class="paso_finalizado">
    <c:if test="<%=traeLogo && !Validator.isBlank(urlImagen)%>">
        <div class="img-thumbnail-frame">
            <img src="<%=urlImagen%>" class="img-thumbnail" alt=""/>
        </div>
    </c:if>

    <div class="info-finalizado">
        <h2 class="pasoSubtitle"><liferay-ui:message key="canal.comunicado.correcto.anadir"/></h2>
        <div class="finalizadoImg">
            <object type="image/svg+xml" data="ok.svg">
                <svg xmlns="http://www.w3.org/2000/svg" width="259" height="259" fill="none"><path fill="#72B561" d="M129.62 223.46c51.97 0 93.82-42.33 93.82-94.3a93.64 93.64 0 0 0-93.82-93.83 94.04 94.04 0 0 0-94.07 94.06 94.04 94.04 0 0 0 94.07 94.07Zm0-176.6c45.38 0 82.53 37.15 82.53 82.53 0 45.39-37.15 82.54-82.53 82.54-45.39 0-82.54-37.15-82.54-82.54 0-45.38 37.15-82.54 82.54-82.54Z" /><path fill="#72B561" d="M181.35 94.35c-2.59-2.58-7.53-2.58-10.11.24l-55.03 56.9-28.69-26.33a7.55 7.55 0 0 0-10.1.23 7.55 7.55 0 0 0 .23 10.12l34.1 31.04a7.47 7.47 0 0 0 4.7 1.88c1.88 0 3.52-.7 5.17-2.12l60.2-61.84c2.59-3.06 2.59-7.53-.47-10.12Z" /></svg>
            </object>
        </div>
        <p class="pasoSubtitle"><liferay-ui:message key="canal.comunicado.correcto.gracias"/></p>
        <h3 class="identificadorLabel"><liferay-ui:message key="canal.comunicado.correcto.identificador"/></h3>
        <p class="identificadorValue"><%=comunicado.getCodigo()%></p>
    </div>

    <c:choose>
      <c:when test="<%=Validator.isBlank(downloadURL) && Validator.isBlank(downloadURLProcedimiento)%>">
        <p><liferay-ui:message key="canal.comunicado.correcto.informacion.noEnlace"/></p>
      </c:when>
      <c:when test="<%=Validator.isBlank(downloadURL)%>">
         <p><liferay-ui:message key="canal.comunicado.correcto.informacion.noEnlacePrott"/></p>
      </c:when>
      <c:when test="<%=Validator.isBlank(downloadURLProcedimiento)%>">
         <p><liferay-ui:message key="canal.comunicado.correcto.informacion.noEnlaceProc"/></p>
      </c:when>
      <c:otherwise>
        <p><liferay-ui:message key="canal.comunicado.correcto.informacion"/></p>
      </c:otherwise>
    </c:choose>


<script>
    $(document).ready(function () {
        $(".clsProcCom").prop("href", "<%=downloadURLProcedimiento%>");
        $(".clsProteccionDatos").prop("href", "<%=downloadURL%>");
    });
</script>