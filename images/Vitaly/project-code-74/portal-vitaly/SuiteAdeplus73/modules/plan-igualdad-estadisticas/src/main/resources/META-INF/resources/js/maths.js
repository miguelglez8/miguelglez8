 function calculatePercentage(value, total) {
    var percentage = ((value * 100) / total).toFixed(0);
    return (isFinite(percentage) ? percentage : 0)
}

function calculateAnalysis(nHombres, nMujeres) {
    var total = nHombres + nMujeres;
    var pHombres = calculatePercentage(nHombres, total);
    var pMujeres = calculatePercentage(nMujeres, total);

    if (pHombres >= 60) return "M";
    if (pMujeres >= 60) return "F";
    return "E";
}

function calculateTablePuestoEdad(data) {

    var groups = [];
    var labels = [];

    data.reduce(function(res, value) {
      if (!res[value.puesto]) {
        res[value.puesto] = { puesto: value.puesto, hombres: 0, mujeres: 0 };
        groups.push(res[value.puesto]);
        if ((value.puesto!= undefined && value.puesto != 'undefined') && puestos != "" ) {
            labels.push( puestos.find(el => el.id === value.puesto + "").nombre );
        }
      }
      res[value.puesto].hombres += parseInt(value.hombres);
      res[value.puesto].mujeres += parseInt(value.mujeres);
      return res;
    }, {});

    var nHombres = [];
    var nMujeres = [];
    var nTotales = [];

    groups.forEach(function (item, index) {
      nHombres.push(item.hombres);
      nMujeres.push(item.mujeres);
      nTotales.push(parseInt(item.hombres) + parseInt(item.mujeres));
    });

    var result = {};
        result["nHombres"] = nHombres;
        result["nMujeres"] = nMujeres;
        result["nTotales"] = nTotales;
        result["labels"] = labels;

    return result;
}

function calculateTablePuestoAntiguedad(row) {

    var id = row.data('id');
    var row = row.closest('tr');

    var totalHombres = parseInt(row.find("td:eq(1) input").val());
    var totalMujeres = parseInt(row.find("td:eq(4) input").val());

    if (totalHombres < parseInt(row.find("td:eq(2) input").val())) row.find("td:eq(2) input").val(0);
    if (totalMujeres < parseInt(row.find("td:eq(5) input").val())) row.find("td:eq(5) input").val(0);

    row.find("td:eq(3)").html('<span class="hombres-percentage">' + calculatePercentage(row.find("td:eq(2) input").val(), totalHombres) + '%</span>');
    row.find("td:eq(6)").html('<span class="mujeres-percentage">' + calculatePercentage(row.find("td:eq(5) input").val(), totalMujeres) + '%</span>');

    var totalRow = $('#' + id).closest('tr');
    var totalInputs = totalRow.find("input");

    totalInputs.each(function() {
        var totalId = $(this).attr('id');
        var total = 0;
        $('*[data-id="' + totalId + '"]').each(function(){ total = total + parseInt($(this).val()); });
        $('#' + totalId).val(total);
    });

    totalRow.find("td:eq(3)").html('<span class="hombres-percentage">' + calculatePercentage(totalRow.find("td:eq(2) input").val(), totalRow.find("td:eq(1) input").val()) + '%</span>');
    totalRow.find("td:eq(6)").html('<span class="mujeres-percentage">' + calculatePercentage(totalRow.find("td:eq(5) input").val(), totalRow.find("td:eq(4) input").val()) + '%</span>');

}

function getModa(data, type) {

    var moda = {};
    $.each(data,function(index,element){
        var qty = type == 'mujeres' ? parseInt(element.mujeres) : parseInt(element.hombres);
        if(moda[element.id] == undefined) moda[element.id] = 0;
        moda[element.id] += qty;
    });
    var array = Object.values(moda);
    var modas = Object.keys(moda).filter(key => moda[key] === Math.max(...array));

    var modaStr = "";
    var personas =  data.reduce(function (sum, item) { return sum + (type == 'mujeres' ? parseInt(item.mujeres) : parseInt(item.hombres)); }, 0);
    if (personas != 0) {

        var results = [];
        modas.forEach(function (item, index) {
          results.push( getEstudio(item) );
        });
        modaStr = results.join(", ");

    }
    $('#moda_' + type).val( modaStr );
    return modaStr != "" ? modas : "";
}

