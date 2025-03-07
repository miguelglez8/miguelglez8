<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado"%>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Categoria" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.GrupoEmpresa" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.GrupoEmpresaLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil"%>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Comp"%>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil"%>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Accion"%>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.AccionLocalServiceUtil"%>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil"%>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.repository.model.FileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLAppLocalServiceUtil" %>
<%@ page import="org.apache.commons.io.IOUtils" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
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

<%
long idComunicado = ParamUtil.getLong(request,"comunicadoEditId",0);
ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
long idLanguage=1;
Comunicado comunicado=ComunicadoLocalServiceUtil.fetchComunicado(idComunicado);
String entidad="";
String cif="";
String categoria="";
String nombreGestor="";
String notificado="No";
String base64Image = "";
String downloadURLProcedimiento="";
String urlId=com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil.fetchComp(comunicado.getCompId()).getUrlId();

com.canal.etico.v2.liferay.portlet.canal.manager.model.Comp companiaEtica=com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil.fetchComp(comunicado.getCompId());
if(!Validator.isBlank(companiaEtica.getProcedimientoFileId())){
    DLFileEntry dlFileEntryProcedimiento = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(companiaEtica.getProcedimientoFileId()));

    if(Validator.isNotNull(dlFileEntryProcedimiento)){
         downloadURLProcedimiento = "/documents/" + dlFileEntryProcedimiento.getGroupId() +"/"+ dlFileEntryProcedimiento.getFolderId() + "/"
                                   + dlFileEntryProcedimiento.getTitle() + "/" + dlFileEntryProcedimiento.getUuid();
    }
}

Calendar endDateCalendar = Calendar.getInstance();
endDateCalendar.setTime(new Date());
List<Accion> accionesList = null;

boolean isExistGrupo = true;
String nombreVinculacion="";
if(Validator.isNotNull(comunicado)){
    long idCompany=comunicado.getCompId();
    idLanguage=1;
    nombreVinculacion=comunicado.getVinculacion();
    String localeLang=themeDisplay.getUser().getLocale().toString();
    if(localeLang.equalsIgnoreCase("ca_ES")){
        idLanguage=2;
    }else if(localeLang.equalsIgnoreCase("en_US")){
        idLanguage=3;
    }
    accionesList = AccionLocalServiceUtil.getAllAccionesByComp(idCompany);

    if(comunicado.getVinculacion().contains("laboral") || comunicado.getVinculacion().contains("employment")){
        nombreVinculacion=LanguageUtil.get(bundle, "canal.comunicado.vinculacion.finalizada");
    }else if(comunicado.getVinculacion().contains("iniciada") || comunicado.getVinculacion().contains("started") ){
        nombreVinculacion=LanguageUtil.get(bundle, "canal.comunicado.vinculacion.noIniciada");
    }else if(comunicado.getVinculacion().contains("profesional") || comunicado.getVinculacion().contains("professional") ){
        nombreVinculacion=LanguageUtil.get(bundle, "canal.comunicado.vinculacion.profesional");
    }else{
        nombreVinculacion=LanguageUtil.get(bundle, "canal.comunicado.vinculacion.otros");
    }
    //obtenemos los nombres o nombre del gestor
    if(comunicado.getGestorId().equals("0")) {
        nombreGestor=LanguageUtil.get(bundle, "estado.datatable.filtro.activa.pendiente");
    }else{
        if(comunicado.getGestorId().contains(",")) {
            String[] allGestor=comunicado.getGestorId().split(",");
            for(int x=0;x<allGestor.length;x++){
                if(Validator.isNotNull(CanalEticoUserUtil.getUserCompany(Long.valueOf(allGestor[x]),idCompany))){
                    if(x==allGestor.length-1){
                        nombreGestor=nombreGestor+CanalEticoUserUtil.getUserCompany(Long.valueOf(allGestor[x]),idCompany).getName();
                    }else{
                        nombreGestor=CanalEticoUserUtil.getUserCompany(Long.valueOf(allGestor[x]),idCompany).getName()+", "+nombreGestor;
                    }
                }
            }
        }else{
            long idUsuario=Long.parseLong(comunicado.getGestorId());
            nombreGestor=CanalEticoUserUtil.getUserCompany(idUsuario,idCompany).getName();
        }
    }

    if(!Validator.isBlank(comunicado.getNotificacion())){
        notificado="Si";
    }
    //Obtenemos el nombre de la categoria
    if(comunicado.getCategoria()==0){
        categoria=LanguageUtil.get(bundle, "estado.datatable.filtro.activa.pendiente");
    }else{
        Categoria cate = CategoriaLocalServiceUtil.fetchCategoria(comunicado.getCategoria());
        categoria = cate.getNombre();
        if(cate.getNombre_cat().isEmpty() || cate.getNombre_eng().isEmpty()){
            categoria=cate.getNombre();
        }else if(idLanguage == 1){
            categoria=cate.getNombre();
        }else if(idLanguage == 2 && !cate.getNombre_cat().isEmpty()){
            categoria= cate.getNombre_cat();
        }else if(idLanguage == 3 && !cate.getNombre_eng().isEmpty()){
            categoria= cate.getNombre_eng();
        }
        //categoria = CategoriaLocalServiceUtil.fetchCategoria(comunicado.getCategoria()).getNombre();
        if(comunicado.getCategoria() == 6) categoria += " (" + comunicado.getCategoriaDesc() + ")";
    }
    //En caso de tener id grupo empresa mostraremos los datos de este
    if(comunicado.getIdGrupoEmpresa()>1){
        GrupoEmpresa grupoEmpresa=GrupoEmpresaLocalServiceUtil.fetchGrupoEmpresa(comunicado.getIdGrupoEmpresa());

        if(Validator.isNull(grupoEmpresa)){
            isExistGrupo = false;
            if (!Validator.isBlank(comunicado.getNombreEmpresa())){
                entidad=comunicado.getNombreEmpresa();
                cif=comunicado.getCif();
            }else{
                Comp compania=CompLocalServiceUtil.fetchComp(comunicado.getCompId()); //Ponemos la del padre
                entidad=compania.getName();
                cif=comunicado.getCif();
            }
        }else{
            if (!Validator.isBlank(comunicado.getNombreEmpresa())){
                entidad=comunicado.getNombreEmpresa();
                cif=comunicado.getCif();
            }else{
                entidad=grupoEmpresa.getEntidad();
                cif=grupoEmpresa.getCif();
            }
        }

    }else{
        Comp compania=CompLocalServiceUtil.fetchComp(comunicado.getCompId());
        entidad=compania.getName();
        cif=comunicado.getCif();
        List<GrupoEmpresa> listaGrupoEmpresa=GrupoEmpresaLocalServiceUtil.getAllGrupoEmpresaByCompId(comunicado.getCompId());
        if(Validator.isNotNull(compania.getLogo())&& listaGrupoEmpresa.size()==0){
            DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(compania.getLogo()));
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dlFileEntry.getFileEntryId());
            InputStream inputStream = fileEntry.getContentStream();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            base64Image = Base64.getEncoder().encodeToString(bytes);
        }
    }
}

