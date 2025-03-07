/* import * as CONSTANTES_PICKER from './constantes.js'; */

//const URL_DOMAIN = "http://localhost:8080";
var URL_DOMAIN = "http://localhost:8081";
//REAL:
var URL_ADD =  "/api/jsonws/preving.sign/add-sign/company-id/#COMPANY_ID#/group-id/#GROUP_ID#/user-id/#USER_ID#/activity-id/#ID_ACTIVIDAD#/start-date/#START_DATE#/finish-date/#END_DATE#/observations/#OBSERVACIONES#?p_auth=#P_AUTH#";
var URL_UPDATE = "/api/jsonws/preving.sign/update-sign/sign-id/#ID_SIGN#/activity-id/#ID_ACTIVIDAD#/start-date/#START_DATE#/finish-date/#END_DATE#/observations/#OBSERVACIONES#?p_auth=#P_AUTH#"
var URL_DEL =  "/api/jsonws/preving.sign/delete-sign/sign-id/#ID_SIGN#?p_auth=#P_AUTH#";
//PARA PRUEBAS:
/*const URL_ADD = "/previngJSON/add.html?COMPANY_ID=#COMPANY_ID#&group-id=#GROUP_ID#&user-id=#USER_ID#&activity-id=#ID_ACTIVIDAD#&start-date=#START_DATE#&finish-date=#END_DATE#&observations=#OBSERVACIONES#?p_auth=#P_AUTH#";
const URL_UPDATE = "/previngJSON/add.html?COMPANY_ID=#COMPANY_ID#&group-id=#GROUP_ID#&user-id=#USER_ID#&activity-id=#ID_ACTIVIDAD#&start-date=#START_DATE#&finish-date=#END_DATE#&observations=#OBSERVACIONES#?p_auth=#P_AUTH#";
const URL_DEL = "/previngJSON/del.html?/#ID_SIGN#?p_auth=#P_AUTH#";*/

var HOY = new Date(new Date().setHours(23,59,59,0));
var HOY_MIN = new Date(new Date().setHours(0,0,0,0));

//Mensajes
var LOCALIZE = {
    es: {
        meses : ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
        dias : ["L", "M", "X", "J", "V", "S", "D"],
        mensajes: {
            editDelnotPermitido : " No esta permitido editar o borrar registros",
            tiempoEditSuperado : "Tiempo máximo para editar el registro superado",
            tiempoDelSuperado : "Tiempo máximo para borrar el registro superado",
            saveHolidayDays : "No esta permitido el registro este día festivo",
            finAntesInicio: "Debe introducir una hora de fin superior a la de inicio",
            noFuture : "No esta permitido realizar registros de días superiores a hoy",
            savePastDays : "No se permite el registro con fecha a inferior a hoy",
            savePastDaysValue : "No se permite el registro inferior a #DIAS# días laborables con respecto a la fecha actual",
            maxHoursWeekValue : "No se permite introducir mas de #LIMITE# semanales",
            faltaActividad : "Debe seleccionar una actividad",
            faltaFecha : "Debe seleccionar el día que desea en el calendario",
            faltaHoras : "Debe introducir la hora de inicio del registro",
            horasIguales : "La hora de inicio y de fin no pueden ser la misma",
            fichajeFuturo : "No se pueden introducir registros superiores a hoy",
            solapamiento : "El registro introducido coincide con otro registro ",
            maximoDia: "Los registros superan el maximo de horas por día",
            maximoSemana: "Los registros superan el maximo de horas por semana",
            actividadDesconocida: "Actividad desconocida",
            entrada: "Entrada",
            salida: "Salida",
            tiempo: "Tiempo",
            tipo: "Tipo",
            observaciones: "Observaciones",
            observacionesEmpresa: "Observaciones empresa",
            minDia: "minutos en el día.",
            minSemana: "minutos en la semana.",
            eliminar: "Eliminar",
            sinSalida: "-Sin salida-",
            abierto: "-Abierto-",
            editandoFichaje: "Editando fichaje ",
            cerrado: "cerrado, solo puede modificar las observaciones",
            abiertoMensaje: "abierto, puede indicar la hora de finalización y las observaciones",
            fichajesSinCerrar: "Tiene fichajes sin cerrar, debe finalizarlos antes de introducir uno nuevo.",
            soloDiaCurso: "Solo puede introducir registros horarios abiertos en el día en curso.",
            eliminarRegistro: "¿Desea eliminar el fichaje?",
            dia: "Día",
            semana: "Semana"
        }
    },
    es_ES: {
        meses : ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
        dias : ["L", "M", "X", "J", "V", "S", "D"],
        mensajes: {
            editDelnotPermitido : " No esta permitido editar o borrar registros",
            tiempoEditSuperado : "Tiempo máximo para editar el registro superado",
            tiempoDelSuperado : "Tiempo máximo para borrar el registro superado",
            saveHolidayDays : "No esta permitido el registro este día festivo",
            finAntesInicio: "Debe introducir una hora de fin superior a la de inicio",
            noFuture : "No esta permitido realizar registros de días superiores a hoy",
            savePastDays : "No se permite el registro con fecha a inferior a hoy",
            savePastDaysValue : "No se permite el registro inferior a #DIAS# días laborables con respecto a la fecha actual",
            maxHoursWeekValue : "No se permite introducir mas de #LIMITE# semanales",
            faltaActividad : "Debe seleccionar una actividad",
            faltaFecha : "Debe seleccionar el día que desea en el calendario",
            faltaHoras : "Debe introducir la hora de inicio del registro",
            horasIguales : "La hora de inicio y de fin no pueden ser la misma",
            fichajeFuturo : "No se pueden introducir registros superiores a hoy",
            solapamiento : "El registro introducido coincide con otro registro ",
            maximoDia: "Los registros superan el maximo de horas por día",
            maximoSemana: "Los registros superan el maximo de horas por semana",
            actividadDesconocida: "Actividad desconocida",
            entrada: "Entrada",
            salida: "Salida",
            tiempo: "Tiempo",
            tipo: "Tipo",
            observaciones: "Observaciones",
            observacionesEmpresa: "Observaciones empresa",
            minDia: "minutos en el día.",
            minSemana: "minutos en la semana.",
            eliminar: "Eliminar",
            sinSalida: "-Sin salida-",
            abierto: "-Abierto-",
            editandoFichaje: "Editando fichaje ",
            cerrado: "cerrado, solo puede modificar las observaciones",
            abiertoMensaje: "abierto, puede indicar la hora de finalización y las observaciones",
            fichajesSinCerrar: "Tiene fichajes sin cerrar, debe finalizarlos antes de introducir uno nuevo.",
            soloDiaCurso: "Solo puede introducir registros horarios abiertos en el día en curso.",
            eliminarRegistro: "¿Desea eliminar el fichaje?",
            dia: "Día",
            semana: "Semana"
        }
    },
    eus : {
        meses : [],
        dias : [],
        mensajes: {
            finAntesInicio: "",
            savePastDays : "",
            maxHoursWeekValue : ""
        }
    },
    ca_ES : {
        meses : ["Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"],
        dias : ["L", "M", "X", "J", "V", "S", "D"],
        mensajes: {
            editDelnotPermitido : "No està permès editar o esborrar registres",
            tiempoEditSuperado : "Temps màxim per editar el registre superat",
            tiempoDelSuperado : "Temps màxim per esborrar el registre superat",
            saveHolidayDays : "No està permès el registre d'aquest dia festiu",
            finAntesInicio: "Ha d'introduir una hora de finalització superior a la d'inici",
            noFuture : "No està permès realitzar registres de dies superiors a avui ",
            savePastDays : "No es permet el registre amb data inferior a avui",
            savePastDaysValue : "No es permet el registre inferior a #DIAS# dies laborals respecte a la data actual",
            maxHoursWeekValue : "No es permet introduir més de #LIMITE# setmanals ",
            faltaActividad : "Ha de seleccionar una activitat ",
            faltaFecha : "Ha de seleccionar el dia que desitja al calendari",
            faltaHoras : "Ha d'introduir l'hora d'inici del registre",
            horasIguales : "L'hora d'inici i de finalització no poden ser la mateixa ",
            fichajeFuturo : "No es poden introduir registres superiors a avui ",
            solapamiento : "El registre introduït coincideix amb un altre registre  ",
            maximoDia: "Els registres superen el màxim d'hores per dia",
            maximoSemana: "Els registres superen el màxim d'hores per setmana",
            actividadDesconocida: "Activitat desconeguda ",
            entrada: "Entrada ",
            salida: "Sortida",
            tiempo: "Temps",
            tipo: "Tipus",
            observaciones: "Observacions",
            observacionesEmpresa: "Observacions empresa",
            minDia: "minuts durant el dia.",
            minSemana: "minuts durant la setmana.",
            eliminar: "Eliminar",
            sinSalida: "-Sense sortida-",
            abierto: "-Obert-",
            editandoFichaje: "Editant fitxatge",
            cerrado: "tancat, només pot modificar les observacions",
            abiertoMensaje: "obert, pot indicar l'hora de finalització i les observacions",
            fichajesSinCerrar: "Té fitxatges sense tancar, ha de finalitzar-los abans d'introduir-ne un de nou.",
            soloDiaCurso: "Només pot introduir registres horaris oberts en el dia en curs.",
            eliminarRegistro: "¿Desitja eliminar el fitxatge? ",
            dia: "Dia",
            semana: "Setmana"
        }
    },
    gal : {}
};



