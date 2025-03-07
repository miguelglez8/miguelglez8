<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.role.CanalEticoRoleUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.vinculaciones.web.constants.VinculacionesPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ include file="init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">

<script>
    Liferay.Loader.define._amd = Liferay.Loader.define.amd;
    Liferay.Loader.define.amd = false;
</script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="https://nightly.datatables.net/responsive/js/dataTables.responsive.min.js"></script>
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
    long compId = (long)request.getAttribute(VinculacionesPortletKeys.COMPANY_ID);
    boolean isAdmin = CanalEticoRoleUtil.isCompanyAdminRole(themeDisplay.getCompanyId(), themeDisplay.getUser());

    boolean hasPermission = CanalEticoUserUtil.isUserInDefaultCompPermission(themeDisplay.getUserId());
    boolean omniAdminRole = CanalEticoRoleUtil.isOmniAdminRole(themeDisplay.getCompanyId(), themeDisplay.getUser());


    System.out.println("Vinculaciones: " + user.getFullName() + ", compId: " + compId +", isOmniadmin " + permissionChecker.isOmniadmin());

    List<Vinculacion> listVinculaciones = (List<Vinculacion>) request.getAttribute(VinculacionesPortletKeys.VINCULACIONES_LIST);

    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
    String yes = LanguageUtil.get(bundle, "vinculacion.view.yes");
    String no  = LanguageUtil.get(bundle, "vinculacion.view.no");
    String all = LanguageUtil.get(bundle, "vinculacion.view.all");
%>

<main class="content">

    <c:if test="<%=!hasPermission && !omniAdminRole%>">
        <div class="formulario">
            <div class="title-group">
                <h2><liferay-ui:message key="vinculacion.view.title"/></h2>
                <p><liferay-ui:message key="vinculacion.view.no.company.permission"/></p>
            </div>
        </div>
    </c:if>

    <c:if test="<%=hasPermission || omniAdminRole%>">
        <div class="formulario">
            <div class="title-group">
                <h2><liferay-ui:message key="vinculacion.view.title"/></h2>
                <portlet:renderURL var="addUserURL">
                    <portlet:param name="mvcPath" value="/vinculacion.jsp"></portlet:param>
                </portlet:renderURL>
                <aui:button-row cssClass="button-holder">
                    <aui:button onClick="<%= addUserURL.toString() %>" value="vinculacion.view.new" cssClass="btn btn-outline-primary btn-sm" primary="true"></aui:button>
                </aui:button-row>
            </div>

            <div class="row">
                <fieldset class="input-group-inline mt-3 mb-2">
                    <legend><liferay-ui:message key="vinculacion.view.active"/></legend>
                    <div class="custom-control custom-radio mr-4">
                        <input class="form-check-input" name="inpSearchActive" id="inpSearchActive1"
                         value="<%=yes%>" type="radio" class="inpSearchActive" checked onchange="changeActivo(this.value)" />
                        <label class="form-check-label" for="inpSearchActive1"><%=yes%></label>
                    </div>
                    <div class="custom-control custom-radio mr-4">
                        <input class="form-check-input" name="inpSearchActive" id="inpSearchActive2"
                         value="<%=no%>" type="radio" class="inpSearchActive" onchange="changeActivo(this.value)" />
                        <label class="form-check-label" for="inpSearchActive2"><%=no%></label>
                    </div>
                    <div class="custom-control custom-radio mr-4">
                        <input class="form-check-input" name="inpSearchActive" id="inpSearchActive3"
                        value="<%=all%>" type="radio" class="inpSearchActive" onchange="changeActivo(this.value)" />
                        <label class="form-check-label" for="inpSearchActive3"><%=all%></label>
                    </div>
                </fieldset>
            </div>

            <div class="row">
                <div class="col">
                    <table id="table" class="display" style="width:100%">
                        <thead>
                            <tr>
                                <th><liferay-ui:message key="vinculacion.view.name"/></th>
                                <th><liferay-ui:message key="vinculacion.view.active"/></th>
                                <%--<c:if test="<%=isAdmin%>"><th><liferay-ui:message key="vinculacion.view.empresa"/></th></c:if>--%>
                                <th><liferay-ui:message key="vinculacion.view.action"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="<%=listVinculaciones%>" var="vinculacion">
                                <%
                                    Vinculacion vinculacion = (Vinculacion) pageContext.getAttribute("vinculacion");
                                    String compName = LanguageUtil.get(bundle, "vinculacion.view.global");
                                    if(isAdmin && vinculacion.getCompId() > 0){
                                        Comp comp = CompLocalServiceUtil.fetchComp(vinculacion.getCompId());
                                        if(Validator.isNotNull(comp)){
                                            compName=comp.getName();
                                        }
                                    }
                                    System.out.println(vinculacion.getNombre() + " " + vinculacion.getCompId() + ", " + vinculacion.getActivo() + " " + (vinculacion.getActivo()?yes:no));
                                %>
                                <liferay-portlet:renderURL varImpl="editURL">
                                    <portlet:param name="vinculacionEditId" value="<%=String.valueOf(vinculacion.getVinculacionId())%>" />
                                    <portlet:param name="mvcPath" value="/vinculacion.jsp" />
                                </liferay-portlet:renderURL>
                                <tr>
                                    <td>${vinculacion.nombre}</td>
                                    <td><%=vinculacion.getActivo()?yes:no%></td>
                                    <%--<td><%=compName%></td>--%>
                                    <td>
                                        <c:if test="<%=permissionChecker.isOmniadmin() || vinculacion.getCompId() == compId%>">
                                            <a class="ico-acciones-tabla" href="<%=editURL.toString()%>">
                                                <img src="<%=themeDisplay.getPathThemeImages()%>/ico_edit.png" alt="<liferay-ui:message key="edit" />" />
                                            </a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
