<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil" %>
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
    if(compList.size()==0){
        if (Validator.isNumber(compIdentification)) {
            comp=CompLocalServiceUtil.fetchComp(Long.parseLong(compIdentification));
            if(Validator.isNotNull(comp)){
                compList.add(comp);
            }
        }
    }
    if(compList.size()>=1){
        comp=compList.get(0);
        idComp=comp.getCompId();
        compania=CanalEticoUserUtil.getCompanyByEmpresaId(idComp);
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
        <div class="form-group input-text-wrapper">
            <select id="languageForm" class="field form-control">
                <option value="" disabled="" selected="" hidden=""><liferay-ui:message key='selector.language'/></option>
                <option value="es">ES</option>
                <option value="ca">CA</option>
                <option value="en">EN</option>
            </select
        </div>

        <c:if test="<%=Validator.isNotNull(compania) && compania.getLogo() > 0 && sizeLista<=0%>">
            <div class="img-thumbnail-frame">

                <%
                    DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(compania.getLogo()));
                    String url = "";
                    if(Validator.isNotNull(dlFileEntry)) {
                        url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
                                dlFileEntry.getFolderId() + "/" + dlFileEntry.getTitle();
                    }
                %>
                <img src="<%=url%>" class="img-thumbnail" alt=""/>
            </div>
        </c:if>
        <!-- CAPA 0-->
        <div id="paso_0">

            <h2 class="pasoTitle"><liferay-ui:message key="canal.comunicado.form.paso0.titulo"/> <%=nombreEmpresa%></h2>
            <p><liferay-ui:message key="canal.comunicado.form.paso0.descr1"/></p>
            <p><liferay-ui:message key="canal.comunicado.form.paso0.descr2"/></p>
            <c:choose>
              <c:when test="<%=Validator.isBlank(downloadURLProcedimiento)%>">
                <p><liferay-ui:message key="canal.comunicado.form.paso0.descr3.noEnlace"/></p>
              </c:when>
              <c:otherwise>
                <p><liferay-ui:message key="canal.comunicado.form.paso0.descr3"/></p>
              </c:otherwise>
            </c:choose>
            <h3><liferay-ui:message key="canal.comunicado.form.paso0.tituloAccion"/></h3>
            <div class="wizardOptions">
                <button type="button" class="btn btn-light" onclick="hideInpComunicadoCode()" onkeypress="hideInpComunicadoCode()" id="btnNuevaCom"><liferay-ui:message key="canal.comunicado.form.paso0.accion1"/></button>
                <button type="button" class="btn btn-light" onclick="showInpComunicadoCode()" onkeypress="showInpComunicadoCode()" id="btnAnadirCom"><liferay-ui:message key="canal.comunicado.form.paso0.accion2"/></button>
                <div class="wizardOption d-none" id="dvComunicadoCode">
                    <div class="inputContainer">
                        <label for="inpComunicadoCode" class="sReader"><liferay-ui:message key="canal.comunicado.form.paso0.anadirPlaceHolder"/></label>
                        <input type="text" id="inpComunicadoCode" maxlength="8" placeholder="<liferay-ui:message key="canal.comunicado.form.paso0.anadirPlaceHolder"/>">
                    </div>
                </div>
            </div>
            <div class="wizard-buttons">
                <button type="button" class="btn btn-tertiary setModeButton disabled"><liferay-ui:message key="canal.comunicado.form.botones.siguiente"/></button>
            </div>

        </div>
        <!-- END CAPA 0-->

        <!-- CAPA PASOS -->
        <div id="pasos_nums" class="d-none">
            <div class="pasos_title_wrapper">
                <h2><liferay-ui:message key="canal.comunicado.form.guion.titulo"/></h2>
            </div>
            <div id="spnGuion" class="dtik-proceso">
                <ul id="steps">
                    <li>
                        <span class="dtik-proceso__paso dtik-proceso__paso--seleccionado" id="contPaso_0">
                            <span class="dtik-proceso__paso__num">1</span>
                            <span class="dtik-proceso__paso__desc"><liferay-ui:message key="canal.comunicado.form.guion.paso1"/></span>
                        </span>
                    </li>
                    <li>
                        <span class="dtik-proceso__paso " id="contPaso_1">
                            <span class="dtik-proceso__paso__num">2</span>
                            <span class="dtik-proceso__paso__desc"><liferay-ui:message key="canal.comunicado.form.guion.paso2"/></span>
                        </span>
                    </li>
                    <li>
                        <span class="dtik-proceso__paso  " id="contPaso_2">
                            <span class="dtik-proceso__paso__num">3</span>
                            <span class="dtik-proceso__paso__desc"><liferay-ui:message key="canal.comunicado.form.guion.paso3"/></span>
                        </span>
                    </li>
                    <li>
                        <span class="dtik-proceso__paso " id="contPaso_3">
                            <span class="dtik-proceso__paso__num">4</span>
                            <span class="dtik-proceso__paso__desc"><liferay-ui:message key="canal.comunicado.form.guion.paso4"/></span>
                        </span>
                    </li>
                </ul>
            </div>
        </div>
        <!-- END CAPA PASOS -->

        <!-- CAPA PASO AÑADIR-->
        <div id="pasos_anadirInfo" class="d-none">
            <c:choose>
              <c:when test="<%=Validator.isBlank(downloadURLProcedimiento)%>">
                <p><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.descr.noEnlace"/></p>
              </c:when>
              <c:otherwise>
                 <p><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.descr"/></p>
              </c:otherwise>
            </c:choose>
            <h2 class="pasoSubtitle"><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.titulo"/></h2>
            <p><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.subTitulo"/></p>
            <div class="wizardOptions">
                <div class="wizardOption">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label disabled" for="inpEstadoExiste"><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.estado"/></label>
                        <input type="text" class="field form-control" id="inpEstadoExiste" disabled>
                    </div>
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="asuntoAdicionalVisible"><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.asunto"/>*</label>
                        <input type="text" class="field form-control" id="asuntoAdicionalVisible" name="asuntoAdicionalVisible">
                    </div>
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="descripcionAdicionalVisible"><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.descripcion"/>*</label>
                        <textarea id="descripcionAdicionalVisible"
                        style='font-family: "Inter", "Ubuntu", sans-serif;font-size: 0.875rem;'
                        name="descripcionAdicionalVisible" class="field form-control"></textarea>
                    </div>
                    <div class="form-group input-text-wrapper">
                        <p class="control-label form-label"><liferay-ui:message key="canal.comunicado.form.paso3.adjuntar"/></p>
                        <button type="button" class="btn btn-outline-light" onclick="addFile('anadir')" onkeypress="addFile('anadir')"><liferay-ui:message key="canal.comunicado.form.paso3.adjuntarSeleccione"/></button>
                        <div class="d-none">
                            <!-- ¿input tipo file? -->
                        </div>
                        <div id="spnSelectedFilesAnadir"></div>
                    </div>
                    <!-- <div class="row mt-3">
                        <div class="col-1">&nbsp;</div>
                        <div class="col-10 text-left">
                            <label><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.adjuntar"/></label>
                            <input type="file" id="inpFileAnadirInfo"/>
                        </div>
                        <div class="col-1">&nbsp;</div>
                    </div> -->
                </div>
                <div class="wizardOption">
                    <div class="form-group form-check">
                        <input type="checkbox" id="chkAnadirProcLeido" class="form-check-input chkAnadirPoliticas">
                        <c:choose>
                          <c:when test="<%=Validator.isBlank(downloadURLProcedimiento)%>">
                           <label for="chkAnadirProcLeido"><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.checkInformado.noEnlace"/></label>
                          </c:when>
                          <c:otherwise>
                            <label for="chkAnadirProcLeido"><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.checkInformado"/></label>
                          </c:otherwise>
                        </c:choose>

                    </div>
                    <div class="form-group form-check">
                        <input id="chkAnadirInfoLeido" type="checkbox" class="form-check-input chkAnadirPoliticas">
                        <label for="chkAnadirInfoLeido"><liferay-ui:message key="canal.comunicado.form.pasoAnadirInfo.checkLeido"/></label>
                        <div id="spnAnadirInfoProteccion" class="hide">
                            <p id="spnAnadirTxtRPGD"><liferay-ui:message key="canal.comunicado.form.paso4.checkLeido.info"/></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="wizard-buttons">
                <button type="button" class="btn btn-tertiary sendAnadirButton disabled" ><liferay-ui:message key="canal.comunicado.form.botones.enviar"/></button>
            </div>

        </div>
        <!-- END CAPA PASO AÑADIR-->


        <!-- CAPA 1-->
        <div id="paso_1" class="d-none">

            <h3 class="pasoTitle"><liferay-ui:message key="canal.comunicado.form.paso1.titulo"/></h3>
            <c:choose>
              <c:when test="<%=Validator.isBlank(downloadURLProcedimiento)%>">
                <p><liferay-ui:message key="canal.comunicado.form.paso1.descr.noEnlace"/></p>
              </c:when>
              <c:otherwise>
                <p><liferay-ui:message key="canal.comunicado.form.paso1.descr"/></p>
              </c:otherwise>
            </c:choose>
            <!-- FORM IDENTIFICARSE -->
            <div class="wizardOptions">
                <button type="button" class="btn btn-light" onclick="showIdentificarse()" onkeypress="showIdentificarse()" id="btnIdentificarse"><liferay-ui:message key="canal.comunicado.form.paso1.identificarme"/></button>
                <div id="frmIdentificarse" class="wizardOption d-none">
                    <div class="row">
                        <div class="col-5">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="inpEmail"><liferay-ui:message key="canal.comunicado.form.paso1.identificarme.email"/>*</label>
                                <input class="field form-control success-field inpPaso_1" id="inpEmail" name="inpEmail" type="text" value="" aria-required="true">
                                <p class="text-danger hide" id="emailRequired"><liferay-ui:message key="canal.comunicado.form.paso1.error.email"/></p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="inpNombre"><liferay-ui:message key="canal.comunicado.form.paso1.identificarme.nombre"/>*</label>
                                <input class="field form-control success-field inpPaso_1" id="inpNombre" name="inpNombre" type="text" value="" aria-required="true">
                                <p class="text-danger hide" id="nombreRequired"><liferay-ui:message key="canal.comunicado.form.paso1.error.nombre"/></p>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="inpApellidos"><liferay-ui:message key="canal.comunicado.form.paso1.identificarme.apellidos"/>*</label>
                                <input class="field form-control success-field inpPaso_1" id="inpApellidos" name="inpApellidos" type="text" value="" aria-required="true">
                                <p class="text-danger hide" id="apellidosRequired"><liferay-ui:message key="canal.comunicado.form.paso1.error.apellidos"/></p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <div class="row">
                                <div class="col-5">
                                    <div class="form-group input-text-wrapper">
                                        <label class="control-label" for="inpNif"><liferay-ui:message key="canal.comunicado.form.paso1.identificarme.nif"/></label>
                                        <input class="field form-control success-field" id="inpNif" name="inpNif" type="text" value="" aria-required="true">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group input-text-wrapper">
                                        <label class="control-label" for="inpTlfn"><liferay-ui:message key="canal.comunicado.form.paso1.identificarme.tlfn"/></label>
                                        <input class="field form-control success-field" id="inpTlfn" name="inpTlfn" type="text" value="" aria-required="true">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- INFORMADO IDENTIFICADO -->
                    <div class="form-group form-check">
                        <input type="checkbox" id="chkInformadoIdentificado" class="form-check-input" onchange="setInformadoIdentificado(this)">
                        <label for="chkInformadoIdentificado">
                            <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo"/>
                            <span class="showCheckedIdentificado hide">
                                &nbsp;<liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.aviso"/>
                            </span>
                        </label>

                        <p class="text-danger hide" id="contactoRequired"><liferay-ui:message key="canal.comunicado.form.paso1.error.informadoAnonimoSinDatos"/></p>
                    </div>

                    <!-- alineación personalizada -->
                    <div class="flexRow d-none" id="spnInformadoIdentificado">
                        <div class="form-group form-check">
                            <input type="checkbox" id="chkIdentificadoEmail" class="form-check-input">
                            <label for="chkIdentificadoEmail"><liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformado.email"/></label>
                        </div>
                        <fieldset class="form-group">
                            <div class="form-check">
                                <input type="checkbox" id="chkIdentificadoPostal" class="form-check-input clsChkEnvioComunicacion">
                                <label for="chkIdentificadoPostal"><liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.postal"/></label>
                            </div>
                            <input type="text" id="inpPostalIdentificado"
                            class="inpPaso_Identificado_1 clschkIdentificadoPostal" aria-label="Dirección" disabled="true"
                            placeholder='<liferay-ui:message key="canal.comunicado.form.paso1.error.direccionPlaceHolder"/>'>
                            <p class="text-danger hide" id="chkIdentificadoPostalRequired"><liferay-ui:message key="canal.comunicado.form.paso1.error.cp"/></p>

                        </fieldset>
                        <fieldset class="form-group">
                            <div class="form-check">
                                <input type="checkbox" id="chkIdentificadoOtro" class="form-check-input clsChkEnvioComunicacion">
                                <label for="chkIdentificadoOtro"><liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.otro"/></label>
                            </div>
                            <input type="text" id="inpOtroIdentificado" class="inpPaso_Identificado_1 clschkIdentificadoOtro" aria-label="Otro" disabled="true">
                            <p class="text-danger hide" id="chkOtroIdentificadoRequired"><liferay-ui:message key="canal.comunicado.form.paso1.error.otro"/></p>

                        </fieldset>
                    </div>
                    <!-- END INFORMADO IDENTIFICADO -->
                </div>

                <button type="button" class="btn btn-light" onclick="hideIdentificarse()" onkeypress="hideIdentificarse()" id="btnNoIdentificarse"><liferay-ui:message key="canal.comunicado.form.paso1.anonimo"/></button>
                <div id="spnNoIdentificarse" class="wizardOption d-none">
                    <p><liferay-ui:message key="canal.comunicado.form.paso1.anonimo.descr"/></p>

                    <div class="form-group form-check">
                        <input type="checkbox" id="chkInformadoAnonymous" class="form-check-input" onchange="setInformadoAnonymous(this)">
                        <label for="chkInformadoAnonymous">
                        <liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo"/>
                        <span class="showChecked">
                            &nbsp;<liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.aviso"/>
                        </span></label>

                        <p class="text-danger hide" id="contactoAnonymousRequired">
                            <liferay-ui:message key="canal.comunicado.form.paso1.error.informadoAnonimoSinDatos"/>
                        </p>
                    </div>

                    <div class="flexRow d-none" id="spnInformadoAnonymous">
                        <fieldset class="form-group">
                            <div class="form-check">
                                <input type="checkbox" id="chkAnonymousEmail" class="form-check-input clsChkEnvioComunicacion">
                                <label for="chkAnonymousEmail"><liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.email"/></label>
                            </div>
                            <input type="text" id="inpEmailAnonymous" class="inpPaso_anonymous_1 clschkAnonymousEmail" aria-label="email anonimo" disabled="true">
                            <p class="text-danger hide" id="emailAnonymousRequired"><liferay-ui:message key="canal.comunicado.form.paso1.error.email"/></p>
                        </fieldset>
                        <fieldset class="form-group">
                            <div class="form-check">
                                <input type="checkbox" id="chkAnonymousPostal" class="form-check-input clsChkEnvioComunicacion">
                                <label for="chkAnonymousPostal"><liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.postal"/></label>
                            </div>
                            <input type="text" id="inpPostalAnonymous"
                            class="inpPaso_anonymous_1 clschkAnonymousPostal" aria-label="Dirección anonimo" disabled="true"
                            placeholder='<liferay-ui:message key="canal.comunicado.form.paso1.error.direccionPlaceHolder"/>'>
                            <p class="text-danger hide" id="chkAnonymousPostalRequired"><liferay-ui:message key="canal.comunicado.form.paso1.error.cp"/></p>

                        </fieldset>
                        <fieldset class="form-group">
                            <div class="form-check">
                                <input type="checkbox" id="chkAnonymousOtro" class="form-check-input clsChkEnvioComunicacion">
                                <label for="chkAnonymousOtro"><liferay-ui:message key="canal.comunicado.form.paso1.anonimo.checkInformadodo.otro"/></label>
                            </div>
                            <input type="text" id="inpOtroAnonymous" class="inpPaso_anonymous_1 clschkAnonymousOtro" aria-label="otro anonimo" disabled="true">
                            <p class="text-danger hide" id="inpOtroAnonymousRequired"><liferay-ui:message key="canal.comunicado.form.paso1.error.otro"/></p>

                        </fieldset>
                    </div>

                </div>
            </div>
            <div class="wizard-buttons">
                <button type="button" class="btn btn-tertiary previousButton">ANTERIOR</button>
                <button type="button" class="btn btn-tertiary nextButton btnNext_paso_1 "><liferay-ui:message key="canal.comunicado.form.botones.siguiente"/></button>
            </div>
            <!-- END FORM DE IDENTIFDICARSE-->
        </div>
        <!-- END CAPA 1-->


        <!-- CAPA 2-->
        <div id="paso_2" class="d-none">

            <h3 class="pasoTitle"><liferay-ui:message key="canal.comunicado.form.paso2.titulo"/></h3>
            <c:choose>
              <c:when test="<%=Validator.isBlank(downloadURLProcedimiento)%>">
                <p><liferay-ui:message key="canal.comunicado.form.paso2.descr.noEnlace"/></p>
              </c:when>
              <c:otherwise>
                <p><liferay-ui:message key="canal.comunicado.form.paso2.descr"/></p>
              </c:otherwise>
            </c:choose>
            <div class="wizardOptions">
                <div class="wizardOption">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="cmbEntidad"><liferay-ui:message key="canal.comunicado.form.paso2.entidad"/>*</label>
                        <c:if test="<%=sizeLista<=0%>">
                            <input type="text" class="field form-control" value= "<%=nombreEmpresa%>" disabled id="cmbEntidad"/>
                        </c:if>
                        <c:if test="<%=sizeLista>=1%>">
                            <select class="field form-control" id="cmbEntidad" name="cmbEntidad">
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
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="cmbRelacion"><liferay-ui:message key="canal.comunicado.form.paso2.labelVinculacion"/>*</label>
                        <select id="cmbRelacion" class="field form-control">
                            <option value="" disabled selected hidden><liferay-ui:message key='canal.comunicado.form.paso2.relacionNoIndicada'/></option>
                        </select>
                        <p class="text-danger hide" id="relacionRequired"><liferay-ui:message key="canal.comunicado.form.paso2.error.relacion"/></p>
                    </div>
                </div>
            </div>
            <div class="wizard-buttons">
                <button type="button" class="btn btn-tertiary previousButton"><liferay-ui:message key="canal.comunicado.form.botones.anterior"/></button>
                <button type="button" class="btn btn-tertiary nextButton btnNext_paso_2 "><liferay-ui:message key="canal.comunicado.form.botones.siguiente"/></button>
            </div>

        </div>
        <!-- END CAPA 2-->

        <!-- CAPA 3-->
        <div id="paso_3" class="d-none">

            <h3 class="pasoTitle"><liferay-ui:message key="canal.comunicado.form.paso3.titulo"/></h3>
            <c:choose>
              <c:when test="<%=Validator.isBlank(downloadURLProcedimiento)%>">
                <p><liferay-ui:message key="canal.comunicado.form.paso3.descr.noEnlace"/></p>
              </c:when>
              <c:otherwise>
                <p><liferay-ui:message key="canal.comunicado.form.paso3.descr"/></p>
              </c:otherwise>
            </c:choose>
            <div class="wizardOptions">
                <div class="wizardOption">
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="inpAsunto"><liferay-ui:message key="canal.comunicado.form.paso3.asunto"/>*</label>
                        <input type="text" class="field form-control inpPaso_3" value="" id="inpAsunto">
                        <p class="text-danger hide" id="asuntoRequired"><liferay-ui:message key="canal.comunicado.form.paso3.error.asunto"/></p>
                    </div>
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="inpDescripcion"><liferay-ui:message key="canal.comunicado.form.paso3.descrField"/>*</label>
                        <textarea id="inpDescripcion"
                         style='font-family: "Inter", "Ubuntu", sans-serif;font-size: 0.875rem;'
                        class="field form-control inpPaso_3"></textarea>
                        <p class="text-danger hide" id="descripcionRequired"><liferay-ui:message key="canal.comunicado.form.paso3.error.descripcion"/></p>
                    </div>
                    <div class="form-group input-text-wrapper">
                        <p class="control-label form-label"><liferay-ui:message key="canal.comunicado.form.paso3.adjuntar"/></p>
                        <button type="button" class="btn btn-outline-light" onclick="addFile('new')" onkeypress="addFile('new')"><liferay-ui:message key="canal.comunicado.form.paso3.adjuntarSeleccione"/></button>
                        <div class="d-none">
                            <input type="file" id="<portlet:namespace/>inpFile_00" name="<portlet:namespace/>inpFile_00" class="clsFile"><button type="button" onclick="deleteFile(1,'new')">Eliminar</button><br/>
                            <input type="file" id="<portlet:namespace/>inpFile_01" name="<portlet:namespace/>inpFile_01" class="clsFile"><button type="button" onclick="deleteFile(2,'new')">Eliminar</button><br/>
                            <input type="file" id="<portlet:namespace/>inpFile_02" name="<portlet:namespace/>inpFile_02" class="clsFile"><button type="button" onclick="deleteFile(3,'new')">Eliminar</button><br/>
                            <input type="file" id="<portlet:namespace/>inpFile_03" name="<portlet:namespace/>inpFile_03" class="clsFile"><button type="button" onclick="deleteFile(4,'new')">Eliminar</button><br/>
                            <input type="file" id="<portlet:namespace/>inpFile_04" name="<portlet:namespace/>inpFile_04" class="clsFile"><button type="button" onclick="deleteFile(5,'new')">Eliminar</button>
                        </div>
                    </div>
                    <div id="spnSelectedFiles"></div>
                </div>
            </div>
            <div class="wizard-buttons">
                <button type="button" class="btn btn-tertiary previousButton"><liferay-ui:message key="canal.comunicado.form.botones.anterior"/></button>
                <button type="button" class="btn btn-tertiary nextButton btnNext_paso_3 "><liferay-ui:message key="canal.comunicado.form.botones.siguiente"/></button>
            </div>

        </div>
        <!-- END CAPA 3-->

        <!-- CAPA 4-->
        <div id="paso_4" class="d-none">

            <h3 class="pasoTitle"><liferay-ui:message key="canal.comunicado.form.paso4.titulo"/></h3>
            <p><liferay-ui:message key="canal.comunicado.form.paso4.descr1"/></p>
            <p><liferay-ui:message key="canal.comunicado.form.paso4.descr2"/></p>
            <div class="wizardOptions">
                <div class="wizardOption">
                    <h4><liferay-ui:message key="canal.comunicado.form.paso4.tituloEntidad"/></h4>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="resCIF"><liferay-ui:message key="canal.comunicado.form.paso4.cif"/></label>
                                <input class="field form-control" type="text"  value= "<%=cif%>" id="resCIF" disabled>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="resEntidad"><liferay-ui:message key="canal.comunicado.form.paso4.nombreEntidad"/></label>
                                <input class="field form-control" type="text" value="" id="resEntidad" disabled>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="resVinculacion"><liferay-ui:message key="canal.comunicado.form.paso4.relacionEntidad"/></label>
                                <input class="field form-control" type="text" value="" id="resVinculacion" disabled>
                            </div>
                        </div>
                    </div>

                    <h4><liferay-ui:message key="canal.comunicado.form.paso4.tituloPersona"/></h4>
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="resEmail"><liferay-ui:message key="canal.comunicado.form.paso4.email"/></label>
                                <input class="field form-control" type="text" value="" id="resEmail" disabled>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="resNombre"><liferay-ui:message key="canal.comunicado.form.paso4.nombre"/></label>
                                <input class="field form-control" type="text" value="" id="resNombre" disabled>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="resApellidos"><liferay-ui:message key="canal.comunicado.form.paso4.apellidos"/></label>
                                <input class="field form-control" type="text" value="" id="resApellidos" disabled>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="resNIF"><liferay-ui:message key="canal.comunicado.form.paso4.nif"/></label>
                                <input class="field form-control" type="text" value="" id="resNIF" disabled>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="resTelfn"><liferay-ui:message key="canal.comunicado.form.paso4.tlfn"/></label>
                                <input class="field form-control" type="text" value="" id="resTelfn" disabled>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="resPostal"><liferay-ui:message key="canal.comunicado.form.paso4.postal"/></label>
                                <input class="field form-control" type="text" value="" id="resPostal" disabled>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group input-text-wrapper">
                                <label class="control-label" for="resOtros"><liferay-ui:message key="canal.comunicado.form.paso4.otros"/></label>
                                <input class="field form-control" type="text" value="" id="resOtros" disabled>
                            </div>
                        </div>
                    </div>

                    <h4><liferay-ui:message key="canal.comunicado.form.paso4.tituloContenido"/></h4>
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="resAsunto"><liferay-ui:message key="canal.comunicado.form.paso4.asunto"/></label>
                        <input type="text" class="field form-control" value="" id="resAsunto" disabled>
                    </div>
                    <div class="form-group input-text-wrapper">
                        <label class="control-label" for="resDescripcion"><liferay-ui:message key="canal.comunicado.form.paso4.descr"/></label>
                        <div id="resDescripcion" class="disabled isInput hasScroll" style="height: 200px; pointer-events: visible;"></div>

                    </div>
                    <div id="spnResAdjuntos">
                        <div class="prv-descarga">
                            <p class="prv-name">ADJUNTO  1</p>
                        </div>
                    </div>
                </div>
                <div class="wizardOption">
                    <div class="form-group form-check">
                        <input type="checkbox" id="chkFinPoliticas" class="form-check-input chkFinPoliticas">
                        <c:choose>
                          <c:when test="<%=Validator.isBlank(downloadURLProcedimiento)%>">
                            <label for="chkFinPoliticas"><liferay-ui:message key="canal.comunicado.form.paso4.checkInformado.noEnlace"/></label>
                          </c:when>
                          <c:otherwise>
                            <label for="chkFinPoliticas"><liferay-ui:message key="canal.comunicado.form.paso4.checkInformado"/></label>
                          </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="form-group form-check">
                        <input id="chkLeidoRPGD" type="checkbox" class="form-check-input chkFinPoliticas">
                        <label for="chkLeidoRPGD"><liferay-ui:message key="canal.comunicado.form.paso4.checkLeido"/></label>
                        <div id="spnInfoProteccion" class="hide">
                            <p id="spnTxtRPGD"><liferay-ui:message key="canal.comunicado.form.paso4.checkLeido.info"/></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="g-recaptcha" data-sitekey="6LeeGW8pAAAAALVTDyCKUUYiCtBLCxXdOTYO8xC4" data-callback="sendCaptcha"></div>

            <div class="wizard-buttons">
                <button type="button" class="btn btn-tertiary previousButton"><liferay-ui:message key="canal.comunicado.form.botones.anterior"/></button>
                <!-- <button type="button" class="btn btn-tertiary endButton">
                    <liferay-ui:message key="canal.comunicado.form.botones.finalizar"/>
                </button> -->
                <button type="submit" class="btn btn-tertiary endButton disabled" disabled><liferay-ui:message key="canal.comunicado.form.botones.finalizar"/></button>
            </div>

        </div>
        <!-- END CAPA 4-->

    </div>

    <div class="d-none">
        <!-- <textarea id="dataJson" name="dataJson"></textarea> -->
        <aui:input name="dataJson" />
        <aui:input name="g-recaptcha-response" />
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

    <div class="contentModal alert-modal error" id="modalContainerEnd" style="border-radius: 15px; width: 650px;">
        <div class="headerModal">
            <p id="errorEntidad"><span><liferay-ui:message key="label.error"/></p>
        </div>
        <div class="container">
            <div class="col-12 text-center">
                <p class="text-center"><liferay-ui:message key="label.texto.error"/></p>
            </div>
        </div>
         <div class="footerModal">


            <button type="button" class="buttonModal closeMyModal " id="btnCancelCom"  data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="label.cerrar"/>
            </button>
         </div>
    </div>

    <div class="contentModal alert-modal error" id="modalContainerCaducada" style="border-radius: 15px; width: 650px;">
        <div class="headerModal">
            <p id="errorEntidad"><span><liferay-ui:message key="label.aviso.modal"/></p>
        </div>
        <div class="container">
            <div class="col-12 text-center">
                <p class="text-center"><liferay-ui:message key="texto.modal.caducada"/></p>
            </div>
        </div>
         <div class="footerModal">


            <button type="button" class="buttonModal closeMyModal " id="btnCancelComCad"  data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="label.cerrar"/>
            </button>
         </div>
    </div>

    <div class="contentModal alert-modal error" id="modalContainerFinalizada" style="border-radius: 15px; width: 650px;">
        <div class="headerModal">
            <p id="errorEntidad"><span><liferay-ui:message key="label.aviso.modal"/></p>
        </div>
        <div class="container">
            <div class="col-12 text-center">
                <p class="text-center"><liferay-ui:message key="texto.modal.finalizada"/></p>
            </div>
        </div>
         <div class="footerModal">


            <button type="button" class="buttonModal closeMyModal " id="btnCancelComFin"  data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="label.cerrar"/>
            </button>
         </div>
    </div>


    <div class="divModal" id="modalPortlet">
    </div>
