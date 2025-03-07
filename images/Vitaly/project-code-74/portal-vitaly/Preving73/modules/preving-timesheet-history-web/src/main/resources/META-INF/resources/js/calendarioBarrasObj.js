//Mensajes
var LOCALIZE = {
    es: {
        mensajes: {
            totalHoras : "Total horas",
            entrada:"Entrada",
            salida:"Salida",
            tiempo:"Tiempo",
            tipo: "Tipo",
            observaciones:"Observaciones",
            observacionesEmpresa:"Observaciones empresa",
            sinCerrar: "-Sin cerrar-",
            abierto: "-Abierto-"
        }
    },es_ES: {
        mensajes: {
            totalHoras : "Total horas",
            entrada:"Entrada",
            salida:"Salida",
            tiempo:"Tiempo",
            tipo: "Tipo",
            observaciones:"Observaciones",
            observacionesEmpresa:"Observaciones empresa",
            sinCerrar: "-Sin cerrar-",
            abierto: "-Abierto-"
        }
    },
    ca_ES: {
        mensajes: {
            totalHoras : "Total hores",
            entrada:"Entrada",
            salida:"Sortida",
            tiempo:"Temps",
            tipo:"Tipo",
            observaciones:"Observacions",
            observacionesEmpresa:"Observacions empresa",
            sinSalida: "-Sense sortida-",
            abierto: "-Obert-"
        }
    }
};

