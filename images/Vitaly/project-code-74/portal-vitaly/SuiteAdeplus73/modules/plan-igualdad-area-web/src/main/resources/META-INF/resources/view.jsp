<%@ include file="/init.jsp" %>

<%

ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

boolean hasRole = PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                      || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser());

%>

<portlet:renderURL var="areaURL">
    <portlet:param name="mvcRenderCommandName" value="goArea" />
    <portlet:param name="compId" value="${compId}" />
    <portlet:param name="versionId" value="${versionId}" />
    <portlet:param name="areaId" value="AREA_ID" />
</portlet:renderURL>

<liferay-ui:success key="area-save-success" message="area.view.save.success" />

<main class="content">
    <div class="formulario">

        <div class="title-group">
            <div class="row mb-2">
                <div class="col-md-6 col-12">
                    <c:if test="<%= !hasRole %>">
                        <h2><liferay-ui:message key="area.view.title"/></h2>
                    </c:if>
                </div>
                <div class="col-md-6 col-12 text-right">
                    <a class="prv-iconLinks prv-iconLinks__plus" href="<%= areaURL.replace("AREA_ID", "") %>">
                        <liferay-ui:message key="area.view.annadir"/>
                    </a>
                </div>
            </div>
        </div>

        <div class="prv-search prv-form">
            <div class="row align-items-start mb-4">

                <%-- TEXTO --%>
               <div class="col-lg-3 col-md-6 col-12">
                    <label for="inpBuscarCajon"> <liferay-ui:message key="area.view.buscar" /></label>
                    <input type="text" name="search" id="search" class="clsSelector2Width filter-item" />
                </div>

                <%-- ACTIVA --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <fieldset>
                        <legend><liferay-ui:message key="area.view.activa"/></legend>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchActiva" id="inpSearchActiva1" value="0" type="radio" checked />
                                <label class="form-check-label" for="inpSearchActiva1"><liferay-ui:message key="area.view.activa.si" /></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchActiva" id="inpSearchActiva2" value="1" type="radio" />
                                <label class="form-check-label" for="inpSearchActiva2"><liferay-ui:message key="area.view.activa.no" /></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchActiva" id="inpSearchActiva3" value="2" type="radio" />
                                <label class="form-check-label" for="inpSearchActiva3"><liferay-ui:message key="area.view.activa.todas" /></label>
                            </div>
                        </div>
                    </fieldset>
                </div>

            </div>
        </div>

        <div class="row">
            <div class="col">
                <div id="spnCargando" class="loading-animation"></div>
                <table id="tabla_areas" class="display" style="width:100%; display:none;">
                    <thead>
                        <tr>
                            <th><liferay-ui:message key="area.view.area"/></th>
                            <th><liferay-ui:message key="area.view.nHombre"/></th>
                            <th><liferay-ui:message key="area.view.nMujeres"/></th>
                            <th><liferay-ui:message key="area.view.total"/></th>
                            <th><liferay-ui:message key="area.view.baja"/></th>
                            <th></th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>

    </div>
</main>

<portlet:resourceURL id="buscarAreas" var="buscarAreasURL"/>
<script>

    var tabla = null;
    $(document).ready( function () {
        tabla = $('#tabla_areas').on('preXhr.dt', function ( e, settings, data ) {
                            $("#spnCargando").hide();
                            $("#tabla_areas").show();
                }).DataTable({
                    dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
                    pageLength: 20,
                    "ajax": {
                        "url": "<%= buscarAreasURL %>",
                        "data" : {
                            "<%= PlanIgualdadAreaWebPortletKeys.COMPID_PARAM %>" : "${compId}",
                            "<%= PlanIgualdadAreaWebPortletKeys.VERSIONID_PARAM %>" : "${versionId}"
                        },
                        "dataSrc": function ( json ) {
                            return json.data;
                        }
                    },
                    "columns": [
                        {"data" : "area"},
                        {"data" : "nHombres"},
                        {"data" : "nMujeres"},
                        {"data" : "nTotal"},
                        {"data" : "baja"},
                        {"data" : "areaId"},
                        {"data" : "baja"}
                    ],
                    "columnDefs": [
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
                              "orderable": false,
                              "render": function (data, type, row) {
                                            if(data == null || data == undefined || data == "") return "";
                                            var url = "<%= areaURL %>".replace("AREA_ID", data);
                                            return  '<a class="ico-acciones-tabla" href="' + url + '">' +
                                                        '<span class="icon-pencil"></span>' +
                                                    '</a>';
                                        }
                        },
                        {
                              "targets"  : [6],
                              "orderable": false,
                              "visible": false,
                              "render":  function (data, type, row) {
                                              if(data == null || data == undefined || data == "") return "";
                                              return  new Date(data).getTime();
                                          }
                        }
                    ],
                    buttons: [
                        'csv', 'excel', 'print', 'pdf'
                    ],
                    "initComplete": function(settings, json) {
                        $("#spnCargando").hide();
                        $("#tabla_areas").show();
                        searchChange();
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

            var text = $('#search').val().toLowerCase();
            var activa = $('input:radio[name=inpSearchActiva]:checked').val();

            var cond = 0;
            var numCond = 0;

            if (text != "") {
                numCond++;
                if ((data[0].toLowerCase().match(text) ||
                    data[0].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) || 
                    (data[1].toLowerCase().match(text) ||
                    data[1].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                    (data[2].toLowerCase().match(text) ||
                    data[2].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) || 
                    (data[3].toLowerCase().match(text) || 
                    data[3].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                    (data[4].toLowerCase().match(text) ||
                    data[4].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text))) cond++;
            }

            if (activa != "2") {
                numCond++;
                if (activa == "0" && (data[6].trim().match('^$') || data[6] >= new Date().getTime())) cond++;
                if (activa == "1" && (!data[6].trim().match('^$') && data[6] < new Date().getTime())) cond++;
            }

            if(numCond == cond) return true;
            return false;

        });

        function searchChange() {
            $('.filter-item').on('change keyup', function(){
                tabla.draw();
            });
        }

    });

</script>