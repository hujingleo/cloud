package com.suyou.eurekaclient.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.SceneDataDao;
import com.suyou.eurekaclient.entity.SceneDataEntity;
import com.suyou.eurekaclient.service.SceneDataService;
import org.springframework.stereotype.Service;


@Service("sceneDataService")
public class SceneDataServiceImpl extends ServiceImpl<SceneDataDao, SceneDataEntity> implements SceneDataService {

    @Override
    public SceneDataEntity getBySceneId(long sceneId) {
        return baseMapper.selectById(sceneId);
    }

    @Override
    public Long saveSceneData(SceneDataEntity sceneDataEntity) {
        baseMapper.save(sceneDataEntity);
        return sceneDataEntity.getId();
    }

    @Override
    public String getSceneStrById(long id) {
        return baseMapper.getSceneStrById(id);
    }
}