%>

<c:if test="<%=Validator.isNotNull(comunicado)%>">
    <c:set var="comun" value="<%=comunicado%>" />
    <c:set var="idLanguage" value="<%=idLanguage%>" />
    <div class="hide">
        <table id="tablaRecibi" class="display " style="width:100%">
           <thead>
               <tr>
                   <th>Tabla oculta</th>
                   <th>Para exportar</th>
               </tr>
           </thead>
        </table>
    </div>
    <div id="misComunicaciones" class="detalle">

        <a href="#" class="btn-back">Mis comunicaciones</a>
        <!-- LLEGA --->
        <!-- <h1>EXISTE: <%=isExistGrupo%></h1> -->

        <div class="row d-none" id="capaErroresGrupo">
            <div class="col">

        	       <div class="" style="">
                         <div class="alert alert-dismissible alert-danger" role="alert">
        			        <liferay-ui:message key="canal.comunicado.aviso.grupoEmpresa.eliminado" />
        		        </div>
        	        </div>

            </div>
        </div>

        <h2 class="h4"><liferay-ui:message key="title.datos.generales"/></h2>
        <div class="row">
            <div class="col-md-3">
                <div class="form-group input-text-wrapper">
                    <label class="control-label" for="identificador"><liferay-ui:message key="accion.datatable.col.codigo"/></label>
                    <input type="text" class="field form-control" value="${comun.codigo}" id="identificador" disabled>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group input-text-wrapper">
                    <label class="control-label" for="fechaRecepcion"><liferay-ui:message key="label.fecha.recepcion"/></label>
                    <c:set var="formattedDate">
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${comun.createDate}" />
                    </c:set>
                    <input type="text" class="field form-control" value="${formattedDate}" id="fechaRecepcion" disabled>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group input-text-wrapper">
                    <label class="control-label" for="estado"><liferay-ui:message key="accion.datatable.col.estado"/></label>
                    <input type="text" class="field form-control" value="${comun.estado}" id="estado" disabled>
                </div>
            </div>
            <div class="col-md">
                <div class="rowButtonWrapper">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="categoria"><liferay-ui:message key="accion.datatable.col.categoria"/></label>

                        <input type="text" class="field form-control" value="<%=categoria%>" id="categoria" disabled>
                    </div>
                    <button type="button" class="btn btn-dropdown" id="mostrarOcultarDatos"><span class="collapsed"><liferay-ui:message key="label.ver.datos"/></span><span class="expanded"><liferay-ui:message key="labe.ver.menos.datos"/></span></button>
                </div>
            </div>
        </div>
        <div id="hiddenContent" class="hide">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="gestor"><liferay-ui:message key="accion.datatable.col.gestor"/></label>
                        <input type="text" class="field form-control" value="<%=nombreGestor%>" id="gestor" disabled>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="seguimientoEstado"><liferay-ui:message key="label.seguimiento"/></label>
                        <input type="text" class="field form-control" value="<%=notificado%>" id="seguimientoEstado" disabled>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="medioComunicacion"><liferay-ui:message key="label.comunicacion"/></label>
                        <input type="text" class="field form-control" value="${comun.notificacion}" id="medioComunicacion" disabled>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="fechaFin"><liferay-ui:message key="label.fecha.finalizacion"/></label>
                        <c:set var="formattedDateEnd">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${comun.endDate}" />
                        </c:set>
                        <input type="text" class="field form-control" value="${formattedDateEnd}" id="fechaFin" disabled>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="fechaCad"><liferay-ui:message key="label.fecha.caducidad"/></label>
                        <c:set var="formattedDateCaduc">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${comun.caducidadDate}" />
                        </c:set>
                        <input type="text" class="field form-control" value="${formattedDateCaduc}" id="fechaCad" disabled>
                    </div>
                </div>
            </div>
            <div id="acuseRecibo" class="prv-descarga-wrapper">
                <div class="prv-descarga">
                    <p class="prv-name" id="exportarPDF" ><liferay-ui:message key="label.acuse.recibo" arguments="${comun.codigo}"/></p>
                </div>
            </div>
            <h2 class="h4"><liferay-ui:message key="title.datos.entidad"/></h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="cif"><liferay-ui:message key="label.cif"/></label>
                        <input type="text" class="field form-control" value="<%=cif%>" id="cif" disabled>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="entidad"><liferay-ui:message key="accion.datatable.col.entidad"/></label>
                        <input type="text" class="field form-control" value="<%=entidad%>" id="entidad" disabled>
                    </div>
                </div>
            </div>

            <h2 class="h4"><liferay-ui:message key="title.datos.personales"/></h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="email"><liferay-ui:message key="label.email"/></label>
                         <c:choose>
                            <c:when test='<%=comunicado.getAnonimo()==false%>'>
                                <input type="text" class="field form-control" value="${comun.email}" id="email" disabled>
                            </c:when>
                            <c:otherwise>
                               <input type="text" class="field form-control" value='<liferay-ui:message key="canal.comunicado.edicion.avisoAnonimo"/>' id="email" disabled>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>

                <div class="col-md-2">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="nombre"><liferay-ui:message key="label.nombre"/></label>
                        <input type="text" class="field form-control" value="${comun.nombre}" id="nombre" disabled>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="apellidos"><liferay-ui:message key="label.apellidos"/></label>
                        <input type="text" class="field form-control" value="${comun.apellidos}" id="apellidos" disabled>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="nif"><liferay-ui:message key="label.nif"/></label>
                        <input type="text" class="field form-control" value="${comun.nif}" id="nif" disabled>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="telefono"><liferay-ui:message key="label.telefono"/></label>
                        <input type="text" class="field form-control" value="${comun.telefono}" id="telefono" disabled>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="correoPostal"><liferay-ui:message key="label.correo"/></label>
                        <input type="text" class="field form-control" value="${comun.direccionPostal}" id="correoPostal" disabled>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="otros"><liferay-ui:message key="label.otros"/></label>
                        <input type="text" class="field form-control" value="${comun.otros}" id="otros" disabled>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="relacionEntidad"><liferay-ui:message key="label.relacion"/></label>
                        <input type="text" class="field form-control" value="<%=nombreVinculacion%>" id="relacionEntidad" disabled>
                    </div>
                </div>
            </div>

            <h2 class="h4"><liferay-ui:message key="title.datos.contenido"/></h2>
            <div class="rowButtonWrapper">
                <div class="form-group input-text-wrapper">
                    <label class="control-label" for="asunto"><liferay-ui:message key="label.asunto"/></label>
                    <input type="text" class="field form-control" value="${comun.asunto}" id="asunto">
                </div>
                <button class="btn-acciones icon-save" onclick="saveAsunto()"><span class="sReader">Guardar</span></button>
            </div>
            <div class="form-group input-text-wrapper">
                <label class="control-label" for="descripcion"><liferay-ui:message key="label.descripcion"/></label>
                    <div id="descripcion" class="disabled isInput hasScroll" style="height: 200px; pointer-events: visible;">${comun.descripcion}</div>
            </div>
            <div id="spnSelectedFiles" class="prv-descarga-wrapper">
                <% if(!Validator.isBlank(comunicado.getAdjuntosId())) {
                    if(comunicado.getAdjuntosId().contains(",")){
                        for(String idFile:comunicado.getAdjuntosId().split(",")){
                            DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(idFile));
                            if(Validator.isNotNull(dlFileEntry)){
                                String downloadURL = "/documents/" + dlFileEntry.getGroupId() +"/"+ dlFileEntry.getFolderId() + "/"
                                        + dlFileEntry.getTitle() + "/" + dlFileEntry.getUuid()+"?download=true";
                                %>
                                <div class="prv-descarga">
                                    <a class="prv-enlace" href="<%= downloadURL %>"><%=dlFileEntry.getTitle()%></a>
                                </div>
                                <%
                            }
                        }
                    }else {
                        DLFileEntry dlFileEntryUnique = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(comunicado.getAdjuntosId()));
                        if(Validator.isNotNull(dlFileEntryUnique)){
                            String downloadURL = "/documents/" + dlFileEntryUnique.getGroupId() +"/"+ dlFileEntryUnique.getFolderId() + "/"
                                    + dlFileEntryUnique.getTitle() + "/" + dlFileEntryUnique.getUuid()+"?download=true";
                            %>
                            <div class="prv-descarga">
                                <a class="prv-enlace" href="<%= downloadURL %>"><%=dlFileEntryUnique.getTitle()%></a>
                            </div>
                            <%
                        }
                    }
                }%>
            </div>
            <div id="informacion_adicional">
                <h2 class="h4"><liferay-ui:message key="title.datos.adicional"/></h2>
                <table id="table_additionalInform" class="display" style="width:100%">
                    <thead>
                    <tr>
                        <th><liferay-ui:message key="label.fecha.recepcion"/></th>
                        <th><liferay-ui:message key="label.asunto"/></th>
                        <th><liferay-ui:message key="label.descripcion"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>

    </div>

    <h2 class="h4"><liferay-ui:message key="title.datos.gestion"/></h2>
    <div class="alert alert-primary hide" role="alert" id="warningCaducidadText">
        <p id="textFinalizarComunicado"><liferay-ui:message key="warning.finalizar.comunicado" arguments="${formattedDateCaduc}" /></p>
    </div>
    <div class="d-flex justify-content-end">
        <button class="btn btn-primary btn-new-entity" id="creacionAccionComunicado" onClick="nuevaAccionComunicado()" type="button">
            <liferay-ui:message key="label.button.accion"/>
        </button>
    </div>
    <table id="table_accion" class="display" style="width:100%">
        <thead>
        <tr>
            <th><liferay-ui:message key="label.fecha.accion"/></th>
            <th><liferay-ui:message key="accion.datatable.col.estado"/></th>
            <th><liferay-ui:message key="label.accion"/></th>
            <th><liferay-ui:message key="label.observaciones"/></th>
            <th><liferay-ui:message key="label.usuario"/></th>
            <th><liferay-ui:message key="label.notificado"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
    <div class="btn-wrapper">
        <span class="thisTooltip" title='<liferay-ui:message key="canal.comunicado.title.button"/>'><aui:button cssClass="btn btn-tertiary btn-lg btn-new-comp" disabled="true" value="label.button.finalizar" id="finalizarComunicado" onClick="mostrarModalFinalizacion()" /></span>
    </div>
    <!-- MODAL -->
    <div class="contentModal" id="modalContainer" style="border-radius: 15px; width: 650px;">
        <div class="headerModal">
            <p id="addEntidad"><span><liferay-ui:message key="label.button.accion"/></p>
        </div>
        <div class="container">
            <c:if test='<%=!Validator.isBlank(comunicado.getNotificacion()) && !comunicado.getNotificacion().contains("EMAIL")%>'>
                <div>
                     <div class="alert alert-primary" role="alert" id="warningCaducidad">
                            <p><liferay-ui:message key="text.notificado" /></p>
                    </div>
                </div>
            </c:if>

            <div class="row myrow">
                <div class="col-3">
                   <label for="<portlet:namespace/>fechaAccion" class="labelInput"><liferay-ui:message key="label.fecha"/>*</label>
                </div>
                <div class="col-6">
                   <liferay-ui:input-date name="fechaAccion" cssClass="prv-input-date" nullable="true" showDisableCheckbox="false" firstEnabledDate="<%=new Date()%>"  lastEnabledDate="<%=new Date()%>"/>
                   <p class="text-danger hide" id="invalidDate"><liferay-ui:message key="invalid.date"/></p>
                </div>
            </div>
            <div class="row myrow" id="inputSelectAccion">
                <div class="col-3">
                    <label for="<portlet:namespace/>accionesSelect" class="labelInput"><liferay-ui:message key="accion.view.title"/>*</label>
                </div>
                <div class="col-9">
                    <aui:select name="accionesSelect" label="">
                        <option value="0"><liferay-ui:message key="select.accion"/></option>
                        <c:forEach var="acci" items="<%=accionesList%>">
                            <c:if test="${empty acci.endDate}">
                                <option value="${acci.primaryKey}" status-acc="${acci.estado}">
                                    <c:choose>
                                        <c:when test="${empty acci.nombre_cat || empty acci.nombre_eng || idLanguage==1}">
                                            ${acci.nombre}
                                        </c:when>
                                        <c:when test="${not empty acci.nombre_cat && idLanguage==2}">
                                            ${acci.nombre_cat}
                                        </c:when>
                                        <c:when test="${not empty acci.nombre_eng && idLanguage==3}">
                                            ${acci.nombre_eng}
                                        </c:when>
                                    </c:choose>
                                </option>
                            </c:if>
                        </c:forEach>
                    </aui:select>
                    <p class="text-danger hide" id="invalidAccion"><liferay-ui:message key="invalid.accion"/></p>
                </div>
            </div>
            <div class="row myrow">
                <div class="col-3">
                    <label for="<portlet:namespace/>estado" class="labelInput"><liferay-ui:message key="accion.datatable.col.estado"/></label>
                </div>
                <div class="col-9">
                    <aui:input name="estado" disabled="true" label=""></aui:input>
                </div>
            </div>
            <div class="row myrow">
                <div id="brand-logo-modal" class="col-3">
                    <label for="<portlet:namespace/>observaciones" class="labelInput"><liferay-ui:message key="label.observaciones"/></label>
                </div>
                <div class="col-9">
                <aui:input name="observaciones" cssClass="inputModal" type="textarea" label="">
                    <aui:validator name="required" errorMessage="company.error.required" />
                </aui:input>
                <p class="text-danger hide" id="requiredEntidad"><liferay-ui:message key="input.required"/></p>
                </div>
            </div>
            <!-- FICHEROS -->
            <div class="row myrow">
                <div id="brand-logo-modal" class="col-3">
                        <label for="<portlet:namespace/>fichero" class="labelInput"><liferay-ui:message key="label.adjuntos"/></label>
                </div>
                <div class="col-9">
                      <button type="button" class="btn btn-outline-light" onclick="addFile('new')"
                        onkeypress="addFile('new')">
                            <liferay-ui:message key="label.ficheros"/>
                      </button>
                      <div class="d-none">
                            <input type="file" id="<portlet:namespace/>inpFile_00" name="<portlet:namespace/>inpFile_00" class="clsFile"><button type="button" onclick="deleteFile(1,'new')">Eliminar</button><br/>
                            <input type="file" id="<portlet:namespace/>inpFile_01" name="<portlet:namespace/>inpFile_01" class="clsFile"><button type="button" onclick="deleteFile(2,'new')">Eliminar</button>
                      </div>
                      <div id="spnSelectedFilesAjax"></div>
                </div>
            </div>

            <c:if test='<%=comunicado.getNotificacion().contains("EMAIL")%>'>
                <div class="row rowComplete">
                    <div class="col-12">
                    <input class="form-check-input form-check-inline" checked="true" type="checkbox" id="toggle-button">
                        <label for="toggle-button"><liferay-ui:message key="label.notificacion.check"/></label>
                    </input>
                    </div>
                </div>
            </c:if>
        </div>
         <div class="footerModal">
             <button type="button" class="btn-primary buttonModal " id="saveComAcc" onclick="saveComAcc()" data-dismiss="modal" aria-label="Close">
                <liferay-ui:message key="categoria.view.dialog.guardar"/>
             </button>

            <button type="button" class="buttonModal closeMyModal " id="btnCancel"  data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="categoria.view.dialog.cancelar"/>
            </button>
         </div>
    </div>

    <div class="contentModal alert-modal warning" id="modalContainerEnd" style="border-radius: 15px; width: 650px;">
        <div class="headerModal">
            <p id="addEntidad"><span><liferay-ui:message key="label.header.finalizar"/></p>
        </div>
        <div class="container">
            <div class="col-12 text-center">
                <p class="text-center"><liferay-ui:message key="finalizar.comunicado"/></p>
            </div>
        </div>
         <div class="footerModal">
             <button type="button" class="btn-primary buttonModal " id="endComAcc" onclick="endComAcc()" data-dismiss="modal" aria-label="Close">
                <liferay-ui:message key="label.button.finalizar"/>
             </button>

            <button type="button" class="buttonModal closeMyModal " id="btnCancelCom"  data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="categoria.view.dialog.cancelar"/>
            </button>
         </div>
    </div>




    <div class="divModal" id="modalPortlet">
    </div>



