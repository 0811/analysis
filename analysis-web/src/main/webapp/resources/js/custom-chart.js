
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

        series: [{}]
    };

    $.getJSON(url, function(data) {
        options.series[0].data = data;
        var chart = new Highcharts.Chart(options);
    });
}