package com.suyou.eurekaclient.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.suyou.eurekaclient.entity.PosterEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 15:58:47
 */
@Mapper
public interface PosterDao extends BaseMapper<PosterEntity> {
    List<PosterEntity> getByOpenIdAndType(@Param("openId") String openId, @Param("type") String type);
}
