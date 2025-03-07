<%@ include file="init.jsp" %>

<aui:input name="<%= id %>" value="" type="hidden" class="datatable-tag" />

<div id="table_<%= id %>_wrapper" class="dataTables_wrapper no-footer">
    <div class="capaDatosTabla">
        <div class="dt-info">
            <label>
                <liferay-ui:message key="<%= title %>" />
                <span class="contract-info text-secondary" data-toggle="tooltip" title="<liferay-ui:message key="<%= tooltip %>" />">
                    <i class="icon-info-sign"></i>
                </span>
            </label>
        </div>
        <div class="dt-buttons">
            <button class="dt-button buttons-addRow" aria-controls="table_<%= id %>" type="button">
                <span><liferay-ui:message key="organizacion.tabla.annadir" /></span>
            </button>
        </div>
    </div>
    <table id="table_<%= id %>" class="display dataTable no-footer dtr-inline"
           style="width: 100%;" aria-describedby="table_<%= id %>_info" role="grid"
           data-delete-title="<liferay-ui:message key="<%= deleteTitle %>"/>"
           data-edit-title="<liferay-ui:message key="<%= editTitle %>"/>"
    >
        <thead>
            <tr role="row">
                <th data-id="puesto" data-requiered="true"><liferay-ui:message key="organizacion.tabla.puesto"/></th>
                <th data-id="nombre" data-requiered="true"><liferay-ui:message key="organizacion.tabla.nombre"/></th>
                <th data-id="apellidos" data-requiered="true"><liferay-ui:message key="organizacion.tabla.apellidos"/></th>
                <th data-id="email"><liferay-ui:message key="organizacion.tabla.email"/></th>
                <th data-id="nif"><liferay-ui:message key="organizacion.tabla.nif"/></th>
                <th aria-label="" class="no-sort"></th>
            </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>

<div class="modal show hide" id="rowEditModal">
    <div class="modal-backdrop show"></div>
    <div class="modal-dialog center">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="edit-modal-title"></h5>
                <button type="button" class="close hide-modal">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="prv-form"></div>
            </div>
            <div class="modal-footer">
                <button class="btn btn btn-outline-primary mr-4 hide-modal btn-primary" type="button">
                    <span class="lfr-btn-label"><liferay-ui:message key="organizacion.cancelar" /></span>
                </button>
                <button class="btn btn btn-primary btn-primary prv-save-modal" type="button">
                    <span class="lfr-btn-label"><liferay-ui:message key="organizacion.guardar" /></span>
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal show hide" id="rowDeleteModal">
    <div class="modal-backdrop show"></div>
    <div class="modal-dialog center">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="delete-modal-title"></h5>
                <button type="button" class="close hide-modal">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="prv-form">
                    <p><liferay-ui:message key="organizacion.tabla.confirmar"/></p>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn btn-outline-primary mr-4 hide-modal btn-primary" type="button">
                    <span class="lfr-btn-label">Cancelar</span>
                </button>
                <button class="btn btn btn-primary btn-primary prv-delete-modal" type="button">
                    <span class="lfr-btn-label">Aceptar</span>
                </button>
            </div>
        </div>
    </div>
</div>

