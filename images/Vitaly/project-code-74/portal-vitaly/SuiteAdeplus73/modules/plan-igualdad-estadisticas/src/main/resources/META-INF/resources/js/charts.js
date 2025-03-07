function barChartConfig(data, title, label, stacked, hasLegend) {
    return {
               type: 'bar',
               data: data,
               options: {
                   responsive: true,
                   legend: {
                       display: hasLegend
                   },
                   title: {
                       display: true,
                       text: title
                   },
                   scales: {
                       yAxes: [{ticks:{beginAtZero:true}, stacked: stacked}],
                       xAxes: [{ticks:{beginAtZero:true}, stacked : stacked}]
                   },
                   plugins:{
                       datalabels: {
                           color: '#ffffff',
                           display: true,
                           formatter: function(value, context) {
                               return value + label;
                           }
                       }
                   }
               }
           };
}

function horizontalBarChartConfig(data, totals, title, label, hasTitle, hasLegend) {
    return {
        type: 'horizontalBar',
        data: data,
        options: {
        	maintainAspectRatio: false,
            responsive: true,
            legend: {
                display: hasLegend
            },
            title: {
                display: hasTitle,
                text: title
            },
            scales: {
                xAxes: [{ticks:{beginAtZero:true}}]
            },
            plugins:{
                datalabels: {
                color: '#ffffff',
                display: true,
                formatter: function(value, context) {
                       if (totals != "") return calculatePercentage(value, totals[context.dataIndex]) + label;
                       return value + label;
                   }
                }
            }
        }
    };
}

function horizontalGroupBarChartConfig(data, labels, label, title) {
    return  {
         type: "horizontalBar",
         data: data,
         options: {
           scales: {
             yAxes: [
                {labels: labels, weight: 1 },
                {id: 'yAxis1', type: 'category', offset: true, weight: 0}
             ]
           },
           responsive: true,
           title: {
               display: true,
               text: title
           },
           plugins:{
             datalabels: {
                 color: '#ffffff',
                 display: true,
                 formatter: function(value, context) {
                    if (value === "0") return "";
                    return value + label;
                 }
             }
           }
         }
   };
}

function pieChartConfig(data, title, label, total) {
    return {
       type: 'pie',
       data: data,
       options: {
           responsive: true,
           title: {
               display: true,
               text: title
           },
           plugins:{
               datalabels: {
                   color: '#ffffff',
                   display: true,
                   formatter: function(value, context) {
                       var percentage = ((value * 100) / total).toFixed(0);
                       if (percentage < 3) return "";
                       return percentage + '%';
                   }
               }
           }
       }
  };
}

function lineChartConfig(data, title) {
    return {
        type: 'line',
        data: data,
        options: {
            responsive: true,
            title: {
               display: true,
               text: title
            },
            plugins:{
               datalabels: {
                   display: false
               }
            },
            scales: {
               yAxes: [{ticks: { max: 100, min: 0}}]
            }
        }
   };
}

function createChart(id, config) {

    var chart = Object.values(Chart.instances).filter((c) => c.canvas.id == 'canvas_' + id).pop();
    if (chart != undefined) {
        chart.destroy();
    }

    var canvas = document.createElement('canvas');
        canvas.id = "canvas_" + id;

    var row = document.createElement('div');
        row.className = 'col-md-6';
        row.appendChild(canvas);

    var charts = document.getElementById('charts');
        charts.appendChild(row);

    Chart.defaults.global.defaultFontSize = 16;
    var ctx = document.getElementById("canvas_" + id).getContext("2d");
    var chart = new Chart(ctx, config);

}

function updateChart(id, config, data) {

    var chart = Object.values(Chart.instances).filter((c) => c.canvas.id == 'estadistica_' + id).pop();
    if (chart != undefined) {
        chart.destroy();
    }

    Chart.defaults.global.defaultFontSize = 16;
    var ctx = document.getElementById("estadistica_" + id).getContext("2d");
    new Chart(ctx, config);

}

