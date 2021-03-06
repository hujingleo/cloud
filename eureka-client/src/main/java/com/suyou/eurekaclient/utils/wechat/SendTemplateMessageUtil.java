package com.suyou.eurekaclient.utils.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.entity.RemindEntity;
import com.suyou.eurekaclient.entity.UserEntity;
import com.suyou.eurekaclient.service.AccessTokenService;
import com.suyou.eurekaclient.service.PosterService;
import com.suyou.eurekaclient.utils.HttpClientUtil;
import com.suyou.eurekaclient.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@EnableScheduling
@Slf4j
public class SendTemplateMessageUtil {
    @Value("${wechat.officialAccountsAppId}")
    private String appId;
    @Value("${wechat.officialAccountsAppSecret}")
    private String appSecret;
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private PosterService posterService;

    @Scheduled(cron = "0 0/1  *  * * ? ") // 每分钟执行一次模板消息定时推送
    public void scheduler() {
        //先查询需要推送的用户以及海报id
        //获取未开始的会议
        log.warn("开始执行定时推送会议提醒任务");
        List<PosterEntity> posterEntityList = posterService.getComingMeeting();
        if (posterEntityList != null && !posterEntityList.isEmpty()) {

            for (PosterEntity posterEntity : posterEntityList) {
                Date startDate = posterEntity.getStartDate();
                int remindTime = posterEntity.getRemindBefore();
                int hasRemind = posterEntity.getHasRemind();
                if (0 != remindTime&&hasRemind!=1) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(startDate);
                    cal.add(Calendar.SECOND, -remindTime);
                    Date remindDate = cal.getTime();
                    if (remindDate.before(new Date())) {
                        int posterId = posterEntity.getId();
                        //查出该会议下所有参与者
                        List<UserEntity> userEntityList = posterService.getParticipants(posterId);

                        if (null != userEntityList && !userEntityList.isEmpty()) {
                            log.warn("需要提醒的会议海报id为:" + posterId + "需要提醒用户数为:" + userEntityList.size());
                            //发送模板消息
                            for (UserEntity userEntity : userEntityList) {
                                log.warn("开始发送模板消息");
                                Miniprogram miniprogram = new Miniprogram();
                                miniprogram.setAppid("wxcb530c140be871b2");
                                miniprogram.setPagepath("pages/myWaitting/myWaitting");
                                JSONObject json = new JSONObject();
                                TemplateSendData data = new TemplateSendData();
                                String content = posterEntity.getContent();
                                JSONObject jsonObject = JSONObject.parseObject(content);
                                String name = jsonObject.get("title").toString();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String enddatestr = sdf.format(posterEntity.getEndDate());
                                String startdatestr = sdf2.format(posterEntity.getStartDate());
                                String startTime = startdatestr + "--" + enddatestr;
                                String address = posterEntity.getAddress();

                                int remindMinutes = posterEntity.getRemindBefore() / 60;
                                String remindstr = String.valueOf(remindMinutes);
                                data.setFirst(new Keyword("您好," + remindstr + "分钟后会议即将开始", "#173177"));
                                data.setKeyword1(new Keyword(name, "#173177"));
                                data.setKeyword2(new Keyword(startTime, "#173177"));
                                data.setKeyword3(new Keyword(address, "#173177"));
                                data.setRemark(new Keyword("请做好准备,请及时参加会议", "#173177"));
                                json.put("touser", userEntity.getOfficialAccountsOpenId());
                                json.put("template_id", "JWcS4nKbNJQV0qyRLk1dLsduX6K-h4NQnTQTBKhOZNU");
                                json.put("url", "http://weixin.qq.com/download");
                                json.put("miniprogram", miniprogram);
                                json.put("data", data);
                                log.warn("请求微信模板消息参数为: " + json.toString());
                                boolean result = WechatUtils.sendTemplateMessage(accessTokenService.getLatestToken(appId, appSecret), json.toJSONString());
                                if (result) {
                                    log.warn("发送会议提醒模板消息成功,海报id为: " + posterEntity.getId());
                                    //发送成功标记会议已经提醒
                                    posterEntity.setHasRemind(1);
                                    boolean updateresult = posterService.updateById(posterEntity);
                                    if (!updateresult) {
                                        log.error("发送会议提醒模板消息成功但标记会议海报已经推送失败,海报id为: " + posterEntity.getId());
                                    }
                                } else {
                                    log.warn("发送会议提醒模板消息失败,海报id为: " + posterEntity.getId());
                                }
                            }
                        } else {
                            log.warn("需要提醒的会议海报id为:" + posterId + "需要提醒用户数为:" + userEntityList.size());
                        }
                    }
                }
            }
        } else {
            log.warn("没有需要提醒的会议海报");
        }
    }


//    public static void main(String[] args) {
//        String s = URLEncoder.encode("%E8%AF%81%E5%BC%80%20%E0%BD%A2%E0%BE%9F%E0%BD%BC%E0%BD%82%E0%BD%A6%E0%BC%8B%E0%BD%95%E0%BE%B1%E0%BD%BA");
//        //System.out.println("转码后为: "+URLDecoder.decode("%E6%B1%AA%E5%B0%8F%E8%90%8D%20-%20CIKERS"));
//        System.out.println("转码前为: "+s);
//        System.out.println("转码后为"+URLDecoder.decode("%E6%97%B6%E5%B0%9A%E5%AE%9D%E8%B4%9D%E4%B8%93%E4%B8%9A%E5%84%BF%E7%AB%A5%E6%91%84%E5%BD%B1.%E5%B0%8F%E8%83%96"));
//    }
}