var calendarioPicker = (function(){
    version : 0.4;
    return{
        urlSigns : "/previngJSON/signs.js",
        /*arrAJAXurl :[{holidays: "/previngJSON/holidays.js"},
            {activities: "/previngJSON/activities.js"},
            { rules : "/previngJSON/rules.js"}],*/

        urlSigns: "/api/jsonws/preving.sign/get-signs-by-user-id-between-dates/company-id/#COMPANY_ID#/group-id/#GROUP_ID#/user-id/#USER_ID#/start-date/#DATE_START#/end-date/#DATE_END#?p_auth=#P_AUTH#",
        arrAJAXurl :[{holidays:  "/api/jsonws/preving.holiday/get-holidays-from-group/company-id/#COMPANY_ID#/group-id/#GROUP_ID#?p_auth=#P_AUTH#"},
            {activities: "/api/jsonws/preving.activity/get-activities-from-group/company-id/#COMPANY_ID#/group-id/#GROUP_ID#?p_auth=#P_AUTH#"},
            { rules : "/api/jsonws/preving.companyconf/get-company-conf-by-company-id-and-group-id/company-id/#COMPANY_ID#/group-id/#GROUP_ID#?p_auth=#P_AUTH#"}],
        isDelete : false,
        isLoadPage : false,
        JSON_DATA : {},
        MENSAJE : null,
        idioma : "es_ES",
        companyId : null,
        groupId : null,
        userId : null,
        pAuth : null,
        arrFichajes: null,
        objSemana: null,
        diaClick : null,
        fichajesDiaSeleccionado : [],
        tiempoTrabajoSemana: 0,
        tiempoTrabajadoDia: 0,
        objFichaje: null, //Para pruebas sin LF
        idFichajeEdit: null,
        isFichajeClose: false,
        //@see inicio
        init : function(dominio,companyId,groupId,userId,pAuth, idioma){
            URL_DOMAIN = dominio;
            this.companyId = companyId;
            this.groupId = groupId;
            this.userId = userId;
            this.pAuth = pAuth;
            this.idioma = idioma;
            $('#calendarioClicker').calendar({
                color:'#1ab394',
                months: (LOCALIZE[idioma]).meses,
                days: (LOCALIZE[idioma]).dias,
                onSelect:function (event) {
                    calendarioPicker.clickCalendario(event);
                }
            });
            this.isLoadPage = true;
            this.getJsonData(0);

        },
        getURLAjax : function(url){
            return URL_DOMAIN  + url.replace("#COMPANY_ID#",this.companyId)
                .replace("#GROUP_ID#",this.groupId)
                .replace("#USER_ID#",this.userId)
                .replace("#P_AUTH#",this.pAuth);
        },
        getJsonData : function(indice){
            //try{
            //obtener reglas , actividades y festivos
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (this.readyState == 4 && this.status == 200){
                    //if(calendarioPicker.cont++ > 20) return;
                    calendarioPicker.JSON_DATA[Object.keys(calendarioPicker.arrAJAXurl[indice])[0]] = JSON.parse(this.responseText);
                    if(indice < calendarioPicker.arrAJAXurl.length - 1){
                        calendarioPicker.getJsonData(++indice);
                    }else{ //seguimos con los signs del mes actual
                        var hoy = new Date();
                        var dS = new Date(), dE = new Date();
                        dS.setTime(hoy.getTime());
                        dS.setDate(1); //primer dia del mes
                        dE.setDate(calendarioPicker.getDaysInMonth(hoy.getMonth(),hoy.getFullYear())); //ultimo día
                        calendarioPicker.getSigns ({diaClick: hoy, inicio: dS, fin: dE});
                        //calendarioPicker.loadComboActividades();
                        document.getElementsByClassName("today")[0].click();
                        //siempre, no se puede introducir en el pasado
                        //calendarioPicker.JSON_DATA.rules.savePastDays = false;

                        /*
                        creationDate.setTime(this.arrFichajes[i].createDate);
                        if(this.JSON_DATA.rules.editSigns && (Date.now() <= (creationDate.getTime() + this.JSON_DATA.rules.editSignsValue*60*1000) )){ //creación cumple los limites de tiempo
                        */

                        window.setInterval(calendarioPicker.timerPapeleras, 5000);
                    }
                }
                //this.isLoadPage = true;
            };
            var key = Object.keys(this.arrAJAXurl[indice])[0];
            xhr.open("GET", this.getURLAjax((this.arrAJAXurl[indice])[key]), true);
            xhr.send(null);
            //}catch(e){}
        },
        timerPapeleras : function(){
            var elems = document.getElementsByClassName("fa-trash");
            var item = null;
            var creationDate = new Date();
            //console.log("REVISANDO");
            for(var i = 0; i < elems.length; i++){
                //console.log(elems[i].parentNode.parentNode.id)
                item = calendarioPicker.getSignById(elems[i].parentNode.parentNode.id);
                creationDate.setTime(item.createDate);
                if(calendarioPicker.JSON_DATA.rules.editSigns &&
                    Date.now() >= creationDate.getTime() + parseInt(calendarioPicker.JSON_DATA.rules.editSignsValue*60*1000)){
                    elems[i].style.display = "none";
                }
            }
        },

        //@see cargar el combo de actividades
        //Se hace en el view.jsp
/*        loadComboActividades : function(){
            var option = null;
            var cmb = document.getElementById("cmbActividad");
            if(cmb == null) return null;
            //this.JSON_DATA["activities"].sort(function(a, b){
            //    return a.name > b.name ? 1 : -1;
            //});
            option = document.createElement("option");
            option.text = "";
            cmb.options.add(option);
            for(var i = 0; i < this.JSON_DATA["activities"].length; i++){
                if(this.JSON_DATA.activities[i].active){
                    option = document.createElement("option");
                    option.text = this.cleanEntitys(this.JSON_DATA.activities[i].name);
                    //option.text = this.JSON_DATA.activities[i].name;

                    option.value = this.JSON_DATA.activities[i].activityId;
                    cmb.options.add(option);
                }
            }
        },*/
        textoClean : null,
        cleanEntitys : function(str){
            if(this.textoClean == null){
                var txt = document.createElement("textarea");
                txt.style.display = "none";
            }
            txt.innerHTML = str;
            return txt.value;
        },
        evtTarget : null,
        clickCalendario : function(evt){
            if(this.isLoadPage){

                calendarioPicker.evtTarget = evt;
                //console.log("click dia: " + calendarioPicker.evtTarget );

                document.getElementById("lblMensaje").innerHTML = "";
                calendarioPicker.cleanFormulario();
                var arrFecha = evt.label.split(".");
                this.fechaSeleccionada = evt.label;
                this.diaClick = new Date(arrFecha[2] + "/" + arrFecha[1] + "/" + arrFecha[0])
                document.getElementById("spFecha").innerHTML  = evt.label.replace(/\./g,"/");
                objSemana = this.getPrimerDiaSemana(this.diaClick);

                this.createDetallesDia(objSemana.diaClick, objSemana.inicio, objSemana.fin);
            }
        },

        disabledBotones : function(dS){
            document.getElementById("btnGuardar").disabled = false;
            this.isDelete = true;
            var isProhibidoAnadir =  false; //controlar estas validaciones
            if(this.isFestivo(dS) && !this.canSign(dS)){
                document.getElementById("lblMensaje").innerHTML = (LOCALIZE[this.idioma]).mensajes.saveHolidayDays;
                document.getElementById("btnGuardar").disabled = true;

                isProhibidoAnadir = true;

                //console.log("1");
                this.isDelete = false; //LO CONTRARIO A DISABLED
            }else if(dS.getTime() > HOY.getTime()){
                document.getElementById("lblMensaje").innerHTML = (LOCALIZE[this.idioma]).mensajes.noFuture;
                document.getElementById("btnGuardar").disabled = true;

                isProhibidoAnadir = true;

                this.isDelete = false; //LO CONTRARIO A DISABLED
            }else if(this.JSON_DATA.rules.savePastDays == false && HOY.getTime() < dS.getTime()){
                // no se permite pasado
                document.getElementById("lblMensaje").innerHTML = (LOCALIZE[this.idioma]).mensajes.savePastDays;
                document.getElementById("btnGuardar").disabled = true;

                isProhibidoAnadir = true;

                this.isDelete = false;
                //}else if(this.JSON_DATA.rules.savePastDays == true && HOY.getTime() - ((Number(this.JSON_DATA.rules.savePastDaysValue)+1)*60*60*24*1000) >= dS.getTime()){
            }else if(!this.limitePasadoDias(this.JSON_DATA.rules.savePastDaysValue,HOY,dS)){
                //supera en dias lo permitido en pasado
                document.getElementById("lblMensaje").innerHTML = (LOCALIZE[this.idioma]).mensajes.savePastDaysValue.replace("#DIAS#",this.JSON_DATA.rules.savePastDaysValue);
                document.getElementById("btnGuardar").disabled = true;

                isProhibidoAnadir = true;

                this.isDelete = false;
            }else if(this.JSON_DATA.rules.maxHoursDay == true && this.tiempoTrabajadoDia >= (this.JSON_DATA.rules.maxHoursDayValue *60*60*1000)){
                //no se permiten más horas dia
                document.getElementById("btnGuardar").disabled = true;
                //console.log("5 : maxHoursDay: " + this.JSON_DATA.rules.maxHoursDay + ", tiempoTrabajadoDia: " + this.tiempoTrabajadoDia + ", maxHoursDayValue: " + this.JSON_DATA.rules.maxHoursDayValue *60*60*1000);

                isProhibidoAnadir = true;

                this.isDelete = true;
            }else if(this.JSON_DATA.rules.maxHoursWeek == true && this.tiempoTrabajoSemana >= (this.JSON_DATA.rules.maxHoursWeekValue *60*60*1000)){
                //no se permiten más horas semana
                document.getElementById("btnGuardar").disabled = true;

                isProhibidoAnadir = true;


                this.isDelete = true;
            }
            return !isProhibidoAnadir; // false no se permite

        },
        //@see devuelve si esta permitido el fichaje, quitando del limite los festivos y fines de semana, en fin ...
        limitePasadoDias : function(diasPasado, hoy, dStart){
            if(this.JSON_DATA.rules.savePastDays == false) return false;
            let dPast = new Date(HOY.getTime());
            dPast.setHours(0);
            dPast.setMinutes(0);
            dPast.setSeconds(0);
            let cuantos = 0;
            while(cuantos < diasPasado){
                dPast.setTime(dPast.getTime() - 60*60*24*1000);
                if(dPast.getDay() != 6  &&   dPast.getDay() != 0 && !this.isFestivo(dPast)){
                    cuantos++;
                }
            }
            dPast.setTime( dPast.getTime());
            if(dStart.getTime() >= dPast.getTime()) return true;
            return false;
        },
        getSigns : function(objSemana){
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if(this.readyState == 4 && this.status == 200){
                    calendarioPicker.JSON_DATA["signs"] = JSON.parse(this.responseText).sort(function(a, b){
                        return a.startDate - b.startDate;
                    });
                    calendarioPicker.marcaDia();
                }
            };
            var url = this.getURLAjax(this.urlSigns);
            url = url.replace("#DATE_START#",this.formatDataLang (objSemana.inicio,"-","DateFormat")).replace("#DATE_END#",this.formatDataLang (objSemana.fin,"-","DateFormat"));
            xhr.open("GET", url, true);
            xhr.send(null);
        },
        isFestivo : function(dateSeek){
            var is = false, d = null, arr = null;
            for( x in this.JSON_DATA.holidays){
                if(this.JSON_DATA.holidays[x].active){
                    if(this.JSON_DATA.holidays[x].day == dateSeek.getDate() && this.JSON_DATA.holidays[x].month == (dateSeek.getMonth()+1)){
                        is = true;
                        break;
                    }
                }
            }
            return is;
        },
        canSign : function(dateSeek){
            var is = false, d = null, arr = null;
            for( x in this.JSON_DATA.holidays){
                if(this.JSON_DATA.holidays[x].active){
                    if(this.JSON_DATA.holidays[x].day == dateSeek.getDate() && this.JSON_DATA.holidays[x].month == (dateSeek.getMonth()+1)){
                        if(this.JSON_DATA.holidays[x].allowSign == true) {
                            return true;
                        }
                    }
                }
            }
            return is;
        },
        marcaDia : function(){
            var cal = document.getElementsByClassName("current")[0];
            var tr = null, td = null;
            var dSeek = new Date(), dS = new Date();
            var punto = "<span class=\"marcaCalendario\">&nbsp;</span>";
            dSeek.setMonth(CALENDARIO_PICKER.MES);
            dSeek.setFullYear(CALENDARIO_PICKER.ANO);
            dSeek.setHours(0);
            dSeek.setMinutes(0);
            dSeek.setSeconds(0);
            cal = cal.childNodes[0]; //tbody
            for(var x = 0; x < cal.childNodes.length; x++){
                tr = cal.childNodes[x];
                for( var y = 0; y < tr.childNodes.length; y++){
                    td = tr.childNodes[y];
                    if(td.innerHTML != ""){
                        td.innerHTML = td.innerHTML.replace(punto,"");
                        dSeek.setDate(Number(td.innerHTML.trim()));
                        for(var c = 0; c < this.JSON_DATA.signs.length; c++){ //buscamos si tiene fichaje ese dia
                            dS.setTime(Number(this.JSON_DATA.signs[c].startDate));
                            dS = this.dateGMTtoUTC(dS);
                            if(  dS.getTime() >= dSeek.getTime()  && dS.getTime() <= (dSeek.getTime() + ((60*60*24*1000) -(60*1000) ))){
                                td.innerHTML += punto;
                                break;
                            }
                        }//end signs

                        if(this.isFestivo(dSeek)){
                            //td.style.backgroundColor = "#52BE80";
                            //console.log("dSeek: " + dSeek.toDateString())
                            td.className = "marcaFestivo";
                        }

                    }//end if innerHTML

                }//end td
            }//end tr
        },
        //@see ajustar de GMT+X a UTC (LIFERAY)
        dateGMTtoUTC : function(dateTime){
            if(!dateTime) dateTime = new Date();
            dateTime.setTime(dateTime.getTime() + dateTime.getTimezoneOffset()*60*1000);
            return dateTime;
        },
        getSignById : function(idSign){
            let objSign = null;
            for(var x = 0; x < this.JSON_DATA.signs.length; x++){
                if(this.JSON_DATA.signs[x].signId == idSign){
                    objSign = this.JSON_DATA.signs[x];
                }
            }
            return objSign;
        },
        getActividad : function (idTipo){
            var obj = null;
            //for(x in this.JSON_DATA.activities){
            for(var x = 0; x < this.JSON_DATA.activities.length; x++){
                if(this.JSON_DATA.activities[x].activityId == idTipo){
                    obj = this.JSON_DATA.activities[x];
                    break;
                }
            }
            if(obj == null){
                obj = {
                    color : "RED",
                    active: true,
                    activityId: "-1",
                    color: "#00FFFF",
                    name: (LOCALIZE[this.idioma]).mensajes.actividadDesconocida,
                    type: 1,
                    workTime: false
                }
            }
            return obj;
        },
        getDaysInMonth : function(month,year){
            return  (new Date(year, Number(month) + 1, 0)).getDate();
        },
        createDetallesDia : function(d, dS,dE){
            this.disabledBotones(d);

            document.getElementById("capaDetalles").innerHTML = '<table id="tablaDetalles" class="table table-responsive table-striped border" style="width:99%;">' +
                '<thead><tr>' +
                '<th style="width:2%;">&nbsp;</th>' +
                //'<th style="width:2%;"><image src="images/papelera.jpg"/></th>' +
                '<th style="width:10%;">' + (LOCALIZE[this.idioma]).mensajes.entrada + '</th>' +
                '<th style="width:10%;">' + (LOCALIZE[this.idioma]).mensajes.salida + '</th>' +
                '<th style="width:10%;">' + (LOCALIZE[this.idioma]).mensajes.tiempo + '</th>' +
                '<th style="width:16%;">' + (LOCALIZE[this.idioma]).mensajes.tipo + '</th>' +
                '<th style="width:10%;">' + (LOCALIZE[this.idioma]).mensajes.observaciones + '</th>' +
                '<th style="width:10%;">' + (LOCALIZE[this.idioma]).mensajes.observacionesEmpresa + '</th>' +
                '</tr></thead><tbody></table>';

            var tabla = null, tr = null, td = null, text = null;
            //var divRow = null, col =  null;
            var calHoras = 0, actividad = null;
            var horas = 0, minutos = 0;
            this.tiempoTrabajadoDia = 0;
            this.arrFichajes = [];
            var dLog = this.dateGMTtoUTC(null);
            var dLogEnd = new Date();
            var isWorkTime = false;
            var creationDate = new Date();


            tabla = document.getElementById("tablaDetalles");

            for(x in this.JSON_DATA.signs){
                isWorkTime = (this.getActividad(this.JSON_DATA.signs[x].activityId)).workTime;
                dLog.setTime(Number(this.JSON_DATA.signs[x].startDate));
                dLog = this.dateGMTtoUTC(dLog);
                if(this.JSON_DATA.signs[x].finishDate != ""){
                    dLogEnd.setTime(Number(this.JSON_DATA.signs[x].finishDate));
                    dLogEnd = this.dateGMTtoUTC(dLogEnd);
                }


                //dia seleccionado
                //if(Number(this.JSON_DATA.signs[x].startDate) >= d.getTime()  &&  Number(this.JSON_DATA.signs[x].startDate)  <= (d.getTime() + 60*60*24*1000) ){
                if(dLog.getTime() >= d.getTime()  &&  dLog.getTime() <= (d.getTime() + (60*60*24*1000) - (60*1000)) ){
                    this.arrFichajes.push(this.JSON_DATA.signs[x]);

                    if(isWorkTime && this.JSON_DATA.signs[x].finishDate != null){
                        this.tiempoTrabajadoDia +=  dLogEnd.getTime() - dLog.getTime();
                        if(dLogEnd.getHours() == 23 && dLogEnd.getMinutes() == "59")  this.tiempoTrabajadoDia += 1000;
                    }
                }
                //semana
                //if(Number(this.JSON_DATA.signs[x].startDate) >= dS.getTime()  &&  Number(this.JSON_DATA.signs[x].startDate)  <= dE.getTime() ){
                if(dLog.getTime() >= dS.getTime()  &&  dLog.getTime()  <= dE.getTime() ){
                    if(isWorkTime && this.JSON_DATA.signs[x].finishDate != null){
                        calHoras += Number(this.JSON_DATA.signs[x].finishDate) - Number(this.JSON_DATA.signs[x].startDate);
                        if(dLogEnd.getHours() == 23 && dLogEnd.getMinutes() == "59")  calHoras += 1000;
                    }
                }
            }
            //Total día
            horas = parseInt(this.tiempoTrabajadoDia/ (60*60*1000))
            minutos = (this.tiempoTrabajadoDia/(60*1000))%(60)
            document.getElementById("spHorasDia").innerHTML = horas + " h " + minutos + " " +(LOCALIZE[this.idioma]).mensajes.minDia;
            //Total semana:
            this.tiempoTrabajoSemana = calHoras;
            horas = parseInt(calHoras/ (60*60*1000))
            minutos = (calHoras/(60*1000))%(60)
            document.getElementById("spHorasSemana").innerHTML =  horas + " h " + minutos + " " +(LOCALIZE[this.idioma]).mensajes.minSemana;

            this.arrFichajes.sort(function(a, b){
                return a.startDate - b.startDate;
            });

            for(var i = 0; i < this.arrFichajes.length; i++){
                tr = document.createElement("tr");
                tr.onclick = function(){calendarioPicker.editFichaje(this.id);}
                tr.className = " filaDetalleFichaje";
                tr.id = this.arrFichajes[i].signId;


                td = document.createElement("td"); //check
                //td.innerHTML = "<input type=\"checkbox\" class=\"inpChkFichaje\"  id=\"" + this.arrFichajes[i].signId + "\"></input>&nbsp;";
                td.innerHTML = "&nbsp;";
                creationDate.setTime(this.arrFichajes[i].createDate);
                if(this.JSON_DATA.rules.editSigns && (Date.now() <= (creationDate.getTime() + this.JSON_DATA.rules.editSignsValue*60*1000) )){ //creación cumple los limites de tiempo
                    //td.innerHTML = "<image src=\"images/papelera.jpg\" onclick=\"calendarioPicker.deleteData(" + this.arrFichajes[i].signId + ")\" />";
                    td.innerHTML = "<span class=\"fa fa-trash fa-2x\" id=\"btnEliminar\" onclick=\"calendarioPicker.deleteData(" + this.arrFichajes[i].signId + ")\" />";
                }
                tr.appendChild(td);

                td = document.createElement("td"); //Entrada
                d.setTime(Number(this.arrFichajes[i].startDate));
                d = this.dateGMTtoUTC(d);
                //td.innerHTML =  this.formatDataLang(d,"/") + " - " + ((d.getHours() < 10)? "0" + d.getHours(): d.getHours()) + ":" + ((d.getMinutes() < 10)? "0" + d.getMinutes(): d.getMinutes())
                td.innerHTML = ((d.getHours() < 10)? "0" + d.getHours(): d.getHours()) + ":" + ((d.getMinutes() < 10)? "0" + d.getMinutes(): d.getMinutes())
                tr.appendChild(td);

                td = document.createElement("td");//Salida
                if (this.arrFichajes[i].finishDate != null){
                    dE.setTime(Number(this.arrFichajes[i].finishDate));
                    dE = this.dateGMTtoUTC(dE);
                    if(dE.getHours() == 23 && dE.getMinutes() == 59) dE.setTime(dE.getTime() + 1000);
                    td.innerHTML = ((dE.getHours() < 10)? "0" + dE.getHours(): dE.getHours()) + ":" + ((dE.getMinutes() < 10)? "0" + dE.getMinutes(): dE.getMinutes());
                }else{
                    td.innerHTML = (LOCALIZE[this.idioma]).mensajes.sinSalida;
                }
                tr.appendChild(td);


                td = document.createElement("td");//Tiempo
                if (this.arrFichajes[i].finishDate != null){
                    calHoras = 0;
                    if(dE.getHours() == 23 && dE.getMinutes() == 59) calHoras += 1000;
                    calHoras += ((dE.getTime() - d.getTime()));
                    horas = parseInt(calHoras/ (60*60*1000))
                    minutos = (calHoras/(60*1000))%(60)
                    td.innerHTML =  horas + " h " + minutos + " min";
                }else{
                    td.innerHTML =  (LOCALIZE[this.idioma]).mensajes.abierto;
                }
                tr.appendChild(td);

                actividad = this.getActividad(this.arrFichajes[i].activityId);
                td = document.createElement("td");//Tipo
                td.innerHTML = "<span class=\"miniCuadro\" style=\"background-color:" + actividad.color +  ";\"></span>&nbsp;";
                td.innerHTML += "<span class='act-id-"+ actividad.activityId+"'>"+ actividad.name+"</span>";
                tr.appendChild(td);

                td = document.createElement("td");//Observaciones
                td.innerHTML = decodeURIComponent(this.arrFichajes[i].observations.replace(/%(?![0-9][0-9a-fA-F]+)/g, '%25'));
                tr.appendChild(td);

                td = document.createElement("td");//Observaciones mod
                td.innerHTML = decodeURIComponent(this.arrFichajes[i].observationsAdmin.replace(/%(?![0-9][0-9a-fA-F]+)/g, '%25'));
                tr.appendChild(td);

                tabla.appendChild(tr);
            }
            //ultimo fichaje
            if(this.arrFichajes.length > 0) document.getElementById("timeStart").value = ((dE.getHours() <10)? "0" + dE.getHours(): dE.getHours()) + ":" + ((dE.getMinutes() < 10)? "0" + dE.getMinutes() : dE.getMinutes());
            this.disabledBotones(d);
            //Mostrar el idioma del usuario.
            $("#tablaDetalles name[language-id='"+this.idioma+"']" ).css("display", "block");
        },
        editFichaje : function(id){
            let objSign = this.getSignById(id);
            if(objSign == null) return;
            this.cleanFormulario();

            this.idFichajeEdit = id;
            let dStart = new Date(), dEnd = new Date();
            dStart.setTime(objSign.startDate);

            //solo se puede editar los comentarios en los cerrados y todo en los sin cerrar ambos del mismo dia en curso
            //if(dStart.getDate() != HOY.getDate() || dStart.getMonth() != HOY.getMonth() || dStart.getFullYear() != HOY.getFullYear()) return false;

            //Permitir editar comentarios en fichajes hasta la fecha de permitir añadir.
            //if(!this.limitePasadoDias(this.JSON_DATA.rules.savePastDaysValue,HOY,dStart)) return false;

            document.getElementById("lblMensaje").innerHTML = (LOCALIZE[this.idioma]).mensajes.editandoFichaje;
            var creationDate = new Date();
            creationDate.setTime(objSign.createDate);


            var timeMax = new Date();
            timeMax.setTime(creationDate.getTime() + this.JSON_DATA.rules.editDeleteSignsValue *60 * 1000);
            //console.log("objSign.createDate: " + creationDate)
            //console.log("timeMax: " + timeMax)

            //limite de tiempo en edición ... por minutos
            if(objSign.finishDate != null && !this.JSON_DATA.rules.editDeleteSigns){
                //document.getElementById("lblMensaje").innerHTML =  "NO ESTA PERMITIDO EDITAR O BORRAR REGISTROS "; //(LOCALIZE[this.idioma]).mensajes.cerrado;
                document.getElementById("lblMensaje").innerHTML =  (LOCALIZE[this.idioma]).mensajes.editDelnotPermitido;
                document.getElementById("btnGuardar").disabled = true;

            }else if( objSign.finishDate != null && this.JSON_DATA.rules.editDeleteSigns && (new Date()).getTime() > timeMax.getTime()){ //esta cerrado
                document.getElementById("lblMensaje").innerHTML =  (LOCALIZE[this.idioma]).mensajes.tiempoEditSuperado;
                document.getElementById("btnGuardar").disabled = true;

            }else if(this.JSON_DATA.rules.editSigns && (Date.now() <= (creationDate.getTime() + parseInt(this.JSON_DATA.rules.editSignsValue*1000*60)) )){ //creación cumple los limites de tiempo
                document.getElementById("cmbActividad").disabled = false;
                document.getElementById("timeStart").disabled = false;
                document.getElementById("timeEnd").disabled = false;
                document.getElementById("inpObservaciones").disabled = false;
            }else{
                document.getElementById("cmbActividad").disabled = true;
                document.getElementById("timeStart").disabled = true;
                if(objSign.finishDate != null){
                    this.isFichajeClose = true;
                    document.getElementById("timeEnd").disabled = true;
                    document.getElementById("inpObservaciones").disabled = false;
                    document.getElementById("lblMensaje").innerHTML += (LOCALIZE[this.idioma]).mensajes.cerrado;
                }else{
                    document.getElementById("timeEnd").disabled = false;
                    document.getElementById("inpObservaciones").disabled = false;
                    document.getElementById("lblMensaje").innerHTML += (LOCALIZE[this.idioma]).mensajes.abierto;
                }
            }


            this.isFichajeClose = false;

            document.getElementById("btnGuardar").disabled = false; // Se permite siempre editar las observaciones

            /*this.idFichajeEdit = id;
            let dStart = new Date(), dEnd = new Date();
            dStart.setTime(objSign.startDate);*/

            //solo se puede editar los comentarios en los cerrados y todo en los sin cerrar ambos del mismo dia en curso
            //if(dStart.getDate() != HOY.getDate() || dStart.getMonth() != HOY.getMonth() || dStart.getFullYear() != HOY.getFullYear()) return false;

            dStart = this.dateGMTtoUTC(dStart);
            document.getElementById("cmbActividad").value = objSign.activityId;
            document.getElementById("timeStart").value = ((dStart.getHours() < 10)? "0" + dStart.getHours() : dStart.getHours()) + ":" + ((dStart.getMinutes() < 10)? "0" + dStart.getMinutes(): dStart.getMinutes());
            if(objSign.finishDate != null){
                dEnd.setTime(objSign.finishDate);
                dEnd = this.dateGMTtoUTC(dEnd);
                document.getElementById("timeEnd").value = ((dEnd.getHours() < 10)? "0" + dEnd.getHours() : dEnd.getHours()) + ":" + ((dEnd.getMinutes() < 10)? "0" + dEnd.getMinutes(): dEnd.getMinutes());
            }
            document.getElementById("inpObservaciones").value = decodeURIComponent(objSign.observations.replace(/%(?![0-9][0-9a-fA-F]+)/g, '%25'));
        },
        getPrimerDiaSemana : function(date){
            var isDiaSemana = -1;
            date.setHours(0,0,0,0);
            var dS = new Date(), dE = new Date();
            dS.setTime(date.getTime());
            dE.setTime(date.getTime());
            while(dS.getDay() != 1){ //lunes
                dS.setTime(dS.getTime() - (60*60*24*1000));
            }
            while(dE.getDay() != 0){ //domingo
                dE.setTime(dE.getTime() + (60*60*24*1000));
            }
            return {
                diaClick : date,
                inicio : dS,
                fin : dE
            }
        },
        formatDataLang : function(d,separator,lang){
            try{
                var str = "";
                if(separator == null) separator = "/";
                dd = (d.getDate() < 10)? "0" + d.getDate() :  d.getDate();
                mm = (d.getMonth()+1  <10)? "0" + (d.getMonth() +1) :  d.getMonth() +1;
                if(lang == null || lang == "es"){
                    str = dd + separator + mm + separator + d.getFullYear();
                }else if(lang == "en"){
                    str = mm + separator + dd + separator + d.getFullYear();
                }else if(lang == "en_EN"){
                    str =  d.getFullYear()+ separator +  mm + separator + dd;
                }else if(lang == "DateFormat"){
                    str = d.getFullYear() + separator +  mm + separator + dd;
                }
                return str;
            }catch(e){
                //console.error("calendarioBarras.formatDataLang(): " + e.toString())
            }

        },

        someOpenFichaje : function(){
            let isOpen = false;
            //console.log(HOY_MIN.getTime())
            if(this.idFichajeEdit != null) return isOpen;
            for(x in this.JSON_DATA.signs){
                //solo si el fichaje es del dia actual
                if(this.JSON_DATA.signs[x].finishDate == null && this.JSON_DATA.signs[x].startDate >= HOY_MIN.getTime() && this.JSON_DATA.signs[x].startDate <= HOY.getTime()){
                    isOpen = true;
                    break;
                }
            }
            return isOpen;
        },

        validate : function(){

            document.getElementById("lblMensaje").innerHTML = ""
            var elem = document.getElementsByClassName("filaDetalleFichaje");
            Array.prototype.forEach.call(elem, function(node){ node.style.backgroundColor = ""; });
            var inicioTime = document.getElementById("timeStart").value;
            var finTime = null;

            if(document.getElementById("timeEnd").value != ""){
                finTime = (document.getElementById("timeEnd").value == "00:00")? "23:59:59" : (document.getElementById("timeEnd").value + ":00");
            }



            //superada la fecha max de edición ... solo permite obs
            if(this.idFichajeEdit != null){
                let objSign = this.getSignById(this.idFichajeEdit);
                if(objSign == null)  return;
                let fechaIni = new Date();
                fechaIni.setTime(objSign.startDate);
                fechaIni = this.dateGMTtoUTC(fechaIni);



                var creationDate = new Date();
                creationDate.setTime(objSign.createDate);
                var timeMax = new Date();
                timeMax.setTime(creationDate.getTime() + this.JSON_DATA.rules.editDeleteSignsValue *60 * 1000);

                if(!this.limitePasadoDias(this.JSON_DATA.rules.savePastDaysValue,HOY,fechaIni)
                    || (objSign.finishDate != null && this.JSON_DATA.rules.editDeleteSigns && (new Date()).getTime() > timeMax.getTime())){ //esta en edición .. permite mod observaciones
                    let fechaFin = new Date();
                    fechaFin.setTime(objSign.finishDate);
                    fechaFin = this.dateGMTtoUTC(fechaFin);
                    this.saveData(objSign.activityId, fechaIni,fechaFin );
                    return;
                }
            }

            var idActividad = document.getElementById("cmbActividad").value;
            /* Tiene algún fichaje sin cerrar*/
            if(this.someOpenFichaje()){
                this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.fichajesSinCerrar;
            }else if(this.fechaSeleccionada == null || this.fechaSeleccionada == ""){
                this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.faltaFecha;
            }else if(document.getElementById("cmbActividad").value == ""){
                this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.faltaActividad;
                //Ahora se pueden crear fichajes sin hora de fin.
            }else if(inicioTime == "" /*|| finTime == ""*/){
                this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.faltaHoras;
            }else{

                var arrDate = this.fechaSeleccionada.split(".");
                var arrTime = inicioTime.split(":");
                var dS = new Date(arrDate[2], arrDate[1]-1, arrDate[0], arrTime[0], arrTime[1]);
                var dE = null;


                //console.log("COMPROBANDO DISABLED  !!  de " + dS + " :::: " + this.disabledBotones(dS) );
                if(!this.disabledBotones(dS)) {
                    console.log("NO PERMITIDO !!");
                    return;
                }


                //solo se puede editar los comentarios en los cerrados y todo en los sin cerrar ambos del mismo dia en curso
                if(finTime == null && (dS.getDate() != HOY.getDate() || dS.getMonth() != HOY.getMonth() || dS.getFullYear() != HOY.getFullYear())){
                    this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.soloDiaCurso;
                }else{
                    if(finTime != null){
                        arrTime = finTime.split(":");
                        var dE = new Date(arrDate[2], arrDate[1]-1, arrDate[0], arrTime[0], arrTime[1], arrTime[2]);
                    }
                    if(this._validate(idActividad,dS,dE)) this.saveData(idActividad,dS,dE);
                }

            }
            document.getElementById("lblMensaje").innerHTML = this.MENSAJE;
        },

        updateSign : function(objSign){
            for(let i = 0; i < this.JSON_DATA.signs.length; i++){
                if(this.JSON_DATA.signs[i].signId == objSign.signId){
                    this.JSON_DATA.signs[i].activityId = objSign.activityId;
                    //if(!this.isFichajeClose){
                    this.JSON_DATA.signs[i].finishDate = objSign.finishDate;
                    //}
                    this.JSON_DATA.signs[i].observations = objSign.observations;
                    //this.JSON_DATA.signs[i].observationsAdmin = objSign.observationsAdmin;
                    this.JSON_DATA.signs[i].startDate = objSign.startDate;
                    //this.JSON_DATA.signs[i].userId = objSign.userId;
                    break;
                }
            }

        },
        //@see mandar datos al servidor
        saveData : function(idActivity,dS,dE){
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (this.readyState == 4 && this.status == 200){
                    if(calendarioPicker.idFichajeEdit != null){
                        //REAL
                        var item = JSON.parse(this.responseText);
                        //PRUEBAS
                        //var item = calendarioPicker.objFichaje;
                        //REAL
                        item.signId = calendarioPicker.idFichajeEdit;
                        calendarioPicker.updateSign(item);
                    }else{
                        //REAL añadir item :
                        calendarioPicker.JSON_DATA.signs.push(JSON.parse(this.responseText));
                        //BORRAR !!! PRUEBAS añadir:
                        //calendarioPicker.JSON_DATA.signs.push(calendarioPicker.objFichaje);
                    }
                    var objSemana = calendarioPicker.getPrimerDiaSemana(calendarioPicker.diaClick);
                    calendarioPicker.createDetallesDia(objSemana.diaClick,objSemana.inicio,objSemana.fin);
                    calendarioPicker.cleanFormulario();
                    calendarioPicker.marcaDia();
                    calendarioPicker.clickCalendario(calendarioPicker.evtTarget);
                }
            };
            var urlAdd = null;
            if(this.idFichajeEdit != null) {
                urlAdd = this.getURLAjax(URL_UPDATE);
            }else{
                urlAdd = this.getURLAjax(URL_ADD);
            }

            /*console.log("dS: " + dS);
            console.log("dE: " + dE);*/

            var inicio = this.formatDataLang(dS,"-","en_EN") +  " " + ((dS.getHours() < 10)? "0" + dS.getHours() : dS.getHours()) + ":" + ((dS.getMinutes() < 10)? "0" + dS.getMinutes() : dS.getMinutes())  + ":00.000000";
            var fin = "";
            if(dE != null){
                fin = this.formatDataLang(dE,"-","en_EN") +  " " + ((dE.getHours() < 10)? "0" + dE.getHours() : dE.getHours()) + ":" + ((dE.getMinutes() < 10)? "0" + dE.getMinutes() : dE.getMinutes())  + ":00.000000";;
            }else{
                urlAdd = urlAdd.replace("finish-date/#END_DATE#/","-finish-date/");
            }

            /*console.log("inicio: " + inicio);
            console.log("fin: " + fin);*/

            if(document.getElementById("inpObservaciones").value == ""){
                urlAdd = urlAdd.replace("#START_DATE#",inicio).replace("#END_DATE#", fin).replace("#ID_ACTIVIDAD#",idActivity).replace("/observations/#OBSERVACIONES#","/-observations").replace("#ID_SIGN#",this.idFichajeEdit);
            }else{
                var txtCode = encodeURIComponent(document.getElementById("inpObservaciones").value);
                urlAdd = urlAdd.replace("#START_DATE#",inicio).replace("#END_DATE#", fin).replace("#ID_ACTIVIDAD#",idActivity).replace("#OBSERVACIONES#",txtCode).replace("#ID_SIGN#",this.idFichajeEdit);
                console.log("urlAdd: " + urlAdd);
            }

            xhr.open("POST", urlAdd, true);
            xhr.send(null);
        },
        //@see eliminar registros
        deleteData : function(idSign){
            let objSign = this.getSignById(idSign);
            if(objSign == null) return;

            var creationDate = new Date();
            creationDate.setTime(objSign.createDate);
            var timeMax = new Date();
            timeMax.setTime(creationDate.getTime() + this.JSON_DATA.rules.editDeleteSignsValue *60 * 1000);
            console.log("objSign.createDate: " + creationDate)
            console.log("timeMax: " + timeMax)
            if(objSign.finishDate != null && !this.JSON_DATA.rules.editDeleteSigns){
                //document.getElementById("lblMensaje").innerHTML =  "NO ESTA PERMITIDO EDITAR O BORRAR REGISTROS "; //(LOCALIZE[this.idioma]).mensajes.cerrado;
                document.getElementById("lblMensaje").innerHTML = (LOCALIZE[this.idioma]).mensajes.editDelnotPermitido;
                return;
            }else if( objSign.finishDate != null && this.JSON_DATA.rules.editDeleteSigns && (new Date()).getTime() > timeMax.getTime()){ //esta cerrado
                document.getElementById("lblMensaje").innerHTML = (LOCALIZE[this.idioma]).mensajes.tiempoDelSuperado;
                event.stopPropagation();
                return;
            }

            if(this.JSON_DATA.rules.editSigns){
                if(!confirm((LOCALIZE[this.idioma]).mensajes.eliminarRegistro)) return;
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function(){
                    if (this.readyState == 4 && this.status == 200){
                        //quitar item
                        for(x in calendarioPicker.JSON_DATA.signs){
                            if(calendarioPicker.JSON_DATA.signs[x].signId == idSign){
                                calendarioPicker.JSON_DATA.signs.splice(x,1);
                                calendarioPicker.marcaDia();
                                break;
                            }
                        }
                        var objSemana = calendarioPicker.getPrimerDiaSemana(calendarioPicker.diaClick);
                        calendarioPicker.createDetallesDia(objSemana.diaClick, objSemana.inicio, objSemana.fin);
                        calendarioPicker.cleanFormulario();
                        //document.getElementById("timeStart").value = null;
                    }
                }
                var urlDel = URL_DOMAIN  + URL_DEL.replace("#ID_SIGN#",idSign).replace("#P_AUTH#",this.pAuth);
                xhr.open("GET", urlDel, true);
                xhr.send(null);
            }
        },
        cleanFormulario : function(mode){
            document.getElementById("btnGuardar").disabled = false;
            document.getElementById("cmbActividad").disabled = false;
            document.getElementById("timeStart").disabled = false;
            document.getElementById("timeEnd").disabled = false;
            document.getElementById("inpObservaciones").disabled = false;

            console.log("LIMPIAR FORM")
            
            //this.fechaSeleccionada = null; // Eliminar la fecha seleccionada
            document.getElementById("spFecha").innerHTML = "";
            if(mode == null){
                document.getElementById("timeStart").value = document.getElementById("timeEnd").value;
            }else{
                document.getElementById("timeStart").value = "";
            }

            this.idFichajeEdit = null;
            this.isFichajeClose = false;

            document.getElementById("lblMensaje").innerHTML = "";
            document.getElementById("timeEnd").value = "";
            document.getElementById("cmbActividad").value = "";
            document.getElementById("inpObservaciones").value = "";
        },
        _validate : function(idActividad,dateStart, dateEnd){
            this.MENSAJE = "";
            var res = null;
            //if(this.JSON_DATA.rules.savePastDays == false) alert()

            if(this.JSON_DATA.rules.savePastDays == false  && HOY.toLocaleDateString() != dateStart.toLocaleDateString()){
                this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.savePastDays;

                /*}else if( this.JSON_DATA.rules.savePastDays == true
                    && HOY.getTime() - (Number(this.JSON_DATA.rules.savePastDaysValue)*60*60*24*1000) >= dateStart.getTime() ){
                    this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.savePastDaysValue.replace("#DIAS#",this.JSON_DATA.rules.savePastDaysValue);*/
            }else if(dateEnd != null && dateEnd.getTime() <= dateStart.getTime()){
                this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.finAntesInicio;
            }else if(dateEnd != null && dateEnd.getTime() == dateStart.getTime()){
                this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.horasIguales;
            }else if(dateStart.getTime() > HOY.getTime()){
                this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.fichajeFuturo;
            }else if((res = this.maximoHoras(idActividad,dateStart,dateEnd)).isMaximo ){
                this.MENSAJE = (LOCALIZE[this.idioma]).mensajes["maximo" +  res.tipo] + " " + res.mensaje;
            }else if( this.solapamiento(dateStart,dateEnd)){ //solapamientos
                this.MENSAJE = (LOCALIZE[this.idioma]).mensajes.solapamiento;
            }

            if(this.MENSAJE != "") return false;
            return true;
        },
        maximoHoras : function(idActividad,dS,dE){
            if(dE == null || this.idFichajeEdit != null) return false;
            var res = { isMaximo: false, tipo: null, mensaje: null};
            var tiempo = dE.getTime() - dS.getTime();
            if(!(this.getActividad(idActividad)).workTime) return res; //no es computable en la jornada
            if(this.JSON_DATA.rules.maxHoursDay){
                if( ((this.tiempoTrabajadoDia + tiempo)/(60*60*1000)) > this.JSON_DATA.rules.maxHoursDayValue){
                    res.isMaximo = true;
                    res.tipo = "Dia";
                    res.mensaje = " (" +this.JSON_DATA.rules.maxHoursDayValue + " h)";
                }
            }
            if(this.JSON_DATA.rules.maxHoursWeek){
                if( ((this.tiempoTrabajoSemana + tiempo)/(60*60*1000)) > this.JSON_DATA.rules.maxHoursWeekValue){
                    res.isMaximo = true;
                    res.tipo = "Semana";
                    res.mensaje = " (" +this.JSON_DATA.rules.maxHoursWeekValue + " h)";
                }
            }
            return res;
        },
        solapamiento : function(dS,dE){
            if(dE == null){
                dE = new Date();
                dE.setTime(dS.getTime() + 60*1000);

            }
            var dS_sign = new Date();
            var dE_sign = new Date();
            var isSolapa = false;
            var x = 0;
            for(x = 0; x < this.arrFichajes.length;x++){
                dS_sign.setTime(this.arrFichajes[x].startDate);
                dE_sign.setTime(this.arrFichajes[x].finishDate);
                dS_sign =  this.dateGMTtoUTC(dS_sign);
                dE_sign =  this.dateGMTtoUTC(dE_sign);
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
            if(isSolapa ){
                if(this.idFichajeEdit != null && this.arrFichajes[x].signId == this.idFichajeEdit){
                    isSolapa = false;
                }else{
                    document.getElementById(this.arrFichajes[x].signId).style.backgroundColor = "#F8C471";
                }

            }
            return isSolapa;
        }
    }
})();