<liferay-portlet:resourceURL copyCurrentRenderParameters="<%=false %>" id="getAllAditionalComunByCodigo" var="getAllAditionalComunByCodigoURL">
    <liferay-portlet:param name="codigoComun" value="${comun.codigo}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%=false %>" id="getAllComunicadosAccion" var="getAllComunicadosAccionURL">
    <liferay-portlet:param name="idComun" value="${comun.comunicadoId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%=false %>" id="finalizarComunicado" var="finalizarComunicadoURL">
    <liferay-portlet:param name="idComun" value="${comun.comunicadoId}" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%=false %>" id="saveAsuntoComunicado" var="saveAsuntoComunicadoURL">
    <liferay-portlet:param name="idComun" value="${comun.comunicadoId}" />
</liferay-portlet:resourceURL>


<liferay-portlet:resourceURL copyCurrentRenderParameters="<%=false %>" id="createComunicadosAccion" var="createComunicadosAccionURL">

</liferay-portlet:resourceURL>

<script>
var mostrar=false;
var tabla=null;
var tablaAccion=null;
var jsonData = null;
var jsonDataAccion=null
var estadoFinalizado=false;
var canBeFinished=false;
var hoy = new Date();
    hoy.setHours(0, 0, 0);
var pendiente ="<liferay-ui:message key="estado.datatable.filtro.activa.pendiente"/>"
var array=[]
    array.push(pendiente);
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.admision"/>');
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.investigacion"/>');
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.finalizacion"/>');
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.finalizada"/>');
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.caducada"/>');
var recibiParrafo2="<liferay-ui:message key="canal.comunicado.correcto.recibi.parrafo2"/>"
var recibiParrafo2General="<liferay-ui:message key="canal.comunicado.correcto.recibi.infoGeneral.parrafo2"/>"
var recibiParrafo3="<liferay-ui:message key="canal.comunicado.correcto.recibi.infoGeneral.parrafo3"/>"
var dataRecibi= [{
   "col1" : "Oculta exportacion",
   "col2" : "Hack pdf"
}];
var urlConsulta = "<%=themeDisplay.getPortalURL()%>/web/canal-etico/registro?id=<%=urlId%>";
var urlProcedimiento = "https://" + window.location.hostname +  encodeURI("<%=downloadURLProcedimiento%>");

