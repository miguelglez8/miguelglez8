var tabla = null;
var urlAjaxBase = "https://preving2.serikat.es/web/canal-etico-denuncias/administracion-denuncias?p_p_id=com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaV2Portlet_INSTANCE_T6VlaDoZ3ixU&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_resource_id=getAllComunicadosByCompany&p_p_cacheability=cacheLevelPage&doAsUserId=jSViU%2BE2AcCkFKMcRgkVOw%3D%3D" ;
var urlAjaxCustomCategory ="https://preving2.serikat.es/web/canal-etico-denuncias/administracion-denuncias?p_p_id=com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaV2Portlet_INSTANCE_T6VlaDoZ3ixU&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_resource_id=customGestor&p_p_cacheability=cacheLevelPage&doAsUserId=jSViU%2BE2AcCkFKMcRgkVOw%3D%3D" ;
var editURL = "https://preving2.serikat.es/web/canal-etico-denuncias/administracion-denuncias?p_p_id=com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaV2Portlet_INSTANCE_T6VlaDoZ3ixU&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&doAsUserId=jSViU%2BE2AcCkFKMcRgkVOw%3D%3D&_com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaV2Portlet_INSTANCE_T6VlaDoZ3ixU_comunicadoEditId=ID_COMUNICADO&_com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaV2Portlet_INSTANCE_T6VlaDoZ3ixU_mvcPath=%2Fcomunicado.jsp";
var jsonData = null;
var array=[]
array.push('Pendiente de asignar');
array.push('Admisión/Inadmisión');
array.push('Investigación');
array.push('Finalización');
array.push('Finalizada');
array.push('Caducada');
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

var buttonCommon = {
        exportOptions: {
            format: {
                body: function ( data, row, column, node ) {
                    // Strip $ from salary column to make it numeric
                    if(column==8){
                        return ;
                    }else if(column==5){
                        var gestoresImportados='';
                        for(var y=2;y<node.childNodes.length;y++){
                            gestoresImportados+=node.childNodes[y].textContent;
                        }
                        return gestoresImportados;
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
};

tabla = $('#table2').DataTable( {
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
                alert("Su sesiÃ³n a caducado. Introduzca las credenciales de nuevo. ")
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
		$("#table2").before(elementoAnadido);
		if($('th').last().width()<80){
		   $('th').last().width(80)
		}
		// script para tooltip
		$("#table2 tbody tr > td:nth-child(3)").each( function(i, e){
			console.log($(e));
			$(e).find('span:not(.poptip)').remove();
			$(e).html('<span class="poptip" title="'+$(e).text()+'">'+$(e).text()+'</span>');
		});
		loadAllPoptip();// carga los tooltip
    },
    "rowCallback": function( row, data ) {
		console.log(row.querySelector('td:nth-child(3)').innerHTML);
		console.log(data);
		var elemContent = document.createRange().createContextualFragment(
			`<span class="poptip" title="${row.querySelector('td:nth-child(3)').textContent}">${row.querySelector('td:nth-child(3)').textContent}</span>`
		);
		row.querySelector('td:nth-child(3)').innerHTML = ''
		row.querySelector('td:nth-child(3)').appendChild(elemContent);
		loadPoptip( row.querySelector('td:nth-child(3) .poptip') );
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
                return '<span class="hide">'+removeAccents(data)+'</span> '+data
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
                return '<span class="hide">'+data+'</span>'+array[row.status]
            }
         },
         {
            "targets"  : [3],
            "orderable": true,
            "render": function (data, type, row) {
                let arr = data.split("-");
                let html =  "";
                if(arr.length > 1 && arr[0] == "10006"){//es id OTRA
                    html = '<span class="hide" id="categCom' + row.idComun + '">'+ arr[0]+'</span> ';
                    html += '<span title="' + row.descripcionCat + '">' + arr[1] + '</span>';
                }else if(arr.length > 1){
                    html = '<span class="hide" id="categCom' + row.idComun + '">'+ arr[0]+'</span> ';
                    html += arr[1];
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
                 return '<span class="hide">'+removeAccents(data)+'</span> '+data
             }
         },
         {
              "targets"  : [5],
              "orderable": true,
              "render": function (data, type, row) {
                  var dataGuion=data.indexOf("-")
                  var splitUsuarios=data.substr(dataGuion+1,data.length-1).split(",")
                  var cadenaUsuarios=''
                  for(var x=0;x<splitUsuarios.length;x++){
                    cadenaUsuarios+='<p>'+splitUsuarios[x]+'</p>'
                  }
                  return '<span class="hide" id="gestorCom'+row.idComun+'">'+data.substr(0,dataGuion)+'</span> '+cadenaUsuarios
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
                if(row.idComun != 1){
                    var cadena='<div class="acciones-wrapper">'
                    cadena+='<a class="ico-acciones-tabla editar" href="#ENLACE#"></a> '.replace("#ENLACE#", editURL.replace("ID_COMUNICADO", data))
                    if(true){
                        if(row.status==4 || row.status==5){
                            cadena=cadena+'<button type="button" class="ico-acciones-tabla categorizarComunicado" disabled></button>'+
                            '<button class="ico-acciones-tabla asignarComunicado" disabled></button>';
                        }else{
                            cadena=cadena+'<button type="button" class="ico-acciones-tabla categorizarComunicado" onclick="asignarCategoria(#ID#)"></button>'.replace("#ID#",data)+
                            '<button class="ico-acciones-tabla asignarComunicado" onclick="asignarGestor(#ID#)"></button>'.replace("#ID#",data);
                        }
                    }
                    if(row.prorrogado==false && row.status<4){

                        cadena+='<button class="ico-acciones-tabla prorrogarComunicado" onclick="prorrogarCom(#ID#)"></button>'.replace("#ID#",data)


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
            extend: 'pdfHtml5'
        } )
	],
	"language": {
		"sProcessing":     "Procesando...",
		"sLengthMenu":     "Mostrar _MENU_ comunicados",
		"sZeroRecords":    "No se encontraron comunicados",
		"sEmptyTable":     "No se han creado comunicados",
		"sInfo":           "Mostrando comunicados del _START_ al _END_ de un total de _TOTAL_ comunicados",
		"sInfoEmpty":      "Mostrando comunicados del 0 al 0 de un total de 0 comunicados",
		"sInfoFiltered":   "(filtrado de un total de _MAX_ comunicados)",
		"sInfoPostFix":    "",
		"sSearch":         "Buscar",
		"sUrl":            "",
		"sInfoThousands":  ",",
		"sLoadingRecords": "Cargando...",
		"oPaginate": {
			"sFirst":    "Primero",
			"sLast":     "Ãltimo",
			"sNext":     "Siguiente",
			"sPrevious": "Anterior"
		},
		"oAria": {
			"sSortAscending":  "Activar para ordenar la columna de manera ascendente",
			"sSortDescending": "Activar para ordenar la columna de manera descendente"
		},
		"buttons": {
			"copy": "Copiar",
			"colvis": "Visibilidad"
		}
	}
} );