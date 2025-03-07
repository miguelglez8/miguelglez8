<%@ include file="/init.jsp" %>

<%
CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());

boolean isMaxHour = companyConf.getMaxHoursDay();
float maxHourDay = companyConf.getMaxHoursDayValue();

java.util.ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

List<Activity> activityList = (List<Activity>) request.getAttribute(PrevingUserCustomCalendarPortletKeys.LIST_ACTIVITIES);
%>


<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">

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
    Liferay.Loader.define._amd = Liferay.Loader.define.amd;
    Liferay.Loader.define.amd = false;
</script>

<div class="row" id="rowDataTable">
    <div class="col sm-3">
       <!--  <input type="text" class="w-100 field form-control success-field mt-0" id="spnInputSearch" /> -->
    </div>
    <div class="col sm-3">
    </div>
    <div class="col sm-3">
        <button type="button" id="btnNewUserCalendar"  class="mt-1 right" onClick="addUserCalendar()">
            <liferay-ui:message key="user.custom.calendar.button.newDialog"/>
        </button>
    </div>
</div>
<div class="row mt-3">
    <div class="col-md-12">
        <table id="tableUserCalendars" class="display" style="width:100% !important;">
            <thead>
            <tr>
                <th><liferay-ui:message key="user.custom.calendar.col.name"/></th>
                <th><liferay-ui:message key="user.custom.calendar.col.time"/></th>
                <th><liferay-ui:message key="user.custom.calendar.col.date"/></th>
                <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>




<!-- modal -->
<div class="contentModal  " id="modalContainer" style="border-radius: 10px; width: 850px; height:800px;">
    <div class="col-md-12 headerModal">
        <p id="addEntidad">&nbsp;&nbsp;
        <span id="lblTituloEditar" class="d-none">
            <liferay-ui:message key="user.custom.calendar.button.editDialog"/>
        </span>
        <span id="lblTituloNuevo" class="d-none">
            <liferay-ui:message key="user.custom.calendar.button.newDialog"/>
        </span>

        </p>
    </div>
    <div id="containerModal" style="margin-top: 30px;">
        <div class="container">
            <div class="row myrow m-3" >
                <div class="col-12">
                    <label class="control-label" for="<portlet:namespace/>inpNombre">
                    	<liferay-ui:message key="user.custom.calendar.dialog.content.nombre"/>
                    </label>
                    <input type="text" id="<portlet:namespace/>calendarName" class="w-100 field form-control"/>
                </div>
            </div>
            <hr class="hr" />
            <div class="row myrow m-3" >
                <div class="col-6 ">
                     <label class="control-label" for="<portlet:namespace/>cmbActividad">
                        <liferay-ui:message key="user.custom.calendar.dialog.content.actividad"/>
                    </label>
                    <select  id="<portlet:namespace/>activityId" class="w-100 field form-control">
                        <option value="" data-tipo=""><liferay-ui:message key="user.custom.calendar.dialog.indiqueActividad"/></option>
                        <%for(Activity activity:activityList){%>
                            <c:if test="<%=activity.isActive()%>">
                                <option value="<%=activity.getActivityId()%>" data-tipo="<%=activity.getType()%>">
                                    <%=activity.getName(user.getLocale())%>
                                </option>
                            </c:if>
                        <%}%>
                    </select>
                </div>
                <div class="col-2">
                    <label class="control-label" for="<portlet:namespace/>inpStart">
                        <liferay-ui:message key="user.custom.calendar.dialog.content.entrada"/>
                    </label>
                    <input   type="time"  id="<portlet:namespace/>start" class="w-100 field form-control"/>
                </div>
                <div class="col-2">
                    <label class="control-label" for="<portlet:namespace/>inpEnd">
                        <liferay-ui:message key="user.custom.calendar.dialog.content.salida"/>
                    </label>
                    <input  type="time"  id="<portlet:namespace/>end" class="w-100 field form-control"/>
                </div>
                <div class="col-2">
                    <label class="control-label" for="<portlet:namespace/>btnDialogNew">
                        &nbsp;
                    </label>
                    <span class="w-100 field form-control" style="border: none !important;margin-top: -5px;">
                        <button class="btn-primary buttonModal"
                        onclick="addUserCalendarRow()" id="<portlet:namespace/>btnDialogNew">&nbsp; + &nbsp;</button>
                    </span>
                </div>
            </div>
            <hr class="hr" />
            <div class="row myrow m-3" >
                <div class="col-12 mt-1">
                    <span style="min-height:420px; max-height: 420px; overflow-y: auto; display:block;">
                    <table id="tableUserCalendar" class="w-100 display dataTable no-footer">
                        <thead>
                            <tr>
                                <th>
                                    <liferay-ui:message key="user.custom.calendar.dialog.content.entrada"/>
                                </th>
                                <th>
                                    <liferay-ui:message key="user.custom.calendar.dialog.content.salida"/>
                                </th>
                                <th>
                                    <liferay-ui:message key="user.custom.calendar.dialog.content.tiempo"/>
                                </th>
                                <th>
                                    <liferay-ui:message key="user.custom.calendar.dialog.content.tipo"/>
                                </th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="row mt-1">
                        <div class="col-10 " ><span class="alert alert-danger d-none" id="capaErrorDialog">&nbsp;</span></div>
                        <div class="col-2 text-right font-weight-bold" id="contadorHorasDialog" >&nbsp; </div>
                    </div>
                    </span>
                </div>
            </div>

        </div>
        <div class="footerModal">
             <button type="button" class="btn-primary buttonModal" id="btnSaveCad"
                onclick="sendCalendar()" data-dismiss="modal" aria-label="Close">
                <liferay-ui:message key="user.custom.calendar.button.guardar"/>
             </button>

            <button type="button" class="buttonModal closeMyModal " id="btnCancel"  style="background-color: buttonface;color: black;" data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="user.custom.calendar.button.cancelar" />
            </button>
        </div>
    </div>
