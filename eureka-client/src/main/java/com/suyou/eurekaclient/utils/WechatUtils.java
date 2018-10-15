package com.suyou.eurekaclient.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

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
           requestUrl = requestUrl.replace("ACCESS_TOKEN", getAccessToken(appid, secret)).replace("OPENID", openId);
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
        String access_token;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
        requestUrl = requestUrl.replace("APPID", appid).replace("SECRET", secret);
        // 获取accessToken
        String result = HttpClientUtil.getGetResponse(requestUrl);
        if (!StringTools.isNullOrEmpty(result)) {
            JSONObject json = JSON.parseObject(result);
            if (null!=json.get("errcode")&&null!=json.get("errcode").toString()){
                String errcode = json.get("errcode").toString();
                if (errcode.equalsIgnoreCase("40001")){
                    int i =0;
                    result = HttpClientUtil.getGetResponse(requestUrl);
                    while (i<5){
                        if (StringTools.isNullOrEmpty(result)||null!=JSON.parseObject(result).get("errcode")) {
                            result = HttpClientUtil.getGetResponse(requestUrl);
                        }
                    }
                }
            }
            if (null!=json.get("access_token")&&!StringTools.isNullOrEmpty(json.get("access_token").toString())){
                access_token = json.get("access_token").toString();
                return access_token;
            }
        }
        return null;
    }


}
