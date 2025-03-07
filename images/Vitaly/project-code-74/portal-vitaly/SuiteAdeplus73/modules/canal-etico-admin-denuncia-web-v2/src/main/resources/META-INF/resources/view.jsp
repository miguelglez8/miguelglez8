<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.model.Categoria" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.v2.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.categoria.web.constants.CategoriasPortletKeys" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserApplicationLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserApplicationClientLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.UserCompany" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.constants.CanalEticoCommonsPortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.PrefsPropsUtil" %>
<%@ page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.model.Role" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.canal.etico.liferay.portlet.admin.denuncia.web.constants.AdminDenunciaV2PortletKeys" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationPK" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.Application" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.UserApplication" %>
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
	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
	long companyEditId = 0;
	if(Validator.isNotNull(CanalEticoUserUtil.getUserDefaultCompany(themeDisplay.getUserId()))){
        companyEditId=CanalEticoUserUtil.getUserDefaultCompany(themeDisplay.getUserId()).getCompId();
    }
    long idLanguage=1;
    String localeLang=themeDisplay.getUser().getLocale().toString();
    if(localeLang.equalsIgnoreCase("ca_ES")){
        idLanguage=2;
    }else if(localeLang.equalsIgnoreCase("en_US")){
        idLanguage=3;
    }
	List<Categoria> categoriaList = CategoriaLocalServiceUtil.getAllCategoriesByComp(companyEditId);
	Map<Long,String> categoriaCombo = new HashMap<Long,String>();
	String idioma = themeDisplay.getUser().getLocale().toString();
	for(Categoria c : categoriaList){
	    if(Validator.isNull(c.getDeleteData())){
	        if(c.getNombre_cat().isEmpty() || c.getNombre_eng().isEmpty() ){
	            categoriaCombo.put(c.getPrimaryKey(), c.getNombre());
	        }else if(idioma.equals("es_ES")){
                categoriaCombo.put(c.getPrimaryKey(), c.getNombre());
            }else if(idioma.equals("ca_ES") && !c.getNombre_cat().isEmpty()){
                categoriaCombo.put(c.getPrimaryKey(), c.getNombre_cat());
            }else if(idioma.equals("en_US") && !c.getNombre_eng().isEmpty()){
                categoriaCombo.put(c.getPrimaryKey(), c.getNombre_eng());
            }
        }
	}
    Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");

	//Modelo 2
	 List <UserCompany> users=new ArrayList<UserCompany>();
	if(CanalEticoCommonsPortletKeys.ITS_NEW_VERSION){
        users=UserApplicationClientLocalServiceUtil.getUsersByEmpresaIdAndApplicationWithLicense(
                app.getApplicationId(),0,companyEditId);

    }else{
    //Modelo 1
        users=new ArrayList<UserCompany>();
        List <UserCompany> usersNoOrder=UserCompanyLocalServiceUtil.getUsersFromCompany(companyEditId);
        for(UserCompany uc:usersNoOrder){
            UserApplicationPK pk = new UserApplicationPK(uc.getUserId(), companyEditId, app.getApplicationId());
            UserApplication userApp = UserApplicationLocalServiceUtil.fetchUserApplication(pk);
            if(Validator.isNotNull(userApp) && Validator.isNull(userApp.getDeleteDate())){
                users.add(uc);
            }
        }
    }
    Collections.sort(users, Comparator.comparing(UserCompany::getName,String.CASE_INSENSITIVE_ORDER));
	String roleName = PrefsPropsUtil.getString("canal.etico.rol.company.admin");
	Role role=null;
    role = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), roleName);
    boolean hasRole=false;
    for (Role rol:UserLocalServiceUtil.fetchUser(themeDisplay.getUserId()).getRoles()){
        if(rol.equals(role)){
            hasRole=true;
        }
    }
    hasRole=CanalEticoUserUtil.getIsAdmin(hasRole,companyEditId,themeDisplay.getUserId());


%>
<h2 class="portlet-title-text"><liferay-ui:message key="comunicacion.title"/></h2>

 <div class="row d-none" id="capaErrores">
        <div class="col">
              <div class="" style="">
                    <div class="alert alert-dismissible alert-danger" role="alert">
                        <span id="spnError"></span>
                    </div>
                </div>
        </div>
</div>

