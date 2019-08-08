document.addEventListener("DOMContentLoaded", function () {
    $('#modal_delete').on('show.bs.modal', function (e) {
        var hourId = $(e.relatedTarget).data('hour-id');
        $(e.currentTarget).find('input[id="id"]').val(hourId);
    });
})