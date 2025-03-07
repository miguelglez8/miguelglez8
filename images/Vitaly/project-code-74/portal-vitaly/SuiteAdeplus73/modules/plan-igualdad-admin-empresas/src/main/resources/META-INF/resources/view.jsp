<%@ page import="com.plan.igualdad.liferay.portlet.admin.empresas.web.enums.FamiliaNormativa" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense" %>
<%@ page import="com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.model.User" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.List" %>
<%@ include file="init.jsp" %>

<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	List<User> userList = (List<User>) request.getAttribute(AdminEmpresasPortletKeys.USER_LIST);
	List<ApplicationLicense> licenseList = (List<ApplicationLicense>) request.getAttribute(AdminEmpresasPortletKeys.LICENSE_LIST);

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

%>

<liferay-ui:success key="consultant-save-success" message="admin.empresas.save.consultant.success" />

<main class="content">
    <div class="formulario">

		<div class="title-group">
			<h2><liferay-ui:message key="company.view.title"/></h2>
		</div>

		<div class="form-content prv-form">

			<div class="row align-items-start mb-4">
                <div class="col-lg-3 col-md-6 col-12">
                    <legend>
                        <liferay-ui:message key="company.view.textoBuscar"/>
                    </legend>
                    <input type="search" class="" placeholder="" aria-controls="table_company" id="inpBuscarCajon">
                </div>
                <div class="col-lg-3 col-md-6 col-12">
					<fieldset class="">
						<legend><liferay-ui:message key="company.view.estado"/></legend>
						<aui:select name="<%=AdminEmpresasPortletKeys.ESTADO_EMPRESA%>" label="">
                            <aui:option value="">
                            	
                            </aui:option>
							<c:forEach items="${estadosList}" var="estado">
								<aui:option value="${estado.nombre}">
	                            	${estado.nombre}
	                            </aui:option>
							</c:forEach>
                        </aui:select>
					</fieldset>
				</div>
                <div class="col-lg-3 col-md-6 col-12 d-flex">
                    <div class="col-md-6 col-12 pl-0">
                        <fieldset class="">
                            <legend><liferay-ui:message key="company.view.startDate"/></legend>
                            <liferay-ui:input-date name="<%=AdminEmpresasPortletKeys.START_DATE%>" nullable="true" showDisableCheckbox="false" />
                        </fieldset>
                    </div>
                    <div class="col-md-6 col-12 pr-0">
                        <fieldset class="">
                            <legend><liferay-ui:message key="company.view.endDate"/></legend>
                            <liferay-ui:input-date name="<%=AdminEmpresasPortletKeys.END_DATE%>" nullable="true" showDisableCheckbox="false"/>
                        </fieldset>
                    </div>
                </div>
            </div>
			<div class="row align-items-end">
        		<div class="col-md-12 col-12 text-right">
            		<div class="btnClear">
                		<button class="btn btn btn-outline-primary btn-sm btn-primary" type="button" onclick="cleanFilters()">
                    		<%=LanguageUtil.get(bundle, "company.view.limpiarFiltros")%>
                		</button>
                		<button class="btn btn btn-sm btn-primary" type="button" onclick="searchChange()">
                    		<%=LanguageUtil.get(bundle, "company.view.buscarView")%>
                 		</button>
                		<span id="spanErrorBusqueda" role="alert" class="has-error help-block"></span>                    
            		</div>
        		</div>
    		</div>
		
		</div>

        <div class="row mt-3">
            <div class="col">
                <div id="spnCargando" class="loading-animation"></div>
                <div id="spnTabla" style="display:none">
                    <table id="table_company" class="display dataTable no-footer dtr-inline stripe " >
                        <thead>
                        <tr>
                            <th><liferay-ui:message key="company.view.cif"/></th>
                            <th><liferay-ui:message key="company.view.name"/></th>
                            <th><liferay-ui:message key="company.view.interlocutor"/></th>
                            <th><liferay-ui:message key="company.view.interlocutor.email"/></th>
                            <th><liferay-ui:message key="company.view.estado"/></th>
                            <th><liferay-ui:message key="company.view.date.contrato"/></th>
                            <th><liferay-ui:message key="company.view.consultant"/></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>

    </div>
</main>

