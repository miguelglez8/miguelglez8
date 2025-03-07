<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.company.configuration.web.constants.CompanyConfigurationPortletKeys" %>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>


<%@ include file="init.jsp" %>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.5/js/dataTables.responsive.min.js">


<script>
	Liferay.Loader.define._amd = Liferay.Loader.define.amd;
	Liferay.Loader.define.amd = false;
</script>
<script type="text/javascript" charset="utf8" src="https://cdn.ckeditor.com/4.19.0/standard/ckeditor.js"></script>
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
<%

	long companyEditId = 0;
	if(Validator.isNotNull(CanalEticoUserUtil.getUserDefaultCompany(themeDisplay.getUserId()))){
	    companyEditId=CanalEticoUserUtil.getUserDefaultCompany(themeDisplay.getUserId()).getCompId();
	}

	/*Comp companyFromUser = CanalEticoUserUtil.getUserDefaultCompanyInCanalEtico(themeDisplay.getUserId());

	if(Validator.isNotNull(companyFromUser)){
		companyEditId = companyFromUser.getCompId();
	}
*/
	boolean hasPermission = true;
    String observacionesHTML = "";
	Comp comp = null;
	com.adeplus.liferay.portlet.suite.manager.model.Comp compania=null;
	if(companyEditId > 0 && hasPermission) {
		comp = CompLocalServiceUtil.fetchComp(companyEditId);
		compania=com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil.fetchComp(companyEditId);
		observacionesHTML = HtmlUtil.escape(comp.getObservations());
	}

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

%>

<c:if test="<%=!hasPermission%>">
	<div class="formulario">
		<div class="title-group">
			<h2><liferay-ui:message key="company.view.configuration"/></h2>
			<p><liferay-ui:message key="company.view.no.company.permission"/></p>
		</div>
	</div>
</c:if>

