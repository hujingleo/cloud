package com.suyou.eurekaclient.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;


import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
@Slf4j
public class WechatUtils {

    private static int socketTimeout = 10000;// 连接超时时间，默认10秒
    private static int connectTimeout = 30000;// 传输超时时间，默认30秒

    /**
     * 获取用户信息
     * @param openId 用户标识
     * @return WeixinUserInfo
     */
    public static JSONObject getUserInfo(String appid, String secret, String openId) {
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
       String access_token = getAccessToken(appid,secret);
       if (!StringTools.isNullOrEmpty(access_token)) {
           requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);
           // 获取用户信息
           String result = HttpClientUtil.getGetResponse(requestUrl);
           JSONObject json = JSON.parseObject(result);
           return json;
       }
       return null;
    }
    /**
     * 获取accessToken
     * @param appid
     * @param secret
     * @return accessToken 接口访问凭证
     *
     */
    public static synchronized String getAccessToken(String appid, String secret) {
        String access_token =null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
        requestUrl = requestUrl.replace("APPID", appid).replace("SECRET", secret);
        // 获取accessToken
        String result = HttpClientUtil.getGetResponse(requestUrl);
        if (StringTools.isNullOrEmpty(result)||null==JSON.parseObject(result).get("access_token")){
            log.error("获取accessToken返回值为null ");
            result = HttpClientUtil.getGetResponse(requestUrl);
            int i =1;
            while (i<6){
                if (StringTools.isNullOrEmpty(result)||null==JSON.parseObject(result).get("access_token")) {
                    log.error("获取accessToken失败,重新尝试第"+i+"次");
                    result = HttpClientUtil.getGetResponse(requestUrl);
                }
                i++;
            }
        }
        if (!StringTools.isNullOrEmpty(result)&&null!=JSON.parseObject(result).get("access_token")){
            access_token=JSON.parseObject(result).get("access_token").toString();
            log.error("获取accessToken返回值为"+access_token);
        }
        return access_token;
    }

//    public static void main(String[] args) {
//        getAccessToken("wxcb530c140be871b2","1ed62575110442334b944dc841cc0b77");
//    }
}
