package com.suyou.cloud.controller;

import com.suyou.cloud.service.UserService;
import com.suyou.cloud.utils.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public BaseResp test(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        return userService.login(username,password);
    }
    @RequestMapping(value = "/sysUserLilst",method = RequestMethod.GET)
    public BaseResp sysUserLilst(Model model){
         return  userService.list();
    }
    @RequestMapping(value = "/pageList",method = RequestMethod.GET)
    public BaseResp pageList(){
        return  userService.pageList();
    }
    @RequestMapping(value = "/posterList",method = RequestMethod.GET)
    public BaseResp posterList(){
        return  userService.posterList();
    }
}
