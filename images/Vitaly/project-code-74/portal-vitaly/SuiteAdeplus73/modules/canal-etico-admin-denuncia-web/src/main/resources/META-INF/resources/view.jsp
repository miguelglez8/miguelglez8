<%@ page import="com.canal.etico.liferay.portlet.admin.denuncia.web.constants.AdminDenunciaPortletKeys" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Categoria" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Denuncia" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.DenunciaAccionLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.estado.CanalEticoEstadoUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>

<%@ include file="init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">

<script>
	Liferay.Loader.define._amd = Liferay.Loader.define.amd;
	Liferay.Loader.define.amd = false;
</script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="https://nightly.datatables.net/responsive/js/dataTables.responsive.min.js"></script>
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

<%
	List<Denuncia> denunciaList = (List<Denuncia>) request.getAttribute(AdminDenunciaPortletKeys.DENUNCIAS);
	List<Categoria> categoriasList = (List<Categoria>) request.getAttribute(AdminDenunciaPortletKeys.CATEGORIAS);

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
	SimpleDateFormat dateFormatSearch = new SimpleDateFormat("yyyyMMdd");
	dateFormatSearch.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

	boolean hasPermission = (boolean) request.getAttribute(AdminDenunciaPortletKeys.HAS_PERMISSION_IN_COMP);
	String startDateStr = (String) request.getAttribute(AdminDenunciaPortletKeys.START_DATE);
	String endDateStr = (String) request.getAttribute(AdminDenunciaPortletKeys.END_DATE);

	Date start = dateFormat.parse(startDateStr);
	Date end = dateFormat.parse(endDateStr);

	Calendar calStart = Calendar.getInstance();
	calStart.add(Calendar.MONTH, -3);

	Calendar calEnd = Calendar.getInstance();

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

	String consultaStr 	= LanguageUtil.get(bundle, "denuncia.view.denuncia.tipo.consulta");
	String denunciaStr  = LanguageUtil.get(bundle, "denuncia.view.denuncia.tipo.denuncia");
	String registrado  	= LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.registrado");
	String enProceso  	= LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.en.proceso");
	String finalizado  	= LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.finalizado");
	String sinFinalizar = LanguageUtil.get(bundle, "denuncia.view.denuncia.estado.sin.finalizar");

%>

