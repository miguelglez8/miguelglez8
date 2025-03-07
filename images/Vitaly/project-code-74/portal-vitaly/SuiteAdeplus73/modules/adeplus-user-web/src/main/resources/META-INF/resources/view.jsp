<%@ page import="com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionUserUtil" %>
<%@ page import="com.adeplus.liferay.portlet.commons.web.role.AdeplusRoleUtil" %>
<%@ page import="com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Application" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Comp" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.CompApplication" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.UserCompany" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.CompApplicationLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication" %>

<%@ include file="init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">

<script>
    Liferay.Loader.define._amd = Liferay.Loader.define.amd;
    Liferay.Loader.define.amd = false;
</script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/responsive/2.5.0/js/dataTables.responsive.min.js"></script>
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dateFormatSearch = new SimpleDateFormat("yyyyMMdd");

    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

    String yes = LanguageUtil.get(bundle, "user.view.yes");
    String no  = LanguageUtil.get(bundle, "user.view.no");
    String all = LanguageUtil.get(bundle, "user.view.all");

    List<User> userList = (List<User>) request.getAttribute(AdeplusUsersPortletKeys.USER_LIST);
    long userCompId =  (long) request.getAttribute(AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT);
    long userClientId =  (long) request.getAttribute(AdeplusUsersPortletKeys.USER_CLIENT_ID_EDIT);
    UserCompany userComp =  (UserCompany) request.getAttribute(AdeplusUsersPortletKeys.USER_COMPANY);

    List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

    List<CompApplication> applicationsFromCompany = CompApplicationLocalServiceUtil.getApplicationsFromCompany(userCompId);
    boolean isModelMulti = false;
    List<CompClientApplication> compApplicationClient = CompClientApplicationLocalServiceUtil.getClientsByCompanyId(userCompId);
    if(Validator.isNotNull(compApplicationClient) && compApplicationClient.size() > 0){
        isModelMulti = true;
    }

    List<Long> appIds = new ArrayList<>();
    for(CompApplication ca:applicationsFromCompany){
        appIds.add(ca.getApplicationId());
    }

    Date now = new Date();

    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    calendar.add(Calendar.DAY_OF_MONTH, -30);

%>

<liferay-ui:error key="error-create-nif" message="company.view.save.error.nif" />
<liferay-ui:error key="error-create-email" message="company.view.save.error.email" />

<c:if test="<%=userCompId == 0%>">
    <h2><liferay-ui:message key="user.view.users"/></h2>
    <p><liferay-ui:message key="user.view.no.company"/></p>
</c:if>
<c:if test="<%=userCompId > 0 && (Validator.isNull(userComp) || !userComp.isAdmin())%>">
    <h2><liferay-ui:message key="user.view.users"/></h2>
    <p><liferay-ui:message key="user.view.no.admin"/></p>