<script>

    var json = JSON.parse('<%= Validator.isNotNull(value) ? value : "{}"  %>');
    for (var key in json.data) {
        if (Object.keys( json.data[key] ).length != 0) {
            var row =   '<tr>' +
                            '<td>' + json.data[key].puesto + '</td>' +
                            '<td>' + json.data[key].nombre + '</td>' +
                            '<td>' + json.data[key].apellidos + '</td>' +
                            '<td>' + json.data[key].email + '</td>' +
                            '<td>' + json.data[key].nif + '</td>' +
                            '<td>' +
                                '<div class="d-flex justify-content-end">' +
                                    '<a href="javascript:void(0)" class="ico-acciones-tabla prv-edit-row"><span class="icon-pencil"></span></a>' +
                                    '<a href="javascript:void(0)" class="ico-acciones-tabla prv-remove-row"><span class="icon-trash"></span></a>' +
                                '</div>' +
                            '</td>' +
                        '</tr>';
            $("#table_<%= id %>").find('tbody').append(row);
        }
    }

    $('form[name="<portlet:namespace /><%= form %>"]').on('submit', function(){
        var tabla = tableToJson("table_<%= id %>");
        $("#<portlet:namespace/><%= id %>").val(tabla);

        var bodyElement = document.querySelector('body');
        bodyElement.removeAttribute('data-tables');
    });

    $('li').click(function() {
        var bodyElement = document.querySelector('body');
        bodyElement.removeAttribute('data-tables');
    });

    $(document).ready( function () {
        $('#table_<%= id %>').DataTable({
            dom: '',
            columnDefs: [
               { orderable: false, targets: -1 }
            ],
            "language": {
                "sProcessing":     "<liferay-ui:message key="planigualdad.view.datatable.sProcessing"/>",
                "sLengthMenu":     "<liferay-ui:message key="planigualdad.view.datatable.sLengthMenu"/>",
                "sZeroRecords":    "<liferay-ui:message key="planigualdad.view.datatable.sZeroRecords"/>",
                "sEmptyTable":     "<liferay-ui:message key="planigualdad.view.datatable.sEmptyTable"/>",
                "sInfo":           "<liferay-ui:message key="planigualdad.view.datatable.sInfo"/>",
                "sInfoEmpty":      "<liferay-ui:message key="planigualdad.view.datatable.sInfoEmpty"/>",
                "sInfoFiltered":   "<liferay-ui:message key="planigualdad.view.datatable.sInfoFiltered"/>",
                "sInfoPostFix":    "<liferay-ui:message key="planigualdad.view.datatable.sInfoPostFix"/>",
                "sSearch":         "<liferay-ui:message key="planigualdad.view.datatable.sSearch"/>",
                "sUrl":            "<liferay-ui:message key="planigualdad.view.datatable.sUrl"/>",
                "sInfoThousands":  "<liferay-ui:message key="planigualdad.view.datatable.sInfoThousands"/>",
                "sLoadingRecords": "<liferay-ui:message key="planigualdad.view.datatable.sLoadingRecords"/>",
                "oPaginate": {
                    "sFirst":    "<liferay-ui:message key="planigualdad.view.datatable.sFirst"/>",
                    "sLast":     "<liferay-ui:message key="planigualdad.view.datatable.sLast"/>",
                    "sNext":     "<liferay-ui:message key="planigualdad.view.datatable.sNext"/>",
                    "sPrevious": "<liferay-ui:message key="planigualdad.view.datatable.sPrevious"/>"
                },
                "oAria": {
                    "sSortAscending":  "<liferay-ui:message key="planigualdad.view.datatable.sSortAscending"/>",
                    "sSortDescending": "<liferay-ui:message key="planigualdad.view.datatable.sSortDescending"/>"
                },
                "buttons": {
                    "copy": "<liferay-ui:message key="planigualdad.view.datatable.copy"/>",
                    "colvis": "<liferay-ui:message key="planigualdad.view.datatable.colvis"/>"
                }
            }
        });
    });

    var addRowButtons = document.querySelectorAll('.buttons-addRow');
    var modalEdit = document.getElementById('rowEditModal');
    var modalDelete = document.getElementById('rowDeleteModal');
    var editRows = document.querySelectorAll('.prv-edit-row');
    var deleteRows = document.querySelectorAll('.prv-remove-row');
    var saveModals = document.querySelectorAll('.prv-save-modal');
    var deleteModals = document.querySelectorAll('.prv-delete-modal');
    var hideModal = document.querySelectorAll('.hide-modal');
    var bodyElement = document.querySelector('body');

    if (addRowButtons.length > 0 && bodyElement.getAttribute('data-tables') == null) {

        var hideModalFunction = function(){
            if (hideModal.length > 0){
                for (const arb of hideModal) {
                    arb.addEventListener('click', function(){
                        modalEdit.classList.add("hide");
                        modalDelete.classList.add("hide");
                        document.querySelectorAll(".prv-form .row").forEach(el => el.remove());
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

                        }
                        modalDelete.classList.add('hide');
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
                var inputPattern = "";

                if(modalEdit.getAttribute('data-type') == 'edit'){
                    //hau ez da balio ona
                    inputValue = rowTexts[x].textContent;
                }

                if(tt.getAttribute('data-requiered') == 'true'){
                    //hau ez da balio ona
                    var inputRequiered = "requiered";
                }

                if (tt.getAttribute('data-id') == 'email') {
                    inputPattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
                }

                prvForm.innerHTML = prvForm.innerHTML + '<div class="row align-items-start mb-4">' +
                        '<div class="col-12">' +
                        '<label for="tableTitleForm-' + x + '" class="' + inputRequiered + '">' +
                            tt.textContent +
                        '</label>' +
                        '<input type="search" pattern="' + inputPattern + '" class="" ' + inputRequiered + ' value="' + inputValue + '"' +
                            ' placeholder="" aria-controls="table_company" id="tableTitleForm-' + x + '">' +
                    '</div>' +
                '</div>';

                x++;
            }
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
                                if (!new RegExp(iv.pattern).test(iv.value)) {
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
                                var x = 0;

                                for (const iv of inputValues) {
                                    tableRowsTd[x].innerHTML = iv.value
                                    x++;
                                }
                            }

                            else{
                                var data = [];

                                for (const iv of inputValues) {
                                    data.push(iv.value);
                                }

                                var actions = '<div class="d-flex justify-content-end">' +
                                              '<a href="javascript:void(0)" class="ico-acciones-tabla prv-edit-row">' +
                                              '<span class="icon-pencil"></span>' +
                                              '</a>' +
                                              '<a href="javascript:void(0)" class="ico-acciones-tabla prv-remove-row">' +
                                              '<span class="icon-trash"></span>' +
                                              '</a>' +
                                              '</div>';
                                data.push(actions);

                                $('#' + tableId).DataTable().row.add(data).draw(false);

                                editRows = document.querySelectorAll('.prv-edit-row');
                                deleteRows = document.querySelectorAll('.prv-remove-row');

                                editRowsFunction();
                                deleteRowsFunction();
                            }


                            modalEdit.classList.add('hide');
                            document.querySelectorAll(".prv-form .row").forEach(el => el.remove());

                        }else{
                            if(!document.querySelector('.validationErrorText')){
                                var modalBody = modalEdit.querySelector('.prv-form');
                                const node = document.createElement("div");
                                const textnode = document.createTextNode("Datos vacíos o no válidos");
                                node.appendChild(textnode);
                                node.classList.add('validationErrorText');
                                modalBody.appendChild(node);
                            }
                        }

                    });
                }
            }
        };

        var tableToJson = function(id) {
            var data = [];
            var headers = $("#" + id + " th");
            var rows = $("#" + id + " tbody tr").each(function(index) {
              data[index] = {};
              cells = $(this).find("td").not(':last');
              cells.each(function(cellIndex) {
                data[index][$(headers[cellIndex]).data('id')] = $(this).html();
              });
            });
            var json = {};
            json.data = data;
            return JSON.stringify(json);
        }

        saveModalsFunction();
        deleteModalsFunction();
        hideModalFunction();

        bodyElement.setAttribute('data-tables', 'true');
    }

    addRowsFunction();
    editRowsFunction();
    deleteRowsFunction();

</script>


