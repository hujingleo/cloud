package com.suyou.eurekaclient.service;


import com.baomidou.mybatisplus.service.IService;
import com.suyou.eurekaclient.entity.SceneDataEntity;

/**
 * 用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:06
 */
public interface SceneDataService extends IService<SceneDataEntity> {
    SceneDataEntity getBySceneId(long sceneId);
    Long saveSceneData(SceneDataEntity sceneDataEntity);
    String getSceneStrById(long id);
}
