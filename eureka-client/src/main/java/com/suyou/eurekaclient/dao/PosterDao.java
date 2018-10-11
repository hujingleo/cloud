package com.suyou.eurekaclient.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.entity.UserEntity;
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
    int savePoster(PosterEntity posterEntity);
    List<PosterEntity> getByOpenIdAndType(@Param("openId") String openId, @Param("type") String type);
    List<PosterEntity> getMyProduction(@Param("openId") String openId);
    List<UserEntity> getParticipants(@Param("posterId") int posterId);

}
