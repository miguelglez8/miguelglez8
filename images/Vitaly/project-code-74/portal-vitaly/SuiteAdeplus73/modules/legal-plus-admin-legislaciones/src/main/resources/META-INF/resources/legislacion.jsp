<%@ taglib prefix="liferay-asset" uri="http://liferay.com/tld/ui" %>
<%@ page import="com.legalplus.liferay.portlet.admin.legislaciones.web.constants.AdminLegislacionesPortletKeys" %>
<%@ page import="com.liferay.asset.kernel.model.AssetCategory" %>
<%@ page import="com.liferay.asset.kernel.model.AssetVocabulary" %>
<%@ page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil" %>
<%@ page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil" %>
<%@ page import="com.legalplus.liferay.portlet.admin.legislaciones.web.enums.AmbitoAplicacion" %>
<%@ page import="com.legalplus.liferay.portlet.admin.legislaciones.web.enums.FamiliaNormativa" %>
<%@ page import="com.legalplus.liferay.portlet.admin.legislaciones.web.enums.TipoNormativa" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Comp" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.*" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.*" %>
<%@ include file="/init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">

<script>
    Liferay.Loader.define._amd = Liferay.Loader.define.amd;
    Liferay.Loader.define.amd = false;
</script>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.flash.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.html5.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.print.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/i18n/es.js"></script>

<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

<script>
    Liferay.Loader.define.amd = Liferay.Loader.define._amd;
</script>

<%
    String legislacionEditId = ParamUtil.getString(request, AdminLegislacionesPortletKeys.LEGISLACION_ID_EDIT, "");

    Calendar fechaPublicacionCalendar = Calendar.getInstance();
    fechaPublicacionCalendar.setTime(new Date());
    Calendar fechaDerogacionCalendar = Calendar.getInstance();
    fechaDerogacionCalendar.setTime(new Date());

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
    SimpleDateFormat dateFormatSearch = new SimpleDateFormat("yyyyMMdd");
    dateFormatSearch.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

    Date now = new Date();

    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

    List<CCAA> ccaaList = CCAALocalServiceUtil.getCCAAs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

    List<CNAES> cnaes = CNAESLocalServiceUtil.getCNAESs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    List<LegislacionCNAES> legislacionCNAESList = LegislacionCNAESLocalServiceUtil.findByLegislacion(legislacionEditId);
    
    String cnaesValueHide = ""; // cnaes de la legislación
    for(LegislacionCNAES cnae : legislacionCNAESList){
    	if(!cnaesValueHide.isEmpty()) cnaesValueHide += ",";
    	cnaesValueHide += cnae.getCnae();		
    }
    
    
    List<Requisito> byLegislacion = RequisitoLocalServiceUtil.findByLegislacion(legislacionEditId);

    Legislacion legislacion = null;
    if(legislacionEditId != ""){
        legislacion = LegislacionLocalServiceUtil.fetchLegislacion(legislacionEditId);
        if(Validator.isNotNull(legislacion)){
            if(Validator.isNotNull(legislacion.getPublicacion())) {
                fechaPublicacionCalendar.setTime(legislacion.getPublicacion());
            }
            if(Validator.isNotNull(legislacion.getDerogacion())) {
                fechaDerogacionCalendar.setTime(legislacion.getDerogacion());
            }
        }
    }

    AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(themeDisplay.getScopeGroupId(), AdminLegislacionesPortletKeys.VOCABULARIO_CONSULTOR);
    List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

    List<CompClientApplication> comps = CompClientApplicationLocalServiceUtil.getCompClientApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
%>

<liferay-ui:success key="legislacion-save-success" message="admin.legislaciones.edit.save.success" />
<liferay-ui:success key="requisito-save-success" message="admin.legislaciones.edit.requisito.save.success" />
<liferay-ui:error key="error-create-legislacion" message="admin.legislaciones.edit.error" />

<liferay-portlet:actionURL name="/legislacion/save" var="saveURL" />
<aui:form  name="edit_form" action="<%=saveURL.toString()%>" method="post" >

<portlet:renderURL var="cancelURL">
 	<portlet:param name="mvcRenderCommandName" value="/"></portlet:param>