<div class="content">
    <div  id="capaFilters" class="row">
        <div class="col-2">
            <div id="spnInputSearch"> </div>
        </div>

        <div class="col-2">
            <label for="cmbEstado">
                <liferay-ui:message key="accion.datatable.filtro.estado.title" />
            </label>
            <select class="ml-0 form-control" id="cmbEstado" name="cmbEstado"
                label='<liferay-ui:message key="categoria.datatable.filtro.activa.title"/>'
                placeholder='<liferay-ui:message key="accion.datatable.filtro.activa.noIndicado"/>'
                onchange="tabla.draw()" value="">
                <option disabled  selected value=""></option>
                <option value="0"><liferay-ui:message key="estado.datatable.filtro.activa.pendiente"/></option>
                <option value="1"><liferay-ui:message key="estado.datatable.filtro.activa.admision"/></option>
                <option value="2"><liferay-ui:message key="estado.datatable.filtro.activa.investigacion"/></option>
                <option value="3"><liferay-ui:message key="estado.datatable.filtro.activa.finalizacion"/></option>
                <option value="4"><liferay-ui:message key="estado.datatable.filtro.activa.finalizada"/></option>
                <option value="5"><liferay-ui:message key="estado.datatable.filtro.activa.caducada"/></option>

            </select>
        </div>
        <div class="col-2">
            <label for="cmbCategoria">
                <liferay-ui:message key="accion.datatable.filtro.categoria.title" />
            </label>
            <select class="ml-0 form-control" id="cmbCategoria" name="cmbCategoria"
                label='<liferay-ui:message key="categoria.datatable.filtro.activa.title"/>'
                onchange="tabla.draw()">
                <option value="-1" selected disabled></option>
                <c:forEach var="cat" items="<%=categoriaCombo%>">
                    <option value="${cat.key}">${cat.value}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-2">
            <label for="cmbGestor">
                <liferay-ui:message key="accion.datatable.filtro.gestor.title" />
            </label>
            <select class="ml-0 form-control" id="cmbGestor" name="cmbGestor" label='<liferay-ui:message key="categoria.datatable.filtro.activa.title"/>' onchange="tabla.draw()">
                <option value="-1" disabled selected hidden><liferay-ui:message key="select.gestor.noIndicada"/></option>
                <option value="0"><liferay-ui:message key="estado.datatable.filtro.activa.pendiente"/></option>
                <c:forEach var="usr" items="<%=users%>">
                    <c:if test="${empty usr.userEndDate}">
                        <option value="${usr.userId}">${usr.name} ${usr.lastName}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>

        <div class="col-2">
            <label for="<portlet:namespace/>startDate">
                <liferay-ui:message key="accion.datatable.filtro.fecha.recepcion.title" />
            </label>
            <liferay-ui:input-date name="startDate" cssClass="prv-input-date datepicker"  nullable="true" showDisableCheckbox="false"/>
        </div>

        <div class="col-2">
            <label for="<portlet:namespace/>endDate">
                <liferay-ui:message key="accion.datatable.filtro.fecha.hasta.title" />
            </label>
                   <liferay-ui:input-date name="endDate" cssClass="prv-input-date datepicker" nullable="true" showDisableCheckbox="false"/>
        </div>
    </div>

    <fieldset class="input-group-inline mt-3 mb-2">

    </fieldset>



    <div class="row">
        <div class="col">
            <table id="table" class="display" style="width:100%">
                <thead>
                <tr>
                    <th><liferay-ui:message key="accion.datatable.col.codigo"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.fechaAlta"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.estado"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.categoria"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.entidad"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.gestor"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.fechaBaja"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.fechaCaducidad"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="contentModal" id="modalContainer" style="border-radius: 10px; width: 650px;">
        <div class="col-md-12 headerModal">
            <p id="addEntidad"><liferay-ui:message key="categorizar.comunicacion"/></p>
            <p id="addCaducidad" class="hide"><liferay-ui:message key="prorrogar.caducidad"/></p>
            <p id="editEntidad" class="hide" ><liferay-ui:message key="accion.datatable.editar.title"/></p>
        </div>
        <div class="hide" id="containerInputs">
            <div class="container">
                <div class="row myrow">
                   <div class="col-3">
                       <label for="asuntoCom" class="labelInput"><liferay-ui:message key="label.asunto"/></label>
                   </div>
                   <div class="col-9">
                        <input type="text" disabled style="width:100%;" id="asuntoCom" name="asuntoCom" />
                   </div>
                </div>
                <div class="row myrow">
                   <div class="col-3">
                       <label for="descripcionCom" class="labelInput"><liferay-ui:message key="label.descripcion"/></label>
                   </div>
                   <div class="col-9">
                        <div id="descripcionCom" class="disabled isInput hasScroll" style="height: 200px; pointer-events: visible;"></div>
                   </div>
                </div>
                <div class="row myrow hide" id="inputSelectCategoria">
                    <div class="col-3">
                        <label for="<portlet:namespace/>categoriaSelect" class="labelInput"><liferay-ui:message key="accion.datatable.col.categoria"/>*</label>
                    </div>
                    <div class="col-9">
                        <aui:select name="categoriaSelect" label="">
                            <option value=""><liferay-ui:message key="select.categoria"/></option>
                            <c:forEach var="cate" items="<%=categoriaList%>">
                                <c:if test="${cate.deleteData==null}">
                                    <option value="${cate.primaryKey}">${cate.nombre}</option>
                                </c:if>
                            </c:forEach>
                        </aui:select>
                        <p class="text-danger hide" id="invalidCategoria"><liferay-ui:message key="invalid.categoria"/></p>
                    </div>
                </div>
                <div class="row myrow hide" id="descCat">
                   <div class="col-3">
                       <label for="<portlet:namespace/>descripcionCat" class="labelInput"><liferay-ui:message key="label.indiquela"/></label>
                   </div>
                   <div class="col-9">
                        <aui:input type="text" label="" name="descripcionCat"></aui:input>
                   </div>
                </div>
                <div class="row myrow hide" id="inputSelectGestor">
                    <div class="col-3">
                        <label for="<portlet:namespace/>gestorSelect" class="labelInput"><liferay-ui:message key="asignar.usuarios"/>*</label>
                    </div>
                    <div class="col-9">
                        <aui:select name="gestorSelect" label="" >
                            <c:forEach var="gest" items="<%=users%>">
                                <c:if test="${empty gest.userEndDate}">
                                    <option value="${gest.userId}">${gest.name} ${gest.lastName}</option>
                                </c:if>
                            </c:forEach>
                        </aui:select>
                        <p class="text-danger hide" id="invalidGestor"><liferay-ui:message key="invalid.gestor"/></p>
                    </div>
                </div>
            </div>
            <div id="footerCategoria" class="footerModal hide">
                 <button type="button" class="btn-primary buttonModal " id="btnSaveCat" onclick="asignarCat()" data-dismiss="modal" aria-label="Close">
                    <liferay-ui:message key="btn.aniadir.categoria"/>
                 </button>

                <button type="button" class="buttonModal closeMyModal " id="btnCancelCateg"  data-dismiss="modal" aria-label="Close">
                   <liferay-ui:message key="categoria.view.dialog.cancelar"/>
                </button>
            </div>
            <div id="footerGestor" class="footerModal hide">
                 <button type="button" class="btn-primary buttonModal " id="btnSaveGestor" onclick="asignarGstr()" data-dismiss="modal" aria-label="Close">
                    <liferay-ui:message key="btn.aniadir.gestor"/>
                 </button>

                <button type="button" class="buttonModal closeMyModal " id="btnCancelGest"  data-dismiss="modal" aria-label="Close">
                   <liferay-ui:message key="categoria.view.dialog.cancelar"/>
                </button>
            </div>
        </div>
        <div class="hide" id="containerCaducidad">
            <div class="container">
                <div class="col-12 text-center" id="prorrogaPrevia">
                   <p class="text-center"><liferay-ui:message key="prorrogar.caducidad.texto.previo"/></p>
                   <p class="text-center"><liferay-ui:message key="prorrogar.caducidad.texto2"/></p>
                </div>
                <div class="col-12 text-center hide" id="prorrogaAceptada">
                   <p class="text-center"><liferay-ui:message key="prorrogar.caducidad.texto1"/></p>
                   <p class="text-center"><liferay-ui:message key="prorrogar.caducidad.texto2"/></p>
                </div>
            </div>
            <div class="footerModal">
                <button type="button" class="btn-primary buttonModal " id="btnContinueCad" onclick="continueModal()" aria-label="Close">
                    <liferay-ui:message key="btn.prorrogar"/>
                 </button>
                 <button type="button" class="btn-primary buttonModal hide" id="btnSaveCad" onclick="prorrogarFecha()" data-dismiss="modal" aria-label="Close">
                    <liferay-ui:message key="btn.prorrogar"/>
                 </button>

                <button type="button" class="buttonModal closeMyModal " id="btnCancel"  data-dismiss="modal" aria-label="Close">
                   <liferay-ui:message key="categoria.view.dialog.cancelar"/>
                </button>
            </div>
        </div>
