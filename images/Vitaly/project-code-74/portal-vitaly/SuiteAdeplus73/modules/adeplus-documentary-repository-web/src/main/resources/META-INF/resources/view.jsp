<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.language.Language"%>
<%@page import="com.adeplus.liferay.portlet.documentary.repository.web.constants.AdeplusDocumentaryRepositoryPortletKeys"%>
<%@page import="java.util.ResourceBundle"%>
<%@ include file="/init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">

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
	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getLocale());
	String compId = (String) request.getAttribute(AdeplusDocumentaryRepositoryPortletKeys.COMPID_PARAM);

%>
<liferay-ui:success key="file-add-success" message="document.view.message.add-file-success" />
<liferay-ui:success key="file-delete-success" message="document.view.message.delete-file-success" />
<liferay-ui:error key="file-add-error" message="document.view.message.add-file-error" />
<liferay-ui:error key="file-add-error-exist" message="document.view.message.add-file-error-exist" />

<liferay-portlet:renderURL varImpl="addDocumentURL">
	<portlet:param name="mvcPath" value="/document.jsp" />
	<portlet:param name="<%=AdeplusDocumentaryRepositoryPortletKeys.COMPIDDOCUMENTARY_PARAM %>" value="<%=compId %>" />
</liferay-portlet:renderURL>
<main class="content">
	<div class="formulario">
		<div class="title-group">
			<div class="row">
                <div class="col-md-6 col-12">
                    <h2><liferay-ui:message key="document.view.title"/></h2>
                </div>
                
                <div class="col-md-6 col-12 text-right">
                    <a class="prv-iconLinks prv-iconLinks__plus" href="<%= addDocumentURL.toString() %>">
                        <liferay-ui:message key="document.view.upload-document"/>
                    </a>
                </div>
            </div>
		</div>
		<div class="form-content prv-form">
			<div class="row align-items-start mb-4">
				
                <div class="col-lg-6 col-md-6 col-12">
                    <fieldset>
                        <legend><liferay-ui:message key="document.view.documents"/></legend>
                        <div class="checksBorder">
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="<%=AdeplusDocumentaryRepositoryPortletKeys.RADIO_BUTTONS_SEARCH %>" id="inpSearchDocuments1" value="GeneradosPorElConsultor" type="radio" onChange="tabla.draw();" />
								<label class="form-check-label" for="inpSearchDocuments1">
									<liferay-ui:message key="document.view.generated-by-my-consultant"/>
								</label>
                            </div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="<%=AdeplusDocumentaryRepositoryPortletKeys.RADIO_BUTTONS_SEARCH %>" id="inpSearchDocuments2" value="SubidosPorMi" type="radio" onChange="tabla.draw();" />
								<label class="form-check-label" for="inpSearchDocuments2">
									<liferay-ui:message key="document.view.uploaded-by-me"/>
								</label>
							</div>
                            <div class="custom-control custom-radio mr-4">
                                <input class="form-check-input filter-item" name="<%=AdeplusDocumentaryRepositoryPortletKeys.RADIO_BUTTONS_SEARCH %>" id="inpSearchDocuments3" value="Todos" type="radio" checked="checked" onChange="tabla.draw();" />
								<label class="form-check-label" for="inpSearchDocuments3">
									<liferay-ui:message key="document.view.all"/>	
								</label>
							</div>
                        </div>
                    </fieldset>
                </div>
				<div class="col-sm-12 col-md-3 col-lg-3">				
					<label for="inpSearch">
            			<liferay-ui:message key="document.view.search"/>            		
            		</label>
            		<input type="search" name="<%=AdeplusDocumentaryRepositoryPortletKeys.SEARCH %>" class="" placeholder="" aria-controls="table_documents_repository" id="inpSearch">
				</div>
			</div>
			<div class="row align-items-start mb-4">
				<div class="col-md-3 col-12 d-flex">
					<div class="col-md-11 col-12 pl-0">
						<label for="<%=AdeplusDocumentaryRepositoryPortletKeys.START_DATE %>" class="control-label">
							<liferay-ui:message key="document.view.date-from"/>
						</label>
					 	<liferay-ui:input-date cssClass="filter-item datepicker" name="<%=AdeplusDocumentaryRepositoryPortletKeys.START_DATE %>" nullable="true" showDisableCheckbox="false" />
					</div>
				</div>
				<div class="col-md-3 col-12 pr-0">
					 <div class="col-md-11 col-12 pr-0">
					 	<label for="<%=AdeplusDocumentaryRepositoryPortletKeys.END_DATE %>" class="control-label">
							<liferay-ui:message key="document.view.until"/>
						</label>
					 	<liferay-ui:input-date cssClass="filter-item datepicker" name="<%=AdeplusDocumentaryRepositoryPortletKeys.END_DATE %>" nullable="true" showDisableCheckbox="false" />
					 </div>
				</div>
				<div class="col-md-4 col-12 pr-0" style="margin-top: 35px;">
					<div class="custom-control custom-checkbox mr-4">
						<input class="custom-control-input clsApps" type="checkbox" id="lastVersion" name="<%=AdeplusDocumentaryRepositoryPortletKeys.LAST_VERSION %>" onchange="tabla.draw();">
						<label class="custom-control-label" for="lastVersion">
							<liferay-ui:message key="document.view.show-last-version"/>	
						</label>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col">
				<div id="spnCargando" class="loading-animation"></div>
				<table id="table_documents_repository" class="display" style="width:100%">
					<thead>
						<tr>
							<th class="display-none"></th>
							<th class="display-none"></th>
							<th><liferay-ui:message key="document.view.date"/></th>
							<th class="display-none"></th>
							<th><liferay-ui:message key="document.view.user"/></th>
							<th><liferay-ui:message key="document.view.document"/></th>
							<th><liferay-ui:message key="document.view.observations"/></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${documentsRepository}" var="document">
							<tr>
								<td class="display-none">${document.isConsultor}</td>
								<td class="display-none">${document.lastVersion}</td>
								<td>${document.date}</td>
								<td class="display-none">${document.userId}</td>
								<td>${document.userName}</td>
								<td>${document.documentName}</td>
								<td>${document.observations}</td>
								<td>
									<div class="d-flex justify-content-end">
										<portlet:resourceURL id="/documentaryRepository/downloadDocument" var="downloadDocumentURL">
											<portlet:param name="<%=AdeplusDocumentaryRepositoryPortletKeys.FILE_ID %>" value="${document.documentId}" />
											<portlet:param name="<%=AdeplusDocumentaryRepositoryPortletKeys.IS_INFORME %>" value="${document.isInforme}" />
											<portlet:param name="<%=AdeplusDocumentaryRepositoryPortletKeys.NAME %>" value="${document.documentName}" />									
											<portlet:param name="<%=AdeplusDocumentaryRepositoryPortletKeys.COMPID_PARAM %>" value="<%=compId%>" />		
										</portlet:resourceURL>
										<a class="ico-acciones-tabla prv-reverseHover-img" href="<%=downloadDocumentURL.toString()%>">
											<img src="<%=themeDisplay.getPathThemeImages()%>/ico-Download.png" alt="<liferay-ui:message key="dowload" />" />
										</a>
										<c:if test="${document.permissDelete}">
											<a data-id="${document.documentId}" class="ico-acciones-tabla prv-reverseHover-img delete-document" href="#">
												<img src="<%=themeDisplay.getPathThemeImages()%>/ico-Delete.png" alt="<liferay-ui:message key="delete" />" />
											</a>
										</c:if>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</main>

