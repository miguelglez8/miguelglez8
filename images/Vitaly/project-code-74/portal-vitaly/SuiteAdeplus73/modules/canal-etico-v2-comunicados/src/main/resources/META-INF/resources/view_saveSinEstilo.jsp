<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.GrupoEmpresa" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.GrupoEmpresaLocalServiceUtil" %>
<%
    String compIdentification = PortalUtil.getOriginalServletRequest(request).getParameter("id");
    Comp comp=null;
    long idComp=0;
    com.adeplus.liferay.portlet.suite.manager.model.Comp compania=null;
    List<Comp> compList = CompLocalServiceUtil.getAllCompByFriendlyUrl(compIdentification);
    String cif="";
    String nombreEmpresa="";
    int sizeLista=0;
    String downloadURL="";
    String downloadURLProcedimiento="";
    List<GrupoEmpresa> grupoEmpresaLista=null;
    if(compList.size()>=1){
        comp=compList.get(0);
        idComp=comp.getCompId();
        compania=com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil.fetchComp(idComp);
        cif=compania.getCif();
        nombreEmpresa=compania.getName();
        grupoEmpresaLista=GrupoEmpresaLocalServiceUtil.getAllGrupoEmpresaByCompId(idComp);
        sizeLista= grupoEmpresaLista.size();
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

    }

%>
<c:if test="<%=Validator.isNull(comp)%>">
<%@ include file="/error.jsp" %>
</c:if>

<liferay-portlet:actionURL name="/company/save" var="saveCompURL" />
<liferay-portlet:actionURL name="/aditional/save" var="saveAnadirURL" />
<portlet:resourceURL id="getComunicadoByCodigo" var="getComunicadoByCodigoURL"/>

<c:if test="<%=Validator.isNotNull(comp)%>">
    <liferay-portlet:renderURL varImpl="editURL">
        <portlet:param name="codigoComunicado" value="CODIGO_COM" />
        <portlet:param name="mvcPath" value="/additional.jsp" />
    </liferay-portlet:renderURL>
    <!-- MIOS -->
	<link rel="stylesheet" href="css/canalEticoWizard.css" />
	<link rel="stylesheet" href="css/eticoWizard.css" />



