package com.suyou.cloud.controller;

import com.suyou.cloud.service.UserService;
import com.suyou.cloud.utils.BaseResp;
import com.suyou.cloud.utils.JWTUtil;
import com.suyou.cloud.utils.StringTools;
import com.suyou.cloud.utils.WechatLoginForm;
import jdk.nashorn.internal.parser.Token;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    @GetMapping(value = "/saveUserOfficialAccountsOpenId")
    @ResponseBody
    public void saveUserOfficialAccountsOpenId(HttpServletResponse response,String openid, String code) throws IOException {
        if (StringTools.isNullOrEmpty(code)){
            log.error("获取公众号openid并保存接口code为空");
            response.getWriter().write("<script language=\"javascript\">window.opener=null;window.close();</script>");
        }
        log.warn("获取公众号openid并保存接口code为:"+code);
        log.warn("获取公众号openid并保存接口openid为:"+openid);
        String openId = openid;
        if (StringTools.isNullOrEmpty(openId)) {
            log.error("获取公众号openid并保存接口openId为空");
            response.getWriter().write("<script language=\"javascript\">window.opener=null;window.close();</script>");
        }
        BaseResp baseResp = userService.saveUserOfficialAccountsOpenId(code,openId);
        if (baseResp.getE()!=0){
            log.error("获取公众号openid并保存接口失败,返回信息为: "+baseResp.getMsg());
        }
        log.warn("获取公众号openid并保存接口成功");
        response.getWriter().write("<script language=\"javascript\">window.opener=null;window.close();</script>");
    }

    //判断用户是否有公众号openid
    @GetMapping(value = "/getUserOfficialAccountsOpenId")
    @ResponseBody
    public BaseResp getUserOfficialAccountsOpenId(HttpServletRequest request) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return userService.getUserOfficialAccountsOpenId(openId);
    }

}