</c:if>
    <script src="https://www.google.com/recaptcha/enterprise.js" async defer></script>
    <script>
        var contPaso = 0;
        var captchaChek=false;
        var valueCaptcha=''
        $('.closeMyModal').click(function(){
            $('#modalContainerCaducada').css('display','none');
            $('#modalContainerFinalizada').css('display','none');
            $('#modalContainerEnd').css('display','none');
        })
        function sendCaptcha(token) {
            captchaChek=true;
            valueCaptcha=grecaptcha.enterprise.getResponse()
            if($(".chkFinPoliticas:checked").length == 2){
                    $(".endButton").removeClass("disabled");
                    $(".endButton").prop("disabled", false);
                    $("#<portlet:namespace/>g-recaptcha-response").val(valueCaptcha)
            }else{
                $(".endButton").addClass("disabled");
                $(".endButton").prop("disabled", true);
            }

        }
        function mostrarModal(){
            $('#modalContainerCaducada').css('display','none');
            $('#modalContainerFinalizada').css('display','none');
            $('#modalPortlet').css('display','block');
            $('#modalContainerEnd').css('display','block');

        }

        function mostrarModalCaducada(){
            $('#modalContainerFinalizada').css('display','none');
            $('#modalContainerEnd').css('display','none');
            $('#modalPortlet').css('display','block');
            $('#modalContainerCaducada').css('display','block');

        }

        function mostrarModalFinalizada(){
            $('#modalContainerEnd').css('display','none');
            $('#modalContainerCaducada').css('display','none');
            $('#modalPortlet').css('display','block');
            $('#modalContainerFinalizada').css('display','block');

        }


        $(document).ready(function () {

            var current_fs, next_fs, previous_fs; //fieldsets
            var opacity;


             $(".chkFinPoliticas").on("change", function(){

                            if($("#chkLeidoRPGD").prop("checked") == true){
                                $("#spnInfoProteccion").removeClass("hide");
                            }else{
                                $("#spnInfoProteccion").addClass("hide");
                            }
                            if($(".chkFinPoliticas:checked").length == 2 && captchaChek){
                                $(".endButton").removeClass("disabled");
                                $(".endButton").prop("disabled", false);
                                $("#<portlet:namespace/>g-recaptcha-response").val(valueCaptcha)
                            }else{
                                $(".endButton").addClass("disabled");
                                $(".endButton").prop("disabled", true);
                            }
                        });

             $("#languageForm").on("change", function(){
                var urlActual = window.location.href
                var idiomaSeleccted = $("#languageForm").val();
                if(urlActual.indexOf("/es/")>0 || urlActual.indexOf("/en/")>0 || urlActual.indexOf("/ca/")>0){
                    urlActual=urlActual.replace("/es/","/"+idiomaSeleccted+"/");
                    urlActual=urlActual.replace("/en/","/"+idiomaSeleccted+"/");
                    urlActual=urlActual.replace("/ca/","/"+idiomaSeleccted+"/");
                }else{
                    urlActual = urlActual.replace("/web/","/"+idiomaSeleccted+"/web/")
                }
                window.location = urlActual;
             });

            //asunto  y descr de añadir
            $("#asuntoAdicionalVisible, #descripcionAdicionalVisible").on("keyup", function(){

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
                        && $("#asuntoAdicionalVisible").val() != ""
                            && $("#descripcionAdicionalVisible").val() != ""){

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


            var options = $('#cmbEntidad option');
            var arr = options.map(function(_, o) {
                    return {
                        t: $(o).text(),
                        v: o.value,
                        id: o.id
                    };
                }).get();
            arr.sort(function(o1, o2) {
                var t1 = o1.t.toLowerCase(), t2 = o2.t.toLowerCase();
                return t1 > t2 ? 1 : t1 < t2 ? -1 : 0;
            });
            options.each(function(i, o) {
                    o.value = arr[i].v;
                    o.id = arr[i].id;
                    $(o).text(arr[i].t);
            });
            $("#cmbEntidad").prepend('<option value="0" disabled selected hidden><liferay-ui:message key="canal.comunicado.form.paso2.relacionNoIndicada"/></option>')

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

                if(!window["validate_btnNext_paso_" + contPaso]()) return; // validar cada paso

                contPaso++;

                if($(this).hasClass("disabled")){
                    return;
                }


                current_fs = $(this).closest('[id*="paso_"]');
                next_fs = current_fs.next();
                $("#contPaso_" + contPaso).addClass("dtik-proceso__paso--seleccionado");

                next_fs.removeClass("d-none");
                current_fs.addClass("d-none");

                getDataForm();
            });

            $(".previousButton").click(function () {
                console.log("(P) contPaso: " + contPaso)
                if(contPaso <= 0){
                    $("#pasos_nums").addClass("d-none");
                    $("#pasos_anadirInfo").addClass("d-none");
                    $("#paso_1").addClass("d-none");
                    $("#paso_0").removeClass("d-none");
                    contPaso = 0;
                    return;
                }

                $("#contPaso_" + contPaso).removeClass("dtik-proceso__paso--seleccionado");
                contPaso--;
                $("#contPaso_" + contPaso).addClass("dtik-proceso__paso--seleccionado");


                current_fs =  $(this).closest('[id*="paso_"]');
                previous_fs = current_fs.prev();

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
            $("#btnNuevaCom").removeClass("btn-seleccionado");
            $("#btnAnadirCom").addClass("btn-seleccionado");
            $(".setModeButton").addClass("disabled");

        }
        function hideInpComunicadoCode(){
            $("#dvComunicadoCode").addClass("d-none");
            isAnadirInfo = false;
            $("#btnNuevaCom").addClass("btn-seleccionado");
            $("#btnAnadirCom").removeClass("btn-seleccionado");
            $(".setModeButton").removeClass("disabled");
        }

        function showIdentificarse(){
            anonymous = false;
            $("#frmIdentificarse").removeClass("d-none");
            $("#spnNoIdentificarse").addClass("d-none");
            $("#btnNoIdentificarse").removeClass("btn-seleccionado")
            $("#btnIdentificarse").addClass("btn-seleccionado")

        }
        function hideIdentificarse(){
            anonymous = true;
            $("#frmIdentificarse").addClass("d-none");
            $("#spnNoIdentificarse").removeClass("d-none");
            $("#btnIdentificarse").removeClass("btn-seleccionado")
            $("#btnNoIdentificarse").addClass("btn-seleccionado")


        }
        function setInformadoAnonymous(_node){
            //validatePaso_anonymous_1();
            if(_node.checked){
                $("#spnInformadoAnonymous").removeClass("d-none");
            }else{
                $("#spnInformadoAnonymous").addClass("d-none");
                $("#contactoAnonymousRequired").addClass("hide");
            }
        }
        function setInformadoIdentificado(_node){
            $(".showCheckedIdentificado").addClass("hide");
            if(_node.checked){
                $("#spnInformadoIdentificado").removeClass("d-none");
                $(".showCheckedIdentificado").removeClass("hide");
            }else{
                $("#spnInformadoIdentificado").addClass("d-none");
                $("#contactoRequired").addClass("hide");
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

                if(this.value != "") arrFilesSelected.push('<div class="prv-descarga"><p class="prv-name">'
                                                            + this.value.substring(this.value.lastIndexOf("\\")+1)
                                                            + '</p><button type="button" class="btn-delete" onclick="deleteFile(' + indice + ',' + "'" + _type + "'" +  ')" onkeypress="deleteFile(' + indice + ',' + "'" + _type + "'" +  ')"><span class="sReader">Eliminar</span></button>'
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

        $(".clsChkEnvioComunicacion").on("change",function(){
            if($(this).prop("checked") == undefined || $(this).prop("checked") == null) return;

            $(".cls" +  $(this).prop("id")).prop("disabled", !$(this).prop("checked"));
            if($(this).prop("checked")  == false) $(".cls" +  $(this).prop("id")).val("");

        });




        function isEmailCorrect(_mail) {
            if(_mail.trim() == "") _mail = "noesvalido";
            var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            return regex.test(_mail);
        }


        function validatePaso_1(){
            var isOk = true;
            if(!anonymous){
                isOk =  _validatePaso_1_noAnonymous();
            }else{
                isOk = validatePaso_anonymous_1();
            }
            return isOk;
        }

        function _validatePaso_1_noAnonymous(){
            var isOk = true;
            $("#contactoRequired").addClass("hide");

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

                if($("#chkIdentificadoPostal").prop("checked") == true && $("#inpPostalIdentificado").val().trim() == ""){
                    $("#chkIdentificadoPostalRequired").removeClass("hide");
                    isOk = false;
                }
                if($("#chkIdentificadoPostal").prop("checked") == false || ($("#chkIdentificadoPostal").prop("checked") == true && $("#inpPostalIdentificado").val().trim() != "")){
                    $("#chkIdentificadoPostalRequired").addClass("hide");
                }
                if($("#chkIdentificadoOtro").prop("checked") == true && $("#inpOtroIdentificado").val().trim() == ""){
                    $("#chkOtroIdentificadoRequired").removeClass("hide");
                    isOk = false;
                }
                if($("#chkIdentificadoOtro").prop("checked") == false || ($("#chkIdentificadoOtro").prop("checked") == true && $("#inpOtroIdentificado").val().trim() != "")){
                    $("#chkOtroIdentificadoRequired").addClass("hide");
                }
                if($("#chkIdentificadoEmail").prop("checked") == false && $("#chkIdentificadoPostal").prop("checked") == false && $("#chkIdentificadoOtro").prop("checked") == false) isOk = false;

            }

            if($("#chkIdentificadoPostal").prop("checked") == false) $("#inpPostalIdentificado").val('');
            if($("#chkIdentificadoOtro").prop("checked") == false) $("#inpOtroIdentificado").val('');


            /*if(!isOk && $("#chkInformadoIdentificado").prop("checked") == true ){
                $("#contactoRequired").removeClass("hide");
            }*/

            if($("#chkInformadoIdentificado").prop("checked") == true
                && $("#chkIdentificadoEmail").prop("checked") == false
                    && $("#chkIdentificadoPostal").prop("checked") == false
                     && $("#chkIdentificadoOtro").prop("checked") == false ){

                $("#contactoRequired").removeClass("hide");
                isOk = false;
            }

            return isOk;
        }

        function validatePaso_anonymous_1(){
            console.log("PASA AQUI")
            var isOk = true;
            $("#contactoAnonymousRequired").addClass("hide");

            if($("#chkInformadoAnonymous").prop("checked") == true
                    && $("#chkAnonymousEmail").prop("checked") == false
                        && $("#chkAnonymousPostal").prop("checked") == false
                            && $("#chkAnonymousOtro").prop("checked") == false){

                $("#contactoAnonymousRequired").removeClass("hide");
                isOk = false;
                return isOk;
            }



            if($("#chkInformadoAnonymous").prop("checked") == true){
                if($("#chkAnonymousEmail").prop("checked") == true){
                    $("#emailAnonymousRequired").addClass("hide");
                    if(!isEmailCorrect($("#inpEmailAnonymous").val().trim())){
                        $("#emailAnonymousRequired").removeClass("hide");
                        isOk = false;
                    }
                }
                $("#chkAnonymousPostalRequired").addClass("hide");
                if($("#chkAnonymousPostal").prop("checked") == true && $("#inpPostalAnonymous").val().trim() == ""){
                    $("#chkAnonymousPostalRequired").removeClass("hide");
                    isOk = false;
                }
                $("#inpOtroAnonymousRequired").addClass("hide");
                if($("#chkAnonymousOtro").prop("checked") == true && $("#inpOtroAnonymous").val().trim() == "") {
                    $("#inpOtroAnonymousRequired").removeClass("hide");
                    isOk = false;
                }

                if($("#chkAnonymousEmail").prop("checked") == false && $("#chkAnonymousPostal").prop("checked") == false && $("#chkAnonymousOtro").prop("checked") == false) isOk = false;



                if($("#chkAnonymousPostal").prop("checked") == false) $("#inpPostalAnonymous").val('');
                if($("#chkAnonymousOtro").prop("checked") == false) $("#inpOtroAnonymous").val('');
                if($("#chkAnonymousEmail").prop("checked") == false) $("#inpEmailAnonymous").val('');


            }
            return isOk;
        }

        function validatePaso_2(){
            if($("#cmbEntidad").children('option').length > 0){ //es un select

                if($("#cmbEntidad").val() == null){
                    $("#entidadRequired").removeClass("hide");
                    return false;
                }else{
                    $("#entidadRequired").addClass("hide");
                }
            }

            if($("#cmbRelacion").val()!= null){
                $("#relacionRequired").addClass("hide");
                return true;
            }else{
                $("#relacionRequired").removeClass("hide");
            }
            return false;
        }
        function validatePaso_3(){
            var isOk = true;
            $("#asuntoRequired").addClass("hide");
            $("#descripcionRequired").addClass("hide");
            if($("#inpAsunto").val().trim() == ""){
                $("#asuntoRequired").removeClass("hide");
                isOk = false;
            }else if($("#inpDescripcion").val().trim() == ""){
                $("#descripcionRequired").removeClass("hide");
                 isOk = false;
            }
            return isOk;
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
            dataJson.idioma = themeDisplay.getLanguageId();
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
            if($("#chkIdentificadoEmail").prop("checked") == true || $("#chkAnonymousEmail").prop("checked") == true){
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
            //$("#resDescripcion").val($("#inpDescripcion").val());
            $("#resDescripcion").html($("#inpDescripcion").val().replaceAll('\n','</br>'));

            //Listado de adjuntos
            var arrFilesSelected = [];
            /*$(".clsFile").each(function(){
                if(this.value != "") arrFilesSelected.push('<div class="btn btn-light">' + this.value.substring(this.value.lastIndexOf("\\")+1) + "</div>");
            });*/
            $(".clsFile").each(function(){
                if(this.value != "") arrFilesSelected.push('<div class="prv-descarga"><p class="prv-name">' + this.value.substring(this.value.lastIndexOf("\\")+1) + "</p></div>");
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


        var arrEstados = "<liferay-ui:message key="canal.comunicado.estados"/>";
        arrEstados = arrEstados.split(",");
        function isCorrectCode(_value){
            $.ajax('<%=getComunicadoByCodigoURL.toString()%>&idComp='+<%=idComp%> +'&comunicadoCode=' + _value,
            {
                dataType: 'json',
                success: function (response,status,xhr) {
                    console.log("data.estado : " + response.data[0].estado);
                    console.log("data.estado NUMBER: " + Number(response.data[0].estado));
                    if(response.data[0].estado==4){
                        mostrarModalFinalizada();
                    }else if(response.data[0].estado==5){
                        mostrarModalCaducada();
                    }else{
                        comunicadoJson = response;
                        $("#inpEstadoExiste").val(arrEstados[Number(response.data[0].estado)]);
                        $("#spIdentificador").html(_value);
                        $(".setModeButton").removeClass("disabled");
                    }
                },
                error: function (jqXhr, textStatus, errorMessage) {
                    console.log("ERROR")
                    $(".setModeButton").addClass("disabled");
                    comunicadoJson = null;
                    mostrarModal();
                }
            });
        }

        function validate_btnNext_paso_0(){
            console.log("validar paso 0");
            return validatePaso_1();
        }
        function validate_btnNext_paso_1(){
            console.log("validar paso 1");
            return validatePaso_2();
        }
        function validate_btnNext_paso_2(){
            console.log("validar paso 2");
            return validatePaso_3();
        }


    </script>




