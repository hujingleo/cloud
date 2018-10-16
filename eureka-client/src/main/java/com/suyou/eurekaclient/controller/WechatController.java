package com.suyou.eurekaclient.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.suyou.eurekaclient.entity.PageEntity;
import com.suyou.eurekaclient.entity.UserEntity;
import com.suyou.eurekaclient.service.PageService;
import com.suyou.eurekaclient.service.UserService;
import com.suyou.eurekaclient.utils.*;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.*;


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

    private String officialAccountsAppId;
    private String officialAccountsAppSecret;
//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    /**
     * 小程序登录
     */
    @PostMapping("/login")
    @ApiOperation("微信登录")
    public BaseResp login(@RequestBody WechatLoginForm wechatLoginForm) {
        if (StringUtils.isBlank(wechatLoginForm.getCode())) {
            return BaseResp.error("code不能为空");
        }
        if (StringUtils.isBlank(wechatLoginForm.getAvatarUrl())) {
            return BaseResp.error("头像信息不能为空");
        }
        if (StringUtils.isBlank(wechatLoginForm.getGender())) {
            return BaseResp.error("性别信息不能为空");
        }
        if (StringUtils.isBlank(wechatLoginForm.getNickName())) {
            return BaseResp.error("昵称不能为空");
        }
        if (StringUtils.isBlank(wechatLoginForm.getEncryptedData())) {
            return BaseResp.error("用户信息密文不能为空");
        }
        if (StringUtils.isBlank(wechatLoginForm.getIv())) {
            return BaseResp.error("ivc参数不能为空");
        }
        String avatarUrl = wechatLoginForm.getAvatarUrl();
        String gender = wechatLoginForm.getGender();
        String nickName = wechatLoginForm.getNickName();
        String code = wechatLoginForm.getCode();
        String param = "?grant_type=" + grantType + "&appid=" + appId + "&secret=" + appSecret + "&js_code=" + code;

        String url = "https://api.weixin.qq.com/sns/jscode2session" + param;
        //get json数据
        String result = HttpClientUtil.getGetResponse(url);
//        String result = restTemplate.getForEntity(url, String.class).getBody();
        if (StringTools.isNullOrEmpty(result)) {
            log.error("小程序登录接口返回结果为空");
            return BaseResp.error("小程序登录接口返回结果为空");
        }
        log.warn("小程序登录接口返回参数：{}", result);
        JSONObject rsJosn = JSON.parseObject(result);
        if (rsJosn.get("errcode") != null) {
            //返回异常信息
            log.error("小程序登陆返回异常信息：" + rsJosn.get("errmsg").toString());
            return BaseResp.error(rsJosn.get("errmsg").toString());
        }
        String sessionKey = null;
        Map<String, Object> map = new HashMap<>();
        String unionId = null;
        com.alibaba.fastjson.JSONObject userInfoJSON = null;
        String openId = null;
        if (null != rsJosn.get("session_key")) {
            sessionKey = rsJosn.get("session_key").toString();
        } else {
            log.error("小程序登录接口无法获得session_key");
            return BaseResp.error("小程序登录接口无法获得session_key");
        }
        if (null != rsJosn.get("openid")) {
            openId = rsJosn.get("openid").toString();
        } else {
            log.error("小程序登录接口无法获得openid");
            return BaseResp.error("小程序登录接口无法获得openid");
        }
        if (!StringTools.isNullOrEmpty(wechatLoginForm.getEncryptedData())) {
            //////////////// 2、对encryptedData加密数据进行AES解密其中包含这openid和unionid ////////////////
            try {
                log.warn("收到encryptedData为：" + wechatLoginForm.getEncryptedData() + "用户openid为" + openId + "昵称为" + nickName);
                String decryptResult = AesCbcUtil.decrypt(wechatLoginForm.getEncryptedData(), sessionKey, wechatLoginForm.getIv(), "UTF-8");
                if (null != decryptResult && decryptResult.length() > 0) {
                    userInfoJSON = JSON.parseObject(decryptResult);
                    log.warn("对encryptedData加密数据进行AES解密得到数据" + userInfoJSON);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("对encryptedData加密数据进行AES解密失败，encryptedData数据为：" + wechatLoginForm.getEncryptedData());
                return BaseResp.error("对encryptedData加密数据进行AES解密失败");
            }
        }
        if (null == userInfoJSON) {
            log.error("用户微信登陆encryptedData无法解密，用户encryptedData为" + wechatLoginForm.getEncryptedData());
        } else {
            if (null != userInfoJSON.get("unionId") && !StringTools.isNullOrEmpty(userInfoJSON.get("unionId").toString())) {
                unionId = userInfoJSON.get("unionId").toString();
            } else if (null != rsJosn.get("unionid") && !StringTools.isNullOrEmpty(rsJosn.get("unionid").toString())) {
                unionId = rsJosn.get("unionid").toString();
            } else {
                log.error("用户微信登陆无法获得unionid，暂用openid代替，用户openid为" + openId + "昵称为" + nickName);
                unionId = openId;
            }
        }
        try {
            //如果通过open_id能查出存在用户，则直接返回用户信息
            synchronized (this) {
                if (null==userService.selectOne(new EntityWrapper<UserEntity>().eq("open_id",openId))) {
                    //抽空不全插入检查，唯一键等；
                    UserEntity userEntity = new UserEntity();
                    userEntity.setOpenId(openId);
                    userEntity.setUnionId(unionId);
                    userEntity.setAvatarUrl(avatarUrl);
                    userEntity.setGender(gender);
                    nickName = URLDecoder.decode(nickName,"utf-8");
                    userEntity.setNickName(nickName);
                    userEntity.setCreatedDate(new Date());
                    if (!userService.insert(userEntity)) {
                        log.error("登陆接口插入用户数据失败,用户openid为" + openId + "昵称为" + nickName);
                        return BaseResp.error("登陆接口插入用户数据失败,用户openid为" + openId + "昵称为" + nickName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登陆接口插入用户数据异常" + e.getMessage() + "用户openid为" + openId + "昵称为" + nickName);
            return BaseResp.error("登陆接口插入用户数据异常,用户openid为" + openId + "昵称为" + nickName);
        }
        String token = JWTUtil.sign(openId);
        map.put("openId", openId);
        map.put("unionId", unionId);
        map.put("sessionKey", sessionKey);
        map.put("token", token);
        return BaseResp.ok(map);
    }

    //获取小程序码
    @RequestMapping("getWXacode.jpg")
    @ResponseBody
    public ResponseEntity<byte[]> getWXacode(String scene_id, String page) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result1 = RestTemplateUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret);
        String access_token = JSONObject.parseObject(result1).getString("access_token");
        String url = "http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token;
        Map<String, Object> param = new HashMap<>();
        param.put("scene", scene_id);
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

    //获取公众号openid并保存
    @GetMapping(value = "saveUserOfficialAccountsOpenId")
    @ApiOperation(value = "获取公众号openid并保存")
    @ResponseBody
    public BaseResp saveUserOfficialAccountsOpenId(String code, String openId) {
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        UserEntity userEntity = userService.selectOne(new EntityWrapper<UserEntity>().eq("open_id",openId));
        if (null == userEntity) {
            return BaseResp.error(-3, "token invalid.");
        }
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?";
        url += "appid=" + officialAccountsAppId + "&secret=" + officialAccountsAppSecret + "&code=" + code + "&grant_type=authorization_code";
        //get json数据
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForEntity(url, String.class).getBody();
        if (StringTools.isNullOrEmpty(result)) {
            return BaseResp.ok("获取用户信息失败");
        }
        JSONObject rsJosn = JSON.parseObject(result);
        if (null == rsJosn.get("openid") ){
            return BaseResp.error("获取用户信息失败");
        }
        if (!StringTools.isNullOrEmpty(rsJosn.get("openid").toString())) {
            String officialAccountsOpenId = rsJosn.get("openid").toString();
            userEntity.setOfficialAccountsOpenId(officialAccountsOpenId);
            boolean updateresult = userService.updateById(userEntity);
            if (!updateresult ) {
                return BaseResp.ok("更新用户公众号openid失败");
            }
            return BaseResp.ok("更新用户公众号openid成功");
        }
        return BaseResp.error("更新用户公众号openid失败");
    }

    @GetMapping(value = "getUserOfficialAccountsOpenId")
    @ApiOperation(value = "判断用户是否有公众号openId")
    @ResponseBody
    public BaseResp getUserOfficialAccountsOpenId(String openId) {
        UserEntity userEntity = userService.selectOne(new EntityWrapper<UserEntity>().eq("open_id",openId));
        if (null == userEntity) {
            return BaseResp.error(-3, "token invalid.");
        }
        BaseResp baseResp = new BaseResp();
        if (StringTools.isNullOrEmpty(userEntity.getOfficialAccountsOpenId())) {
            baseResp.setE(0);
            baseResp.setData("0");
            baseResp.setMsg("未找到用户公众号openid");
        } else {
            baseResp.setE(0);
            baseResp.setData("1");
            baseResp.setMsg("用户已存在公众号openid");
        }
        return baseResp;
    }

//    @PostMapping(value = "getUserOfficialAccountsOpenId")
//    @ApiOperation(value = "发送模板消息")
//    @ResponseBody
//    public BaseResp getUserOfficialAccountsOpenId(String openId) {
//        UserEntity userEntity = userService.selectOne(new EntityWrapper<UserEntity>().eq("open_id",openId));
//        if (null == userEntity) {
//            return BaseResp.error(-3, "token invalid.");
//        }
//        BaseResp baseResp = new BaseResp();
//        if (StringTools.isNullOrEmpty(userEntity.getOfficialAccountsOpenId())) {
//            baseResp.setE(0);
//            baseResp.setData("0");
//            baseResp.setMsg("未找到用户公众号openid");
//        } else {
//            baseResp.setE(0);
//            baseResp.setData("1");
//            baseResp.setMsg("用户已存在公众号openid");
//        }
//        return baseResp;
//    }

}
