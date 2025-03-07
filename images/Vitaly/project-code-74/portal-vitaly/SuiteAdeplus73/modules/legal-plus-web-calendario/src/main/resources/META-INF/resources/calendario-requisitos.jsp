<%@ page import="com.legalplus.liferay.portlet.web.calendario.constants.WebCalendarioPortletKeys" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.CCAA" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento" %>
<%@ page import="com.legalplus.liferay.portlet.web.calendario.enums.FamiliaNormativa" %>
<%@ page import="com.legalplus.liferay.portlet.web.calendario.enums.AmbitoAplicacion" %>
<%@ page import="com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>

<%@ include file="/init.jsp" %>

<%

ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

List<FamiliaNormativa> familiaList = (List<FamiliaNormativa>) request.getAttribute(WebCalendarioPortletKeys.FAMILIA_LIST);
List<CCAA> ccaaList = (List<CCAA>) request.getAttribute(WebCalendarioPortletKeys.CCAA_LIST);
List<Ayuntamiento> aytoList = (List<Ayuntamiento>) request.getAttribute(WebCalendarioPortletKeys.AYTO_LIST);
String compId = (String) request.getAttribute(WebCalendarioPortletKeys.COMPANY_ID);

boolean hasRole = LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                    || LegalplusRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser());

String eval = (String) request.getAttribute(WebCalendarioPortletKeys.EVAL_PARAM);
String startDate = (String) request.getAttribute(WebCalendarioPortletKeys.START_DATE_PARAM);
String endDate = (String) request.getAttribute(WebCalendarioPortletKeys.END_DATE_PARAM);

Calendar startCalendar = Calendar.getInstance();
Calendar endCalendar = Calendar.getInstance();

if (Validator.isNotNull(startDate)) {
    startCalendar.setTimeInMillis(Long.parseLong(startDate));
}

if (Validator.isNotNull(endDate)) {
    endCalendar.setTimeInMillis(Long.parseLong(endDate));
}

%>

<c:if test="<%= hasRole %>">
    <%@include file="menu.jsp" %>
</c:if>

<c:if test="<%= !hasRole %>">
    <div class="title-group">
        <div class="row">
            <div class="col-md-6 col-12">
                <h2><liferay-ui:message key="company.calendario.title.tab"/></h2>
            </div>
        </div>
    </div>
</c:if>