<%@include file="delete.jspf" %>

<script>
	var tabla = null;
	$(document).ready( function () {
	    
		tabla = $('#table_documents_repository').DataTable( {
			responsive: true,
			pageLength: 25,
			"initComplete": function( settings, json ) {
                $("#spnCargando").hide();
                $("#table_documents_repository").show();
            },
            "columnDefs": [
                {
	                "targets"  : [7],
	                "orderable": false
                }
			],
			order: [[0, 'desc']],
			"language": {
				"sLengthMenu":     "<%=LanguageUtil.get(bundle, "document.view.datatable.sLengthMenu")%>",
				"sZeroRecords":    "<%=LanguageUtil.get(bundle, "document.view.datatable.sZeroRecords")%>",
				"sEmptyTable":     "<%=LanguageUtil.get(bundle, "document.view.datatable.sEmptyTable")%>",
				"sInfo":           "<%=LanguageUtil.get(bundle, "document.view.datatable.sInfo")%>",
				"sInfoEmpty":      "<%=LanguageUtil.get(bundle, "document.view.datatable.sInfoEmpty")%>",
				"sInfoFiltered":   "<%=LanguageUtil.get(bundle, "document.view.datatable.sInfoFiltered")%>",
				"sInfoPostFix":    "<%=LanguageUtil.get(bundle, "document.view.datatable.sInfoPostFix")%>",
				"sSearch":         "<%=LanguageUtil.get(bundle, "document.view.datatable.sSearch")%>",
				"sLoadingRecords": "<%=LanguageUtil.get(bundle, "document.view.datatable.sLoadingRecords")%>",
				"oPaginate": {
					"sFirst":    "<%=LanguageUtil.get(bundle, "document.view.datatable.sFirst")%>",
					"sLast":     "<%=LanguageUtil.get(bundle, "document.view.datatable.sLast")%>",
					"sNext":     "<%=LanguageUtil.get(bundle, "document.view.datatable.sNext")%>",
					"sPrevious": "<%=LanguageUtil.get(bundle, "document.view.datatable.sPrevious")%>"
				},
				"oAria": {
					"sSortAscending":  "<%=LanguageUtil.get(bundle, "document.view.datatable.sSortAscending")%>",
					"sSortDescending": "<%=LanguageUtil.get(bundle, "document.view.datatable.sSortDescending")%>"
				},
				"buttons": {
					"copy": "<%=LanguageUtil.get(bundle, "document.view.datatable.copy")%>",
					"colvis": "<%=LanguageUtil.get(bundle, "document.view.datatable.colvis")%>"
				}
			}
		});
		
		$.fn.dataTable.ext.search.push(
        	function( settings, data, dataIndex ) {                
                var cond = 0;
                var numCond = 0;
				
                var text = $('#inpSearch').val().toLowerCase();
                if (text != "") {
                    numCond++;
                    if ((data[2].toLowerCase().match(text) ||
                        data[2].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) || 
                        (data[4].toLowerCase().match(text) ||
                        data[4].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) ||
                        (data[5].toLowerCase().match(text) ||
                        data[5].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text)) || 
                        (data[6].toLowerCase().match(text) || 
                        data[6].toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '').match(text))) cond++;
                }
                
                var uploadFor = $('input:radio[name=inpSearchDocuments]:checked').val();

                if (uploadFor != "Todos") {
                    numCond++;
                    if (uploadFor == "GeneradosPorElConsultor" && data[0]==1){
                    	cond++;
                    }else if (uploadFor == "SubidosPorMi" && data[3]==<%= themeDisplay.getUser().getUserId()%>){
                    	cond++;
                    }
                }
                
                if($( "input[name=<%=AdeplusDocumentaryRepositoryPortletKeys.LAST_VERSION %>]:checked").length > 0 ){
                	numCond++;
                	if(data[1] == 1){
                		cond++;
                	}
                	
                } 
                
                var inicio = $("#<portlet:namespace/><%=AdeplusDocumentaryRepositoryPortletKeys.START_DATE%>").val();
                var fin = $("#<portlet:namespace/><%=AdeplusDocumentaryRepositoryPortletKeys.END_DATE%>").val();
                
                var arrDate = null;
                var dateFilter = null;
                var dateFilterCol = null;
                
                if(inicio.trim() != "" && isValidDate(inicio)){
                    numCond++;
                    arrDate = inicio.split("/");
                    dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    arrDate = data[2].split("/");
                    dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    if(dateFilterCol.getTime() >= dateFilter.getTime() ) cond++;
                }

                if(fin.trim() != "" && isValidDate(fin)){
                    numCond++;
                    arrDate = fin.split("/");
                    dateFilter = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    arrDate = data[2].split("/");
                    dateFilterCol = new Date(arrDate[2],arrDate[1]-1,arrDate[0],"00","00");
                    if(dateFilterCol.getTime() <= dateFilter.getTime() ) cond++;
                }

                if(numCond == cond) return true;
                return false;
            }
        );
        
		$(document).on('keypress',function(e) {
		    if(e.which == 13) {
		   		tabla.draw() ;
		    }
		});

		$(document).on('keyup', '#inpSearch', function(e) {
	   		tabla.draw() ;
		});
		
		tabla.draw();
		
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
	} );
	
	function isValidDate(_date){
        var reg = /^(0[1-9]|[1-2]\d|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
        if(reg.test(_date)) return true;
        return false;

    }

	$(document).on('click', '.delete-document', function(e){
	    e.preventDefault();
	    var id = $(this).data('id');
	    $('#deleteDocument').removeClass('hide');
	    $('#deleteDocument').addClass('show');
	    $('#<portlet:namespace /><%=AdeplusDocumentaryRepositoryPortletKeys.FILE_ID%>').val(id);
	    $('#<portlet:namespace /><%=AdeplusDocumentaryRepositoryPortletKeys.COMPID_PARAM%>').val('<%=compId%>');

	});
	
	$(document).on('click', '.hide-modal', function(e) {
	    e.preventDefault();
	    $('#deleteDocument').addClass('hide');
	});
</script>