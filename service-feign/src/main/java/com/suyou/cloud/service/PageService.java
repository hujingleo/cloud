package com.suyou.cloud.service;

import com.suyou.cloud.utils.BaseResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-user")
public interface PageService {
    @RequestMapping(value = "/page/list",method = RequestMethod.GET)
    BaseResp list();
    @GetMapping(value = "/page/getById")
    BaseResp getById(Integer id);
}
