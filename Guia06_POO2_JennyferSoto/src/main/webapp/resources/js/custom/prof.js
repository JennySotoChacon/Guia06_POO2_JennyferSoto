$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consProf([{name : 'codiProfPara', value : row.id.trim()}]);
        });
        return false;
    };
    //Eliminar
    $.fn.funcElimProf = function() {
        $(this).confirmation(
        {
            popout: true,
            onConfirm: function() {
                elimProf();
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

    INIT_OBJE_PROF();
});

function INIT_OBJE_PROF()
{
    $("#TablProf").initBootTable();
    $("#FormProf\\:btonElim").funcElimProf();
}
