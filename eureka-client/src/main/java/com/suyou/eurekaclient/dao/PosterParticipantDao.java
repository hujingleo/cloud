package com.suyou.eurekaclient.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.entity.PosterParticipantEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 15:58:46
 */
@Mapper
public interface PosterParticipantDao extends BaseMapper<PosterParticipantEntity> {
}