var estadoValue=$('#estado').val()
$('#estado').val(array[estadoValue]);

$('#mostrarOcultarDatos').on('click',function(e){
    if(!mostrar){
        $('#hiddenContent').removeClass('hide');
        $(e.currentTarget).addClass('isDrop');
        mostrar=true;
    }else{
        $('#hiddenContent').addClass('hide');
        $(e.currentTarget).removeClass('isDrop');
        mostrar=false;
    }

});

function dataDefault(){
    if(hoy.getMonth()+1<10){
        var mesDefault="0"+(hoy.getMonth()+1)
    }else{
        var mesDefault=(hoy.getMonth()+1)
    }
    var fechaDefault=hoy.getDate()+"/"+mesDefault+"/"+hoy.getFullYear();
    $("#<portlet:namespace/>fechaAccion").val(fechaDefault);
    $('#<portlet:namespace/>accionesSelect').val('0');
    $('#<portlet:namespace/>estado').val('');
    $('#<portlet:namespace/>observaciones').val('');
    $('#invalidAccion').addClass('hide');
    $('#invalidDate').addClass('hide');
}

function nuevaAccionComunicado(){
    dataDefault();
    mostrarModal();
}

function mostrarModal(){
    $('#modalPortlet').css('display','block');
    $('#modalContainer').css('display','block');
}

