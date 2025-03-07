function createEditor(id, value, func) {

    var editor = CKEDITOR.replace('textarea_' + id, {
        on: {
            instanceReady: function() {
                var buffer = CKEDITOR.tools.eventsBuffer(1000, function() {
                    func();
                } );

                this.on( 'change', buffer.input );
            }
        }
    });
    editor.setData(value);

}

function initCards() {
    $(document).ready(function() {
        $('#adeplus .card-header .btn-link').toggleClass("collapsed");
    });
}

window.addEventListener('beforeunload', function (e) {
    if ($.active > 0) {
        e.preventDefault();
        e.returnValue = '';
    }
});

initCards();