</portlet:renderURL>

    <aui:input name="<%=AdminLegislacionesPortletKeys.LEGISLACION_ID_EDIT%>" value="<%=legislacionEditId%>" type="hidden" />
    <div class="title-group mt-3">
    	<a href="<%= cancelURL.toString() %>" class="toBackView">
    		<i class="icon-arrow-left"></i>
    		<liferay-ui:message key="admin.legislaciones.view.labelVolverLegislaciones"/>
    	</a>
    	<h2>
    		<c:if test="<%=Validator.isNotNull(legislacion)%>">
    			<liferay-ui:message key="admin.legislaciones.view.labelEditarLegislacion"/>
    		</c:if>
    		<c:if test="<%=Validator.isNull(legislacion)%>">
    			<liferay-ui:message key="admin.legislaciones.view.btnNuevaLegislacion"/>
    		</c:if>
    	</h2>
    	
    </div>
    <div class="form-content form-datos prv-form">
    	<div class="row align-items-start mb-2">
        	<div class="form-group col-12 required">
            	<div class="form-group input-text-wrapper has-error">
                	<aui:input name="<%=AdminLegislacionesPortletKeys.NOMBRE%>" type="text" label="admin.legislaciones.view.legislacion" value="<%=Validator.isNotNull(legislacion)?legislacion.getNombre():""%>" >
                    	<aui:validator name="required" errorMessage="admin.legislaciones.edit.validator.required" />
					</aui:input>
            	</div>
        	</div>
    	</div>
    	<div class="row align-items-start mb-2">
    		<div class="form-group col-md-6 col-12 required">
            	<div class="form-group input-text-wrapper">
                	<aui:input name="<%=AdminLegislacionesPortletKeys.ENLACE%>" type="text" label="admin.legislaciones.view.enlace" value="<%=Validator.isNotNull(legislacion)?legislacion.getEnlace():""%>">
                        <aui:validator name="required" errorMessage="admin.legislaciones.edit.validator.required" />
                    	<aui:validator name="url" errorMessage="admin.legislaciones.edit.validator.url" />
                    </aui:input>
            	</div>
        	</div>
    	
    		<div class="form-group col-md-3 col-12">
            	<fieldset class="required">
                	<div class="form-group input-select-wrapper">
                    	<aui:select name="<%=AdminLegislacionesPortletKeys.TIPO%>" label="admin.legislaciones.view.tipo" >
                                    <aui:option value=""><liferay-ui:message key="admin.legislaciones.view.selectTipoNormativa"/></aui:option>
                                    <c:forEach items="<%= TipoNormativa.values() %>" var="tipo">
                                        <%
                                            TipoNormativa curTipo = (TipoNormativa) pageContext.getAttribute("tipo");
                                        %>
                                        <option value="${tipo.codigo}" <%=Validator.isNotNull(legislacion) && legislacion.getTipo() == Long.valueOf(curTipo.getCodigo()) ?  "selected" : ""%> >
                                            <liferay-ui:message key="${tipo.descripcion}"/>
                                        </option>
                                    </c:forEach>
                                    <aui:validator name="required"/>
						</aui:select>
                	</div>
            	</fieldset>
        	</div>
        	<div class="form-group col-md-3 col-12">                       
            	<fieldset class="required">
                	<div class="form-group input-select-wrapper">
                    	<aui:select name="<%=AdminLegislacionesPortletKeys.AMBITO%>" label="admin.legislaciones.view.ambito" onchange="changeAmbito(this.value)">
                                	<aui:option value=""><liferay-ui:message key="admin.legislaciones.view.selectAmbito"/></aui:option>
                                	<c:forEach items="<%= AmbitoAplicacion.values() %>" var="ambito">
                                        <%
                                            AmbitoAplicacion curAmbito = (AmbitoAplicacion) pageContext.getAttribute("ambito");
                                        %>
                                        <option value="${ambito.codigo}" <%=Validator.isNotNull(legislacion) && legislacion.getAmbito() == Long.valueOf(curAmbito.getCodigo()) ?  "selected" : ""%> >
                                            <liferay-ui:message key="${ambito.descripcion}"/>
                                        </option>
                                    </c:forEach>
                                    <aui:validator name="required"/>
						</aui:select>
                	</div>
            	</fieldset>
        	</div> 
    	</div>
    	<div class="row align-items-start mb-2">
        <div class="form-group col-md-6 col-12 required">
            <fieldset class="">            
            	<legend><liferay-ui:message key="admin.legislaciones.view.familia"/></legend>
            	<div class="checksBorder">
            		<aui:input name="<%=AdminLegislacionesPortletKeys.FAMILIA_TODAS%>" type="checkbox" label="admin.legislaciones.view.familia.todas" checked="<%=Validator.isNotNull(legislacion)?"99;".equals(legislacion.getFamilia()):false%>" cssClass="check-app" ></aui:input>
					<aui:input name="<%=AdminLegislacionesPortletKeys.FAMILIA_PRL%>" type="checkbox" label="legislaciones.familia.prl" checked="<%=Validator.isNotNull(legislacion)?legislacion.getFamilia().contains("0"):false%>" cssClass="check-app"></aui:input>
                	<aui:input name="<%=AdminLegislacionesPortletKeys.FAMILIA_MEDIO_AMBIENTE%>" type="checkbox" label="legislaciones.familia.medioambiente" checked="<%=Validator.isNotNull(legislacion)?legislacion.getFamilia().contains("1"):false%>" cssClass="check-app" ></aui:input>
                	<aui:input name="<%=AdminLegislacionesPortletKeys.FAMILIA_SEGURIDAD_INDUSTRIAL%>" type="checkbox" label="legislaciones.familia.seguridad.industrial" checked="<%=Validator.isNotNull(legislacion)?legislacion.getFamilia().contains("2"):false%>" cssClass="check-app" ></aui:input>
                	<aui:input name="<%=AdminLegislacionesPortletKeys.FAMILIA_SEGURIDAD_ALIMENTARIA%>" type="checkbox" label="legislaciones.familia.seguridad.alimentaria" checked="<%=Validator.isNotNull(legislacion)?legislacion.getFamilia().contains("3"):false%>" cssClass="check-app" ></aui:input>
                </div>                
            </fieldset>

            <span id="spnRequiredFamilia" class="clsRequiredSelect">
                <span class="has-error">
                    <div class="form-validator-stack help-block">
                        <div role="alert" class=" required">
                            <liferay-ui:message key="admin.legislaciones.edit.validator.required" />
                        </div>
                    </div>
                </span>
            </span>
        </div>
    </div>
    <div class="row align-items-start mb-2">
        <div class="col-lg-3 col-md-6 col-12">
            <fieldset class="">
                <div class="form-group input-select-wrapper">                            		
                    <label class="control-label" for="<portlet:namespace/><%=AdminLegislacionesPortletKeys.CCAA%>">
                    	<liferay-ui:message key="admin.legislaciones.view.ccaa"/>
                    	<span class="contract-info text-secondary" data-toggle="tooltip" title="<liferay-ui:message key="admin.legislaciones.view.info.ccaa"/>">
                            <i class="icon-info-sign"></i>
                        </span>
					</label>
                    <select  id="<portlet:namespace/><%=AdminLegislacionesPortletKeys.CCAA%>" name="<portlet:namespace/><%=AdminLegislacionesPortletKeys.CCAA%>" label="admin.legislaciones.view.ccaa" class="js-select2 clsSelector2Width" >
                       <c:forEach var="CCAA" items="<%=ccaaList%>">
                            <%
                               CCAA ccaa = (CCAA) pageContext.getAttribute("CCAA");
                            %>
                            <aui:option value="${CCAA.ccaaId}" selected="<%=Validator.isNotNull(legislacion)?ccaa.getCcaaId() == legislacion.getCcaa():false%>" >
                                ${CCAA.nombre}
                            </aui:option>
                        </c:forEach>
					</select>                    
                    <!-- error de obligatorio -->
                     <span id="spnRequiredCCAA" class="clsRequiredSelect">									 
                        <span class="has-error">
                            <div class="form-validator-stack help-block">
                                <div role="alert" class=" required">
                                    <liferay-ui:message key="admin.legislaciones.edit.validator.required" />
                                </div>
                            </div>
                        </span>
                    </span>
                </div>
            </fieldset>
        </div>
        <div class="col-lg-3 col-md-6 col-12">
            <fieldset class="">
                <div class="form-group input-select-wrapper">
                    <div>
                        <label class="control-label" for="<portlet:namespace/><%=AdminLegislacionesPortletKeys.AYUNTAMIENTOS%>">
                        	<liferay-ui:message key="admin.legislaciones.view.ayuntamientos"/>
                        	<span class="contract-info text-secondary" data-toggle="tooltip" title="<liferay-ui:message key="admin.legislaciones.view.info.ayto"/>">
                                <i class="icon-info-sign"></i>
                            </span>
						</label>
                    </div>
                    <select id="cmb_ayto" name="cmb_ayto" class="js-select2 clsSelector2Width select2-hidden-accessible" data-select2-id="select2-data-cmb_ayto" tabindex="-1" aria-hidden="true" disabled=""><option value="" selected="" data-select2-id="select2-data-20-y0y8"></option></select>                    
                    <span style="display:none;">                               		
                        <aui:input name="<%=AdminLegislacionesPortletKeys.AYUNTAMIENTOS%>" value="<%=Validator.isNotNull(legislacion)?legislacion.getAyuntamiento():""%>"></aui:input>
                    </span>
                    <!-- error de obligatorio -->
                    <span id="spnRequiredAyto" class="clsRequiredSelect">									 
                        <span class="has-error">
                            <div class="form-validator-stack help-block">
                                <div role="alert" class=" required">
                                    <liferay-ui:message key="admin.legislaciones.edit.validator.required" />
                                </div>
                            </div>
                        </span>
                    </span>
                </div>
            </fieldset>
        </div>
        <div class="col-md-6 col-12 d-flex">
            <div class="col-md-6 col-12 pl-0">
                <label for="<%=AdminLegislacionesPortletKeys.FECHA_PUBLICACION%>" class="control-label">
                	<liferay-ui:message key="admin.legislaciones.view.fecha.publicacion"/>
                </label>
                <liferay-ui:input-date name="<%=AdminLegislacionesPortletKeys.FECHA_PUBLICACION%>"
                                                   lastEnabledDate="<%= new Date() %>"
                                                   yearValue="<%=fechaPublicacionCalendar.get(Calendar.YEAR)%>"
                                                   monthValue="<%=fechaPublicacionCalendar.get(Calendar.MONTH)%>"
                                                   dayValue="<%=fechaPublicacionCalendar.get(Calendar.DAY_OF_MONTH)%>" />
                <!-- error de obligatorio -->
                <span id="spnRequiredPublicacion" class="clsRequiredSelect">
                   <span class="has-error">
                       <div class="form-validator-stack help-block">
                           <div role="alert" class=" required">
                               <liferay-ui:message key="admin.legislaciones.edit.validator.incorrect" />
                           </div>
                       </div>
                   </span>
                </span>
            </div>
            <div class="col-md-6 col-12 pr-0">            
            	<label for="<%=AdminLegislacionesPortletKeys.FECHA_DEROGACION%>" class="control-label">
            		<liferay-ui:message key="admin.legislaciones.view.fecha.derogacion"/>
            	</label>
                 <span id="spnFechaDerogacion"> <!-- para buscar el check -->
                 	<liferay-ui:input-date name="<%=AdminLegislacionesPortletKeys.FECHA_DEROGACION%>" nullable="true" />
                 </span>    
            </div>
        </div>
    </div>
  	<div class="row align-items-start mb-2">
        <div class="col-lg-3 col-md-6 col-12">
            <fieldset class="cnae">
                <div class="form-group input-select-wrapper required">
                   <label class="control-label" for="<%=AdminLegislacionesPortletKeys.CNAE%>">
                    	<liferay-ui:message key="admin.legislaciones.view.cnae"/>
					</label>
                    <select  id="cmb_cnae" name="cmb_cnae" class="js-select2-multi clsSelector2Width required">
                        <%
                             CNAES cnaesSelect = (CNAES) pageContext.getAttribute("cnaes");

                         %>
                        <c:forEach var="cnaes" items="<%=cnaes%>">
                             <aui:option value="${cnaes.cnae}">
                                 ${cnaes.nombre} (${cnaes.cnae})
                             </aui:option>
                        </c:forEach>
                    </select>
                    <span id="spnRequiredCNAE" class="clsRequiredSelect">
                        <span class="has-error">
                            <div class="form-validator-stack help-block">
                                <div role="alert" class=" required">
                                        <liferay-ui:message key="admin.legislaciones.edit.validator.required" />
                                </div>
                            </div>
                        </span>
                    </span>
                    <span style="display:none;">
                    	<aui:input  name="<%=AdminLegislacionesPortletKeys.CNAE%>" value="<%=Validator.isNotNull(legislacion)? cnaesValueHide:""%>" multiple="true">                                		
                        </aui:input>
					</span>
                </div>
            </fieldset>
        </div>

        <div class="col-lg-3 col-md-6 col-12">
            <fieldset class="empresas">
                <div class="form-group input-select-wrapper required">
                    <label class="control-label" for="<portlet:namespace/><%=AdminLegislacionesPortletKeys.EMPRESAS%>">
                        <liferay-ui:message key="admin.legislaciones.view.empresas"/>
                    </label>
                    <select  id="cmd_empresas" name="<portlet:namespace/><%=AdminLegislacionesPortletKeys.EMPRESAS%>"
                        label="" class="js-select2-empresas clsSelector2Width" onchange=recalcularAlto(this)>
                        <c:forEach items="<%= comps %>" var="comp">
                            <aui:option value="${comp.empresaId}">${comp.description}</aui:option>
                        </c:forEach>
                    </select>
                    <span id="spnRequiredEmpresas" class="clsRequiredSelect">
                        <span class="has-error">
                            <div class="form-validator-stack help-block">
                                <div role="alert" class=" required">
                                    <liferay-ui:message key="admin.legislaciones.edit.validator.required" />
                                </div>
                            </div>
                        </span>
                    </span>
                </div>
            </fieldset>
        </div>

        <div class="col-lg-3 col-md-6 col-12">
            <fieldset class="">
                <div class="form-group input-select-wrapper required">
                    <label class="control-label" for="<portlet:namespace/><%=AdminLegislacionesPortletKeys.ETIQUETAS%>">
                        <liferay-ui:message key="admin.legislaciones.view.etiquetas"/>
                    </label>
                    <select  id="cmd_etiquetas" name="<portlet:namespace/><%=AdminLegislacionesPortletKeys.ETIQUETAS%>"
                    	label="" class="js-select2-etiquetas clsSelector2Width" onchange=recalcularAlto(this)>
                       <c:forEach var="category" items="<%= categories %>">
                            <%
                                 AssetCategory curCategory = (AssetCategory) pageContext.getAttribute("category");
                            %>
                            <aui:option value="<%= curCategory.getCategoryId() %>" ><%= curCategory.getTitle(locale) %></aui:option>
                       </c:forEach>
                    </select>
                    <!-- error de obligatorio -->
                    <span id="spnRequiredEtiquetas" class="clsRequiredSelect">
                        <span class="has-error">
                            <div class="form-validator-stack help-block">
                                <div role="alert" class=" required">
                                    <liferay-ui:message key="admin.legislaciones.edit.validator.required" />
                                </div>
                            </div>
                        </span>
                    </span>
                </div>
            </fieldset>
        </div>

    </div>
    <div class="row align-items-start mb-2">
        <div class="form-group col-md-12 required">
            <aui:input name="<%=AdminLegislacionesPortletKeys.DESCRIPCION%>" type="textarea" label="admin.legislaciones.view.descripcion" value="<%=Validator.isNotNull(legislacion)?legislacion.getDescripcion():""%>">
                                <aui:validator name="required" errorMessage="admin.legislaciones.edit.validator.required" />
            </aui:input>
        </div>
    </div>

    <%-- URGENTE --%>
    <div class="row align-items-start mb-2">
        <div class="col-lg-3 col-md-6 col-12 required">
            <fieldset>
                <legend><liferay-ui:message key="admin.legislaciones.view.urgente" /></legend>
                <div class="checksBorder">
                    <div class="custom-control custom-radio mr-4">
                        <input class="form-check-input" name="<portlet:namespace/><%=AdminLegislacionesPortletKeys.URGENTE%>" id="urgente1" value="true" type="radio"
                            <%=Validator.isNotNull(legislacion) && Validator.isNotNull(legislacion.getUrgente()) ? "checked" : "" %>>
                        <label class="form-check-label" for="urgente1"><liferay-ui:message key="admin.legislaciones.view.active.yes" /></label>
                    </div>
                    <div class="custom-control custom-radio mr-4">
                        <input class="form-check-input" name="<portlet:namespace/><%=AdminLegislacionesPortletKeys.URGENTE%>" id="urgente2" value="false" type="radio"
                            <%=Validator.isNull(legislacion) ? "checked" : Validator.isNull(legislacion.getUrgente()) ? "checked" : "" %>>
                        <label class="form-check-label" for="urgente2"><liferay-ui:message key="admin.legislaciones.view.active.no" /></label>
                    </div>
                </div>
            </fieldset>
        </div>
    </div>

  </div>
  
  <c:if test="<%=Validator.isNotNull(legislacion)%>">
  		
		<div class="row align-items-center">   
			<div class="col-md-6 col-12">
				<h3><liferay-ui:message key="admin.legislaciones.view.requisitos"/></h3>
            </div>
            <div class="col-md-6 col-12 text-right">
				<portlet:renderURL var="addRequisitoURL">
					<portlet:param name="<%=AdminLegislacionesPortletKeys.LEGISLACION_ID_EDIT%>" value="<%=String.valueOf(legislacionEditId)%>"></portlet:param>
					<portlet:param name="mvcPath" value="/requisito.jsp"></portlet:param>
				</portlet:renderURL>
				<a class="prv-iconLinks prv-iconLinks__import" href="<%= addRequisitoURL.toString() %>">
					<liferay-ui:message key="admin.legislaciones.view.requisito.nuevo"/>
				</a>    
			</div>
		</div>
        			        <div class="row">
        			        <div class="col-md-12 col-12">
								<div id="spnCargando" class="loading-animation"></div>
            					<!-- oculta hasta que este inicializada -->
            					<span id="spnTabla" style="display:none">
                                <table id="table_requisitos" class="display" style="width:100%">
                                    <thead>
                                    <tr>
                                    	<th><liferay-ui:message key="admin.legislaciones.view.requisito.numero"/></th>
                                        <th><liferay-ui:message key="admin.legislaciones.view.requisito"/></th>
                                        <th></th>
                                        <th><liferay-ui:message key="admin.legislaciones.view.requisito.fecha.baja"/></th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="<%=byLegislacion%>" var="requisito">
                                        <%
                                            Requisito requisito = (Requisito) pageContext.getAttribute("requisito");
                                        %>

                                        <tr>
                                        	<td>${requisito.requisitoId}</td>
                                            <td>${requisito.descripcion}</td>
                                            <td><i class="<%=Validator.isNotNull(requisito.getBaja()) && requisito.getBaja().before(now)?"bi bi-check-circle-fill":"bi bi-check-circle"%>"></i></td>
                                            <c:if test="<%=Validator.isNull(requisito.getBaja())%>">
                                                <td>-</td>
                                            </c:if>
                                            <c:if test="<%=Validator.isNotNull(requisito.getBaja())%>">
                                                <td data-sort="<%=dateFormatSearch.format(requisito.getBaja())%>">
                                                    <%=dateFormat.format(requisito.getBaja())%>
                                                </td>
                                            </c:if>

                                            <td>
                                                <liferay-portlet:renderURL varImpl="editURL">
                                                    <portlet:param name="<%=AdminLegislacionesPortletKeys.LEGISLACION_ID_EDIT%>" value="<%=String.valueOf(requisito.getLegislacionId())%>" />
                                                    <portlet:param name="<%=AdminLegislacionesPortletKeys.REQUISITO_ID_EDIT%>" value="<%=String.valueOf(requisito.getRequisitoId())%>" />
                                                    <portlet:param name="mvcPath" value="/requisito.jsp" />
                                                </liferay-portlet:renderURL>
                                                <a class="ico-acciones-tabla" href="<%=editURL.toString()%>">
                                                    <img src="<%=themeDisplay.getPathThemeImages()%>/ico_edit.png" alt="<liferay-ui:message key="edit" />" />
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                </span>
							</div>
                            </div>                            
                        

                        <script>
                            var tabla = null;
                            $(document).ready( function () {
                                tabla = $('#table_requisitos').DataTable( {
                                	"columnDefs": [        
                        					{
                        				      "targets"  : [0],
                        				      "orderable": false,
                        					},
                        					{
                          				      "targets"  : [1],
                          				      "orderable": false,
                          					},
                        					{
                          				      "targets"  : [2],
                          				      "orderable": false,
                          					},
                          					{
                          				      "targets"  : [3],
                          				      "orderable": false,
                          					}
                        				],
                        			"initComplete": function(settings, json) {
                        					$("#spnCargando").hide();
                        					$("#spnTabla").show();			    
                        			},
                                    dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
                                    buttons: [],
                                    responsive: true,
                                    pageLength: 10,
                                    "language": {
                                        "sProcessing":     "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sProcessing")%>",
                                        "sLengthMenu":     "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sLengthMenu")%>",
                                        "sZeroRecords":    "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sZeroRecords")%>",
                                        "sEmptyTable":     "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sEmptyTable")%>",
                                        "sInfo":           "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sInfo")%>",
                                        "sInfoEmpty":      "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sInfoEmpty")%>",
                                        "sInfoFiltered":   "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sInfoFiltered")%>",
                                        "sInfoPostFix":    "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sInfoPostFix")%>",
                                        "sSearch":         "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sSearch")%>",
                                        "sUrl":            "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sUrl")%>",
                                        "sInfoThousands":  "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sInfoThousands")%>,",
                                        "sLoadingRecords": "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sLoadingRecords")%>",
                                        "oPaginate": {
                                            "sFirst":    "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sFirst")%>",
                                            "sLast":     "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sLast")%>",
                                            "sNext":     "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sNext")%>",
                                            "sPrevious": "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sPrevious")%>"
                                        },
                                        "oAria": {
                                            "sSortAscending":  "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sSortAscending")%>",
                                            "sSortDescending": "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.sSortDescending")%>"
                                        },
                                        "buttons": {
                                            "copy": "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.copy")%>",
                                            "colvis": "<%=LanguageUtil.get(bundle, "admin.legislaciones.view.datatable.colvis")%>"
                                        }
                                    }
                                } );
                            } );

                        </script>

                    </c:if>

    <div class="button-holder d-flex justify-content-end my-4">
        <aui:button-row>

            
            <aui:button onClick="<%= cancelURL.toString() %>" value="back" cssClass="btn btn-outline-primary mr-4" primary="true"></aui:button>

            <aui:button type="submit" cssClass="btn btn-primary" onClick="checkSelect2()" />

        </aui:button-row>
    </div>

