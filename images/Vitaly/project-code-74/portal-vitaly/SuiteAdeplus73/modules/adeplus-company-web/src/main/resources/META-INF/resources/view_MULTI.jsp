<%@ page import="com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionCompanyUtil" %>
<%@ page import="com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Application" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Comp" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
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
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dateFormatSearch = new SimpleDateFormat("yyyyMMdd");

	List<Comp> companyList = (List<Comp>) request.getAttribute(AdeplusCompaniesPortletKeys.COMPANY_LIST);

	List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

	String yes = LanguageUtil.get(bundle, "user.view.yes");
	String no  = LanguageUtil.get(bundle, "user.view.no");
	String all = LanguageUtil.get(bundle, "user.view.all");
    System.out.println(yes);
	Calendar calendar = Calendar.getInstance();

	calendar.set(Calendar.HOUR_OF_DAY, 0);
	calendar.set(Calendar.MINUTE, 0);
	calendar.set(Calendar.SECOND, 0);
	calendar.set(Calendar.MILLISECOND, 0);
	calendar.add(Calendar.DAY_OF_MONTH, -30);

%>

<portlet:renderURL var="addCompanyURL">
	<portlet:param name="mvcPath" value="/company_MULTI.jsp"></portlet:param>
</portlet:renderURL>
<h1>MULTI</h1>
<main class="content">
	<div class="formulario">
		<div class="title-group">
			<h2><liferay-ui:message key="company.view.title"/></h2>
			<aui:button-row cssClass="button-holder">
				<aui:button onClick="<%= addCompanyURL.toString() %>" value="company.view.new.company" cssClass="btn btn-outline-primary btn-sm" primary="true"></aui:button>
			</aui:button-row>
		</div>
		<div class="form-content">
			<div class="row">
				<fieldset class="input-group-inline mt-3 mb-2">
					<legend><liferay-ui:message key="company.view.active"/></legend>
					<div class="custom-control custom-radio mr-4">
						<input class="form-check-input" name="inpSearchActive" id="inpSearchActive1" value="<%=yes%>" type="radio" class="inpSearchActive" checked onChange="tabla.draw();" />
						<label class="form-check-label" for="inpSearchActive1"><%=yes%></label>
					</div>
					<div class="custom-control custom-radio mr-4">
						<input class="form-check-input" name="inpSearchActive" id="inpSearchActive2" value="No" type="radio" class="inpSearchActive" onChange="tabla.draw();" />
						<label class="form-check-label" for="inpSearchActive2"><%=no%></label>
					</div>
					<div class="custom-control custom-radio mr-4">
						<input class="form-check-input" name="inpSearchActive" id="inpSearchActive3" value="Todos" type="radio" class="inpSearchActive" onChange="tabla.draw();" />
						<label class="form-check-label" for="inpSearchActive3"><%=all%></label>
					</div>
				</fieldset>
			</div>

			<div class="row">
				<fieldset class="input-group-inline mb-3">
					<legend><liferay-ui:message key="company.view.applications"/></legend>

					<c:forEach items="<%=appList%>" var="app">
						<%
							Application app = (Application) pageContext.getAttribute("app");
						%>
						<div class="custom-control custom-checkbox mr-4">
							<input class="custom-control-input clsApps" type="checkbox" id="id_${app.applicationId}" name="chkApp" value="${app.name}" <%--<%=!appIds.contains(app.getApplicationId())?"disabled":""%>--%> onchange="tabla.draw();">
							<label class="custom-control-label" for="id_${app.applicationId}">${app.name}</label>
						</div>
					</c:forEach>
				</fieldset>
			</div>

			<div class="row">
				<div class="col">
				    <div id="spnCargando" class="loading-animation"></div>
				    <div id="spnTabla" style="display:none">
					    <table id="table_company" class="display" style="width:100%">
						    <thead>
						    <tr>
							    <th><liferay-ui:message key="company.view.name"/></th>
							    <th><liferay-ui:message key="company.view.cif"/></th>
							    <th><liferay-ui:message key="company.view.applications"/></th>
							    <th><liferay-ui:message key="company.view.active"/></th>
							    <th><liferay-ui:message key="company.view.employees"/></th>
							    <th><liferay-ui:message key="company.view.date.start"/></th>
							    <th><liferay-ui:message key="company.view.date.end"/></th>
							    <th><liferay-ui:message key="company.view.options"/></th>
						    </tr>
						    </thead>
						    <tbody></tbody>
					    </table>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>

<liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllApplications" var="getAllApplicationsURL" />

<liferay-portlet:renderURL varImpl="editURL">
    <portlet:param name="companyEditId" value="ID_EMPRESA" />
    <portlet:param name="mvcPath" value="/company_MULTI.jsp" />
</liferay-portlet:renderURL>

<portlet:resourceURL id="getAllCompaniesMULTI" var="getAllCompaniesURL"/>
<script>
	var tabla = null;
    var editURL = "<%=editURL%>"
	var urlAjaxBase = "<%=getAllCompaniesURL.toString()%>" ;
	var dateDatatableAlta = null;
	var dateDatatableBaja = null;

    var arrLineApps = null, txtLineApps = "";
	var arrApps = [];

	 $(document).ready(function(){
            js_cliente = [];
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (this.readyState == 4 && this.status == 200){
                    arrApps = JSON.parse(this.responseText);
                    initDataTable();
                }
            };
            xhr.open("GET", "<%=getAllApplicationsURL%>", true);
            xhr.send(null);

        });

	// $(document).ready( function () {
	function initDataTable(){
        console.log("iniciando tabla ... ")
		var arrApplications = [];
		var elems = document.getElementsByClassName("clsApps");
		for(var i = 0; i < elems.length; i++){
			arrApplications.push(String(elems[i].id));
		}
tabla = $('#table_company').on('preXhr.dt', function ( e, settings, data ) {
	     	$("#spnCargando").hide();
			$("#spnTabla").show();
	    })


		tabla = $('#table_company').on('preXhr.dt', function ( e, settings, data ) {
		                console.log("preXhr.dt load")
                        //$("#spnCargando").hide();
                        //$("#spnTabla").show();
            }).DataTable( {
            "initComplete": function(settings, json) {
                        $("#spnCargando").hide();
                        $("#spnTabla").show();
            },
			dom: 'Bfrtip',
			ajax: {
                "url": urlAjaxBase,
                "dataSrc": function ( json ) {  return json.data; }
            },
            "columns": [
                            {"data" : "nombre"},
                            {"data" : "cif"},
                            {"data" : "aplicaciones"},
                            {"data" : "activa"},
                            {"data" : "empleados"},
                            {"data" : "fechaAltaTime"},
                            {"data" : "fechaBajaTime"},
                            {"data" : "compId"} //editar
            ],
            "columnDefs": [
                {
                    "targets"  : [7],
                    "orderable": false,
                    "render": function (data, type, row) {
                                return '<a class="ico-acciones-tabla" href="#ENLACE#&multi=1"> '.replace("#ENLACE#", editURL.replace("ID_EMPRESA", data))  +
                                    '<img src="<%=themeDisplay.getPathThemeImages()%>/ico_edit.png" alt="<liferay-ui:message key="edit" />" /> </a>';

                                }
                },
                {
                    "targets"  : [5],
                    "orderable": true,
                    "render": function (data, type, row) {
                        dateDatatableAlta = new Date(parseInt(data));
                    	return "<span class=\"hide\">" + dateDatatableAlta.toLocaleDateString('en-GB').split('/').reverse().join('') +  "</span><span>" + dateDatatableAlta.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>";
                     }
                },
                {
                    "targets"  : [6],
                    "orderable": true,
                    "render": function (data, type, row) {
                        if(data == null || data == undefined || data == " " || data == "") return "";
                        dateDatatableBaja = new Date(parseInt(data));
                        return "<span class=\"hide\">" + dateDatatableBaja.toLocaleDateString('en-GB').split('/').reverse().join('') +  "</span><span>" + dateDatatableBaja.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>";
                    }
                },
                {
                    "targets"  : [2],
                    "orderable": false,
                    "render": function (data, type, row) {
                        if(data == null || data == undefined || data == " ") return "";
                        arrLineApps = data.split("#");
                        txtLineApps = "";
                        for(let i = 1; i < arrLineApps.length; i++){
                            if(txtLineApps != "") txtLineApps += ", ";
                            txtLineApps +=  getNameAppById(arrLineApps[i]);
                        }
                        return txtLineApps + '<span class="badge badge-info viewDetail">+</span>';
                    }
                }
            ],
			responsive: true,
			pageLength: 25,
			buttons: [
				'csv', 'excel', 'print', 'pdf'
			],
			"language": {
				"sProcessing":     "<%=LanguageUtil.get(bundle, "company.view.datatable.sProcessing")%>",
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "company.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "company.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "company.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "company.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "company.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "company.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "company.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "company.view.datatable.sSearch")%>",
				"sUrl":            "<%=LanguageUtil.get(bundle, "company.view.datatable.sUrl")%>",
				"sInfoThousands":  "<%=LanguageUtil.get(bundle, "company.view.datatable.sInfoThousands")%>,",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "company.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "company.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "company.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "company.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "company.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "company.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "company.view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "company.view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "company.view.datatable.colvis")%>"
				}
			}
		} );

		$.fn.dataTable.ext.search.push(
			function( settings, data, dataIndex ) {
				//CONDICIÓN MULTIPLE
				var numCond = 0;

				if(numCond == 0 &&
						(document.querySelector("input[name='inpSearchActive']:checked").value  == "Todos" || data[3].indexOf(document.querySelector("input[name='inpSearchActive']:checked").value.substring(0,1)) == 0)

				) {

					if($( "input[name=chkApp]:checked").length == 0) return true; <!-- USAMOS EL NAME QUE LE HEMOS PUESTO A TODOS LOS CHECKBOX -->
					for(var i= 0; i < arrApplications.length; i++){ <!-- NOMBRE DEL ARRAY EN DONDE HEMOS DEJADO LOS VALORES POSIBLES -->
						if(document.getElementById(arrApplications[i]).checked){
							var appValue = document.getElementById(arrApplications[i]).value;
							if(data[2].indexOf(appValue) != -1){
								return true;
							}
						}
					}
				}

				return false;
			}
		);

		$('#inpSearchActive').change( function() {
			tabla.draw();
		} );

		$('.clsApps').change( function() {
			tabla.draw();
		} );

		tabla.draw();

	//} );
	}


