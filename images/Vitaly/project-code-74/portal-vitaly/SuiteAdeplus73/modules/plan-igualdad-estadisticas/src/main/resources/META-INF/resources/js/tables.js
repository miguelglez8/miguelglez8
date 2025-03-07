function tableToJson(id) {
    var data = [];
    var headers = $("#tabla_" + id + " th");
    var rows = $("#tabla_" + id + " tbody tr").each(function(index) {
      data[index] = {};
      cells = $(this).find("td");
      cells.each(function(cellIndex) {
        var value = $(this).find("input").val();
        if (value === undefined) value = $(this).find("span").data("id");
        if (value === undefined) value = $(this).text();
        data[index][$(headers[cellIndex]).data('id')] = value;
      });
    });
    var json = {};
    json.data = data;
    return JSON.stringify(json);
}

function tableGroupToJson(id, group) {
    var data = [];
    var headers = $("#tabla_" + id + " th");
    var rows = $("#tabla_" + id + " tbody tr").not(".group").each(function(index) {
      data[index] = {};
      cells = $(this).find("td");
      cells.each(function(cellIndex) {
        if (cellIndex > 0 ) data[index][$(headers[cellIndex]).data('id')] = $(this).find("input").val();
        else {
            data[index][$(headers[cellIndex]).data('id')] = "" + $(this).find("span").data("id");
            data[index][$(headers[cellIndex]).data('group')] = "" + $(this).find("span").data(group);
        }
      });
    });
    var json = {};
    json.data = data;
    return JSON.stringify(json);
}

function tableKeyValueToJson(id, group) {
    var data = [];
    var headers = $("#tabla_" + id + " th.key");
    var rows = $("#tabla_" + id + " tbody tr").not(".group").each(function(index) {
      data[index] = {};
      cells = $(this).find("td.value");
      cells.each(function(cellIndex) {
        if (cellIndex == 0) {
            data[index][$(headers[cellIndex]).data('id')] = "" + $(this).find("span").data("id");
            data[index][$(headers[cellIndex]).data('group')] = "" + $(this).find("span").data(group);
        } else {
            data[index][$(headers[cellIndex]).data('id')] = $(this).find("input").val();
        }
      });
    });
    var json = {};
    json.data = data;
    return JSON.stringify(json);
}

function tableNotLastGroupToJson(id, group) {
    var data = [];
    var headers = $("#tabla_" + id + " th");
    var rows = $("#tabla_" + id + " tbody tr").not(".group").each(function(index) {
      data[index] = {};
      cells = $(this).find("td").not(':last');
      cells.each(function(cellIndex) {
        if (cellIndex > 0) data[index][$(headers[cellIndex]).data('id')] = $(this).find("input").val();
        else {
            data[index][$(headers[cellIndex]).data('id')] = "" + $(this).find("span").data("id");
            data[index][$(headers[cellIndex]).data('group')] = "" + $(this).find("span").data(group);
        }
      });
    });
    var json = {};
    json.data = data;
    return JSON.stringify(json);
}

function tableNotLastToJson(id) {
    var data = [];
    var headers = $("#tabla_" + id + " th");
    var rows = $("#tabla_" + id + " tbody tr").each(function(index) {
      data[index] = {};
      cells = $(this).find("td").not(':last');
      cells.each(function(cellIndex) {
        var value = $(this).find("input").val();
        if (value === undefined) value = $(this).find("span").data("id");
        if (value === undefined) value = $(this).text();
        data[index][$(headers[cellIndex]).data('id')] = value;
      });
    });
    var json = {};
    json.data = data;
    return JSON.stringify(json);
}

function tableObjectToJson(id, object) {
    var data = [];
    var headers = $("#tabla_" + id + " th");
    var rows = $("#tabla_" + id + " tbody tr").not(".group").each(function(index) {
      data[index] = object;
      cells = $(this).find("td");
      cells.each(function(cellIndex) {
        data[index][$(headers[cellIndex]).data('id')] = $(this).find("input").val();
      });
    });
    var json = {};
    json.data = data;
    return JSON.stringify(json);
}

function hideTotal() {
    $('.group-start').each(function() {
         if ( $(this).hasClass('collapsed') ) {
             var name = $(this).data('name');
             $('*[data-name="' + name  + '"]').not(this).addClass('hide');
         }
    });
}

function collapsedGroup(id) {
    $('#tabla_' + id + ' tbody').on('click', 'tr.group-start', function () {
        var name = $(this).data('name');
        var collapsed = $(this).hasClass('collapsed');
        var icon = $(this).find("i");

        if ( collapsed ) {
            $('#tabla_' + id + ' tr[data-name=' + name + ']').not(".group-start").removeClass("hide");
            $(this).removeClass("collapsed");
            icon.removeClass("icon-chevron-down").addClass("icon-chevron-up");
        } else {
            $('#tabla_' + id + ' tr[data-name=' + name + ']').not(".group-start").addClass("hide");
            $(this).addClass("collapsed");
            icon.removeClass("icon-chevron-up").addClass("icon-chevron-down");
        }
    });
}

function tableTotals(id){
	var rows = $("#tabla_" + id + " tbody tr").each(function(index) {
      cells = $(this).find("td");
      var haveTotal = false;
      cells.each(function(cellIndex) {
        if($(this).text()=='Total'){
        	haveTotal = true;
        }
      });
      
      if(haveTotal){
          $(this).removeClass('odd');
    	  $(this).addClass('group group-end');
      }
    });
}