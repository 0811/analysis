package com.dempe.analysis.web.chart;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2014/12/3.
 */
public class HighChart {


    private SeriesBean seriesBean;


    private JSONObject xAxis;


    public SeriesBean getSeriesBean() {
        return seriesBean;
    }

    public void setSeriesBean(SeriesBean seriesBean) {
        this.seriesBean = seriesBean;
    }

    public JSONObject getxAxis() {
        return xAxis;
    }

    public void setxAxis(JSONObject xAxis) {
        this.xAxis = xAxis;
    }
}
