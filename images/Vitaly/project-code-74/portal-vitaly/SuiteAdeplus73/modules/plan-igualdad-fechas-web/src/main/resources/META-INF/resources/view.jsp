<%@ include file="/init.jsp" %>

<%

ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
Calendar fecha = Calendar.getInstance();

Long compId = (Long) request.getAttribute(PlanIgualdadFechasWebPortletKeys.COMPID_PARAM);
Long versionId = (Long) request.getAttribute(PlanIgualdadFechasWebPortletKeys.VERSIONID_PARAM);

%>

<main class="content">
    <div class="formulario">

        <div class="title-group">
            <div class="row mb-2">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="hitos.view.title"/></h2>
                </div>
            </div>
        </div>

        <div class="prv-search prv-form">
            <div class="row align-items-start mb-4">

                <%-- EJECUTADOS --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <fieldset>
                        <legend><liferay-ui:message key="hitos.view.ejecutados"/></legend>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchEjecutado" id="inpSearchEjecutado1" value="0" type="radio" onclick="tabla.draw()" />
                                <label class="form-check-label" for="inpSearchEjecutado1"><liferay-ui:message key="hitos.view.ejecutados.si" /></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchEjecutado" id="inpSearchEjecutado2" value="1" type="radio" onclick="tabla.draw()" />
                                <label class="form-check-label" for="inpSearchEjecutado2"><liferay-ui:message key="hitos.view.ejecutados.no" /></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchEjecutado" id="inpSearchEjecutado3" value="2" type="radio" onclick="tabla.draw()" checked />
                                <label class="form-check-label" for="inpSearchEjecutado3"><liferay-ui:message key="hitos.view.ejecutados.todos" /></label>
                            </div>
                        </div>
                    </fieldset>
                </div>

            </div>
        </div>

        <div class="row">
            <div class="col">
                <div id="spnCargando" class="loading-animation"></div>
                <table id="tabla_hitos" class="display" style="width:100%; display:none;">
                    <thead>
                        <tr>
                            <th><liferay-ui:message key="hitos.view.nombre"/></th>
                            <th><liferay-ui:message key="hitos.view.fecha"/></th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>

    </div>
</main>

<portlet:resourceURL id="buscarHitos" var="buscarHitosURL"/>
<script>

    var tabla = null;
    $(document).ready( function () {
        tabla = $('#tabla_hitos').on('preXhr.dt', function ( e, settings, data ) {
                    $("#spnCargando").hide();
                    $("#tabla_hitos").show();
                }).DataTable({
                    dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
                    pageLength: 20,
                    "ajax": {
                        "url": "<%= buscarHitosURL %>",
                        "data" : {
                            "<%= PlanIgualdadFechasWebPortletKeys.COMPID_PARAM %>" : "<%= compId %>",
                            "<%= PlanIgualdadFechasWebPortletKeys.VERSIONID_PARAM %>" : "<%= versionId %>"
                        },
                        "dataSrc": function ( json ) {
                            return json.data;
                        }
                    },
                    "columns": [
                        {"data" : "nombre"},
                        {"data" : "fecha"}
                    ],
                    "columnDefs": [
                        {
                              "targets"  : [0],
                              "orderable": true,
                              "render": function (data, type, row) {
                                            return  '<span class="text-uppercase">' + data +  '</span>';
                                        }
                        },
                        {
                              "targets"  : [1],
                              "orderable": true,
                              "render": function (data, type, row) {
                                            if(data == null || data == undefined || data == "") return "";
                                            var fecha = new Date(data);
                                            return  '<span style="display : none;">' + fecha.toLocaleDateString('en-GB').split('/').reverse().join('') +  '</span>' +
                                                    '<span>' + fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + '</span>';
                                        }
                        }
                    ],
                    buttons: [
                        'csv', 'excel', 'print', 'pdf'
                    ],
                    "order": [],
                    "initComplete": function(settings, json) {
                        $("#spnCargando").hide();
                        $("#tabla_hitos").show();
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

            var ejecutada = $('input:radio[name=inpSearchEjecutado]:checked').val();

            var cond = 0;
            var numCond = 0;

            if (ejecutada != "2") {
                numCond++;
                if (ejecutada == "0" && (!data[3].trim().match('^$') && data[3] < new Date().getTime())) cond++;
                if (ejecutada == "1" && (data[3].trim().match('^$') || data[3] >= new Date().getTime())) cond++;
            }

            if(numCond == cond) return true;
            return false;

        });
        
    });

</script>