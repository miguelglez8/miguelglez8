<%@ page import="com.legalplus.liferay.portlet.admin.legislaciones.web.constants.AdminLegislacionesPortletKeys" %>
<%@ page import="com.liferay.asset.kernel.model.AssetCategory" %>
<%@ page import="com.liferay.asset.kernel.model.AssetVocabulary" %>
<%@ page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil" %>
<%@ page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>

<%@ page import="com.legalplus.liferay.portlet.admin.legislaciones.web.enums.AmbitoAplicacion" %>
<%@ page import="com.legalplus.liferay.portlet.admin.legislaciones.web.enums.FamiliaNormativa" %>
<%@ page import="com.legalplus.liferay.portlet.admin.legislaciones.web.enums.TipoNormativa" %>


<%@ page import="com.legalplus.liferay.portlet.admin.legislaciones.web.util.FamiliaNormasUtil" %>

<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.*" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.*" %>
<%@ include file="/init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">

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
 <!-- dependencias select2 -->
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<!-- Libreria español -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/i18n/es.js"></script>
<!--	iconos -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<!-- estilo site-->
<!-- <link rel="stylesheet" href="/documents/66343/72018/estiloa-preving.css/841c7886-e64a-a42c-d8e9-38c9d2db0be9"> -->

<script>
	Liferay.Loader.define.amd = Liferay.Loader.define._amd;
	Liferay.Loader.define.amd = false;
</script>


<%
    List<Legislacion> legislacionesList = LegislacionLocalServiceUtil.getLegislacions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    List<CCAA> ccaaList = CCAALocalServiceUtil.getCCAAs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    
    /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
    SimpleDateFormat dateFormatSearch = new SimpleDateFormat("yyyyMMdd");
    dateFormatSearch.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));*/

    

    Calendar calStart = Calendar.getInstance();
    calStart.add(Calendar.MONTH, -24);
    

    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

    String yes = LanguageUtil.get(bundle, "admin.legislaciones.view.active.yes");
    String no  = LanguageUtil.get(bundle, "admin.legislaciones.view.active.no");
    String all = LanguageUtil.get(bundle, "admin.legislaciones.view.active.all");

    AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(themeDisplay.getScopeGroupId(), AdminLegislacionesPortletKeys.VOCABULARIO_CONSULTOR);
    List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
%>

<liferay-ui:success key="legislacion-save-success" message="admin.legislaciones.edit.save.success" />
<liferay-ui:success key="requisito-save-success" message="admin.requisito.edit.save.success" />

<liferay-portlet:renderURL varImpl="editURL">
	<portlet:param name="legislacionEditId" value="ID_LEGISLACION" />
	<portlet:param name="mvcPath" value="/legislacion.jsp" />
</liferay-portlet:renderURL>
<portlet:renderURL var="addLegislacionURL">
	<portlet:param name="mvcPath" value="/legislacion.jsp"></portlet:param>
</portlet:renderURL>

