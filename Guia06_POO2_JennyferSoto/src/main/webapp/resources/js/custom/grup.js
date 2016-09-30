$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consGrup([{name : 'codiGrupPara', value : row.id.trim()}]);
        });
        return false;
    };
    //Eliminar
    $.fn.funcElimGrup = function() {
        $(this).confirmation(
        {
            popout: true,
            onConfirm: function() {
                elimGrup();
                $('[data-toggle="confirmation-popout"]').confirmation('hide');
                return false;
            },
            onCancel: function()
            {
                $('[data-toggle="confirmation-popout"]').confirmation('hide');
                return false;
            }
        });
        return false;
    };

    INIT_OBJE_GRUP();
});

function INIT_OBJE_GRUP()
{
    $("#TablGrup").initBootTable();
    $("#FormGrup\\:btonElim").funcElimGrup();
}
