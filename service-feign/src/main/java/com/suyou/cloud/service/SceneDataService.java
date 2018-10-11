package com.suyou.cloud.service;

import com.suyou.cloud.utils.BaseResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-user")
public interface SceneDataService {
    @GetMapping(value = "/wechat/scene/getBySceneId")
    BaseResp getBySceneId(@RequestParam(value = "sceneId") Long sceneId);
    @PostMapping(value = "/wechat/scene/saveSceneData")
    BaseResp saveSceneData(@RequestParam(value = "sceneStr") String sceneStr);
    @GetMapping(value = "/wechat/scene/getWXacode")
    ResponseEntity<byte[]> getWXacode(@RequestParam(value = "sceneId") String sceneId, @RequestParam(value = "page") String page);
}