<main class="content">
    <div class="formulario">
    	  <div class="title-group">
        		<div class="row">
            		<div class="col-md-6 col-12">
                		<h2><liferay-ui:message key="admin.legislaciones.view.title"/></h2>
            		</div>
            		<div class="col-md-6 col-12 text-right">
                		<a class="prv-iconLinks prv-iconLinks__plus" href="<%= addLegislacionURL.toString() %>">
                			<liferay-ui:message key="admin.legislaciones.view.btnNuevaLegislacion"/>
                		</a>
                		<a class="prv-iconLinks prv-iconLinks__import" href="${importadorUrl}">
                		    <liferay-ui:message key="admin.legislaciones.view.btnImportar"/>
                		</a>
            		</div>
        		</div>
    		</div>

	<div class="prv-search prv-form">
		<div class="row align-items-start mb-4">
			<div class="col-lg-3 col-md-6 col-12">
				<label for="inpBuscarCajon"><liferay-ui:message key="admin.legislaciones.view.textoBuscar" /></label>
				<input type="text" id="inpBuscarCajon">
			</div>
			<div class="col-lg-3 col-md-6 col-12">
				<fieldset>
					<legend>
						<liferay-ui:message key="admin.legislaciones.view.active" />
					</legend>
					<div class="checksBorder">
                        <div class="custom-control custom-radio mr-4">
                            <input class="form-check-input" name="inpSearchActive"
                                id="inpSearchActive1" value="0" type="radio" checked=""
                                onchange="changeActivo(this.value);"> <label
                                class="form-check-label" for="inpSearchActive1"><%=yes%></label>
                        </div>
                        <div class="custom-control custom-radio mr-4">
                            <input class="form-check-input" name="inpSearchActive"
                                id="inpSearchActive2" value="1" type="radio"
                                onchange="changeActivo(this.value);"> <label
                                class="form-check-label" for="inpSearchActive2"><%=no%></label>
                        </div>
                        <div class="custom-control custom-radio mr-4">
                            <input class="form-check-input" name="inpSearchActive"
                                id="inpSearchActive3" value="2" type="radio"
                                onchange="changeActivo(this.value);"> <label
                                class="form-check-label" for="inpSearchActive3"><%=all%></label>
                        </div>
					</div>
				</fieldset>
			</div>
			<div class="col-lg-3 col-md-6 col-12 d-flex">
                <div class="col-md-6 col-12 pl-0">
                    <label for="startDate"><liferay-ui:message
                            key="admin.legislaciones.view.desde" /></label>
                    <liferay-ui:input-date name="startDate" nullable="true"
                        showDisableCheckbox="false"
                        yearValue="<%=calStart.get(Calendar.YEAR)%>"
                        monthValue="<%=calStart.get(Calendar.MONTH)%>"
                        dayValue="<%=calStart.get(Calendar.DAY_OF_MONTH)%>" />
                </div>

                <div class="col-md-6 col-12 pr-0">
                    <label for="ID_INPUT_8"><liferay-ui:message
                            key="admin.legislaciones.view.hasta" /></label>
                    <liferay-ui:input-date name="endDate" nullable="true"
                        showDisableCheckbox="false" />
                </div>
            </div>
		</div>
		<div class="row align-items-start mb-4">
            <div class="col-lg-3 col-md-6 col-12">
            	<fieldset class="">
            		<div class="form-group input-select-wrapper">
            			<label class="control-label" for="<portlet:namespace/><%=AdminLegislacionesPortletKeys.ETIQUETAS%>">
            				<liferay-ui:message key="admin.legislaciones.view.etiquetas"/>
            			</label>
            			<select  id="cmd_etiquetas" name="<portlet:namespace/><%=AdminLegislacionesPortletKeys.ETIQUETAS%>" class="js-select2 form-item">
            			   <option value=""></option>
            			   <c:forEach var="category" items="<%= categories %>">
            					<% AssetCategory curCategory = (AssetCategory) pageContext.getAttribute("category"); %>
            					<option value="<%= curCategory.getCategoryId() %>" ><%= curCategory.getTitle(locale) %></option>
            			   </c:forEach>
            			</select>
            		</div>
            	</fieldset>
            </div>
		</div>

		<div class="prv-advanced-search mb-4">
			<a class="prv-advanced-search__trigger" data-toggle="collapse"
				href="#advanced-search" role="button" aria-expanded="true"
				aria-controls="collapseExample">
				<liferay-ui:message key="admin.legislaciones.view.busquedaAvanzada" />
			</a>
		</div>
		<div class="collapse" id="advanced-search">
			<div class="row align-items-start mb-4">
				<div class="col-lg-3 col-md-6 col-12">
					<label for="cmb_tipo"><liferay-ui:message key="admin.legislaciones.view.tipo" /></label>
					<select id="cmb_tipo"
						name="<%=AdminLegislacionesPortletKeys.TIPO%>" label=""
						class="js-select2-noSearch form-item">
						<option value=""></option>
						<c:forEach items="<%=TipoNormativa.values()%>" var="tipo">
							<option value="${tipo.codigo}"><liferay-ui:message key="${tipo.descripcion}" /></option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<label for="cmb_familia"><liferay-ui:message key="admin.legislaciones.view.familia" /></label>
					<select
						id="cmb_familia" name="<%=AdminLegislacionesPortletKeys.FAMILIA%>"
						label="" class="js-select2-noSearch clsSelector2Width form-item">
						<option value=""></option>
						<c:forEach items="<%=FamiliaNormativa.values()%>" var="normativa">
							<option value="${normativa.codigo}"><liferay-ui:message
									key="${normativa.descripcion}" /></option>
						</c:forEach>
					</select>
				</div>

				<div class="col-lg-3 col-md-6 col-12">
					<label for="cmb_ambito"><liferay-ui:message key="admin.legislaciones.view.ambito" /></label>
					<select id="cmb_ambito"
						name="<%=AdminLegislacionesPortletKeys.AMBITO%>" label=""
						onChange="changeAmbito(this.value)"
						class="js-select2-noSearch clsSelector2Width form-item">
						<option value=""></option>
						<c:forEach items="<%=AmbitoAplicacion.values()%>" var="ambito">
							<option value="${ambito.codigo}"><liferay-ui:message
									key="${ambito.descripcion}" /></option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row align-items-start mb-4">
				<div class="col-lg-3 col-md-6 col-12">
					<label for="cmb_ccaa">
					    <liferay-ui:message key="admin.legislaciones.view.ccaa" />
					    <span class="contract-info text-secondary" data-toggle="tooltip" title="<liferay-ui:message key="admin.legislaciones.view.info.ccaa"/>">
                            <i class="icon-info-sign"></i>
                        </span>
                    </label>
                    <select id="cmb_ccaa"
						name="<%=AdminLegislacionesPortletKeys.CCAA%>" label=""
						class="js-select2-ccaa">
						<option value=""></option>
						<c:forEach var="CCAA" items="<%=ccaaList%>">
							<option value="${CCAA.ccaaId}">${CCAA.nombre}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<label for="cmb_ayuntamientos">
					    <liferay-ui:message key="admin.legislaciones.view.ayuntamientos" />
                        <span class="contract-info text-secondary" data-toggle="tooltip" title="<liferay-ui:message key="admin.legislaciones.view.info.ayto"/>">
                            <i class="icon-info-sign"></i>
                        </span>
                    </label>
                    <select
						id="cmb_ayuntamientos"
						name="<%=AdminLegislacionesPortletKeys.AYUNTAMIENTOS%>" label=""
						class="js-select2 clsSelector2Width">
					</select>
				</div>
			</div>

		</div>

		<div class="row align-items-end">
			<div class="col-md-12 col-12 text-right">
				<div class="btnClear">
					<button class="btn btn btn-outline-primary btn-sm btn-primary" type="button" onclick="cleanFilters()">
						<%=LanguageUtil.get(bundle, "admin.legislaciones.view.cleanFilters")%>
					</button>
					<button class="btn btn btn-sm btn-primary" type="button" onclick="buscarLegislaciones()">
						<%=LanguageUtil.get(bundle, "admin.legislaciones.view.buscarLegislaciones")%>
					</button>
					<span id="spanErrorBusqueda" role="alert" class="has-error help-block"></span>
				</div>
			</div>
		</div>

	</div>


	<div class="row">
            <div class="col">
            	<div id="spnCargando" class="loading-animation"></div>
            	<!-- oculta hasta que este inicializada -->
            	<div id="spnTabla" style="display:none">
                    <table id="table_legislaciones" class="display" style="width:100%">
                        <thead>
                        <tr>
                            <th class="no-sort"><liferay-ui:message key="admin.legislaciones.view.requisito.numero"/></th>
                            <th class="no-sort"><liferay-ui:message key="admin.legislaciones.view.legislacion"/></th>
                            <th class="no-sort"><liferay-ui:message key="admin.legislaciones.view.familia"/></th>
                            <th class="no-sort"><liferay-ui:message key="admin.legislaciones.view.tipo"/></th>
                            <th class="no-sort"><liferay-ui:message key="admin.legislaciones.view.ambito"/></th>
                            <th class="no-sort"><liferay-ui:message key="admin.legislaciones.view.cnae"/></th>
                            <th class="no-sort"><liferay-ui:message key="admin.legislaciones.view.fecha.publicacion"/></th>
                            <th class="no-sort"><liferay-ui:message key="admin.legislaciones.view.fecha.derogacion"/></th>
                            <th class="no-sort"></th>
                            <!--  <th class="no-sort"></th>
                            <th class="no-sort"></th> -->
                        </tr>
                        </thead>

                    </table>
                </div><!-- end capa tabla -->
            </div>
        </div>
    </div>