function createIngresosChart(id, labels, labelHombres, labelMujeres, colorHombres, colorMujeres, title) {

    var table = tableGroupToJson(id, "tipo");
    var json = JSON.parse(table);

    var pHombres = [];
    var pMujeres = [];

    (json.data).forEach(function (item, index) {
        var total = $('#tabla_' + id + ' #total_' + item.tipo).val();

        pHombres.push(calculatePercentage(item.hombres, total));
        pMujeres.push(calculatePercentage(item.mujeres, total));
    });

    const data = {
      labels: labels,
      datasets: [
        {
          label: labelHombres,
          data: pHombres,
          backgroundColor: colorHombres
        },
        {
          label: labelMujeres,
          data: pMujeres,
          backgroundColor: colorMujeres
        }
      ]
    };

    const config = barChartConfig(data, title, "%", false, true);
    updateChart(id, config, data);
}

function createPlantillaChart(id, data, labelHombres, labelMujeres, colorHombres, colorMujeres, title) {

    var hombres = data[0].personas;
    var mujeres = data[1].personas;
    var total = [hombres, mujeres].reduce((sum, a) => sum + a, 0);

    var result = [];
    result.push(hombres);
    result.push(mujeres);

    var data = {
        datasets: [{
            data: result,
            backgroundColor: [colorHombres, colorMujeres]
        }],
        labels: [labelHombres, labelMujeres]
    };
    var config = pieChartConfig(data, title, "%", total);
    var chart = new Chart("estadistica_" + id, config);

}

function createEdadChart(id, labelHombres, labelMujeres, colorHombres, colorMujeres, title) {

     var table = tableNotLastToJson(id);
     var json = JSON.parse(table);

     var hombres = [];
     var mujeres = [];

     for (var element of json.data) {
        hombres.push(element.hombres);
        mujeres.push(element.mujeres);
     }

     const data = {
      labels: ["0-20", "20-25", "25-30", "30-35", "35-40", "40-45", "45-50", "50-55", "55-60", "60 >"],
      datasets: [
        {
          label: labelHombres,
          data: hombres,
          borderColor: colorHombres,
          fill: false,
          lineTension: 0,
        },
        {
          label: labelMujeres,
          data: mujeres,
          borderColor: colorMujeres,
          fill: false,
          lineTension: 0,
        }
      ]
    };

    var config = lineChartConfig(data, title);
    updateChart(id, config, data);

}

function createPuestoEdadChart(id, labelHombres, labelMujeres, colorHombres, colorMujeres, title) {

    var table = tableGroupToJson(id, "puesto");
    var json = JSON.parse(table);

    var results = calculateTablePuestoEdad(json.data);

    const data = {
      labels: results.labels,
      datasets: [
        {
          label: labelHombres,
          data: results.nHombres,
          backgroundColor: colorHombres,
          barPercentage:1,
          categoryPercentage:1
        },
        {
          label: labelMujeres,
          data: results.nMujeres,
          backgroundColor: colorMujeres,
          barPercentage:1,
          categoryPercentage:1
        }
      ]
    };

    const config = horizontalBarChartConfig(data, results.nTotales, title, "%", true, true);
    updateChart(id, config, data);

}

function createReduccionJornadaChart(id, labelsHombres, labelsMujeres, labelsTotales,colorHombres, colorMujeres, totalMujeres, totalHombres, totalPersonas, titleMujeres, titleHombres, titleTotales) {

    var table = tableNotLastToJson(id)
    var json = JSON.parse(table);

    var hombresConReduccion = (json.data).map(item => item.hombres).reduce((prev, curr) => prev + parseInt(curr), 0);
    var mujeresConReduccion = (json.data).map(item => item.mujeres).reduce((prev, curr) => prev + parseInt(curr), 0);

    var hombresSinReduccion = parseInt(totalHombres) - hombresConReduccion;
    var mujeresSinReduccion = parseInt(totalMujeres) - mujeresConReduccion;
    
    var plantillaConReduccion = hombresConReduccion + mujeresConReduccion;
    var plantillaSinReduccion = totalPersonas - plantillaConReduccion;

    var dataMujeres = {
          datasets: [{
              data: [mujeresSinReduccion, mujeresConReduccion],
              backgroundColor: [colorHombres, colorMujeres]
          }],
          labels: labelsMujeres
    };

    var dataHombres = {
          datasets: [{
              data: [hombresSinReduccion, hombresConReduccion],
              backgroundColor: [colorHombres, colorMujeres]
          }],
          labels: labelsHombres
    };
    
    var dataTotales = {
        datasets: [{
            data: [plantillaSinReduccion, plantillaConReduccion],
            backgroundColor: [colorHombres, colorMujeres]
        }],
        labels: labelsTotales
    };

    const configMujeres = pieChartConfig(dataMujeres, titleMujeres, "%", totalMujeres);
    const configHombres = pieChartConfig(dataHombres, titleHombres, "%", totalHombres);
    const configPlantillas = pieChartConfig(dataTotales, titleTotales, "%", totalPersonas);

    updateChart(id + "_mujeres", configMujeres, dataMujeres);
    updateChart(id + "_hombres", configHombres, dataHombres);
    updateChart(id + "_plantillas", configPlantillas, dataTotales);
}

