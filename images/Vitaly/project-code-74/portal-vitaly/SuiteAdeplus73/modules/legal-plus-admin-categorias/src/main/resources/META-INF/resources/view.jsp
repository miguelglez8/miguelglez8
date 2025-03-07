<%@ page import="com.legalplus.liferay.portlet.admin.categorias.web.constants.AdminCategoriasPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.asset.kernel.model.AssetCategory" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.List" %>
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
<script>
	Liferay.Loader.define.amd = Liferay.Loader.define._amd;
</script>

<%
	List<AssetCategory> categories = (List<AssetCategory>) request.getAttribute(AdminCategoriasPortletKeys.CATEGORIES_LIST);

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
%>

<liferay-ui:success key="categoria-save-success" message="categories.save.success" />
<liferay-ui:success key="categoria-delete-success" message="categories.delete.success" />

<portlet:renderURL var="addCategoryURL">
	<portlet:param name="mvcPath" value="/category.jsp"></portlet:param>
</portlet:renderURL>
<main class="content">
	<div class="formulario">
		<div class="title-group">
			<div class="row">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="categories.view.title"/></h2>
                </div>
                <div class="col-md-6 col-12 text-right">
                    <a class="prv-iconLinks prv-iconLinks__plus" href="<%= addCategoryURL.toString() %>">
                        <liferay-ui:message key="category.view.new"/>
                    </a>
                </div>
            </div>
		</div>
		
		<div class="prv-search prv-form">
		<div class="row align-items-start mb-4">
			<div class="col-lg-3 col-md-6 col-12">
				<label for="inpBuscarCajon"> <liferay-ui:message
						key="categories.view.textoBuscar" />
				</label> <input type="text" name="" id="inpBuscarCajon">
			</div>
		</div>
		<div class="row align-items-end">
			<div class="col-md-12 col-12 text-right">
				<div class="btnClear">
					<button class="btn btn btn-outline-primary btn-sm btn-primary"
						type="button" onclick="cleanFilters()">
						<%=LanguageUtil.get(bundle, "categories.view.limpiarFiltros")%>
					</button>
					<button class="btn btn btn-sm btn-primary" type="button" onclick="tabla.draw();">
						<%=LanguageUtil.get(bundle, "categories.view.btnBuscar")%>
					</button>
					<span id="spanErrorBusqueda" role="alert"
						class="has-error help-block"></span>
				</div>
			</div>
        </div>
    </div>

		
		<div class="">

			<div class="row">
				<div class="col">
				    <div id="spnCargando" class="loading-animation"></div>
					<table id="table_categories" class="display" style="width:100%; display:none">
						<thead>
                            <tr>
                                <th><liferay-ui:message key="categories.view.name"/></th>
                                <th><liferay-ui:message key="categories.view.description"/></th>
                                <th class="no-sort"></th>
                            </tr>
						</thead>
						<tbody>
						<c:forEach items="<%= categories %>" var="category">
						    <%
                                AssetCategory cat = (AssetCategory) pageContext.getAttribute("category");
                            %>
							<tr>
								<td><%= cat.getTitle(locale) %></td>
								<td><%= cat.getDescription(locale) %></td>
                                <td>
                                    <liferay-portlet:renderURL varImpl="editURL">
                                        <portlet:param name="<%=AdminCategoriasPortletKeys.CATEGORY_ID%>" value="${category.categoryId}" />
                                        <portlet:param name="mvcPath" value="/category.jsp" />
                                    </liferay-portlet:renderURL>
                                    <div class="d-flex justify-content-end">
                                    	<a class="ico-acciones-tabla" href="<%=editURL.toString()%>" onclick="saveParamsSearch()">
											<span  class="icon-pencil"></span>
                                    	</a>
                                     	<a data-id="${category.categoryId}" class="ico-acciones-tabla delete-category" href="#">
											<span  class="icon-trash"></span>
                                    	</a>
                                    </div>
                                </td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

        <%@include file="delete.jspf" %>

	</div>
</main>
<portlet:resourceURL id="legislacionContainsEtiqueta" var="legislacionContainsEtiquetaURL"/>
<script>
	var tabla = null;
	$(document).ready( function () {

		tabla = $('#table_categories').DataTable( {
			dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
			responsive: true,
			processing: true,
			pageLength: 25,
			buttons: [
				'csv', 'excel', 'print', 'pdf'
			],
			"columnDefs": [
                {
                      "targets"  : [2],
                      "orderable": false
                }
			],
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
                $("#table_categories").show();
            },
			"language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "user.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "user.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "user.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "user.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "user.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "user.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "user.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "user.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "user.view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "user.view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "user.view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "user.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "user.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "user.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "user.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "user.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "user.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "user.view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "user.view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "user.view.datatable.colvis")%>"
				}
			}
		});

		tabla.draw();

	});

	$.fn.dataTable.ext.search.push(function( settings, data, dataIndex ) {
	    var text = $("#inpBuscarCajon").val();

	    var cond = 0;
        var numCond = 0;

        if (text != "") {
            numCond++;
            if (data[0].toLowerCase().match(text.toLowerCase()) || data[1].toLowerCase().match(text.toLowerCase()) ||
                data[0].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text.toLowerCase()) ||
                data[1].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text.toLowerCase())  ) cond++;
        }

        if(numCond == cond) return true;
        return false;

	});

	$(document).on('click', '.delete-category', function(e){
	    e.preventDefault();
	    var id = $(this).data('id');
	    $.ajax({
            type: "POST",
            url: '${legislacionContainsEtiquetaURL}',
            data: {
                '<portlet:namespace/>etiquetaId':id,
            },
            success: function (result) {
                $('#deleteCategory').removeClass('hide');
                $('#deleteCategory').addClass('show');
                $('#<portlet:namespace /><%=AdminCategoriasPortletKeys.DELETE_ID%>').val(id);
            },
            error: function (jqXhr, textStatus, errorMessage) {
                $('#noDeleteCategory').removeClass('hide');
                $('#noDeleteCategory').addClass('show');
            }
        })

	});

	$(document).on('click', '.hide-modal', function(e) {
	    e.preventDefault();
	    $('#deleteCategory').addClass('hide');
	    $('#noDeleteCategory').addClass('hide');
	});

	function cleanFilters(){
	    $("#inpBuscarCajon").val("").trigger("change");
        tabla.search("").draw() ;
	}

    $(document).on('keypress',function(e) {
        if(e.which == 13) {
            tabla.draw();
        }
    });

    function saveParamsSearch(){
        var itemSearch = {};
        itemSearch.txtBuscar = document.getElementById("inpBuscarCajon").value.trim();


        sessionStorage.setItem("searchParams_admincategories", JSON.stringify(itemSearch));
        //event.preventDefault();


    }

//Recuperar los filtros
    $(document).ready(function(){
        var advancedSearch = false;
        if(sessionStorage.getItem("searchParams_admincategories") != null && sessionStorage.getItem("searchParams_admincategories") != "null"){
            itemSearch = JSON.parse(sessionStorage.getItem("searchParams_admincategories"));
            if(itemSearch.txtBuscar && itemSearch.txtBuscar != "" ) document.getElementById("inpBuscarCajon").value = itemSearch.txtBuscar;

            sessionStorage.setItem("searchParams_admincategories", null)

            //sessionStorage.clear();
            tabla.draw()

        }
    });

</script>