</main>

<portlet:resourceURL id="buscarLegislaciones" var="buscarLegislacionesURL"/>
<portlet:resourceURL id="buscarRequisitosByLegislacion" var="buscarRequisitosByLegislacionURL"/>
<script>

    /* posiciones dentro del json de los campos */
    var P_NumLegislacion = 0;
    var P_Legislacion = 1;
    var P_Legislacion_oculta= 14; //oculta
    var P_Familia = 2 ;
    var P_Tipo = 3 ;
    var P_Ambito = 4;
    var P_CNAE = 5;
    var P_FPubli = 6;
    var P_FDero = 7;

    //var P_Etiquetas = 8;
    //var P_Descr = 9;
    //var P_Requisitos = 10;

    var P_Eye = 8;
    var P_Link = 9;
    var P_Editar = 10;

    var P_CCAA = 11;
    var P_Ayto = 12;
    var P_Activo = 13;

    var imgEdit = "<%=themeDisplay.getPathThemeImages()%>/ico_edit.png";
    var editURL = "<%=editURL.toString()%>";

    var arrCampos = [$("#cmb_tipo"), $("#cmb_familia"),$("#cmb_ambito"),$("#cmb_ccaa"),$("#cmb_ayuntamientos"),$("#cmd_etiquetas")]; //campos de filtro;
    var arrColumnIndex =  [P_Tipo,P_Familia,P_Ambito,P_CCAA,P_Ayto]; //su equivalencia indice del json/columnas
 	var arrCampoCompareType = ["text","text","text","value","value"]; //comparamos texto o value del selector
	
 	var jsonRequisitos = null;
    var jsonAytos = null;
    var urlAytos = "https://" + window.location.hostname + "/api/jsonws/legalplus.ayuntamiento/get-ayuntamientos-by-ccaa/ccaa-id/#ID_CCAA#?p_auth=#P_AUTH#";
    var urlAytosAjax = null;
    var filterActivo = "Si"; // para pasarle el click en el radio de activo (idiomasï¿½?)
	var tabla = null;
	var seleccione = '<liferay-ui:message key="admin.legislaciones.view.select"/>';

	var dataFilter = {}; // Objeto de filtrado
	dataFilter.activo         = "0";
	dataFilter.tipo           = "1";
	dataFilter.familia        = "0;";
	dataFilter.ambito         = "1";
	dataFilter.ccaa           = "0";
	dataFilter.ayuntamiento   = "0";
	dataFilter.fechaDesde     = new Date("2021-12-31").getTime();
	dataFilter.fechaHasta     = new Date("2022-12-31").getTime();
	dataFilter.texto          = "";

	//var dateTabla = null;
	//const options = { dateStyle : "medium" };
	//familias, tipos, ambito ... desde un profutils del server ¿?
	var dateDatatable = null;			
	var searchCombo = {};
	var optionCombo = null;
	var txtCombo = null;
	function getTextValueCombo(_comboName, _value){
		if(searchCombo[_comboName] == undefined || searchCombo[_comboName] == null) searchCombo[_comboName] = document.getElementById(_comboName); // $("#" + _comboName);

		txtCombo = "";
		for( var i = 0; i < searchCombo[_comboName].options.length; i++){
			optionCombo = searchCombo[_comboName].options[i];
			if( optionCombo.value == _value){
				txtCombo = optionCombo.text;
			}
		}
		return txtCombo;
	}


	var urlAjaxBase = "<%=buscarLegislacionesURL.toString()%>" ;
	var urlAjax = "<%=buscarLegislacionesURL.toString()%>";
	var urlAjaxRequisitos = "<%=buscarRequisitosByLegislacionURL.toString()%>";	
	
	var jsonResultTotal = null; 
	
	var maxTextoNombre = 50;
	$(document).ready( function () {

		  $('.js-select2').select2({
              placeholder: seleccione,
              multiple : false,
              language : "es",
              allowClear: true
          });
		 //sin buscador
		  $('.js-select2-noSearch').select2({
              placeholder: seleccione,
              multiple : false,
              language : "es",
              minimumResultsForSearch: Infinity,
              allowClear: true
          });

		  $('.js-select2-aytos').select2({
			  placeholder: seleccione,
			  language : "es",
              multiple : false,
              allowClear: true
          });
		  $('.js-select2-ccaa').select2({
              placeholder: seleccione,
              multiple : false,
              language : "es",
              allowClear: true
          });

          $("#cmb_ccaa").prop("disabled", true);
          $("#cmb_ayuntamientos").prop("disabled", true);
		  
          //Por defecto, ultimos 2 a񯳠+ activos
          var dateBefore = new Date();
          dateBefore.setTime(dateBefore.getTime() - 2 * ((((365 * 24) * 60) * 60 )* 1000));
          urlAjax = urlAjaxBase + "&activo=0" + "&fechaDesde=" + dateBefore.getTime();

		tabla = $('#table_legislaciones').on('preXhr.dt', function ( e, settings, data ) {
	     	//console.log("llamanda ajax");   
	     	$("#spnCargando").hide();
			$("#spnTabla").show();
	    }).DataTable( {
			"ajax": {
				"url": urlAjax,
                "dataSrc": function ( json ) { jsonResultTotal = json.data; return json.data; }
            },
            "columns": [
                {"data" : "legislacionId"},
                {"data" : "nombre"},
                {"data" : "familia"},
                {"data" : "tipo"},
                {"data" : "ambito"},
                {"data" : "cnaes"},//CNAE
                {"data" : "publicacion"},
                {"data" : null},//FECHA DEROGACION
                {"data" : "legislacionId"},//EYE
                {"data" : "enlace"},//LINK
                {"data" : "legislacionId"},//EDITAR
                {"data" : "ccaa"},//CCAA
                {"data" : "ayuntamiento"},//AYTO
                {"data" : null},//ACTIVO

                {"data" : "nombre"} //OCULTA PARA EXCEL
            ],
			"columnDefs": [
				{
				      "targets"  : [P_NumLegislacion],
				      "orderable": true
				},
				{
				      "targets"  : [P_Legislacion],
				      "orderable": true,
				      "render": function (data, type, row) {
				    	  	if(data.length > maxTextoNombre ){
				    	  		return data.substring(0, maxTextoNombre) + "...";
				    	  	}
				    	  	return data;
						}
				},

				//Oculta para exportar en excelHtml5
				{
                      "targets"  : [P_Legislacion_oculta],
                      "orderable": false,
                      "visible"  : false,
                      "render": function (data, type, row) {
                            return data;
                      }
                },


				{
				      "targets"  : [P_Familia],
				      "orderable": true,
				      "render": function (data, type, row) {
				    	  	var spn = "";
				    	  	var arr = data.split(";");
				    	  	for(var i = 0; i < arr.length; i++){
				    	  		spn += '<div class="d-flex flex-column align-items-start"><span class="badge badge-pill badge-info">' + getTextValueCombo("cmb_familia", arr[i]) + "</span></div>";
				    	  	}
				    	  	return spn;

            		}
				},
				{
				      "targets"  : [P_Tipo],
				      "orderable": false,
				      "visible": false
				},
				{
				      "targets"  : [P_Ambito],
				      "orderable": false,
				      "visible": false,
				      "render": function (data, type, row) {
				    	  	var spn = "";
				    	  	var arr = data.split(";");
				    	  	for(var i = 0; i < arr.length; i++){
				    	  		spn += getTextValueCombo("cmb_ambito", arr[i]) ;
				    	  	}
				    	  	return spn;
        				}
				},
				{
				      "targets"  : [P_CNAE],
				      "orderable": false,
				      "render": function (data, type, row) {

                          if(row.empresas != "") return '<span class="badge badge-pill badge-info">' +
                                                            '<liferay-ui:message key="admin.legislaciones.view.empresas"/>' +
                                                        '</span>';

				    	  if(data == null || data == "") return "";
				    	  var spans = "";
				    	  var arr = data.toString().split("},{");
				    	  var arrItem = null;
				    	  const max = 3;
				    	  var isMax = false;
				    	  
				    	  for(var i = 0; i < arr.length; i++){
				    		  	arrItem = arr[i].split(", nombre=");
								
								if(i == max){
									spans += '<span id="cnae_max_title_' + row.legislacionId + '" role="button" class="badge badge-pill badge-light badge-btn" onclick="showCNAESCell(' + "'" + row.legislacionId + "'" + ')">+' + (arr.length - max) + "</span>";
									spans += '<span class="hide" id="cnae_max_' + row.legislacionId + '">';
									isMax = true;
								}
								spans +='<span class=\"badge badge-info\" title="' + arrItem[1].replace("}","") + '">' +  arrItem[0].replace("{cnae=","").replace("cnae=","") +'</span>';	
								
				    	  }
						  if(isMax) spans += "</span>";
				    	  return spans
            			}
				},
				{
				      "targets"  : [P_FPubli],
				      "orderable": true,
				      "render": function (data, type, row) {				    	  
				    	  if(data == null || data == undefined || data == "") return "";
				    	  dateDatatable = new Date(data);
				    	  return "<span class=\"hideSpanDate\">" + dateDatatable.toLocaleDateString('en-GB').split('/').reverse().join('') +  "</span><span>" + dateDatatable.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>";
            			}
				},
				{
				      "targets"  : [P_FDero],
				      "orderable": true,
				      "visible": false,
				      "render": function (data, type, row) {
				      		if(data["derogacion"])   return (new Date(data["derogacion"])).toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' });
				    		return "";
            			}
				},		
				
				
				{
				      "targets"  : [P_Eye],
				      "orderable": false,
		              "render": function (data, type, row) {		            	  			
		                			var span = '<div class="d-flex justify-content-end">'; 
		                			span += '<span class="ico-acciones-tabla"><span class="viewDetail icon-eye-open" legislacionId="' +  data + '"></span></span>';
		                			span += '<a class="ico-acciones-tabla" href="' + row.enlace  + '" target="_blank"><span class="icon-link"></span></a>';
		                			/*span += '<a class="ico-acciones-tabla" href="' + editURL.replace("ID_LEGISLACION", data)  
		                					+ '" ><image src="' + imgEdit + '" class="icon-edit"></img></a>';*/
		                			/* span += '<a class="ico-acciones-tabla" href="' + editURL.replace("ID_LEGISLACION", data)
		                					+ '" ><span  class="icon-pencil"></span></a>';*/
		                		    span += '<a class="ico-acciones-tabla" onclick="saveParamsSearch()" href="' + editURL.replace("ID_LEGISLACION", data)
                                            		                					+ '" ><span  class="icon-pencil"></span></a>';
		                			span += "</div>";


		              				return span;
		              }
				},
				{
					"targets"  : [P_Link],
				    "orderable": false,
				    "visible": false
				},
				{
				      "targets"  : [P_Editar],
				      "orderable": false,
				      "visible": false
				},
				{
					"targets": [ P_CCAA ], //ccaa
					"visible": false,
					"searchable": true
				},
				{
					"targets": [ P_Ayto ], //ayto
					"visible": false,
					"searchable": true
				},
				{
					"targets": [ P_Activo ], //activo
					"visible": false,
					"searchable": true
				}
			],
			"initComplete": function(settings, json) {
				var inputBuscarHTML = '<input id="inpBuscarCajon" class="field"></input>';
				/*$("div.btnClear").html(inputBuscarHTML + '&#160;&#160;<button class="btn btn btn-outline-primary btn-sm btn-primary" type="button" onclick="cleanFilters()">' + '<%=LanguageUtil.get(bundle, "admin.legislaciones.view.cleanFilters")%>' + '</button>&#160;&#160;<button class="btn btn btn-outline-primary btn-sm btn-primary" type="button" onclick="buscarLegislaciones()">' + '<%=LanguageUtil.get(bundle, "admin.legislaciones.view.buscarLegislaciones")%>' + '</button><span id="spanErrorBusqueda" role="alert" class="has-error help-block"></span>');*/
				$("#spnCargando").hide();
				$("#spnTabla").show();
			},
			"order": [[ P_FPubli, "desc" ],[P_Legislacion,"asc"]],
			dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
			responsive: true,
			pageLength: 20,
			lengthMenu: [[10, 25, 50, 100], [10, 25, 50, 100]],
			buttons: [
				'csv', 'print',
				 { //EXCEL
                    extend: 'excelHtml5',
                    text: 'Excel',
                    filename: 'listado_legislaciones_legalplus.xls',
                    exportOptions: {
                    	
                        columns: [ P_NumLegislacion, P_Legislacion_oculta, P_Familia, P_Tipo, P_Ambito,P_Link, P_FPubli, P_FDero ] //USAMOS P_LINK PARA LOS CNAES
                    },
                    customize: function (xlsx) {
                        var sheet = xlsx.xl.worksheets['sheet1.xml'];
						var i = 0;
						var item = null, itemsCNAES = null;
						var str = null, strCNAES = "";

						
                        $('row c[r^="F"]', sheet).attr( 's', '0' );
						$('row c[r^="F"]', sheet).each( function () { // CNAE
							//$('is t', this).attr( 's', '0' ); 
							//$('is t', this).text("HOLA");	
							strCNAES = "";
							//if(i > 0 &&  i < jsonResultTotal.length + 2 ){ // las dos primersas con del titulo y de la cabecera
								item = jsonResultTotal[i];								
								itemsCNAES = item.cnaes;	                            
	                            for(var x = 0; x < itemsCNAES.length; x++){
	                            	str = itemsCNAES[x].split(", nombre=");
	                            	if(strCNAES != "") strCNAES += " \r\n"; 
	                            	strCNAES += str[1].replace("}","") + " (" + str[0].replace("{cnae=","").replace("cnae=","") + ") \n\r";
	                            }
	                            	
								console.log("strCNAES: " + strCNAES)
								$('is t', this).attr( 's', '0' ); 
								$('is t', this).text(strCNAES);	
							//}	
							i++;                            
                        });
						i = 0;
						str = "";
						$('row c[r^="G"]', sheet).each( function () { // Publicación
							if(i > 0 &&  i < jsonResultTotal.length + 2 ){ 
								str = $('is t', this).text();
								$('is t', this).text(str.substring(8));
							}
							i++;
                        });






                        /*var lastCol = sheet.getElementsByTagName('col').length - 1;
                        var colRange = createCellPos( lastCol ) + '1';
                        //Has to be done this way to avoid creation of unwanted namespace atributes.
                        var afSerializer = new XMLSerializer();
                        var xmlString = afSerializer.serializeToString(sheet);
                        var parser = new DOMParser();
                        var xmlDoc = parser.parseFromString(xmlString,'text/xml');
                        var xlsxFilter = xmlDoc.createElementNS('http://schemas.openxmlformats.org/spreadsheetml/2006/main','autoFilter');
                        var filterAttr = xmlDoc.createAttribute('ref');
                        filterAttr.value = 'A1:' + colRange;
                        xlsxFilter.setAttributeNode(filterAttr);
                        sheet.getElementsByTagName('worksheet')[0].appendChild(xlsxFilter);*/
                    }
                },
				{ //PDF
					text: 'Generar PDF',
					extend: 'pdfHtml5',
					filename: 'legislaciones',
					orientation: 'landscape',//landscape o portrait
					pageSize: 'A4', //A3 , A5 , A6 , legal , letter
					exportOptions: {
						columns: ':visible',
						search: 'applied',
						order: 'applied'
					},
					customize: function (doc) {
						var now = new Date();
						var jsDate = now.getDate()+'-'+(now.getMonth()+1)+'-'+now.getFullYear();											
						var logo = "data:image/jpeg;base64,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"
						
                        doc.pageMargins = [20,60,20,100];
						doc.defaultStyle.fontSize = 11;						
						doc.styles.tableHeader.fontSize = 12;
						
						
						let table = doc.content[1].table.body;
						var item = null, itemsCNAES = null;
						var str = null, strCNAES = "";
						var fechaDatatable = null;
                        for (var i = 1; i < table.length; i++) {
                            fechaDatatable=new Date(jsonResultTotal[i-1].publicacion);
                        	item = jsonResultTotal[i-1];
                            table[i][1].text = item["nombre"] + ": \r\n" + item["descripcion"];
                            itemsCNAES = item.cnaes;
                            
                            strCNAES = "";
                            for(var x = 0; x < itemsCNAES.length; x++){
                            	str = itemsCNAES[x].split(", nombre=");
                            	if(strCNAES != "") strCNAES += " \r\n"; 
                            	strCNAES += str[1].replace("}","") + " (" + str[0].replace("{cnae=","").replace("cnae=","") + ")";
                            }
                            table[i][3].text= strCNAES;
                            table[i][4].text = fechaDatatable.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' });
                            table[i][5].text = table[i][5].text.substring(8);
                            
                        }
                        
                        doc['header']=(function() {
							return {
								columns: [
									{
										image: logo,
										width: 24
									},
									{
										alignment: 'left',
										italics: true,
										text: 'Legal+',
										fontSize: 18,
										margin: [10,0]
									},
									{
										alignment: 'right',
										fontSize: 14,
										text: 'Legislaciones'
									}
								],
								margin: 20
							}
						});	
						
					}
				} //end nuevo boton
        	],
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
		});
		
	  

		var valueCCAA = null;
		var $eventSelect = $(".js-select2-ccaa"); //.... desde ahora solo para ccaa y aytos
		
		$eventSelect.on("change", function (e) {
			if(e.target.id == "cmb_ccaa"){
				//no tiene valor o lo dejan en null
				if(e.target.value == null || e.target.value == ""){
					$("#cmb_ayuntamientos").empty();
					$("#cmb_ayuntamientos").prop("disabled", true);
                    $("#cmb_ayuntamientos").append(new Option("", "", true, true));
					valueCCAA = null;
				}else if( valueCCAA != e.target.value) {
					valueCCAA =  e.target.value;
					$("#cmb_ayuntamientos").empty();
					//ambito es ayto
					if($("#cmb_ambito").val() == "3"){
						var xhr = new XMLHttpRequest();
		                xhr.onreadystatechange = function(){
		                    if (this.readyState == 4 && this.status == 200){
		                    	jsonAytos = JSON.parse(this.responseText).sort(function(a, b){
		                            return a.startDate - b.startDate;
		                        });
	        					var newOption = new Option("", "", true, true);
	        					$("#cmb_ayuntamientos").append(newOption); //.trigger('change');

	        					for(var i = 0; i < jsonAytos.length; i++){
	        						newOption = new Option(jsonAytos[i].nombre, jsonAytos[i].ayuntamientoId, true, true);
		                    		$("#cmb_ayuntamientos").append(newOption); //.trigger('change');
	        					}

	        					$('#cmb_ayuntamientos').val('');
	        					$("#cmb_ayuntamientos").prop("disabled", false);		
		                    }
		                }

		                urlAytosAjax = urlAytos.replace("#ID_CCAA#",e.target.value).replace("#P_AUTH#",Liferay.authToken);	                
		                xhr.open("GET", urlAytosAjax, true);
		                xhr.send(null);
					}
				}

			}// end if ccaa
		});
		function getPaginaGuardada(){

            var ultimaPagina = parseInt(localStorage.getItem("pagina_actual_legislacion"));
            if (isNaN(ultimaPagina)){
                ultimaPagina=0;
            }
            localStorage.setItem("pagina_actual_legislacion",null);
            return ultimaPagina
        }

        $('#table_legislaciones tbody').on('click', '.viewDetail', function (e) {
            var tr = $(this).closest('tr');
            var row = tabla.row( tr );
            if ( row.child.isShown() ) {
                $('div.slider', row.child()).slideUp( function () {
                    row.child.hide();
                    tr.removeClass('shown');
                });
                $(event.target).attr('class','viewDetail icon-eye-open');
            }else{ //OPEN
                row.child( format(row.data()), 'no-padding' ).show();
                tr.addClass('shown');
                //Obtener requisitos
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function(){
                    if (this.readyState == 4 && this.status == 200){
                        jsonRequisitos = JSON.parse(this.responseText);
                        var idCapa = e.target.getAttribute("legislacionId");
                        var spn = document.getElementById("requisitos_" + idCapa);
                        spn.innerHTML = "<ul>";
                        for(x in jsonRequisitos.items){
                            spn.innerHTML += '<li class="mb-3">' +  jsonRequisitos.items[x].descripcion + '</li>';
                        }
                        if(spn.innerHTML == "<ul>") "<li>" + sinRequisitos + "</li>";
                        spn.innerHTML += "<ul>";
                    }
                }
                xhr.open("GET", urlAjaxRequisitos + "&legislacionId="+  e.target.getAttribute("legislacionId"), true);
                xhr.send(null);

                $('div.slider', row.child()).slideDown();
                $(event.target).attr('class','viewDetail icon-eye-close');
            }
        });


        tabla.on( 'draw', function () {
            if(localStorage.getItem("pagina_actual_legislacion")!="null"){
                var pagina=getPaginaGuardada()
                tabla.page(pagina).draw('page');
            }
        });

	});


