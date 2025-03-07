<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Categoria" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.categoria.web.constants.CategoriasPortletKeys" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.role.CanalEticoRoleUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ResourceBundle" %>
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

	/*long compId = (long)request.getAttribute(CategoriasPortletKeys.COMPANY_ID);

	boolean isCompanyAdmin = CanalEticoRoleUtil.isCompanyAdminRole(themeDisplay.getCompanyId(), themeDisplay.getUser());

	boolean isAdmin = permissionChecker.isOmniadmin();

	boolean hasPermission = CanalEticoUserUtil.isUserInDefaultCompPermission(themeDisplay.getUserId());
	boolean omniAdminRole = CanalEticoRoleUtil.isOmniAdminRole(themeDisplay.getCompanyId(), themeDisplay.getUser());*/

	ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

    System.out.println(themeDisplay.getUser().getLocale());

	/*String yes = LanguageUtil.get(bundle, "accion.view.yes");
	String no  = LanguageUtil.get(bundle, "accion.view.no");
	String all = LanguageUtil.get(bundle, "accion.view.all");*/
%>

<div class="content">
    <div class="title-group">
        <h2><liferay-ui:message key="accion.view.title"/></h2>
        <p><liferay-ui:message key="accion.datatable.descripcion"/></p>
    </div>



    <div  id="capaFilters" class="row">
        <div class="col-4">
            <span id="spnInputSearch"> </span>

        </div>

        <div class="col-4">
            <label class="float-left" for="cmbEstado">
                <liferay-ui:message key="accion.datatable.filtro.estado.title" />
            </label>
            <select class="ml-0 form-control" id="cmbEstado" name="cmbEstado" label='<liferay-ui:message key="categoria.datatable.filtro.activa.title"/>' onchange="tabla.draw()">
                <option value="-1"><liferay-ui:message key="accion.datatable.filtro.activa.noIndicado"/></option>
                <option value="0"><liferay-ui:message key="estado.datatable.filtro.activa.pendiente"/></option>
                <option value="1"><liferay-ui:message key="estado.datatable.filtro.activa.admision"/></option>
                <option value="2"><liferay-ui:message key="estado.datatable.filtro.activa.investigacion"/></option>
                <option value="3"><liferay-ui:message key="estado.datatable.filtro.activa.finalizacion"/></option>

            </select>
        </div>
        <div class="col-4">
            <label class="float-left" for="cmbActiva">
                <liferay-ui:message key="accion.datatable.filtro.activa.title" />
            </label>
            <select class="ml-0 form-control" id="cmbActiva" name="cmbActiva" label='<liferay-ui:message key="categoria.datatable.filtro.activa.title"/>' onchange="tabla.draw()">
                <option value="0"><liferay-ui:message key="accion.datatable.filtro.activa.noIndicado"/></option>
                <option value="1" selected><liferay-ui:message key="accion.datatable.filtro.activa.activa"/></option>
                <option value="2"><liferay-ui:message key="accion.datatable.filtro.activa.noActiva"/></option>
            </select>
        </div>
    </div>


    <div class="row">
        <div class="col-8">&nbsp;</div>
        <div class="col-4">
            <button class="float-right btn btn-primary" type="button" onclick="newCat()">
                <liferay-ui:message key="accion.datatable.btn.nuevo"/>
            </button>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <table id="table" class="display" style="width:100%">
                <thead>
                <tr>
                    <th><liferay-ui:message key="accion.datatable.col.categoria"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.estado"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.creada"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.fechaAlta"/></th>
                    <th><liferay-ui:message key="accion.datatable.col.fechaBaja"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="contentModal" id="modalContainer" style="border-radius: 15px; width: 650px;">
        <div class="headerModal">
            <p id="addEntidad"><span><liferay-ui:message key="accion.datatable.btn.nuevo"/></span></p>
            <p class="hide" id="editEntidad"><span><liferay-ui:message key="accion.datatable.editar.title"/></span></p>
        </div>
        <div class="container">
            <div class="row myrow">
                <div id="brand-logo-modal" class="col-3">
                    <label for="<portlet:namespace/>accion" class="labelInput"><liferay-ui:message key="accion.datatable.col.categoria"/>*</label>
                </div>
                <div class="col-9">
                <aui:input name="accion" cssClass="inputModal" type="text" label="">
                    <aui:validator name="required" errorMessage="company.error.required" />
                </aui:input>
                <p class="text-danger hide" id="requiredEntidad"><liferay-ui:message key="input.required"/></p>
                </div>
            </div>
            <div class="row myrow">
                <div class="col-3">
                    <label for="<portlet:namespace/>estado" class="labelInput"><liferay-ui:message key="accion.datatable.col.estado"/>*</label>
                </div>
                <div class="col-9">
                    <aui:select name="estado" label="">
                        <option value="0"><liferay-ui:message key="estado.datatable.filtro.activa.pendiente"/></option>
                        <option value="1"><liferay-ui:message key="estado.datatable.filtro.activa.admision"/></option>
                        <option value="2"><liferay-ui:message key="estado.datatable.filtro.activa.investigacion"/></option>
                        <option value="3"><liferay-ui:message key="estado.datatable.filtro.activa.finalizacion"/></option>
                    </aui:select>
                </div>
            </div>
            <div class="row myrow">
                <div class="col-3">
                   <label for="<portlet:namespace/>endDate" class="labelInput"><liferay-ui:message key="accion.data.fecha"/></label>
                </div>
                <div class="col-6">
                   <liferay-ui:input-date name="endDate" cssClass="prv-input-date" nullable="true" showDisableCheckbox="false" firstEnabledDate="<%=new Date()%>"  lastEnabledDate="<%=new Date()%>"/>
                   <p class="text-danger hide" id="invalidDate"><liferay-ui:message key="invalid.date"/></p>
                </div>
            </div>
        </div>
         <div class="footerModal">
             <button type="button" class="btn-primary buttonModal " id="btnSaveCat" onclick="saveCat()" data-dismiss="modal" aria-label="Close">
                <liferay-ui:message key="categoria.view.dialog.guardar"/>
             </button>

            <button type="button" class="buttonModal closeMyModal " id="btnCancel"  data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="categoria.view.dialog.cancelar"/>
            </button>
         </div>
