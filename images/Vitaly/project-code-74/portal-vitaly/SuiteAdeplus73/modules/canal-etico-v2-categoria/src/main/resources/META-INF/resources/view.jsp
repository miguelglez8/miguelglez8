<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Categoria" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.model.Comp" %>
<%@ page import="com.canal.etico.liferay.portlet.canal.manager.service.CompLocalServiceUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.categoria.web.constants.CategoriasPortletKeys" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.role.CanalEticoRoleUtil" %>
<%@ page import="com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil" %>

<%@ page import="com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil" %>
<%@ page import="com.adeplus.liferay.portlet.suite.manager.model.UserCompany" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
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
    UserCompany userCompany = CanalEticoUserUtil.getUserDefaultCompany(themeDisplay.getUserId());
    long companiaId = 0;
    if(Validator.isNotNull(userCompany)){
        companiaId = userCompany.getCompId();
    }

%>
<div class="content">
    <div class="title-group">
        <h2><liferay-ui:message key="categoria.view.title"/></h2>
        <p><liferay-ui:message key="categoria.datatable.descripcion"/></p>
    </div>



    <div  id="capaFilters" class="row">
        <div class="col-6">
            <span id="spnInputSearch"> </span>

        </div>

        <div class="col-6">
            <label class="float-left">
                <liferay-ui:message key="categoria.datatable.filtro.activa.title" />
            </label>
                <select class="ml-0 form-control" id="cmbActiva" name="cmbActiva" label='<liferay-ui:message key="categoria.datatable.filtro.activa.title"/>' onchange="tabla.draw()">
                    <option value="0"><liferay-ui:message key="categoria.datatable.filtro.activa.noIndicado"/></option>
                    <option value="1" selected><liferay-ui:message key="categoria.datatable.filtro.activa.activa"/></option>
                    <option value="2"><liferay-ui:message key="categoria.datatable.filtro.activa.noActiva"/></option>
                </select>
        </div>
    </div>
    <div class="row">
        <div class="col-8">&nbsp;</div>
        <div class="col-4">
            <button class="float-right btn btn-primary" type="button" onclick="newCat()">
                <liferay-ui:message key="categoria.datatable.btn.nuevo"/>
            </button>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <table id="table" class="display" style="width:100%">
                <thead>
                <tr>
                    <th><liferay-ui:message key="categoria.datatable.col.categoria"/></th>
                    <th><liferay-ui:message key="categoria.datatable.col.creada"/></th>
                    <th><liferay-ui:message key="categoria.datatable.col.fechaAlta"/></th>
                    <th><liferay-ui:message key="categoria.datatable.col.fechaBaja"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>

<span id="capaDialog">
    <div class="contentModal" id="modalContainer" style="border-radius: 10px; width: 650px;">
       <div class="headerModal">
           <p>
                <span id="spnTitleNueva" class="hide">
                    <liferay-ui:message key="categoria.datatable.btn.nuevo"/>
                </span>
                <span id="spnTitleEditar" class="hide">
                    <liferay-ui:message key="categoria.view.dialog.title.editar"/>
                </span>
           </p>
       </div>
       <div class="container">
           <div class="row myrow">
               <div class="col-3">
                   <label for="modalNameRequired" class="labelInput"><liferay-ui:message key="categoria.datatable.col.categoria"/>*</label>
               </div>
               <div class="col-9">
                    <input type="text"  style="width:100%;" id="inpNameCat" name="inpNameCat" maxlength="75" />
                    <p class="text-danger hide" id="modalNameRequired"><liferay-ui:message key="categoria.view.dialog.required.name"/></p>

               </div>
           </div>
           <div class="row myrow">
               <div class="col-3">
                   <label for="<portlet:namespace/>inpBajaCat" class="labelInput"><liferay-ui:message key="categoria.datatable.editar.fechaBaja.input"/></label>
               </div>
               <div class="col-6">
                     <liferay-ui:input-date name="inpBajaCat" cssClass="prv-input-date"
                        nullable="true" showDisableCheckbox="false"
                        firstEnabledDate="<%=new Date()%>"
                        lastEnabledDate="<%=new Date()%>"
                        />
                        <p class="text-danger hide" id="modalDateRequired"><liferay-ui:message key="categoria.view.dialog.required.date"/></p>

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
</span>

