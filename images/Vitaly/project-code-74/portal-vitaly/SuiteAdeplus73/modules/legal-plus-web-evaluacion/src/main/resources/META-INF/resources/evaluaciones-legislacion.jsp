<%@ page import="com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion" %>
<%@ page import="com.legalplus.liferay.portlet.web.evaluacion.enums.TipoNormativa" %>
<%@ page import="com.legalplus.liferay.portlet.web.evaluacion.enums.FamiliaNormativa" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.persistence.ProvinciaPK" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.AyuntamientoLocalServiceUtil" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.service.CCAALocalServiceUtil" %>

<%@ page import="com.legalplus.liferay.portlet.web.evaluacion.enums.AmbitoAplicacion" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ResourceBundle" %>

<%@ include file="/init.jsp" %>

<%

ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

Legislacion legislacion = (Legislacion) request.getAttribute(WebEvaluacionPortletKeys.LEGISLACION_PARAM);
String compId = (String) request.getAttribute(WebEvaluacionPortletKeys.COMP_PARAM);
EvalLegislacion ultimaLegislacion = (EvalLegislacion) request.getAttribute(WebEvaluacionPortletKeys.ULITMA_EVALUACION_LEGISLACION);
EvalLegislacion ultimaLegislacionCumplimentada = (EvalLegislacion) request.getAttribute(WebEvaluacionPortletKeys.ULITMA_EVALUACION_LEGISLACION_CUMPLIMENTADA);

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

%>

<portlet:renderURL var="evaluacionLegislacionURL">
    <portlet:param name="mvcRenderCommandName" value="goEvaluacionLegislacion" />
    <portlet:param name="comp" value="<%= compId %>" />
    <portlet:param name="legislacion" value="<%= legislacion.getLegislacionId() %>" />
    <portlet:param name="version" value="VERSION_ID" />
