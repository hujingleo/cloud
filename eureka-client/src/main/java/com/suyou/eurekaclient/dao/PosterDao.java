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
    int countMyProduction(@Param("openId") String openId);
    int countMyEndingMeeting(@Param("openId") String openId);
    int countMyComingMeeting(@Param("openId") String openId);
    List<PosterEntity> getMyProduction(@Param("offset") int pageIndex,@Param("pageSize") int pageSize,@Param("openId") String openId);
    List<PosterEntity> getMyEndingMeeting(@Param("offset") int pageIndex,@Param("pageSize") int pageSize,@Param("openId") String openId);
    List<PosterEntity> getMyComingMeeting(@Param("offset") int pageIndex,@Param("pageSize") int pageSize,@Param("openId") String openId);
    List<UserEntity> getParticipants(@Param("posterId") int posterId);
    List<UserEntity> getReaderAvatars(@Param("offset") int pageIndex,@Param("pageSize") int pageSize,@Param("id") int id);
}
