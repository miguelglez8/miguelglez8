<%@ page import="com.legalplus.liferay.portlet.admin.empresas.web.enums.FamiliaNormativa" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense" %>
<%@ page import="com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil" %>
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

	boolean adminRole = LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser());
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
            		<label for="inpBuscarCajon">
            			<liferay-ui:message key="company.view.textoBuscar"/>            		
            		</label>
            		<input type="search" class="" placeholder="" aria-controls="table_company" id="inpBuscarCajon">
        		</div>

        		<%-- ACTIVA --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <fieldset>
                        <legend><liferay-ui:message key="company.view.activa"/></legend>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchActiva" id="inpSearchActiva1" value="Si" type="radio" checked />
                                <label class="form-check-label" for="inpSearchActiva1"><liferay-ui:message key="company.view.activa.si" /></abel>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchActiva" id="inpSearchActiva2" value="No" type="radio" />
                                <label class="form-check-label" for="inpSearchActiva2"><liferay-ui:message key="company.view.activa.no" /></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchActiva" id="inpSearchActiva3" value="Todos" type="radio" />
                                <label class="form-check-label" for="inpSearchActiva3"><liferay-ui:message key="company.view.activa.todas" /></label>
                            </div>
                        </div>
                    </fieldset>
                </div>

        	</div>
			
			<div class="prv-advanced-search mb-4">
    			<a class="prv-advanced-search__trigger" data-toggle="collapse" href="#advanced-search" role="button" 
    				aria-expanded="true" aria-controls="collapseExample">
    				<liferay-ui:message key="company.view.busquedaAvanzada" />
    			</a>
			</div>
			
		<div class="collapse" id="advanced-search">
			<div class="row align-items-start mb-4">
                <div class="col-lg-3 col-md-6 col-12">
                    <fieldset class="">
                        <legend><liferay-ui:message key="company.view.consultant"/></legend>
                        <aui:select name="<%=AdminEmpresasPortletKeys.CONSULTOR%>" label=""> <!-- onchange="tabla.draw()"> -->
                            <aui:option value=""></aui:option>
                            <aui:option value="0"><liferay-ui:message key="company.view.pendienteAsignar"/></aui:option>
                            <c:forEach items="<%=userList%>" var="user">
                                <aui:option value="${user.userId}">${user.fullName}</aui:option>
                            </c:forEach>
                        </aui:select>
                    </fieldset>
                </div>
                <div class="col-lg-3 col-md-6 col-12">
                    <fieldset class="">
                        <legend><liferay-ui:message key="company.view.normas"/></legend>
                        <aui:select name="<%=AdminEmpresasPortletKeys.NORMATIVA%>" label="">
                            <aui:option value=""></aui:option>
                            <c:forEach items="<%= FamiliaNormativa.values() %>" var="normativa">
                                <aui:option value="${normativa.codigo}"><liferay-ui:message key="${normativa.descripcion}"/></aui:option>
                            </c:forEach>
                        </aui:select>
                    </fieldset>
                </div>
                <div class="col-lg-3 col-md-6 col-12">
                   <fieldset class="">
                       <legend><liferay-ui:message key="company.view.contrato"/></legend>
                       <aui:select name="<%=AdminEmpresasPortletKeys.CONTRATO%>" label=""> <!--   onchange="tabla.draw()"> -->
                           <aui:option value=""></aui:option>
                           <c:forEach items="<%=licenseList%>" var="license">
                                <aui:option value="${license.licenseId}">${license.name}</aui:option>
                           </c:forEach>
                       </aui:select>
                   </fieldset>
                </div>
            
			</div>
			<div class="row align-items-start mb-4">

                <div class="col-md-6 col-12 d-flex">
                    <div class="col-md-6 col-12 pl-0">
                        <label><liferay-ui:message key="company.view.startDate"/></label>
                        <liferay-ui:input-date name="<%=AdminEmpresasPortletKeys.START_DATE%>" nullable="true" showDisableCheckbox="false" />
                    </div>
                    <div class="col-md-6 col-12 pr-0">
                        <label><liferay-ui:message key="company.view.endDate"/></label>
                        <liferay-ui:input-date name="<%=AdminEmpresasPortletKeys.END_DATE%>" nullable="true" showDisableCheckbox="false"/>
                    </div>
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
                            <th><liferay-ui:message key="company.view.date.start"/></th>
                            <th><liferay-ui:message key="company.view.date.end"/></th>
                            <th><liferay-ui:message key="company.view.name"/></th>
                            <th><liferay-ui:message key="company.view.cif"/></th>
                            <th><liferay-ui:message key="company.view.contrato"/></th>
                            <th><liferay-ui:message key="company.view.normas"/></th>
                            <th><liferay-ui:message key="company.view.consultant"/></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>

        <c:if test="<%= adminRole %>">
            <%@include file="consultant.jspf" %>
        </c:if>

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
                {"data" : "fecha"},
                {"data" : "activa"},
                {"data" : "nombre"},
                {"data" : "cif"},
                {"data" : "contrato"},
                {"data" : "normas"},
                {"data" : "consultores"},
                {"data" : "id"},
                {"data" : "fecha"}
            ],
			"columnDefs": [
                {
                      "targets"  : [0],
                      "orderable": true,
                      "render": function (data, type, row) {
                                   if(data == null || data == undefined || data == "") return "";
                                   var fecha = new Date(data);
                                   return  '<span style="display : none;">' + fecha.toLocaleDateString('en-GB').split('/').reverse().join('') +  '</span>' +
                                           '<span>' + fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + '</span>';
                               }
                },
                {
                      "targets"  : [1],
                      "orderable": true,
                      "visible": true,
                      "render":  function (data, type, row) {
                                      if(data == null || data == undefined || data == "") return "";
                                      var fecha = new Date(data);
                                      return  fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' });
                                  }
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
                                      var familias = "";
                                      var familiasArray = data.split(";");
                                      familiasArray.forEach(function(item) {
                                          if (item != "") {
                                              familias += '<div class="d-flex flex-column align-items-start">' +
                                                              '<span class="badge badge-pill badge-info">' +
                                                                    $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.NORMATIVA%> option[value='" + item + "']").text() +
                                                              '</span>' +
                                                          '</div>';
                                          }
                                      });
                                      if (familiasArray.length > 4) familias += '<span class="hide">Todas</span>';
                                      return familias;
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
                                    <c:if test="<%= adminRole %>">
                                        span += '<a class="add-consultant ico-acciones-tabla" data-id="' + data + '" href="#">' +
                                                  '<span class="icon-user"></span></a>';
                                    </c:if>
                                    span += '<a class="ico-acciones-tabla" onclick="saveParamsSearch()" href="${legislacionesUrl}' +  data + '" >' +
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
            "order": [[0,"desc"],[2,"asc"]],
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

                    var consultor = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.CONSULTOR%> option:selected").text().trim();
                    var consultorVal = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.CONSULTOR%>").val();

                    var normativa = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.NORMATIVA%> option:selected").text().trim();
                    var normativaVal = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.NORMATIVA%>").val();

                    var contrato = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.CONTRATO%> option:selected").text().trim();
                    var contratoVal = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.CONTRATO%>").val();

                    var inicio = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.START_DATE%>").val();
                    var fin = $("#<portlet:namespace/><%=AdminEmpresasPortletKeys.END_DATE%>").val();

                    var activa = $('input:radio[name=inpSearchActiva]:checked').val();

                    var cond = 0;
                    var numCond = 0;

                    if(consultorVal != "" && consultorVal != "0"){
                        numCond++;
                        if(data[6].trim().match(consultor)) cond++;
                    }
                    if (consultorVal == "0") {
                        numCond++;
                        if(data[6].trim().match('^$')) cond++;
                    }

                    if(normativaVal != "" && normativaVal != "99"){
                        numCond++;
                        if(data[5].trim().match(normativa)) cond++;
                    }
                    if(normativaVal == "99") {
                        numCond++;
                        if(data[5].trim().match("Todas")) cond++;
                    }

                    if(contratoVal != ""){
                        numCond++;
                        if(data[4].trim() == contrato) cond++;
                    }

                    var arrDate = null;
                    var dateFilter = null;
                    var dateFilterCol = null;

                    if (activa != "Todos") {
                        numCond++;
                        arrDate = data[1].split("/");
                        dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                        if (activa == "Si" && data[1].trim().match('^$')) cond++;
                        if (activa == "No" && !data[1].trim().match('^$') && dateFilterCol.getTime() < new Date().getTime()) cond++;
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
    //Guardar parametros de filtrado
    function saveParamsSearch(){
        var itemSearch = {};
        itemSearch.txtBuscar = document.getElementById("inpBuscarCajon").value.trim();
        itemSearch.inpSearchActiva1 = document.getElementById("inpSearchActiva1").checked;
        itemSearch.inpSearchActiva2 = document.getElementById("inpSearchActiva2").checked;
        itemSearch.inpSearchActiva3 = document.getElementById("inpSearchActiva3").checked;


        itemSearch.consultor = document.getElementById("<portlet:namespace/>consultor").value;
        itemSearch.normativa = document.getElementById("<portlet:namespace/>normativa").value;
        itemSearch.contrato = document.getElementById("<portlet:namespace/>contrato").value;
        itemSearch.startDate = document.getElementById("<portlet:namespace/>startDate").value;
        itemSearch.endDate = document.getElementById("<portlet:namespace/>endDate").value;

        sessionStorage.setItem("searchParams_emplegislaciones", JSON.stringify(itemSearch));
        //event.preventDefault();


    }

    //Recuperar los filtros
        $(document).ready(function(){
            var advancedSearch = false;
            if(sessionStorage.getItem("searchParams_emplegislaciones") != null && sessionStorage.getItem("searchParams_emplegislaciones") != "null"){
                itemSearch = JSON.parse(sessionStorage.getItem("searchParams_emplegislaciones"));
                if(itemSearch.txtBuscar && itemSearch.txtBuscar != "" ) document.getElementById("inpBuscarCajon").value = itemSearch.txtBuscar;
                if(itemSearch.inpSearchActiva1) document.getElementById("inpSearchActiva1").checked = true;
                if(itemSearch.inpSearchActiva2) document.getElementById("inpSearchActiva2").checked = true;
                if(itemSearch.inpSearchActiva3) document.getElementById("inpSearchActiva3").checked = true;


                var event = new Event('change');

                if(itemSearch.consultor){
                    document.getElementById("<portlet:namespace/>consultor").value = itemSearch.consultor;
                    document.getElementById("<portlet:namespace/>consultor").dispatchEvent(event);
                    advancedSearch = true;
                }

                if(itemSearch.normativa){
                    document.getElementById("<portlet:namespace/>normativa").value = itemSearch.normativa;
                    document.getElementById("<portlet:namespace/>normativa").dispatchEvent(event);
                    advancedSearch = true;
                }
                if(itemSearch.contrato){
                    document.getElementById("<portlet:namespace/>contrato").value = itemSearch.contrato;
                    document.getElementById("<portlet:namespace/>contrato").dispatchEvent(event);
                    advancedSearch = true;
                }
                if(itemSearch.startDate){
                    document.getElementById("<portlet:namespace/>startDate").value = itemSearch.startDate;
                    //document.getElementById("<portlet:namespace/>startDate").dispatchEvent(event);
                    advancedSearch = true;
                }
                if(itemSearch.endDate){
                    document.getElementById("<portlet:namespace/>endDate").value = itemSearch.endDate;
                    //document.getElementById("<portlet:namespace/>endDate").dispatchEvent(event);
                    advancedSearch = true;
                }


                if(advancedSearch){
                    document.getElementsByClassName("prv-advanced-search__trigger")[0].classList.remove("collapsed");
                    document.getElementsByClassName("prv-advanced-search__trigger")[0].classList.add("active");

                    document.getElementById("advanced-search").classList.remove("collapsed");
                    document.getElementById("advanced-search").classList.add("show");
                }


                sessionStorage.setItem("searchParams_emplegislaciones", null)

                //sessionStorage.clear();
                buscarLegislaciones();

            }
        });

</script>