<%@ page import="com.liferay.asset.kernel.model.AssetCategory" %>
<%@ page import="com.liferay.asset.kernel.model.AssetVocabulary" %>
<%@ page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil" %>
<%@ page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.CCAA" %>
<%@ page import="com.legalplus.liferay.portlet.legalplus.manager.model.Ayuntamiento" %>
<%@ page import="com.legalplus.liferay.portlet.web.legislaciones.enums.FamiliaNormativa" %>
<%@ page import="com.legalplus.liferay.portlet.web.legislaciones.enums.AmbitoAplicacion" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.List" %>

<%@ include file="/init.jsp" %>

<%

List<FamiliaNormativa> familiaList = (List<FamiliaNormativa>) request.getAttribute(WebLegislacionesPortletKeys.FAMILIA_LIST);
List<CCAA> ccaaList = (List<CCAA>) request.getAttribute(WebLegislacionesPortletKeys.CCAA_LIST);
List<Ayuntamiento> aytoList = (List<Ayuntamiento>) request.getAttribute(WebLegislacionesPortletKeys.AYTO_LIST);

String compId  = (String) request.getAttribute(WebLegislacionesPortletKeys.COMPANY_ID);
Long licenceId=(Long) request.getAttribute(WebLegislacionesPortletKeys.LICENCE);
ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(themeDisplay.getScopeGroupId(), "Consultor");
List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
%>

