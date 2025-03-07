<%@ page import="com.legalplus.liferay.portlet.admin.consultores.web.constants.AdminConsultoresPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.List" %>
<%@ include file="init.jsp" %>

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
	List<User> userList = (List<User>) request.getAttribute(AdminConsultoresPortletKeys.USER_LIST);

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
%>

<liferay-ui:success key="consultor-save-success" message="user.edit.success" />
<liferay-ui:success key="consultor-delete-success" message="user.delete.success" />

<portlet:renderURL var="addUserURL">
    <portlet:param name="mvcRenderCommandName" value="goAltaConsultor" />
</portlet:renderURL>

<main class="content">
	<div class="formulario">

		<div class="title-group">
			<div class="row">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="user.view.title"/></h2>
                </div>
                <div class="col-md-6 col-12 text-right">
                    <a class="prv-iconLinks prv-iconLinks__plus" href="<%= addUserURL.toString() %>">
                        <liferay-ui:message key="user.view.new"/>
                    </a>
                </div>
            </div>
		</div>

		<div class="prv-search prv-form">
            <div class="row align-items-start mb-4">
                <div class="col-lg-3 col-md-6 col-12">
                    <label for="inpBuscarCajon"> <liferay-ui:message key="consultores.view.textoBuscar" /></label>
                    <input type="text" name="" id="inpBuscarCajon">
                </div>
            </div>
            <div class="row align-items-end">
                <div class="col-md-12 col-12 text-right">
                    <div class="btnClear">
                        <button class="btn btn btn-outline-primary btn-sm btn-primary" type="button" onclick="cleanFilters()">
                            <%=LanguageUtil.get(bundle, "consultores.view.limpiarFiltros")%>
                        </button>
                        <button class="btn btn btn-sm btn-primary" type="button" onclick="tabla.draw();">
                            <%=LanguageUtil.get(bundle, "consultores.view.btnBuscar")%>
                        </button>
                        <span id="spanErrorBusqueda" role="alert" class="has-error help-block"></span>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-content">
            <div class="row">
                <div class="col">
                    <div id="spnCargando" class="loading-animation"></div>
                    <table id="table_users" class="display" style="width:100%; display:none">
                        <thead>
                            <tr>
                                <th><liferay-ui:message key="user.view.name"/></th>
                                <th><liferay-ui:message key="user.view.email"/></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="<%= userList %>" var="user">
                                <tr>
                                    <td>${user.fullName}</td>
                                    <td>${user.emailAddress}</td>
                                    <td>
                                        <div class="d-flex justify-content-end">
                                            <a data-id="${user.userId}" class="ico-acciones-tabla delete-consultant" href="#">
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

<script>
	var tabla = null;
	$(document).ready( function () {

		tabla = $('#table_users').DataTable( {
			dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
			responsive: true,
			pageLength: 25,
            "columnDefs": [
                    {
                          "targets"  : [2],
                          "orderable": false
                    }
            ],
			buttons: [
				'csv', 'excel', 'print', 'pdf'
			],
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
                $("#table_users").show();
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
		} );

		tabla.draw();

	} );

    $.fn.dataTable.ext.search.push(function( settings, data, dataIndex ) {
        var text = $("#inpBuscarCajon").val();

        var cond = 0;
        var numCond = 0;

        if (text != "") {
            numCond++;
            if (data[0].toLowerCase().match(text) || data[1].toLowerCase().match(text) ||
                data[0].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text) ||
                data[1].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)  ) cond++;
        }

        if(numCond == cond) return true;
        return false;

    });

	$(document).on('click', '.delete-consultant', function(e){
        e.preventDefault();
        var id = $(this).data('id');
        $('#deleteConsultant').removeClass('hide');
        $('#deleteConsultant').addClass('show');
        $('#<portlet:namespace /><%=AdminConsultoresPortletKeys.CONSULTOR_ID%>').val(id);
    });

    $(document).on('click', '.hide-modal', function(e) {
        e.preventDefault();
        $('#deleteConsultant').addClass('hide');
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

</script>
