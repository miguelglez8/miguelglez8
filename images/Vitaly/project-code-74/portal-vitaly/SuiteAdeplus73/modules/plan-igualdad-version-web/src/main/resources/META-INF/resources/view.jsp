<%@page import="com.plan.igualdad.liferay.portlet.version.web.constants.PlanIgualdadVersionWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<%

ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
Version versionActiva = (Version) request.getAttribute(PlanIgualdadVersionWebPortletKeys.VERSION_ACTIVA);

boolean hasRole = PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                      || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser());

boolean refresh = (Boolean) request.getAttribute(PlanIgualdadVersionWebPortletKeys.REFRESH_PARAM);

%>

<portlet:renderURL var="versionPlanURL">
    <portlet:param name="mvcRenderCommandName" value="goVersionPlan" />
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="VERSION_ID" />
</portlet:renderURL>

<liferay-ui:success key="version-save-success" message="plan.view.version.save.success" />

<main class="content">
    <div class="formulario">

        <div class="title-group">
            <div class="row mb-2">
                <div class="col-md-6 col-12">
                    <c:if test="<%= !hasRole %>">
                        <h2><liferay-ui:message key="plan.view.version"/></h2>
                    </c:if>
                </div>
                <c:if test="<%= Validator.isNull(versionActiva) %>">
                    <div class="col-md-6 col-12 text-right">
                        <a class="prv-iconLinks prv-iconLinks__plus" href="<%= versionPlanURL.replace("VERSION_ID", "") %>">
                            <liferay-ui:message key="plan.view.version.annadir"/>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>

        <div class="prv-search prv-form">
            <div class="row align-items-start mb-4">

                <%-- ACTIVA --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <fieldset>
                        <legend><liferay-ui:message key="plan.view.version.activa"/></legend>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchActiva" id="inpSearchActiva1" value="0" type="radio" onclick="tabla.draw()" />
                                <label class="form-check-label" for="inpSearchActiva1"><liferay-ui:message key="plan.view.version.activa.si" /></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchActiva" id="inpSearchActiva2" value="1" type="radio" onclick="tabla.draw()" />
                                <label class="form-check-label" for="inpSearchActiva2"><liferay-ui:message key="plan.view.version.activa.no" /></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchActiva" id="inpSearchActiva3" value="2" type="radio" onclick="tabla.draw()" checked />
                                <label class="form-check-label" for="inpSearchActiva3"><liferay-ui:message key="plan.view.version.activa.todas" /></label>
                            </div>
                        </div>
                    </fieldset>
                </div>

            </div>
        </div>

        <div class="row">
            <div class="col">
                <div id="spnCargando" class="loading-animation"></div>
                <table id="tabla_versiones" class="display" style="width:100%; display:none;">
                    <thead>
                        <tr>
                            <th rowspan="2"><liferay-ui:message key="plan.view.version.plan"/></th>
                            <th colspan="2"><liferay-ui:message key="plan.view.version.periodoDatos"/></th>
                            <th colspan="2"><liferay-ui:message key="plan.view.version.periodoPlan"/></th>
                            <th rowspan="2"><liferay-ui:message key="plan.view.version.fechaBaja"/></th>
                            <th rowspan="2"></th>
                        </tr>
                        <tr>
                            <th><liferay-ui:message key="plan.view.version.periodo.inicio"/></th>
                            <th><liferay-ui:message key="plan.view.version.periodo.fin"/></th>
                            <th><liferay-ui:message key="plan.view.version.periodo.inicio"/></th>
                            <th><liferay-ui:message key="plan.view.version.periodo.fin"/></th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>

    </div>
</main>