</c:if>
<c:if test="<%=userCompId > 0 && Validator.isNotNull(userComp) && userComp.isAdmin()%>">
    <main class="content">
        <div class="formulario">
            <div class="title-group">
                <h2><liferay-ui:message key="user.view.users"/></h2>


                    <portlet:renderURL var="addUserURL">
                        <portlet:param name="userCompId" value="<%=String.valueOf(userCompId)%>" />
                        <portlet:param name="mvcPath" value="/user_MULTI.jsp"></portlet:param>
                    </portlet:renderURL>
                    <aui:button-row cssClass="button-holder">
                        <aui:button onClick="<%= addUserURL.toString() %>" value="user.view.new.user" cssClass="btn btn-outline-primary btn-sm" primary="true"></aui:button>
                        <span class="alert alert-warning"><liferay-ui:message key="bases.legales"/></span>
                    </aui:button-row>


            </div>

            <div class="row">
                <fieldset class="input-group-inline mt-3 mb-2">
                    <legend><liferay-ui:message key="user.view.active"/></legend>
                    <div class="custom-control custom-radio mr-4">
                        <input class="form-check-input" name="inpSearchActive" id="inpSearchActive1" value="<%=yes%>" type="radio" class="inpSearchActive" checked onChange="tabla.draw();" />
                        <label class="form-check-label" for="inpSearchActive1"><%=yes%></label>
                    </div>
                    <div class="custom-control custom-radio mr-4">
                        <input class="form-check-input" name="inpSearchActive" id="inpSearchActive2" value="No" type="radio" class="inpSearchActive" onChange="tabla.draw();" />
                        <label class="form-check-label" for="inpSearchActive2"><%=no%></label>
                    </div>
                    <div class="custom-control custom-radio mr-4">
                        <input class="form-check-input" name="inpSearchActive" id="inpSearchActive3" value="Todos" type="radio" class="inpSearchActive" onChange="tabla.draw();" />
                        <label class="form-check-label" for="inpSearchActive3"><%=all%></label>
                    </div>
                </fieldset>
            </div>

            <div class="row">
                <fieldset class="input-group-inline mb-3">
                    <legend><liferay-ui:message key="user.view.applications"/></legend>

                    <c:forEach items="<%=appList%>" var="app">
                        <%
                            Application app = (Application) pageContext.getAttribute("app");
                        %>
                        <div class="custom-control custom-checkbox mr-4">
                            <input class="custom-control-input clsApps" type="checkbox" id="id_${app.applicationId}" name="chkApp" value="${app.name}" <%=!appIds.contains(app.getApplicationId())?"":""%> onchange="tabla.draw();">
                            <label class="custom-control-label" for="id_${app.applicationId}">${app.name}</label>
                        </div>
                    </c:forEach>
                </fieldset>
            </div>

            <div class="row">
                <div class="col">
                <div id="spnCargando" class="loading-animation"></div>
                    <div id="spnTabla" style="display:none">
                    <table id="table_users" class="display" style="width:100%">
                        <thead>
                        <tr>
                            <th><liferay-ui:message key="user.view.name"/></th>
                            <th><liferay-ui:message key="user.view.last.name"/></th>
                            <th><liferay-ui:message key="user.view.nif"/></th>
                            <th><liferay-ui:message key="user.view.email"/></th>
                            <th><liferay-ui:message key="user.view.applications"/></th>
                            <%--<th><liferay-ui:message key="user.view.companies"/></th>--%>
                            <th><liferay-ui:message key="user.view.is.admin"/></th>
                            <th><liferay-ui:message key="user.view.createDate"/></th>
                            <th><liferay-ui:message key="user.view.date.lastLogin"/></th>
                            <th><liferay-ui:message key="user.view.endDate"/></th>
                            <th><liferay-ui:message key="user.view.active"/></th>
                            <th><liferay-ui:message key="user.view.options"/></th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </main>
</c:if>


<liferay-portlet:renderURL copyCurrentRenderParameters="false" varImpl="editUserMulti_URL">
    <portlet:param name="userCompId" value="ID_EMPRESA" />
    <portlet:param name="userEditId" value="ID_USUARIO" />
    <portlet:param name="mvcPath" value="/user_MULTI.jsp" />
</liferay-portlet:renderURL>
<liferay-portlet:renderURL copyCurrentRenderParameters="false" varImpl="editUserURL">
    <portlet:param name="userCompId" value="ID_EMPRESA" />
    <portlet:param name="userEditId" value="ID_USUARIO" />
    <portlet:param name="mvcPath" value="/user.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllUserByCompanyMULTI" var="getAllUserByCompanyMULTI_URL" >
    <portlet:param name="userCompId" value="<%=String.valueOf(userCompId)%>" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllUserByCompany" var="getAllUserByCompanyURL" >
    <portlet:param name="userCompId" value="<%=String.valueOf(userCompId)%>" />
</liferay-portlet:resourceURL>