</main>

<script>
    var tabla = null;
    $(document).ready( function () {
        tabla = $('#table').DataTable( {
            dom: 'Bfrtip',
            responsive: true,
            pageLength: 20,
            buttons: [
                'csv', 'excel', 'print', 'pdf'
            ],
            "language": {
                "sProcessing":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sProcessing")%>",
                "sLengthMenu":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sLengthMenu")%>",
                "sZeroRecords":    "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sZeroRecords")%>",
                "sEmptyTable":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sEmptyTable")%>",
                "sInfo":           "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfo")%>",
                "sInfoEmpty":      "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoEmpty")%>",
                "sInfoFiltered":   "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoFiltered")%>",
                "sInfoPostFix":    "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoPostFix")%>",
                "sSearch":         "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sSearch")%>",
                "sUrl":            "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sUrl")%>",
                "sInfoThousands":  "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sInfoThousands")%>,",
                "sLoadingRecords": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sLoadingRecords")%>",
                "oPaginate": {
                    "sFirst":    "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sFirst")%>",
                    "sLast":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sLast")%>",
                    "sNext":     "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sNext")%>",
                    "sPrevious": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sPrevious")%>"
                },
                "oAria": {
                    "sSortAscending":  "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sSortAscending")%>",
                    "sSortDescending": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.sSortDescending")%>"
                },
                "buttons": {
                    "copy": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.copy")%>",
                    "colvis": "<%=LanguageUtil.get(bundle, "datatable.view.datatable.colvis")%>"
                }
            }
        } );
    } );
    
var filterActivo = "Si"; // para pasarle el click en el radio de activo (idiomas�?)
    
    $.fn.dataTable.ext.search.push(
            function( settings, data, dataIndex ) {
                
                var cond = 0; //cuantos se cumplen
                var numCond = 0; // tiene 3 filtros posibles, cuantos estan seleccionados

                
                
                numCond++; //siempre esta seleccionado un SI / NO / TODAS
                if(filterActivo == "<%=all%>"){
                    cond++;
                }else if(filterActivo == data[1].trim()){
                    cond++;
                }

                //Se cumplen todas:
                if(numCond == cond) return true;
                return false;
        }
    );


    function changeActivo(_activo){
        filterActivo = _activo;
        tabla.draw();
    }

</script>