<c:if test="<%=Validator.isNotNull(compania)%>">
	<liferay-portlet:actionURL name="/canalv2/configuration/save" var="saveCompURL" />
	<aui:form  name="edit_form" action="<%=saveCompURL.toString()%>" method="post" enctype="multipart/form-data">

		<aui:input name="<%=CompanyConfigurationPortletKeys.COMPANY_ID_EDIT%>" value="<%=companyEditId%>" type="hidden" />
		<aui:input name="aaa" value="<%=comp%>" type="hidden" />
		<aui:input name="eliminarPolitic" value="0" type="hidden" />
		<aui:input name="eliminarProced" value="0" type="hidden" />
		<div class="content">
			<div class="titulo-seccion">
				<h2><liferay-ui:message key="company.view.configuration"/></h2>
			</div>

            <div class="row">
                <div class="col-md-12">
                    <h4 class="titleConfiguration"><liferay-ui:message key="company.view.logo.select"/></h4>
                    <c:if test="<%=Validator.isNotNull(compania) && compania.getLogo() > 0%>">
                        <%
                            DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(compania.getLogo()));
                            String url = "";
                            if(Validator.isNotNull(dlFileEntry)) {
                                url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
                                        dlFileEntry.getFolderId() + "/" + dlFileEntry.getTitle();
                            }
                        %>
                        <div class="img-thumbnail-frame">
                            <img src="<%=url%>" class="img-thumbnail" />
                        </div>
                    </c:if>
                    <p><liferay-ui:message key="company.view.logo.description"/></p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <h4 class="titleConfiguration"><liferay-ui:message key="company.view.friendlyURL"/></h4>
                    <p><liferay-ui:message key="company.view.friendlyURL.description"/></p>
                    <div class="form-group input-text-wrapper">
                        <aui:input name="<%=CompanyConfigurationPortletKeys.COMPANY_FRIENDLYURL%>" type="text" label="" placeholder="Indique valor" value="<%=Validator.isNotNull(comp)?comp.getUrlId():""%>" >
                            <aui:validator errorMessage="company.error.alfanum" name="custom">
                            function() {
                                var regex=/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[A-Z]).+$/;
                                var myUrl=document.getElementById('<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_FRIENDLYURL%>').value;
                                return regex.test(myUrl);

                            }
                            </aui:validator>
                        </aui:input>
                    </div>
                    <p class="hasBtn"><liferay-ui:message key="company.view.link"/>
                        <a href="<%=themeDisplay.getPortalURL()%>/web/canal-etico/registro?id=<%=(Validator.isNotNull(comp)&&!"".equals(comp.getUrlId()))?comp.getUrlId():companyEditId%>" target="_blank" id="urlDenuncia">
                            <%=(Validator.isNotNull(comp)&&!"".equals(comp.getUrlId()))?themeDisplay.getPortalURL()+"/web/canal-etico/registro?id="+comp.getUrlId():themeDisplay.getPortalURL()+"/web/canal-etico/registro?id="+companyEditId%>
                        </a>
                        <a class="btn-acciones icon-copy" onclick="copiarUrl()">
                            <img src="<%=request.getContextPath()%>/image/copy.png">
                        </a>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <h4 class="titleConfiguration"><liferay-ui:message key="company.view.grupoEmpresa"/></h4>
                    <input class="form-check-input form-check-inline" type="checkbox" id="toggle-button">
                        <label for="toggle-button"><liferay-ui:message key="company.view.grupoEmpresa.description"/></label>
                    </input>
                    <div class="grupoEmpresa">
                        <div class="d-flex justify-content-end">
                            <aui:button cssClass="btn-primary btn-new-entity" value="company.add.grupoEmpresa" onClick="mostrarModal()"/>
                        </div>
                        <table id="table_companyGroup" class="display hasActionButtons" style="width:100%">
                            <thead>
                            <tr>
                                <th><liferay-ui:message key="company.view.name"/></th>
                                <th><liferay-ui:message key="company.view.cif"/></th>
                                <th><liferay-ui:message key="company.view.employees"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                        <br>
                        <div class="form-group">
                            <p><liferay-ui:message key="company.observation.grupoEmpresa"/></p>
                            <textarea name="<portlet:namespace />observationGrupo" id="<portlet:namespace />observationGrupo"></textarea>
                        </div>
                    </div>
                </div>
            </div>
             <div class="row">
                <div class="col-md-12">
                    <h4 class="titleConfiguration"><liferay-ui:message key="company.view.documentacion"/></h4>
                    <p class="subtitleConfiguration"><liferay-ui:message key="company.view.politica"/></p>
                    <p><liferay-ui:message key="company.view.politica.description"/></p>
                    <div class="form-group inputWidthLimit">
                        <div class="prv-file-container">

                                <aui:input name="politica" label="" type="file" >
                                </aui:input>

                            <div class="prv-input-file__span">
                                <span class="prv-input-file__btn" id="prv-input-file_politica">Examinar...</span>
                                <span class="prv-input-file__txt" id="prv-input-file_txt_politica"></span>
                                <span class="btn-ico delete-ico margin-top-delete delete-file" data-file="politica"></span>
                            </div>
                        </div>
                        <!-- Aquin metemos lo de guardar el documento-->
                        <c:if test="<%=Validator.isNotNull(compania) && Validator.isNotNull(comp)%>">
                            <%
                            String downloadURL="";
                            if(!Validator.isBlank(comp.getPoliticaFileId())){
                                DLFileEntry dlFileEntryPolitica = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(comp.getPoliticaFileId()));

                                if(Validator.isNotNull(dlFileEntryPolitica)){
                                    long idPolitica=dlFileEntryPolitica.getFileEntryId();
                                    long idPoliticaGrupo=dlFileEntryPolitica.getGroupId();
                                     downloadURL = "/documents/" + dlFileEntryPolitica.getGroupId() +"/"+ dlFileEntryPolitica.getFolderId() + "/"
                                                           + dlFileEntryPolitica.getTitle() + "/" + dlFileEntryPolitica.getUuid();
                                }
                            %>
                            <div id="descarga-politica" class="prv-descarga">
                                <a class="prv-enlace" href="<%= downloadURL %>"><%=dlFileEntryPolitica.getTitle()%></a>
                                <button class="btn-delete" onclick="eliminarPolitic()" type="button">
                                  <span class="sReader">Eliminar</span>
                                </button>
                            </div>
                            <%}%>
                        </c:if>

                    </div>
                    <p class="subtitleConfiguration"><liferay-ui:message key="company.view.procedimiento"/></p>
                    <p><liferay-ui:message key="company.view.procedimiento.description"/></p>
                    <div class="form-group inputWidthLimit">
                        <div class="prv-file-container">

                                <aui:input name="procedimiento" label="" type="file" >
                                </aui:input>

                            <div class="prv-input-file__span">
                                <span class="prv-input-file__btn" id="prv-input-file_procedimiento">Examinar...</span>
                                <span class="prv-input-file__txt" id="prv-input-file_txt_procedimiento"></span>
                                <span class="btn-ico delete-ico margin-top-delete delete-file" data-file="procedimiento"></span>
                            </div>
                        </div>
                    </div>
                    <c:if test="<%=Validator.isNotNull(compania) && Validator.isNotNull(comp)%>">
                        <%
                        String downloadURLProcedimiento="";
                        if(!Validator.isBlank(comp.getProcedimientoFileId())){
                            DLFileEntry dlFileEntryProcedimiento = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(comp.getProcedimientoFileId()));

                            if(Validator.isNotNull(dlFileEntryProcedimiento)){
                                 downloadURLProcedimiento = "/documents/" + dlFileEntryProcedimiento.getGroupId() +"/"+ dlFileEntryProcedimiento.getFolderId() + "/"
                                                           + dlFileEntryProcedimiento.getTitle() + "/" + dlFileEntryProcedimiento.getUuid();
                            }

                        %>
                        <div class="prv-descarga" id="descarga-procedimiento">
                            <a class="prv-enlace" href="<%= downloadURLProcedimiento %>"><%=dlFileEntryProcedimiento.getTitle()%></a>
                            <button onclick="eliminarProced()" class="btn-delete" type="button">
                              <span class="sReader">Eliminar</span>
                            </button>
                        </div>
                        <%}%>
                    </c:if>

                    <div class="button-holder d-flex justify-content-end">
                        <div class="btn-wrapper">
                            <aui:button cssClass="btn btn-tertiary btn-lg btn-new-comp" type="submit" />
                        </div>
                    </div>
                </div>
            </div>
		</div>

	</aui:form>

	<div class="contentModal modalAddEntidad" id="modalContainer" style="border-radius: 10px;">
        <div class="headerModal">
            <p id="addEntidad"><liferay-ui:message key="company.add.grupoEmpresa"/></p>
            <p class="hide" id="editEntidad"><liferay-ui:message key="company.edit.grupoEmpresa"/></p>
        </div>
        <div class="container">
            <div class="row myrow">
                <div id="brand-logo-modal" class="col-3">
                    <label class="labelInput"><liferay-ui:message key="company.view.name"/>*</label>
                </div>
                <div class="col-6">
                <aui:input name="<%=CompanyConfigurationPortletKeys.COMPANY_NAME%>" cssClass="inputModal" type="text" label="">
                    <aui:validator name="required" errorMessage="company.error.required" />
                </aui:input>
                <p class="text-danger hide" id="requiredEntidad"><liferay-ui:message key="input.required"/></p>
                </div>
            </div>
            <div class="row myrow">
                <div class="col-3">
                    <label class="labelInput"><liferay-ui:message key="company.view.cif"/>*</label>
                </div>
                <div class="col-6">
                <aui:input name="<%=CompanyConfigurationPortletKeys.COMPANY_CIF%>" cssClass="inputModal" type="text" label="">
                    <aui:validator name="required" errorMessage="company.error.required" />
                </aui:input>
                <p class="text-danger hide" id="requiredCif"><liferay-ui:message key="input.required"/></p>
                </div>
            </div>
            <div class="row myrow">
                <div class="col-3">
                   <label class="labelInput"><liferay-ui:message key="company.view.employees"/>*</label>
                </div>
                <div class="col-6">
                <aui:input name="<%=CompanyConfigurationPortletKeys.COMPANY_EMPLOYE%>" cssClass="inputModal" type="number" min="1" label="" >
                    <aui:validator name="required" errorMessage="company.error.required" />
                </aui:input>
                <p class="text-danger hide" id="requiredNumber"><liferay-ui:message key="input.required"/></p>
                </div>
            </div>
        </div>
         <div class="footerModal">
            <button type="button" class="btn-primary buttonModal" id="botonCrear" onclick="createNewGroup(0)" data-dismiss="modal" aria-label="Close">
                <liferay-ui:message key="addGroup"/>
            </button>
            <button type="button" class="btn-primary buttonModal " id="botonModificar" onclick="modifedGroup()" data-dismiss="modal" aria-label="Close">
                <liferay-ui:message key="addGroup"/>
            </button>
            <button type="button" class="buttonModal closeMyModal" data-dismiss="modal" onclick="displayButtons()" aria-label="Close">
                <liferay-ui:message key="label.button.cancelar"/>
            </button>

        </div>
    </div>

    <div class="contentModal alert-modal warning" id="modalContainerEliminar" style="border-radius: 15px; width: 650px;">
        <div class="headerModal">
            <p id="addEntidad"><span><liferay-ui:message key="label.header.eliminar"/></p>
        </div>
        <div class="container">
            <div class="col-12 text-center">
                <p class="text-center"><liferay-ui:message key="eliminar.grupo"/></p>
            </div>
        </div>
         <div class="footerModal">
             <button type="button" class="btn-primary buttonModal " id="endComAcc" onclick="deleteGroup()" data-dismiss="modal" aria-label="Close">
                <liferay-ui:message key="label.button.finalizar"/>
             </button>

            <button type="button" class="buttonModal closeMyModal " id="btnCancelCom"  data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="label.button.cancelar"/>
            </button>
         </div>
    </div>


    <div class="divModal" id="modalPortlet">
    </div>
