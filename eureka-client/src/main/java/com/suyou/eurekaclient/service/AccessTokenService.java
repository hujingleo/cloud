package com.suyou.eurekaclient.service;


import com.baomidou.mybatisplus.service.IService;
import com.suyou.eurekaclient.entity.AccessTokenEntity;


public interface AccessTokenService extends IService<AccessTokenEntity> {
    String getLatestToken(String appid, String appSecret);
    int updateAccessTokenByAppId(String appid, String access_token);
}
