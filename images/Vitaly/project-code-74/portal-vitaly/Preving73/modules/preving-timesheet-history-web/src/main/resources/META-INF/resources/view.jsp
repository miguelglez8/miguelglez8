<%@ include file="/init.jsp" %>

<script>

    var p_auth = "?p_auth=" + Liferay.authToken;

    var activities = '${activities}';
    var holidays = '${holidays}';
    var signs    = '${signs}';

    console.log(activities);
    console.log(holidays);
    console.log(signs);

    var xhrURL_Actividades = {
        actividades: activities,
        festivos: holidays,
        fichajes: signs
    };


    $(document).ready(function(){
        //iniciar el calendario
        var ID_USUARIO = "ID_USUARIO";
        CalendarioBarras.initCalendarioBarras(xhrURL_Actividades,ID_USUARIO, null, null, "<%=user.getLocale()%>");

        var dS = new Date();

        dS.setTime(1594306800000);
        var dE = new Date();
        dE.setTime(1594317600000);
        console.log(dS.getTimezoneOffset())
        console.log("start: " + dS.toString());
        console.log("end : " + dE.toString());

    });


</script>

<div>
    <div class="row py-3">
        <div class="col" style="padding-top:1.1%;">
            <button onclick="CalendarioBarras.moveMonth(-1)" type="button">&lt;</button>
            &nbsp;&nbsp;<span id="spanMesActual" style="width:150px;display:inline-table;text-align:center;"><liferay-ui:message key="timesheethistory.view.month"/></span>&nbsp;&nbsp;
            <button onclick="CalendarioBarras.moveMonth(+1)" type="button">&gt;</button>
        </div>
        <div class="col text-right">
            <div class="row">
                <div class="col-sm-5" style="padding:0; padding-top:3%; text-align: left;"><liferay-ui:message key="timesheethistory.view.from"/>&nbsp;
                    <liferay-ui:input-date name="inpFechaDesde"/>
				</div>
                <div class="col-sm-5" style="padding:0; padding-top:3%; text-align: left;"><liferay-ui:message key="timesheethistory.view.to"/>&nbsp;&nbsp;
                    <liferay-ui:input-date name="inpFechaHasta"/>
				</div>
                <div class="col-sm-2" style="padding:0; padding-top:3%;"><button onClick="CalendarioBarras.filterFechas()" type="button"><liferay-ui:message key="timesheethistory.view.search"/></button></div>
            </div>
        </div>
    </div>

    <div class="row">
        <div id="contenedorCalendario" class="col">
            <div id="capaHorario" ></div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="row border">
                <div class="col text-right"><b><liferay-ui:message key="timesheethistory.view.total.time"/>: <span id="spanTotalHoras"></span></b></div>
            </div>
        </div>
    </div>
    <div class="row py-3">
        <div id="capaLeyenda"class="col"></div>
    </div>
    <div class="row">
        <div id="capaDetalles" class="col"></div>
    </div>
</div>