</aui:form>

<script>
var urlAytos = "<%= PortalUtil.getPortalURL(themeDisplay) %>/api/jsonws/legalplus.ayuntamiento/get-ayuntamientos-by-ccaa/ccaa-id/#ID_CCAA#?p_auth=#P_AUTH#";
var urlAytosAjax = null;
var jsonAytos = null;
var isLoaded = false; 

var campoFechaDerogacion = "#<portlet:namespace/><%=AdminLegislacionesPortletKeys.FECHA_DEROGACION%>"
var nameAmbito = "#<portlet:namespace/><%=AdminLegislacionesPortletKeys.AMBITO%>";	  
var nameCCAA = "<portlet:namespace/><%=AdminLegislacionesPortletKeys.CCAA%>";	  
var nameAyto = "#<portlet:namespace/><%=AdminLegislacionesPortletKeys.AYUNTAMIENTOS%>";
var nameCNAE = "#<portlet:namespace/><%=AdminLegislacionesPortletKeys.CNAE%>";
var legislacionEditId = '<%=ParamUtil.getString(request, AdminLegislacionesPortletKeys.LEGISLACION_ID_EDIT, "")%>';
var dateDerogacion = '<%=(Validator.isNotNull(legislacion))? legislacion.getDerogacion() : null %>';

