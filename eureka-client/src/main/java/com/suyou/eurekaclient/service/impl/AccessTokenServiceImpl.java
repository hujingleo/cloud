package com.suyou.eurekaclient.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.AccessTokenDao;
import com.suyou.eurekaclient.entity.AccessTokenEntity;
import com.suyou.eurekaclient.service.AccessTokenService;
import com.suyou.eurekaclient.utils.StringTools;
import com.suyou.eurekaclient.utils.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * 获取微信接口凭证access_token
 */
@Slf4j
@Service("accessTokenService")
public class AccessTokenServiceImpl extends ServiceImpl<AccessTokenDao, AccessTokenEntity> implements AccessTokenService {

    @Override
    public String getLatestToken(String appid, String appSecret) {
        String access_token = null;
        try {
        if (null == baseMapper.getLatestToken(appid)) {
             access_token = WechatUtils.getAccessToken(appid, appSecret);
            if (StringTools.isNullOrEmpty(access_token)) {
                log.error("请求微信接口获取access_token失败");
            }
            AccessTokenEntity entity = new AccessTokenEntity();
            entity.setAccess_token(access_token);
            entity.setAppid(appid);
            entity.setCreated_date(new Date());
            int result = baseMapper.insert(entity);
            if (1 != result) {
                log.error("插入access_token失败,access_token为: " + access_token);
            }
                return access_token;
        } else {
            AccessTokenEntity accessTokenEntity = baseMapper.getLatestToken(appid);
            Date created_date = accessTokenEntity.getCreated_date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(created_date);
            cal.add(Calendar.HOUR, 1);
            //判断access_token是否过期,如果过期则重新获取并更新数据库
            if (cal.getTime().before(new Date())) {
                access_token = WechatUtils.getAccessToken(appid, appSecret);
                int updateResult = baseMapper.updatePvByStoreId(appid,access_token);
                if (1!=updateResult){
                    log.error("更新access_token失败,access_token为: " + access_token);
                }
                return access_token;
            }
            if (null!=accessTokenEntity.getAccess_token()){
                return accessTokenEntity.getAccess_token();
            }else {
                log.error("从数据库中获取access_token为空");
                return null;
            }
        }
        }catch (Exception e){
            log.error("获取access_token异常,异常信息为: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getNewestToken(String appid) {
        try {
          AccessTokenEntity accessTokenEntity =  baseMapper.getNewestToken(appid);
          if (accessTokenEntity==null){
              return null;
          }
          String accessToken = accessTokenEntity.getAccess_token();
          if (StringTools.isNullOrEmpty(accessToken)){
              log.error("获取到最新的accesstoken为空");
              return null;
          }
          return accessToken;
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取最新accesstoken异常,异常信息为"+e.getMessage());
        }
        return null;
    }

    @Override
    public int updateAccessTokenByAppId(String appid, String access_token) {
        return baseMapper.updateAccessTokenByAppId(appid,access_token);
    }
}
