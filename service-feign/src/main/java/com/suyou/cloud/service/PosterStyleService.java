package com.suyou.cloud.service;

import com.suyou.cloud.entity.PosterStyleEntity;
import com.suyou.cloud.utils.BaseResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-user")
public interface PosterStyleService {
    @RequestMapping(value = "/posterStyle/list",method = RequestMethod.GET)
    BaseResp list(@RequestParam(value = "type")String type);
    @GetMapping(value = "/posterStyle/getById")
    BaseResp getById(@RequestParam(value = "id") Integer id);
    @GetMapping(value = "/posterStyle/updateState")
    BaseResp updateState(@RequestParam(value = "id") Integer id,@RequestParam(value = "state") Integer state);
    @PostMapping(value = "posterStyle/save")
    BaseResp save(@RequestBody PosterStyleEntity posterStyleEntity);
}