function createContratoJornadaChart(id, dataLabels, configLabels, labelHombres, labelMujeres, colorHombres, colorMujeres, title) {

    var table = tableGroupToJson(id, "jornada");
    var json = JSON.parse(table);

    var pHombres = [];
    var pMujeres = [];

    (json.data).forEach(function (item, index) {
        var total = parseInt(item.hombres) + parseInt(item.mujeres);
        var pHombre = (100 * parseInt(item.hombres)) / total;
        var pMujer = (100 * parseInt(item.mujeres)) / total;

        pHombres.push(pHombre.toFixed(0));
        pMujeres.push(pMujer.toFixed(0));
    });

    var barChartData = {
      labels: dataLabels,
      datasets: [{
          label: labelHombres,
          yAxisID: 'yAxis1',
          backgroundColor: colorHombres,
          stack: "stack0",
          data: pHombres
        },
        {
          label: labelMujeres,
          yAxisID: 'yAxis1',
          backgroundColor: colorMujeres,
          stack: "stack0",
          data: pMujeres
        }
      ]
    };

    var config = horizontalGroupBarChartConfig(barChartData, configLabels, "%", title);
    updateChart(id, config, barChartData);

}

function createFormacionPieChart(id, labels, title, totalMujeres, totalHombres) {

    var row = $("#tabla_" + id + " tbody tr").first();
    var nMujeresFormadas = row.find("td:eq(3) input").val();
    var nHombresFormados = row.find("td:eq(4) input").val();
    var nMujeresNoFormadas = parseInt(totalMujeres) - parseInt(nMujeresFormadas);
    var nHombresNoFormados = parseInt(totalHombres) - parseInt(nHombresFormados);
    var total = parseInt(totalMujeres) + parseInt(totalHombres);

    const data = {
         datasets: [{
             data: [nMujeresFormadas, nMujeresNoFormadas, nHombresFormados, nHombresNoFormados],
             backgroundColor: ['#DE9733', '#9E6008', '#1B4F72', '#7FB3D5']
         }],
         labels: labels
    };
    const config = pieChartConfig(data, title, "%", total);
    updateChart(id + "_formacion", config, data);

}

function createFormacionBarChart(id, labelHombres, labelMujeres, labelTotal, colorHombres, colorMujeres, colorTotal, title) {

    var row = $("#tabla_" + id + " tbody tr").first();
    var mMujeres = row.find("td:eq(5)").text();
    var mHombres = row.find("td:eq(6)").text();
    var mTotal = row.find("td:eq(8)").text();

    const data = {
    	datasets: [{
            data: [mMujeres, mHombres, mTotal],
            backgroundColor: [colorMujeres, colorHombres, colorTotal]
         }],
          labels: [labelMujeres, labelHombres, labelTotal]
    };
    const config = barChartConfig(data, title, "", false, false);

    var chart = Object.values(Chart.instances).filter((c) => c.canvas.id == 'estadistica_' + id + '_duracion').pop();

    if (chart == undefined) {
        var ctx = document.getElementById("estadistica_" + id + "_duracion").getContext("2d");
        new Chart(ctx, config);
    } else {
        var chart = Object.values(Chart.instances).filter((c) => c.canvas.id == 'estadistica_' + id + '_duracion').pop();
            chart.data.datasets[0].data[0] = mMujeres;
            chart.data.datasets[0].data[1] = mHombres;
            chart.data.datasets[0].data[2] = mTotal;
            chart.update();
    }

}

