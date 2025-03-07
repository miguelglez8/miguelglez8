<%@ page import="com.canal.etico.liferay.portlet.admin.denuncia.web.constants.AdminDenunciaPortletKeys" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.*" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.*" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.estado.CanalEticoEstadoUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.file.CanalEticoDocumentLibraryUtil" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFolder" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFolderConstants" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.util.*" %>
<%@ include file="init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">

<%
    long denunciaId = ParamUtil.getLong(request, AdminDenunciaPortletKeys.DENUNCIA_ID, 0);

    Denuncia denuncia = DenunciaLocalServiceUtil.fetchDenuncia(denunciaId);

    boolean showData = CanalEticoEstadoUtil.esCampoVisibleDenuncia(denuncia);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

    String categoriaStr = String.valueOf(denuncia.getDenunciaId());
    Categoria categoria = CategoriaLocalServiceUtil.fetchCategoria(denuncia.getCategoria());
    if(Validator.isNotNull(categoria)){
        categoriaStr = categoria.getNombre();
    }

    String vinculacionStr = String.valueOf(denuncia.getDenunciaId());
    Vinculacion vinculacion = VinculacionLocalServiceUtil.fetchVinculacion(Long.parseLong(denuncia.getVinculacion()));
    if(Validator.isNotNull(vinculacion)){
        vinculacionStr = vinculacion.getNombre();
    }

    List<DLFileEntry> fileEntries = new ArrayList<>();
    DLFolder dlFolderParent = CanalEticoDocumentLibraryUtil.getDLFolder(themeDisplay.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Documentos");
    DLFolder dlFolder = CanalEticoDocumentLibraryUtil.getDLFolder(themeDisplay.getScopeGroupId(), dlFolderParent.getFolderId(), denuncia.getCodigo());
    if(Validator.isNotNull(dlFolder)) {
        fileEntries = DLFileEntryLocalServiceUtil.getFileEntries(themeDisplay.getScopeGroupId(), dlFolder.getFolderId());
    }

    List<Accion> acciones = AccionLocalServiceUtil.getAllAccionesActiveFromCompany(denuncia.getCompId());
    List<DenunciaAccion> denunciaAccionFromDenuncia = DenunciaAccionLocalServiceUtil.getDenunciaAccionFromDenuncia(denuncia.getDenunciaId());

    List<Observacion> observaciones = new ArrayList<>();
    List<Observacion> observacionesFromDenuncia = ObservacionLocalServiceUtil.getObservacionesFromDenuncia(denuncia.getDenunciaId());
    if(Validator.isNotNull(observacionesFromDenuncia)){
        observaciones = observacionesFromDenuncia;
    }

    List<Comunicacion> comunicaciones = ComunicacionLocalServiceUtil.getComunicacionesFromDenuncia(denuncia.getDenunciaId());

    List<Motivo> motivos = MotivoLocalServiceUtil.getAllMotivosActiveFromCompany(denuncia.getCompId());

    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
    String registrado  	= LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.registrado");
    String enProceso  	= LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.en.proceso");
    String finalizado  	= LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.finalizado");
    String sinFinalizar = LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.sin.finalizar");

    String estadoDenuncia = registrado;
    if(denuncia.getEstado() == AdminDenunciaPortletKeys.ESTADO_EN_PROCESO){
        estadoDenuncia = enProceso;
    }else if(denuncia.getEstado() == AdminDenunciaPortletKeys.ESTADO_FINALIZADO){
        estadoDenuncia = finalizado;
    }else if(denuncia.getEstado() == AdminDenunciaPortletKeys.ESTADO_SIN_FINALIZAR){
        estadoDenuncia = sinFinalizar;
    }

%>


<div class="title-group">
    <h2><liferay-ui:message key="denuncia.edit.title"/></h2>
</div>

<div class="row">
    <div class="col-sm-12">
        <ul class="nav nav-tabs" id="tabDenuncia" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="datos-generales-tab" data-toggle="tab" href="#datos-generales" role="tab" aria-controls="datos-generales" aria-selected="true"><liferay-ui:message key="denuncia.edit.datos.generales"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="datos-denuncia-tab" data-toggle="tab" href="#datos-denuncia" role="tab" aria-controls="datos-denuncia" aria-selected="false"><liferay-ui:message key="denuncia.edit.datos.denuncia"/></a>
            </li>
        </ul>
    </div>
</div>

<div class="tab-content" id="tabDenunciaContent">
    <div class="tab-pane fade show active m-5" id="datos-generales" role="tabpanel" aria-labelledby="datos-generales-tab">
        <h4><liferay-ui:message key="denuncia.edit.datos"/></h4>
        <div class="row">
            <div class="col-md-6">
                <fieldset class="input-group-inline">
                    <legend><liferay-ui:message key="denuncia.view.createDate"/></legend>
                    <p><%=dateFormat.format(denuncia.getCreateDate())%></p>
                </fieldset>
            </div>

            <div class="col-md-6">
                <fieldset class="input-group-inline">
                    <legend><liferay-ui:message key="denuncia.view.estado"/></legend>
                    <p><%=estadoDenuncia%></p>
                </fieldset>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <fieldset class="input-group-inline">
                    <legend><liferay-ui:message key="denuncia.view.codigo"/></legend>
                    <p><%=denuncia.getCodigo()%></p>
                </fieldset>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <fieldset class="input-group-inline">
                    <legend><liferay-ui:message key="denuncia.view.tipo"/></legend>
                    <p><%="0".equals(denuncia.getTipo())?"Consulta":"Denuncia"%></p>
                </fieldset>
            </div>
        </div>
        <div class="row">
            <c:if test="<%=showData%>">
                <div class="col-md-12">
                    <fieldset class="input-group-inline">
                        <legend><liferay-ui:message key="denuncia.view.categoria"/></legend>
                        <p><%=categoriaStr%></p>
                    </fieldset>
                </div>
            </c:if>
        </div>

        <c:if test="<%=!denuncia.isAnonimo()%>">
            <h4><liferay-ui:message key="denuncia.edit.datos.personales"/></h4>
            <div class="row">
                <div class="col-md-2">
                    <fieldset class="input-group-inline">
                        <legend><liferay-ui:message key="denuncia.view.nif"/></legend>
                        <p><%=denuncia.getNif()%></p>
                    </fieldset>
                </div>
                <div class="col-md-4">
                    <fieldset class="input-group-inline">
                        <legend><liferay-ui:message key="denuncia.view.nombre"/></legend>
                        <p><%=denuncia.getNombre()%></p>
                    </fieldset>
                </div>
                <div class="col-md-3">
                    <fieldset class="input-group-inline">
                        <legend><liferay-ui:message key="denuncia.view.mail"/></legend>
                        <p><%=denuncia.getEmail()%></p>
                    </fieldset>
                </div>
                <div class="col-md-3">
                    <fieldset class="input-group-inline">
                        <legend><liferay-ui:message key="denuncia.view.telefono"/></legend>
                        <p><%=denuncia.getTelefono()%></p>
                    </fieldset>
                </div>
            </div>
        </c:if>

    </div>

    <div class="tab-pane fade m-5" id="datos-denuncia" role="tabpanel" aria-labelledby="datos-denuncia-tab">

        <!-- Tab datos consulta/denuncia-->
        <h4><liferay-ui:message key="denuncia.edit.contenido"/></h4>
        <div class="row">
            <div class="col-md-12">
                <fieldset class="input-group-inline">
                    <legend><liferay-ui:message key="denuncia.view.asunto"/></legend>
                </fieldset>
                <c:if test="<%=CanalEticoEstadoUtil.esEstadoActivo(denuncia.getEstado())%>">
                    <liferay-portlet:actionURL name="/denuncia/asunto" var="saveAsuntoURL" />
                    <aui:form  name="asunto_form" action="<%=saveAsuntoURL.toString()%>" method="post">
                        <aui:input type="hidden" name="<%=AdminDenunciaPortletKeys.DENUNCIA_ID%>" value="<%=denuncia.getDenunciaId()%>"></aui:input>
                        <div class="row">
                            <div class="col-md-10">
                                <aui:input type="text" name="<%=AdminDenunciaPortletKeys.ASUNTO%>" value="<%=denuncia.getAsunto()%>" >
                                    <aui:validator name="required"/>
                                    <aui:validator name="maxLength">300</aui:validator>
                                </aui:input>
                            </div>
                            <div class="col-md-2">
                                <aui:button-row>
                                    <aui:button type="submit" cssClass="btn btn-secundary mt-3" value="save" />
                                </aui:button-row>
                            </div>
                        </div>
                    </aui:form>
                </c:if>
                <c:if test="<%=!CanalEticoEstadoUtil.esEstadoActivo(denuncia.getEstado())%>">
                    <div class="row">
                        <div class="col-md-10">
                            <aui:input type="text" name="<%=AdminDenunciaPortletKeys.ASUNTO%>" value="<%=denuncia.getAsunto()%>" disabled="true" >
                                <aui:validator name="required"/>
                            </aui:input>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
        <c:if test="<%=showData%>">
            <div class="row">
                <div class="col-md-12">
                    <fieldset class="input-group-inline">
                        <legend><liferay-ui:message key="denuncia.view.descripcion"/></legend>
                    </fieldset>
                    <p class="background-message p-4"><%=denuncia.getDescripcion()%></p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <fieldset class="input-group-inline">
                        <legend><liferay-ui:message key="denuncia.view.adjuntos"/></legend>
                        <ul>
                            <c:forEach items="<%=fileEntries%>" var="file">
                                <%
                                    DLFileEntry fileObj = (DLFileEntry) pageContext.getAttribute("file");
                                    DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileObj.getFileEntryId());
                                    String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
                                            dlFileEntry.getFolderId() +  "/" +dlFileEntry.getTitle() ;
                                %>
                                <li><a href="<%=url%>" target="_blank"><%=dlFileEntry.getTitle()%></a></li>
                            </c:forEach>
                        </ul>
                    </fieldset>
                </div>
            </div>
        </c:if>
        <!-- Fin tab datos consulta/denuncia-->
    </div>
