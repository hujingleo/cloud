package com.suyou.cloud.controller;


import com.suyou.cloud.service.SceneDataService;
import com.suyou.cloud.utils.BaseResp;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序码相关接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:47
 */
@Data
@Slf4j
@RestController
@RequestMapping("/wechat/scene")
public class SceneDataController {
    @Autowired
    private SceneDataService sceneDataService;
    //根据id获取scene_data
    @GetMapping("/getBySceneId")
    @ResponseBody
    public BaseResp getBySceneId(Long sceneId) {
       return sceneDataService.getBySceneId(sceneId);
    }

    //保存scene_data返回对应id
    @PostMapping("/saveSceneData")
    @ResponseBody
    public BaseResp saveSceneData(String sceneStr) {
        return sceneDataService.saveSceneData(sceneStr);
    }

    //获取小程序码
    @RequestMapping("/getWXacode")
    @ResponseBody
    public ResponseEntity<byte[]> getWXacode(HttpServletRequest request, String sceneId, String page)  {
        return sceneDataService.getWXacode(sceneId,page);

    }

    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(url, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

}