var seleccione = '<liferay-ui:message key="admin.legislaciones.view.select"/>';

$(document).ready( function () {

    $('.js-select2-etiquetas').select2({
          placeholder: seleccione,
          multiple : true,
          language: "es",
          allowClear: true
    }).val("<%= Validator.isNotNull(legislacion) ? legislacion.getEtiquetas() : "" %>".split(";")).trigger('change');

    $('.js-select2-empresas').select2({
         placeholder: seleccione,
         multiple : true,
         language: "es",
         allowClear: true
    }).val("<%= Validator.isNotNull(legislacion) ? legislacion.getEmpresas() : "" %>".split(";")).trigger('change');
	
    $('.js-select2').select2({
        placeholder: seleccione,
        multiple : false,
        language: "es",
        allowClear: true
    });

    $('.js-select2-multi').select2({
        placeholder: seleccione,
        multiple : true,
        language: "es",
        allowClear: true
    }).val($(nameCNAE).val().split(",")).trigger('change');

    if ($('#<portlet:namespace/><%=AdminLegislacionesPortletKeys.FAMILIA_TODAS%>').is(':checked')) {
    $('.check-app').prop("checked", false);
    $('.check-app').attr("disabled", true);
    $('#<portlet:namespace/><%=AdminLegislacionesPortletKeys.FAMILIA_TODAS%>').prop("checked", true);
    $('#<portlet:namespace/><%=AdminLegislacionesPortletKeys.FAMILIA_TODAS%>').attr("disabled", false);
    }

    var nodoAyto = $(nameAyto);
    var valueAytoHide =  nodoAyto.val();

    var valueCCAA = null;
    var isLoadAytos = false;

    var $eventSelect = $(".js-select2"); //solo para ccaa y aytos
    $eventSelect.on("change", function (e) {

        if(e.target.id == "cmb_ayto" && isLoadAytos){
            $(nameAyto).val(e.target.value);
        }else if(e.target.name == nameCCAA){
            //no tiene valor o lo dejan en null
            if(e.target.value == null || e.target.value == ""){
                $("#cmb_ayto").empty();
                valueCCAA = null;
            }else if( valueCCAA != e.target.value) {
                valueCCAA =  e.target.value;
                $("#cmb_ayto").empty();

                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function(){
                    if (this.readyState == 4 && this.status == 200){
                        jsonAytos = JSON.parse(this.responseText).sort(function(a, b){
                            return a.startDate - b.startDate;
                        });
                        try{
                        var newOption = new Option("", "", true, true);

                        $("#cmb_ayto").append(newOption); //.trigger('change');

                        for(var i = 0; i < jsonAytos.length; i++){
                            newOption = new Option(jsonAytos[i].nombre, jsonAytos[i].ayuntamientoId, true, true);
                            $("#cmb_ayto").append(newOption); //.trigger('change');
                        }

                        if(isLoaded == true){
                            $("#cmb_ayto").val(valueAytoHide);
                            isLoaded = false;
                        }else{
                            $("#cmb_ayto").val('');
                            $(nameAyto).val("");
                        }
                        }catch(e){
                            console.error("carga aytos: "  + e);
                        }
                        isLoadAytos = true;
                    }
                }

                urlAytosAjax = urlAytos.replace("#ID_CCAA#",e.target.value).replace("#P_AUTH#",Liferay.authToken);
                xhr.open("GET", urlAytosAjax, true);
                xhr.send(null);

            }
        }
    });

    try{
        if(valueAytoHide != ""){
            isLoaded = true;
        }
        $eventSelect.change();
        $eventSelect.trigger("change");
        isLoaded = true;

        changeAmbito( $("#<portlet:namespace/><%=AdminLegislacionesPortletKeys.AMBITO%>").val() );
    } catch(e) { }

    if(legislacionEditId == ""){ //es nuevo ? fecha
        $("#spnFechaDerogacion  input[type=checkbox]").click();
    }else{
        if(dateDerogacion == "null"){
            $("#spnFechaDerogacion  input[type=checkbox]").click();
        }else{
            $(campoFechaDerogacion).val(dateDerogacion);
        }
    }
});