//Detalle de empresa
 $('#table_company tbody').on('click', '.viewDetail', function (e) {
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


	            $('div.slider', row.child()).slideDown();
	            $(event.target).attr('class','viewDetail icon-eye-close');
	        }
	    });

var htmlFila = null;
var namesApps = "";
function format ( d ) {
    htmlFila = '<div class="slider">'+
        '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';

   var clientes = d["clienteContratos"];


    for(let i = 0; i < clientes.length; i++){
        htmlFila += '<tr>'+
        			'<td><span class="label label-primary">' +  clientes[i].cliente + '</span>' +
        			    '<span class="label label-warning">' +  clientes[i].contrato + '</span>' +
        			    '<span class="label label-success">' +  clientes[i].nombre + '</span>' +
        			    '</td>';
        htmlFila += '<td>';
        namesApps = "";
        for(let x = 0; x < clientes[i].apps.length; x++){
            if(namesApps != "") namesApps += ", ";
            namesApps += getNameAppById(clientes[i].apps[x].appId);
        }
        htmlFila += namesApps;
        htmlFila += '</td></tr>';

    }
    htmlFila += "</table></div>"
    return htmlFila;
}

function getNameAppById(_id){
    var resultado = null;
    for(let c = 0; c < arrApps.data.length; c++){
        if(arrApps.data[c].appId == _id){
            resultado = arrApps.data[c].name;
            break;
        }
    }
    return resultado;
}

</script>