var sinRequisitos  = '<liferay-ui:message key="admin.legislaciones.view.sinRequisitos"/>';




 	var inicio = $("#<portlet:namespace/>startDate");
	var fin = $("#<portlet:namespace/>endDate");
	var cond = 0; //cuantos se cumplen
	var numCond = 0; //  filtros posibles, cuantos estan seleccionados

	var arrValues = null;
	var arrDate = null;
	var dateInicio = null;
	var dateInicioCol = null;

	

var htmlFila = null;
function format ( d ) {

	var spansCNAES = "";
	var cnaes = d["cnaes"];
	for (var value of cnaes) {
	    var cnae = value.substring(value.indexOf("cnae=") + 5, value.lastIndexOf(",") );
	    var nombre = value.substring(value.indexOf("nombre=") + 7, value.lastIndexOf("}") );
        spansCNAES += '<div class="d-flex flex-column align-items-start"><span class="badge badge-info">(' + cnae + ") " + nombre + '</span></div>';
	}
	var empresas = d["empresas"];
	if (empresas != "") spansCNAES += '<span class="badge badge-pill badge-info"><liferay-ui:message key="admin.legislaciones.view.empresas"/></span>';

    var spansEtiquetas = "";
    var etiquetas = d["etiquetas"];
    for (var etiqueta of etiquetas) {
        spansEtiquetas += '<div class="d-flex flex-column align-items-start"><span class="badge badge-info">' + etiqueta.nombre  + '</span></div>';
    }
	  
	htmlFila = '<div class="slider">'+
    '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
    	'<tr>'+
			'<td><liferay-ui:message key="admin.legislaciones.view.legislacion"/></td>'+
			'<td>' + d["nombre"] + '</td>'+
		'</tr>';
		
	htmlFila += '<tr>'+
    		'<td><liferay-ui:message key="admin.legislaciones.view.descripcion"/></td>'+
    		'<td>' + d["descripcion"] + '</td>'+
		'</tr>'+
		
		'<tr>'+
			'<td><liferay-ui:message key="admin.legislaciones.view.cnae"/></td>'+
			'<td>' + spansCNAES + '</td>'+
		'</tr>'+
		'<tr>'+
            '<td><liferay-ui:message key="admin.legislaciones.view.etiquetas"/></td>'+
            '<td>' + spansEtiquetas + '</td>'+
        '</tr>'+
		
		
        '<tr>'+
            '<td><liferay-ui:message key="admin.legislaciones.view.requisitos"/></td>'+
            '<td><span id="requisitos_' + d["legislacionId"] + '"><span class="loading-animation"></span> </span></td>'+
        '</tr>'+
    '</table>'+
	'</div>';
	
	return htmlFila;
}
	$('#<portlet:namespace/>endDate, #<portlet:namespace/>startDate').keyup(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);		
	});

	function changeActivo(_activo){
		filterActivo = _activo;
		//si es activo todas o no mostramos la columna de derogación
		//e.preventDefault();
		if(_activo == "1" || _activo == "2"){
	        var column = tabla.column( 7 );
	        column.visible( true);
		}else{
			var column = tabla.column( 7 );
	        column.visible( false);
		}
	}

	function isValidDate(_date){
		var reg = /^(0[1-9]|[1-2]\d|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
		if(reg.test(_date)) return true;
		return false;

	}

	function buscarLegislaciones(){
		try{
			//urlAjax = urlAjaxBase + "&tipo=" + $("#cmb_tipo").val();
	         document.getElementById("spanErrorBusqueda").innerHTML = "";
	         if(($("#cmb_ambito").val() == "2" || $("#cmb_ambito").val() == "3") && $("#cmb_ccaa").val() == ""){ //ayto/comunidad sin comunidad
	        	 document.getElementById("spanErrorBusqueda").innerHTML = '<liferay-ui:message key="admin.legislaciones.view.errorFaltaCCAA"/>';        	 
	         }else if($("#cmb_ambito").val() == "3" && $("#cmb_ayuntamientos").val() == ""){ //ayto sin ayto 
	        	 document.getElementById("spanErrorBusqueda").innerHTML = '<liferay-ui:message key="admin.legislaciones.view.errorFaltaAyto"/>';        	 
	         }
	         
	         if( document.getElementById("spanErrorBusqueda").innerHTML != "") return; 

	        var arrDate = null, dateInicio = null;  //OJO EN LA URL HAY UN ; POR COMO ESTABA GUARDADO EN LA BBDD
	        var url = urlAjaxBase + "&activo=" + filterActivo
	        			+ "&tipo=" + $("#cmb_tipo").val()
	        			+ "&familia=" + $("#cmb_familia").val()
	        			+ "&ambito=" + $("#cmb_ambito").val()
	        			+ "&ccaa=" + $("#cmb_ccaa").val()
	        			+ "&ayuntamiento=" + $("#cmb_ayuntamientos").val()
	        			+ "&texto=" + $("#inpBuscarCajon").val()
	        			+ "&etiquetas=" + $("#cmd_etiquetas").val();

			if(inicio.val().trim() != "" && isValidDate(inicio.val())){
				arrDate = inicio.val().split("/");
				dateInicio = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
				url += "&fechaDesde=" + dateInicio.getTime();
			}
			if( fin.val().trim() != "" && isValidDate(fin.val())){			
				arrDate = fin.val().split("/");
				dateInicio = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
				dateInicio.setDate(dateInicio.getDate() + 1);
				url += "&fechaHasta=" + dateInicio.getTime();
			}

	        tabla.ajax.url( url );
	        tabla.ajax.reload();
		}catch(e){
			console.error("buscarLegislaciones(): " + e);	
		}
	}

	function cleanFilters(){
		for(var i = 0; i < arrCampos.length; i++){
			arrCampos[i].val('').trigger('change');
		}
		$(inicio).val('');
		$(fin).val('');
		$("#inpBuscarCajon").val("");
	}

	function changeAmbito(value){		
		$("#cmb_ccaa").val('').trigger('change');
		$("#cmb_ayuntamientos").val('').trigger('change');
		$("#cmb_ccaa").prop("disabled", true);
		$("#cmb_ayuntamientos").prop("disabled", true);
		
		if(value == "2" || value == "3" ) $("#cmb_ccaa").prop("disabled", false);		
	}	
	
	function showCNAESCell(_id){
		$("#cnae_max_" + _id).removeClass("hide");
		$("#cnae_max_title_" + _id).addClass("hide");
	}

	var advancedSearchTrigger = document.querySelectorAll('.prv-advanced-search__trigger');
	if(advancedSearchTrigger.length > 0){
	    for (const ast of advancedSearchTrigger) {
	        ast.addEventListener('click', function(){
	            ast.classList.toggle('active');

	        });
	    }
	}

    $(document).on('keypress',function(e) {
        if(e.which == 13) {
            buscarLegislaciones();
        }
    });


    //Guardar parametros de filtrado
    function saveParamsSearch(){
        var itemSearch = {};
        itemSearch.txtBuscar = document.getElementById("inpBuscarCajon").value.trim();
        itemSearch.inpSearchActive1 = document.getElementById("inpSearchActive1").checked;
        itemSearch.inpSearchActive2 = document.getElementById("inpSearchActive2").checked;
        itemSearch.inpSearchActive3 = document.getElementById("inpSearchActive3").checked;


        itemSearch.startDate = document.getElementById("<portlet:namespace/>startDate").value;
        itemSearch.endDate = document.getElementById("<portlet:namespace/>endDate").value;

        itemSearch.cmd_etiquetas = document.getElementById("cmd_etiquetas").value;

        itemSearch.cmb_tipo = document.getElementById("cmb_tipo").value;
        itemSearch.cmb_familia = document.getElementById("cmb_familia").value;
        itemSearch.cmb_ambito = document.getElementById("cmb_ambito").value;
        itemSearch.cmb_ccaa = document.getElementById("cmb_ccaa").value;
        itemSearch.cmb_ayuntamientos = document.getElementById("cmb_ayuntamientos").value;


        sessionStorage.setItem("searchParams_legislaciones", JSON.stringify(itemSearch));
        var info = $('#table_legislaciones').DataTable().page.info();
        var page = info.page;
        localStorage.setItem("pagina_actual_legislacion", page);
        //event.preventDefault();


    }


    //Recuperar los filtros
    $(document).ready(function(){
        var advancedSearch = false;
        if(sessionStorage.getItem("searchParams_legislaciones") != null && sessionStorage.getItem("searchParams_legislaciones") != "null"){
            itemSearch = JSON.parse(sessionStorage.getItem("searchParams_legislaciones"));
            if(itemSearch.txtBuscar && itemSearch.txtBuscar != "" ) document.getElementById("inpBuscarCajon").value = itemSearch.txtBuscar;
            if(itemSearch.inpSearchActive1) document.getElementById("inpSearchActive1").checked = true;
            if(itemSearch.inpSearchActive2) document.getElementById("inpSearchActive2").checked = true;
            if(itemSearch.inpSearchActive3) document.getElementById("inpSearchActive3").checked = true;

            if(itemSearch.startDate) document.getElementById("<portlet:namespace/>startDate").value = itemSearch.startDate;
            if(itemSearch.endDate) document.getElementById("<portlet:namespace/>endDate").value = itemSearch.endDate;
            var event = new Event('change');

            if(itemSearch.cmd_etiquetas){
                document.getElementById("cmd_etiquetas").value = itemSearch.cmd_etiquetas;
                document.getElementById("cmd_etiquetas").dispatchEvent(event);
                advancedSearch = true;
            }

            if(itemSearch.cmb_tipo){
                document.getElementById("cmb_tipo").value = itemSearch.cmb_tipo;
                document.getElementById("cmb_tipo").dispatchEvent(event);
                advancedSearch = true;
            }
            if(itemSearch.cmb_familia){
                document.getElementById("cmb_familia").value = itemSearch.cmb_familia;
                document.getElementById("cmb_familia").dispatchEvent(event);
                advancedSearch = true;
            }
            if(itemSearch.cmb_ambito){
                document.getElementById("cmb_ambito").value = itemSearch.cmb_ambito;
                document.getElementById("cmb_ambito").dispatchEvent(event);
                advancedSearch = true;
            }
            if(itemSearch.cmb_ccaa){
                document.getElementById("cmb_ccaa").value = itemSearch.cmb_ccaa;
                document.getElementById("cmb_ccaa").dispatchEvent(event);
                advancedSearch = true;
            }
            if(itemSearch.cmb_ayuntamientos){
                document.getElementById("cmb_ayuntamientos").value = itemSearch.cmb_ayuntamientos;
                document.getElementById("cmb_ayuntamientos").dispatchEvent(event);
                advancedSearch = true;
            }

            if(advancedSearch){
                document.getElementsByClassName("prv-advanced-search__trigger")[0].classList.remove("collapsed");
                document.getElementsByClassName("prv-advanced-search__trigger")[0].classList.add("active");
                /*event = new Event('click');
                document.getElementsByClassName("prv-advanced-search__trigger")[0].dispatchEvent(event);*/

                document.getElementById("advanced-search").classList.remove("collapsed");
                document.getElementById("advanced-search").classList.add("show");

            }


            sessionStorage.setItem("searchParams_legislaciones", null);

            //sessionStorage.clear();
            buscarLegislaciones();

        }
    })



</script>