<main class="content">

	<c:if test="<%=!hasPermission%>">
		<div class="formulario">
			<div class="title-group">
				<h2><liferay-ui:message key="denuncia.view.title"/></h2>
				<p><liferay-ui:message key="denuncia.view.no.company"/></p>
			</div>
		</div>
	</c:if>

	<c:if test="<%=hasPermission%>">
		<div class="formulario">
			<div class="title-group">
				<h2><liferay-ui:message key="denuncia.view.title"/></h2>
				<p><liferay-ui:message key="denuncia.view.help"/></p>
			</div>



			<div class="row">
				<div class="col-sm-4 p-0">
					<fieldset class="input-group-inline">
						<legend><liferay-ui:message key="denuncia.view.tipo"/></legend>
						<aui:select name="<%=AdminDenunciaPortletKeys.TIPO%>" label="" onchange="tabla.draw()">
							<aui:option value="99" ><liferay-ui:message key="denuncia.view.denuncia.todos"/></aui:option>
							<aui:option value="0" ><liferay-ui:message key="denuncia.view.denuncia.tipo.consulta"/></aui:option>
							<aui:option value="1" ><liferay-ui:message key="denuncia.view.denuncia.tipo.denuncia"/></aui:option>
						</aui:select>
					</fieldset>
				</div>
				<div class="col-sm-4">
					<fieldset class="input-group-inline">
						<legend><liferay-ui:message key="denuncia.view.estado"/></legend>
						<aui:select name="<%=AdminDenunciaPortletKeys.ESTADO%>" label="" onchange="tabla.draw()">
							<aui:option value="99" ><liferay-ui:message key="denuncia.view.denuncia.todos"/></aui:option>
							<aui:option value="0" ><liferay-ui:message key="denuncia.view.denuncia.estado.registrado"/></aui:option>
							<aui:option value="1" ><liferay-ui:message key="denuncia.view.denuncia.estado.en.proceso"/></aui:option>
							<aui:option value="2" ><liferay-ui:message key="denuncia.view.denuncia.estado.finalizado"/></aui:option>
							<aui:option value="3" ><liferay-ui:message key="denuncia.view.denuncia.estado.sin.finalizar"/></aui:option>
						</aui:select>
					</fieldset>
				</div>
				<div class="col-sm-4">
					<fieldset class="input-group-inline">
						<legend><liferay-ui:message key="denuncia.view.categoria"/></legend>
						<aui:select name="<%=AdminDenunciaPortletKeys.CATEGORIA%>" label="" onchange="tabla.draw()">
							<aui:option value="99" ><liferay-ui:message key="denuncia.view.denuncia.todas"/></aui:option>
							<c:forEach var="categoria" items="<%=categoriasList%>">
								<aui:option value="${categoria.categoriaId}" >
									${categoria.nombre}
								</aui:option>
							</c:forEach>
						</aui:select>
					</fieldset>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-4 p-0 mb-3">
					<fieldset class="input-group-inline">
						<legend><liferay-ui:message key="denuncia.view.desde"/></legend>
						<liferay-ui:input-date name="startDate"
											   yearValue="<%=calStart.get(Calendar.YEAR)%>"
											   monthValue="<%=calStart.get(Calendar.MONTH)%>"
											   dayValue="<%=calStart.get(Calendar.DAY_OF_MONTH)%>" />

						<div class="cursor_pointer" onclick="tabla.draw();">
							<liferay-ui:icon iconCssClass="icon-filter" label="<%=false %>" message="search" />
						</div>

					</fieldset>
				</div>
				<div class="col-sm-4">
					<fieldset class="input-group-inline">
						<legend><liferay-ui:message key="denuncia.view.hasta"/></legend>
						<liferay-ui:input-date name="endDate"
											   yearValue="<%=calEnd.get(Calendar.YEAR)%>"
											   monthValue="<%=calEnd.get(Calendar.MONTH)%>"
											   dayValue="<%=calEnd.get(Calendar.DAY_OF_MONTH)%>"/>

						<div class="cursor_pointer" onclick=" tabla.draw();">
							<liferay-ui:icon iconCssClass="icon-filter" label="<%=false %>" message="search" />
						</div>

					</fieldset>
				</div>
			</div>


			<div class="row">
				<div class="col">
					<table id="table_denuncias" class="display" style="width:100%">
						<thead>
						<tr>
							<th><liferay-ui:message key="denuncia.view.createDate"/></th>
							<th><liferay-ui:message key="denuncia.view.codigo"/></th>
							<th><liferay-ui:message key="denuncia.view.categoria"/></th>
							<th><liferay-ui:message key="denuncia.view.tipo"/></th>
							<th><liferay-ui:message key="denuncia.view.asunto"/></th>
							<th><liferay-ui:message key="denuncia.view.estado"/></th>
							<th><liferay-ui:message key="denuncia.view.acciones"/></th>
							<th><liferay-ui:message key="denuncia.view.deleteDate"/></th>
							<th><liferay-ui:message key="denuncia.view.accion"/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="<%=denunciaList%>" var="denuncia">
							<%
								Denuncia denuncia = (Denuncia) pageContext.getAttribute("denuncia");
								String categoriaStr = String.valueOf(denuncia.getDenunciaId());
								Categoria categoria = CategoriaLocalServiceUtil.fetchCategoria(denuncia.getCategoria());
								if(Validator.isNotNull(categoria)){
									categoriaStr = categoria.getNombre();
								}
								String acciones = DenunciaAccionLocalServiceUtil.getAccionesHtmlFromDenuncia(denuncia.getDenunciaId());
								String tipo = (denuncia.getTipo() == AdminDenunciaPortletKeys.TIPO_CONSULTA)?consultaStr:denunciaStr;
								String estado = CanalEticoEstadoUtil.getNombreEstadoPorId(denuncia.getEstado(), themeDisplay.getLocale());
								boolean showData = CanalEticoEstadoUtil.esCampoVisibleDenuncia(denuncia);

							%>
							<liferay-portlet:renderURL varImpl="editURL">
								<portlet:param name="denunciaId" value="<%=String.valueOf(denuncia.getDenunciaId())%>" />
								<portlet:param name="mvcPath" value="/denuncia.jsp" />
							</liferay-portlet:renderURL>
							<tr>
								<td data-sort="<%=dateFormatSearch.format(denuncia.getCreateDate())%>">
									<%=dateFormat.format(denuncia.getCreateDate())%>
								</td>
								<td>${denuncia.codigo}</td>
								<td>
									<c:if test="<%=showData%>" >
										<%=categoriaStr%>
									</c:if>
								</td>
								<td><%=tipo%></td>
								<td>${denuncia.asunto}</td>
								<td><%=estado%></td>
								<td>
									<c:if test="<%=showData%>" >
										<%=acciones%>
									</c:if>
								</td>
								<td data-sort="<%=Validator.isNotNull(denuncia.getEndDate())?dateFormatSearch.format(denuncia.getEndDate()):""%>">
									<%=Validator.isNotNull(denuncia.getEndDate())?dateFormat.format(denuncia.getEndDate()):""%>
								</td>
								<td>
									<a class="ico-acciones-tabla" href="<%=editURL.toString()%>">
										<img src="<%=themeDisplay.getPathThemeImages()%>/ico_edit.png" alt="<liferay-ui:message key="edit" />" />
									</a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</c:if>
