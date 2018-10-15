//package com.suyou.eurekaclient.utils.wechat;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.suyou.eurekaclient.entity.PosterEntity;
//import com.suyou.eurekaclient.entity.RemindEntity;
//import com.suyou.eurekaclient.entity.UserEntity;
//import com.suyou.eurekaclient.service.AccessTokenService;
//import com.suyou.eurekaclient.service.PosterService;
//import com.suyou.eurekaclient.utils.HttpClientUtil;
//import com.suyou.eurekaclient.utils.StringTools;
//import com.suyou.eurekaclient.utils.WechatUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.math.BigInteger;
//import java.util.*;
//
//@Configuration
//@EnableScheduling
//@Slf4j
//public class SendTemplateMessageUtil {
//    @Value("${wechat.appId}")
//    private String appId;
//    @Value("${wechat.appSecret}")
//    private String appSecret;
//    @Autowired
//    private AccessTokenService accessTokenService;
//    @Autowired
//    private PosterService posterService;
//
//    @Scheduled(cron = "0 0/1  *  * * ? ") // 每分钟执行一次模板消息定时推送
//    public void scheduler() {
//        //先查询需要推送的用户以及海报id
//        //获取未开始的会议
//        List<PosterEntity> posterEntityList = posterService.getComingMeeting();
//        if (!posterEntityList.isEmpty()) {
//            for (PosterEntity posterEntity : posterEntityList){
//                Date startDate = posterEntity.getStartDate();
//                int remindTime = posterEntity.getRemindBefore();
//                if (0!=remindTime){
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(startDate);
//                    cal.add(Calendar.SECOND, -remindTime);
//                    Date remindDate = cal.getTime();
//                    if (remindDate.after(new Date())){
//                        int posterId = posterEntity.getId();
//                        //查出该会议下所有参与者
//                        List<UserEntity> userEntityList = posterService.getParticipants(posterId);
//                        if (!userEntityList.isEmpty()){
//                            //发送模板消息
//                            //发送完标记会议已经提醒
//                        }
//                    }
//                }
//
//            }
//            log.warn("开始执行模板消息定时推送任务" + new Date().toString());
//            String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
//            Map<String, String> paramMap = new HashMap<String, String>();
//            String result = HttpClientUtil.getPostResponse(url, paramMap);
//            if (!StringTools.isNullOrEmpty(result) && JSON.parseObject(result).get("errmsg").equals("ok")) {
//                //如果模板消息发送成功则插入一条发送记录
//                RemindEntity entity = new RemindEntity();
//
//            }
//        }
//    }
//
//
////    public static void main(String[] args) {
////        String s = URLEncoder.encode("%E8%AF%81%E5%BC%80%20%E0%BD%A2%E0%BE%9F%E0%BD%BC%E0%BD%82%E0%BD%A6%E0%BC%8B%E0%BD%95%E0%BE%B1%E0%BD%BA");
////        //System.out.println("转码后为: "+URLDecoder.decode("%E6%B1%AA%E5%B0%8F%E8%90%8D%20-%20CIKERS"));
////        System.out.println("转码前为: "+s);
////        System.out.println("转码后为"+URLDecoder.decode("%E6%97%B6%E5%B0%9A%E5%AE%9D%E8%B4%9D%E4%B8%93%E4%B8%9A%E5%84%BF%E7%AB%A5%E6%91%84%E5%BD%B1.%E5%B0%8F%E8%83%96"));
////    }
//}