</c:if>
<portlet:resourceURL id="getAllGroupByCompany" var="getAllGroupByCompanyURL"/>
<portlet:resourceURL id="createGroupByCompany" var="createGroupByCompanyURL"/>
<portlet:resourceURL id="deleteGroupByCompany" var="deleteGroupByCompanyURL"/>

<script>

var tabla = null;
var urlAjaxBase = "<%=getAllGroupByCompanyURL.toString()%>" ;
var jsonData = null;
var idGrupoEliminar=0;

function createEditor(value) {

    var editor = CKEDITOR.replace('<portlet:namespace />observationGrupo', {
        on: {
            instanceReady: function() {

            }
        }
    });
    editor.setData(value);

}

function copiarUrl(){
    var str=$("#urlDenuncia").attr('href')
    var aux = document.createElement("input");
    aux.setAttribute("value", str);
    document.body.appendChild(aux);
    aux.select();
    document.execCommand("copy");
    document.body.removeChild(aux);
}
function eliminarPolitica(){
    document.getElementById('<portlet:namespace />politica').value = "";
}

function eliminarProcedimiento(){
    document.getElementById('<portlet:namespace />procedimiento').value = "";
}

function eliminarProced(){
    $("#<portlet:namespace/>eliminarProced").val("1");
    $("#descarga-procedimiento").addClass("hide");

}
function eliminarPolitic(){
    $("#<portlet:namespace/>eliminarPolitic").val("1");
    $("#descarga-politica").addClass("hide");

}