</div>
<div class="divModal" id="modalPortlet">
</div>
<!-- modal delete -->
 <div class="contentModal_delete  alert-modal warning" id="modalContainer_delete"
    style="border-radius: 10px;width: 420px; height: 375px">
        <div class="col-md-12 headerModal">
           <span class="ml-3">
                <liferay-ui:message key="user.custom.calendar.button.deleteUserCalendar"/>
           </span>
        </div>
        <div id="containerModal" style="margin-top: 30px;">
            <div class="container">
                <p id="spnPreguntaEliminar" class="ml-3" style="min-height:80px;"></p>
            </div>

         <div class="footerModal">
             <button type="button" class="btn-primary buttonModal" id="btnSaveCad"
                onclick="deleteUserCalendar()" data-dismiss="modal" aria-label="Close">
                <liferay-ui:message key="user.custom.calendar.button.aceptar"/>
             </button>

            <button type="button" class="buttonModal closeMyModal_Delete " id="btnCancel"  style="background-color: buttonface;color: black;" data-dismiss="modal" aria-label="Close">
               <liferay-ui:message key="user.custom.calendar.button.cancelar" />
            </button>
        </div>
       </div>
    </div>
<div class="divModal_delete" id="modalPortlet_delete">

</div>



<portlet:resourceURL id="getUserCustomCalendars" var="getUserCustomCalendarsURL"/>
<portlet:resourceURL id="getUserCustomCalendarsDetails" var="getUserCustomCalendarsDetailsURL"/>
<portlet:resourceURL id="createUserCustomCalendar" var="createUserCustomCalendarURL"/>
<portlet:resourceURL id="deleteUserCustomCalendar" var="deleteUserCustomCalendarURL"/>

<script>
var isMaxHoursDay = "<%=companyConf.getMaxHoursDay()%>";
var maxHoursDay = "<%=companyConf.getMaxHoursDayValue()%>";

var jsonDataCalendar = []; //calendario editado/creado
var jsonNameCalendar = null;
var tablaUserCustomCalendars = null;
var contNewSign = 0;
var editUserCalendarId = 0;
var params =  "&companyId=<%=companyConf.getCompanyConfId()%>" + "&groupId=<%= themeDisplay.getScopeGroupId()%>" + "&userId=<%=themeDisplay.getUserId()%>";
var urlAjaxNameCalendarsDatatable = "<%=getUserCustomCalendarsURL.toString()%>" + params;
var urlAjaxDataCalendars = "<%=getUserCustomCalendarsDetailsURL.toString()%>";
var urlAjaxAddCalendars = "<%=createUserCustomCalendarURL.toString()%>";
var urlAjaxDeleteCalendars = "<%=deleteUserCustomCalendarURL.toString()%>";

