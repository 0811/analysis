package com.dempe.analysis.web.chart;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2014/12/3.
 */
public class HighChart {


    private JSONArray series;


    private JSONObject xAxis;

    public JSONArray getSeries() {
        return series;
    }

    public void setSeries(JSONArray series) {
        this.series = series;
    }

    public JSONObject getxAxis() {
        return xAxis;
    }

    public void setxAxis(JSONObject xAxis) {
        this.xAxis = xAxis;
    }
}