function getPuestoModa(data, moda, type) {
    if (moda == "") {
        $('#puesto_' + type).val( "" );
        return "";
    }

    var puestosModa =  data.filter(item => item.id === moda);
        puestosModa.sort(function (a, b) {
          return type == 'mujeres' ? (b.mujeres - a.mujeres) : (b.hombres - a.hombres);
        });

    var puesto = puestosModa[0] !== undefined ? puestosModa[0].puesto : "";
    if (puesto != "" && puestos != "") $('#puesto_' + type).val( puestos.find(item => item.id === puesto).nombre );
    return puesto;
}

function getSuperior(data, moda, puesto, type) {
    if (moda == "") {
        $('#superiores_' + type).val( "0%" );
        return "";
    }

    var superiores =  data.filter(item => item.id > moda && item.puesto === puesto);
    var puesto = data.filter(item => item.puesto === puesto);

    var personas =  superiores.reduce(function (sum, item) { return sum + (type == 'mujeres' ? parseInt(item.mujeres) : parseInt(item.hombres)); }, 0);
    var total =  puesto.reduce(function (sum, item) { return sum + (type == 'mujeres' ? parseInt(item.mujeres) : parseInt(item.hombres)); }, 0);
    var result = calculatePercentage(personas, total);

    $('#superiores_' + type).val( result + "%" );
    return result;
}

function getInferior(data, moda, puesto, type) {
    if (moda == "") {
        $('#inferiores_' + type).val( "0%" );
        return "";
    }

    var inferior =  data.filter(item => item.id < moda && item.puesto === puesto);
    var puesto = data.filter(item => item.puesto === puesto);

    var personas =  inferior.reduce(function (sum, item) { return sum + (type == 'mujeres' ? parseInt(item.mujeres) : parseInt(item.hombres)); }, 0);
    var total =  puesto.reduce(function (sum, item) { return sum + (type == 'mujeres' ? parseInt(item.mujeres) : parseInt(item.hombres)); }, 0);
    var result = calculatePercentage(personas, total);

    $('#inferiores_' + type).val( result + "%" );
    return result;
}

function calculateTableEstudios(id, item) {

    if (item != null) {
        var row = item.closest('tr');
        var hombres = row.find("td:eq(1) input").val();
        var mujeres = row.find("td:eq(2) input").val();
        row.find("td:eq(3) input").val(parseInt(hombres) + parseInt(mujeres));
    }

    var table = tableNotLastGroupToJson(id, "puesto");
    var json = JSON.parse(table);

    var modaHombres = getModa(json.data, "hombres");
    var puestoHombres = getPuestoModa(json.data, modaHombres[0], "hombres");
    var superioresHombres = getSuperior(json.data, Math.max(...modaHombres), puestoHombres, "hombres");
    var inferioresHombres = getInferior(json.data, Math.min(...modaHombres), puestoHombres, "hombres");

    var modaMujeres = getModa(json.data, "mujeres");
    var puestoMujeres = getPuestoModa(json.data, modaMujeres[0], "mujeres");
    var superioresMujeres = getSuperior(json.data, Math.max(...modaMujeres), puestoMujeres, "mujeres");
    var inferioresMujeres = getInferior(json.data, Math.min(...modaMujeres), puestoMujeres, "mujeres");

}

