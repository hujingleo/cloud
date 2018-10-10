//package com.suyou.eurekaclient.service.impl;
//
//import com.baomidou.mybatisplus.service.impl.ServiceImpl;
//import com.suyou.eurekaclient.dao.UserFormidDao;
//import com.suyou.eurekaclient.entity.UserFormidEntity;
//import com.suyou.eurekaclient.service.AccessTokenService;
//import com.suyou.eurekaclient.service.UserFormidService;
//import com.suyou.eurekaclient.utils.HttpClientUtil;
//import com.suyou.eurekaclient.utils.StringTools;
////import com.suyou.eurekaclient.utils.WechatUtils;
//import com.suyou.eurekaclient.utils.wechat.Keyword;
//import com.suyou.eurekaclient.utils.wechat.TemplateSendData;
//import lombok.extern.slf4j.Slf4j;
//import net.sf.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service("userFormidService")
//public class UserFormidServiceImpl extends ServiceImpl<UserFormidDao, UserFormidEntity> implements UserFormidService {
//
////    @Autowired
////    private AccessTokenService accessTokenService;
//    @Override
//    public UserFormidEntity getByFormId(String form_id) {
//        return baseMapper.getByFormId(form_id);
//    }
////    @Override
////    public boolean sendDynamicNotice(String open_id, String appId, String appSecret, String title, String content) {
////        try {
////            UserFormidEntity userFormidEntity = baseMapper.getByOpenId(open_id);
////            if (null==userFormidEntity){
////                log.error("openid为 : "+open_id+"的用户找不到可用的form_id");
////                return false;
////            }
////            if (StringTools.isNullOrEmpty(userFormidEntity.getFormId())){
////                log.error("openid为 : "+open_id+"的用户找不到可用的form_id");
////                return false;
////            }
////            String form_id = userFormidEntity.getFormId();
////            String wxTemplateSendUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";
////            synchronized(this){
////                String access_token = accessTokenService.getNewestToken(appId);
////                if (StringTools.isNullOrEmpty(access_token)){
////                    access_token = WechatUtils.getAccessToken(appId, appSecret);
////                }
//////                String access_token = WechatUtils.getAccessToken(appId,appSecret);
//////                String access_token = "14_UVmTgh6doOmUUbu4PnF59ZQxEjXAuGrHCGg2pnO-T6wMMD7QCKsVW7AQnQZ-WGTYk5NsTj9-ZG2XW3FLJfx0fZGD0xXawKeU3eRu6mZuWnXqfgJgIhS8q8MvtKEvxa2YS9mP1DiCVc9mRRcVEDXgAGAKJD";
////                wxTemplateSendUrl += "?access_token=" + access_token;
////            }
////            JSONObject json = new JSONObject();
////            TemplateSendData data = new TemplateSendData();
////            if (!StringTools.isNullOrEmpty(title)){
////                data.setKeyword1(new Keyword(title));
////            }else{
////                data.setKeyword1(new Keyword("赛客运动超市"));
////            }
////            if (!StringTools.isNullOrEmpty(content)){
////                data.setKeyword2(new Keyword(content));
////            }else {
////                data.setKeyword2(new Keyword(" 亲爱的赛客会员，您在赛客运动超市的分享收益已到账，请到会员中心领取现金红包。感谢您对赛客的支持，祝您国庆愉快！"));
////            }
////            json.put("touser", open_id);
////            json.put("template_id", "w4TYVdYvAS1dIJLZDjNBX1tKVIQQX9Z7gy85YCfQ0SA");
////            json.put("form_id", form_id);
////            json.put("page", "pages/index/index");
////            json.put("data", data);
////            log.warn("提现通知模板消息参数为: " + json.toString());
////            String wxTemplateSendUrlResult = HttpClientUtil.post(json, wxTemplateSendUrl);
////            log.warn("提现通知模板消息结果为: " + wxTemplateSendUrlResult);
////            JSONObject wxTemplateSendUrlResultJson = JSONObject.fromObject(wxTemplateSendUrlResult);
////            if(wxTemplateSendUrlResultJson.get("errmsg").toString().equalsIgnoreCase("ok")){
////                log.warn("提现通知模板消息发送成功");
////                return true;
////            }
////            if(wxTemplateSendUrlResultJson.get("errcode").toString().equalsIgnoreCase("40001")){
////                log.warn("提现通知模板消息发送失败,accesstoken失效");
////                String access_token = WechatUtils.getAccessToken(appId, appSecret);
////                log.warn("得到accesstoken为"+access_token);
////                int updateResult = accessTokenService.updateAccessTokenByAppId(appId,access_token);
////                return true;
////            }
////            log.error("提现通知模板消息发送失败,微信返回结果为: "+wxTemplateSendUrlResult);
////            return false;
////        }catch (Exception e){
////            e.printStackTrace();
////            log.error("提现通知模板消息发送异常,异常信息为: "+e.getMessage());
////            return false;
////        }
////    }
//}
