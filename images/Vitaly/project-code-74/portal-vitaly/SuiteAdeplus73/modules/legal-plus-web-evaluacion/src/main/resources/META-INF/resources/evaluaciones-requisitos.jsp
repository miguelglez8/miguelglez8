<%@ page import="com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion" %>
<%@ page import="com.legalplus.liferay.portlet.web.evaluacion.enums.TipoNormativa" %>
<%@ page import="com.legalplus.liferay.portlet.web.evaluacion.enums.FamiliaNormativa" %>
<%@ page import="com.legalplus.liferay.portlet.web.evaluacion.enums.AmbitoAplicacion" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.AyuntamientoLocalServiceUtil" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.CCAALocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ResourceBundle" %>

<%@ include file="/init.jsp" %>

<%

ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

Legislacion legislacion = (Legislacion) request.getAttribute(WebEvaluacionPortletKeys.LEGISLACION_PARAM);
String compId = (String) request.getAttribute(WebEvaluacionPortletKeys.COMP_PARAM);

String ambito = AmbitoAplicacion.getAmbito(String.valueOf(legislacion.getAmbito()), bundle);
String tipo = TipoNormativa.getNormativa(String.valueOf(legislacion.getTipo()), bundle);
String familias = FamiliaNormativa.getFamilias(legislacion.getFamilia(), bundle);
String ccaa = "";
String municipioLegislacion = "";
if(legislacion.getCcaa()>0){
    ccaa=CCAALocalServiceUtil.getCCAA(legislacion.getCcaa()).getNombre();
    if(legislacion.getAyuntamiento()>0){
        municipioLegislacion=AyuntamientoLocalServiceUtil.findByAyuntamiento(legislacion.getAyuntamiento()).getNombre();
    }
}
String format = LanguageUtil.get(bundle, "legislacion.view.dateFormat");
SimpleDateFormat sdf = new SimpleDateFormat(format);

String publicacion = sdf.format(legislacion.getPublicacion());
String derogacion = "";
if (Validator.isNotNull(legislacion.getDerogacion())) {
    derogacion = sdf.format(legislacion.getDerogacion());
}

boolean ultimaEval = (boolean) request.getAttribute(WebEvaluacionPortletKeys.ULITMA_EVALUACION_LEGISLACION);

%>

<portlet:renderURL var="evaluacionRequisitoURL">
    <portlet:param name="mvcRenderCommandName" value="goEvaluacionRequisito" />
    <portlet:param name="comp" value="<%= compId %>" />
    <portlet:param name="legislacion" value="<%= legislacion.getLegislacionId() %>" />
    <portlet:param name="requisito" value="REQUISITO_ID" />
    <portlet:param name="version" value="VERSION_ID" />
</portlet:renderURL>

<liferay-portlet:actionURL name="/evaluacion/requisito/save" var="createEvaluacionURL" >
    <portlet:param name="comp" value="<%= compId %>" />
    <portlet:param name="legislacion" value="<%= legislacion.getLegislacionId() %>" />
</liferay-portlet:actionURL>

<liferay-ui:success key="evaluacion-save-success" message="evaluacion.edit.success" />

<main class="content">
    <div class="formulario">

        <div class="title-group mt-3">
            <a href="${backUrl}" class="toBackView">
                <i class="icon-arrow-left"></i>
                <liferay-ui:message key="evaluaciones.view.back"/>
            </a>
        </div>

        <%@ include file="/legislacion.jsp" %>

        <div class="prv-search prv-form">
            <div class="row mb-3">
                <div class="col-md-6 col-12">
                     <h5><liferay-ui:message key="evaluacion.view.title"/></h5>
                </div>
            </div>
            <div class="row align-items-start mb-4">

                <%-- LEGISLACION --%>
                <div class="col-lg-12 col-md-12 col-12">
                    <table id="table_legislacion" class="display">
                        <thead>
                            <tr>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.fechaEvaluacion"/></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.resultado"/></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.usuario"/></th>
                            </tr>
                            </thead>
                    </table>
                </div>

            </div>
        </div>

        <div class="prv-search prv-form">

            <div class="row mb-3">
                <div class="col-md-12 col-12">
                     <h5><liferay-ui:message key="evaluacion.view.requisitos"/></h5>
                </div>
                <div class="col-md-12 col-12">
                    <a href="${ayudaUrl}" class="text-ayuda ">
                        <liferay-ui:message key="evaluacion.view.ayuda.requisito"/>
                    </a>
                </div>
            </div>
            <div class="row align-items-start mb-4">
                <% String disabledTitle = ultimaEval ? "" : LanguageUtil.get(bundle, "evaluacion.view.evaluacion.requisito.info"); %>
                <div class="col-lg-3 col-md-6 col-12" title="<%= disabledTitle %>">
                    <a class="btn btn-primary <%= ultimaEval ? "" : "disabled" %>" href="<%= createEvaluacionURL %>">
                        <liferay-ui:message key="evaluacion.view.nueva.requisito"/>
                    </a>
                </div>
            </div>
            <div class="row align-items-start mb-4">

                <%-- VERSION --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <label for="version"><liferay-ui:message key="evaluacion.view.version"/></label>
                    <select id="version" name="version" class="filter-item">
                        <option value=""></option>
                    </select>
                </div>

                <%-- EVALUAR --%>
                <div class="col-lg-3 col-md-6 col-12 mt-4">
                    <fieldset>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="evaluarRequisito" id="evaluarRequisito1" value="" type="radio" checked/>
                                <label class="form-check-label" for="evaluarRequisito1"><liferay-ui:message key="calendario.view.estado.todos"/></abel>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="evaluarRequisito" id="evaluarRequisito2" value="eval" type="radio" />
                                <label class="form-check-label" for="evaluarRequisito2">Ver requisitos a evaluar</label>
                            </div>
                        </div>
                    </fieldset>
                </div>

                <%-- PROXIMAS EVALUACIONES --%>
                <div class="col-lg-3 col-md-6 col-12 mt-4">
                    <fieldset>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input type="checkbox" id="inpSearchProximas" name="inpSearchProximas" class="form-check-input filter-item" checked />
                                <label for="inpSearchProximas" class="form-check-label"><liferay-ui:message key="calendario.view.proximas" /></label>
                            </div>
                        </div>
                    </fieldset>
                </div>

            </div>
            <div class="row align-items-start mb-4">

                <%-- REQUISITOS --%>
                <div class="col-lg-12 col-md-12 col-12">
                    <table id="table_requisitos" class="display">
                        <thead>
                            <tr>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.requisito"/></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.version"/></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.fechaEvaluacion"/></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.proxEvaluacion"/></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.cumplimiento"/></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.usuario"/></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.observaciones"/></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.adjunto"/></th>
                                <th class="no-sort"></th>
                            </tr>
                            </thead>
                    </table>
                </div>

            </div>

        </div>

    </div>
