
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

        series: [{}]
    };

    $.getJSON(url, function(data) {
        options.series[0].data = data;
        options.series[0].name = "test";
        var chart = new Highcharts.Chart(options);
    });
}