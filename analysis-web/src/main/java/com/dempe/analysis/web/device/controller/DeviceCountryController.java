package com.dempe.analysis.web.device.controller;

import com.dempe.analysis.web.device.dao.DeviceCountryDao;
import com.dempe.analysis.web.device.model.DeviceCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2014/11/27.
 */
@Controller
@RequestMapping("/deviceCountry")
public class DeviceCountryController {

    @Autowired
    private DeviceCountryDao deviceCountryDao;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<DeviceCountry> list() {
        return deviceCountryDao.find().asList();
    }
}