<main class="content">
    <div class="formulario">

        <div class="prv-search prv-form">

            <div class="row align-items-start mb-4">

                <%-- TEXTO --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <label for="inpBuscarCajon"> <liferay-ui:message key="calendario.view.buscar" /></label>
                    <input type="text" name="search" id="search" class="clsSelector2Width filter-item" />
                </div>

                <%-- EVALUACION --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <fieldset>
                        <legend><liferay-ui:message key="calendario.view.requisitos.evaluar"/></legend>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchEvaluacion" id="inpSearchEvaluacion1" value="" type="radio"
                                    <%= Validator.isNotNull(eval) ? "" : "checked" %> />
                                <label class="form-check-label" for="inpSearchEvaluacion1"><liferay-ui:message key="calendario.view.todas" /></abel>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="inpSearchEvaluacion" id="inpSearchEvaluacion2" value="eval" type="radio"
                                    <%= Validator.isNotNull(eval) ? "checked" : "" %> />
                                <label class="form-check-label" for="inpSearchEvaluacion2"><liferay-ui:message key="calendario.view.evaluar" /></label>
                            </div>
                        </div>
                    </fieldset>
                </div>

                <%-- FECHA --%>
                <div class="col-md-6 col-12 d-flex">
                    <div class="col-md-6 col-12 pl-0">
                        <label for="startDate"><liferay-ui:message key="calendario.view.evaluacionDesde"/></label>
                        <c:choose>
                            <c:when test="<%= Validator.isNotNull(startDate) %>">
                                <liferay-ui:input-date name="startDate" nullable="true"
                                        showDisableCheckbox="false" cssClass="filter-item datepicker"
                                        yearValue="<%=startCalendar.get(Calendar.YEAR)%>"
                                        monthValue="<%=startCalendar.get(Calendar.MONTH)%>"
                                        dayValue="<%=startCalendar.get(Calendar.DAY_OF_MONTH)%>" />
                            </c:when>
                            <c:otherwise>
                                <liferay-ui:input-date name="startDate" nullable="true" cssClass="filter-item datepicker" showDisableCheckbox="false" />
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="col-md-6 col-12 pr-0">
                        <label for="endDate"><liferay-ui:message key="calendario.view.evaluacionHasta"/></label>
                        <c:choose>
                            <c:when test="<%= Validator.isNotNull(endDate) %>">
                                <liferay-ui:input-date name="endDate" nullable="true"
                                        showDisableCheckbox="false" cssClass="filter-item datepicker"
                                        yearValue="<%=endCalendar.get(Calendar.YEAR)%>"
                                        monthValue="<%=endCalendar.get(Calendar.MONTH)%>"
                                        dayValue="<%=endCalendar.get(Calendar.DAY_OF_MONTH)%>" />
                            </c:when>
                            <c:otherwise>
                                <liferay-ui:input-date name="endDate" nullable="true" cssClass="filter-item datepicker" showDisableCheckbox="false" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </div>
            <div class="row align-items-start mb-4">

                <%-- PROXIMAS EVALUACIONES --%>
                <div class="col-lg-3 col-md-6 col-12">
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

            <div class="prv-advanced-search mb-4">
                <a class="prv-advanced-search__trigger" data-toggle="collapse"
                    href="#advanced-search" role="button" aria-expanded="true"
                    aria-controls="collapseExample">
                    <liferay-ui:message key="calendario.view.busquedaAvanzada" />
                </a>
            </div>

            <div class="collapse" id="advanced-search">

                <div class="row align-items-start mb-4">

                    <%-- CUMPLIMIENTO --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <fieldset>
                            <legend><liferay-ui:message key="calendario.view.cumplimiento"/></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento1"
                                            value="0" type="radio" />
                                    <label class="form-check-label" for="inpSearchCumplimiento1"><liferay-ui:message key="calendario.view.si" /></abel>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento2"
                                            value="1" type="radio" />
                                    <label class="form-check-label" for="inpSearchCumplimiento2"><liferay-ui:message key="calendario.view.no" /></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento3"
                                            value="2" type="radio" />
                                    <label class="form-check-label" for="inpSearchCumplimiento3"><liferay-ui:message key="calendario.view.noProcede" /></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento4"
                                            value="" type="radio" checked />
                                    <label class="form-check-label" for="inpSearchCumplimiento4"><liferay-ui:message key="calendario.view.todos" /></label>
                                </div>
                            </div>
                        </fieldset>
                    </div>

                    <%-- ACTIVO --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <fieldset>
                            <legend><liferay-ui:message key="calendario.view.active"/></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input inpSearchActive filter-item" name="inpSearchActive" id="inpSearchActive1" value="0" type="radio" />
                                    <label class="form-check-label" for="inpSearchActive1"><liferay-ui:message key="calendario.view.si" /></abel>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input inpSearchActive filter-item" name="inpSearchActive" id="inpSearchActive2" value="1" type="radio" />
                                    <label class="form-check-label" for="inpSearchActive2"><liferay-ui:message key="calendario.view.no" /></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input inpSearchActive filter-item" name="inpSearchActive" id="inpSearchActive3" value="" type="radio" checked />
                                    <label class="form-check-label" for="inpSearchActive3"><liferay-ui:message key="calendario.view.todos" /></label>
                                </div>
                            </div>
                        </fieldset>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- FAMILIA --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <label for="cmb_ccaa"><liferay-ui:message key="calendario.view.familia"/></label>
                        <select id="cmb_familia" name="<%=WebCalendarioPortletKeys.FAMILIA%>" label=""  class="js-select2-noSearch filter-item">
                            <option value="" ></option>
                            <c:forEach items="<%= familiaList %>" var="normativa">
                                <option value="${normativa.codigo}"><liferay-ui:message key="${normativa.descripcion}"/></option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- AMBITO --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <label for="cmb_ccaa"><liferay-ui:message key="calendario.view.ambito"/></label>
                        <select id="cmb_ambito" name="<%=WebCalendarioPortletKeys.AMBITO%>" label=""  class="js-select2-noSearch filter-item">
                            <option value="" ></option>
                            <c:forEach items="<%= AmbitoAplicacion.values() %>" var="ambito">
                                <option value="${ambito.codigo}"  ><liferay-ui:message key="${ambito.descripcion}"/></option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- CCAA --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <label for="cmb_ccaa"><liferay-ui:message key="calendario.view.ccaa"/></label>
                        <select id="cmb_ccaa" name="<%=WebCalendarioPortletKeys.CCAA%>" label="" class="js-select2 filter-item clsSelector2Width">
                            <option value="" ></option>
                            <c:forEach var="ccaa" items="<%=ccaaList%>">
                                <option value="${ccaa.ccaaId}">${ccaa.nombre}</option>
                             </c:forEach>
                        </select>
                    </div>

                    <%-- AYUNTAMIENTOS --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <label for="cmb_ccaa"><liferay-ui:message key="calendario.view.ayuntamientos"/></label>
                        <select id="cmb_ayuntamientos" name="<%=WebCalendarioPortletKeys.AYUNTAMIENTOS%>" label="" class="js-select2 filter-item clsSelector2Width">
                            <option value="" ></option>
                            <c:forEach var="ayto" items="<%= aytoList %>">
                                <option value="${ayto.ayuntamientoId}">${ayto.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- VERSION --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <label for="cmb_version"><liferay-ui:message key="calendario.view.version"/></label>
                        <select id="cmb_version" name="<%=WebCalendarioPortletKeys.VERSION%>" label=""  class="js-select2-noSearch filter-item">
                            <option value="" ></option>
                        </select>
                    </div>

                    <%-- FECHA --%>
                    <div class="col-md-6 col-12 d-flex">
                        <div class="col-md-6 col-12 pl-0">
                            <label for="proxStartDate"><liferay-ui:message key="calendario.view.proxEvaluacionDesde"/></label>
                            <liferay-ui:input-date name="proxStartDate" nullable="true" cssClass="filter-item datepicker" showDisableCheckbox="false" />
                        </div>

                        <div class="col-md-6 col-12 pr-0">
                            <label for="proxEndDate"><liferay-ui:message key="calendario.view.proxEvaluacionHasta"/></label>
                            <liferay-ui:input-date name="proxEndDate" nullable="true" cssClass="filter-item datepicker" showDisableCheckbox="false" />
                        </div>
                    </div>

                </div>

            </div>

            <div class="row align-items-end">
                <div class="col-md-12 col-12 text-right">
                    <div class="btnClear">
                        <button class="btn btn btn-outline-primary btn-sm btn-primary" type="button" id="filter-cleaner">
                            <liferay-ui:message key="calendario.view.limpiar" />
                        </button>
                    </div>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col">
                <div id="spnCargando" class="loading-animation"></div>
                <table id="tabla_requisitos" class="display" style="width:100%; display:none;">
                    <thead>
                        <tr>
                            <th class="no-sort"><liferay-ui:message key="calendario.view.requisito"/></th>
                            <th class="no-sort"><liferay-ui:message key="calendario.view.version"/></th>
                            <th class="no-sort"><liferay-ui:message key="calendario.view.fechaEvalucion"/></th>
                            <th class="no-sort"><liferay-ui:message key="calendario.view.fechaProxEvalucion"/></th>
                            <th class="no-sort"><liferay-ui:message key="calendario.view.legislacion"/></th>
                            <th class="no-sort"><liferay-ui:message key="calendario.view.familia"/></th>
                            <th class="no-sort"><liferay-ui:message key="calendario.view.ambito"/></th>
                            <th class="no-sort"><liferay-ui:message key="calendario.view.usuario"/></th>
                            <th class="no-sort"><liferay-ui:message key="calendario.view.observaciones"/></th>
                            <th class="no-sort"><liferay-ui:message key="calendario.view.cumplimiento"/></th>
                            <th class="no-sort"></th>
                            <th class="no-sort"></th>
                            <th class="no-sort"></th>
                            <th class="no-sort"></th>
                            <th class="no-sort"></th>
                            <th class="no-sort"></th>
                            <th class="no-sort"></th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>

    </div>
</main>

<portlet:resourceURL id="descargarDocumento" var="descargarDocumentoURL">
    <portlet:param name="id" value="ADJUNTO_ID" />
</portlet:resourceURL>
<portlet:resourceURL id="buscarEvaluacionesRequisito" var="buscarEvaluacionesRequisitoURL"/>
<script>
var jsonData = null;
var tabla = null;
$(document).ready( function () {
        var buttonCommon = {
            exportOptions: {
                format: {
                    body: function ( data, row, column, node ) {
                        if(column>=10){
                            return ;
                        }else if (column==0){
                            return node.childNodes[0].textContent
                        }else if(column==1){
                            return jsonData[row].version;
                        }else if(column==2){
                            if(node.childNodes[1]!==undefined){
                                return node.childNodes[1].textContent ;
                            }else{
                                 return ;
                            }
                        }else if(column==3){
                            if(node.childNodes[1]!==undefined){
                                return node.childNodes[1].textContent ;
                            }else{
                                return ;
                            }
                        }else if(column==4){
                            return node.childNodes[0].textContent
                        }else if(column==5){
                            return node.childNodes[0].textContent
                        }else if(column==6 || column==7 || column==8 || column==9){
                            //return data;
                            if(data.indexOf("span") != -1){
                                return data.substring(0, data.indexOf("<span"));
                            }else{
                                return node.textContent;
                            }

                        }
                    }
                }
            }
        };
     tabla = $('#tabla_requisitos').on('preXhr.dt', function ( e, settings, data ) {
                    $("#spnCargando").hide();
                    $("#tabla_requisitos").show();
                }).DataTable( {
                    dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
                    pageLength: 20,
                    "ajax": {
                        "url": "<%=buscarEvaluacionesRequisitoURL%>",
                        "data" : {
                            "compId" : "<%= compId %>"
                        },
                        "dataSrc": function ( json ) {
                            jsonData = json.data;
                            return json.data;
                        }
                    },
                    "columns": [
                        {"data" : "requisito"},
                        {"data" : "version"},
                        {"data" : "fecha"},
                        {"data" : "prox"},
                        {"data" : "legislacion"},
                        {"data" : "familia"},
                        {"data" : "ambito"},
                        {"data" : "usuario"},
                        {"data" : "observaciones"},
                        {"data" : "cumplimiento"},
                        {"data" : "adjunto"},
                        {"data" : "legislacionId"},
                        {"data" : "ccaa"},
                        {"data" : "ayuntamiento"},
                        {"data" : "fecha"},
                        {"data" : "prox"},
                        {"data" : "activo"},
                        {"data" : "requisitoId"}
                    ],
                    "columnDefs": [
                        {
                              "targets"  : [0],
                              "orderable": true,
                              "render": function (data, type, row) {
                                            return '<span class="substring">' + data + '</span>';
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
                                            return '<span class="substring">' + data + '</span>';
                                        }
                        },
                        {
                              "targets"  : [5],
                              "orderable": true,
                              "render": function (data, type, row) {
                                            if (data == "99;") return '<span class="badge badge-pill badge-info">' +
                                                                            '<liferay-ui:message key="contract.view.familia.todas"/>' +
                                                                      '</span>';
                                            var familias = "";
                                            data.split(";").forEach(function(item) {
                                                familias += '<div class="d-flex flex-column align-items-start">' +
                                                                '<span class="badge badge-pill badge-info">' + $("#cmb_familia option[value='" + item + "']").text() + '</span>' +
                                                            '</div>';
                                            });
                                            return familias;
                                        }
                        },
                        {
                              "targets"  : [6],
                              "orderable": true,
                              "render": function (data, type, row) {
                                            return $("#cmb_ambito option[value='" + data + "']").text();
                                        }
                        },
                        {
                              "targets"  : [9],
                              "orderable": true,
                              "render": function (data, type, row) {
                                            if (data + "" === "0") return 'Si <span class="hide">0</span>';
                                            if (data + "" === "1") return 'No <span class="hide">1</span>';
                                            if (data + "" === "2") return 'No procede <span class="hide">2</span>';
                                            return data;
                                        }
                        },
                        {
                              "targets"  : [10],
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
                              "targets"  : [11],
                              "orderable": false,
                              "render": function (data, type, row) {
                                            if (row.prohibido) {
                                                return  '<span class="ico-acciones-tabla">' +
                                                            '<span class="icon-ban-circle"></span>' +
                                                        '</span>';
                                            } else {
                                                return  '<a class="ico-acciones-tabla" href="${evaluacionUrl}?comp=<%= compId %>&legislacion=' + data +
                                                                                                '&requisito=' + row.requisitoId +
                                                                                                '&version=' + row.version + '">' +
                                                            '<span class="icon-edit"></span>' +
                                                        '</a>';
                                            }
                                        }
                        },
                        {
                              "targets"  : [12],
                              "orderable": false,
                              "visible": false,
                        },
                        {
                              "targets"  : [13],
                              "orderable": false,
                              "visible": false,
                        },
                        {
                              "targets"  : [14],
                              "orderable": false,
                              "visible": false,
                              "render":  function (data, type, row) {
                                              if(data == null || data == undefined || data == "") return "";
                                              var fecha = new Date(data);
                                              return  fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' });
                                          }
                        },
                        {
                              "targets"  : [15],
                              "orderable": false,
                              "visible": false,
                              "render":  function (data, type, row) {
                                              if(data == null || data == undefined || data == "") return "";
                                              var fecha = new Date(data);
                                              return  fecha.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' });
                                          }
                        },
                        {
                              "targets"  : [16],
                              "orderable": false,
                              "visible": false,
                              "render":  function (data, type, row) {
                                              if(data == null || data == undefined || data == "") return "0";
                                              return  "1";
                                          }
                        },
                        {
                              "targets"  : [17],
                              "orderable": false,
                              "visible": false,
                        }
                    ],
                    "order": [[2,"desc"]],
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
                            extend: 'pdfHtml5',
                            orientation: 'landscape'
                        } )
                     ],
                    "initComplete": function(settings, json) {
                        $("#spnCargando").hide();
                        $("#tabla_requisitos").show();
                        searchChange();
                        createVersionSelect();
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
                });

     $.fn.dataTable.ext.search.push(function( settings, data, dataIndex ) {

        var text = $('#search').val().toLowerCase();
        var activo = $('input:radio[name=inpSearchActive]:checked').val();
        var cumplimiento = $('input:radio[name=inpSearchCumplimiento]:checked').val();
        var evaluacion = $('input:radio[name=inpSearchEvaluacion]:checked').val();
        var proximas = $('#inpSearchProximas').is(':checked');

        var familia = $("#cmb_familia option:selected").text().trim();
        var familiaVal = $("#cmb_familia").val();

        var ambito = $("#cmb_ambito option:selected").text().trim();
        var ambitoVal = $("#cmb_ambito").val();

        var ccaa = $("#cmb_ccaa option:selected").val();
        var ccaaVal = $("#cmb_ccaa").val();

        var ayto = $("#cmb_ayuntamientos option:selected").val();
        var aytoVal = $("#cmb_ayuntamientos").val();

        var version = $("#cmb_version option:selected").val();
        var versionVal = $("#cmb_version").val();

        var fechaEvalucionInicio = $("#<portlet:namespace/>startDate").val();
        var fechaEvalucionFin = $("#<portlet:namespace/>endDate").val();

        var fechaProximaInicio = $("#<portlet:namespace/>proxStartDate").val();
        var fechaProximaFin = $("#<portlet:namespace/>proxEndDate").val();

        var cond = 0;
        var numCond = 0;

        if (text != "") {
            numCond++;
            if (data[0].toLowerCase().match(text) || data[4].toLowerCase().match(text) ||
                data[5].toLowerCase().match(text) || data[6].toLowerCase().match(text) ||
                data[7].toLowerCase().match(text) || data[8].toLowerCase().match(text) ||
                data[0].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text) ||
                data[4].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text) ||
                data[5].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text) ||
                data[6].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text) ||
                data[7].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text) ||
                data[8].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)  ) cond++;
        }

        if (activo != "") {
            numCond++;
            if (data[16].trim().match(activo)) cond++;
        }

        if (cumplimiento != "") {
            numCond++;
            if(data[9].trim().match(cumplimiento)) cond++;
        }

        if(familiaVal != ""){
            numCond++;
            var todas = '<liferay-ui:message key="contract.view.familia.todas"/>';
            if(data[5].trim().match(familia) || data[5].trim().match(todas)) cond++;
        }

        if(ambitoVal != ""){
            numCond++;
            if(data[6].trim().match(ambito)) cond++;
        }

        if(ccaaVal != ""){
            numCond++;
            if(data[12].match(ccaa)) cond++;
        }

        if(aytoVal != ""){
            numCond++;
            if(data[13].match(ayto)) cond++;
        }

        if(versionVal != ""){
            numCond++;
            if(data[1].match(version)) cond++;
        }

        if (proximas) {
            numCond++;
            if (data[3].trim().match('^$') && data[9].trim().match('^$')) cond++
        }

        var arrDate = null;
        var dateFilter = null;
        var dateFilterCol = null;

        if (evaluacion != "") {
            numCond++;
            arrDate = data[14].split("/");
            dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
            if( (data[7].trim().match('^$') && dateFilterCol.getTime() < new Date().getTime())
                || (data[2].trim().match('^$') && data[1] == "1") ) cond++
        }

        if(fechaEvalucionInicio.trim() != "" && isValidDate(fechaEvalucionInicio)){
            numCond++;
            arrDate = fechaEvalucionInicio.split("/");
            dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
            arrDate = data[14].split("/");
            dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
            if(dateFilterCol.getTime() >= dateFilter.getTime() ) cond++;
        }

        if(fechaEvalucionFin.trim() != "" && isValidDate(fechaEvalucionFin)){
            numCond++;
            arrDate = fechaEvalucionFin.split("/");
            dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
            arrDate = data[14].split("/");
            dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
            if(dateFilterCol.getTime() <= dateFilter.getTime() ) cond++;
        }

        if(fechaProximaInicio.trim() != "" && isValidDate(fechaProximaInicio)){
            numCond++;
            arrDate = fechaProximaInicio.split("/");
            dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
            arrDate = data[15].split("/");
            dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
            if(dateFilterCol.getTime() >= dateFilter.getTime() ) cond++;
        }

        if(fechaProximaFin.trim() != "" && isValidDate(fechaProximaFin)){
            numCond++;
            arrDate = fechaProximaFin.split("/");
            dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
            arrDate = data[15].split("/");
            dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
            if(dateFilterCol.getTime() <= dateFilter.getTime() ) cond++;
        }

        if(numCond == cond) return true;
        return false;

     });

     $("#filter-cleaner").on("click", function(e) {
         $("#<portlet:namespace/>startDate").val('');
         $("#<portlet:namespace/>endDate").val('');
         $("#<portlet:namespace/>proxStartDate").val('');
         $("#<portlet:namespace/>proxEndDate").val('');
         $("#inpSearchActive3").prop("checked", true);
         $("#inpSearchCumplimiento4").prop("checked", true);
         $("#inpSearchEvaluacion1").prop("checked", true);
         $("#inpSearchProximas2").prop("checked", true);
         $("#cmb_familia").val("").trigger("change");
         $("#cmb_ambito").val("").trigger("change");
         $("#cmb_ccaa").val("").trigger("change");
         $("#cmb_ayuntamientos").val("").trigger("change");
         $("#search").val("").trigger("change");
         tabla.search("").draw() ;
     });

     function isValidDate(_date){
         var reg = /^(0[1-9]|[1-2]\d|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
         if(reg.test(_date)) return true;
         return false;

     }

     function searchChange() {
         $('.filter-item').on('change keyup', function(){
             tabla.draw();
         });
     }

     function createVersionSelect() {
        tabla.column(1).data().unique().sort().each(function (e) {
            $("#cmb_version").append(new Option(e, e, false, false));
        });
     }

     YUI().use('aui-datepicker', function(Y) {
        new Y.DatePicker({
            trigger: '.datepicker',
            mask: '%d/%m/%Y',
            popover : {
                zIndex : 1
            },
            on: {
              selectionChange: function(event) {
                tabla.draw();
              }
            }
        });
     });

});
</script>