<c:choose>
    <c:when test="<%= hasRole %>">
        <%@include file="menu.jsp" %>
    </c:when>
    <c:otherwise>
        <div class="title-group">
            <div class="row">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="empresa.legislaciones.view.title"/></h2>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<main class="content">
    <div class="formulario">

        <div class="prv-search prv-form">

            <div class="row align-items-start mb-4">

                <%-- TEXTO --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <label for="inpBuscarCajon"> <liferay-ui:message key="empresa.legislaciones.view.buscar" /></label>
                    <input type="text" name="search" id="search" class="clsSelector2Width filter-item" />
                </div>

                <%-- ACTIVO --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <fieldset>
                        <legend><liferay-ui:message key="empresa.legislaciones.view.active"/></legend>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input inpSearchActive filter-item" name="inpSearchActive" id="inpSearchActive1" value="0" type="radio" checked/>
                                <label class="form-check-label" for="inpSearchActive1"><liferay-ui:message key="empresa.legislaciones.view.si"/></abel>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input inpSearchActive filter-item" name="inpSearchActive" id="inpSearchActive2" value="1" type="radio" />
                                <label class="form-check-label" for="inpSearchActive2"><liferay-ui:message key="empresa.legislaciones.view.no"/></label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input inpSearchActive filter-item" name="inpSearchActive" id="inpSearchActive3" value="2" type="radio" />
                                <label class="form-check-label" for="inpSearchActive3"><liferay-ui:message key="empresa.legislaciones.view.todos"/></label>
                            </div>
                        </div>
                    </fieldset>
                </div>

                <%-- ETIQUETAS --%>
                <div class="col-lg-3 col-md-6 col-12">
                    <fieldset class="">
                        <div class="form-group input-select-wrapper">
                            <label class="control-label" for="etiquetas">
                                <liferay-ui:message key="empresa.legislaciones.view.etiquetas"/>
                            </label>
                            <select  id="cmd_etiquetas" name="etiquetas" class="js-select2 filter-item">
                               <option value=""></option>
                               <c:forEach var="category" items="<%= categories %>">
                                    <% AssetCategory curCategory = (AssetCategory) pageContext.getAttribute("category"); %>
                                    <option value="<%= curCategory.getCategoryId() %>" ><%= curCategory.getTitle(locale) %></option>
                               </c:forEach>
                            </select>
                        </div>
                    </fieldset>
                </div>

                <c:choose>
                    <c:when test="<%= hasRole  && licenceId==3%>">
                    <%-- VISIBLE --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <div class="form-group input-select-wrapper">
                            <label class="control-label" for="chkNotVisible"><liferay-ui:message key="empresa.legislaciones.view.notVisibleLabel"/></label>
                            <input type="checkbox" id="chkNotVisible" name="chkNotVisible"> <liferay-ui:message key="empresa.legislaciones.view.notVisible"/></input>
                        </div>

                    </div>
                   </c:when>
                </c:choose>

            </div>

            <div class="prv-advanced-search mb-4">
                <a class="prv-advanced-search__trigger" data-toggle="collapse"
                    href="#advanced-search" role="button" aria-expanded="true"
                    aria-controls="collapseExample">
                    <liferay-ui:message key="empresa.legislaciones.view.busquedaAvanzada" />
                </a>
            </div>

            <div class="collapse" id="advanced-search">
                <div class="row align-items-start mb-4">

                    <%-- CUMPLIMIENTO --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <fieldset>
                            <legend><liferay-ui:message key="empresa.legislaciones.view.cumplimiento"/></legend>
                            <div class="checksBorder">
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento1" value="0" type="radio" class="inpSearchActive"/>
                                    <label class="form-check-label" for="inpSearchCumplimiento1"><liferay-ui:message key="empresa.legislaciones.view.si"/></abel>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento2" value="1" type="radio" class="inpSearchActive" />
                                    <label class="form-check-label" for="inpSearchCumplimiento2"><liferay-ui:message key="empresa.legislaciones.view.no"/></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento3" value="2" type="radio" class="inpSearchActive" />
                                    <label class="form-check-label" for="inpSearchCumplimiento3"><liferay-ui:message key="empresa.legislaciones.view.noProcede"/></label>
                                </div>
                                <div class="custom-control custom-radio mr-4">
                                    <input class="form-check-input filter-item" name="inpSearchCumplimiento" id="inpSearchCumplimiento4" value="" type="radio" class="inpSearchActive" checked />
                                    <label class="form-check-label" for="inpSearchCumplimiento4"><liferay-ui:message key="empresa.legislaciones.view.todos"/></label>
                                </div>
                            </div>
                        </fieldset>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- FAMILIA --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <label for="cmb_ccaa"><liferay-ui:message key="empresa.legislaciones.view.familia"/></label>
                        <select id="cmb_familia" name="<%=WebLegislacionesPortletKeys.FAMILIA%>" label=""  class="js-select2-noSearch filter-item">
                            <option value="" ></option>
                            <c:forEach items="<%= familiaList %>" var="normativa">
                                <option value="${normativa.codigo}"><liferay-ui:message key="${normativa.descripcion}"/></option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- AMBITO --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <label for="cmb_ccaa"><liferay-ui:message key="empresa.legislaciones.view.ambito"/></label>
                        <select id="cmb_ambito" name="<%=WebLegislacionesPortletKeys.AMBITO%>" label=""  class="js-select2-noSearch filter-item">
                            <option value="" ></option>
                            <c:forEach items="<%= AmbitoAplicacion.values() %>" var="ambito">
                                <option value="${ambito.codigo}"  ><liferay-ui:message key="${ambito.descripcion}"/></option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- CCAA --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <label for="cmb_ccaa"><liferay-ui:message key="empresa.legislaciones.view.ccaa"/></label>
                        <select id="cmb_ccaa" name="<%=WebLegislacionesPortletKeys.CCAA%>" label="" class="js-select2 filter-item clsSelector2Width" >
                            <option value="" ></option>
                            <c:forEach var="ccaa" items="<%=ccaaList%>">
                                <option value="${ccaa.ccaaId}">${ccaa.nombre}</option>
                             </c:forEach>
                        </select>
                    </div>

                    <%-- AYUNTAMIENTOS --%>
                    <div class="col-lg-3 col-md-6 col-12">
                        <label for="cmb_ccaa"><liferay-ui:message key="empresa.legislaciones.view.ayuntamientos"/></label>
                        <select id="cmb_ayuntamientos" name="<%=WebLegislacionesPortletKeys.AYUNTAMIENTOS%>" label="" class="js-select2 filter-item clsSelector2Width">
                            <option value="" ></option>
                            <c:forEach var="ayto" items="<%= aytoList %>">
                                <option value="${ayto.ayuntamientoId}">${ayto.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>

                </div>
                <div class="row align-items-start mb-4">

                    <%-- FECHA --%>
                    <div class="col-md-6 col-12 d-flex">
                        <div class="col-md-6 col-12 pl-0">
                            <label for="startDate"><liferay-ui:message key="empresa.legislaciones.view.desde"/></label>
                            <liferay-ui:input-date name="startDate" nullable="true" cssClass="filter-item datepicker" showDisableCheckbox="false"/>
                        </div>

                        <div class="col-md-6 col-12 pr-0">
                            <label for="endDate"><liferay-ui:message key="empresa.legislaciones.view.hasta"/></label>
                            <liferay-ui:input-date name="endDate" nullable="true" cssClass="filter-item datepicker" showDisableCheckbox="false"/>
                        </div>
                    </div>

                </div>
            </div>

            <div class="row align-items-end">
                <div class="col-md-12 col-12 text-right">
                    <div class="btnClear">
                        <button class="btn btn btn-outline-primary btn-sm btn-primary" type="button" id="filter-cleaner">
                            <liferay-ui:message key="empresa.legislaciones.view.limpiar" />
                        </button>
                    </div>
                </div>
            </div>

        </div>

         <div class="row">
            <div class="col">
                <div id="spnCargando" class="loading-animation"></div>
                <table id="table_legislaciones" class="display" style="width:100%; display:none;">
                    <thead>
                        <tr>
                            <th class="no-sort" id="checkBoxNotOrder" style="padding: 8px 10px;background-image: none;"><input type="checkbox" id= "chkNotVisibleAll" onclick="setVisibleLegis(this)"></input></th>
                            <th class="no-sort"><liferay-ui:message key="empresa.legislaciones.view.legislacion"/></th>
                            <th class="no-sort"><liferay-ui:message key="empresa.legislaciones.view.familia"/></th>
                            <th class="no-sort"><liferay-ui:message key="empresa.legislaciones.view.ambito"/></th>
                            <th class="no-sort"><liferay-ui:message key="empresa.legislaciones.view.fecha.publicacion"/></th>
                            <th class="no-sort"><liferay-ui:message key="empresa.legislaciones.view.fecha.derogacion"/></th>
                            <th class="no-sort"><liferay-ui:message key="empresa.legislaciones.view.cumplimiento"/></th>
                            <th class="no-sort"></th>
                            <th class="no-sort"></th>
                            <th class="no-sort"></th>
                        </tr>
                        </thead>
                </table>
            </div>
         </div>

    </div>

    <!-- Modal -->
    <div class="modal fade" id="modalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content" style="width: 30%; margin: auto;">
          <div class="modal-header">
            <h5 class="modal-title" id="modalLongTitle"><liferay-ui:message key="title.modal" /></h5>
          </div>
          <div class="modal-body">
           <p><liferay-ui:message key="content.modal"/></p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-primary" data-dismiss="modal"><liferay-ui:message key="button.cancel.modal"/></button>
            <button type="button" class="btn btn-primary" onClick="setNotVisible()" data-dismiss="modal" ><liferay-ui:message key="button.save.modal"/></button>
          </div>
        </div>
      </div>
    </div>
</main>

<portlet:resourceURL id="buscarLegislaciones" var="buscarLegislacionesURL"/>
<portlet:resourceURL id="buscarRequisitos" var="buscarRequisitosURL"/>
<portlet:resourceURL id="cambiarEstadoLegislaciones" var="cambiarEstadoLegislacionesURL"/>


<script>
    var tabla = null;
    var urlTable="";
    var jsonData = null;
    var longitudHeader=$('#modalLongTitle').html().length
    var compId="<%=compId%>"
    $(document).ready( function () {
        var buttonCommon = {
            exportOptions: {
                format: {
                    body: function ( data, row, column, node ) {
                        // Strip $ from salary column to make it numeric
                        if(column==5 || column == 0 || column >= 7){
                            return ;
                        }else if(column==1){
                            return jsonData[row].nombre;
                        }else if(column==2){
                            return node.childNodes[0].textContent ;
                        }else if(column==3){
                            return node.childNodes[0].textContent ;
                        }else if(column==4){
                            return node.childNodes[1].textContent ;
                        }else if(column==6){
                            return data;
                        }
                    }
                }
            }
        };

        tabla = $('#table_legislaciones').on('preXhr.dt', function ( e, settings, data ) {
                        $("#spnCargando").hide();
                        $("#table_legislaciones").show();
                    }).DataTable( {
                        //dom: '<"capaDatosTabla"iB>t<"capaPaginador"prl>',
                        dom: '<"capaDatosTabla"iB>t<"capaPaginador"pr<"panelBaja">l>',
                        responsive: true,
                        pageLength: 20,
                        buttons: [
                            'csv', 'excel', 'print', 'pdf'
                        ],
                        "ajax": {
                            "url": "<%= buscarLegislacionesURL %>&<portlet:namespace/>compId="+compId+"",
                            "dataSrc": function ( json ) {
                                jsonData=json.data
                                return json.data;
                             }
                        },
                        "columns": [
                            {"data" : "notVisible"},
                            {"data" : "nombre"},
                            {"data" : "familia"},
                            {"data" : "ambito"},
                            {"data" : "publicacion"},
                            {"data" :  null},
                            {"data" :  "cumplimiento"},
                            {"data" : "legislacionId"},
                            {"data" : "legislacionId"},
                            {"data" : "enlace"},
                        ],
                        "columnDefs": [
                            //Columna del check para visible
                            {
                                "targets"  : [0],
                                "orderable": false,
                                "render": function (data, type, row) {
                                    //true esta oculto y false visible
                                    if(arrSetVisible.find(element => element ==row.legislacionId)==undefined){
                                        return '<input type="checkbox"  id="chk_baja_#IDLEGISLACION#" value="#IDLEGISLACION#" onclick="setVisibleLegis(this)"></input>'.replaceAll("#IDLEGISLACION#",row.legislacionId);
                                    }else{
                                        return '<input type="checkbox"  id="chk_baja_#IDLEGISLACION#" value="#IDLEGISLACION#" onclick="setVisibleLegis(this)" checked></input>'.replaceAll("#IDLEGISLACION#",row.legislacionId);
                                    }
                                },
                                "visible" : <%= hasRole && licenceId==3 %>,
                                "searchable": false
                            },

                            {
                                "targets"  : [1],
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
                                "orderable": false,
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
                                "targets"  : [3],
                                "orderable": false,
                                "render": function (data, type, row) {
                                    return $("#cmb_ambito option[value='" + data + "']").text();
                                }
                            },
                            {
                                "targets"  : [4],
                                "orderable": true,
                                "render": function (data, type, row) {
                                    if(data == null || data == undefined || data == "") return "";
                                    publishDate = new Date(data);
                                    return  '<span style="display:none;">' + publishDate.toLocaleDateString('en-GB').split('/').reverse().join('') +  '</span>' +
                                            '<span>' + publishDate.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + '</span>';

                                }
                            },
                            {
                                "targets"  : [5],
                                "orderable": true,
                                "visible": false,
                                "render": function (data, type, row) {
                                    if(data["derogacion"])
                                        return (new Date(data["derogacion"])).toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' });
                                    return "";
                                }
                            },
                            {
                                "targets"  : [6],
                                "orderable": false,
                                "render": function (data, type, row) {
                                    if (data + "" === "0") return "<liferay-ui:message key="empresa.legislaciones.view.si" />";
                                    if (data + "" === "1") return "<liferay-ui:message key="empresa.legislaciones.view.no" />";
                                    if (data + "" === "2") return "<liferay-ui:message key="empresa.legislaciones.view.noProcede" />";
                                    return data;
                                }
                            },
                            {
                                "targets"  : [7],
                                "orderable": false,
                                "render": function (data, type, row) {
		                		    return '<span class="ico-acciones-tabla"><span class="viewDetail icon-eye-open" legislacionId="' +  data + '"></span></span>';
                                }
                            },
                            {
                                "targets"  : [8],
                                "orderable": false,
                                "render": function (data, type, row) {
                                                return  '<a class="ico-acciones-tabla" onclick="saveParamsSearch()" href="${evaluacionUrl}?comp=<%= compId %>&legislacion=' + data + '">' +
                                                            '<span class="icon-edit"></span>' +
                                                        '</a>';
                                           }
                            },
                            {
                                "targets"  : [9],
                                "orderable": false,
                                "render": function (data, type, row) {
                                    return '<a class="ico-acciones-tabla" href="' + data + '" target="_blank"><span class="icon-link"></span></a>';
                                }
                            }
                        ],
                        "initComplete": function(settings, json) {
                            $('#checkBoxNotOrder').removeClass('sorting_asc');
                            changeActivo();
                            $("#spnCargando").hide();
                            $("#table_legislaciones").show();
                        },
                        "order": [[3,"desc"],[0,"asc"]],
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


        $('.js-select2-noSearch').select2({
          placeholder: '<liferay-ui:message key="empresa.legislaciones.view.select"/>',
          multiple : false,
          language : "es",
          minimumResultsForSearch: Infinity,
          allowClear: true
        });

        $('.js-select2').select2({
          placeholder: '<liferay-ui:message key="empresa.legislaciones.view.select"/>',
          multiple : false,
          language : "es",
          allowClear: true
        });

        function searchFilterAjax (){
            var url = "<%= buscarLegislacionesURL %>"
                                    + "&activo="        + $("input[name=inpSearchActive]:checked").val()
                                    + "&cumplimiento="  + $("input[name=inpSearchCumplimiento]:checked").val()
                                    + "&familia="       + $("#cmb_familia").val()
                                    + "&ambito="        + $("#cmb_ambito").val()
                                    + "&ccaa="          + $("#cmb_ccaa").val()
                                    + "&ayuntamiento="  + $("#cmb_ayuntamientos").val()
                                    + "&texto="         + $("#search").val()
                                    + "&etiquetas=" + $("#cmd_etiquetas").val();

                         if( document.getElementById("chkNotVisible")){

                            url = (document.getElementById("chkNotVisible").checked)? url+ "&oculto=true" : url+ "&oculto=false" ;
                         }

                        console.log( "url: " + url);

                        var inicio = $("#<portlet:namespace/>startDate");
                        var fin = $("#<portlet:namespace/>endDate");

                        if(inicio.val().trim() != "" && isValidDate(inicio.val())){
                            arrDate = inicio.val().split("/");
                            dateInicio = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                            url += "&fechaDesde=" + dateInicio.getTime();
                        }
                        if( fin.val().trim() != "" && isValidDate(fin.val())){
                            arrDate = fin.val().split("/");
                            dateInicio = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                            dateInicio.setDate(dateInicio.getDate() + 1);
                            url += "&fechaHasta=" + dateInicio.getTime();
                        }
                        urlTable=url+"&<portlet:namespace/>compId="+compId+"";
                        tabla.ajax.url(urlTable);
                        var ultimaPagina = parseInt(localStorage.getItem("pagina_actual"));
                        //tabla.ajax.reload(null, false).page(ultimaPagina).draw('page' );
						tabla.ajax.reload();

        }

        function getPaginaGuardada(){

            var ultimaPagina = parseInt(localStorage.getItem("pagina_actual"));
            if (isNaN(ultimaPagina)){
            	ultimaPagina=0;
            }
            localStorage.setItem("pagina_actual",null);
            return ultimaPagina
        }

        $(".filter-item").on("keyup change", function(e) {
            searchFilterAjax();
        });

        $("#chkNotVisible").click(function(){
            searchFilterAjax();
        })
        $("#filter-cleaner").on("click", function(e) {
            $("#<portlet:namespace/>startDate").val('');
            $("#<portlet:namespace/>endDate").val('');
            $("#inpSearchActive1").prop("checked", true);
            $("#inpSearchCumplimiento4").prop("checked", true);
            $("#search").val("").trigger("change");
            $("#cmb_familia").val("").trigger("change");
            $("#cmb_ambito").val("").trigger("change");
            $("#cmb_ccaa").val("").trigger("change");
            $("#cmb_ayuntamientos").val("").trigger("change");
            $("#cmd_etiquetas").val("").trigger("change");
        });


            $("#table_legislaciones tbody").on("click", ".viewDetail", function (e) {
                var tr = $(this).closest('tr');
                var row = tabla.row(tr);

                if (row.child.isShown()) {
                    $('div.slider', row.child()).slideUp( function () {
                        row.child.hide();
                        tr.removeClass('shown');
                    });
                    $(e.target).attr('class','viewDetail icon-eye-open');
                } else {
                    row.child(format(row.data()), 'no-padding' ).show();
                    tr.addClass('shown');

                    <c:if test="${licence ne optimo}">
                        var xhr = new XMLHttpRequest();
                        xhr.onreadystatechange = function(){
                            if (this.readyState == 4 && this.status == 200){
                                var requisitos = JSON.parse(this.responseText);
                                var reqList = document.getElementById("requisitos_" + e.target.getAttribute("legislacionId"));
                                reqList.innerHTML = '<ul>';
                                for(x in requisitos.items){
                                    reqList.innerHTML += '<li class="mb-3">' +  requisitos.items[x].descripcion + '</li>';
                                }
                                if(reqList.innerHTML == '<ul>') '<li><liferay-ui:message key="admin.legislaciones.view.sinRequisitos"/></li>';
                                reqList.innerHTML += '</ul>';
                            }
                        }
                        xhr.open("GET", "<%= buscarRequisitosURL.toString() %>" + "&legislacionId="+  e.target.getAttribute("legislacionId") + "&compId=<%=compId%>", true);
                        xhr.send(null);
                    </c:if>

                    $('div.slider', row.child()).slideDown();
                    $(event.target).attr('class','viewDetail icon-eye-close');
                }

            });


        function format(legislacion) {

            var spansEtiquetas = "";
            var etiquetas = legislacion["etiquetas"];
            for (var etiqueta of etiquetas) {
                spansEtiquetas += '<div class="d-flex flex-column align-items-start"><span class="badge badge-info">' + etiqueta.nombre  + '</span></div>';
            }

        	return  '<div class="slider">'+
                        '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
                            '<tr>'+
                                '<td><liferay-ui:message key="empresa.legislaciones.view.legislacion"/></td>'+
                                '<td>' + legislacion["nombre"] + '</td>'+
                            '</tr>'+
                            '<tr>'+
                                '<td><liferay-ui:message key="empresa.legislaciones.view.descripcion"/></td>'+
                                '<td>' + legislacion["descripcion"] + '</td>'+
                            '</tr>'+
                            '<tr>'+
                                '<td><liferay-ui:message key="empresa.legislaciones.view.etiquetas"/></td>'+
                                '<td>' + spansEtiquetas + '</td>'+
                            '</tr>'+
                            <c:if test="${licence ne optimo}">
                                '<tr>'+
                                    '<td><liferay-ui:message key="empresa.legislaciones.view.requistios"/></td>'+
                                    '<td><span id="requisitos_' + legislacion["legislacionId"] + '"><span class="loading-animation"></span> </span></td>'+
                                '</tr>'+
                            </c:if>
                        '</table>'+
                    '</div>';
        }

        function changeActivo() {
            $("input[name=inpSearchActive]").on("click", function(){
                tabla.column(4).visible( $(this).val() == "1" || $(this).val() == "2" );
            });
        }

        function isValidDate(_date){
            var reg = /^(0[1-9]|[1-2]\d|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
            if(reg.test(_date)) return true;
            return false;

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
                     $(".filter-item").trigger("change");
                  }
                }
            });
        });
       tabla.on( 'draw', function () {
        if(localStorage.getItem("pagina_actual")!="null"){
            var pagina=getPaginaGuardada()
            tabla.page(pagina).draw('page');

        }

       });
    });
    //Guardar parametros de filtrado
    function saveParamsSearch(){
            var itemSearch = {};
            itemSearch.search = document.getElementById("search").value.trim();
            itemSearch.inpSearchActive1 = document.getElementById("inpSearchActive1").checked;
            itemSearch.inpSearchActive2 = document.getElementById("inpSearchActive2").checked;
            itemSearch.inpSearchActive3 = document.getElementById("inpSearchActive3").checked;

            itemSearch.cmd_etiquetas = document.getElementById("cmd_etiquetas").value;
            itemSearch.inpSearchCumplimiento1 = document.getElementById("inpSearchCumplimiento1").checked;
            itemSearch.inpSearchCumplimiento2 = document.getElementById("inpSearchCumplimiento2").checked;
            itemSearch.inpSearchCumplimiento3 = document.getElementById("inpSearchCumplimiento3").checked;
            itemSearch.inpSearchCumplimiento4 = document.getElementById("inpSearchCumplimiento4").checked;

            itemSearch.cmb_familia = document.getElementById("cmb_familia").value;
            itemSearch.cmb_ambito = document.getElementById("cmb_ambito").value;
            itemSearch.cmb_ccaa = document.getElementById("cmb_ccaa").value;
            itemSearch.cmb_ayuntamientos = document.getElementById("cmb_ayuntamientos").value;


            itemSearch.startDate = document.getElementById("<portlet:namespace/>startDate").value;
            itemSearch.endDate = document.getElementById("<portlet:namespace/>endDate").value;
            //Capturas página actual

            sessionStorage.setItem("searchParams_empLegislacionesTab", JSON.stringify(itemSearch));
            //event.preventDefault();

            var info = $('#table_legislaciones').DataTable().page.info();
            var page = info.page;
            localStorage.setItem("pagina_actual", page);
        }

         //Recuperar los filtros
            $(document).ready(function(){
                <%if(hasRole && licenceId==3){%>
                    $("div.panelBaja").html('<button type="button" class="btn btn-primary" id="buttonBaja" data-toggle="modal" data-target="#modalCenter" onClick="addTitle()"><liferay-ui:message key="empresa.legislaciones.view.btnNotVisible" /></buton>');
                <%}%>
                var advancedSearch = false;
                if(sessionStorage.getItem("searchParams_empLegislacionesTab") != null && sessionStorage.getItem("searchParams_empLegislacionesTab") != "null"){
                    itemSearch = JSON.parse(sessionStorage.getItem("searchParams_empLegislacionesTab"));
                    if(itemSearch.search && itemSearch.search != "" ) document.getElementById("search").value = itemSearch.search;
                    if(itemSearch.inpSearchActive1) document.getElementById("inpSearchActive1").checked = true;
                    if(itemSearch.inpSearchActive2) document.getElementById("inpSearchActive2").checked = true;
                    if(itemSearch.inpSearchActive3) document.getElementById("inpSearchActive3").checked = true;


                    var event = new Event('change');

                    if(itemSearch.cmd_etiquetas){
                        document.getElementById("cmd_etiquetas").value = itemSearch.cmd_etiquetas;
                        document.getElementById("cmd_etiquetas").dispatchEvent(event);
                        advancedSearch = true;
                    }

                    if(itemSearch.inpSearchCumplimiento1) document.getElementById("inpSearchCumplimiento1").checked = true;
                    if(itemSearch.inpSearchCumplimiento2) document.getElementById("inpSearchCumplimiento2").checked = true;
                    if(itemSearch.inpSearchCumplimiento3) document.getElementById("inpSearchCumplimiento3").checked = true;
                    if(itemSearch.inpSearchCumplimiento4) document.getElementById("inpSearchCumplimiento4").checked = true;

                    if(itemSearch.cmb_tipo){
                        document.getElementById("cmb_tipo").value = itemSearch.cmb_tipo;
                        document.getElementById("cmb_tipo").dispatchEvent(event);
                        advancedSearch = true;
                    }
                    if(itemSearch.cmb_familia){
                        document.getElementById("cmb_familia").value = itemSearch.cmb_familia;
                        document.getElementById("cmb_familia").dispatchEvent(event);
                        advancedSearch = true;
                    }
                    if(itemSearch.cmb_ambito){
                        document.getElementById("cmb_ambito").value = itemSearch.cmb_ambito;
                        document.getElementById("cmb_ambito").dispatchEvent(event);
                        advancedSearch = true;
                    }
                    if(itemSearch.cmb_ccaa){
                        document.getElementById("cmb_ccaa").value = itemSearch.cmb_ccaa;
                        document.getElementById("cmb_ccaa").dispatchEvent(event);
                        advancedSearch = true;
                    }
                    if(itemSearch.cmb_ayuntamientos){
                        document.getElementById("cmb_ayuntamientos").value = itemSearch.cmb_ayuntamientos;
                        document.getElementById("cmb_ayuntamientos").dispatchEvent(event);
                        advancedSearch = true;
                    }


                    if(itemSearch.startDate){
                        document.getElementById("<portlet:namespace/>startDate").value = itemSearch.startDate;
                        advancedSearch = true;
                    }
                    if(itemSearch.endDate){
                        document.getElementById("<portlet:namespace/>endDate").value = itemSearch.endDate;
                        advancedSearch = true;
                    }

                    if(advancedSearch){
                        document.getElementsByClassName("prv-advanced-search__trigger")[0].classList.remove("collapsed");
                        document.getElementsByClassName("prv-advanced-search__trigger")[0].classList.add("active");
                        document.getElementById("advanced-search").classList.remove("collapsed");
                        document.getElementById("advanced-search").classList.add("show");
                    }

                    sessionStorage.setItem("searchParams_empLegislacionesTab", null)

                    //sessionStorage.clear();
                    $(".filter-item")[0].dispatchEvent(event);

                }
            })

    var arrSetVisible = [];
    function setVisibleLegis(_node){
        if(_node.id == "chkNotVisibleAll"){
            var cells = tabla.column(0).nodes();
            var state = _node.checked;
            for (var i = 0; i < cells.length; i += 1) {
                cells[i].querySelector("input[type='checkbox']").checked = state;
                if(_node.checked){
                    if(arrSetVisible.find(element => element ==cells[i].querySelector("input[type='checkbox']").value)==undefined){
                        arrSetVisible.push(cells[i].querySelector("input[type='checkbox']").value);
                    }
                }else{
                    arrSetVisible.splice(arrSetVisible.indexOf(cells[i].querySelector("input[type='checkbox']").value),1);
                }
            }
        }else{
            if(_node.checked){
                if(arrSetVisible.find(element => element ==_node.value)==undefined){
                    arrSetVisible.push(_node.value);
                }
            }else{
                arrSetVisible.splice(arrSetVisible.indexOf(_node.value),1);
            }
        }
    }

    function addTitle(){
        var title= $('#modalLongTitle').html()
        title=title.substring(0,longitudHeader) + " ("+arrSetVisible.length+") disposiciones seleccionadas";
        $('#modalLongTitle').html(title);
        $('#modalLongTitle').css("white-space","normal");
    }

    function setNotVisible(){
        //llamar al servicio
        var compId="<%=compId%>";
        var legislacionesId=arrSetVisible
        console.log();
         $.ajax({
            type: 'POST',
            url: '<%= cambiarEstadoLegislacionesURL %>',
            data: { '<portlet:namespace/>compId' : compId ,
                    '<portlet:namespace/>legislacionesId': legislacionesId.join(",")},
            success: function (data){
                //una vez enviado el parametro limpiamos el array
                arrSetVisible=[];
                var ultimaPagina = parseInt(localStorage.getItem("pagina_actual"));
				if ( isNaN( ultimaPagina ) )
				{
					ultimaPagina = 0;
 				}
                tabla.ajax.reload(null, false).page(ultimaPagina).draw('page' );
                tabla.ajax.url(urlTable);
            }
         });

    }
</script>