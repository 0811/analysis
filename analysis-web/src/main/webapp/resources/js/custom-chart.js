
function getLineChart(div,url){
    var options = {
        chart: {
            renderTo: div,
            type: 'line'
        },
        title: {
            text: '',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        yAxis: {
            title: {
                text: ''
            }

        },
        xAxis: {
            categories: ['00', '01', '02', '03', '04', '05',
                '06', '07', '08', '09', '10', '11','12','13','14','15','16','17','18','19','20','21','22','23']
        },
        credits:{
            enabled:false // 禁用版权信息
        },
        exporting:{
            enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
        },

        series: [{showInLegend: false}]
    };

    $.getJSON(url, function(data) {
        options.series[0].data = data.today;
        options.series[0].name="today";
//        options.series[1].data=data.yesterday;
//        options.series[1].name="yesterday";
        var chart = new Highcharts.Chart(options);
    });
}