<portlet:resourceURL id="buscarVersiones" var="buscarVersionesURL"/>
<script>

	
	if(<%=refresh%>){
		location.reload();
		
		<%
		refresh = false;
		%>
	}
	
    var tabla = null;
    $(document).ready( function () {
        tabla = $('#tabla_versiones').on('preXhr.dt', function ( e, settings, data ) {
                    $("#spnCargando").hide();
                    $("#tabla_versiones").show();
                }).DataTable({
                    dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
                    pageLength: 20,
                    "ajax": {
                        "url": "<%= buscarVersionesURL %>",
                        "data" : {
                            "compId" : "${compId}"
                        },
                        "dataSrc": function ( json ) {
                            return json.data;
                        }
                    },
                    "columns": [
                        {"data" : "nombre"},
                        {"data" : "inicioPeriodoDatos"},
                        {"data" : "finPeriodoDatos"},
                        {"data" : "inicioPeriodoIgualdad"},
                        {"data" : "finPeriodoIgualdad"},
                        {"data" : "fechaBaja"},
                        {"data" : "versionId"},
                        {"data" : "fechaBaja"},
                        {"data" : "fechaCreacion"}
                    ],
                    "columnDefs": [
                        {
                              "targets"  : [1],
                              "orderable": true,
                              "render": function (data, type, row) {
                                            if(data == null || data == undefined || data == "") return "";
                                            var fecha = new Date(data);
                                            return  '<span style="display : none;">' + fecha.toLocaleDateString('en-GB').split('/').reverse().join('') +  '</span>' +
                                                    '<span>' + fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + '</span>';
                                        }
                        },
                        {
                              "targets"  : [2],
                              "orderable": true,
                              "render": function (data, type, row) {
                                            if(data == null || data == undefined || data == "") return "";
                                            var fecha = new Date(data);
                                            return  '<span style="display : none;">' + fecha.toLocaleDateString('en-GB').split('/').reverse().join('') +  '</span>' +
                                                    '<span>' + fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + '</span>';
                                        }
                        },
                        {
                              "targets"  : [3],
                              "orderable": true,
                              "render": function (data, type, row) {
                                            if(data == null || data == undefined || data == "") return "";
                                            var fecha = new Date(data);
                                            return  '<span style="display : none;">' + fecha.toLocaleDateString('en-GB').split('/').reverse().join('') +  '</span>' +
                                                    '<span>' + fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + '</span>';
                                        }
                        },
                        {
                              "targets"  : [4],
                              "orderable": true,
                              "render": function (data, type, row) {
                                            if(data == null || data == undefined || data == "") return "";
                                            var fecha = new Date(data);
                                            return  '<span style="display : none;">' + fecha.toLocaleDateString('en-GB').split('/').reverse().join('') +  '</span>' +
                                                    '<span>' + fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + '</span>';
                                        }
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
                              "orderable": false,
                              "render": function (data, type, row) {
                                            if(data == null || data == undefined || data == "") return "";
                                            var version = "<%= Validator.isNotNull(versionActiva) ? versionActiva.getVersionId() : StringPool.BLANK %>";
                                            if (version === data) {
                                                var url = "<%= versionPlanURL %>".replace("VERSION_ID", data);
                                                return  '<a class="ico-acciones-tabla" href="' + url + '">' +
                                                            '<span class="icon-pencil"></span>' +
                                                        '</a>';
                                            }
                                            return '';
                                        }
                        },
                        {
                              "targets"  : [7],
                              "orderable": false,
                              "visible": false,
                              "render":  function (data, type, row) {
                                              if(data == null || data == undefined || data == "") return "";
                                              return  new Date(data).getTime();
                                          }
                        },
                        {
                              "targets"  : [8],
                              "orderable": false,
                              "visible": false,
                              "render":  function (data, type, row) {
                                              if(data == null || data == undefined || data == "") return "";
                                              return  new Date(data).getTime();
                                          }
                        }
                    ],
                    "order": [[8,"desc"]],
                    buttons: [
                        'csv', 'excel', 'print', 'pdf'
                    ],
                    "initComplete": function(settings, json) {
                        $("#spnCargando").hide();
                        $("#tabla_versiones").show();
                    },
                    "language": {
                        "sProcessing":     "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sProcessing")%>",
                        "sLengthMenu":     "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sLengthMenu")%>",
                        "sZeroRecords":    "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sZeroRecords")%>",
                        "sEmptyTable":     "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sEmptyTable")%>",
                        "sInfo":           "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sInfo")%>",
                        "sInfoEmpty":      "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sInfoEmpty")%>",
                        "sInfoFiltered":   "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sInfoFiltered")%>",
                        "sInfoPostFix":    "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sInfoPostFix")%>",
                        "sSearch":         "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sSearch")%>",
                        "sUrl":            "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sUrl")%>",
                        "sInfoThousands":  "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sInfoThousands")%>,",
                        "sLoadingRecords": "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sLoadingRecords")%>",
                        "oPaginate": {
                            "sFirst":    "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sFirst")%>",
                            "sLast":     "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sLast")%>",
                            "sNext":     "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sNext")%>",
                            "sPrevious": "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sPrevious")%>"
                        },
                        "oAria": {
                            "sSortAscending":  "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sSortAscending")%>",
                            "sSortDescending": "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.sSortDescending")%>"
                        },
                        "buttons": {
                            "copy": "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.copy")%>",
                            "colvis": "<%=LanguageUtil.get(bundle, "planigualdad.view.datatable.colvis")%>"
                        }
                    }
                });

        $.fn.dataTable.ext.search.push(function( settings, data, dataIndex ) {

            var activa = $('input:radio[name=inpSearchActiva]:checked').val();

            var cond = 0;
            var numCond = 0;

            if (activa != "2") {
                numCond++;
                if (activa == "0" && (data[7].trim().match('^$') || data[7] >= new Date().getTime())) cond++;
                if (activa == "1" && (!data[7].trim().match('^$') && data[7] < new Date().getTime())) cond++;
            }

            if(numCond == cond) return true;
            return false;

        });
    });

</script>