function calculateTableHorariosJornada(id, item) {

    if (item != null) {
        var row = item.closest('tr');
        var hombres = row.find("td:eq(1) input").val();
        var mujeres = row.find("td:eq(3) input").val();
        row.find("td:eq(5) input").val( parseInt(hombres) + parseInt(mujeres) );
    }

    var colTotal = 0;
    $('.turnos-total').each(function(){ colTotal += parseInt( $(this).val() ) });

    $("#tabla_" + id + " tbody tr").not(".group").each(function(index) {

        var row = $(this);

        var hombres = row.find("td:eq(1) input").val();
        var mujeres = row.find("td:eq(3) input").val();
        var total = parseInt(hombres) + parseInt(mujeres);

        row.find("td:eq(2)").html('<span>' + calculatePercentage(hombres, colTotal) + '%</span>');
        row.find("td:eq(4)").html('<span>' + calculatePercentage(mujeres, colTotal) + '%</span>');
        row.find("td:eq(6)").html('<span>' + calculatePercentage(total, colTotal) + '%</span>');

    });

    $("#tabla_" + id + " tbody tr.group-end").each(function(index) {

        var row = $(this);

        var idHombres = row.find("td:eq(1) input").attr("id");
        var idMujeres = row.find("td:eq(3) input").attr("id");

        var totalHombres = 0;
        var totalMujeres = 0;

        $('*[data-id="' + idHombres + '"]').each(function(){ totalHombres += parseInt($(this).val()); });
        $('*[data-id="' + idMujeres + '"]').each(function(){ totalMujeres += parseInt($(this).val()); });

        var totalTotal = parseInt(totalHombres) + parseInt(totalMujeres);

        row.find("td:eq(1) input").val(totalHombres);
        row.find("td:eq(3) input").val(totalMujeres);
        row.find("td:eq(5) input").val(totalTotal);

        row.find("td:eq(2)").html('<span>' + calculatePercentage(totalHombres, colTotal) + '%</span>');
        row.find("td:eq(4)").html('<span>' + calculatePercentage(totalMujeres, colTotal) + '%</span>');
        row.find("td:eq(6)").html('<span>' + calculatePercentage(totalTotal, colTotal) + '%</span>');
    });

}

function calculateTableIngresos(id, item) {
    if (item != null) {
        var row = $(this).closest('tr');
        var mujeres = row.find("td:eq(1) input").val();
        var hombres = row.find("td:eq(3) input").val();
        row.find("td:eq(5) input").val( parseInt(hombres) + parseInt(mujeres) );
    }

    $("#tabla_" + id + " tbody tr.group-end").each(function(index) {

        var row = $(this);

        var idMujeres = row.find("td:eq(1) input").attr("id");
        var idHombres = row.find("td:eq(3) input").attr("id");

        var totalHombres = 0;
        var totalMujeres = 0;

        $('*[data-id="' + idHombres + '"]').each(function(){ totalHombres += parseInt($(this).val()); });
        $('*[data-id="' + idMujeres + '"]').each(function(){ totalMujeres += parseInt($(this).val()); });

        var totalTotal = parseInt(totalHombres) + parseInt(totalMujeres);

        row.find("td:eq(1) input").val(totalMujeres);
        row.find("td:eq(3) input").val(totalHombres);
        row.find("td:eq(5) input").val(totalTotal);

        row.find("td:eq(2)").html('<span>' + calculatePercentage(totalMujeres, totalTotal) + '%</span>');
        row.find("td:eq(4)").html('<span>' + calculatePercentage(totalHombres, totalTotal) + '%</span>');
        row.find("td:eq(6)").html('<span>' + calculatePercentage(totalTotal, totalTotal) + '%</span>');

   });

   $("#tabla_" + id + " tbody tr").not(".group").each(function(index) {

        var row = $(this);

        var tipo = row.find("td:eq(0) span").data('tipo');
        var colTotal = $('#tabla_' + id + ' #total_' + tipo).val();

        var mujeres = row.find("td:eq(1) input").val();
        var hombres = row.find("td:eq(3) input").val();
        var total = parseInt(hombres) + parseInt(mujeres);

        row.find("td:eq(5) input").val(total);
        row.find("td:eq(2)").html('<span>' + calculatePercentage(mujeres, colTotal) + '%</span>');
        row.find("td:eq(4)").html('<span>' + calculatePercentage(hombres, colTotal)  + '%</span>');
        row.find("td:eq(6)").html('<span>' + calculatePercentage(total, colTotal) + '%</span>');

   });

}

