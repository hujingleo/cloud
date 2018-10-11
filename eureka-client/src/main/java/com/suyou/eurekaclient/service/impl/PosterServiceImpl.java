package com.suyou.eurekaclient.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.PosterDao;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.entity.UserEntity;
import com.suyou.eurekaclient.service.PosterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("posterService")
public class PosterServiceImpl extends ServiceImpl<PosterDao, PosterEntity> implements PosterService {


    @Override
    public List<PosterEntity> getByOpenIdAndType(String openId, String type) {
        return baseMapper.getByOpenIdAndType(openId, type);
    }

    @Override
    public List<PosterEntity> getMyProduction(String openId) {
        return baseMapper.getMyProduction(openId);
    }

    @Override
    public List<UserEntity> getParticipants(int posterId) {
        return null;
    }

    @Override
    public int save(PosterEntity posterEntity) {
        return baseMapper.savePoster(posterEntity);
    }

}
