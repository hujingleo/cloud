package com.suyou.cloud.service;

import com.suyou.cloud.utils.BaseResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-user")
public interface UserService {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
    @RequestMapping(value = "/sysUser/login",method = RequestMethod.GET)
    BaseResp login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password);
    @RequestMapping(value = "/sysUser/list",method = RequestMethod.GET)
    BaseResp list();
}
