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
    public List<PosterEntity> getMyProduction(Integer offset , Integer pageSize,String openId) {
        return baseMapper.getMyProduction(offset,pageSize,openId);
    }

    @Override
    public List<PosterEntity> getMyEndingMeeting(Integer offset , Integer pageSize,String openId) {
        return baseMapper.getMyEndingMeeting(offset,pageSize,openId);
    }

    @Override
    public List<PosterEntity> getMyComingMeeting(Integer offset , Integer pageSize,String openId) {
        return baseMapper.getMyComingMeeting(offset,pageSize,openId);
    }

    @Override
    public List<UserEntity> getParticipants(int posterId) {
        return null;
    }

    @Override
    public List<UserEntity> getReaderAvatars(Integer offset , Integer pageSize , int id) {
        return baseMapper.getReaderAvatars(offset,pageSize,id);
    }

    @Override
    public int save(PosterEntity posterEntity) {
        return baseMapper.savePoster(posterEntity);
    }

}