function checkSelect2(){	
	let isOk = true;
	if( ($(nameAmbito).val() == "2" &&  $("#" + nameCCAA).val() == null) || ($(nameAmbito).val() == "3" &&  $("#" + nameCCAA).val() == null)){ //tiene seleccionado ambito ccaa sin ccaa
		$("#spnRequiredCCAA").attr("class","clsRequiredSelect_visible");
		isOk = false;
	}
	if($(nameAmbito).val() == "3" && $("#cmb_ayto").val() == null){ //tiene seleccionado ambito ayto sin ayto	
		$("#spnRequiredAyto").attr("class","clsRequiredSelect_visible");
		isOk = false;
	}
	if($("#cmb_cnae").val() == "" && $("#cmd_empresas").val() == ""){
		$("#spnRequiredCNAE").attr("class","clsRequiredSelect_visible");
		$("#spnRequiredEmpresas").attr("class","clsRequiredSelect_visible");
		isOk = false;
	}

	if($("#cmd_etiquetas").val() == "") {
        $("#spnRequiredEtiquetas").attr("class","clsRequiredSelect_visible");
        isOk = false;
	}

	if($("#<portlet:namespace/><%=AdminLegislacionesPortletKeys.FECHA_PUBLICACION%>").val() != "") {
	    var curDate = $("#<portlet:namespace/><%=AdminLegislacionesPortletKeys.FECHA_PUBLICACION%>").val();
	    var [day, month, year] = curDate.split('/');
	    if (new Date(+year, month - 1, +day) > new Date()) {
	        $("#spnRequiredPublicacion").attr("class","clsRequiredSelect_visible");
	        isOk = false;
	    }
	}

	if ($('.check-app:checkbox:checked').length == 0) {
	    $("#spnRequiredFamilia").attr("class","clsRequiredSelect_visible");
        isOk = false;
    } else {
        $("#spnRequiredFamilia").attr("class","clsRequiredSelect");
    }
	
	if(!isOk){
		event.preventDefault();
		event.stopPropagation();
		return false;	
	}
	return true;
}

