package com.suyou.eurekaclient.utils.wechat;

import com.suyou.eurekaclient.service.AccessTokenService;
import com.suyou.eurekaclient.utils.StringTools;
import com.suyou.eurekaclient.utils.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
public class AccessTokenUpdateUtil {
    private final Logger log = LoggerFactory.getLogger(getClass());


    @Value("${cikers.wechat.appId}")
    private String appId;
    @Value("${cikers.wechat.appSecret}")
    private String appSecret;
    @Autowired
    private AccessTokenService accessTokenService;
    @Scheduled(cron = "0 0/10  *  * * ? ") // 每30分钟执行一次更新accesstoken
    public void scheduler() {
        log.warn("开始执行更新accesstoken定时任务" +new Date().toString());
        String access_token = WechatUtils.getAccessToken(appId, appSecret);
        log.warn("得到accesstoken为"+access_token);
        if (StringTools.isNullOrEmpty(access_token)){
            access_token = WechatUtils.getAccessToken(appId, appSecret);
        }
        int updateResult = accessTokenService.updateAccessTokenByAppId(appId,access_token);
        if (1!=updateResult){
            log.error("更新access_token失败,access_token为: " + access_token);
        }
    }


//    public static void main(String[] args) {
//        String s = URLEncoder.encode("%E8%AF%81%E5%BC%80%20%E0%BD%A2%E0%BE%9F%E0%BD%BC%E0%BD%82%E0%BD%A6%E0%BC%8B%E0%BD%95%E0%BE%B1%E0%BD%BA");
//        //System.out.println("转码后为: "+URLDecoder.decode("%E6%B1%AA%E5%B0%8F%E8%90%8D%20-%20CIKERS"));
//        System.out.println("转码前为: "+s);
//        System.out.println("转码后为"+URLDecoder.decode("%E6%97%B6%E5%B0%9A%E5%AE%9D%E8%B4%9D%E4%B8%93%E4%B8%9A%E5%84%BF%E7%AB%A5%E6%91%84%E5%BD%B1.%E5%B0%8F%E8%83%96"));
//    }
}