</div>

<div class="row">
    <div class="col-sm-12">
        <h4><liferay-ui:message key="denuncia.edit.gestion"/></h4>
    </div>
    <div class="col-sm-12">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#acciones" role="tab" aria-controls="acciones" aria-selected="true"><liferay-ui:message key="denuncia.edit.acciones"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#comunicaciones" role="tab" aria-controls="comunicaciones" aria-selected="false"><liferay-ui:message key="denuncia.edit.comunicaciones"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="contact-tab" data-toggle="tab" href="#observaciones" role="tab" aria-controls="observaciones" aria-selected="false"><liferay-ui:message key="denuncia.edit.observaciones"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="finish-tab" data-toggle="tab" href="#finalizar" role="tab" aria-controls="finalizar" aria-selected="false"><liferay-ui:message key="denuncia.edit.finalizar"/></a>
            </li>
        </ul>

        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active m-5" id="acciones" role="tabpanel" aria-labelledby="acciones-tab"><%@ include file="accion.jspf" %></div>
            <div class="tab-pane fade m-5" id="comunicaciones" role="tabpanel" aria-labelledby="comunicaciones-tab"><%@ include file="comunicacion.jspf" %></div>
            <div class="tab-pane fade m-5" id="observaciones" role="tabpanel" aria-labelledby="observaciones-tab"><%@ include file="observacion.jspf" %></div>
            <div class="tab-pane fade m-5" id="finalizar" role="tabpanel" aria-labelledby="finish-tab"><%@ include file="finalizar.jspf" %></div>
        </div>
    </div>
</div>