function mostrarModal(){
    $('#modalPortlet').css('display','block');
    $('#modalContainer').css('display','block');
}

function mostrarModalEliminar(){
    $('#modalPortlet').css('display','block');
    $('#modalContainerEliminar').css('display','block');
}


$(document).ready( function () {
    var txtObservaciones='<%=observacionesHTML%>';
    createEditor(txtObservaciones);


    $("#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_FRIENDLYURL%>").on("keyup",function(e){
        var str=e.target.value
        var strReplace=str.replace(/[^\w]/gi, '')
        e.target.value=strReplace
    })

    $( "#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_NAME%>").on( "blur", function() {
          if($( "#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_NAME%>").val()==""){
            $('#requiredEntidad').removeClass('hide');
          }else{
            $('#requiredEntidad').addClass('hide');
          }
    });

    $( "#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_CIF%>").on( "blur", function() {
      var cifValue=$( "#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_CIF%>").val()
      if(cifValue==""){
        $('#requiredCif').removeClass('hide');
      }else{
        $('#requiredCif').addClass('hide');
      }
    });

    $( "#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_EMPLOYE%>").on( "blur", function() {
          var employeesValue=$( "#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_EMPLOYE%>").val()
          if(employeesValue==""){
            $('#requiredNumber').removeClass('hide');
          }else{
            $('#requiredNumber').addClass('hide');
          }
        });


tabla = $('#table_companyGroup').on('preXhr.dt', function ( e, settings, data ) {
		                console.log("preXhr.dt load")
                        //$("#spnCargando").hide();
                        //$("#spnTabla").show();
            }).DataTable( {
            "initComplete": function(settings, json) {
                        console.log("initComplete load")
                        $("#spnCargando").hide();
                        $("#spnTabla").show();
            },
            "rowCallback": function( row, data ) {
                if(row.querySelector('.poptip') !== null ){
                   row.querySelectorAll('.poptip').forEach((elem, i) => {
                       loadPoptip( elem );
                   });
                }
            },
			dom: 'Bfrtip',
			ajax: {
                "url": urlAjaxBase,
                "dataSrc": function ( json ) { jsonData = json.data; if(jsonData.length>=2 && !document.getElementById('toggle-button').checked){$("#toggle-button").click()}return json.data; }
            },
            "columns": [
                {"data" : "nombre"},
                {"data" : "cif"},
                {"data" : "empleados"},
                {"data" : "groupId"} //editar
            ],
            "columnDefs": [
                {
                    "targets"  : [0],
                    "orderable": true,
                    "render": function (data, type, row) {
                        return '<span class="poptip" title="'+data+'">'+data+'</span>';
                    }
                },
                {
                  "targets"  : [3],
                  "orderable": false,
                  "render": function (data, type, row) {
                            if(data>0){
                                return '<div class="acciones-wrapper"><button class="ico-acciones-tabla editar" type="button" onclick="#ENLACE#"> '.replace("#ENLACE#", "modificarGrupo("+data+")")  +
                                  '</button>'+
                                  '<button class="ico-acciones-tabla eliminar" type="button" onclick="#ENLACE#"> '.replace("#ENLACE#", "eliminarGrupo("+data+")")  +
                                    '</button></div>';
                                }else{
                                     return '';

                                     }
                          }
                }
            ],
			responsive: true,
			pageLength: 25
		 } );

$('.btn-new-entity').removeClass("btn-secondary")
$('.btn-new-comp').removeClass("btn-primary")
})