var arrTimeStartCol = null, resCol = 0, arrDateCol = null;
$(document).ready( function () {
    tablaUserCustomCalendars = $('#tableUserCalendars').DataTable( {
            ajax: {
                "url": urlAjaxNameCalendarsDatatable,
                "dataSrc": function ( json ) {
                                jsonNameCalendar = json.data;
                                return json.data;
                           }
            },
            dom: 'frtip',
            pageLength: 20,
            responsive: false,
            initComplete : function (){
                $("#tableUserCalendars_filter").addClass("d-none");
                    $("#spnInputSearch").on("keyup", function(e){
                    $("#tableUserCalendars_filter  input").val($("#spnInputSearch").val());
                        $("#tableUserCalendars_filter  input").trigger("keyup")
                    });
            },
            buttons: [
                'csv'
            ],
            "columns": [
                {"data" : "name"},
                {"data" : "time"},
                {"data" : "createdDate"},
                {"data" : "userCustomCalendarId"} //editar
            ],
            "columnDefs": [
                    {
                    "targets"  : [0],
                    "orderable": true,
                    "render": function (data, type, row) {
                               return data;
                              }
                    },
                    {
                        "targets"  : [1],
                        "orderable": true,
                        "render": function (data, type, row) {
                             //var arrTimeStartCol = null, resCol = 0;

                             console.log(row)

                             data = data / 60;
                             if( data % 1 !== 0){
                                    arrTimeStartCol = String(data).split(".");
                                    resCol = arrTimeStartCol[0] + " h " + ((data % 1) * 60).toFixed(0) + " min";
                             }else{
                                    resCol = data  + " h 0 min";
                             }
                          return resCol;
                        }
                    },
                    {
                    "targets"  : [2],
                    "orderable": true,
                    "render": function (data, type, row) {
                        arrDateCol = data.toString().split(" ");
                        arrDateCol = arrDateCol[0].split("-");
                        return arrDateCol[2] + "/" + arrDateCol[1] + "/" + arrDateCol[0];

                    }
                },
                    {
                        "targets"  : [3],
                        "orderable": false,
                        "searchable": false,
                        "className": "text-right",
                        "render": function (data, type, row) {
                            return '<span class="fa fa-pencil" title="' + '<%=LanguageUtil.get(bundle, "user.custom.calendar.button.editDialog")%>' + '" onclick="editUserCalendar(' + data + ')"> &nbsp;</span>' +
                                        '&nbsp;&nbsp;<span class="fa fa-trash" title="' + '<%=LanguageUtil.get(bundle, "user.custom.calendar.button.deleteUserCalendar")%>' + '" onclick="eliminateUserCalendar(' + data + ')"> &nbsp;</span>';


                        }
                    }
                ],
            "language": {
               "sProcessing":     "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sProcessing")%>",
                   "sLengthMenu":     "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sLengthMenu")%>",
                   "sZeroRecords":    "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sZeroRecords")%>",
                   "sEmptyTable":     '<liferay-ui:message key="user.custom.calendar.datatable.sEmptyTableWorkCenters"/>',
                   "sInfo":           "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sInfo")%>",
                   "sInfoEmpty":      "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sEmptyTableWorkCenters")%>",
                   "sInfoFiltered":   "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sInfoFiltered")%>",
                   "sInfoPostFix":    "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sInfoPostFix")%>",
                   "sSearch":         "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sSearch")%>",
                   "sUrl":            "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sUrl")%>",
                   "sInfoThousands":  "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sInfoThousands")%>,",
                   "sLoadingRecords": "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sLoadingRecords")%>",
                   "oPaginate": {
                       "sFirst":    "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sFirst")%>",
                       "sLast":     "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sLast")%>",
                       "sNext":     "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sNext")%>",
                       "sPrevious": "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sPrevious")%>"
                   },
                   "oAria": {
                       "sSortAscending":  "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sSortAscending")%>",
                       "sSortDescending": "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.sSortDescending")%>"
                   },
                   "buttons": {
                       "copy": "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.copy")%>",
                       "colvis": "<%=LanguageUtil.get(bundle, "user.custom.calendar.datatable.colvis")%>"
                }
            }
            });



}); // end document ready