function mostrarModalFinalizacion(){
    $('#modalPortlet').css('display','block');
    $('#modalContainerEnd').css('display','block');
}

function crearRecibi( nombreEmpresa,codigoEmpresa, fecha,dataRecibi,imagen){

    tablaRecibi = $('#tablaRecibi').DataTable( {
        data: dataRecibi,
        "columns": [
        {"data" : "col1"},
        {"data" : "col2"}
        ],
        "columnDefs": [],
        dom: 'iBtprl',
        buttons: [
        {
        extend: 'pdfHtml5',
        text: 'HAck export recibo',
        filename: 'recibo_comunicacion',
        orientation: 'portrait',//landscape o portrait
        pageSize: 'A4', //A3 , A5 , A6 , legal , letter
        exportOptions: {
            columns : [],
        },
        customize: function ( doc ) {
            doc.content = [
                {
                    text: [
                        { text: '<liferay-ui:message key="canal.comunicado.correcto.recibi.titulo"/>'
                        , fontSize: 16, bold: true },
                        '\n',
                        '\n',
                        { text: '<liferay-ui:message key="canal.comunicado.correcto.recibi.parrafo1"/>'
                          .replace("#FECHA#",fecha),
                          fontSize: 12, color: 'black', alignment : 'justify' },
                        { text: codigoEmpresa, fontSize: 12, bold: true },
                        { text: '<liferay-ui:message key="canal.comunicado.correcto.recibi.parrafo1b"/>', fontSize: 12, bold: false },
                        '\n',
                        '\n',
                        { text: recibiParrafo2,
                            fontSize: 12, color: 'black' , alignment : 'justify'},
                        //enlace
                        { text: urlConsulta , fontSize: 12, color: 'blue', link: urlConsulta },
                        '\n',
                        '\n',
                        //'\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002\u2002'
                        { text: '<liferay-ui:message key="canal.comunicado.correcto.recibi.infoGeneral.titulo"/>'
                        , fontSize: 12, color: 'black' ,  decoration: 'underline', alignment : 'justify', decorationColor: 'black'},
                        '\n',
                        '\n',
                        { text: '<liferay-ui:message key="canal.comunicado.correcto.recibi.infoGeneral.parrafo1"/>', fontSize: 12, color: 'black' , alignment : 'justify'},
                        '\n',
                        '\n',
                        { text: recibiParrafo2General, fontSize: 12, color: 'black' , alignment : 'justify'},
                        { text: '<liferay-ui:message key="canal.comunicado.correcto.recibi.infoGeneral.parrafo2b"/>',
                            fontSize: 12, color: 'blue', link: urlProcedimiento},
                        '\n',
                        '\n',
                        { text: recibiParrafo3, fontSize: 12, color: 'black', alignment : 'justify' },
                        '\n',
                        '\n',
                        { text: '<liferay-ui:message key="canal.comunicado.correcto.recibi.infoGeneral.parrafo4"/>', fontSize: 12, color: 'black', alignment : 'justify' }
                        //{ text: 'Haz clic aquí para ir a mi sitio web', fontSize: 10, color: 'blue', link: 'https://www.tusitio.com' }

                    ],
                    //fontSize: 10,
                    //bold: true,
                    margin: [50, 100, 50, 100] // Ajusta los márgenes si lo necesitas
                },
            ];

            if(imagen != ""){
                doc['header']=(function(page, pages) {
                    return {
                        margin: [ 24, 24, 24, 24 ],
                        alignment: 'right',
                        image: 'data:image/png;base64,'+imagen+'',
                        fit: [100,100]
                        /* width: 300,
                        height: 300 */

                    }
                });
            }

            doc['footer']=(function(page, pages) {
                    return {
                        alignment: 'center',
                        fontSize: 8,
                        margin: [ 0, 0, 0, 12 ],
                        text: [nombreEmpresa],
                    }
            });
        }
    }
        ]
    });
}

