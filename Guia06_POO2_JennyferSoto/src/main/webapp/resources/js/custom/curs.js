$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consCurs([{name : 'codiCursPara', value : row.id.trim()}]);
        });
        return false;
    };
    //Eliminar
    $.fn.funcElimCurs = function() {
        $(this).confirmation(
        {
            popout: true,
            onConfirm: function() {
                elimCurs();
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

    INIT_OBJE_CURS();
});

function INIT_OBJE_CURS()
{
    $("#TablCurs").initBootTable();
    $("#FormCurs\\:btonElim").funcElimCurs();
}