function createNewGroup(id){
    var name=$('#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_NAME%>').val()
    var cif=$('#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_CIF%>').val()
    var employees=$('#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_EMPLOYE%>').val()
    var idGrupo=id

    if(cif==""){
        $('#requiredCif').removeClass('hide');
    }else{
        $('#requiredCif').addClass('hide');
    }
    if(name==""){
        $('#requiredEntidad').removeClass('hide');
    }else{
        $('#requiredEntidad').addClass('hide');
    }
    if(employees==""){
        $('#requiredNumber').removeClass('hide');
    }else{
        $('#requiredNumber').addClass('hide');
    }
    if(!isNaN(employees) && cif!="" && name!=""){
        if(employees>0){
            $.ajax({
                type: "POST",
                url: '${createGroupByCompanyURL}',
                data: {
                    '<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_GROUP%>':idGrupo,
                    '<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_EMPLOYE%>' : employees,
                    '<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_CIF%>' : cif,
                    '<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_NAME%>' : name
                },
                success: function (result) {
                  console.log("Okay: "+result)
                  tabla.ajax.reload()
                  $('.closeMyModal').click()
              }
            });
        }
    }



}
function modificarGrupo(id){
    mostrarModal();
    var grupo = getGrupoById(id);
    $('#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_NAME%>').val(grupo.nombre);
    $('#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_CIF%>').val(grupo.cif);
    $('#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_EMPLOYE%>').val(grupo.empleados);
    $('#botonModificar').css('display','block');
    $('#botonCrear').css('display','none');
    $('#botonModificar').attr('idGrupo',id);
    $('#editEntidad').removeClass('hide');
    $('#addEntidad').addClass('hide');
}

