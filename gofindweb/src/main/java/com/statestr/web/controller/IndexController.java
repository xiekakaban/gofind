package com.statestr.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ruantianbo on 2017/3/19.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String welcome(){
        System.out.println("Jump into index Controller");
        return "index";
    }
    @RequestMapping("/admin")
    public String admin(){
        System.out.println("Jump into admin Controller");
        return "admin";
    }
}
