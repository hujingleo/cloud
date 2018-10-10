
package com.suyou.eurekaclient.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.suyou.eurekaclient.entity.AccessTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface AccessTokenDao extends BaseMapper<AccessTokenEntity> {
  AccessTokenEntity getLatestToken(@Param("appid") String appid);
  AccessTokenEntity getNewestToken(@Param("appid") String appid);
  int updatePvByStoreId(@Param("appid") String appid, @Param("access_token") String access_token);
  int updateAccessTokenByAppId(@Param("appid") String appid, @Param("access_token") String access_token);
}
