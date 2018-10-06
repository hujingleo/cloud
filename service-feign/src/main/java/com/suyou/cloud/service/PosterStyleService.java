package com.suyou.cloud.service;

import com.suyou.cloud.utils.BaseResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-user")
public interface PosterStyleService {
    @RequestMapping(value = "/posterStyle/list",method = RequestMethod.GET)
    BaseResp list();
    @GetMapping(value = "/posterStyle/getById")
    BaseResp getById(Integer id);
}
