
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
        credits:{
            enabled:false // 禁用版权信息
        },
        exporting:{
            enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
        },

        series: [{}]
    };

    $.getJSON(url, function(data) {
        options.series[0].data = data;
        options.series[0].name = "";
        var chart = new Highcharts.Chart(options);
    });
}