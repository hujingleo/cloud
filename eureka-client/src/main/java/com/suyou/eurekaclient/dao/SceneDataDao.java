
package com.suyou.eurekaclient.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.suyou.eurekaclient.entity.SceneDataEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:06
 */
@Mapper
public interface SceneDataDao extends BaseMapper<SceneDataEntity> {
  int save(SceneDataEntity sceneDataEntity);
  String getSceneStrById(long id);
}