<portlet:resourceURL  id="getAllCategories" var="getAllCategories">
    <portlet:param name="companyId" value="<%=String.valueOf(companiaId)%>" />
</portlet:resourceURL>

<portlet:resourceURL id="customCategory" var="customCategory"/>

<style>
.dataTables_filter{
    /*display: none;*/
}
</style>
<script>
	var tabla = null;
	var urlAjaxBase = "<%=getAllCategories.toString()%>" ;
    var urlAjaxCustomCategory ="<%=customCategory.toString()%>" ;
    var jsonData = null;
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
                        if(column>=4){
                            return ;
                        }else{
                            if(node.childNodes[2]!==undefined){
                                return node.childNodes[2].textContent;
                            }else if(node.childNodes[1]!==undefined && column==2){
                                return node.childNodes[1].textContent;;
                            }else if(node.childNodes[2]==undefined && column==3){
                                return;
                            }else{
                                return  node.childNodes[0].textContent;
                            }
                        }
                    }
                }
            }
        };


		tabla = $('#table').DataTable( {
			dom: 'Bfrtip',
			order: [[0, 'asc']],
			ajax: {
                "url": urlAjaxBase,
                "dataSrc": function ( json ) {
                    jsonData = json.data; return json.data;
                    }
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
                        console.log(row)
                        /*
                        if(data == "ADEPLUS CONSULTORES"){
                            return "<span class='hide'>0" + row.createBy + "</span>" + data;
                        }
                        return "<span class='hide'>99" + row.createBy + "</span>" + data;
                        */
                        return '<span class="poptip" title="'+data+'">'+data+'</span>';
                    }
                 },
                 {
                    "targets"  : [1],
                    "orderable": true,
                    "render": function (data, type, row) {
                        if(data == "ADEPLUS CONSULTORES"){
                            return '<span class="hide">0</span> <span class="poptip" title="'+data+'">'+data+'</span>';
                        }
                        return '<span class="hide">99</span> <span class="poptip" title="'+data+'">'+data+'</span>';
                    }
                 },
                 {
                    "targets"  : [2],
                    "orderable": true,
                    "render": function (data, type, row) {
                        if(data!=""){
                            dateDatatableAlta = new Date(parseInt(data));
                            return "<span class='hide'>" + dateDatatableAlta.getTime() + "/</span>" + "<span>" + dateDatatableAlta.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>"
                        }
                        return "";
                    }
                 },
                 {
                    "targets"  : [3],
                    "orderable": true,
                    "render": function (data, type, row) {
                        if(data!=""){
                            dateDatatableAlta = new Date(parseInt(data));
                            return "<span class='hide'>" + dateDatatableAlta.getTime() + "/</span>" + "<span>" + dateDatatableAlta.toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) + "</span>"
                        }
                        return "";
                    }
                 },
                 {
                    "targets"  : [4],
                    "orderable": false,
                    "render": function (data, type, row) {
                        if(row.compId != 1){
                            return '<div class="acciones-wrapper"><button type="button" class="ico-acciones-tabla editar" onclick="editCategoria(#ID#)"></button></div>'.replace("#ID#",row.id);
                        }
                        return "";
                    }
            }],
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

    var hoy = new Date();
    hoy.setHours(0, 0, 0);
    var manana = new Date();
    manana.setTime(hoy.getTime() + 24 * 60 * 60 * 1000);
    var dateTemp = null;
    var dateFile = null;
    var dateBaja = null;

    function removeAccents (str) {
          return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
    }
    $(document).ready( function () {
    $.fn.dataTable.ext.search.push(
            function (settings, data, dataIndex, rowData, counter) {
            var cond = 0, cumpleCond = 0;
            var val =  removeAccents($('.dataTables_filter input').val().toLowerCase());
            buscar = removeAccents(data[0].toLowerCase()) + " " + removeAccents(data[1].toLowerCase());;
            if(val != "") cond++;
            if($("#cmbActiva").val() != "0"){
                cond++;
                if($("#cmbActiva").val() == "1"){
                                if(data[3] != ""){
                                    dateBaja = data[3].split("/");
                                    dateBaja = dateBaja[3] + "-" + dateBaja[2] + "-" + dateBaja[1];
                                    dateFile = new Date(dateBaja);
                                    dateFile.setHours(0, 0, 0);
                                    if(dateFile.getTime() > hoy.getTime()){
                                        cumpleCond++;
                                    }
                                }else{
                                    cumpleCond++;
                                }
                            }else{
                                if(data[3] != ""){
                                    dateBaja=data[3].split("/");
                                    dateBaja=dateBaja[3] + "-" + dateBaja[2] + "-" + dateBaja[1];
                                    dateFile = new Date(dateBaja);
                                    dateFile.setHours(0, 0, 0);
                                    if(dateFile.getTime() <= hoy.getTime()){
                                        cumpleCond++;
                                    }
                                }
                            }

            }



            if (val != "" && buscar.includes(val)) {
                cumpleCond++;
            }
            console.log("cond: " + cond + " / cumpleCond: " + cumpleCond);
            if(cond == cumpleCond) return true;
            return false;

        }
    );


  $('.dataTables_filter input')
    .off()
    .on('keyup', function() {
        tabla.draw();
     });

} );


     $("#<portlet:namespace/>inpBajaCat").on("focus", function(e){
         $("#modalDateRequired").addClass("hide");
     });
     $("#inpNameCat").on("focus", function(e){
              $("#modalNameRequired").addClass("hide");
        });

    var isEdit = false;
    var idEditCat = 0;


    function newCat(){
        $("#inpNameCat").val('');
        $("#<portlet:namespace/>inpBajaCat").val('');
        isEdit = false;
        $("#spnTitleNueva").removeClass("hide");
        $("#spnTitleEditar").addClass("hide");
        mostrarModal();
    }

    function getValidDate(_val){
        try{
            if(_val == "") return "";
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

    function saveCat(){
        if($("#inpNameCat").val().trim() == ""){
            $("#modalNameRequired").removeClass("hide");
            return;
        }
        console.log("FECHA: " + $("#<portlet:namespace/>inpBajaCat").val())
        var strValues = "";
        var dateField = null;
        if( (dateField = getValidDate($("#<portlet:namespace/>inpBajaCat").val())) == null  ){
            $("#modalDateRequired").removeClass("hide");
            return;
        }



        if(!isEdit){
            strValues = "&idCat=0&nameCat=" + $("#inpNameCat").val() + "&deleteDate=" + $("#<portlet:namespace/>inpBajaCat").val();
        }else{
            strValues = "&idCat=" + idEditCat + "&nameCat=" + $("#inpNameCat").val() + "&deleteDate=" + $("#<portlet:namespace/>inpBajaCat").val();
        }

        console.log(urlAjaxCustomCategory + strValues)
        $.ajax({
            type:"GET",
            url:urlAjaxCustomCategory + strValues,
            /*data:{},*/
            success:function(res){
                 console.log(res)
                 $(".closeMyModal").click();
                 tabla.ajax.reload();
             }
        });
        isEdit = false;

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
        $("#inpNameCat").val(cat.name);
        if(cat.fechaBaja != ""){
            $("#<portlet:namespace/>inpBajaCat").val( (new Date(cat.fechaBaja)).toLocaleDateString("es-ES", { year: 'numeric', month: '2-digit', day: '2-digit' }) );
        }else{
             $("#<portlet:namespace/>inpBajaCat").val('');
        }
        $("#spnTitleNueva").addClass("hide");
        $("#spnTitleEditar").removeClass("hide");
        mostrarModal();
        console.log("editar: " + _id)
    }

</script>