<portlet:resourceURL id="buscarEmpresas" var="buscarEmpresasURL"/>
<script>
	var tabla = null;
	$(document).ready( function () {

		tabla = $('#table_company').DataTable( {
			dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
			responsive: true,
			pageLength: 20,
			"ajax": {
                "url": "<%=buscarEmpresasURL%>",
                "dataSrc": function ( json ) {
                    return json.data;
                }
            },
            "columns": [
                {"data" : "cif"},
                {"data" : "nombre"},
                {"data" : "interlocutor"},
                {"data" : "interlocutorEmail"},
                {"data" : "estado"},
                {"data" : "fecha"},
                {"data" : "consultores"},
                {"data" : "id"},
                {"data" : "fecha"}
            ],
			"columnDefs": [
                {
                    "targets"  : [0],
                    "orderable": true
                },
                {
                    "targets"  : [1],
                    "orderable": true
                },
                {
                      "targets"  : [2],
                      "orderable": true
                },
                {
                      "targets"  : [3],
                      "orderable": true
                },
                {
                    "targets"  : [4],
                    "orderable": true
              	},
                {
                    "targets"  : [5],
                    "orderable": true,
                    "render": function (data, type, row) {
                        if(data == null || data == undefined || data == "") return "";
                        var fecha = new Date(data);
                        return  '<span style="display : none;">' + fecha.toLocaleDateString('en-GB').split('/').reverse().join('') +  '</span>' +
                            '<span>' + fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + '</span>';
                    }
                },
                {
                      "targets"  : [6],
                      "orderable": true,
                      "render": function (data, type, row) {
                                    var consultores = "";
                                    data.forEach((item) => {
                                        consultores += '<p data-comp="' + item.compId + '" data-id="' + item.userId + '">' + item.name + ' ' + item.lastName + '</p>';
                                    });
                                    return consultores;
                                }
                },
                {
                    "targets"  : [7],
                    "orderable": false,
                    "searchable": false,
                    "render": function (data, type, row) {
                                    var span = '';
                                    span += '<a class="ico-acciones-tabla" href="${legislacionesUrl}' +  data + '" >' +
                                      			'<span class="icon-pencil"></span></a>';
                                    return span;
                                              
                              }
              	},
              	{
                      "targets"  : [8],
                      "orderable": false,
                      "visible": false,
                      "render":  function (data, type, row) {
                                      if(data == null || data == undefined || data == "") return "";
                                      var fecha = new Date(data);
                                      return  fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' });
                                  }
                }
            ],
            "order": [[0,"desc"],[1,"asc"]],
			buttons: [
				'csv', 'excel', 'print', 'pdf'
			],
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
                $("#spnTabla").show();
            },
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


                    var inicio = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.START_DATE%>").val();
                    var fin = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.END_DATE%>").val();

                    var cond = 0;
                    var numCond = 0;

                    var estadoVal = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.ESTADO_EMPRESA%>").val();

                    var arrDate = null;
                    var dateFilter = null;
                    var dateFilterCol = null;


                     if(estadoVal != ""){
                        numCond++;
                        console.log(">>> data[4]: " + data[4]);
                        if(data[4].trim().match(estadoVal))cond++;
                     }

                    if(inicio.trim() != "" && isValidDate(inicio)){
                        numCond++;
                        arrDate = inicio.split("/");
                        dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                        arrDate = data[8].split("/");
                        dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                        if(dateFilterCol.getTime() >= dateFilter.getTime() ) cond++;
                    }

                    if(fin.trim() != "" && isValidDate(fin)){
                        numCond++;
                        arrDate = fin.split("/");
                        dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                        arrDate = data[8].split("/");
                        dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                        if(dateFilterCol.getTime() <= dateFilter.getTime() ) cond++;
                    }

                    if(numCond == cond) return true;
                    return false;
            }
        );

		tabla.draw();

	});

	function isValidDate(_date){
        var reg = /^(0[1-9]|[1-2]\d|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
        if(reg.test(_date)) return true;
        return false;

    }

	$(document).on('click', '.add-consultant', function(e){
        e.preventDefault();
        var id = $(this).data('id');
        $('#addConsultant').removeClass('hide');
        $('#addConsultant').addClass('show');
        $('#<portlet:namespace />compId').val(id);

        $('#consultantsList .input-select-wrapper').removeClass('has-error');
        $('#consultantsList #<portlet:namespace/>userIdHelper').remove()

        var consultants = [];
        $("p[data-comp='" + id + "']").each(function() {
             consultants.push($(this).data('id'));
        });
        $('#<portlet:namespace/><%=AdminEmpresasPortletKeys.USER_ID%>').val(consultants).trigger("change");
    });

    $('#<portlet:namespace/><%=AdminEmpresasPortletKeys.USER_ID%>').select2({
        placeholder: "Seleccione ..",
        multiple : true,
        language: "es"
    });

    $(document).on('click', '.hide-modal', function(e) {
        e.preventDefault();
        $('#addConsultant').addClass('hide');
    });

   //limpiar filtros
   var arrCampos = ["<portlet:namespace/><%=AdminEmpresasPortletKeys.CONSULTOR%>","<portlet:namespace/><%=AdminEmpresasPortletKeys.NORMATIVA%>","<portlet:namespace/><%=AdminEmpresasPortletKeys.CONTRATO%>","<portlet:namespace/><%=AdminEmpresasPortletKeys.START_DATE%>","<portlet:namespace/><%=AdminEmpresasPortletKeys.END_DATE%>"];

   function cleanFilters(){
		for(var i = 0; i < arrCampos.length; i++){
			$("#" + arrCampos[i]).val('').trigger('change');
		}
		$("#inpBuscarCajon").val("");
   }

   var advancedSearchTrigger = document.querySelectorAll('.prv-advanced-search__trigger');
   if(advancedSearchTrigger.length > 0){
      for (const ast of advancedSearchTrigger) {
          ast.addEventListener('click', function(){
              ast.classList.toggle('active');
          });
      }
   }

   function searchChange() {
       var value = $('#inpBuscarCajon').val();
       tabla.search(value).draw() ;
   }

   $(document).on('keypress',function(e) {
       if(e.which == 13) {
           searchChange();
       }
   });

</script>