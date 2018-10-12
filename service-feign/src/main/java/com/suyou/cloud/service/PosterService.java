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
    @GetMapping(value = "/poster/getReaders")
    BaseResp getReaders(@RequestParam(value = "pageIndex")Integer pageIndex,@RequestParam(value = "pageSize")Integer pageSize,@RequestParam(value = "id")Integer id);
    @PostMapping(value = "/poster/save")
    BaseResp save(@RequestBody PosterEntity posterEntity);
    @PostMapping(value = "/poster/update")
    BaseResp update(@RequestBody PosterEntity posterEntity);
    @GetMapping(value = "/poster/getByOpenIdAndType")
    BaseResp getByOpenIdAndType(@RequestParam(value = "openId")String openId,@RequestParam(value = "type")String type);
    @GetMapping(value = "/poster/getMyPoster")
    BaseResp getMyPoster(@RequestParam(value = "openId")String openId);
    @GetMapping(value = "/poster/getMyMeeting")
    BaseResp getMyMeeting(@RequestParam(value = "openId")String openId);
    @GetMapping(value = "/poster/getMyProduction")
    BaseResp getMyProduction(@RequestParam(value = "openId")String openId);
    @GetMapping(value = "/poster/reserveMeeting")
    BaseResp reserveMeeting(@RequestParam(value = "openId")String openId,@RequestParam(value = "id")Integer id);

}