function getGrupoById(_id){
    var res = null;
    for(var x in jsonData){
        if(jsonData[x].groupId == _id){
            res = jsonData[x];
            break;
        }
    }
    return res;
}

function modifedGroup(){
    var idGrupo=$('#botonModificar').attr('idGrupo');
    $('#botonModificar').css('display','none');
    $('#botonCrear').css('display','block');
    createNewGroup(idGrupo);
}
function displayButtons(){
    $('#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_NAME%>').val("");
    $('#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_CIF%>').val("")
    $('#<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_EMPLOYE%>').val("")
    $('#botonModificar').css('display','none');
    $('#botonCrear').css('display','block');
    $('#addEntidad').removeClass('hide');
    $('#editEntidad').addClass('hide');
    $('#requiredNumber').addClass('hide');
    $('#requiredEntidad').addClass('hide');
    $('#requiredCif').addClass('hide');
}
function eliminarGrupo(id){
    idGrupoEliminar=id
    mostrarModalEliminar();
    console.log("entre");

}

function deleteGroup(){
    $.ajax({
        type: "POST",
        url: '${deleteGroupByCompanyURL}',
        data: {
            '<portlet:namespace/><%=CompanyConfigurationPortletKeys.COMPANY_GROUP%>':idGrupoEliminar
        },
        success: function (result) {
          console.log("Okay: "+result)
          tabla.ajax.reload()
          $('.closeMyModal').click()
      }
    });

}


$(document).ready(function(){
                $("#prv-input-file_file, #prv-input-file_txt_procedimiento, #prv-input-file_procedimiento").on("click", function(e){
                    $('#<portlet:namespace/>procedimiento').click();
                });

                $('#<portlet:namespace/>procedimiento').on("change", function(e){
                    var fileName = $(this).val().split('\\').pop();
                    $("#prv-input-file_txt_procedimiento").html("");
                    $("#prv-input-file_txt_procedimiento").html(fileName);
                });


        $("#prv-input-file_file, #prv-input-file_txt_politica, #prv-input-file_politica").on("click", function(e){
            $('#<portlet:namespace/>politica').click();
        });

        $('#<portlet:namespace/>politica').on("change", function(e){
            var fileName = $(this).val().split('\\').pop();
            $("#prv-input-file_txt_politica").html("");
            $("#prv-input-file_txt_politica").html(fileName);
        });

        $(".delete-file").on("click", function(e) {
            var name = $(this).data("file");
            $("#prv-input-file_txt_" + name).html("");
            $("#<portlet:namespace/>" + name).val("");
        });
    });
</script>