<aui:form name="frmComunicado" action="<%=saveCompURL.toString()%>" method="post" enctype="multipart/form-data">
<div class="wizardPreving">
        <div class="row">
            <div class="col-2">&nbsp;</div>
            <div class="col-8 text-center">
                <c:if test="<%=Validator.isNotNull(compania) && compania.getLogo() > 0 && sizeLista<=0%>">
                    <%
                        DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(compania.getLogo()));
                        String url = "";
                        if(Validator.isNotNull(dlFileEntry)) {
                            url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
                                    dlFileEntry.getFolderId() + "/" + dlFileEntry.getTitle();
                        }
                    %>
                    <div><img src="<%=url%>" class="img-thumbnail" /></div>
                    </c:if>
            </div>
            <div class="col-2">&nbsp;</div>
        </div>
        <!-- CAPA 0-->
        <span id="paso_0">
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-center">
                    <h2><b><liferay-ui:message key="canal.comunicado.form.paso0.titulo"/> <%=nombreEmpresa%></b></h2>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-justify">
                    <liferay-ui:message key="canal.comunicado.form.paso0.descr1"/>
                    <liferay-ui:message key="canal.comunicado.form.paso0.descr2"/>
                    <liferay-ui:message key="canal.comunicado.form.paso0.descr3"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-3">&nbsp;</div>
                <div class="col-6 text-center"><b>
                        <h2><liferay-ui:message key="canal.comunicado.form.paso0.tituloAccion"/></h2>
                    </b></div>
                <div class="col-3">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-3">&nbsp;</div>
                <div class="col-6">
                    <div clas="row">
                        <div class="col-12">
                            <div class="btn btn-light w-100" onclick="hideInpComunicadoCode()" id="btnNuevaCom"><liferay-ui:message key="canal.comunicado.form.paso0.accion1"/></div>
                        </div>
                    </div>
                    <div clas="row mt-3">
                        <div class="col-12 mt-3">
                            <div class="btn btn-light w-100" onclick="showInpComunicadoCode()" id="btnAnadirCom"><liferay-ui:message key="canal.comunicado.form.paso0.accion2"/></div>
                        </div>
                    </div>

                    <div class="row mt-3 d-none" id="dvComunicadoCode">
                        <div class="col-12 mt-3">
                            <input type="text" id="inpComunicadoCode" maxlength="8"
                            placeholder='<liferay-ui:message key="canal.comunicado.form.paso0.anadirPlaceHolder"/>'/>
                        </div>
                    </div>

                </div>
                <div class="col-3">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-12 text-center mt-5">
                    <div class="btn btn-primary setModeButton disabled" ><liferay-ui:message key="canal.comunicado.form.botones.siguiente"/></div>
                </div>
            </div>
        </span>
        <!-- END CAPA 0-->

        <!-- CAPA PASOS -->
        <span id="pasos_nums" class="d-none">
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-center">
                    <h2><b><liferay-ui:message key="canal.comunicado.form.guion.titulo"/></b></h2>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div id="spnGuion" class="dtik-proceso mt-5 mb-5 ">
                        <ul id="steps">
                            <li>
                                <span class="dtik-proceso__paso dtik-proceso__paso--seleccionado">
                                    <span class="dtik-proceso__paso__num">1</span>
                                    <span class="dtik-proceso__paso__desc"><liferay-ui:message key="canal.comunicado.form.guion.paso1"/></span>
                                </span>
                            </li>
                            <li>
                                <span class="dtik-proceso__paso ">
                                    <span class="dtik-proceso__paso__num">2</span>
                                    <span class="dtik-proceso__paso__desc"><liferay-ui:message key="canal.comunicado.form.guion.paso2"/></span>
                                </span>
                            </li>
                            <li>
                                <span class="dtik-proceso__paso  ">
                                    <span class="dtik-proceso__paso__num">3</span>
                                    <span class="dtik-proceso__paso__desc"><liferay-ui:message key="canal.comunicado.form.guion.paso3"/></span>
                                </span>
                            </li>
                            <li>
                                <span class="dtik-proceso__paso ">
                                    <span class="dtik-proceso__paso__num">4</span>
                                    <span class="dtik-proceso__paso__desc"><liferay-ui:message key="canal.comunicado.form.guion.paso4"/></span>
                                </span>
                            </li>

                        </ul>
                    </div>

                </div>
            </div>
        </span>
        <!-- END CAPA PASOS -->

        <!-- CAPA PASO AÑADIR-->
        <span id="pasos_anadirInfo" class="d-none">
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-center">
                    <liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.descr"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-left">
                    <h2><b><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.titulo"/> </b></h2>
                </div>
                <div class="col-1 ">&nbsp;</div>
            </div>
            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-left">
                    <liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.subTitulo"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-3 text-left">
                    <label><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.estado"/></label>
                    <input type="text" disabled id="inpEstadoExiste"/>
                </div>
                <div class="col-8">&nbsp;</div>
            </div>
             <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-left">
                    <label> <liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.asunto"/>*</label>
                    <input type="text"  id="asuntoAdicionalVisible" name="asuntoAdicionalVisible"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-left">
                    <label><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.descripcion"/>*</label>
                    <textarea  id="descripcionAdicionalVisible" name="descripcionAdicionalVisible"> </textarea>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-1">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <label><liferay-ui:message key="canal.comunicado.form.paso3.adjuntar"/></label><br/>
                    <div class="btn btn-light" onclick="addFile('anadir')"><liferay-ui:message key="canal.comunicado.form.paso3.adjuntarSeleccione"/></div>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row mt-1">
                <div class="col-1">&nbsp;</div>
                <div class="col-10" id="spnSelectedFilesAnadir"></div>
                <div class="col-1">&nbsp;</div>
            </div>

            <!-- <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-left">
                    <label><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.adjuntar"/></label>
                    <input type="file" id="inpFileAnadirInfo"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div> -->
            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-left">
                    <input type="checkbox" id="chkAnadirProcLeido" class="chkAnadirPoliticas"/>&nbsp;<liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.checkInformado"/></a>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-left">
                    <input type="checkbox" id="chkAnadirInfoLeido" class="chkAnadirPoliticas" />&nbsp;<liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.checkLeido"/>

                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <span id="spnAnadirInfoProteccion" class="hide">
                <div class="row mt-3">
                    <div class="col-1">&nbsp;</div>
                    <div class="col-10 text-justify">
                        <span id="spnAnadirTxtRPGD"> <liferay-ui:message key="canal.comunicado.form.paso4.checkLeido.info"/></span>
                    </div>
                    <div class="col-1">&nbsp;</div>
                </div>
            </span>
            <div class="row">
                <div class="col-12 text-center mt-5">
                    <div class="btn btn-primary sendAnadirButton disabled" ><liferay-ui:message key="canal.comunicado.form.botones.enviar"/></div>

                </div>
            </div>
        </span>
        <!-- END CAPA PASO AÑADIR-->


        <!-- CAPA 1-->
        <span id="paso_1" class="d-none">
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-center">
                    <h2><b><liferay-ui:message key="canal.comunicado.form.paso1.titulo"/></b></h2>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-justify">
                    <liferay-ui:message key="canal.comunicado.form.paso1.descr"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row mt-3">
                <div class="col-3">&nbsp;</div>
                <div class="col-6">
                    <div clas="row">
                        <div class="col-12">
                            <div class="btn btn-light w-100" onclick="showIdentificarse()"><liferay-ui:message key="canal.comunicado.form.paso1.identificarme"/></div>
                        </div>
                    </div>
                </div>
                <div class="col-3">&nbsp;</div>
            </div>
            <!-- FORM IDENTIFICARSE -->
            <span id="frmIdentificarse" class="d-none">
                <div class="row mt-3 ">
                    <div class="col-1">&nbsp;</div>
                    <div class="col-4 form-group">
                        <div class="form-group input-text-wrapper ">
                            <label class="control-label" for="inpEmail">
                                <liferay-ui:message key="canal.comunicado.form.paso1.identificarme.email"/>*
                            </label>
                            <input class="field form-control success-field inpPaso_1"
                                id="inpEmail"
                                name="inpEmail" type="text" value=""
                                aria-required="true">
                                <p class="text-danger hide" id="emailRequired">
                                    <liferay-ui:message key="canal.comunicado.form.paso1.error.email"/>
                                </p>
                        </div>
                    </div>
                    <div class="col-3 form-group">
                        <div class="form-group input-text-wrapper ">
                            <label class="control-label" for="inpNombre">
                                <liferay-ui:message key="canal.comunicado.form.paso1.identificarme.nombre"/>*
                            </label>
                            <input class="field form-control success-field inpPaso_1"
                                id="inpNombre"
                                name="inpNombre" type="text" value=""
                                aria-required="true">
                                <p class="text-danger hide" id="nombreRequired">
                                    <liferay-ui:message key="canal.comunicado.form.paso1.error.nombre"/>
                                </p>
                        </div>
                    </div>
                    <div class="col-3 form-group">
                        <div class="form-group input-text-wrapper ">
                            <label class="control-label" for="inpApellidos">
                                <liferay-ui:message key="canal.comunicado.form.paso1.identificarme.apellidos"/>*
                            </label>
                            <input class="field form-control success-field inpPaso_1"
                                id="inpApellidos"
                                name="inpApellidos" type="text" value=""
                                aria-required="true">
                                <p class="text-danger hide" id="apellidosRequired">
                                    <liferay-ui:message key="canal.comunicado.form.paso1.error.apellidos"/>
                                </p>
                        </div>
                    </div>
                    <div class="col-1">&nbsp;</div>
                </div>
                <div class="row  ">
                    <div class="col-1">&nbsp;</div>
                    <div class="col-4">
                            <div class="row  ">
                                <div class="col-6 form-group ">
                                    <div class="form-group input-text-wrapper ">
                                        <label class="control-label" for="inpNif">
                                            <liferay-ui:message key="canal.comunicado.form.paso1.identificarme.nif"/>
                                        </label>
                                        <input class="field form-control success-field"
                                            id="inpNif"
                                            name="inpNif" type="text" value=""
                                            aria-required="true">
                                    </div>
                                </div>
                                <div class="col-6 form-group">
                                    <div class="form-group input-text-wrapper ">
                                        <label class="control-label" for="inpTlfn">
                                            <liferay-ui:message key="canal.comunicado.form.paso1.identificarme.tlfn"/>
                                        </label>
                                        <input class="field form-control success-field"
                                            id="inpTlfn"
                                            name="inpTlfn" type="text" value=""
                                            aria-required="true">
                                    </div>
                                </div>
                            </div>
                        </div>

                </div>
                <!-- INFORMADO IDENTIFICADO -->
                                    <div class="row mt-3">
                                                        <div class="col-1">&nbsp;</div>
                                                        <div class="col-10">
                                                            <input type="checkbox" id="chkInformadoIdentificado" onChange="setInformadoIdentificado(this)" >
                                                                <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo"/>
                                                            </input>
                                                            <p class="text-danger hide" id="contactoRequired">
                                                                <liferay-ui:message key="canal.comunicado.form.paso1.error.informadoAnonimoSinDatos"/>
                                                            </p>
                                                        </div>
                                                        <div class="col-1">&nbsp;</div>
                                                    </div>
                                    <div class="row mt-3 d-none" id="spnInformadoIdentificado" >
                                                        <div class="col-1">&nbsp;</div>
                                                        <div class="col-3 ml-3">
                                                            <input type="checkbox" id="chkIdentificadoEmail">
                                                                <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformado.email"/>
                                                            </input>
                                                        </div>
                                                        <div class="col-3">
                                                            <input type="checkbox" id="chkIdentificadoPostal">
                                                                <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.postal"/>
                                                            </input><input type="text" id="inpPostalIdentificado" class="inpPaso_Identificado_1"/>
                                                        </div>
                                                        <div class="col-3">
                                                            <input type="checkbox" id="chkIdentificadoOtro">
                                                                <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.otro"/>
                                                            </input><input type="text" id="inpOtroIdentificado" class="inpPaso_Identificado_1" />
                                                        </div>
                                                    </div>

                                    <!-- END INFORMADO IDENTIFICADO -->
            </span>
            <!-- END FORM DE IDENTIFDICARSE-->
            <div class="row mt-3">
                <div class="col-3">&nbsp;</div>
                <div class="col-6">
                    <div clas="row mt-3">
                        <div class="col-12 mt-1">
                            <div class="btn btn-light w-100" onclick="hideIdentificarse()"><liferay-ui:message key="canal.comunicado.form.paso1.anonimo"/></div>
                        </div>
                    </div>
                </div>
                <div class="col-3">&nbsp;</div>
            </div>
            <span id="spnNoIdentificarse" class="d-none">
                <div class="row mt-3">
                    <div class="col-1">&nbsp;</div>
                    <div class="col-10 text-justify">
                        <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.descr"/>
                    </div>
                    <div class="col-1">&nbsp;</div>
                </div>
                <div class="row mt-3">
                    <div class="col-1">&nbsp;</div>
                    <div class="col-10">
                        <input type="checkbox" id="chkInformadoAnonymous" onChange="setInformadoAnonymous(this)" >
                            <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo"/>
                        </input>
                        <p class="text-danger hide" id="contactoAnonymousRequired">
                            <liferay-ui:message key="canal.comunicado.form.paso1.error.informadoAnonimoSinDatos"/>
                        </p>
                    </div>
                    <div class="col-1">&nbsp;</div>
                </div>
                <div class="row mt-3 d-none" id="spnInformadoAnonymous" >
                    <div class="col-1">&nbsp;</div>
                    <div class="col-3 ml-3">
                        <input type="checkbox" id="chkAnonymousEmail">
                            <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.email"/>
                        </input><input type="text" id="inpEmailAnonymous" class="inpPaso_anonymous_1"/>
                        <p class="text-danger hide" id="emailAnonymousRequired">
                            <liferay-ui:message key="canal.comunicado.form.paso1.error.email"/>
                        </p>
                    </div>
                    <div class="col-3">
                        <input type="checkbox" id="chkAnonymousPostal">
                            <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.postal"/>
                        </input><input type="text" id="inpPostalAnonymous" class="inpPaso_anonymous_1"/>
                    </div>
                    <div class="col-3">
                        <input type="checkbox" id="chkAnonymousOtro">
                            <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.otro"/>
                        </input><input type="text" id="inpOtroAnonymous" class="inpPaso_anonymous_1" />
                    </div>
                </div>
            </span>

            <div class="row">
                <div class="col-12 text-center mt-5">
                    <div class="btn btn-primary nextButton btnNext_paso_1 disabled" > <liferay-ui:message key="canal.comunicado.form.botones.siguiente"/></div>
                </div>
            </div>
        </span>
        <!-- END CAPA 1-->


        <!-- CAPA 2-->
        <span id="paso_2" class="d-none">
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-center">
                    <h2><b><liferay-ui:message key="canal.comunicado.form.paso2.titulo"/></b></h2>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-justify">
                    <liferay-ui:message key="canal.comunicado.form.paso2.descr"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row">
                <div class="col-2">&nbsp;</div>
                <div class="col-8">
                    <label><liferay-ui:message key="canal.comunicado.form.paso2.entidad"/></label>
                    <c:if test="<%=sizeLista<=0%>">
                        <input type="text" value= "<%=nombreEmpresa%>" disabled id="cmbEntidad"/>
                     </c:if>
                    <c:if test="<%=sizeLista>=1%>">
                        <select id="cmbEntidad" name="cmbEntidad">
                            <option value="0" id="nulo">-No seleccionado-</option>
                            <option value="1" id="<%=cif%>"><%=nombreEmpresa%></option>
                            <c:forEach var="grEmp" items="<%=grupoEmpresaLista%>">
                                <option value="${grEmp.primaryKey}" id="${grEmp.cif}">${grEmp.entidad}</option>
                            </c:forEach>
                        </select>
                        <p class="text-danger hide" id="entidadRequired">
                            <liferay-ui:message key="canal.comunicado.form.paso2.error.entidad"/>
                        </p>
                    </c:if>
                </div>
                <div class="col-2">&nbsp;</div>
            </div>

            <div class="row">
                <div class="col-2">&nbsp;</div>
                <div class="col-8">
                    <label><liferay-ui:message key="canal.comunicado.form.paso2.labelVinculacion"/>*</label>
                    <select id="cmbRelacion">
                        <option value=""><liferay-ui:message key='canal.comunicado.form.paso2.relacionNoIndicada'/></option>
                    </select>
                    <p class="text-danger hide" id="relacionRequired">
                        <liferay-ui:message key="canal.comunicado.form.paso2.error.relacion"/>
                    </p>
                </div>
                <div class="col-2">&nbsp;</div>
            </div>


            <div class="row">
                <div class="col-12 text-center mt-5">
                    <div class="btn btn-primary previousButton" ><liferay-ui:message key="canal.comunicado.form.botones.anterior"/></div>
                    <div class="btn btn-primary nextButton btnNext_paso_2 disabled" ><liferay-ui:message key="canal.comunicado.form.botones.siguiente"/></div>
                </div>
            </div>
        </span>
        <!-- END CAPA 2-->

        <!-- CAPA 3-->
        <span id="paso_3" class="d-none">
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-center">
                    <h2><b><liferay-ui:message key="canal.comunicado.form.paso3.titulo"/></b></h2>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-justify">
                    <liferay-ui:message key="canal.comunicado.form.paso3.descr"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <label><liferay-ui:message key="canal.comunicado.form.paso3.asunto"/>*</label>
                    <input type="text" value= "" id="inpAsunto" class="inpPaso_3"/>
                    <p class="text-danger hide" id="asuntoRequired">
                       <liferay-ui:message key="canal.comunicado.form.paso3.error.asunto"/>
                    </p>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-1">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <label><liferay-ui:message key="canal.comunicado.form.paso3.descrField"/>*</label>
                    <textarea id="inpDescripcion" class="inpPaso_3"></textarea>
                    <p class="text-danger hide" id="descripcionRequired">
                       <liferay-ui:message key="canal.comunicado.form.paso3.error.descripcion"/>
                    </p>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-1">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <label><liferay-ui:message key="canal.comunicado.form.paso3.adjuntar"/></label><br/>
                    <div class="btn btn-light" onclick="addFile('new')"><liferay-ui:message key="canal.comunicado.form.paso3.adjuntarSeleccione"/></div>
                    <span class="d-none">
                        <br/>
                        <input type="file" id="<portlet:namespace/>inpFile_00" name="<portlet:namespace/>inpFile_00" class="clsFile"><button type="button" onclick="deleteFile(1,'new')">Eliminar</button><br/>
                        <input type="file" id="<portlet:namespace/>inpFile_01" name="<portlet:namespace/>inpFile_01" class="clsFile"><button type="button" onclick="deleteFile(2,'new')">Eliminar</button><br/>
                        <input type="file" id="<portlet:namespace/>inpFile_02" name="<portlet:namespace/>inpFile_02" class="clsFile"><button type="button" onclick="deleteFile(3,'new')">Eliminar</button><br/>
                        <input type="file" id="<portlet:namespace/>inpFile_03" name="<portlet:namespace/>inpFile_03" class="clsFile"><button type="button" onclick="deleteFile(4,'new')">Eliminar</button><br/>
                        <input type="file" id="<portlet:namespace/>inpFile_04" name="<portlet:namespace/>inpFile_04" class="clsFile"><button type="button" onclick="deleteFile(5,'new')">Eliminar</button><br/>
                    </span>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row mt-1">
                <div class="col-1">&nbsp;</div>
                <div class="col-10" id="spnSelectedFiles"></div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row">
                <div class="col-12 text-center mt-5">
                    <div class="btn btn-primary previousButton" ><liferay-ui:message key="canal.comunicado.form.botones.anterior"/></div>
                    <div class="btn btn-primary nextButton btnNext_paso_3 disabled" ><liferay-ui:message key="canal.comunicado.form.botones.siguiente"/></div>
                </div>
            </div>
        </span>
        <!-- END CAPA 3-->

         <!-- CAPA 4-->
         <span id="paso_4" class="d-none">
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-center">
                    <h2><b><liferay-ui:message key="canal.comunicado.form.paso4.titulo"/></b></h2>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10 text-justify">
                   <p>
                        <liferay-ui:message key="canal.comunicado.form.paso4.descr1"/>
                   <p>
                        <liferay-ui:message key="canal.comunicado.form.paso4.descr2"/>
                   </p>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <h2><b><liferay-ui:message key="canal.comunicado.form.paso4.tituloEntidad"/></b></h2>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-3">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.cif"/></label>
                    <input type="text" value= "<%=cif%>" disabled id="resCIF"/>
                </div>
                <div class="col-4">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.nombreEntidad"/></label>
                    <input type="text" value= "A123456789" disabled id="resEntidad"/>
                </div>
                <div class="col-3">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.relacionEntidad"/></label>
                    <input type="text" value= "A123456789" disabled id="resVinculacion"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <h2><b><liferay-ui:message key="canal.comunicado.form.paso4.tituloPersona"/></b></h2>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-3">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.email"/></label>
                    <input type="text" value= "A123456789" disabled id="resEmail"/>
                </div>
                <div class="col-2">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.nombre"/></label>
                    <input type="text" value= "A123456789" disabled id="resNombre"/>
                </div>
                <div class="col-2">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.apellidos"/></label>
                    <input type="text" value= "A123456789" disabled id="resApellidos"/>
                </div>
                <div class="col-1">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.nif"/></label>
                    <input type="text" value= "A123456789" disabled id="resNIF"/>
                </div>
                <div class="col-2">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.tlfn"/></label>
                    <input type="text" value= "A123456789" disabled id="resTelfn"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-5">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.postal"/></label>
                    <input type="text" value= "A123456789" disabled id="resPostal"/>
                </div>
                <div class="col-3">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.otros"/></label>
                    <input type="text" value= "A123456789" disabled id="resOtros"/>
                </div>

                <div class="col-3">&nbsp;</div>
            </div>

            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <h2><b><liferay-ui:message key="canal.comunicado.form.paso4.tituloContenido"/></b></h2>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.asunto"/></label>
                    <input type="text" value= "A123456789" disabled id="resAsunto"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <label><liferay-ui:message key="canal.comunicado.form.paso4.descr"/></label>
                    <input type="text" value= "A123456789" disabled id="resDescripcion"/>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-3" >
                <div class="col-1">&nbsp;</div>
                <div class="col-10" id="spnResAdjuntos">
                    <div class="btn btn-light"> ADJUNTO  1</div>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <input type="checkbox" class="chkFinPoliticas">&nbsp;<liferay-ui:message key="canal.comunicado.form.paso4.checkInformado"/></input>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>

            <div class="row mt-3">
                <div class="col-1">&nbsp;</div>
                <div class="col-10">
                    <input id="chkLeidoRPGD" type="checkbox" class="chkFinPoliticas">&nbsp;<liferay-ui:message key="canal.comunicado.form.paso4.checkLeido"/></input>
                </div>
                <div class="col-1">&nbsp;</div>
            </div>
            <span id="spnInfoProteccion" class="hide">
                <div class="row mt-3">
                    <div class="col-1">&nbsp;</div>
                    <div class="col-10 text-justify">
                       <span id="spnTxtRPGD"> <liferay-ui:message key="canal.comunicado.form.paso4.checkLeido.info"/></span>
                    </div>
                    <div class="col-1">&nbsp;</div>
                </div>
            </span>

            <div class="row">
                <div class="col-12 text-center mt-5">
                    <div class="btn btn-primary previousButton" ><liferay-ui:message key="canal.comunicado.form.botones.anterior"/></div>
                    <!-- <div class="btn btn-primary endButton" >
                        <liferay-ui:message key="canal.comunicado.form.botones.finalizar"/>
                    </div> -->
                    <button type="submit"  class="btn btn-primary endButton  disabled" disabled>
                        <liferay-ui:message key="canal.comunicado.form.botones.finalizar"/>
                    </button>
                </div>
            </div>
        </span>
        <!-- END CAPA 4-->

    </div>

    <div class="d-none">
        <!-- <textarea id="dataJson" name="dataJson"></textarea> -->
        <aui:input name="dataJson" />
    </div>



    </aui:form>

    <span class="hide">
    <aui:form name="frmComunicadoAnadir" action="<%=saveAnadirURL.toString()%>" method="post" enctype="multipart/form-data">
        <aui:input  name="asuntoAdicional" label="canal.comunicado.form.pasoAnadirInfo.asunto"/>
    	<aui:input  name="descripcionAdicional"  type="textarea"  label="canal.comunicado.form.pasoAnadirInfo.descripcion"/>
    	<aui:input  name="codigoComunicado"/>
    	<input type="file" id="<portlet:namespace/>inpFile_0100" name="<portlet:namespace/>inpFile_0100" class="clsFileAnadir"><button type="button" onclick="deleteFile(100,'anadir')">Eliminar</button><br/>
        <input type="file" id="<portlet:namespace/>inpFile_0101" name="<portlet:namespace/>inpFile_0101" class="clsFileAnadir"><button type="button" onclick="deleteFile(101,'anadir')">Eliminar</button><br/>
        <input type="file" id="<portlet:namespace/>inpFile_0102" name="<portlet:namespace/>inpFile_0102" class="clsFileAnadir"><button type="button" onclick="deleteFile(102,'anadir')">Eliminar</button><br/>
        <input type="file" id="<portlet:namespace/>inpFile_0103" name="<portlet:namespace/>inpFile_0103" class="clsFileAnadir"><button type="button" onclick="deleteFile(103,'anadir')">Eliminar</button><br/>
        <input type="file" id="<portlet:namespace/>inpFile_0104" name="<portlet:namespace/>inpFile_0104" class="clsFileAnadir"><button type="button" onclick="deleteFile(104,'anadir')">Eliminar</button><br/>
        <aui:button id="btnEnviarNuevaInfo" label="ENVIAR" type="submit"/>
    </aui:form>
    </span>

