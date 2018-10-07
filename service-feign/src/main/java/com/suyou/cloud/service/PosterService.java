package com.suyou.cloud.service;

import com.suyou.cloud.entity.PosterEntity;
import com.suyou.cloud.utils.BaseResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-user")
public interface PosterService {
    @RequestMapping(value = "/poster/list",method = RequestMethod.GET)
    BaseResp list();
    @GetMapping(value = "/poster/getById")
    BaseResp getById(@RequestParam(value = "id")Integer id);
    @PostMapping(value = "/poster/save")
    BaseResp save(PosterEntity posterEntity);
}
