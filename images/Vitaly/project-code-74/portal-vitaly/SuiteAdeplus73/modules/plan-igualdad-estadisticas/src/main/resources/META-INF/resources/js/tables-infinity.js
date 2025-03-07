var addRowButtons = document.querySelectorAll('.buttons-addRow');
var modalEdit = document.getElementById('rowEditModal');
var modalDelete = document.getElementById('rowDeleteModal');
var editRows = document.querySelectorAll('.prv-edit-row');
var deleteRows = document.querySelectorAll('.prv-remove-row');
var saveModals = document.querySelectorAll('.prv-save-modal');
var deleteModals = document.querySelectorAll('.prv-delete-modal');
var hideModal = document.querySelectorAll('.hide-modal');

var hideModalFunction = function(){
    if (hideModal.length > 0){
        for (const arb of hideModal) {
            arb.addEventListener('click', function(){
                modalEdit.classList.add("hide");
                modalDelete.classList.add("hide");
            });
        }
    }
};

var deleteRowsFunction = function(){
    if (deleteRows.length > 0){
        for (const arb of deleteRows) {
            arb.addEventListener('click', function(){
                modalDelete.setAttribute('data-type', 'remove');
                modalDelete.setAttribute('data-row', arb.closest("tr").rowIndex);

                var tableId = arb.getAttribute('aria-controls');
                var table = document.getElementById(tableId);
                if(table == null){
                    table = arb.closest("table");
                }

                var title = table.getAttribute('data-delete-title');
                var header = document.getElementById('delete-modal-title');
                header.innerHTML = title;

                modalDelete.setAttribute('data-table', table.getAttribute('id'));
                modalDelete.classList.remove('hide');
            });
        }
    }
};

var deleteModalsFunction = function(){
    if (deleteModals.length > 0){
        for (const sm of deleteModals) {
            sm.addEventListener('click', function(){
                var tableId = modalDelete.getAttribute('data-table');
                var tableElement = document.getElementById(tableId);

                if(modalDelete.getAttribute('data-type') == 'remove'){

                    var tableRowNumber = modalDelete.getAttribute('data-row');
                    var tableRows = tableElement.querySelectorAll('tr');
                    var tableRow = tableRows[tableRowNumber];

                    $('#' + tableId).DataTable().row(tableRow).remove().draw();

                    var id = "canvas_" + tableRow.cells[0].innerHTML;
                    var element = document.getElementById(id);
                        element.parentNode.remove();

                }
                modalDelete.classList.add('hide');
                saveOcupacionSector('0');
            });
        }
    }
};

var constructModalForm = function(arb){
    var tableId = arb.getAttribute('aria-controls');
    var table = document.getElementById(tableId);
    var rowTexts = "";
    if(table == null){
        table = arb.closest("table");
        rowTexts = arb.closest("tr").querySelectorAll('td:not(:last-child)');
    }
    modalEdit.setAttribute('data-table', table.getAttribute('id'));

    var title = table.getAttribute('data-edit-title');
    var header = document.getElementById('edit-modal-title');
    header.innerHTML = title;

    var tableTitles = table.querySelectorAll('th:not(:last-child)');
    modalEdit.classList.remove('hide');

    var prvForm = modalEdit.querySelector('.prv-form');
    var x = 0;
    prvForm.innerHTML = "";
    for (const tt of tableTitles) {
        var inputValue = "";
        var inputRequiered = "";
        var required=";"
        if(modalEdit.getAttribute('data-type') == 'edit'){
            inputValue = rowTexts[x].textContent;
        }

        if(tt.getAttribute('data-requiered') == 'true'){
            var inputRequiered = "requiered";
            required = "requiered";
        }

        var inputType = "form-control";
        var disabled = "";
        var type = "search";
        if (tt.getAttribute('data-id') == 'hombres' || tt.getAttribute('data-id') == 'mujeres') {
            var inputType = "ocupacion-field form-control";
            var type = "number";
        }
        if (tt.getAttribute('data-id') == 'total') {
            var inputType = "ocupacion-total form-control";
            disabled = "disabled";
            var type = "number";
        }

        prvForm.innerHTML = prvForm.innerHTML + '<div class="row align-items-start mb-4">' +
                '<div class="col-12">' +
                '<label for="tableTitleForm-' + x + '" class="' + inputRequiered + '">' +
                    tt.textContent +
                '</label>' +
                '<input min=0 ' + disabled +' type="' + type + '" class="' + inputType + ' ' + inputRequiered + ' " '  +
                        required + ' value="' + inputValue + '" id="tableTitleForm-' + x + '">' +
            '</div>' +
        '</div>';

        x++;
    }

    $('.ocupacion-field').on('input', function() {
        if(this.value < 0 || this.value == '')    this.value='';

        var sum = 0;
        $('.ocupacion-field').each(function(){
            sum += parseInt(this.value);
        });
        $('.ocupacion-total').first().val(sum);
    });
};