$(document).ready( function () {

    $("#descripcion").html($("#descripcion").html().replaceAll('\n','</br>'))

    if("<%=isExistGrupo%>" == "false"){
        $("#capaErroresGrupo").removeClass("d-none");
    }
    if(${comun.estado}<3){
        if(document.querySelector('.thisTooltip') !== null ){
           document.querySelectorAll('.thisTooltip').forEach((elem, i) => {
               setPoptip( elem );
           });
        }
    }else{
        $('.thisTooltip').removeAttr('title');
    }
    var buttonCommon = {
        exportOptions: {
            format: {
                body: function ( data, row, column, node ) {
                    // Strip $ from salary column to make it numeric
                    if(column>=6){
                        return ;
                    }else{
                        if(node.childNodes[1]!==undefined){
                            return node.childNodes[1].textContent;
                        }else{
                            if(column==3){
                             var longitud=jsonDataAccion.length-1-row
                             return jsonDataAccion[longitud].observaciones
                            }
                            return  node.childNodes[0].textContent;
                        }
                    }
                }
            }
        }
    };



    crearRecibi("<%=entidad%>","${comun.codigo}", "${formattedDate}",dataRecibi,"<%=base64Image%>")
    $('#exportarPDF').click(function () {
        document.getElementsByClassName("buttons-pdf")[0].click()
    });
    $('#<portlet:namespace/>accionesSelect').on("change",function(){
         if($('#<portlet:namespace/>accionesSelect').val()>0){
             var estado = $('#<portlet:namespace/>accionesSelect option:selected').attr('status-acc');
             $('#<portlet:namespace/>estado').val(array[estado]);
             $('#invalidAccion').addClass('hide');
         }else{
            $('#<portlet:namespace/>estado').val('');
            $('#invalidAccion').removeClass('hide');
         }
    });

    $('.closeMyModal').click(function(){
        totalFicheros=$('.btn-delete').length
        for(var z=0;z<totalFicheros;z++){
            deleteFile(z,'new');
        }
    });

    $('.btn-back').attr('href',themeDisplay.getCanonicalURL())

    tabla = $('#table_additionalInform').DataTable( {
        dom: 'B',
        ajax: {
            "url": '${getAllAditionalComunByCodigoURL}',
            "dataSrc": function ( json ) { jsonData = json.data; return json.data; }
        },
        buttons: [

        ],
        "initComplete": function(settings, json) {
                    console.log("initComplete load")
                    if(json.data.length==0){
                        $('#informacion_adicional').css('display','none')
                    }
                    $("#spnCargando").hide();
                    $("#spnTabla").show();
        },
        "columns": [
            {"data" : "fechaRecepcion"},
            {"data" : "asunto"},
            {"data" : "descripcion"},
            {"data" : "file"} //editar
        ],
        "columnDefs": [
             {
                  "targets"  : [0],
                  "orderable": true,
                  "render": function (data, type, row) {
                      dateDatatableRecepcion = new Date(parseInt(data));
                      return "<span class='hide'>"+dateDatatableRecepcion.getTime()+"/</span><span>" + dateDatatableRecepcion.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>"
                  }
             },
             {
                "targets"  : [1],
                "orderable": true,
                "render": function (data, type, row) {
                 var maxTexto = 22;
                 if(data.length > maxTexto){
                     return data.substring(0, maxTexto) + "...";
                 }
                 return data;
                }
             },
             {
                "targets"  : [2],
                "orderable": true,
                "render": function (data, type, row) {
                  var maxTexto = 25;
                  if(data.length > maxTexto){
                      return data.substring(0, maxTexto) + "...";
                  }
                  return data;
                 }
             },
             {
                  "targets"  : [3],
                  "orderable": true,
                  "render": function (data, type, row) {
                      var cadena='<div class="acciones-wrapper">';
                      if(data.length>0){
                        for(var x=0; x<data.length;x++){
                            cadena+='<a class="hide file'+row.comAdId+'" href="'+data[x]+'" download></a>'
                        }
                        cadena+='<a class="ico-acciones-tabla descargar" onclick="descargaAdicional('+row.comAdId+')"></a>'
                      }
                      if(row.asunto.length>22 || row.descripcion.length>25){
                          cadena +='<button class="ico-acciones-tabla viewDetail" data-comAccId="' +  row.idComAcc + '"><span class="sReader">Detalle</span></button>';
                      }
                      cadena+='</div>'
                      return cadena;
             }
        }],
        responsive: true,
        pageLength: 25,
        "language": {
            "sProcessing":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sProcessing")%>",
            "sLengthMenu":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sLengthMenu")%>",
            "sZeroRecords":    "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sZeroRecords")%>",
            "sEmptyTable":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sEmptyTableAdicional")%>",
            "sInfo":           "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfo")%>",
            "sInfoEmpty":      "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoEmpty")%>",
            "sInfoFiltered":   "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoFiltered")%>",
            "sInfoPostFix":    "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoPostFix")%>",
            "sSearch":         "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sSearch")%>",
            "sUrl":            "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sUrl")%>",
            "sInfoThousands":  "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoThousands")%>,",
            "sLoadingRecords": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sLoadingRecords")%>",
            "oPaginate": {
                "sFirst":    "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sFirst")%>",
                "sLast":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sLast")%>",
                "sNext":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sNext")%>",
                "sPrevious": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sPrevious")%>"
            },
            "oAria": {
                "sSortAscending":  "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sSortAscending")%>",
                "sSortDescending": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sSortDescending")%>"
            }
        }
    });

    $("#table_additionalInform tbody").on("click", ".viewDetail", function (e) {
            var tr = $(this).closest('tr');
            var row = tabla.row(tr);

            if (row.child.isShown()) {
                $('div.slider', row.child()).slideUp( function () {
                    row.child.hide();
                    tr.removeClass('shown');
                });
               $(e.target).removeClass('eye-close');
            } else {
                row.child(formatAdicional(row.data()), 'no-padding' ).show();
                tr.addClass('shown');
                $(e.target).addClass('eye-close');
                $('div.slider', row.child()).slideDown();
            }

        });
        function formatAdicional(comunicadoAdicional) {

            return  '<div class="slider">'+
                '<table cellpadding="0" cellspacing="0" border="0">'+
                    '<tr>'+
                        '<td>Asunto:</td>'+
                        '<td>' + comunicadoAdicional["asunto"] + '</td>'+
                    '</tr>'+
                    '<tr>'+
                        '<td><liferay-ui:message key="label.descripcion"/>:</td>'+
                        '<td>' + comunicadoAdicional["descripcion"] + '</td>'+
                    '</tr>'+
                '</table>'+
            '</div>';
        }


    tablaAccion = $('#table_accion').DataTable( {
        dom: 'B',
        responsive: false,
        ajax: {
          "url": '${getAllComunicadosAccionURL}',
          "dataSrc": function ( json ) { jsonDataAccion = json.data; return json.data; }
        },
        "initComplete": function(settings, json) {
                  console.log("initComplete load")
                  if(${comun.estado}==4 || ${comun.estado}==5){
                      $('#warningCaducidadText').addClass('hide')
                      $('#creacionAccionComunicado').prop('disabled',true);
                      $('#<portlet:namespace/>finalizarComunicado').prop('disabled',true);
                  }
                  $("#spnCargando").hide();
                  $("#spnTabla").show();
        },
        "columns": [
          {"data" : "fechaAccion"},
          {"data" : "estado"},
          {"data" : "accion"},
          {"data" : "observaciones"},
          {"data" : "usuario"},
          {"data" : "notificado"},
          {"data" : "file"}//editar
        ],
         "columnDefs": [
             {
                "targets"  : [0],
                 "orderable": true,
                 "render": function (data, type, row) {
                    dateDatatableAlta = new Date(parseInt(data));
                    return "<span class='hide'>"+dateDatatableAlta.getTime()+"/</span><span>" + dateDatatableAlta.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>"
                 }
             },
             {
                "targets"  : [1],
                "orderable": true,
                "render": function (data, type, row) {
                   if(row.estado==3 && !canBeFinished){
                        $('#<portlet:namespace/>finalizarComunicado').prop('disabled',false);
                        $('#<portlet:namespace/>finalizarComunicado').removeClass('disabled');
                        $('#warningCaducidadText').removeClass('hide')
                        $('.thisTooltip').removeAttr('title');
                        $('#poptipElem_1').remove()
                        canBeFinished=true

                   }
                   return '<span class="hide">'+data+'</span>'+array[row.estado]
                }
             },
             {
                  "targets"  : [3],
                  "orderable": true,
                  "render": function (data, type, row) {
                    var maxTexto = 22;
                    if(data.length > maxTexto){
                        return data.substring(0, maxTexto) + "...";
                    }
                    return data;
                }
             },
             {
                 "targets"  : [5],
                 "orderable": true,
                 "render": function (data, type, row) {
                    if(data){
                        return 'Si'
                    }else{
                        return 'No'
                    }
                 }
             },
             {
                 "targets"  : [6],
                  "orderable": true,
                  "render": function (data, type, row) {
                    var cadena='<div class="acciones-wrapper">';
                    if(data.length>0){
                        for(var x=0; x<data.length;x++){
                            cadena+='<a class="hide file'+row.idComAcc+'" href="'+data[x]+'" download></a>'
                        }
                        cadena+='<button class="ico-acciones-tabla descargar" onclick="descargaAdicional('+row.idComAcc+')"></button>'
                    }

                    if(row.observaciones.length>22 || row.accion.length>25){
                        cadena +='<button class="ico-acciones-tabla viewDetail" data-comAccId="' +  row.idComAcc + '"><span class="sReader">Detalle</span></button>';
                    }
                    cadena+='</div>'
                    return cadena
                  }
             }
         ],
        "order":[
            [ 0, 'desc' ]
        ],
        pageLength: 25,
        buttons: [
            $.extend( true, {}, buttonCommon,{
                extend: 'csvHtml5',
                filename: "${comun.codigo}-"+"<%=entidad%>"
            }),
            $.extend( true, {}, buttonCommon,{
                extend: 'excelHtml5',
                filename: "${comun.codigo}-"+"<%=entidad%>"
            }),
            $.extend( true, {}, buttonCommon,{
                extend: 'print',
                filename: "${comun.codigo}-"+"<%=entidad%>"
            }),
            $.extend( true, {}, buttonCommon,{
                extend: 'pdfHtml5',
                orientation: 'landscape',
                filename: "${comun.codigo}-"+"<%=entidad%>"
            })
        ],

        "language": {
            "sProcessing":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sProcessing")%>",
            "sLengthMenu":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sLengthMenu")%>",
            "sZeroRecords":    "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sZeroRecords")%>",
            "sEmptyTable":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sEmptyTableAcciones")%>",
            "sInfo":           "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfo")%>",
            "sInfoEmpty":      "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoEmpty")%>",
            "sInfoFiltered":   "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoFiltered")%>",
            "sInfoPostFix":    "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoPostFix")%>",
            "sSearch":         "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sSearch")%>",
            "sUrl":            "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sUrl")%>",
            "sInfoThousands":  "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoThousands")%>,",
            "sLoadingRecords": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sLoadingRecords")%>",
            "oPaginate": {
              "sFirst":    "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sFirst")%>",
              "sLast":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sLast")%>",
              "sNext":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sNext")%>",
              "sPrevious": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sPrevious")%>"
            },
            "oAria": {
              "sSortAscending":  "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sSortAscending")%>",
              "sSortDescending": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sSortDescending")%>"
            },
            "buttons": {
              "copy": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.copy")%>",
              "colvis": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.colvis")%>"
            }
        }
    });

    $("#table_accion tbody").on("click", ".viewDetail", function (e) {
        var tr = $(this).closest('tr');
        var row = tablaAccion.row(tr);

        if (row.child.isShown()) {
            $('div.slider', row.child()).slideUp( function () {
                row.child.hide();
                tr.removeClass('shown');
            });
           $(e.target).removeClass('eye-close');
        } else {
            row.child(format(row.data()), 'no-padding' ).show();
            tr.addClass('shown');
            $(e.target).addClass('eye-close');
            $('div.slider', row.child()).slideDown();
        }

    });
    function format(comunicadoAccion) {

        return  '<div class="slider">'+
            '<table cellpadding="0" cellspacing="0" border="0">'+
                '<tr>'+
                    '<td><liferay-ui:message key="accion.view.title"/>:</td>'+
                    '<td>' + comunicadoAccion["accion"] + '</td>'+
                '</tr>'+
                '<tr>'+
                    '<td>Observaciones:</td>'+
                    '<td>' + comunicadoAccion["observaciones"] + '</td>'+
                '</tr>'+
            '</table>'+
        '</div>';
    }

});