function recalcularAlto(_node){
	var howMuch =$("#" + _node.id).val().length; 
  	howMuch = (30 * howMuch) + 3;  	
  	_node.nextElementSibling.style.marginBottom =  howMuch + "px";
}
  
function changeAmbito(value){	
	$("#spnRequiredCCAA").attr("class","clsRequiredSelect");
	$("#spnRequiredAyto").attr("class","clsRequiredSelect");
	if( value == "2" ){ //CCAA
		$("#" + nameCCAA).prop("disabled", false);
		$("#cmb_ayto").prop("disabled", true);
		$("#cmb_ayto").val('').trigger("change");
	}else if( value == "3"){ // AYTO
		$("#" + nameCCAA).prop("disabled", false);
		$("#cmb_ayto").prop("disabled", false);
	}else{	
		$("#" + nameCCAA).prop("disabled", true);
		$("#cmb_ayto").prop("disabled", true);
		$("#cmb_ayto").val('').trigger("change");
		$("#" + nameCCAA).val('').trigger("change");
		
		
	} 
}

$(document).on("click", ".check-app", function(e){
    if ( $(this).attr('id') == "<portlet:namespace/><%=AdminLegislacionesPortletKeys.FAMILIA_TODAS%>" && $(this).is(':checked')) {
        $('.check-app').prop("checked", false);
        $('.check-app').attr("disabled", true);
        $(this).prop("checked", true);
        $(this).attr("disabled", false);
    } else {
        $('.check-app').attr("disabled", false);
    }
});

