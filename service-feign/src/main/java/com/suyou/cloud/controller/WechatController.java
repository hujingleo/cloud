package com.suyou.cloud.controller;

import com.suyou.cloud.service.UserService;
import com.suyou.cloud.utils.BaseResp;
import com.suyou.cloud.utils.WechatLoginForm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 15:58:47
 */
@Slf4j
@Data
@RestController
@RequestMapping("wechat")
@ConfigurationProperties(prefix = "wechat")
public class WechatController {
    private String appId;
    private String appSecret;
    private String grantType;
    private String mchId;
    private String key;
    @Autowired
    private UserService userService;
    /**
     * 小程序登录
     */
    @PostMapping("/login")
    public BaseResp login(@RequestBody WechatLoginForm wechatLoginForm) {
        BaseResp baseResp = userService.wechatLogin(wechatLoginForm);
        return  baseResp;
    }
}
