package com.statestr.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ruantianbo on 2017/3/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/user/login")
    public void login(@RequestParam("userName") String userName){

    }
}