var editRowsFunction = function(){
    if (editRows.length > 0){
        for (const arb of editRows) {
            arb.addEventListener('click', function(){
                modalEdit.setAttribute('data-type', 'edit');
                modalEdit.setAttribute('data-row', arb.closest("tr").rowIndex);
                constructModalForm(arb);
            });
        }
    }
};

var addRowsFunction = function(){
    if (addRowButtons.length > 0){
        for (const arb of addRowButtons) {
            arb.addEventListener('click', function(){
                modalEdit.setAttribute('data-type', 'add');
                modalEdit.removeAttribute('data-row');
                constructModalForm(arb);
            });
        }
    }
};

var saveModalsFunction = function() {
    if (saveModals.length > 0){
        for (const sm of saveModals) {
            sm.addEventListener('click', function(){
                var inputValues = modalEdit.querySelector('.modal-body').querySelectorAll('input');
                var tableId = modalEdit.getAttribute('data-table');
                var tableElement = document.getElementById(tableId);
                var validationOK = true;

                for (const iv of inputValues) {
                    if(iv.getAttribute('requiered') !== null){
                        if(iv.value == ""){
                            validationOK = false;
                            iv.classList.add('validation-error');
                        }
                    }
                }

                if(validationOK){
                    if(modalEdit.getAttribute('data-type') == 'edit'){
                        var tableRowNumber = modalEdit.getAttribute('data-row');
                        var tableRows = tableElement.querySelectorAll('tr');
                        var tableRowsTd = tableRows[tableRowNumber].querySelectorAll('td');
                        var tableRowChart = tableRows[tableRowNumber].getAttribute("data-chart");
                        var x = 0;

                        for (const iv of inputValues) {
                            tableRowsTd[x].innerHTML = iv.value
                            x++;
                        }

                        $('#' + tableId).DataTable().draw();
                        createOcupacionSectorChart(tableRowChart, inputValues[0].value, [parseInt(inputValues[1].value), parseInt(inputValues[2].value)]);
                    }

                    else{
                        var data = {};
                            data["cnae"] = inputValues[0].value;
                            data["hombres"] = inputValues[1].value;
                            data["mujeres"] = inputValues[2].value;
                            data["total"] = inputValues[3].value;

                        $('#' + tableId).DataTable().row.add(data).draw();
                        createOcupacionSectorChart(inputValues[0].value, inputValues[0].value, [parseInt(inputValues[1].value), parseInt(inputValues[2].value)]);

                        editRows = document.querySelectorAll('.prv-edit-row');
                        deleteRows = document.querySelectorAll('.prv-remove-row');

                        editRowsFunction();
                        deleteRowsFunction();
                    }


                    modalEdit.classList.add('hide');
                    saveOcupacionSector('0');

                }else{
                    if(!document.querySelector('.validationErrorText')){
                        var modalBody = modalEdit.querySelector('.prv-form');
                        const node = document.createElement("div");
                        const textnode = document.createTextNode("Datos vacios o no válidos");
                        node.appendChild(textnode);
                        node.classList.add('validationErrorText');
                        modalBody.appendChild(node);
                    }
                }

            });
        }
    }
};