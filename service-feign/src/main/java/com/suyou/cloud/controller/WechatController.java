package com.suyou.cloud.controller;

import com.suyou.cloud.service.UserService;
import com.suyou.cloud.utils.BaseResp;
import com.suyou.cloud.utils.JWTUtil;
import com.suyou.cloud.utils.StringTools;
import com.suyou.cloud.utils.WechatLoginForm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

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

    //获取公众号openid并保存
    @PostMapping(value = "/saveUserOfficialAccountsOpenId")
    @ResponseBody
    public BaseResp saveUserOfficialAccountsOpenId(HttpServletRequest request,String code) {
        if (StringTools.isNullOrEmpty(code)){
            return BaseResp.error("code为空");
        }
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return userService.saveUserOfficialAccountsOpenId(code,openId);
    }

    //判断用户是否有公众号openid
    @PostMapping(value = "/getUserOfficialAccountsOpenId")
    @ResponseBody
    public BaseResp getUserOfficialAccountsOpenId(HttpServletRequest request) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return userService.getUserOfficialAccountsOpenId(openId);
    }

}