</div>
<div class="divModal" id="modalPortlet">
</div>




<portlet:resourceURL id="getAllComunicadosByCompany" var="getAllComunicadosByCompanyURL"/>
<portlet:resourceURL id="customGestor" var="customGestorURL"/>
<portlet:resourceURL id="customCategorizacion" var="customCategorizacionURL"/>
<portlet:resourceURL id="prorrogarComunicado" var="prorrogarComunicadoURL"/>
<liferay-portlet:renderURL varImpl="editURL">
    <portlet:param name="comunicadoEditId" value="ID_COMUNICADO" />
    <portlet:param name="mvcPath" value="/comunicado.jsp" />
</liferay-portlet:renderURL>
<style>
.dataTables_filter{
    /*display: none;*/
}
</style>

<script>
	var tabla = null;
	var urlAjaxBase = "<%=getAllComunicadosByCompanyURL.toString()%>" ;
    var urlAjaxCustomCategory ="<%=customGestorURL.toString()%>" ;
    var editURL = "<%=editURL%>";
    var jsonData = null;
    var pendiente ="<liferay-ui:message key="estado.datatable.filtro.activa.pendiente"/>"
    var array=[]
    array.push(pendiente);
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.admision"/>');
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.investigacion"/>');
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.finalizacion"/>');
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.finalizada"/>');
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.caducada"/>');
    var titleEditar='<liferay-ui:message key="title.comunicado.editar"/>'
    var titleProrrogar='<liferay-ui:message key="title.comunicado.prorrogar"/>'
    var titleAsignar='<liferay-ui:message key="title.comunicado.asignar"/>'
    var titleCategorizar='<liferay-ui:message key="title.comunicado.categorizar"/>'
    var hoy = new Date();
    hoy.setHours(0, 0, 0);
    var manana = new Date();
    var n = 0;
    var innerTag = 0;
    var innerInput = 0;
    var elementoAnadido='<div id="filterTag_wrapper" aria-label="Filtro:"></div>'

    manana.setTime(hoy.getTime() + 24 * 60 * 60 * 1000);
    function removeAccents (str) {
          return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
    }


    function setTagLimiter( selinput ) {
        n = 0;
        innerTag = 0;
        innerInput = 0;
        selinput = selinput.parent();

        selinput.find('.select2-selection__rendered li.select2-selection__choice').each( function( i, li ){
            if( $(li).hasClass('hide') ){
                $(li).removeClass('hide');
            }
            innerTag += $(li).outerWidth();
            if( innerTag >= selinput.find('.select2-selection__rendered').width() ){
                n++;
                $(li).addClass('hide');
            }else{
                n = 0;
            }
            if( selinput.find('.select2-selection__choice.numb').length ){
                selinput.find('.select2-selection__choice.numb').remove();
            }
            if( n != 0 ){

                if( $(li).parent().find('.select2-search.select2-search--inline').length ){
                    innerInput = selinput.find('.select2-selection__rendered').width() - ( selinput.find('.select2-search.select2-search--inline').outerWidth() + 20 );
                    $('<li class="select2-selection__choice numb">+ '+$(li).parent().find('.select2-selection__choice.hide').length+'</li>').insertBefore( $(li).parent().find('.select2-search.select2-search--inline') );
                }else{
                    innerInput = selinput.find('.select2-selection__rendered').width() - 20;
                    $(li).parent().append('<li class="select2-selection__choice numb">+'+$(li).parent().find('.select2-selection__choice.hide').length+'</li>');
                }

                innerTag = 0;
                selinput.find('.select2-selection__rendered li.select2-selection__choice:not(.hide, .numb)').each( function( i, li ){
                    innerTag += $(li).outerWidth();
                });
                if( innerTag + selinput.find('.select2-selection__choice.numb').outerWidth() >= innerInput ){
                    if( selinput.find('.select2-selection__choice.hide').first().prev().length && !selinput.find('.select2-selection__choice.hide:nth-child(2)').length ){
                        selinput.find('.select2-selection__choice.hide').first().prev().addClass('hide');
                    }
                    $(li).parent().find('.select2-selection__choice.numb').html('+' + $(li).parent().find('.select2-selection__choice.hide').length);
                }
            }
        });
    }

	$(document).ready( function () {

	    //extender la session
        /*setInterval(function(){
        console.log("Refresh session ...")
            Liferay.Session.extend();
        }, 60*1000);*/

	    $('.closeMyModal').click(function(){
            $('#modalContainer').removeClass("alert-modal warning")
        });
        $('#<portlet:namespace/>categoriaSelect').on("change",function(){
            if($('#<portlet:namespace/>categoriaSelect').val()==""){
                $('#invalidCategoria').removeClass('hide')
            }else{
                $('#invalidCategoria').addClass('hide');
                if($('#<portlet:namespace/>categoriaSelect option:selected').text()=="OTRA"){
                    $('#descCat').removeClass('hide');
                }else{
                    $('#descCat').addClass('hide');
                }
            }
        });
        let selectInput = $('#cmbEstado, #cmbCategoria, #<portlet:namespace/>gestorSelect');

        $('#cmbEstado').select2({
            multiple : true,
            language: "es",
            placeholder: '<liferay-ui:message key="accion.datatable.filtro.activa.noIndicado"/>',
            closeOnSelect: false
        });
        $('option:selected', '#cmbEstado').remove();


        $('#<portlet:namespace/>gestorSelect').select2({
            multiple : true,
            language: "es",
            placeholder: '<liferay-ui:message key="select.gestor.noIndicada"/>',
            closeOnSelect: false
        });

        $('#cmbCategoria').select2({
            multiple : true,
            language: "es",
            placeholder: '<liferay-ui:message key="accion.datatable.filtro.activa.noIndicado"/>',
            closeOnSelect: false
        });
        $('option:selected', '#cmbCategoria').remove();

        selectInput.on("select2:open", function (e) {
            console.log( "open", $('.select2-container--open').length );
            let isMultiple = $(e.currentTarget).attr('multiple');
            if( typeof isMultiple !== 'undefined' && isMultiple !== false ){
                $('.select2-container--open').addClass('isMultiple');
            }
        });
        selectInput.on('change', function (e) {
            e.preventDefault();
            let isMultiple = $(e.currentTarget).attr('multiple');
            if( typeof isMultiple !== 'undefined' && isMultiple !== false ){
                setTagLimiter( $( e.target ) );
            }
        });

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

		tabla = $('#table').DataTable( {
			dom: 'Bfrtip',
			ajax: {
                "url": urlAjaxBase,
                "dataSrc": function ( json ) {

                    jsonData = json.data;
                    console.log("json")
                    console.log(json)
                    if(json.status == "fail"){
                        $("#capaErrores").removeClass("d-none");
                        $("#spnError").html(json.msg);
                    }else if(json == ""){
                        alert("Su sesión a caducado. Introduzca las credenciales de nuevo. ")
                        window.location = "/c/portal/logout"
                    }

                    return json.data;
                }
            },
            "initComplete": function(settings, json) {
               $(".dataTables_filter > label").addClass("hide");
               $(".dataTables_filter  label").find("input").addClass("mt-4 ml-0 mr-5").appendTo("#spnInputSearch");
               $("#table_wrapper").addClass("filterFullWidth");
               $("#capaFilters").appendTo("#table_filter");
               $("#table").before(elementoAnadido);
                if($('th').last().width()<100){
                   $('th').last().width(100)
               }
               //loadAllPoptip()
            },
            "rowCallback": function( row, data ) {
                if(row.querySelector('.poptip') !== null ){
                   row.querySelectorAll('.poptip').forEach((elem, i) => {
                       loadPoptip( elem );
                   });
                }
                if(row.querySelector('.thisTooltip') !== null ){
                   row.querySelectorAll('.thisTooltip').forEach((elem, i) => {
                       setPoptip( elem );
                   });
                }
            },
             "columns": [
                {"data" : "codigo"},
                {"data" : "fechaAlta"},
                {"data" : "status"},
                {"data" : "categoria"},
                {"data" : "entidad"},
                {"data" : "gestor"},
                {"data" : "fechaBaja"},
                {"data" : "fechaCad"},
                {"data" : "idComun"}//editar
            ],
            "columnDefs": [
                 {
                    "targets"  : [0],
                    "orderable": true,
                    "render": function (data, type, row) {
                        return '<span class="hide">'+removeAccents(data)+'</span><span class="poptip" title="'+data+'">'+data+'</span>'
                    }
                 },
                 {
                     "targets"  : [1],
                     "orderable": true,
                     "render": function (data, type, row) {
                         dateDatatableAlta = new Date(parseInt(data));
                         return "<span class='hide'>"+dateDatatableAlta.getTime()+"/</span><span>" + dateDatatableAlta.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>"
                     }
                 },
                 {
                    "targets"  : [2],
                    "orderable": true,
                    "render": function (data, type, row) {
                        return '<span class="hide">'+data+'</span> <span class="poptip" title="'+array[row.status]+'">'+array[row.status]+'</span>'
                    }
                 },
                 {
                    "targets"  : [3],
                    "orderable": true,
                    "render": function (data, type, row) {
                        let arr = data.split("-");
                        let html =  "";
                        if(arr.length > 1 && arr[0] == "6"){//es id OTRA
                            html = '<span class="hide" id="categCom' + row.idComun + '">'+ arr[0]+'</span> ';
                            html += '<span class="poptip" title="' + arr[1] + ' ('+ row.descripcionCat + ')">' + arr[1] + ' ('+ row.descripcionCat + ')</span>';
                        }else if(arr.length > 1){
                            html = '<span class="hide" id="categCom' + row.idComun + '">'+ arr[0]+'</span> ';
                            if(row.descripcionCat==''){
                                html += '<span class="poptip" title="' + arr[1] +'">' + arr[1] + '</span>';
                            }else{
                                html += '<span class="poptip" title="' + arr[1] + ' ('+ row.descripcionCat + ')">' + arr[1] + ' ('+ row.descripcionCat + ')</span>';
                            }
                        }else{
                            html = '<span class="hide" id="categCom' + row.idComun + '">' +'</span> ';
                        }
                        return html;

                    }
                 },
                 {
                     "targets"  : [4],
                     "orderable": true,
                     "render": function (data, type, row) {
                         return '<span class="hide">'+removeAccents(data)+'</span> <span class="poptip" title="'+data+'">'+data+'</span>'
                     }
                 },
                 {
                      "targets"  : [5],
                      "orderable": true,
                      "render": function (data, type, row) {
                          var dataGuion=data.indexOf("-")
                          var splitUsuarios=data.substr(dataGuion+1,data.length-1).split(",")
                          var cadenaUsuarios=''
                          var titleUser=""
                          for(var x=0;x<splitUsuarios.length;x++){
                            titleUser+='<p>'+splitUsuarios[x]+'</p>'
                            cadenaUsuarios+=splitUsuarios[x]+' '
                          }
                          return '<span class="hide" id="gestorCom'+row.idComun+'">'+data.substr(0,dataGuion)+'</span> <span class="usersData poptip" title="'+titleUser+'">'+cadenaUsuarios+'</span>'
                      }
                 },
                 {
                    "targets"  : [6],
                    "orderable": true,
                    "render": function (data, type, row) {
                        if(data!=""){
                            dateDatatableAlta = new Date(parseInt(data));
                            return "<span class='hide'>"+dateDatatableAlta.getTime()+"/</span><span>" + dateDatatableAlta.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>"
                        }
                        return "";
                    }
                 },
                 {
                     "targets"  : [7],
                     "orderable": true,
                     "render": function (data, type, row) {
                         if(data!=""){
                             dateDatatableAlta = new Date(parseInt(data));
                             return "<span class='hide'>"+dateDatatableAlta.getTime()+"/</span><span>" + dateDatatableAlta.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>"
                         }
                         return "";
                     }
                 },
                 {
                    "targets"  : [8],
                    "orderable": false,
                    "render": function (data, type, row) {
                        if(row.idComun != 0){
                            var cadena='<div class="acciones-wrapper">'
                            cadena+='<a class="ico-acciones-tabla editar thisTooltip" title="'+titleEditar+'" href="#ENLACE#"></a> '.replace("#ENLACE#", editURL.replace("ID_COMUNICADO", data))
                            if(<%=hasRole%>){
                                if(row.status==4 || row.status==5){
                                    cadena=cadena+'<button type="button" class="ico-acciones-tabla categorizarComunicado" disabled></button>'+
                                    '<button class="ico-acciones-tabla asignarComunicado" disabled></button>';
                                }else{
                                    cadena=cadena+'<button type="button" class="ico-acciones-tabla categorizarComunicado thisTooltip" title="'+titleCategorizar+'" onclick="asignarCategoria(#ID#)"></button>'.replace("#ID#",data)+
                                    '<button class="ico-acciones-tabla asignarComunicado thisTooltip" title="'+titleAsignar+'" onclick="asignarGestor(#ID#)"></button>'.replace("#ID#",data);
                                }
                            }else{
                                if(row.status==4 || row.status==5){
                                    cadena=cadena+'<button type="button" class="ico-acciones-tabla categorizarComunicado" disabled></button>';
                                }else{
                                    cadena=cadena+'<button type="button" class="ico-acciones-tabla categorizarComunicado thisTooltip" title="'+titleCategorizar+'" onclick="asignarCategoria(#ID#)"></button>'.replace("#ID#",data);
                                }

                            }
                            if(row.prorrogado==false && row.status<4){

                                cadena+='<button class="ico-acciones-tabla prorrogarComunicado thisTooltip" title="'+titleProrrogar+'" onclick="prorrogarCom(#ID#)"></button>'.replace("#ID#",data)


                            }else{

                                cadena+='<button class="ico-acciones-tabla prorrogarComunicado" disabled></button>'

                            }
                            cadena+='</div>'
                            return cadena;
                        }
                        return "";
                    }
            }],
            "order":[
                [ 1, 'desc' ]
            ],
			//responsive: true,
			pageLength: 20,
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

	});
    var dateFile = null;
    var condiciones=0;
    var numeroCondiciones=6;
    var contenidoInput=""
    var sizeEstado=1;
    var sizeCategoria=1;
    $.fn.dataTable.ext.search.push(
            function( settings, data, dataIndex ) {
                condiciones=0;
                if($("#spnInputSearch input").val()=="" || $("#spnInputSearch input").val()==undefined){
                    condiciones++
                }else{

                    contenidoInput=removeAccents($("#spnInputSearch input").val().toUpperCase());
                    if(removeAccents(data[0].toUpperCase()).indexOf(contenidoInput)>=0 || removeAccents(data[4].toUpperCase()).indexOf(contenidoInput)>=0 ){
                        condiciones++
                    }else{
                        condiciones--;
                    }
                }
                sizeEstado=$("#cmbEstado").val().length
                //Si el size de estados es 1 no es multiple
                if(sizeEstado==0){
                    condiciones++;
                }
                else if(sizeEstado==1){
                    if($("#cmbEstado").val() == "-1") {condiciones++;}
                    if($("#cmbEstado").val() == "0"){
                    console.log("valor: "+data[1])
                    console.log("status: "+status)
                        if(data[2].charAt(0) == '0'){
                            condiciones++;
                        }else{
                            condiciones--;
                        }
                    }
                    if($("#cmbEstado").val() == "1"){
                        console.log("valor: "+data[1])
                        console.log("status: "+status)
                        if(data[2].charAt(0) == '1'){
                            condiciones++
                        }else{
                            condiciones--;
                        }
                    }
                    if($("#cmbEstado").val() == "2"){
                        console.log("valor: "+data[1])
                        console.log("status: "+status)
                        if(data[2].charAt(0) == '2'){
                            condiciones++;
                        }else{
                            condiciones--;
                        }
                    }
                    if($("#cmbEstado").val() == "3"){
                        if(data[2].charAt(0) == '3'){
                            condiciones++;
                        }else{
                            condiciones--;
                        }
                    }
                    if($("#cmbEstado").val() == "4"){
                        if(data[2].charAt(0) == '4'){
                            condiciones++;
                        }else{
                            condiciones--;
                        }
                    }
                    if($("#cmbEstado").val() == "5"){
                        if(data[2].charAt(0) == '5'){
                            condiciones++;
                        }else{
                           condiciones--;
                        }
                    }
                }else{
                //Si el size de estados es distinto a 1 es multiple
                    for(var i=0;i<sizeEstado;i++){
                        if($("#cmbEstado").val()[i] == "-1") {condiciones++;break;}
                        if($("#cmbEstado").val()[i] == "0"){
                            if(data[2].charAt(0) == '0'){
                                condiciones++;
                                break;
                            }
                        }
                        if($("#cmbEstado").val()[i] == "1"){

                            if(data[2].charAt(0) == '1'){
                                condiciones++
                                break;
                            }
                        }
                        if($("#cmbEstado").val()[i] == "2"){

                            if(data[2].charAt(0) == '2'){
                                condiciones++;
                                break;
                            }
                        }
                        if($("#cmbEstado").val()[i] == "3"){
                            if(data[2].charAt(0) == '3'){
                                condiciones++;
                                break;
                            }
                        }
                        if($("#cmbEstado").val()[i] == "4"){
                            if(data[2].charAt(0) == '4'){
                                condiciones++;
                                break;
                            }
                        }
                        if($("#cmbEstado").val()[i] == "5"){
                            if(data[2].charAt(0) == '5'){
                                condiciones++;
                                break;
                            }
                        }
                    }

                }
                sizeCategoria=$("#cmbCategoria").val().length;
                if(sizeCategoria==0){
                    condiciones++;
                }else if(sizeCategoria==1){
                    if($("#cmbCategoria").val() == "0") {condiciones++;}
                    else if($("#cmbCategoria").val()==data[3].split(" ")[0]){
                        condiciones++
                    }
                }else{
                    for(var i=0;i<sizeCategoria;i++){
                        if($("#cmbCategoria").val()[i] == "0") {condiciones++;break;}
                        else if($("#cmbCategoria").val()[i]==data[3].split(" ")[0]){
                            condiciones++;
                            break;
                        }
                    }
                }
                if($("#cmbGestor").val()==null){condiciones++}
                else if(data[5].split(" ")[0].includes($("#cmbGestor").val())){
                    condiciones++
                }

                if($("#<portlet:namespace/>startDate").val() == "" && $("#<portlet:namespace/>endDate").val() == "") {condiciones=condiciones+2;}
                else{
                    var dateInicio=$("#<portlet:namespace/>startDate").val();
                    var dateFin=$("#<portlet:namespace/>endDate").val();
                    if(dateInicio != ""){
                        var dateStart=data[1].split("/");
                        dateStart=dateStart[3]+"-"+dateStart[2]+"-"+dateStart[1]
                        dateFile = new Date(dateStart);
                        dateFile.setHours(0, 0, 0);
                        dateInicio=dateInicio.split("/");
                        dateInicio=dateInicio[2]+"-"+dateInicio[1]+"-"+dateInicio[0]
                        var dateInicioFile=new Date(dateInicio);
                        dateInicioFile.setHours(0, 0, 0);
                        if(dateFile.getTime() >= dateInicioFile.getTime()){
                           condiciones++;
                        }
                    }else{
                        condiciones++
                    }
                    if(dateFin != ""){
                        var dateEnd=data[1].split("/");
                        dateEnd=dateEnd[3]+"-"+dateEnd[2]+"-"+dateEnd[1]
                        dateFile = new Date(dateEnd);
                        dateFile.setHours(0, 0, 0);
                        dateFin=dateFin.split("/");
                        dateFin=dateFin[2]+"-"+dateFin[1]+"-"+dateFin[0]
                        var dateFinFile=new Date(dateFin);
                        dateFinFile.setHours(0, 0, 0);
                        if(dateFile.getTime() <= dateFinFile.getTime()){
                          condiciones++;
                        }
                    }else{
                        condiciones++
                    }
                }

                if(condiciones == numeroCondiciones) {
                    return true;
                }else{
                    return false;
               }
        }
    );
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
    $(document).on('keyup', '#<portlet:namespace/>startDate', function(e) {
      if($("#<portlet:namespace/>startDate").val() == ""){
        tabla.draw() ;
      }
    });
    $(document).on('keyup', '#<portlet:namespace/>endDate', function(e) {
      if($("#<portlet:namespace/>endDate").val() == ""){
        tabla.draw() ;
      }
    });

    var isEdit = false;
    var idComunicado = 0;
    var idCategoria = "";
    var idGestor="";
 function prorrogarCom(_id){
     idComunicado=_id;
     $("#addEntidad").addClass("hide");
     $("#editEntidad").addClass("hide");
     $("#containerInputs").addClass("hide");
     $("#prorrogaAceptada").addClass("hide");
     $("#btnSaveCad").addClass("hide");

     $("#containerCaducidad").removeClass("hide");
     $("#btnContinueCad").removeClass("hide");
     $("#prorrogaPrevia").removeClass("hide");
     $("#addCaducidad").removeClass("hide");
     $('#modalContainer').addClass("alert-modal warning")
     mostrarModal();
 }
function mostrarModal(){
   $('#modalPortlet').css('display','block');
   $('#modalContainer').css('display','block');
}

function asignarCategoria(_id){
     idComunicado=_id;
     $('#invalidCategoria').addClass('hide');
     idCategoria=$("#categCom"+_id+"").html();
     $('#<portlet:namespace/>categoriaSelect option[value="'+idCategoria+'"]').attr('selected',true);
     var datos=getDataById(_id);
     $('#<portlet:namespace/>descripcionCat').val(datos.descripcionCat);
     if($('#<portlet:namespace/>categoriaSelect option:selected').text()=="OTRA"){
         $('#descCat').removeClass('hide');
     }else{
         $('#descCat').addClass('hide');
     }
     $("#asuntoCom").val(datos.asunto);
     $("#descripcionCom").html(datos.descripcion.replaceAll('\n','</br>'));



     $("#addEntidad").removeClass("hide");

     $("#containerInputs").removeClass("hide");
     $("#containerCaducidad").addClass("hide");

     $("#inputSelectCategoria").removeClass("hide");
     $("#inputSelectGestor").addClass("hide");

     $("#footerCategoria").removeClass("hide");
     $("#footerGestor").addClass("hide");

     $("#editEntidad").addClass("hide");
     $("#addCaducidad").addClass("hide");
     mostrarModal();
}

function asignarGestor(_id){
     idComunicado=_id;
     $('#invalidGestor').addClass('hide');
      idGestor=$("#gestorCom"+_id+"").html();
      var allGestores=[]
      if(idGestor.includes(",")){
         allGestores=idGestor.split(",")
      }else{
         allGestores.push(idGestor);
      }

     var datos=getDataById(_id);
     $("#asuntoCom").val(datos.asunto);
     $("#descripcionCom").html(datos.descripcion.replaceAll('\n','</br>'));

     $("#editEntidad").removeClass("hide");


     $("#containerInputs").removeClass("hide");
     $("#containerCaducidad").addClass("hide");

     $("#inputSelectCategoria").addClass("hide");
     $("#inputSelectGestor").removeClass("hide");

     $("#footerCategoria").addClass("hide");
     $("#footerGestor").removeClass("hide");

     $("#addEntidad").addClass("hide");
     $("#addCaducidad").addClass("hide");
     mostrarModal();
     $('#<portlet:namespace/>gestorSelect').val(allGestores).trigger("change");
}



$(document).on('click', '#btnCancelCateg', function(e) {
    $('#<portlet:namespace/>categoriaSelect option[value="'+idCategoria+'"]').attr('selected',false);
});
function getDataById(_id){
    var res = null;
    for(var x in jsonData){
        if(jsonData[x].idComun == _id){
            res = jsonData[x];
            break;
        }
    }
    return res;
}

function prorrogarFecha(){
    $.ajax({
        type: "POST",
        url: '${prorrogarComunicadoURL}',
        data: {
            '<portlet:namespace/><%=AdminDenunciaV2PortletKeys.COMUNICADO_ID%>':idComunicado,
        },
        success: function (result) {
          console.log("Okay: "+result)
          tabla.ajax.reload()
          $('.closeMyModal').click()
          for(var x=0; x<$('.poptip').length;x++){loadPoptip( $('.poptip')[x])}
      }
    });
}

function continueModal(){
    $("#prorrogaAceptada").removeClass("hide");
    $("#btnSaveCad").removeClass("hide");
    $("#prorrogaPrevia").addClass("hide");
    $("#btnContinueCad").addClass("hide");

}
function asignarCat(){
    if($('#<portlet:namespace/>categoriaSelect').val()==""){
        $('#invalidCategoria').removeClass('hide')
    }else{
        $('#invalidCategoria').addClass('hide');

        $.ajax({
            type: "POST",
            url: '${customCategorizacionURL}',
            data: {
                '<portlet:namespace/><%=AdminDenunciaV2PortletKeys.COMUNICADO_ID%>':idComunicado,
                '<portlet:namespace/><%=AdminDenunciaV2PortletKeys.CATEGORIA_ID%>':$('#<portlet:namespace/>categoriaSelect').val(),
                '<portlet:namespace/>descripcionCat': $('#<portlet:namespace/>descripcionCat').val()
            },
            success: function (result) {
              console.log("Okay: "+result)
              tabla.ajax.reload()

              $('.closeMyModal').click()
              $('#invalidCategoria').addClass('hide')
              for(var x=0; x<$('.poptip').length;x++){loadPoptip( $('.poptip')[x])}
            },
            error: function (jqXhr, textStatus, errorMessage) {
               console.log("ERROR")
               $('#invalidCategoria').removeClass('hide')
            }
        });
    }
}
function asignarGstr(){
    var allIdGestorSelect=$('#<portlet:namespace/>gestorSelect').val();
    if($('#<portlet:namespace/>gestorSelect').val()==""){
        $('#invalidGestor').removeClass('hide')
    }else{
        $('#invalidGestor').addClass('hide');
        var allIdGestorSend="";
        for(i=0;i<allIdGestorSelect.length;i++){
           if(i==allIdGestorSelect.length-1){
               allIdGestorSend=allIdGestorSend+allIdGestorSelect[i];
           }else{
               allIdGestorSend=+allIdGestorSelect[i]+","+allIdGestorSend;
           }
        }

        $.ajax({
        type: "POST",
        url: '${customGestorURL}',
        data: {
            '<portlet:namespace/><%=AdminDenunciaV2PortletKeys.COMUNICADO_ID%>':idComunicado,
            '<portlet:namespace/><%=AdminDenunciaV2PortletKeys.GESTOR_ID%>':allIdGestorSend

        },
        success: function (result) {
          console.log("Okay: "+result)
          tabla.ajax.reload()
          $('.closeMyModal').click()
          $('#invalidGestor').addClass('hide')
          for(var x=0; x<$('.poptip').length;x++){loadPoptip( $('.poptip')[x])}

        },
        error: function (jqXhr, textStatus, errorMessage) {
           console.log("ERROR")
           $('#invalidGestor').removeClass('hide')
        }
        });
    }
}



</script>