</main>
<script>

var tabla = null;
	$(document).ready( function () {
		tabla = $('#table_denuncias').DataTable( {            
			dom: 'Bfrtip',
			responsive: true,
			pageLength: 25,
			lengthMenu: [[10, 25, 50, 100], [10, 25, 50, 100]],
			buttons: [
				'csv', 'excel', 'print', 'pdf'
			],
			"language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "denuncia.view.datatable.colvis")%>"
				}
			}
		} );
	} 
);
    

    var filterActivo = "Si"; // para pasarle el click en el radio de activo (idiomas�?)
    
    $.fn.dataTable.ext.search.push(
            function( settings, data, dataIndex ) {

                var tipo = $("#_com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaPortlet_tipo option:selected").text().trim();
                var tipoVal = $("#_com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaPortlet_tipo").val();

                var estado = $("#_com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaPortlet_estado option:selected" ).text().trim();
                var estadoVal = $("#_com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaPortlet_estado").val();

                var cat = $("#_com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaPortlet_categoria option:selected" ).text().trim();
                var catVal = $("#_com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaPortlet_categoria" ).val();

                var inicio = $("#_com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaPortlet_startDate").val();
                var fin = $("#_com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaPortlet_endDate").val();

                var cond = 0; //cuantos se cumplen
                var numCond = 0; //  filtros posibles, cuantos estan seleccionados

                if(tipoVal != 99){
                    numCond++; // siempre tiene un valor seleccionado                
                    if(data[3].trim() == tipo) cond++;
                }
                
                if(estadoVal != 99){
                    numCond++; // siempre tiene un valor seleccionado                
                    if(data[5].trim() == estado) cond++;
                }
                
                if(catVal != 99){
                    numCond++; // siempre tiene un valor seleccionado                
                    if(data[2].trim() == cat) cond++;
                }

                var arrDate = null;
                var dateInicio = null;
                var dateInicioCol = null;

                if(inicio.trim() != "" && isValidDate(inicio)){                    
                    numCond++;                 
                    arrDate = inicio.split("/");
                    dateInicio = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");                    
                    arrDate = data[0].split("/");
                    dateInicioCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");       
                    if(dateInicioCol.getTime() >= dateInicio.getTime() ) cond++;                                 
                }

                if(fin.trim() != "" && isValidDate(fin)){
                    numCond++;                 
                    arrDate = fin.split("/");
                    dateInicio = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");                    
                    arrDate = data[0].split("/");
                    dateInicioCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");       
                    if(dateInicioCol.getTime() <= dateInicio.getTime() ) cond++;                                 
                }
                

                //Se cumplen todas:
                if(numCond == cond) return true;
                return false;
        }
    );
	
    $('#<portlet:namespace/>_starDate').change(function(event){
    	var keycode = (event.keyCode ? event.keyCode : event.which);
    	if(keycode == '13'){
    		console.log('You pressed a "enter" key in textbox');
    	}
    });

    function changeActivo(_activo){
        filterActivo = _activo;
        tabla.draw();
    }

    function inputKey(e){
        if(e.keyCode == 13) tabla.draw();
    }

    function isValidDate(_date){
        var reg = /^(0[1-9]|[1-2]\d|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
        if(reg.test(_date)) return true;
        return false;

    }
</script>