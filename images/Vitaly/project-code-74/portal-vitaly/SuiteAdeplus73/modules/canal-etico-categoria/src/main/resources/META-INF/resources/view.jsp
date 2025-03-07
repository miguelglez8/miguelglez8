<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Categoria" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.categoria.web.constants.CategoriasPortletKeys" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.role.CanalEticoRoleUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ResourceBundle" %>
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
	List<Categoria> listCategorias = (List<Categoria>) request.getAttribute(CategoriasPortletKeys.CATEGORIAS_LIST);

	long compId = (long)request.getAttribute(CategoriasPortletKeys.COMPANY_ID);

	boolean isCompanyAdmin = CanalEticoRoleUtil.isCompanyAdminRole(themeDisplay.getCompanyId(), themeDisplay.getUser());

	boolean isAdmin = permissionChecker.isOmniadmin();

	boolean hasPermission = CanalEticoUserUtil.isUserInDefaultCompPermission(themeDisplay.getUserId());
	boolean omniAdminRole = CanalEticoRoleUtil.isOmniAdminRole(themeDisplay.getCompanyId(), themeDisplay.getUser());


	//System.out.println("Categorias: " + user.getFullName() + ", compId: " + compId +", isOmniadmin " + permissionChecker.isOmniadmin());

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
	String yes = LanguageUtil.get(bundle, "categoria.view.yes");
	String no  = LanguageUtil.get(bundle, "categoria.view.no");
	String all = LanguageUtil.get(bundle, "categoria.view.all");
%>