var CalendarioBarras = (function(){
    version : 2.2;
    return{
        JSON_DATA : null,
        idioma : "es_ES",
        CONSTANTES : {
            HOY : new Date(),
            ID_USUARIO:  -1,
            MES_ACTUAL : null,
            ANO_ACTUAL :  null,
            TOTAL_HORAS : 0,
            TOTALES_HORAS_DIA :{}
        },
        drawCalendario : function(jsonData,id_usuario){
            try{
                this.CONSTANTES.TOTAL_HORAS = 0;
                this.CONSTANTES.TOTAL_HORAS_DIA = {};
                var dStart = null, dEnd = null;
                var horas = 0, minutos = 0;
                for(x in jsonData.fichajes){
                    if(jsonData.fichajes[x].finishDate != null){
                        dStart = new Date();
                        dStart.setTime(jsonData.fichajes[x].startDate);
                        dEnd = new Date();
                        dEnd.setTime(jsonData.fichajes[x].finishDate);
                        this.printBarra(jsonData.fichajes[x].signId, dStart, dEnd, jsonData.fichajes[x].activityId);
                    }
                }
                this.CONSTANTES.TOTAL_HORAS = 0;
                for(c in this.CONSTANTES.TOTALES_HORAS_DIA){
                    this.CONSTANTES.TOTAL_HORAS  += this.CONSTANTES.TOTALES_HORAS_DIA[c];
                    this.CONSTANTES.TOTALES_HORAS_DIA[c] = null; // para limpiar
                }
                horas = parseInt(this.CONSTANTES.TOTAL_HORAS/60);
                minutos = this.CONSTANTES.TOTAL_HORAS%60;
                document.getElementById("spanTotalHoras").innerHTML = ((horas < 10)? "0" + horas : horas) + "h " + ((minutos < 10)? "0" + minutos : minutos ) + " min";
                //document.getElementById("contenedorCalendario").style.height =  (document.getElementsByClassName("row-calendario").length* 30) + "px";
                //unir capas a clicks
                document.querySelectorAll(".clsFicha").forEach(function(elem){
                    elem.addEventListener('click', function (event){
                        //alert(event.target.id);
                        CalendarioBarras.clickFichaje(event.target);
                    });
                });

                //Mostrar el idioma del usuario.
                $("#capaLeyenda name[language-id='"+this.idioma+"']" ).css("display", "block");

            }catch(e){
                console.error("calendarioBarras.drawCalendario(): " + e);
            }
        },
        //@see dibujar detalles de ese dia
        createDetallesDia : function(id){
            document.getElementById("capaDetalles").innerHTML = "<div class=\"row\" ><div class=\"col\">"+(LOCALIZE[this.idioma]).mensajes.entrada+"</div><div class=\"col\">"+(LOCALIZE[this.idioma]).mensajes.salida+"</div><div class=\"col-sm-1\">"+(LOCALIZE[this.idioma]).mensajes.tiempo+"</div>"
                + "<div class=\"col\">"+(LOCALIZE[this.idioma]).mensajes.tipo+"</div><div class=\"col-sm-2\">"+(LOCALIZE[this.idioma]).mensajes.observaciones+"</div><div class=\"col\">"+(LOCALIZE[this.idioma]).mensajes.observacionesEmpresa+"</div></div>";
            var divRow = null, col =  null;
            var fila_id = document.getElementById(id).parentNode.parentNode.id;

            Array.prototype.forEach.call(document.getElementsByClassName("row-calendario"), function (node) {
                node.style.backgroundColor = ""

            });
            document.getElementById(id).parentNode.parentNode.style.backgroundColor = "#d6dbdf";
            // document.getElementById(id).parentNode.parentNode.style.backgroundColor = "#d6dbdf";
            if(fila_id == undefined || fila_id == null) return;
            fila_id = (fila_id.split("-"))[2];
            var d = new Date();
            d.setDate(parseInt(fila_id.substring(0,2)));
            d.setMonth(parseInt(fila_id.substring(4,2))-1);
            d.setFullYear(parseInt(fila_id.substring(4)));
            d.setMinutes(0);
            d.setHours(0);
            d.setSeconds(0);
            d.setUTCMilliseconds(0);
            var arrFichajes = [];
            var dLog = this.dateGMTtoUTC(null);
            for(x in this.JSON_DATA.fichajes){
                if(Number(this.JSON_DATA.fichajes[x].startDate) >= d.getTime()  &&  Number(this.JSON_DATA.fichajes[x].startDate)  <= (d.getTime() + 60*60*24*1000) ){
                    dLog.setTime(Number(this.JSON_DATA.fichajes[x].startDate));
                    arrFichajes.push(this.JSON_DATA.fichajes[x]);
                }
            }
            var dE = new Date();
            var calHoras = 0, actividad = null;
            var horas = 0, minutos = 0;
            for(var i = 0; i < arrFichajes.length; i++){
                divRow = document.createElement("div");
                divRow.className = "row";

                col = document.createElement("div"); //Entrada
                col.className = "col";
                d.setTime(Number(arrFichajes[i].startDate))
                d = this.dateGMTtoUTC(d);
                col.innerHTML =  this.formatDataLang(d,"/") + " - " + ((d.getHours() < 10)? "0" + d.getHours(): d.getHours()) + ":" + ((d.getMinutes() < 10)? "0" + d.getMinutes(): d.getMinutes())

                divRow.appendChild(col);
                col = document.createElement("div");//Salida
                col.className = "col";
                if(arrFichajes[i].finishDate != null){
                    dE.setTime(Number(arrFichajes[i].finishDate));
                    dE = this.dateGMTtoUTC(dE);
                    col.innerHTML =  this.formatDataLang(dE,"/") + " - ";
                    if(dE.getHours() == 23 && dE.getMinutes() == 59){
                        col.innerHTML += "00:00"
                    } else{
                        col.innerHTML += ((dE.getHours() < 10)? "0" + dE.getHours(): dE.getHours()) + ":" + ((dE.getMinutes() < 10)? "0" + dE.getMinutes(): dE.getMinutes())
                    }
                }else{
                    col.innerHTML = (LOCALIZE[this.idioma]).mensajes.abierto;
                }


                divRow.appendChild(col);
                col = document.createElement("div");//Tiempo
                col.className = "col-sm-1";
                calHoras = 0;
                if(arrFichajes[i].finishDate  != null){
                    if(dE.getHours() == 23 && dE.getMinutes() == 59) calHoras += 1000;
                    calHoras += ((dE.getTime() - d.getTime()));
                    horas = parseInt(calHoras/ (60*60*1000))
                    minutos = (calHoras/(60*1000))%(60)
                    col.innerHTML =  horas + " h " + minutos + " min";
                }else{
                    col.innerHTML = (LOCALIZE[this.idioma]).mensajes.sinCerrar;
                }
                divRow.appendChild(col);

                actividad = this.getActividad(arrFichajes[i].activityId);
                col = document.createElement("div");//Tipo
                col.className = "col";
                col.innerHTML = "<span class=\"miniCuadro\" style=\"background-color:" + actividad.color +  ";\"></span>&nbsp;" + this.cleanEntitys(actividad.name);
                divRow.appendChild(col);

                col = document.createElement("div");//Observaciones
                col.className = "col-sm-2";
                col.innerHTML = arrFichajes[i].observations;
                divRow.appendChild(col);

                col = document.createElement("div");//Observaciones mod
                col.className = "col";
                col.innerHTML = arrFichajes[i].observationsAdmin
                divRow.appendChild(col);

                document.getElementById("capaDetalles").appendChild(divRow);
                //Mostrar el idioma del usuario.
                $("#capaDetalles name[language-id='"+this.idioma+"']" ).css("display", "block");
            }
        },

        createLeyenda : function(){
            try{
                document.getElementById("capaLeyenda").innerHTML = "";
                var divRow = null, col =  null, txt = null;
                divRow = document.createElement("div");
                divRow.className = "row";
                for( p in this.JSON_DATA.actividades){
                    if(this.JSON_DATA.actividades[p].active){
                        col = document.createElement("div");
                        txt = this.cleanEntitys(this.JSON_DATA.actividades[p].name);
                        col.innerHTML = "<span class=\"cuadroLeyenda miniCuadroLeyenda\" style=\"background-color:#COLOR#;\">&nbsp;</span>&nbsp;#NOMBRE#"
                            .replace("#NOMBRE#",txt).replace("#COLOR#",this.JSON_DATA.actividades[p].color);
                        col.className = "col-sm-3 leyend-field";
                        divRow.appendChild(col);
                    }
                }
                document.getElementById("capaLeyenda").appendChild(divRow);
            }catch(e){
                console.error("calendarioBarras.createLeyenda(): " + e);
            }
        },
        createLineaTabla : function(dateSeek, fFin){
            try{

                this.idioma = "es_ES";

                var where = document.getElementById("capaHorario");
                var divRow = null;
                var col =  null,  col15 = null;
                var day24h = 0;
                where.innerHTML = "";
                this.CONSTANTES.ANO_ACTUAL = dateSeek.getFullYear();
                this.CONSTANTES.MES_ACTUAL = dateSeek.getMonth() +1;
                document.getElementById("spanMesActual")
                    .innerHTML = dateSeek.toLocaleString(this.idioma.replace("_","-"), { month: 'long' }) + " (" + this.CONSTANTES.ANO_ACTUAL + ")";
                for(var columna = 0; dateSeek.getTime() < (fFin.getTime() + day24h); columna++, dateSeek.setTime(dateSeek.getTime() + day24h)){
                    divRow = document.createElement("div");
                    divRow.className = "row-calendario border";
                    col = document.createElement("div");

                    col.className = (this.isFestivo(dateSeek) & columna != 0)? "col-date festivo" : "col-date";
                    if(columna == 0){
                        col.innerHTML = "Fecha";
                    }else{
                        divRow.id = "fila-horario-" + this.formatDataLang(dateSeek,"");
                        col.innerHTML = " <span class='day-week d-block d-sm-none'> " + this.formatDataLangShort(dateSeek,"/") + "</span>"
                            + " <span class='day-week d-none d-sm-block'> " + this.formatDataLang(dateSeek,"/") + " - "
                            + dateSeek.toLocaleString(this.idioma.replace("_","-"), {weekday: 'long'})+ "</span>";
                        day24h = (day24h == 0)? 60*60*24*1000 : day24h;
                    }
                    divRow.appendChild(col);
                    for(var i = 0; i < 24; i++){
                        col = document.createElement("div");
                        if(where.childNodes.length == 0){
                            col.className = (this.isFestivo(dateSeek) && columna != 0)? "col-calendario-hora festivo" : "col-calendario-hora";
                        }else{
                            col.className = (this.isFestivo(dateSeek) && columna != 0)? "col-calendario festivo" : "col-calendario";
                        }
                        if(columna == 0 && i != 24) col.innerHTML = i+1;
                        divRow.appendChild(col);
                    }
                    col = document.createElement("div");
                    col.className = "col-total text-right d-none d-sm-block";
                    if(columna == 0) col.innerHTML = (LOCALIZE[this.idioma]).mensajes.totalHoras;
                    divRow.appendChild(col);
                    where.appendChild(divRow);
                }
                this.createLeyenda();
            }catch(e){
                console.error("calendarioBarras.createLineaTabla(): " + e.toString());
            }
        },
        isFestivo : function(dateSeek){
            var is = false, d = null, arr = null;
            for( x in this.JSON_DATA.festivos){
                if(this.JSON_DATA.festivos[x].active){
                    if(this.JSON_DATA.festivos[x].day == dateSeek.getDate() && this.JSON_DATA.festivos[x].month == (dateSeek.getMonth()+1)){
                        is = true;
                        break;
                    }
                }
            }
            return is;
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
                console.error("calendarioBarras.formatDataLang(): " + e.toString())
            }
        },formatDataLangShort : function(d,separator,lang){
            try{
                var str = "";
                if(separator == null) separator = "/";
                dd = (d.getDate() < 10)? "0" + d.getDate() :  d.getDate();
                return dd;
            }catch(e){
                console.error("calendarioBarras.formatDataLang(): " + e.toString())
            }
        },
        printBarra : function(id, entrada, salida, tipo){
            try{
                var htmlCol = null, idFila =  null;
                var calcuHoras = 0;
                var horas = 0 , minutos = 0;
                entrada = this.getCleanHour( entrada);
                salida  = this.getCleanHour( salida);
                idFila =  "fila-horario-" + this.formatDataLang(entrada.fecha,"");
                htmlCol = document.getElementById( idFila );

                if(htmlCol ){
                    celda = entrada.hora + 1;
                    //console.log("id: " + id + " / " + salida.hora + ":" + salida.min)
                    var cEnd  = salida.hora + 1;
                    if(cEnd == 24) cEnd = 25;

                    for(var c = celda; c <= cEnd ; c++ ){
                        if(!( salida.min == 0 && (c -1) == salida.hora)){
                            this.pintarCelda(htmlCol, c, entrada, salida, tipo,id);
                        }

                    }//end for celda
                    if(!this.CONSTANTES.TOTALES_HORAS_DIA[idFila]) this.CONSTANTES.TOTALES_HORAS_DIA[idFila] = 0;
                    if(this.getActividad(tipo).workTime == true){
                        calcuHoras = 0;
                        if(salida.hora == "0" && salida.min == "0") calcuHoras += 1000;
                        calcuHoras += (salida.fecha.getTime() - entrada.fecha.getTime());
                        this.CONSTANTES.TOTALES_HORAS_DIA[idFila] += parseInt(calcuHoras /(60*1000));
                        hora = parseInt(this.CONSTANTES.TOTALES_HORAS_DIA[idFila]/ (60))
                        minutos = this.CONSTANTES.TOTALES_HORAS_DIA[idFila]%60;
                        htmlCol.lastChild.innerHTML =  ((hora < 10)? "0" + hora : hora) + " h " + ((minutos < 10)? "0" + minutos : minutos) + " min";
                    }

                }
            }catch(e){
                console.error("calendarioBarras.printBarra(): " + e.toString());
            }
        },
        pintarCelda : function(fila, c, entrada, salida, tipo, id){
            try{
                if(c == 1) return;
                if(c != 1) c--;

                //console.log(id + " - printCelda: " + c + "/ " + entrada.hora + ":" + entrada.min + " a " + salida.hora + ":" + salida.min)
                var cols = fila.childNodes[c]; //contenido de la columna
                var min = 0;
                var porc = 100;
                var col15 = document.createElement("div");
                col15.className = "minutos15";

                if(salida.hora == 23 && salida.min == 59){ // si termina a las 00:00 -> se le pasa 23:59:59
                    salida.hora = 0
                    salida.min = 0;
                }

                if(entrada.hora == salida.hora){
                    min = salida.min - entrada.min;
                }else{
                    if(c  == entrada.hora){
                        min = 60 - entrada.min;
                    }else if(c  == salida.hora){
                        min = salida.min;
                    }else{
                        min = 60;
                    }

                }
                porc = ((min * 100)/60).toFixed(2);
                //col15.style.width =   porc +  "%";
                col15.id = fila.id + "-hora-" + id ;
                col15.className = "clsFicha";
                col15.innerHTML = "&#160;";
                //para el title ..
                if (salida.hora == 25 && salida.min == 0){
                    salida.hora = 0;
                    salida.min = 0;
                }
                col15.title = "("+ id + ") " + this.cleanEntitys(this.getActividad(tipo).name)  + " (" + ((entrada.hora < 10)? "0" + entrada.hora : entrada.hora) + ":" + ((entrada.min < 10)? "0" + entrada.min : entrada.min) + " - "
                    + ((salida.hora < 10)? "0" + salida.hora : salida.hora) + ":" + ((salida.min < 10)? "0" + salida.min : salida.min) + ")";
                col15.style.backgroundColor = this.getActividad(tipo).color;

                col15.setAttribute("starttime",entrada.min);
                col15.style.display = "inline-table";


                if(c == entrada.hora){
                    col15.style.gridColumnStart = (entrada.min == 0)? 1 : entrada.min;
                }else{
                    col15.style.gridColumnStart = 1;
                }
                if(salida.hora > c){
                    col15.style.gridColumnEnd =  "last";
                }else{
                    col15.style.gridColumnEnd =  (salida.min == 0)? "last" : salida.min;
                }

                /*console.log("grid : " + col15.style.gridColumnStart + "/" + col15.style.gridColumnEnd);
                console.log("c: " + c);
                console.log("entrada: " + entrada.hora + ":" + entrada.min )
                console.log("salida: " + salida.hora + ":" + salida.min )*/

                cols.appendChild(col15);
            }catch(e){
                console.error("calendarioBarras.pintarCelda(): " + e.toString());
            }
        },
        getActividad : function (idTipo){
            var obj = null;
            for(x in this.JSON_DATA.actividades){
                if(this.JSON_DATA.actividades[x].activityId == idTipo){
                    obj = this.JSON_DATA.actividades[x];
                    break;
                }
            }
            if(obj == null){
                obj = {
                    color : "RED",
                    active: true,
                    activityId: "2000",
                    color: "#00FFFF",
                    name: "Actividad desconocida",
                    type: 1,
                    workTime: false
                }

            }
            return obj;
        },
        textoClean : null,
        cleanEntitys : function(str){
            if(this.textoClean == null){
                var txt = document.createElement("textarea");
                txt.style.display = "none";
            }
            txt.innerHTML = str;
            return txt.value;
        },
        clickFichaje : function(node){
            //alert(node.id + "\n\r" + node.title);
            //movemos la capa de detalles a ... ¿?
            this.createDetallesDia(node.id);
        },
        filterFechas : function(id_usuario){
			//console.log(document.getElementById("_com_preving_liferay_portlet_timesheet_history_web_TimesheetHistoryPortlet_inpFechaHasta").value)
            try{
                if(id_usuario == null) id_usuario = this.CONSTANTES.ID_USUARIO;
                var fInicio = this.formatDataLang(new Date(document.getElementById("_com_preving_liferay_portlet_timesheet_history_web_TimesheetHistoryPortlet_inpFechaDesde").value.replace( /(\d{2})\/(\d{2})\/(\d{4})/, "$2/$1/$3")), "-", "en_EN");
				//console.log(fInicio);
				//console.log(document.getElementById("_com_preving_liferay_portlet_timesheet_history_web_TimesheetHistoryPortlet_inpFechaHasta").value)
                var fFin = document.getElementById("_com_preving_liferay_portlet_timesheet_history_web_TimesheetHistoryPortlet_inpFechaHasta").value.replace( /(\d{2})\/(\d{2})\/(\d{4})/, "$2/$1/$3");
				//console.log(fFin);
				var fFinEN = this.formatDataLang(new Date(fFin), "-", "en_EN");
				//console.log(fFinEN);
                var dS = null, dE = null;
                if(fInicio.trim() == "" && fFin.trim() != ""){
                    alert("Debe indicar una fecha de inicio");
                    return false;
                }

                if(fInicio.trim() != "" && fFin.trim() == ""){
                    dS = new Date(fInicio);
                    dE = new Date();
                }else if(fInicio.trim() != "" && fFin.trim() != ""){
                    dS = new Date(fInicio);
                    dE = new Date(fFin);
                } else if(fInicio.trim() == "" && fFin.trim() == "") {
                    dS = null;
                    dE = null;
                }

                if(dS != null && dS.getTime() > dE.getTime()){
                    alert("Debe indicar una fecha de inico inferior a la de fin");
                    return false;
                }
                if(dS != null && dS.getFullYear() < 2019){
                    //alert("Ha introducido un año inferior a la app");
                    return false;
                }


                this._continue(dS,dE);
            }catch(e){
                console.error("filterFechas(): " + e.toString());
            }
        },
        //@see ajustar de GMT+X a UTC (LIFERAY)
        dateGMTtoUTC : function(dateTime){
            if(!dateTime) dateTime = new Date();
            dateTime.setTime(dateTime.getTime() + dateTime.getTimezoneOffset()*60*1000);
            return dateTime;

        },
        //@see devuele item con hora y minutos separados
        getCleanHour : function(dateHora){
            dateHora = this.dateGMTtoUTC(dateHora);
            return{
                fecha : dateHora,
                hora : dateHora.getHours(),
                min : dateHora.getMinutes()
            }
        },
        //@see dias por mes
        getDaysInMonth : function(month,year){
            return  (new Date(2020, month, 0)).getDate();
        },
        //@see moverse por meses
        moveMonth : function(summ){
            try{
                //no dejar que avance mas del mes actual
                if(summ > 0 && this.CONSTANTES.ANO_ACTUAL == this.CONSTANTES.HOY.getFullYear() &&
                    parseInt(this.CONSTANTES.MES_ACTUAL-1) ==  this.CONSTANTES.HOY.getMonth() ) return;
                var day = null;
                this.CONSTANTES.MES_ACTUAL = this.CONSTANTES.MES_ACTUAL + summ;

                if(this.CONSTANTES.MES_ACTUAL > 12){
                    this.CONSTANTES.ANO_ACTUAL++;
                    this.CONSTANTES.MES_ACTUAL = 1;
                }else if(this.CONSTANTES.MES_ACTUAL <= 0){
                    this.CONSTANTES.ANO_ACTUAL--;
                    this.CONSTANTES.MES_ACTUAL = 12;
                }

                if(this.CONSTANTES.MES_ACTUAL == this.CONSTANTES.HOY.getMonth()+1 ){
                    day =  this.CONSTANTES.HOY.getDate();
                }else{
                    day = this.getDaysInMonth(this.CONSTANTES.MES_ACTUAL,this.CONSTANTES.ANO_ACTUAL);
                }

                var fInicio = new Date(this.CONSTANTES.ANO_ACTUAL + "/" + (this.CONSTANTES.MES_ACTUAL) + "/01");
                var fFin = new Date(this.CONSTANTES.ANO_ACTUAL + "/" + (this.CONSTANTES.MES_ACTUAL) + "/" + day);
                document.getElementById("_com_preving_liferay_portlet_timesheet_history_web_TimesheetHistoryPortlet_inpFechaDesde").value = this.formatDataLang(fInicio,"/","es");
                document.getElementById("_com_preving_liferay_portlet_timesheet_history_web_TimesheetHistoryPortlet_inpFechaDesde").value = this.formatDataLang(fFin,"/","es");
                this.createLineaTabla(fInicio, fFin);
                this.drawCalendario(this.JSON_DATA, this.CONSTANTES.ID_USUARIO);
            }catch(e){
                console.error("moveMonth(): " + e.toString());
            }
        },
        setUsuarioID : function(id_usuario){
            this.CONSTANTES.ID_USUARIO = id_usuario;
        },
        initCalendarioBarras : function(xhrDirs,id_usuario,fInicio, fFin, idioma){
            try{
                var jsonData = null;
                this.JSON_DATA = {};
                this.idioma = idioma;
                this.ID_USUARIO = id_usuario;
                this.loadXMLHttpJsonRequest(xhrDirs, null,this.JSON_DATA);
            }catch(e){
                console.error("initCalendarioBarras(): " + e.toString());
            }
        },
        //@see una vez cargado los ajax
        _continue : function(fInicio, fFin){
            if(fInicio == null){
				fInicio = new Date ();
				fInicio.setDate(1);
			}
            this.CONSTANTES.MES_ACTUAL = fInicio.getMonth();
            this.CONSTANTES.ANO_ACTUAL = fInicio.getFullYear();
            if(fFin == null){
                fFin = new Date ();
                fFin.setMonth(fInicio.getMonth());
                fFin.setDate(this.getDaysInMonth(fInicio.getMonth()+1,fInicio.getFullYear()));
                //fFin.setDate(this.getDaysInMonth(this.CONSTANTES.MES_ACTUAL,this.CONSTANTES.ANO_ACTUAL));
            }
			document.getElementById("_com_preving_liferay_portlet_timesheet_history_web_TimesheetHistoryPortlet_inpFechaDesde").value = this.formatDataLang(fInicio,"/","es");
			document.getElementById("_com_preving_liferay_portlet_timesheet_history_web_TimesheetHistoryPortlet_inpFechaHasta").value = this.formatDataLang(fFin,"/","es");

            var d = new Date();
            d.setTime(1594072800000)
            d = this.dateGMTtoUTC(d);
            d.setTime(1594079999000)
            d = this.dateGMTtoUTC(d);

            this.createLineaTabla(fInicio, fFin);
            this.drawCalendario(this.JSON_DATA,this.CONSTANTES.ID_USUARIO);
        },
        //@see llamadas AJAX recursivas
        isDibujar :  false,
        loadXMLHttpJsonRequest : function(xhrDirs,prop,jsonRequest){
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (this.readyState == 4 && this.status == 200){
                    //ordenar por fecha inicio
                    CalendarioBarras.JSON_DATA[(prop == null)? Object.keys(xhrDirs)[0] : prop] = JSON.parse(this.responseText).sort(function(a, b){
                        return a.startDate - b.startDate;
                    });
                    for(p in xhrDirs){
                        if(CalendarioBarras.JSON_DATA[p] == undefined){
                            this.isDibujar = false;
                            CalendarioBarras.loadXMLHttpJsonRequest(xhrDirs, p,CalendarioBarras.JSON_DATA);
                            break;
                        }else{
                            this.isDibujar = true;
                        }
                    }
                    if(this.isDibujar)    CalendarioBarras._continue(null,null);
                }
            };
            xhr.open("GET", xhrDirs[ (prop == null)? Object.keys(xhrDirs)[0] : prop ], true);
            xhr.send(null);
        }
    }})();