function mostrarModal(){
        $('#modalPortlet').css('display','block');
        $('#modalContainer').css('display','block');
        cleanModal();
}

$('.closeMyModal').click(function(){
                $('.divModal').hide();
                $('.contentModal').hide();
                $("#wrapper").css("z-index","0");
                /*$("#<portlet:namespace/>inpNombre").val('');
                $("#<portlet:namespace/>inpFechaBaja").val('');*/
});

$('.closeMyModal_Delete').click(function(){
                $('.divModal_delete').hide();
                $('.contentModal_delete').hide();
                $("#wrapper").css("z-index","0");

});

function sendCalendar(){

    if($("#<portlet:namespace/>calendarName").val() == "" || jsonDataCalendar.length == 0 ) return;
    if(isMaxHoursDay == "true" && Number(totalMinutes) > (maxHoursDay*60)){
        return;
    }

    var formData = new FormData();

    formData.append('companyId',"<%=companyConf.getCompanyConfId()%>");
    formData.append('groupId',"<%=themeDisplay.getScopeGroupId()%>");
    formData.append('userId',"<%=themeDisplay.getUserId()%>");

    var dataJson = {};
    var arrData = [];
    var item = {};

    item.userNameCalendarId = editUserCalendarId;
    item.name = $("#<portlet:namespace/>calendarName").val();

    item.signs = jsonDataCalendar;

    formData.append('data', JSON.stringify(item));

    $.ajax({
        type: "POST",
        url: urlAjaxAddCalendars,
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
          console.log("Okay: " + result);
          tablaUserCustomCalendars.ajax.reload();
          tablaUserCustomCalendars.draw();
          $('.closeMyModal').click();
      }
    });

}

/*Añadir un fichaje a la tabla/json*/
function addUserCalendarRow(){
    $("#capaErrorDialog").html('');
    $("#capaErrorDialog").addClass("d-none");
    if($("#<portlet:namespace/>activityId").val() == ""
        || $("#<portlet:namespace/>start").val() == ""
            || $("#<portlet:namespace/>end").val() == ""){


        $("#capaErrorDialog").html('<liferay-ui:message key="user.custom.calendar.error.faltanCampos"/>');
        $("#capaErrorDialog").removeClass("d-none");
        return;
    }


    if(solapamiento($("#<portlet:namespace/>start").val(), $("#<portlet:namespace/>end").val())){
        console.log("Solapamiento");
        $("#capaErrorDialog").html('<liferay-ui:message key="user.custom.calendar.error.solapamiento"/>');
        $("#capaErrorDialog").removeClass("d-none");
        return;
    }

    var item = {};
    var resCalculate = null;
    item.id = "n-" + ++contNewSign;
    item.activityId = $("#<portlet:namespace/>activityId").val();
    item.activityText = $("#<portlet:namespace/>activityId option:selected").text();
    item.activityType = $("#<portlet:namespace/>activityId option" + "[value=" + item.activityId + "]").attr("data-tipo");
    item.start = $("#<portlet:namespace/>start").val();
    item.end = $("#<portlet:namespace/>end").val();
    resCalculate = calculateTime(item.start, item.end);
    item.time = resCalculate.label;
    item.minutes = resCalculate.minutes;


    jsonDataCalendar.push(item);

    $("#<portlet:namespace/>activityId").val("");
    $("#<portlet:namespace/>start").val("");
    $("#<portlet:namespace/>end").val("");


    paintTableSigns();
}


function deleteUserCalendar(){

    var formData = new FormData();
    formData.append('companyId',"<%=companyConf.getCompanyConfId()%>");
    formData.append('groupId',"<%=themeDisplay.getScopeGroupId()%>");
    formData.append('userId',"<%=themeDisplay.getUserId()%>");
    formData.append('userNameCalendarId',idToEliminate);


    $.ajax({
        type: "POST",
        url: urlAjaxDeleteCalendars,
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            console.log("Okay: " + result);
            tablaUserCustomCalendars.ajax.reload();
            tablaUserCustomCalendars.draw();
            $('.closeMyModal_Delete').click();
      }
    });



    idToEliminate = 0;
}