</div>
<div class="divModal" id="modalPortlet">
</div>




<portlet:resourceURL id="getAllActions" var="getAllActionsURL"/>
<portlet:resourceURL id="customAccion" var="customAccionURL"/>
<style>
.dataTables_filter{
    /*display: none;*/
}
</style>
<script>
	var tabla = null;
	var urlAjaxBase = "<%=getAllActionsURL.toString()%>" ;
    var urlAjaxCustomCategory ="<%=customAccionURL.toString()%>" ;
    var jsonData = null;
    var array=[]
    array.push('<liferay-ui:message key="estado.datatable.filtro.activa.pendiente"/>');
    array.push('<%=LanguageUtil.get(bundle,"estado.datatable.filtro.activa.admision")%>');
    array.push('<%=LanguageUtil.get(bundle,"estado.datatable.filtro.activa.investigacion")%>');
    array.push('<%=LanguageUtil.get(bundle,"estado.datatable.filtro.activa.finalizacion")%>');
    var hoy = new Date();
    hoy.setHours(0, 0, 0);
    var manana = new Date();
    manana.setTime(hoy.getTime() + 24 * 60 * 60 * 1000);
    function removeAccents (str) {
          return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
    }


	$(document).ready( function () {
        //extender la session
            /*setInterval(function(){
                console.log("Refresh session ...")
                Liferay.Session.extend();
            }, 60*1000);*/

        var buttonCommon = {
            exportOptions: {
                format: {
                    body: function ( data, row, column, node ) {
                        // Strip $ from salary column to make it numeric
                        if(column>=5){
                            return ;
                        }else{
                            if(node.childNodes[2]!==undefined){
                                return node.childNodes[2].textContent;
                            }else if(node.childNodes[1]!==undefined && column==3){
                                return node.childNodes[1].textContent;;
                            }else if(node.childNodes[1]==undefined && column==4){
                                return;
                            }else{
                                return  node.childNodes[0].textContent;
                            }
                        }
                    }
                }
            }
        };

        $("#<portlet:namespace/>accion").on( "blur", function() {
            if($( "#<portlet:namespace/>accion").val()==""){
                $('#requiredEntidad').removeClass('hide');
            }else{
                $('#requiredEntidad').addClass('hide');
            }
        });
        $("#btnCancel").on("click",function(){
            $('#requiredEntidad').addClass('hide');
            isEdit=false;
        })
		tabla = $('#table').DataTable( {
			dom: 'Bfrtip',
			ajax: {
                "url": urlAjaxBase,
                "dataSrc": function ( json ) {  jsonData = json.data; return json.data; }
            },
            "initComplete": function(settings, json) {
               $(".dataTables_filter > label").addClass("hide");
               $(".dataTables_filter  label").find("input").addClass("mt-4 ml-0 mr-5").appendTo("#spnInputSearch");
               $(".dt-buttons").addClass("mt-4");
               $("#capaFilters").appendTo("#table_filter");
            },
            "rowCallback": function( row, data ) {
               if(row.querySelector('.poptip') !== null ){
                  row.querySelectorAll('.poptip').forEach((elem, i) => {
                      loadPoptip( elem );
                  });
               }
            },
             "columns": [
                {"data" : "name"},
                {"data" : "status"},
                {"data" : "createBy"},
                {"data" : "fechaAlta"},
                {"data" : "fechaBaja"},
                {"data" : "id"} //editar
            ],
            "columnDefs": [
                 {
                    "targets"  : [0],
                    "orderable": true,
                    "render": function (data, type, row) {
                        return '<span class="hide">'+removeAccents(data)+'</span> <span class="poptip" title="'+data+'">'+data+'</span>';
                    }
                 },
                 {
                    "targets"  : [1],
                    "orderable": true,
                    "render": function (data, type, row) {
                        return '<span class="hide">'+data+'</span> <span class="poptip" title="'+array[row.status]+'">'+array[row.status]+'</span>';
                    }
                 },
                 {
                    "targets"  : [2],
                    "orderable": true,
                    "render": function (data, type, row) {
                        return '<span class="hide">'+removeAccents(data)+'</span>  <span class="poptip" title="'+data+'">'+data+'</span>';
                    }
                 },
                 {
                    "targets"  : [3],
                    "orderable": true,
                    "render": function (data, type, row) {
                        dateDatatableAlta = new Date(parseInt(data));
                        return "<span class='hide'>"+dateDatatableAlta.getTime()+"/</span><span>" + dateDatatableAlta.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>"
                    }
                 },
                 {
                    "targets"  : [4],
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
                    "targets"  : [5],
                    "orderable": false,
                    "render": function (data, type, row) {
                        if(row.compId != 1){
                            return '<div class="acciones-wrapper"><button type="button" class="ico-acciones-tabla editar" onclick="editCategoria(#ID#)"></button></div>'.replace("#ID#",row.id);
                        }
                        return "";
                    }
            }],
            "order":[
                [ 1, 'asc' ]
            ],
			responsive: true,
			pageLength: 20,
			buttons: [
                $.extend( true, {}, buttonCommon,{
                    extend: 'csvHtml5',
                }),
                $.extend( true, {}, buttonCommon,{
                    extend: 'excelHtml5',
                }),
                $.extend( true, {}, buttonCommon,{
                    extend: 'print',
                }),
                $.extend( true, {}, buttonCommon,{
                    extend: 'pdfHtml5',
                })
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
    var dateFile = null;
    var condiciones=0;
    var numeroCondiciones=3;
    var contenidoInput=""

    $.fn.dataTable.ext.search.push(
            function( settings, data, dataIndex ) {
                condiciones=0;
                if($("#spnInputSearch input").val()=="" || $("#spnInputSearch input").val()==undefined){
                    condiciones++
                }else{

                    contenidoInput=removeAccents($("#spnInputSearch input").val().toUpperCase());
                    if(removeAccents(data[0].toUpperCase()).indexOf(contenidoInput)>=0 || removeAccents(data[2].toUpperCase()).indexOf(contenidoInput)>=0 ){
                        condiciones++
                    }else{
                        condiciones--;
                    }
                }
                if($("#cmbEstado").val() == "-1") {condiciones++;}
                if($("#cmbEstado").val() == "0"){
                console.log("valor: "+data[1])
                console.log("status: "+status)
                    if(data[1].charAt(0) == '0'){
                        condiciones++;
                    }else{
                        condiciones--;
                    }
                }
                if($("#cmbEstado").val() == "1"){
                    console.log("valor: "+data[1])
                    console.log("status: "+status)
                    if(data[1].charAt(0) == '1'){
                        condiciones++
                    }else{
                        condiciones--;
                    }
                }
                if($("#cmbEstado").val() == "2"){
                    console.log("valor: "+data[1])
                    console.log("status: "+status)
                    if(data[1].charAt(0) == '2'){
                        condiciones++;
                    }else{
                        condiciones--;
                    }
                }
                if($("#cmbEstado").val() == "3"){
                    if(data[1].charAt(0) == '3'){
                        condiciones++;
                    }else{
                        condiciones--;
                    }
                }
                if($("#cmbActiva").val() == "0") {condiciones++;}
                if($("#cmbActiva").val() == "1"){
                    if(data[4] != ""){
                        var dateBaja=data[4].split("/");
                        dateBaja=dateBaja[3]+"-"+dateBaja[2]+"-"+dateBaja[1]
                        dateFile = new Date(dateBaja);
                        dateFile.setHours(0, 0, 0);
                        if(dateFile.getTime() > hoy.getTime()){
                           condiciones++;
                        }else{
                            condiciones--;
                        }
                    }else{
                       condiciones++;
                    }
                }
                if($("#cmbActiva").val() == "2"){
                    if(data[4] != ""){
                        var dateBaja=data[4].split("/");
                        dateBaja=dateBaja[3]+"-"+dateBaja[2]+"-"+dateBaja[1]
                        dateFile = new Date(dateBaja);
                        dateFile.setHours(0, 0, 0);
                        if(dateFile.getTime() <= hoy.getTime()){
                           condiciones++;
                        }else{
                           condiciones--;;
                        }
                    }else{
                        condiciones--;
                    }

                }

                if(condiciones == numeroCondiciones) {
                    return true;
                }else{
                    return false;
               }
        }
    );



    var isEdit = false;
    var idEditCat = 0;


    function newCat(){
        $("#<portlet:namespace/>accion").val('');
        $("#<portlet:namespace/>endDate").val('');
        isEdit = false;
        $("#addEntidad").removeClass("hide");
        $("#editEntidad").addClass("hide");
        mostrarModal();
    }

    function getValidDate(_val){
        if(_val!=""){
            try{
                var arrDate = _val.split("/");
                if(arrDate.length != 3) return null;
                if(isNaN(arrDate[0]) || isNaN(arrDate[1]) || isNaN(arrDate[2])) return null;

                dateTemp = new Date(arrDate[2] + "-" + arrDate[1] + "-" + arrDate[0]);

                if(dateTemp.getTime() >= hoy.getTime() && dateTemp.getTime() <= manana.getTime()){
                    return dateTemp;
                }

            }catch(e){}
            return null;
        }
        return "";
    }

    function saveCat(){
        if($( "#<portlet:namespace/>accion").val()==""){
            $('#requiredEntidad').removeClass('hide');
        }else{
            $('#requiredEntidad').addClass('hide');
        }

        if($("#<portlet:namespace/>accion").val().trim() == "") return;
        var strValues = "";
        if(!isEdit){
            strValues = "&idCat=0&nameCat=" + $("#<portlet:namespace/>accion").val() + "&deleteDate=" + $("#<portlet:namespace/>endDate").val()+"&idEstado="+$("#<portlet:namespace/>estado").val();
        }else{
            strValues = "&idCat=" + idEditCat + "&nameCat=" + $("#<portlet:namespace/>accion").val() + "&deleteDate=" + $("#<portlet:namespace/>endDate").val()+"&idEstado="+$("#<portlet:namespace/>estado").val();
        }
        if(getValidDate($("#<portlet:namespace/>endDate").val())!=null){
            $('#invalidDate').addClass('hide');
            console.log(urlAjaxCustomCategory + strValues)
            $.ajax({
                type:"GET",
                url:urlAjaxCustomCategory + strValues,
                /*data:{},*/
                success:function(res){
                     console.log(res)
                     $(".closeMyModal").click();
                     tabla.ajax.reload();
                     isEdit = false;
                 }
            });
        }else{
             $('#invalidDate').removeClass('hide');
        }


    }
    function mostrarModal(){
       $('#modalPortlet').css('display','block');
       $('#modalContainer').css('display','block');
    }

    function getCatById(_id){
        var res = null;
        for(var x in jsonData){
            if(jsonData[x].id == _id){
                res = jsonData[x];
                break;
            }
        }
        return res;
    }

    function editCategoria(_id){
        isEdit = true;
        idEditCat = _id;
        var cat = getCatById(_id);
        dateDatatableAlta = new Date(parseInt(cat.fechaBaja));
        $("#<portlet:namespace/>accion").val(cat.name);
        if(cat.fechaBaja!=""){
            $("#<portlet:namespace/>endDate").val( dateDatatableAlta.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }));
        }else{
            $("#<portlet:namespace/>endDate").val(cat.fechaBaja)
        }
        $("#addEntidad").addClass("hide");
        $("#editEntidad").removeClass("hide");
        mostrarModal();
        console.log("editar: " + _id)
    }

</script>