function saveComAcc(){
    var sendPost=true
    var idComunicado=${comun.comunicadoId};
    var fechaAccion=$("#<portlet:namespace/>fechaAccion").val();
    var idAccion=$('#<portlet:namespace/>accionesSelect').val();
    var idEstado=$('#<portlet:namespace/>accionesSelect option:selected').attr('status-acc');
    var observaciones=$('#<portlet:namespace/>observaciones').val();
    var notificar=false;

    if($('#toggle-button').length>0){
        notificar=document.getElementById('toggle-button').checked
    }

    if(getValidDate(fechaAccion)==null){
         $('#invalidDate').removeClass('hide');
         sendPost=false;
    }
    if(idAccion==0){
         $('#invalidAccion').removeClass('hide');
          sendPost=false;
    }
   if(sendPost){
        var formData = new FormData();
        formData.append('inpFile_00', document.querySelector('#<portlet:namespace/>inpFile_00').files[0]);
        formData.append('inpFile_01', document.querySelector('#<portlet:namespace/>inpFile_01').files[0]);

        formData.append('<portlet:namespace/>idComunicado',idComunicado);
        formData.append('<portlet:namespace/>fechaAccion',fechaAccion);
        formData.append('<portlet:namespace/>idAccion',idAccion);
        formData.append('<portlet:namespace/>idEstado',idEstado);
        formData.append('<portlet:namespace/>observaciones',observaciones);
        formData.append('<portlet:namespace/>notificado',notificar);

        console.log("FORMDATA");
        console.log(formData);

        $.ajax({
            type: "POST",
            url: '${createComunicadosAccionURL}',
            data: formData,
            processData: false,
            contentType: false,
            success: function (result) {
              console.log("Okay: "+result)
              tablaAccion.ajax.reload()
              $('.closeMyModal').click()
          }
        });
    }
}