function cleanModal(){
    //$("#<portlet:namespace/>calendarId").val("");
    $("#<portlet:namespace/>activityId").val("");
    $("#<portlet:namespace/>start").val("");
    $("#<portlet:namespace/>end").val("");

    $("#capaErrorDialog").html("");
    $("#capaErrorDialog").addClass("d-none");

}

function addUserCalendar(){
    editUserCalendarId = 0;
    cleanModal();

    $("#lblTituloEditar").addClass("d-none");
    $("#lblTituloNuevo").removeClass("d-none");


    var tabla = document.getElementById("tableUserCalendar");
    var cuerpoTabla = tabla.getElementsByTagName("tbody")[0];
    while (cuerpoTabla.firstChild) {
       cuerpoTabla.removeChild(cuerpoTabla.firstChild);
    }
    jsonDataCalendar = [];
    $("#contadorHorasDialog").html("0 h 0 min");
    $("#<portlet:namespace/>activityId").val('');
    $("#<portlet:namespace/>start").val('');
    $("#<portlet:namespace/>end").val('');
    $("#<portlet:namespace/>calendarName").val('');
    mostrarModal();

}

var txtOriginal =  '<liferay-ui:message key="user.custom.calendar.dialog.content.preguntaEliminar"/>';
var idToEliminate = 0;
function eliminateUserCalendar(_id){
    var name = "";
    idToEliminate = _id;
    for(var x in jsonNameCalendar){
        if(jsonNameCalendar[x].userCustomCalendarId == _id){
            name = jsonNameCalendar[x].name;
            break;
        }
    }
    $("#spnPreguntaEliminar").html(txtOriginal.replace("#NOMBRE#",name));
    $('#modalPortlet_delete').css('display','block');
    $('#modalContainer_delete').css('display','block');
}

function editUserCalendar(_id){
    $("#capaErrorDialog").html('');
    $("#capaErrorDialog").addClass("d-none");

    $("#lblTituloEditar").removeClass("d-none");
    $("#lblTituloNuevo").addClass("d-none");

    editUserCalendarId = _id;
    var resCalculate = null;
    var formData = new FormData();
    formData.append('calendarId',_id);
    formData.append('companyId',"<%=companyConf.getCompanyConfId()%>");
    formData.append('groupId',"<%=themeDisplay.getScopeGroupId()%>");
    formData.append('userId',"<%=themeDisplay.getUserId()%>");

    $.ajax({
        type: "POST",
        url: urlAjaxDataCalendars,
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
          console.log("Okay: " + result);
          //if(result.data.length > 0){

            //COMPLETAR CON LOS NOMBRES DE ACTIVIDAD
            for(var i = 0; i < result.data.length; i++){
                result.data[i].activityText =  $('#<portlet:namespace/>activityId option[value="' + result.data[i].activityId + '"]').text();
                resCalculate = calculateTime(result.data[i].start, result.data[i].end);
                result.data[i].time = resCalculate.label;
                result.data[i].minutes = resCalculate.minutes;
            }

            jsonDataCalendar = result.data;
            $("#<portlet:namespace/>calendarName").val(result.name);

          //}

          mostrarModal();
          paintTableSigns();
      }
    });
}