function createPromocionChart(id, labelsPromocion, labelsPersonas, colorsPromocion, colorsPersonas, totalMujeres, totalHombres, titlePromocion, titlePersonas) {

    var row = $("#tabla_" + id + " tbody tr").first();
    var nMujeres = row.find("td:eq(2) input").val();
    var nHombres = row.find("td:eq(3) input").val();
    var nTotal = parseInt(totalMujeres) + parseInt(totalHombres);
    var pTotal = parseInt(nMujeres) + parseInt(nHombres);

    const dataPromocion = {
         datasets: [{
             data: [nMujeres, nHombres, (nTotal - pTotal)],
             backgroundColor: colorsPromocion
         }],
         labels: labelsPromocion
    };

    const dataPersonas = {
         datasets: [{
             data: [nMujeres, nHombres],
             backgroundColor: colorsPersonas
         }],
         labels: labelsPersonas
    };

    const configPromocion = pieChartConfig(dataPromocion, titlePromocion, "%", nTotal);
    const configPersonas = pieChartConfig(dataPersonas, titlePersonas, "%", pTotal);

    updateChart(id + "_promocion", configPromocion, dataPromocion);
    updateChart(id + "_personas", configPersonas, dataPersonas);
}

function createPuntuacionBarChart(id, colorM, colorF, colorE) {

    var agrupaciones = JSON.parse( tableToJson(id + "_agrupaciones") );

    var labels = [];
    var puntos = [];
    var backgroundColors = [];

    var masculinizado = [];
    var feminizado = [];
    var equilibrado = [];

    (agrupaciones.data).forEach(function (item, index) {
        if (puestos != "") labels.push( puestos.find(el => el.id === item.puesto + "").nombre );
        puntos.push(item.puntos);

        if ( item.categoria == "M" ) backgroundColors.push(colorM);
        if ( item.categoria == "F" ) backgroundColors.push(colorF);
        if ( item.categoria == "E" ) backgroundColors.push(colorE);
    });

    const data = {
        labels: labels,
        datasets: [{
            data: puntos,
            backgroundColor: backgroundColors
        }]
    };
    const config =  horizontalBarChartConfig(data, "", "", "", false, false);
    updateChart(id + "_puntuacion", config, data);

}

function createAgrupacionesBarChart(id, labelHombres, labelMujeres, colorHombres, colorMujeres, title) {

    var agrupaciones = JSON.parse( tableToJson(id + "_agrupaciones") );

    var labels = [];
    var mujeres = [];
    var hombres = [];

    var result = [];
    (agrupaciones.data).reduce(function(res, value) {
      if (!res[value.agrupacion]) {
        res[value.agrupacion] = { agrupacion: value.agrupacion, nHombres: 0,  nMujeres: 0};
        result.push(res[value.agrupacion])
      }
      res[value.agrupacion].nHombres += parseInt(value.nHombres);
      res[value.agrupacion].nMujeres += parseInt(value.nMujeres);
      return res;
    }, {});

    result.forEach(function (item, index) {
        var label = [];
            label.push(item.agrupacion);
            label.push(getCategoriaLabel( calculateAnalysis(item.nHombres, item.nMujeres) ));
        labels.push(label);

        if(item.nMujeres>0){
        	mujeres.push(item.nMujeres);
        }else{
        	mujeres.push(" ");
        }
        
        if(item.nHombres>0){
        	hombres.push(item.nHombres);
        }else{
        	hombres.push(" ");
        }
    });

    const data = {
       labels: labels,
       datasets: [
         {
           label: labelMujeres,
           backgroundColor: colorMujeres,
           data: mujeres
         },
         {
           label: labelHombres,
           backgroundColor: colorHombres,
           data: hombres
         }
       ]
    };
    const config = barChartConfig(data, title, "", true, true);
    updateChart(id + "_agrupaciones", config, data);

}