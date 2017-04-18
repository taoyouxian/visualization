


var Charts = { 
    Datas: {

    },
    tplCharts: {
        tplBar: {
            title: {
                text: '',
                subtext: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['类型数量'] // '蒸发量', '降水量'
            },
            toolbox: {
                show: true,
                feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    data: [] // '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '类型数量',
                    type: 'bar',
                    data: [0, 0, 0, 0, 0],
                    markPoint: {
                        data: [
                            { type: 'max', name: '最大值' },
                            { type: 'min', name: '最小值' }
                        ]
                    },
                    markLine: {
                        data: [
                            { type: 'average', name: '平均值' }
                        ]
                    }
                },
            ]
        },
        tplLine: {
            title: {
                text: '',
                subtext: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: [] // '意向', '预购', '成交'
            },
            toolbox: {
                show: true,
                feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar', 'stack', 'tiled'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: [] // '周一', '周二', '周三', '周四', '周五', '周六', '周日'
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '',
                    type: 'line',
                    smooth: true,
                    itemStyle: { normal: { areaStyle: { type: 'default' } } },
                    data: [] // 0, 0, 0, 0, 0, 0, 0
                },
            ]
        },
        tplPie: {
            title: {
                text: '',
                subtext: '',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br />{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data: []//'直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎'
            },
            toolbox: {
                show: true,
                feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    magicType: {
                        show: true,
                        type: ['pie', 'funnel'],
                        option: {
                            funnel: {
                                x: '25%',
                                width: '50%',
                                funnelAlign: 'left',
                                max: 1548
                            }
                        }
                    },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
            },
            calculable: true,
            series: [
                {
                    name: '',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: [
                        //{ value: 335, name: '直接访问' },
                        //{ value: 310, name: '邮件营销' },
                        //{ value: 234, name: '联盟广告' },
                        //{ value: 135, name: '视频广告' },
                        //{ value: 1548, name: '搜索引擎' }
                    ]
                }
            ]
        },
    },
    Load: function () {
        var me = Charts;
        try { 
            //var aOption = me.Datas.PieOption; 
            //Ac.acGetTable(Init.Path.Charts_Bar, {}, function (aRes) {
            //    me.Datas.Bar = aRes.Datas;  
            //    for (i in me.Datas.Dicts) {
            //        var aItem = me.Datas.Dicts[i];
            //        aOption.xAxis[0].data.push(aItem.F_Caption); 
            //        for (var i in me.Datas.Bar) {
            //            var aSubBar = me.Datas.Bar[i];
            //            if (aSubBar.F_Chk == 0 && aSubBar.F_ParentCode == aItem.F_Dict) {
            //                aOption.series[0].data[aItem.F_ID] = aSubBar.F_Count;
            //                aSubBar.F_Chk = 1;
            //                break;
            //            }
            //        }
            //    } 
            //    var aCharts = echarts.init($(".divPage")[0], 'macarons');
            //    aCharts.setOption(aOption);
            //    setTimeout(function () {
            //        window.onresize = function () {
            //            aCharts.resize();
            //        }
            //    }, 200);
            //})
            //Ac.acGetTable(Init.Path.Charts_Line, {}, function (aRes) {
            //    me.Datas.Line = aRes.Datas;

            //    var myDate = new Date(); 
            //    var year = myDate.getFullYear(); 
            //    var month = myDate.getMonth() + 1;

            //    if (month >=7) {
            //        for (var j = month - 6; j <= month; j++) {
            //            aOption.xAxis[0].data.push(year + "年" + j + "月");
            //        }
            //    } 
            //    else { 
            //        for (var i = 6+month; i <= 12; i++) {
            //            aOption.xAxis[0].data.push(year-1 + "年" + i + "月");
            //        }
            //        for (var i = 1; i <= month; i++) {
            //            aOption.xAxis[0].data.push(year + "年" + i + "月");
            //        }
            //    } 
            //    var series = [];
            //    for (i in me.Datas.Dicts) {
            //        var aItem = me.Datas.Dicts[i];
            //        aOption.legend.data.push(aItem.F_Caption);
            //        //var aInfo = {
            //        //    name: '',
            //        //    type: 'line',
            //        //    stack: '总量',
            //        //    data: [0, 0, 0, 0, 0, 0, 0]
            //        //};
            //        var aInfo = {
            //            name: '',
            //            type: 'line',
            //            smooth: true,
            //            itemStyle: { normal: { areaStyle: { type: 'default' } } },
            //            data: [0, 0, 0, 0, 0, 0, 0]
            //        };
            //        aInfo.name = me.Datas.Dicts[i].F_Caption;
            //        $.each(me.Datas.Line, function (aInd, aSubLine) {
            //            if (aSubLine.F_Chk == 0 && aSubLine.F_ParentCode == aItem.F_Dict) {
            //                aInfo.data[aSubLine.F_Ind] = aSubLine.F_Count;
            //                aSubLine.F_Chk = 1;
            //            }
            //        });
            //        series.push(aInfo);
            //    }
            //    aOption.series = series;
            //    var aCharts = echarts.init($(".divPage")[0], 'macarons');
            //    aCharts.setOption(aOption);
            //})
            //var aOption = me.Datas.PieOption;
            //Ac.acGetTable(Init.Path.Charts_Bar, {}, function (aRes) {
            //    me.Datas.Bar = aRes.Datas;
            //    aOption.series[0].data = me.Datas.Bar;
            //    for (i in me.Datas.Bar) {
            //        aOption.legend.data.push(me.Datas.Bar[i].name);
            //    }
            //    var aCharts = echarts.init($(".divPage")[0], 'macarons');
            //    aCharts.setOption(aOption);
            //})
        }
        catch (e) {
            var me = e;
        }
    },  
};