function deleteRowSign(_id){

    for(x = 0; x < jsonDataCalendar.length;x++){
        if(jsonDataCalendar[x].id == _id){
            jsonDataCalendar.splice(x,1);
            break;
        }

    }
    paintTableSigns();
}
var totalMinutes = 0;
function paintTableSigns(){
    var tabla = document.getElementById("tableUserCalendar");
    var cuerpoTabla = tabla.getElementsByTagName("tbody")[0];
    while (cuerpoTabla.firstChild) {
       cuerpoTabla.removeChild(cuerpoTabla.firstChild);
    }
    var fila = null, celda = null, txtCelda = null, item = null;

    jsonDataCalendar.sort(comparateHoras);
    var arrCampos = ["start","end","time","activityText"];
    totalMinutes = 0;
    for(var i = 0; i < jsonDataCalendar.length; i++){
        fila = document.createElement("tr");
        if(jsonDataCalendar[i].activityType == 1){
            totalMinutes += jsonDataCalendar[i].minutes;
        }

        for(var c = 0; c < arrCampos.length; c++){
            celda = document.createElement("td");
            item = jsonDataCalendar[i];
            textoCelda = document.createTextNode(item[arrCampos[c]]);
            celda.appendChild(textoCelda);
            fila.appendChild(celda);
        }
        celda = document.createElement("td");
        //textoCelda = document.createElement('<span class="fa fa-trash" onClick="deleteRowSign(' + '"' + item.id + '")>&nbsp;</span>');
        textoCelda = document.createElement('span');
        textoCelda.classList.add("fa")
        textoCelda.classList.add("fa-trash");
        textoCelda.onclick = function(_id){
            return function(){
                deleteRowSign(_id);
            }
        }(item.id);


        celda.appendChild(textoCelda);
        fila.appendChild(celda);

        cuerpoTabla.appendChild(fila);
    }


    if(jsonDataCalendar.length > 0) $("#<portlet:namespace/>start").val(jsonDataCalendar[jsonDataCalendar.length - 1].end);
    $("#contadorHorasDialog").html(formatTime(totalMinutes));

    $("#capaErrorDialog").addClass("d-none");
    if(isMaxHoursDay == "true" && Number(totalMinutes) > (maxHoursDay*60)){
        $("#capaErrorDialog").removeClass("d-none");
        $("#capaErrorDialog").html('<liferay-ui:message key="user.custom.calendar.error.limitHoursDay" />'.replace('#HORAS#',maxHoursDay));
    }

}


function calculateTime(_start, _end){
    var arrTimeStart = null, arrTimeEnd = null;
    var start = 0, end = 0;
    var res = {};
    res.label = "";
    res.minutes = 0;
    arrTimeStart =_start.split(":");
    arrTimeEnd = _end.split(":")

    start = (Number(arrTimeStart[0]) * 60) + Number(arrTimeStart[1]);
    end = (Number(arrTimeEnd[0]) * 60) + Number(arrTimeEnd[1]);
    res.minutes += (end - start);

     res.label = formatTime(res.minutes);

    console.log(res)
    return res;
}

function formatTime(_minutes){
    var txt = "";
    if( _minutes % 60 === 0){
         txt = (_minutes / 60) + " h "
    }else{
        if( ((_minutes / 60) % 60) < 1){
            txt = _minutes  + " min";
        }else{
            txt = (String(_minutes/60).split("."))[0] + " h " + (_minutes % 60) .toFixed(0) + " min";
        }
    }
    return txt;
}

 function solapamiento(_dS,_dE){
    var isSolapa = false;

    var arrDs = _dS.split(":");
    var dS = new Date();
    dS.setHours(arrDs[0],arrDs[1],0,0);

    arrDs = _dE.split(":");
    var dE = new Date();
    dE.setHours(arrDs[0],arrDs[1],0,0);


    var dS_sign = new Date();
    var dE_sign = new Date();

    var x = 0;

    for(x = 0; x < jsonDataCalendar.length;x++){
        arrDs = jsonDataCalendar[x].start.split(":");
        dS_sign.setHours(arrDs[0],arrDs[1],0,0);

        arrDs = jsonDataCalendar[x].end.split(":");
        dE_sign.setHours(arrDs[0],arrDs[1],0,0);

        /*console.log("dS: " + dS + " / dE: " + dE);
        console.log("dS_sign: " + dS_sign + " / dE_sign: " + dE_sign);*/

        if(  (dS.getTime() >= dS_sign.getTime() && dS.getTime() < dE_sign.getTime()) ||
            (dE.getTime() > dS_sign.getTime() && dE.getTime() <= dE_sign.getTime()) ){
            isSolapa = true;
            break;
        }else  if(  (dS_sign.getTime() >= dS.getTime() &&  dE_sign.getTime() < dE.getTime()) ||
            (dS_sign.getTime() >= dE.getTime() && dE_sign.getTime() <= dE.getTime() ) ){
            isSolapa = true;
            break;
        }
    }
    return isSolapa;
}
function comparateHoras(a, b) {
    var horaA = a.start.split(":");
    var horaB = b.start.split(":");

    var horasA = parseInt(horaA[0], 10);
    var minutosA = parseInt(horaA[1], 10);
    var horasB = parseInt(horaB[0], 10);
    var minutosB = parseInt(horaB[1], 10);

    if (horasA !== horasB)  return horasA - horasB;
    return minutosA - minutosB;
}
</script>