function endComAcc(){
    $.ajax({
        type: "POST",
        url: '${finalizarComunicadoURL}',
        success: function (result) {
            console.log("Okay: "+result)
            location.reload();
            $('.closeMyModal').click()
        }
    });


}

function saveAsunto(){
    var asuntoCom=$('#asunto').val()
    if(asuntoCom.length>0){
        $.ajax({
            type: "POST",
            url: '${saveAsuntoComunicadoURL}',
            data: {
                '<portlet:namespace/>asuntoComunicado':asuntoCom
            },
            success: function (result) {
                console.log("Okay: "+result)
                location.reload();
            }
        });
    }
}

function descargaAdicional(_idCom){
    $('.file'+_idCom+'').each(function() {
         // Simular el clic en cada enlace de descarga
         console.log($(this).get(0))
         $(this).get(0).click();
    });
}

function getValidDate(_val){
        if(_val!=""){
            try{
                var arrDate = _val.split("/");
                if(arrDate.length != 3) return null;
                if(isNaN(arrDate[0]) || isNaN(arrDate[1]) || isNaN(arrDate[2])) return null;

                dateTemp = new Date(arrDate[2] + "-" + arrDate[1] + "-" + arrDate[0]);

                return dateTemp;

            }catch(e){}
            return null;
        }
        return "";
    }

    //FICHEROS
    function addFile(_type){
                var arr = null;
                if(_type == "new"){
                    arr = document.getElementsByClassName("clsFile");
                }else{
                    arr = document.getElementsByClassName("clsFileAnadir");
                }


                for(var i = 0; i < arr.length; i++){
                    if(arr[i].value == "") {
                        arr[i].click();
                        break;
                    }
                }
                paintFiles(_type);
            }

            function deleteFile(_id, _type){
                console.log("_id: " + _id)
                var elem = document.getElementById("<portlet:namespace/>inpFile_0" + _id);
                elem.value = "";
                paintFiles(_type);
            }

    function paintFiles(_type){
                var arrFilesSelected = [];
                var clsFile = (_type == "new")? ".clsFile" : ".clsFileAnadir";
                var indice = 0;
                $(clsFile).each(function(_index){
                    indice = this.id.substring(this.id.lastIndexOf("_") + 2)
                    console.log("id nuevo : " + this.id.substring(this.id.lastIndexOf("_") + 2))

                    if(this.value != "") arrFilesSelected.push('<div class="prv-descarga"><p class="prv-name">'
                                                                + this.value.substring(this.value.lastIndexOf("\\")+1)
                                                                + '</p><button type="button" class="btn-delete" onclick="deleteFile(' + indice + ',' + "'" + _type + "'" +  ')" onkeypress="deleteFile(' + indice + ',' + "'" + _type + "'" +  ')"><span class="sReader">Eliminar</span></button>'
                                                                + "</div>");
                });


                if(_type == "new") {
                     $("#spnSelectedFilesAjax").html(arrFilesSelected.join(" "));
                }else{
                     $("#spnSelectedFilesAnadir").html(arrFilesSelected.join(" "));
                }

            }

            $(".clsFile").on("change", function(){
                paintFiles('new');
            });
            $(".clsFileAnadir").on("change", function(){
                paintFiles('anadir');
            });

</script>

</c:if>