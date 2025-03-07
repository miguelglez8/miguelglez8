var createField = function(id, section, type, field){

    switch (type) {
      case 'radio':
              createRadio(id, section, field);
              break;
      case 'checkbox':
              createCheckbox(id, section, field);
              break;
      case 'textarea':
              createTextarea(id, section, field);
              break;
      case 'table':
              //createTable(id, section, field);
              break;
      case 'matrix':
              createMatrix(id, section, field);
              break;
    }

};

var createTextarea = function(id, section, field) {

    var fieldJSON = JSON.parse(field);
    var timer;

    var textarea = document.createElement('textarea');
        textarea.id = 'textarea-' + id;
        textarea.name = id;
        textarea.maxLength = 5000;
        textarea.cols = 80;
        textarea.rows = 5;
        textarea.className = 'field form-control';
        textarea.required = isRequired(fieldJSON);

        textarea.addEventListener('keyup', function (event) {
            clearTimeout(timer);
            timer = setTimeout(function (event) {
                var cuestionario = document.getElementById('cuestionario-' + section);
                var responses = createSeccionJSON(cuestionario);
                saveCuestionario(responses, section);
            }, 500);
        });

    var label = document.createElement('label');
        label.className = 'control-label';
        label.innerHTML = fillLanguage(fieldJSON.label);

    var group = document.createElement('div');
        group.className = 'form-group col-md-12 col-12';
        group.appendChild(label);
        group.appendChild(textarea);

    var row = document.createElement('div');
        row.className = 'row align-items-start mb-4 ' + isRequired(fieldJSON);
        row.appendChild(group);

    var sectionDiv = document.getElementById('seccion-' + section);
        sectionDiv.appendChild(row);

}

var createRadio = function(id, section, field) {

    var fieldJSON = JSON.parse(field);
    var timer;

    var label = document.createElement('label');
        label.className = 'control-label';
        label.innerHTML = fillLanguage(fieldJSON.label);

    var legend = document.createElement('legend');
    	legend.className =isRequired(fieldJSON);
        legend.appendChild(label);

    var border = document.createElement('div');
        border.className = 'checksBorder';

    for (var key in fieldJSON.options) {

        var radio = document.createElement('input');
            radio.type = 'radio';
            radio.className = 'form-check-input mr-1';
            radio.name = id;
            radio.id = 'radio-' + id + fieldJSON.options[key].value;
            radio.value = fieldJSON.options[key].value;

            radio.addEventListener('click', function (event) {
                var cuestionario = document.getElementById('cuestionario-' + section);
                var responses = createSeccionJSON(cuestionario);
                saveCuestionario(responses, section);
            });

        var label = document.createElement('label');
            label.className = 'form-check-label';
            label.innerHTML = fillLanguage(fieldJSON.options[key].label);
            label.htmlFor = 'radio-' + id + fieldJSON.options[key].value;

        var control = document.createElement('div');
            control.className = 'custom-control custom-radio col-md-12 mr-4';
            control.appendChild(radio);
            control.appendChild(label);

        if (fieldJSON.options[key].other === 'true') {

            var input = document.createElement('input');
                input.type = 'text';
                input.className = 'form-check-input ml-2 rounded';
                input.dataset.other = 'radio-' + id + fieldJSON.options[key].value;

                input.addEventListener('keyup', function (event) {
                    clearTimeout(timer);
                    timer = setTimeout(function (event) {
                        var cuestionario = document.getElementById('cuestionario-' + section);
                        var responses = createSeccionJSON(cuestionario);
                        saveCuestionario(responses, section);
                    }, 500);
                });

                control.appendChild(input);

        }

        border.appendChild(control);
    }

    var fieldset = document.createElement('fieldset');
        fieldset.appendChild(legend);
        fieldset.appendChild(border);

    var group = document.createElement('div');
        group.className = 'form-group col-md-12 col-12';
        group.appendChild(fieldset);

    var row = document.createElement('div');
        row.className = ' row align-items-start mb-4 ' + isVisible(row, fieldJSON);
        row.id = id;
        row.appendChild(group);

    var sectionDiv = document.getElementById('seccion-' + section);
        sectionDiv.appendChild(row);

}

var createCheckbox = function(id, section, field) {

    var fieldJSON = JSON.parse(field);
    var timer;

    var label = document.createElement('label');
        label.className = 'control-label';
        label.innerHTML = fillLanguage(fieldJSON.label);

    var legend = document.createElement('legend');
        legend.appendChild(label);

    var border = document.createElement('div');
        border.className = 'checksBorder';

    for (var key in fieldJSON.options) {

        var check = document.createElement('input');
            check.type = 'checkbox';
            check.className = 'field check-app mr-1';
            check.name = id;
            check.id = 'checkbox-' + id + fieldJSON.options[key].value;
            check.value = fieldJSON.options[key].value;

            check.addEventListener('click', function (event) {
                var cuestionario = document.getElementById('cuestionario-' + section);
                var responses = createSeccionJSON(cuestionario);
                saveCuestionario(responses, section);
            });

        var span =  document.createElement('span');
            span.innerHTML = fillLanguage(fieldJSON.options[key].label);

        var option = document.createElement('label');
            option.htmlFor = 'checkbox-' + id + fieldJSON.options[key].value;
            option.appendChild(check);
            option.appendChild(span);

        var control = document.createElement('div');
            control.className = 'custom-control custom-radio col-md-12 mr-4';
            control.appendChild(option);
            border.appendChild(control);

        if (fieldJSON.options[key].other === 'true') {

            var input = document.createElement('input');
                input.type = 'text';
                input.className = 'form-check-input ml-2 rounded';
                input.dataset.other = 'checkbox-' + id + fieldJSON.options[key].value;

                input.addEventListener('keyup', function (event) {
                    clearTimeout(timer);
                    timer = setTimeout(function (event) {
                        var cuestionario = document.getElementById('cuestionario-' + section);
                        var responses = createSeccionJSON(cuestionario);
                        saveCuestionario(responses, section);
                    }, 500);
                });

                control.appendChild(input);

        }
    }

    var fieldset = document.createElement('fieldset');
        fieldset.appendChild(legend);
        fieldset.appendChild(border);

    var group = document.createElement('div');
        group.className = 'form-group col-md-12 col-12';
        group.appendChild(fieldset);

    var row = document.createElement('div');
        row.className = isRequired(fieldJSON) + ' row align-items-start mb-4 ' + isVisible(row, fieldJSON);
        row.id = id;
        row.appendChild(group);

    var sectionDiv = document.getElementById('seccion-' + section);
        sectionDiv.appendChild(row);

}