function calculateTableCeses(id, item) {

    if (item != null) {
        var row = item.closest('tr');
        var mujeres = row.find("td:eq(1) input").val();
        var hombres = row.find("td:eq(3) input").val();
        var total = parseInt(hombres) + parseInt(mujeres)
        row.find("td:eq(5) input").val( total );

        row.find("td:eq(2)").html('<span>' + calculatePercentage(mujeres, total) + '%</span>');
        row.find("td:eq(4)").html('<span>' + calculatePercentage(hombres, total) + '%</span>');
        row.find("td:eq(6)").html('<span>' + calculatePercentage(total, total) + '%</span>');
    }

    $("#tabla_" + id + " tbody tr.group-end").each(function(index) {
        var row = $(this);

        var idMujeres = row.find("td:eq(1) input").attr("id");
        var idHombres = row.find("td:eq(3) input").attr("id");

        var totalHombres = 0;
        var totalMujeres = 0;

        $('*[data-id="' + idHombres + '"]').each(function(){ totalHombres += parseInt($(this).val()); });
        $('*[data-id="' + idMujeres + '"]').each(function(){ totalMujeres += parseInt($(this).val()); });

        var totalTotal = parseInt(totalHombres) + parseInt(totalMujeres);

        row.find("td:eq(1) input").val(totalMujeres);
        row.find("td:eq(3) input").val(totalHombres);
        row.find("td:eq(5) input").val(totalTotal);

        row.find("td:eq(2)").html('<span>' + calculatePercentage(totalMujeres, totalTotal) + '%</span>');
        row.find("td:eq(4)").html('<span>' + calculatePercentage(totalHombres, totalTotal) + '%</span>');
        row.find("td:eq(6)").html('<span>' + calculatePercentage(totalTotal, totalTotal) + '%</span>');
    });

}

function calculateTableReduccionJornada(item) {
    var row = item.closest('tr');
    var hombres = row.find("td:eq(1) input").val();
    var mujeres = row.find("td:eq(2) input").val();
    var total = parseInt(hombres) + parseInt(mujeres);
    row.find("td:eq(3) input").val(total);
}

function calculateTableSituacionItl(id, item) {

    if (item != null) {
        var row = item.closest('tr');
        var nMujeres = row.find("td:eq(1) input").val();
        var dMujeres = row.find("td:eq(2) input").val();
        var nHombres = row.find("td:eq(5) input").val();
        var dHombres = row.find("td:eq(6) input").val();
        var nTotal = parseInt(nMujeres) + parseInt(nHombres);
        var dTotal = parseInt(dMujeres) + parseInt(dHombres);

        var pMujeres = calculatePercentage(dMujeres, dTotal);
        var pHombres = calculatePercentage(dHombres, dTotal);
        var pTotal = calculatePercentage(nTotal, nTotal);

        var mMujeres = (dMujeres / nMujeres).toFixed(0);
        var mHombres = (dHombres / nHombres).toFixed(0);
        var mTotal = (dTotal / nTotal).toFixed(0);

        row.find("td:eq(3)").html('<span>' +  pMujeres + '%</span>');
        row.find("td:eq(4)").html('<span>' +  (isFinite(mMujeres) ? mMujeres : '0') + '</span>');
        row.find("td:eq(7)").html('<span>' +  pHombres + '%</span>');
        row.find("td:eq(8)").html('<span>' +  (isFinite(mHombres) ? mHombres : '0') + '</span>');
        row.find("td:eq(9)").html('<span>' +  dTotal + '</span>');
        row.find("td:eq(10)").html('<span>' + pTotal + '%</span>');
        row.find("td:eq(11)").html('<span>' + mTotal + '</span>');
    }

    $("#tabla_" + id + " tbody tr.group-end").each(function(index) {

        var row = $(this);

        var idNMujeres = row.find("td:eq(1) input").attr("id");
        var idDMujeres = row.find("td:eq(2) input").attr("id");
        var idNHombres = row.find("td:eq(5) input").attr("id");
        var idDHombres = row.find("td:eq(6) input").attr("id");

        var totalNMujeres = 0;
        var totalDMujeres = 0;
        var totalNHombres = 0;
        var totalDHombres = 0;

        $('*[data-id="' + idNMujeres + '"]').each(function(){ totalNMujeres += parseInt($(this).val()); });
        $('*[data-id="' + idDMujeres + '"]').each(function(){ totalDMujeres += parseInt($(this).val()); });
        $('*[data-id="' + idNHombres + '"]').each(function(){ totalNHombres += parseInt($(this).val()); });
        $('*[data-id="' + idDHombres + '"]').each(function(){ totalDHombres += parseInt($(this).val()); });


        row.find("td:eq(1) input").val(totalNMujeres);
        row.find("td:eq(2) input").val(totalDMujeres);
        row.find("td:eq(5) input").val(totalNHombres);
        row.find("td:eq(6) input").val(totalDHombres);

        var totalDTotal = parseInt(totalDMujeres) + parseInt(totalDHombres);
        var totalNTotal = parseInt(totalNMujeres) + parseInt(totalNHombres);
        var totalPTotal = calculatePercentage(totalNTotal, totalNTotal);
        var totalMTotal = (totalDTotal / totalNTotal).toFixed(0);

        var mMujeres = (totalDMujeres / totalNMujeres).toFixed(0);
        var mHombres = (totalDHombres / totalNHombres).toFixed(0);

        row.find("td:eq(3)").html('<span>' +  calculatePercentage(totalDMujeres, totalDTotal) + '%</span>');
        row.find("td:eq(4)").html('<span>' +  (isFinite(mMujeres) ? mMujeres : '0') + '</span>');
        row.find("td:eq(7)").html('<span>' +  calculatePercentage(totalDHombres, totalDTotal) + '%</span>');
        row.find("td:eq(8)").html('<span>' +  (isFinite(mHombres) ? mHombres : '0') + '</span>');
        row.find("td:eq(9)").html('<span>' +  totalDTotal + '</span>');
        row.find("td:eq(10)").html('<span>' + totalPTotal + '%</span>');
        row.find("td:eq(11)").html('<span>' + totalMTotal + '</span>');

    });

}

