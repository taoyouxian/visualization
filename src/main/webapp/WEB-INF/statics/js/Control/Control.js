
var Control = {
    Datas: {
        LineCharts:[],

    },
    doOnPickSQL: function () {
        var me = Control;
        try {

        }
        catch (e) {; }
    },
    doExcute: function () {
        var me = Control;
        try {
            var aSQL = $("#txtSQL").val();
            $.ajax({
                url : "Chart/doExcute",
                type : "GET",
                dataType: "json",
                data : {query: aSQL},
                success : function(result) {
                    console.log(result);

                },
                error: function (a, b, c) {
                    var aResult = { State: 0, Datas: { Ea: a, Eb: b, Ec: c } };
                    console.log(aResult);
                }
            });
        }
        catch (e) {; }
    },
    doClear: function () {
        var me = Control;
        try {
            $("#txtSQL").html();
        }
        catch (e) {; }
    },
};
