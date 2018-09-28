package com.suyou.cloud.controller;

import com.suyou.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(@RequestParam String name){
        return userService.sayHiFromClientOne(name);
    }
}