</main>

<portlet:resourceURL id="descargarDocumento" var="descargarDocumentoURL">
    <portlet:param name="id" value="ADJUNTO_ID" />
</portlet:resourceURL>
<portlet:resourceURL id="buscarEvaluacionesRequisitos" var="buscarEvaluacionesRequisitosURL"/>
<portlet:resourceURL id="buscarEvaluacionesLegislacion" var="buscarEvaluacionesLegislacionURL"/>
<script>
 var jsonData = null;
$(document).ready( function () {
    var buttonCommonReq = {
        exportOptions: {
            format: {
                body: function ( data, row, column, node ) {
                    // Strip $ from salary column to make it numeric
                    if(column==0){
                         return jsonData[row].descripcion;
                    }else if(column==2){
                        return node.childNodes[1].textContent ;
                    }
                    else{
                        return data
                    }
                }
            }
        }
    };

    var buttonCommon = {
        exportOptions: {
            format: {
                body: function ( data, row, column, node ) {
                    // Strip $ from salary column to make it numeric
                    if(column==0){
                        return node.childNodes[1].textContent ;
                    }
                    else{
                        return data
                    }
                }
            }
        }
    };
    <%-- TABLA LEGISLACION --%>
    var tabla_legislacion = $('#table_legislacion').on('preXhr.dt', function ( e, settings, data ) {
        $("#spnCargando").hide();
        $("#table_legislacion").show();
    }).DataTable( {
        dom: 'Brtip',
        pageLength: 20,
        "ajax": {
            "url": "<%=buscarEvaluacionesLegislacionURL%>",
            "data" : {
                "comp" : "<%= compId %>",
                "legislacion": "<%= legislacion.getLegislacionId() %>"
            },
            "dataSrc": function ( json ) {
                return json.data;
            }
        },
        "columns": [
            {"data" : "fecha"},
            {"data" : "cumplimiento"},
            {"data" : "usuario"},
        ],
        "columnDefs": [
            {
                  "targets"  : [0],
                  "orderable": true,
                  "render": function (data, type, row) {
                                if(data == null || data == undefined || data == "") return '<span style="display : none;">21000101</span>';
                                var fecha = new Date(data);
                                return  '<span style="display : none;">' + fecha.toLocaleDateString('en-GB').split('/').reverse().join('') +  '</span>' +
                                        '<span>' + fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + '</span>';
                            }
            },
            {
                  "targets"  : [1],
                  "orderable": true,
                  "render": function (data, type, row) {
                                if (data + "" === "0") return "<liferay-ui:message key="evaluacion.view.si"/>";
                                if (data + "" === "1") return "<liferay-ui:message key="evaluacion.view.no"/>";
                                if (data + "" === "2") return "<liferay-ui:message key="evaluacion.view.noProcede"/>";
                                return data;
                            }
            }
        ],
        buttons: [
             $.extend( true, {}, buttonCommon, {
                extend: 'csvHtml5'
             } ),
             $.extend( true, {}, buttonCommon, {
                extend: 'excelHtml5'
             } ),
             $.extend( true, {}, buttonCommon, {
                extend: 'print'
             } ),
             $.extend( true, {}, buttonCommon, {
                extend: 'pdfHtml5'
             } )
        ],
        "initComplete": function(settings, json) {
            $("#spnCargando").hide();
            $("#table_legislacion").show();
        },
        "order": [[0,"desc"]],
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
    });

    <%-- TABLA REQUISITOS --%>
    var tabla_requisitos = $('#table_requisitos').on('preXhr.dt', function ( e, settings, data ) {
        $("#spnCargando").hide();
        $("#tabla_requisitos").show();
    }).DataTable( {
        dom: 'Brtip',
        pageLength: 20,
        "ajax": {
            "url": "<%=buscarEvaluacionesRequisitosURL%>",
            "data" : {
                "comp" : "<%= compId %>",
                "legislacion": "<%= legislacion.getLegislacionId() %>"
            },
            "dataSrc": function ( json ) {
                jsonData = json.data;
                return json.data;
            }
        },
        "columns": [
            {"data" : "descripcion"},
            {"data" : "version"},
            {"data" : "fecha"},
            {"data" : "prox"},
            {"data" : "cumplimiento"},
            {"data" : "usuario"},
            {"data" : "observaciones"},
            {"data" : "adjunto"},
            {"data" : "requisitoId"}
        ],
        "columnDefs": [
            {
                "targets"  : [0],
                "orderable": true,
                "render": function (data, type, row) {
                    var maxTexto = 50;
                    if(data.length > maxTexto){
                        return data.substring(0, maxTexto) + "...";
                    }
                    return data;
                }
            },
            {
                  "targets"  : [2],
                  "orderable": true,
                  "render": function (data, type, row) {
                                if(data == null || data == undefined || data == "") return '<span style="display : none;">21000101</span>';
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
                                if (data + "" === "0") return "<liferay-ui:message key="evaluacion.view.si"/>";
                                if (data + "" === "1") return "<liferay-ui:message key="evaluacion.view.no"/>";
                                if (data + "" === "2") return "<liferay-ui:message key="evaluacion.view.noProcede"/>";
                                return data;
                            }
            },
            {
                  "targets"  : [7],
                  "orderable": false,
                  "render": function (data, type, row) {
                                if(data == null || data == undefined || data == "") return "";
                                var url = "<%=descargarDocumentoURL%>".replace("ADJUNTO_ID", data);
                                return  '<a class="ico-acciones-tabla file-download" href="' + url + '">' +
                                            '<span class="icon-download"></span>' +
                                        '</a>';
                            }
            },
            {
                  "targets"  : [8],
                  "orderable": false,
                  "render": function (data, type, row) {
                                if(row.fecha != null && row.fecha != undefined && row.fecha != "" &&
                                    new Date(row.fecha).getTime() < new Date().setFullYear(new Date().getFullYear() - 1)) {

                                    return "";
                                } else {
                                    var url = "<%= evaluacionRequisitoURL %>".replace("VERSION_ID", row.version).replace("REQUISITO_ID", data);
                                    return '<a class="ico-acciones-tabla" title="<liferay-ui:message key="evaluacion.view.hover.title"/>"  href="' + url + '" >' +
                                               '<span class="icon-pencil"></span>' +
                                           '</a>';
                                }
                            }
            }
        ],
        buttons: [
            $.extend( true, {}, buttonCommonReq, {
               extend: 'csvHtml5'
            } ),
            $.extend( true, {}, buttonCommonReq, {
               extend: 'excelHtml5'
            } ),
            $.extend( true, {}, buttonCommonReq, {
               extend: 'print'
            } ),
            $.extend( true, {}, buttonCommonReq, {
               extend: 'pdfHtml5'
            } )
        ],
        "initComplete": function(settings, json) {
            $("#spnCargando").hide();
            $("#tabla_requisitos").show();
            filterEvalRequisitos();
            createVersionSelect();
        },
        "order": [[2,"desc"]],
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
    });

    $.fn.dataTable.ext.search.push(function( settings, data, dataIndex ) {

        var cond = 0;
        var numCond = 0;

        if (settings.sTableId == "table_requisitos" ) {

            var cumplimiento = $('input:radio[name=evaluarRequisito]:checked').val();
            var proximas = $('#inpSearchProximas').is(':checked');

            var version = $("#version option:selected").val();
            var versionVal = $("#version").val();

            var arrDate = null;
            var dateFilterCol = null;

            if (cumplimiento != "") {
                numCond++;
                arrDate = data[9].split("/");
                dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                if ( (data[4].trim().match('^$') && dateFilterCol.getTime() < new Date().getTime())
                        || (data[9].trim().match('^$') && data[1] == "1") ) cond++;
            }

            if (proximas) {
                numCond++;
                if (data[3].trim().match('^$') && data[4].trim().match('^$')) cond++
            }

            if(versionVal != ""){
                numCond++;
                if(data[1].match(version)) cond++;
            }

        }

        if(numCond == cond) return true;
        return false;

    });

    function filterEvalRequisitos() {
        $('.filter-item').on('change', function(){
            tabla_requisitos.draw();
        });
    }

    function createVersionSelect() {
        tabla_requisitos.column(1).data().unique().sort().each(function (e) {
            $("#version").append(new Option(e, e, false, false));
        });
     }

});

</script>