</c:if>
    <script>
        $(document).ready(function () {

            var current_fs, next_fs, previous_fs; //fieldsets
            var opacity;
            var contPaso = 0;

            $(".inpPaso_1").on("keyup", function(){
                validatePaso_1();
            });

            $(".inpPaso_anonymous_1").on("keyup", function(){
                validatePaso_anonymous_1();
            });

            $(".inpPaso_Identificado_1").on("keyup", function(){
                validatePaso_1();
            });

            $("#chkAnonymousEmail,#chkAnonymousPostal,#chkAnonymousOtro").on("change", function(){
                validatePaso_anonymous_1();
            });

            $("#chkIdentificadoEmail,#chkIdentificadoPostal,#chkIdentificadoOtro").on("change", function(){
                validatePaso_1();
            });

            $(".chkFinPoliticas").on("change", function(){

                if($("#chkLeidoRPGD").prop("checked") == true){
                    $("#spnInfoProteccion").removeClass("hide");
                }else{
                    $("#spnInfoProteccion").addClass("hide");
                }
                if($(".chkFinPoliticas:checked").length == 2){
                    $(".endButton").removeClass("disabled");
                    $(".endButton").prop("disabled", false);
                }else{
                    $(".endButton").addClass("disabled");
                    $(".endButton").prop("disabled", true);
                }
            });
            //asunto  y descr de añadir
            $("#<portlet:namespace/>asuntoAdicionalVisible, #<portlet:namespace/>descripcionAdicionalVisible").on("keyup", function(){
               console.log('$(this).value: ' + $(this).val());
               if($(this).val().length > 0){
                    _changeAnadirPoliticas();
               }else{
                    $(".sendAnadirButton").addClass("disabled");
                    $(".sendAnadirButton").prop("disabled", true);
               }
            });
            $(".chkAnadirPoliticas").on("change", function(){
                _changeAnadirPoliticas();

            });


            function _changeAnadirPoliticas(){
                if($("#chkAnadirInfoLeido").prop("checked") == true){
                    $("#spnAnadirInfoProteccion").removeClass("hide");
                }else{
                    $("#spnAnadirInfoProteccion").addClass("hide");
                }
                if($(".chkAnadirPoliticas:checked").length == 2
                        && $("#<portlet:namespace/>asuntoAdicionalVisible").val() != ""
                            && $("#<portlet:namespace/>descripcionAdicionalVisible").val() != ""){

                    $(".sendAnadirButton").removeClass("disabled");
                    $(".sendAnadirButton").prop("disabled", false);
                }else{
                    $(".sendAnadirButton").addClass("disabled");
                    $(".sendAnadirButton").prop("disabled", true);
                }
            }
            $(".sendAnadirButton").on("click", function(){
                if($(this).hasClass("disabled")) return;
                sendAnadirInfoForm();
            });

            //enviar info nueva
            function sendAnadirInfoForm(){
                $("#<portlet:namespace/>asuntoAdicional").val($("#asuntoAdicionalVisible").val());
                $("#<portlet:namespace/>descripcionAdicional").val($("#descripcionAdicionalVisible").val());
                $("#<portlet:namespace/>codigoComunicado").val($("#inpComunicadoCode").val());
                $("#<portlet:namespace/>btnEnviarNuevaInfo").click();
            }



            if($("#cmbEntidad").children('option').length > 0){
                $("#cmbEntidad").on("change", function(){
                    validatePaso_2();
                });
            }

            $("#cmbRelacion").on("change", function(){
                validatePaso_2();
            });
            $(".inpPaso_3").on("keyup", function(){
                validatePaso_3();
            });

            var options = $('#cmbEntidad option');
            var arr = options.map(function(_, o) {
                    return {
                        t: $(o).text(),
                        v: o.value
                    };
                }).get();
            arr.sort(function(o1, o2) {
                var t1 = o1.t.toLowerCase(), t2 = o2.t.toLowerCase();
                return t1 > t2 ? 1 : t1 < t2 ? -1 : 0;
            });
            options.each(function(i, o) {
                    o.value = arr[i].v;
                    $(o).text(arr[i].t);
            });

            //agregar las relaciones
            var arrRelaciones = [<liferay-ui:message key="canal.comunicado.form.paso2.arrRelaciones"/>];
            var selectRelaciones = document.getElementById('cmbRelacion');
            for (var i = 0; i < arrRelaciones.length; i++){
               var opt = document.createElement('option');
               opt.value = arrRelaciones[i];
               opt.innerHTML = arrRelaciones[i];
               selectRelaciones.appendChild(opt);
            }

            //enlaces a documentos

            $(".clsProcCom").prop("href", "<%=downloadURLProcedimiento%>");
            $(".clsProteccionDatos").prop("href", "<%=downloadURL%>");

            //texto RPGD
             $("#spnTxtRPGD").html($("#spnTxtRPGD").html().replaceAll("#RAZONSOCIAL#", "<%=nombreEmpresa%>"));
             $("#spnAnadirTxtRPGD").html($("#spnTxtRPGD").html().replaceAll("#RAZONSOCIAL#", "<%=nombreEmpresa%>"));

            $(".nextButton").on("click", function () {
                contPaso++;

                if($(this).hasClass("disabled")){
                    return;
                }


                console.log("contPaso: " + contPaso);
                current_fs = $(this).parent().parent().parent();
                next_fs = $(this).parent().parent().parent().next();
                //$("#steps li > span").eq($("fieldset").index(next_fs) - 1).addClass("dtik-proceso__paso--hecho");
                //$("#steps li > span").eq($("fieldset").index(next_fs)).addClass("dtik-proceso__paso--seleccionado");

                //next_fs.show();
                next_fs.removeClass("d-none");
                current_fs.addClass("d-none");

                getDataForm();
            });

            $(".previousButton").click(function () {

                contPaso--;
                console.log("contPaso: " + contPaso);


                current_fs = $(this).parent().parent().parent();
                previous_fs = $(this).parent().parent().parent().prev();

                console.log("(PREV) current_fs.id::: " + current_fs.attr('id'));
                console.log("(PREV) previous_fs.id::: " + previous_fs.attr('id'));

                /*$("#steps li > span").eq($("fieldset").index(current_fs)).removeClass("dtik-proceso__paso--seleccionado");
                $("#steps li > span").eq($("fieldset").index(current_fs)).removeClass("dtik-proceso__paso--hecho");
                $("#steps li > span").eq($("fieldset").index(previous_fs)).addClass("dtik-proceso__paso--seleccionado");
                $("#steps li > span").eq($("fieldset").index(previous_fs)).removeClass("dtik-proceso__paso--hecho");*/



                //previous_fs.show();
                previous_fs.removeClass("d-none");
                previous_fs.removeClass("d-none");
                current_fs.addClass("d-none");

            });


            $(".setModeButton").click(function () {

                if($(this).hasClass("disabled")) return;

                $("#paso_0").addClass("d-none");

                if(!isAnadirInfo){
                    $("#pasos_anadirInfo").addClass("d-none");
                    $("#paso_0").addClass("d-none");
                    $("#pasos_nums").removeClass("d-none");
                    $("#paso_1").removeClass("d-none");

                }else{
                    $("#pasos_anadirInfo").removeClass("d-none");
                    $("#paso_0").addClass("d-none");

                }
            });



        });


        /********************************/
        var anonymous = false;
        var isAnadirInfo = false;
        var urlActionAnadir = "<%=saveAnadirURL%>";
        var urlActionNew = "<%=saveCompURL%>";

        function showInpComunicadoCode(){
            $("#dvComunicadoCode").removeClass("d-none");
            isAnadirInfo = true;
            //$('#<portlet:namespace/>frmComunicado').attr('action', '<%=saveAnadirURL%>>');
            $("#btnNuevaCom").removeClass("btn-seleccionado");
            $("#btnAnadirCom").addClass("btn-seleccionado");
            $(".setModeButton").addClass("disabled");
            console.log( "Action: " + $('#<portlet:namespace/>frmComunicado').attr('action'))
        }
        function hideInpComunicadoCode(){
            $("#dvComunicadoCode").addClass("d-none");
            isAnadirInfo = false;
            //$('#<portlet:namespace/>frmComunicado').attr('action', '<%=saveCompURL%>>');
            $("#btnNuevaCom").addClass("btn-seleccionado");
            $("#btnAnadirCom").removeClass("btn-seleccionado");
            $(".setModeButton").removeClass("disabled");
            console.log( "Action: " + $('#<portlet:namespace/>frmComunicado').attr('action'))
        }

        function showIdentificarse(){
            anonymous = false;
            //validatePaso_1();
            $("#frmIdentificarse").removeClass("d-none");
            $("#spnNoIdentificarse").addClass("d-none");
        }
        function hideIdentificarse(){
            anonymous = true;
            $("#frmIdentificarse").addClass("d-none");
            $("#spnNoIdentificarse").removeClass("d-none");
            $(".btnNext_paso_1").removeClass("disabled");

        }
        function setInformadoAnonymous(_node){
            //validatePaso_1();
            validatePaso_anonymous_1();
            if(_node.checked){
                $("#spnInformadoAnonymous").removeClass("d-none");
            }else{
                $("#spnInformadoAnonymous").addClass("d-none");
                $(".btnNext_paso_1").removeClass("disabled");
            }
        }
        function setInformadoIdentificado(_node){
                    //validatePaso_1();
            if(_node.checked){
                $("#spnInformadoIdentificado").removeClass("d-none");
            }else{
                $("#spnInformadoIdentificado").addClass("d-none");
            }
        }



        /*Ficheros*/
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
                if(this.value != "") arrFilesSelected.push('<div class="btn btn-light">'
                                                            + this.value.substring(this.value.lastIndexOf("\\")+1)
                                                            + '<div class="btn" onclick="deleteFile(' + indice + ',' + "'" + _type + "'" +  ')">X</div>'
                                                            + "</div>");
            });

            if(_type == "new") {
                 $("#spnSelectedFiles").html(arrFilesSelected.join(" "));
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



        function isEmailCorrect(_mail) {
            if(_mail.trim() == "") _mail = "noesvalido";
            var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            return regex.test(_mail);
        }


        function validatePaso_1(){
            $(".btnNext_paso_1").addClass("disabled");
            var isOk = true;
            if(!anonymous){
                isOk =  _validatePaso_1_noAnonymous();
            }else{
                isOk = validatePaso_anonymous_1();
            }
            if(isOk){
               $(".btnNext_paso_1").removeClass("disabled");
            }
        }

        function _validatePaso_1_noAnonymous(){
            var isOk = true;
            if(!isEmailCorrect($("#inpEmail").val())){
                isOk = false;
                $("#emailRequired").removeClass("hide");
            }else{
                $("#emailRequired").addClass("hide");
            }

            if($("#inpNombre").val().trim() == ""){
                isOk = false;
                $("#nombreRequired").removeClass("hide");
            }else{
                $("#nombreRequired").addClass("hide");
            }
            if($("#inpApellidos").val().trim() == ""){
                isOk = false;
                $("#apellidosRequired").removeClass("hide");
            }else{
                $("#apellidosRequired").addClass("hide");
            }
            if($("#chkInformadoIdentificado").prop("checked") == true){

                if($("#chkIdentificadoPostal").prop("checked") == true && $("#inpPostalIdentificado").val().trim() == "") isOk = false;

                if($("#chkIdentificadoOtro").prop("checked") == true && $("#inpOtroIdentificado").val().trim() == "") isOk = false;

                if($("#chkIdentificadoEmail").prop("checked") == false && $("#chkIdentificadoPostal").prop("checked") == false && $("#chkIdentificadoOtro").prop("checked") == false) isOk = false;

            }

            if($("#chkIdentificadoPostal").prop("checked") == false) $("#inpPostalIdentificado").val('');
            if($("#chkIdentificadoOtro").prop("checked") == false) $("#inpOtroIdentificado").val('');


            console.log("isOk: " + isOk)
            return isOk;
        }

        function validatePaso_anonymous_1(){
            var isOk = true;
            $("#contactoAnonymousRequired").addClass("hide");

            if($("#chkInformadoAnonymous").prop("checked") == true){
                if($("#chkAnonymousEmail").prop("checked") == true){
                    $("#emailAnonymousRequired").addClass("hide");
                    if(!isEmailCorrect($("#inpEmailAnonymous").val().trim())){
                        $("#emailAnonymousRequired").removeClass("hide");
                        isOk = false;
                    }
                }

                if($("#chkAnonymousPostal").prop("checked") == true && $("#inpPostalAnonymous").val().trim() == "") isOk = false;

                if($("#chkAnonymousOtro").prop("checked") == true && $("#inpOtroAnonymous").val().trim() == "") isOk = false;

                if($("#chkAnonymousEmail").prop("checked") == false && $("#chkAnonymousPostal").prop("checked") == false && $("#chkAnonymousOtro").prop("checked") == false) isOk = false;

                console.log("isOk: " + isOk)
                if(!isOk){
                    //$("#contactoAnonymousRequired").removeClass("hide");
                    $(".btnNext_paso_1").addClass("disabled");
                }else{
                    $(".btnNext_paso_1").removeClass("disabled");
                }
                if($("#chkAnonymousPostal").prop("checked") == false) $("#inpPostalAnonymous").val('');
                if($("#chkAnonymousOtro").prop("checked") == false) $("#inpOtroAnonymous").val('');
                if($("#chkAnonymousEmail").prop("checked") == false) $("#inpEmailAnonymous").val('');


            }
            return isOk;
        }

        function validatePaso_2(){
            $(".btnNext_paso_2").addClass("disabled");
            if($("#cmbEntidad").children('option').length > 0){ //es un select

                if($("#cmbEntidad").val() == "0"){
                    $("#entidadRequired").removeClass("hide");
                    return false;
                }else{
                    $("#entidadRequired").addClass("hide");
                }
            }

            if($("#cmbRelacion").val().trim() != ""){
                $("#relacionRequired").addClass("hide");
                $(".btnNext_paso_2").removeClass("disabled");
                return true;
            }else{
                $("#relacionRequired").removeClass("hide");
            }
            return false;
        }
        function validatePaso_3(){
            var isOk = true;
            $(".btnNext_paso_3").addClass("disabled");
            $("#asuntoRequired").addClass("hide");
            $("#descripcionRequired").addClass("hide");
            if($("#inpAsunto").val().trim() == ""){
                //$("#asuntoRequired").removeClass("hide");
                isOk = false;
            }else if($("#inpDescripcion").val().trim() == ""){
                 //$("#descripcionRequired").removeClass("hide");
                 isOk = false;
            }
            if(isOk){
                $(".btnNext_paso_3").removeClass("disabled");
                return true;
            }
            return false;
        }
        function validatePaso_4(){
            return true;
        }


        /*Recoger datos*/
        var dataJson = {};
        function getDataForm(){
            paintFiles('new');
            dataJson.compId = <%=idComp%>; // DE PRUEBA
            dataJson.isAnadirInfo = isAnadirInfo;
            dataJson.anonymous = anonymous;

            //Paso 1
            if(!anonymous){
                //... identificado
                dataJson.email = $("#inpEmail").val();
                dataJson.nombre = $("#inpNombre").val();
                dataJson.apellidos = $("#inpApellidos").val();
                dataJson.nif = $("#inpNif").val();
                dataJson.tlfn = $("#inpTlfn").val();
                dataJson.informadoAnonymous = false;
                dataJson.emailAnonymous = "";
                dataJson.postalAnonymous = "";
                dataJson.otroAnonymous = "";
                if($("#chkInformadoIdentificado").prop("checked") == true){ //usamos los mismos campos para comunicaciones que en anonimo
                    dataJson.informadoAnonymous = true;
                    dataJson.emailAnonymous = $("#inpEmail").val();
                    dataJson.postalAnonymous = $("#inpPostalIdentificado").val();
                    dataJson.otroAnonymous = $("#inpOtroIdentificado").val();
                }

            }else{
                //... anonimo
                dataJson.email = "";
                dataJson.nombre = "";
                dataJson.apellidos = "";
                dataJson.nif = "";
                dataJson.tlfn = "";
                dataJson.emailAnonymous = $("#inpEmailAnonymous").val();
                dataJson.postalAnonymous = $("#inpPostalAnonymous").val();
                dataJson.otroAnonymous = $("#inpOtroAnonymous").val();

            }
            dataJson.toBeInformado = false;
            if($("#chkInformadoIdentificado").prop("checked") == true
                    || $("#chkInformadoAnonymous").prop("checked") == true){
                dataJson.toBeInformado = true;
            }

            dataJson.toNotificado = "";
            if($("#chkIdentificadoEmail").prop("checked") == true || $("#chkInformadoAnonymous").prop("checked") == true){
                dataJson.toNotificado +=  "EMAIL ";
            }
            if($("#chkIdentificadoPostal").prop("checked") == true || $("#chkAnonymousPostal").prop("checked") == true){
                dataJson.toNotificado +=  "POSTAL ";
            }
            if($("#chkIdentificadoOtro").prop("checked") == true || $("#chkAnonymousOtro").prop("checked") == true){
                dataJson.toNotificado +=  "OTRO";
            }



            //Paso 2
            dataJson.relacion = $("#cmbRelacion").val();

            //Paso 3
            dataJson.asunto = $("#inpAsunto").val();
            dataJson.descripcion = $("#inpDescripcion").val();

            //Paso 4 ... rellenar resumen
            if("<%=sizeLista>=1%>" == "true"){
                $("#resCIF").val($("#cmbEntidad option:selected").attr('id'));
                $("#resEntidad").val($("#cmbEntidad option:selected").text());
                dataJson.isGroup = true;
                dataJson.cif = $("#cmbEntidad option:selected").attr('id');
                dataJson.idEmpresaGrupo = $("#cmbEntidad option:selected").val();
                dataJson.nombreEmpresa = $("#cmbEntidad option:selected").text();
            }else{
                $("#resCIF").val('<%=cif%>');
                $("#resEntidad").val("<%=nombreEmpresa%>");
                dataJson.isGroup = false;
                dataJson.idEmpresaGrupo = 0;
                dataJson.cif = '<%=cif%>';
                dataJson.nombreEmpresa = "<%=nombreEmpresa%>";
            }

            $("#resVinculacion").val($("#cmbRelacion option:selected").text());

            $("#resEmail").val( (anonymous)? $("#inpEmailAnonymous").val() : $("#inpEmail").val());
            $("#resNombre").val(dataJson.nombre);
            $("#resApellidos").val(dataJson.apellidos);
            $("#resNIF").val(dataJson.nif);
            $("#resTelfn").val(dataJson.tlfn);
            $("#resPostal").val(dataJson.postalAnonymous);
            $("#resOtros").val(dataJson.otroAnonymous);
            $("#resAsunto").val($("#inpAsunto").val());
            $("#resDescripcion").val($("#inpDescripcion").val());

            //Listado de adjuntos
            var arrFilesSelected = [];
            $(".clsFile").each(function(){
                if(this.value != "") arrFilesSelected.push('<div class="btn btn-light">' + this.value.substring(this.value.lastIndexOf("\\")+1) + "</div>");
            });

            $("#spnResAdjuntos").html(arrFilesSelected.join(" "));
            console.log("DATAJSON :");
            console.log(dataJson);
            $("#<portlet:namespace/>dataJson").val(JSON.stringify(dataJson));
        }

        var comunicadoJson = null;
        $("#inpComunicadoCode").on("keyup paste", function(){
            if($(this).val().length >= 8){
                console.log("comprobar código ..");
                isCorrectCode($(this).val());
            }else{
                $(".setModeButton").addClass("disabled");
            }
        });

        /*$("#inpComunicadoCode").on("paste", function(){
            console.log("comprobar código: " + $(this).val());
            if(isCorrectCode($(this).val())){
                $(".setModeButton").addClass("disabled");
            }
        });*/

        var arrEstados = '<liferay-ui:message key="canal.comunicado.estados"/>';
        arrEstados = arrEstados.split(",");
        function isCorrectCode(_value){
            $.ajax('<%=getComunicadoByCodigoURL.toString()%>&idComp='+<%=idComp%> +'&comunicadoCode=' + _value,
            {
                dataType: 'json',
                success: function (response,status,xhr) {
                    /*console.log("CORRECTO...")
                    console.log(response)*/
                    console.log("data.estado : " + response.data[0].estado);
                    console.log("data.estado NUMBER: " + Number(response.data[0].estado));
                    comunicadoJson = response;
                    $("#inpEstadoExiste").val(arrEstados[Number(response.data[0].estado)]);
                    $("#spIdentificador").html(_value);
                    $(".setModeButton").removeClass("disabled");
                },
                error: function (jqXhr, textStatus, errorMessage) {
                    console.log("ERROR")
                    $(".setModeButton").addClass("disabled");
                    comunicadoJson = null;
                    alert("Comunicado no encontrado")
                }
            });
        }

    </script>






