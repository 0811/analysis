package com.dempe.analysis.web.chart;

import com.alibaba.fastjson.JSONArray;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Administrator on 2014/12/3.
 */
public class SeriesBean {

    @JsonProperty("name")
    private String name;
//    @JsonProperty("color")
//    private String color;
    @JsonProperty("data")
    private JSONArray data;



    private boolean showInLegend;


    public SeriesBean(String name, JSONArray data) {
        this.name = name;
        //this.color = color;
        this.data = data;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    public boolean isShowInLegend() {
        return showInLegend;
    }

    public void setShowInLegend(boolean showInLegend) {
        this.showInLegend = showInLegend;
    }
}
