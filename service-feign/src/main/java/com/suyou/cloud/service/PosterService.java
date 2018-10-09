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
    BaseResp getById(@RequestParam(value = "openId")String openId,@RequestParam(value = "id")Integer id);
    @PostMapping(value = "/poster/save")
    BaseResp save(@RequestBody PosterEntity posterEntity);
    @GetMapping(value = "/poster/getByOpenIdAndType")
    BaseResp getByOpenIdAndType(@RequestParam(value = "openId")String openId,@RequestParam(value = "type")String type);
    @GetMapping(value = "/poster/getMyPoster")
    BaseResp getMyPoster(@RequestParam(value = "openId")String openId);
}