var createMatrix = function(id, section, field) {

    var fieldJSON = JSON.parse(field);

    var label = document.createElement('label');
        label.className = 'control-label';
        label.innerHTML = fillLanguage(fieldJSON.label);

    var tr = document.createElement('tr');
    for (var key in fieldJSON.headers) {

        var th = document.createElement('th');
        th.innerHTML = fillLanguage(fieldJSON.headers[key]);
        tr.appendChild(th);
    }

    var thead = document.createElement('thead');
    thead.appendChild(tr);

    var tbody = document.createElement('tbody');
    for (var key in fieldJSON.rows) {
        var tr = document.createElement('tr');

        for (var row in fieldJSON.rows[key]) {
            var td = document.createElement('td');

            if (fieldJSON.rows[key][row].type === "text") {
                td.innerHTML = fillLanguage(fieldJSON.rows[key][row].value);

            } else {

                var input = document.createElement('input')
                    input.type = 'checkbox';
                    input.name = 'matrix-' +  fieldJSON.rows[key][row].id;
                    input.id = 'checkbox-matrix-' + fieldJSON.rows[key][row].id + '1';
                    input.value= '1';

                    input.addEventListener('click', function (event) {
                        var cuestionario = document.getElementById('cuestionario-' + section);
                        var responses = createSeccionJSON(cuestionario);
                        saveCuestionario(responses, section);
                    });

                td.appendChild(input);
            }

            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    }

    var table = document.createElement('table');
        table.className = 'display dataTable no-footer dtr-inline';
        table.id = 'table_' + id;
        table.appendChild(thead);
        table.appendChild(tbody);

    var group = document.createElement('div');
        group.className = 'form-group col-md-12 col-12';
        group.appendChild(label);
        group.appendChild(table);

    var row = document.createElement('div');
        row.className = 'row align-items-start mb-4 ' + isRequired(fieldJSON);
        row.appendChild(group);

    var sectionDiv = document.getElementById('seccion-' + section);
        sectionDiv.appendChild(row);
}

var isRequired = function(field) {
    return field.required === 'true' ? 'required' : '';
}

var isVisible = function(element, field) {

    if(field.hasOwnProperty('visible')){

        document.querySelectorAll('input[name="' + field.visible.id + '"]').forEach(item => {
          item.addEventListener('click', event => {
                var value = document.querySelector('input[name="' + field.visible.id + '"]:checked').value;

                if (value == field.visible.value )
                    element.classList.remove("hide");
                else
                    element.classList.add("hide");

          });
        });

        element.dataset.visibleId = field.visible.id;
        element.dataset.visibleValue = field.visible.value;

        return 'hide';
    }
    return '';

}

var createSeccionJSON = function(section) {

    var array = $(section).serializeArray();
    var json = [];
    $.each(array, function () {

        var id = this.name
        var value = this.value
        var type = document.getElementsByName(this.name)[0].type;

        var item = {};
            item["name"] = id;
            item["value"] = value;
            item["type"] = type;

        var data = type + '-' + id + value;
        var other = $("div").find('[data-other="' + data + '"]');
        if (other.length > 0) {
            item["other"] = other.first().val();
        }

        json.push(item);
    });
    return json;

}

var fillField = function(section, fields) {

    var responses = JSON.parse(fields);
    for (var key in responses) {

        switch (responses[key].type) {
              case 'radio':
                      fillRadio(responses[key]);
                      break;
              case 'checkbox':
                      fillCheckbox(responses[key]);
                      break;
              case 'textarea':
                      fillTextarea(responses[key]);
                      break;
              case 'table':
                      //console.log('table');
                      break;
            }

    }

}

var fillTextarea = function(field) {
    $('#textarea-' + field.name).val(field.value);
}

var fillRadio = function(field) {

    var input = $('input:radio[name=' + field.name + ']');
        input
            .filter('[value=' + field.value + ']')
            .prop('checked', true);

    $('div').find('[data-visible-id=' + field.name + ']').each(function() {

        var name = $(this).data('visible-id');
        var value = $(this).data('visible-value');

        if( value ==  $('input[name=' + name + ']:checked').val() ) {
            $(this).removeClass('hide');
        }

    });

    fillOther(field);

}

var fillCheckbox = function(field) {
    $('input:checkbox[id=checkbox-' + field.name + field.value + ']')
        .prop('checked', true);

    fillOther(field);
}

var fillOther = function(field) {

    var data = field.type + '-' + field.name + field.value;
    var other = $("div").find('[data-other=' + data + ']');
    if (other.length > 0) {
        other.first().val(field.other);
    }

}

var fillLanguage = function(label) {
    if (language[label]) return language[label];
    else                 return label;
}

var fillEstado = function(section, estado) {
    if (estado == '0') $('#heading' + section).removeClass('prv-not-empty');
    else               $('#heading' + section).addClass('prv-not-empty');
}