<main class="content">
	<c:if test="<%=!hasPermission&& !omniAdminRole%>">
		<div class="formulario">
			<div class="title-group">
				<h2><liferay-ui:message key="categoria.view.title"/></h2>
				<p><liferay-ui:message key="categoria.view.no.company.permission"/></p>
			</div>
		</div>
	</c:if>

	<c:if test="<%=hasPermission || omniAdminRole%>">
		<div class="formulario">
			<div class="title-group">
				<h2><liferay-ui:message key="categoria.view.title"/></h2>
				<portlet:renderURL var="addUserURL">
					<portlet:param name="mvcPath" value="/categoria.jsp"></portlet:param>
				</portlet:renderURL>
				<aui:button-row cssClass="button-holder">
					<aui:button onClick="<%= addUserURL.toString() %>" value="categoria.view.new" cssClass="btn btn-outline-primary btn-sm" primary="true"></aui:button>
				</aui:button-row>
			</div>

			<fieldset class="row">
				<fieldset class="input-group-inline mt-3 mb-2">
					<aui:select name="<%=CategoriasPortletKeys.AFECTA%>" label="categoria.edit.afecta" onchange="tabla.draw()">
						<aui:option value="0"><liferay-ui:message key="categoria.edit.denuncia"/></aui:option>
						<aui:option value="1"><liferay-ui:message key="categoria.edit.consulta"/></aui:option>
						<aui:option value="2"><liferay-ui:message key="categoria.edit.denuncia.consulta"/></aui:option>
					</aui:select>
				</fieldset>
			</div>

			<div class="row">
				<fieldset class="input-group-inline mt-3 mb-2">
					<legend><liferay-ui:message key="categoria.view.active"/></legend>
					<div class="custom-control custom-radio mr-4">
						<input class="form-check-input" name="inpSearchActive" id="inpSearchActive1"
						value="<%=yes%>" type="radio" class="inpSearchActive" checked onchange="changeActivo(this.value)" />
						<label class="form-check-label" for="inpSearchActive1"><%=yes%></label>
					</div>
					<div class="custom-control custom-radio mr-4">
						<input class="form-check-input" name="inpSearchActive" id="inpSearchActive2"
						value="<%=no%>" type="radio" class="inpSearchActive" onchange="changeActivo(this.value)" />
						<label class="form-check-label" for="inpSearchActive2"><%=no%></label>
					</div>
					<div class="custom-control custom-radio mr-4">
						<input class="form-check-input" name="inpSearchActive" id="inpSearchActive3"
						 value="<%=all%>" type="radio" class="inpSearchActive" onchange="changeActivo(this.value)" />
						<label class="form-check-label" for="inpSearchActive3"><%=all%></label>
					</div>
				</fieldset>
			</div>

			<div class="row">
				<div class="col">
					<table id="table" class="display" style="width:100%">
						<thead>
						<tr>
							<th><liferay-ui:message key="categoria.view.name"/></th>
							<th><liferay-ui:message key="categoria.view.active"/></th>
							<th><liferay-ui:message key="categoria.view.afecta"/></th>
							<th><liferay-ui:message key="categoria.view.codigo"/></th>
							<c:if test="<%=isAdmin%>"><th><liferay-ui:message key="categoria.view.empresa"/></th></c:if>
							<th><liferay-ui:message key="categoria.view.action"/></th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="<%=listCategorias%>" var="categoria">
							<%
								Categoria categoria = (Categoria) pageContext.getAttribute("categoria");
								String afectaA = categoria.getTipo();
								switch (categoria.getTipo()){
									case "0": afectaA = LanguageUtil.get(bundle, "categoria.edit.denuncia");break;
									case "1": afectaA = LanguageUtil.get(bundle, "categoria.edit.consulta");break;
									case "2": afectaA = LanguageUtil.get(bundle, "categoria.edit.denuncia.consulta");break;
								}

								String compName = LanguageUtil.get(bundle, "categoria.view.global");
								if(isAdmin && categoria.getCompId() > 0){
									Comp comp = CompLocalServiceUtil.fetchComp(categoria.getCompId());
									if(Validator.isNotNull(comp)){
										compName=comp.getName();
									}

								}

								System.out.println("categoriaId: " + categoria.getCategoriaId()+ ", compId: " + categoria.getCompId() + ", nombre: "+ categoria.getNombre() + ", tipo: " + categoria.getTipo() + ", activo: " + categoria.getActivo());
							%>
							<liferay-portlet:renderURL varImpl="editURL">
								<portlet:param name="categoriaEditId" value="<%=String.valueOf(categoria.getCategoriaId())%>" />
								<portlet:param name="mvcPath" value="/categoria.jsp" />
							</liferay-portlet:renderURL>
							<tr>
								<td>${categoria.nombre}</td>
								<td><%=categoria.getActivo()?yes:no%></td>
								<td><%=afectaA%></td>
								<td>${categoria.codigo}</td>
								<c:if test="<%=isAdmin%>"><td><%=compName%></td></c:if>
								<td>
									<c:if test="<%=permissionChecker.isOmniadmin() || categoria.getCompId() == compId%>">
										<a class="ico-acciones-tabla" href="<%=editURL.toString()%>">
											<img src="<%=themeDisplay.getPathThemeImages()%>/ico_edit.png" alt="<liferay-ui:message key="edit" />" />
										</a>
									</c:if>
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
		tabla = $('#table').DataTable( {
			dom: 'Bfrtip',
			responsive: true,
			pageLength: 20,
			buttons: [
				'csv', 'excel', 'print', 'pdf'
			],
			"language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sEmptyTable")%>",
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
		} );
	} );


    var filterActivo = "<%=yes%>"; // para pasarle el click en el radio de activo (idiomas�?)
    
    $.fn.dataTable.ext.search.push(
            function( settings, data, dataIndex ) {
                
                var afecta = $( "#_com_canal_etico_liferay_portlet_categoria_web_CategoriasPortlet_afecta option:selected" ).text().trim();
                
                var cond = 0; //cuantos se cumplen
                var numCond = 0; // tiene 3 filtros posibles, cuantos estan seleccionados

                numCond++; // siempre tiene un valor seleccionado                
                if(data[2].trim() == afecta) cond++;
                
                numCond++; //siempre esta seleccionado un SI / NO / TODAS

                if(filterActivo == "<%=all%>"){
                    cond++;
                }else if(filterActivo == data[1].trim()){
                    cond++;
                }

                //Se cumplen todas:
                if(numCond == cond) return true;
                return false;
        }
    );


    function changeActivo(_activo){
        filterActivo = _activo;
        tabla.draw();
    }
</script>