<liferay-portlet:resourceURL  copyCurrentRenderParameters="false" id="getAllApplications" var="getAllApplicationsURL" />
<script>
     var tabla = null;
     var urlAjaxBaseMulti = "<%=getAllUserByCompanyMULTI_URL.toString()%>"  ;
     var urlAjaxBaseMono = "<%=getAllUserByCompanyURL.toString()%>"  ;
     var dateDatatableAlta = null;
     var dateDatatableLogin = null;
     var dateDatatableBaja = null;
     var htmlApps = "";
     var arrApps = null;
     var arrApplications = [];
     var modelUser = "<%=isModelMulti%>";
     var editUserURL = "<%=editUserURL%>";

 $(document).ready( function () {
        if(modelUser == "true"){
            editUserURL = "<%=editUserMulti_URL%>";
        }
        var elems = document.getElementsByClassName("clsApps");
         for(var i = 0; i < elems.length; i++){
             arrApplications.push(String(elems[i].id));
             //console.log(String(elems[i].id));
         }
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if (this.readyState == 4 && this.status == 200){
                arrApps = JSON.parse(this.responseText);
                initDataTable();
            }
        };
        xhr.open("GET", "<%=getAllApplicationsURL%>", true);
        xhr.send(null);
    });

    function initDataTable() {



        tabla = $('#table_users').DataTable( {
        "initComplete": function(settings, json) {
                        $("#spnCargando").hide();
                        $("#spnTabla").show();
                    },
            dom: 'Bfrtip',
            ajax: {
                            "url": (modelUser == "true")? urlAjaxBaseMulti : urlAjaxBaseMono,
                            "dataSrc": function ( json ) {
                                    return json.data;
                                }
                        },
                         "columns": [
                            {"data" : "nombre"},
                            {"data" : "apellidos"},
                            {"data" : "nif"},
                            {"data" : "email"},
                            {"data" : "apps"},
                            {"data" : "isAdmin"},
                            {"data" : "fechaCreacion"},
                            {"data" : "lastLogin"},
                            {"data" : "fechaBaja"},
                            {"data" : "activo"},
                            {"data" : "userId"} //editar
                        ],
                        "columnDefs": [
                            {
                                "targets"  : [10],
                                "orderable": false,
                                "render": function (data, type, row) {
                                    return '<a class="ico-acciones-tabla" href="#ENLACE#"> '.replace("#ENLACE#", editUserURL.replace("ID_EMPRESA", "<%=String.valueOf(userCompId)%>").replace("ID_USUARIO",data)) +
                                            '<img src="<%=themeDisplay.getPathThemeImages()%>/ico_edit.png" alt="<liferay-ui:message key="edit" />" /> </a>';

                                    }
                            },
                            {
                                "targets"  : [4], //Apps
                                "orderable": true,
                                "render": function (data, type, row) {
                                    if(modelUser == "false"){
                                        htmlApps = "";
                                        for(let i = 0; i < data.length; i++) htmlApps += data[i].name + "<br/>";
                                        return htmlApps;
                                    }else{
                                        if(data == null || data == undefined || data == " ") return "";
                                        return getNameAppById(data) + '<span class="badge badge-info viewDetail">+</span>'
                                    }
                                }
                             },
                             {
                                "targets"  : [5], //Administrador
                                "orderable": true,
                                "render": function (data, type, row) {
                                    return (data == true)? '<liferay-ui:message key="user.view.yes"/>': '<liferay-ui:message key="user.view.no"/>'  ;
                                }
                            },
                            {
                                "targets"  : [6], //Fecha de creacion
                                "orderable": true,
                                "render": function (data, type, row) {
                                    dateDatatableAlta = new Date(parseInt(data));
                                    return "<span class=\"hide\">" + dateDatatableAlta.toLocaleDateString('en-GB').split('/').reverse().join('') +  "</span><span>" + dateDatatableAlta.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>";
                                }
                            },
                            {
                                "targets"  : [7], //Fecha login
                                "orderable": true,
                                "render": function (data, type, row) {
                                        if(data == null || data == undefined || data == "" ) return "";
                                        dateDatatableLogin = new Date(parseInt(data));
                                                return "<span class=\"hide\">" + dateDatatableLogin.toLocaleDateString('en-GB').split('/').reverse().join('') +  "</span><span>" + dateDatatableLogin.toLocaleTimeString("es-ES", { hour: '2-digit', minute:'2-digit' }) + "  "
                                                + dateDatatableLogin.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>";
                                }
                            },
                             {
                                "targets"  : [8], //Fecha baja
                                "orderable": true,
                                "render": function (data, type, row) {
                                    if(data == null || data == undefined || data == "") return "";
                                    dateDatatableBaja = new Date(parseInt(data));
                                        return "<span class=\"hide\">" + dateDatatableBaja.toLocaleDateString('en-GB').split('/').reverse().join('') +  "</span><span>" + dateDatatableBaja.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>";
                                }
                            },
                            {
                                "targets"  : [9], //Administrador
                                "orderable": true,
                                "render": function (data, type, row) {
                                    return (data == true)? '<liferay-ui:message key="user.view.yes"/>': '<liferay-ui:message key="user.view.no"/>'  ;
                                }
                            }
                        ],
            responsive: true,
            pageLength: 25,
            lengthMenu: [[10, 25, 50, 100], [10, 25, 50, 100]],
            buttons: [
                'csv', 'excel', 'print', 'pdf'
            ],
            "language": {
                "sProcessing":     "<%=LanguageUtil.get(bundle, "user.view.datatable.sProcessing")%>",
                "sLengthMenu":     "<%=LanguageUtil.get(bundle, "user.view.datatable.sLengthMenu")%>",
                "sZeroRecords":    "<%=LanguageUtil.get(bundle, "user.view.datatable.sZeroRecords")%>",
                "sEmptyTable":     "<%=LanguageUtil.get(bundle, "user.view.datatable.sEmptyTable")%>",
                "sInfo":           "<%=LanguageUtil.get(bundle, "user.view.datatable.sInfo")%>",
                "sInfoEmpty":      "<%=LanguageUtil.get(bundle, "user.view.datatable.sInfoEmpty")%>",
                "sInfoFiltered":   "<%=LanguageUtil.get(bundle, "user.view.datatable.sInfoFiltered")%>",
                "sInfoPostFix":    "<%=LanguageUtil.get(bundle, "user.view.datatable.sInfoPostFix")%>",
                "sSearch":         "<%=LanguageUtil.get(bundle, "user.view.datatable.sSearch")%>",
                "sUrl":            "<%=LanguageUtil.get(bundle, "user.view.datatable.sUrl")%>",
                "sInfoThousands":  "<%=LanguageUtil.get(bundle, "user.view.datatable.sInfoThousands")%>,",
                "sLoadingRecords": "<%=LanguageUtil.get(bundle, "user.view.datatable.sLoadingRecords")%>",
                "oPaginate": {
                    "sFirst":    "<%=LanguageUtil.get(bundle, "user.view.datatable.sFirst")%>",
                    "sLast":     "<%=LanguageUtil.get(bundle, "user.view.datatable.sLast")%>",
                    "sNext":     "<%=LanguageUtil.get(bundle, "user.view.datatable.sNext")%>",
                    "sPrevious": "<%=LanguageUtil.get(bundle, "user.view.datatable.sPrevious")%>"
                },
                "oAria": {
                    "sSortAscending":  "<%=LanguageUtil.get(bundle, "user.view.datatable.sSortAscending")%>",
                    "sSortDescending": "<%=LanguageUtil.get(bundle, "user.view.datatable.sSortDescending")%>"
                },
                "buttons": {
                    "copy": "<%=LanguageUtil.get(bundle, "user.view.datatable.copy")%>",
                    "colvis": "<%=LanguageUtil.get(bundle, "user.view.datatable.colvis")%>"
                }
            }
        } );

        $.fn.dataTable.ext.search.push(
            function( settings, data, dataIndex ) {
                //CONDICIÓN MULTIPLE
                var numCond = 0;
                if(numCond == 0 &&
                    (document.querySelector("input[name='inpSearchActive']:checked").value  == "Todos" || data[9].indexOf(document.querySelector("input[name='inpSearchActive']:checked").value.substring(0,1)) == 0)
                ) {

                    if($( "input[name=chkApp]:checked").length == 0) return true; <!-- USAMOS EL NAME QUE LE HEMOS PUESTO A TODOS LOS CHECKBOX -->
                    for(var i= 0; i < arrApplications.length; i++){ <!-- NOMBRE DEL ARRAY EN DONDE HEMOS DEJADO LOS VALORES POSIBLES -->

                        console.log(arrApplications[i] + "  " + document.getElementById(arrApplications[i]).checked);
                        //console.log( " - " + document.getElementById(arrApplications[i]).checked);

                        if(document.getElementById(arrApplications[i]).checked){
                            var appValue = document.getElementById(arrApplications[i]).value;

                            console.log( "   * " + appValue + " == " + data[2]);
                            if(data[4].indexOf(appValue) != -1){
                                return true;
                            }
                        }
                    }
                }

                return false;

            }
        );

        $('#inpSearchActive').change( function() {
            tabla.draw();
        } );

        $('.clsApps').change( function() {
            tabla.draw();
        } );

        tabla.draw();

    }

    function getNameAppById(_data){
        if(_data == null || _data == "") return "";
        _data = JSON.parse(_data);
        var resultado = "";
        for(let x = 0; x < _data.length; x++){
            for(let c = 0; c < arrApps.data.length; c++){
                if(arrApps.data[c].appId == _data[x].appId){
                    if(resultado != "") resultado += ", ";
                    resultado += arrApps.data[c].name;
                    break;
                }
            }
        }
        return resultado;
        }

</script>