function calculateTableFormacion(id) {

     $("#tabla_" + id + " tbody tr").each(function(index) {

        var row = $(this);
        var hMujeres = row.find("td:eq(1) input").val();
        var hHombres = row.find("td:eq(2) input").val();
        var nMujeres = row.find("td:eq(3) input").val();
        var nHombres = row.find("td:eq(4) input").val();
        var hTotal = parseInt(hMujeres) + parseInt(hHombres);
        var nTotal = parseInt(nMujeres) + parseInt(nHombres);

        var mMujeres = (hMujeres / nMujeres).toFixed(1);
        var mHombres = (hHombres / nHombres).toFixed(1);
        var mTotal = (hTotal / nTotal).toFixed(1);

        row.find("td:eq(5)").html('<span>' +  (isFinite(mMujeres) ? mMujeres : '0') + '</span>');
        row.find("td:eq(6)").html('<span>' +  (isFinite(mHombres) ? mHombres : '0') + '</span>');
        row.find("td:eq(7)").html('<span>' +  (isFinite(hTotal) ? hTotal : '0') + '</span>');
        row.find("td:eq(8)").html('<span>' +  (isFinite(mTotal) ? mTotal : '0') + '</span>');

     });

}

function calculateTablePromocion(id, totalMujeres, totalHombres) {

    $("#tabla_" + id + " tbody tr").each(function(index) {

        var row = $(this);
        var nMujeres = row.find("td:eq(2) input").val();
        var nHombres = row.find("td:eq(3) input").val();
        var nTotal = parseInt(totalMujeres) + parseInt(totalHombres);
        var pTotal = parseInt(nMujeres) + parseInt(nHombres);

        row.find("td:eq(1)").html('<span>' + (isFinite(pTotal) ? pTotal : '0') + '</span>');
        row.find("td:eq(4)").html('<span>' + calculatePercentage(nMujeres, nTotal)  + '%</span>');
        row.find("td:eq(5)").html('<span>' + calculatePercentage(nHombres, nTotal) + '%</span>');

    });

}

function calculateTableAuditoria(id, item) {

    var row = item.closest('tr');
    var hombres = parseInt(row.find("td:eq(1) input").val());
    var mujeres = parseInt(row.find("td:eq(2) input").val());
    var difference = calculatePercentage(hombres - mujeres, Math.max(hombres, mujeres));

    row.find("td:eq(3)").html('<span>' + difference + '%</span>');
}