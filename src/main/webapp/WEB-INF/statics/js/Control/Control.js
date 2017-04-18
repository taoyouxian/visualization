var Control = {
    Datas: {
        LineCharts: [],
        logs: [],
        sql: "SELECT location, month, avg(temperature) as temp FROM weather where location = '[LOCATION]' GROUP BY location, month ORDER BY month",
        locations: [
            "KOSEOUL",
            "ALBIRMIN",
            "KSWICHIT",
            "BNMANAMA",
            "HIHONOLU",
            "BYMINSK",
            "DLBONN",
            "JPTOKYO",
            "CGBRZAVL",
            "ERDUBAI",
            "ISTELAVIV",
            "AUBRSBAN",
            "COBOGOTA",
            "IASIOCTY",
            "HKHONGKG",
            "CISHNYNG",
            "BHBELIZE",
            "CNQUEBEC",
            "IDBOISE",
            "COGRNDJU",
            "KWKUWAIT",
            "AUMELBRN",
            "CYNICOSI",
            "JPOSAKA",
            "FLMIAMIB",
            "INBOMBAY",
            "AGBUENOS",
            "CISHANGH",
            "DRSNTODM",
            "DNCOPNHG",
            "ETADSABA",
            "CIGNGZHO",
            "CNCALGRY",
            "GBBANJUL",
            "IDJAKRTA",
            "BIBJMBRA",
            "BMRNGOON",
            "DLFRNKFT",
            "GWBISSAU",
            "CICHNGDU",
            "ARFTSMIT",
            "FLORLAND",
            "BWDHAKA",
            "GAMACON",
            "GACOLMBS",
            "ILSPRING",
            "BZRIODJN",
            "JPSAPPOR",
            "ILPEORIA",
            "IEDUBLIN",
            "CNVANCVR",
            "ARLIROCK",
            "FRBRDAUX",
            "BANASSAU",
            "COCOSPGS",
            "INFTWAYN",
            "HOTEGUCI",
            "ERABUDBI",
            "INCHENAI",
            "ALMOBILE",
            "BZSAOPLO",
            "BZBRSLIA",
            "INEVANSV",
            "IADESMOI",
            "FRPARIS",
            "CAFRESNO",
            "AUCNBERA",
            "ALHUNTSV",
            "JDAMMAN",
            "GASAVANN",
            "CNMONTRL",
            "CZPRAGUE",
            "AZYUMA",
            "CNTORONT",
            "DEWILMIN",
            "GRATHENS",
            "CNREGINA",
            "ALMONTGO",
            "AKFAIRBA",
            "AZTUCSON",
            "HUBUDPST",
            "IVABIDJN",
            "KSTOPEKA",
            "INCALCUT",
            "FLTAMPA",
            "BOLAPAZ",
            "IDPOCATE",
            "GYGRGTWN",
            "FLWPALMB",
            "GNCONKRY",
            "AUSYDNEY",
            "AKJUNEAU",
            "AZFLAGST",
            "KNNAIROB",
            "CSSANJOS",
            "ISTELAVI",
            "CIBIEJNG",
            "KRPYGYNG",
            "FLTALLAH",
            "FIHELSIN",
            "GUGUATML"
        ]
    },
    Load: function () {
        var me = Control;
        try {
            var aInfo = me.Datas.locations;
            var aStr = "";
            for (var i in aInfo) {
                aStr += '<option value="' + aInfo[i] + '">' + aInfo[i] + '</option>';
            }
            hhls.fillElement("#cmbLocation", aStr);
            var aSQL = me.Datas.sql.replace("[LOCATION]", $("#cmbLocation").val());
            $("#txtSQL").val(aSQL);
        }
        catch (e) {
            ;
        }
    },
    doOnPickSQL: function () {
        var me = Control;
        try {
            var aSQL = me.Datas.sql.replace("[LOCATION]", $("#cmbLocation").val());
            $("#txtSQL").val(aSQL);
        }
        catch (e) {
            ;
        }
    },
    doExcute: function () {
        var me = Control;
        try {
            var aSQL = $("#txtSQL").val();
            var aLocation = $("#cmbLocation").val();
            if(aSQL != ""){
                $.get("/Chart/doExcute", {query: aSQL, filter: aLocation}, function (d) {
                    console.log(d);
                    // var json = $.parseJSON(d);
                    var json = d;
                    if (json.success) {
                        var aObj = hhls.getJsonObj(json.obj);
                        me.doFixChart(aSQL, aObj);
                    }
                    var aHtml = $("#txtMsg").val();
                    aHtml = aLocation + "_"  + json.msg + "\r\n" + aHtml;
                    $("#txtMsg").val(aHtml);

                    var aNum = Math.random()*100 + 1;
                    $("#cmbLocation").val(me.Datas.locations[parseInt(aNum)]);
                    me.doOnPickSQL();
                });
            }
        }
        catch (e) {
            ;
        }
    },
    doClear: function () {
        var me = Control;
        try {
            $("#txtSQL").val("");
        }
        catch (e) {
            ;
        }
    },
    doFixChart: function (aSQL, aObj) {
        var me = Control;
        try {
            var aInfo = {
                sql: aSQL,
                obj: aObj
            }
            me.Datas.logs.push(aInfo);
            var aOption = Charts.tplCharts.tplLine;
            aOption.title.text = $("#cmbLocation").val();
            // aOption.title.subtext = aSQL;
            // aOption.xAxis[0].data = [];
            // aOption.series = [];
            aOption.legend.data.push($("#cmbLocation").val());
            var aInfo = {
                name: '',
                type: 'line',
                smooth: true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: []
            };
            aInfo.name = $("#cmbLocation").val();
            for (var i in aObj) {
                var aItem = aObj[i];
                if(me.Datas.logs.length == 1)
                aOption.xAxis[0].data.push(aItem.month);
                aInfo.data.push(aItem.temp);
            }
            aOption.series.push(aInfo);
            var aHtml = "<div style='height:200px;width: 100%;border-bottom: 1px solid #dfdfdf;' class='" + me.Datas.logs.length + "'></div>";
            $(".divPreview").prepend(aHtml);
            var aCharts = echarts.init($(".divPreview ." + me.Datas.logs.length)[0], 'macarons');
            aCharts.setOption(aOption);
            $("#labCount").text(me.Datas.logs.length);
        }
        catch (e) {
            ;
        }
    },
};
