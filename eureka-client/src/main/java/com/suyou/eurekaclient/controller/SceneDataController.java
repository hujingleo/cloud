package com.suyou.eurekaclient.controller;


import com.alibaba.fastjson.JSONObject;
import com.suyou.eurekaclient.entity.SceneDataEntity;
import com.suyou.eurekaclient.service.AccessTokenService;
import com.suyou.eurekaclient.service.SceneDataService;
import com.suyou.eurekaclient.service.UserService;
import com.suyou.eurekaclient.utils.BaseResp;
import com.suyou.eurekaclient.utils.EmojiFilterUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.math.BigDecimal;
import java.util.Date;
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
@ConfigurationProperties(prefix = "wechat")
@RequestMapping("/wechat/scene")
public class SceneDataController {

    private String appId;//微信小程序appid
    private String appSecret;//微信小程序密钥

    @Autowired
    private SceneDataService sceneDataService;
    @Autowired
    private UserService userService;

    //根据id获取scene_data
    @GetMapping("/getBySceneId")
    @ApiOperation(value = "根据id获取sceneData")
    @ResponseBody
    public BaseResp getBySceneId(Long sceneId) {
        if (sceneId == null || sceneId == 0) {
            return BaseResp.error(98, "sceneId不能为空");
        }
        return BaseResp.ok(sceneDataService.getSceneStrById(sceneId));
    }

    //保存scene_data返回对应id
    @PostMapping("/saveSceneData")
    @ApiOperation(value = "保存scene_data返回对应id")
    @ResponseBody
    public BaseResp saveSceneData(String sceneStr) {
        if (sceneStr == null || sceneStr == "") {
            return BaseResp.error(98, "scene_str参数异常,不能为空");
        }
        //将emoji表情替换成*
        try {
            sceneStr = EmojiFilterUtils.filterEmoji(sceneStr);
            SceneDataEntity sceneDataEntity = new SceneDataEntity();
            sceneDataEntity.setSceneStr(sceneStr);
            return BaseResp.ok(sceneDataService.saveSceneData(sceneDataEntity));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存scene_da异常,异常信息为：" + e.getMessage());
            return BaseResp.error("保存scene_da异常");
        }
    }

    //获取小程序码
    @RequestMapping("/getWXacode")
    @ResponseBody
    public ResponseEntity<byte[]> getWXacode(HttpServletRequest request, String sceneId, String page) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result1 = get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret);
        String access_token = JSONObject.parseObject(result1).getString("access_token");
        String url = "http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token;
        Map<String, Object> param = new HashMap<>();
        param.put("scene", sceneId);
        param.put("page", page);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        HttpEntity requestEntity = new HttpEntity(param, headers);
        byte[] result = null;
        String baseString = null;
        try {
            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取小程序码异常，异常信息为" + e.getMessage());
            return null;
        }
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