$(document).on("change", "#cmb_cnae", function(e) {
    var value = $("#cmb_cnae").val();
    var empresas = $('#cmd_empresas').val();

    if( value.includes("99999") ){
        $('#cmb_cnae').prop('disabled', true);
        $('#cmd_empresas').prop('disabled', true);
        $('.select2-selection__choice__remove').css("display", "block");
        $(nameCNAE).val("99999");

        if (value.length > 1) {
            $("#cmb_cnae").val('99999').trigger('change');
        }

    } else {

        if (value != "")    $('#cmd_empresas').prop('disabled', true);
        else                $('#cmd_empresas').prop('disabled', false);

        if (empresas.length != 0) $('#cmb_cnae').prop('disabled', true);
        else                      $('#cmb_cnae').prop('disabled', false);

        $(nameCNAE).val($("#cmb_cnae").val());
    }
    $("#spnRequiredCNAE").attr("class","clsRequiredSelect");

    var howMuch =$("#cmb_cnae").val().length
    howMuch = (30 * howMuch) + 3;
    e.target.nextElementSibling.style.marginBottom =  howMuch + "px";
});

$(document).on("click", ".cnae .select2-selection__choice__remove", function(){
    var value = $("#cmb_cnae").val();
    if( value.includes("99999") ){
        $('.select2-selection__choice__remove').css("display", "block");
        $('#cmb_cnae').prop('disabled', false);
        $("#cmb_cnae").val('').trigger('change');
    }
});

$('#cmd_empresas').on('select2:unselect', function (e) {
    var value = $("#cmb_cnae").val();
    var empresas = $('#cmd_empresas').val();

    $("#cmd_empresas").val(empresas).trigger('change');
    $("#cmb_cnae").val(value).trigger('change');
});

$('#cmd_empresas, #cmb_cnae').on('select2:select', function (e) {
    $("#cmb_cnae").trigger('change');
});

</script>