</portlet:renderURL>

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
                <div class="col-md-12 col-12">
                    <h5><liferay-ui:message key="evaluacion.view.title" /></h5>
                </div>
                <div class="col-md-12 col-12">
                    <a href="${ayudaUrl}" class="text-ayuda ">
                        <liferay-ui:message key="evaluacion.view.ayuda.legislacion"/>
                    </a>
                </div>
            </div>
            <div class="row align-items-start mb-4">

                <%-- NUEVA EVALUACION --%>
                <% String addEvaluacionLegislacion =  evaluacionLegislacionURL.replace("VERSION_ID", ""); %>
                <% String disabledEvaluacionLegislacion = Validator.isNotNull(ultimaLegislacion) &&  ultimaLegislacion.getCumplimiento() == -1 ? "disabled" : ""; %>
                <% String disabledTitle = Validator.isBlank(disabledEvaluacionLegislacion) ? "" : LanguageUtil.get(bundle, "evaluacion.view.evaluacion.legislacion.info"); %>
                <div class="col-lg-3 col-md-6 col-12" title="<%= disabledTitle %>" >
                    <a class="btn btn-primary <%= disabledEvaluacionLegislacion %>" href="<%= addEvaluacionLegislacion %>">
                        <liferay-ui:message key="evaluacion.view.nueva"/>
                    </a>
                </div>

            </div>
            <div class="row align-items-start mb-4">

                <%-- ESTADO --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <legend>
                        <liferay-ui:message key="evaluacion.view.estado" />
                        <span class="contract-info text-secondary" data-toggle="tooltip" title="<liferay-ui:message key="evaluacion.view.estado.info"/>">
                            <i class="icon-info-sign"></i>
                        </span>
                    </legend>
                    <fieldset>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="cumplimiento" id="cumplimiento1"
                                    value="<liferay-ui:message key="evaluacion.view.si"/>" type="radio" class="inpSearchActive" disabled="true"
                                    <%= Validator.isNotNull(ultimaLegislacionCumplimentada) && (ultimaLegislacionCumplimentada.getCumplimiento() == 0)  ? "checked" : "" %> />
                                <label class="form-check-label" for="cumplimiento1"><liferay-ui:message key="evaluacion.view.si"/></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="cumplimiento" id="cumplimiento2" disabled="true"
                                    value="<liferay-ui:message key="evaluacion.view.no"/>" type="radio" class="inpSearchActive"
                                    <%= Validator.isNotNull(ultimaLegislacionCumplimentada) && (ultimaLegislacionCumplimentada.getCumplimiento() == 1)  ? "checked" : "" %> />
                                <label class="form-check-label" for="cumplimiento2"><liferay-ui:message key="evaluacion.view.no"/></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="cumplimiento" id="cumplimiento3" disabled="true"
                                    value="<liferay-ui:message key="evaluacion.view.noProcede"/>" type="radio" class="inpSearchActive"
                                    <%= Validator.isNotNull(ultimaLegislacionCumplimentada) && (ultimaLegislacionCumplimentada.getCumplimiento() == 2)  ? "checked" : "" %> />
                                <label class="form-check-label" for="cumplimiento3"><liferay-ui:message key="evaluacion.view.noProcede"/></label>
                            </div>
                        </div>
                    </fieldset>
                </div>

                <%-- ACTIVO --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <fieldset>
                        <legend><liferay-ui:message key="calendario.view.estado"/></legend>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input inpSearchActive filter-item" name="inpSearchEstado" id="inpSearchEstado1" value="" type="radio" checked/>
                                <label class="form-check-label" for="inpSearchEstado1"><liferay-ui:message key="calendario.view.estado.todas" /></abel>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input inpSearchActive filter-item" name="inpSearchEstado" id="inpSearchEstado2" value="eval" type="radio" />
                                <label class="form-check-label" for="inpSearchEstado2"><liferay-ui:message key="calendario.view.estado.evaluar" /></label>
                            </div>
                        </div>
                    </fieldset>
                </div>

                <%-- CUMPLIMIENTO --%>
                <div class="col-lg-4 col-md-6 col-12">
                    <legend><liferay-ui:message key="evaluacion.view.cumplimiento.filtro"/></legend>
                    <fieldset>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento0"
                                    value="" type="radio" class="inpSearchActive" checked/>
                                <label class="form-check-label" for="inpSearchCumplimiento0"><liferay-ui:message key="evaluacion.view.todos"/></abel>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento1"
                                    value="<liferay-ui:message key="evaluacion.view.si"/>" type="radio" class="inpSearchActive" />
                                <label class="form-check-label" for="inpSearchCumplimiento1"><liferay-ui:message key="evaluacion.view.si"/></abel>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento2"
                                    value="<liferay-ui:message key="evaluacion.view.no"/>" type="radio" class="inpSearchActive" />
                                <label class="form-check-label" for="inpSearchCumplimiento2"><liferay-ui:message key="evaluacion.view.no"/></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento3"
                                    value="<liferay-ui:message key="evaluacion.view.noProcede"/>" type="radio" class="inpSearchActive" />
                                <label class="form-check-label" for="inpSearchCumplimiento3"><liferay-ui:message key="evaluacion.view.noProcede"/></label>
                            </div>
                        </div>
                    </fieldset>
                </div>

            </div>
            <div class="row align-items-start mb-4">

                <%-- EVALUACIONES --%>
                <div class="col-lg-12 col-md-12 col-12">
                    <table id="table_evaluaciones" class="display">
                        <thead>
                            <tr>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.fechaEvaluacion" /></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.proxEvaluacion" /></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.cumplimiento" /></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.usuario" /></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.observaciones" /></th>
                                <th class="no-sort"><liferay-ui:message key="evaluacion.view.adjunto" /></th>
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
<portlet:resourceURL id="buscarEvaluacionesLegislacion" var="buscarEvaluacionesLegislacionURL"/>
<script>
$(document).ready( function () {
        var buttonCommon = {
            exportOptions: {
                format: {
                    body: function ( data, row, column, node ) {
                        // Strip $ from salary column to make it numeric
                        if(column==8){
                            return ;
                        }else if(column==5){
                            var gestoresImportados='';
                            for(var y=2;y<node.childNodes.length;y+=4){
                                if(!gestoresImportados.includes(node.childNodes[y].textContent)){
                                    gestoresImportados+=node.childNodes[y].textContent+" ";
                                }
                            }
                            return gestoresImportados;
                        }else if(column==0){
                            return node.childNodes[1].textContent ;
                        }
                        else{
                            if(node.childNodes[2]!==undefined){
                                return node.childNodes[2].textContent;
                            }else{
                                if(node.childNodes[1]!==undefined){
                                    return node.childNodes[1].textContent;
                                }else{
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        };
        <%-- TABLA LEGISLACIONES --%>
        var tabla = $('#table_evaluaciones').on('preXhr.dt', function ( e, settings, data ) {
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
                {"data" : "prox"},
                {"data" : "cumplimiento"},
                {"data" : "usuario"},
                {"data" : "observaciones"},
                {"data" : "adjunto"},
                {"data" : "version"},
                {"data" : "fecha"}
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
                                    if (data + "" === "0") return "<liferay-ui:message key="evaluacion.view.si"/>";
                                    if (data + "" === "1") return "<liferay-ui:message key="evaluacion.view.no"/>";
                                    if (data + "" === "2") return "<liferay-ui:message key="evaluacion.view.noProcede"/>";
                                    return data;
                                }
                },
                {
                      "targets"  : [5],
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
                      "targets"  : [6],
                      "orderable": false,
                      "render": function (data, type, row) {
                                   if(row.fecha != null && row.fecha != undefined && row.fecha != "" &&
                                        new Date(row.fecha).getTime() < new Date().setFullYear(new Date().getFullYear() - 1)) {

                                        return "";
                                   } else {
                                        var url = "<%= evaluacionLegislacionURL %>".replace("VERSION_ID", data);
                                        return '<a class="ico-acciones-tabla" title="<liferay-ui:message key="evaluacion.view.hover.title"/>" href="' + url + '" >' +
                                                   '<span class="icon-pencil"></span>' +
                                               '</a>';
                                   }
                                }
                },
                {
                      "targets"  : [7],
                      "orderable": false,
                      "visible": false,
                      "render":  function (data, type, row) {
                                    if(data == null || data == undefined || data == "") return "";
                                    var fecha = new Date(data);
                                    return  fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' });
                                 }
                }
            ],
            buttons: [
                'csv', 'excel', 'print', 'pdf'
            ],
            "initComplete": function(settings, json) {
                filterEvalLegislacion();
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

        $.fn.dataTable.ext.search.push(function( settings, data, dataIndex ) {

            var estado = $('input:radio[name=inpSearchEstado]:checked').val();
            var cumplimiento = $('input:radio[name=inpSearchCumplimiento]:checked').val();

            var cond = 0;
            var numCond = 0;

            var arrDate = null;
            var dateFilterCol = null;

            if (estado != "") {
                numCond++;
                arrDate = data[7].split("/");
                dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                if (data[2].trim().match('^$') && dateFilterCol.getTime() < new Date().getTime()) cond++;
            }

            if (cumplimiento != "") {
                numCond++;
                if (data[2].trim().match(cumplimiento)) cond++;
            }

            if(numCond == cond) return true;
            return false;

        });

        function filterEvalLegislacion() {
            $('.filter-item').on('change', function(){
                tabla.draw();
            });
        }

    });

</script>