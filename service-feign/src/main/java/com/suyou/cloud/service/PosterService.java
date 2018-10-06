package com.suyou.cloud.service;

import com.suyou.cloud.utils.BaseResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-user")
public interface PosterService {
    @RequestMapping(value = "/poster/list",method = RequestMethod.GET)
    BaseResp list();
    @GetMapping(value = "/poster/getById")
    BaseResp getById(@RequestParam(value = "id")Integer id);
}
