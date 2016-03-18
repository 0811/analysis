package com.dempe.analysis.manger.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2014/11/23.
 */
@Controller
public class HomeController {


    @RequestMapping("index")
    public String home() {
        System.out.println("----------home controller-----------");
        return "index";
    }

    @RequestMapping("/main")
    public String main() {
        System.out.println("----------main controller-----------");
        return "index";
    }


}
