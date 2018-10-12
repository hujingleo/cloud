package com.suyou.eurekaclient.service;


import com.baomidou.mybatisplus.service.IService;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.entity.UserEntity;

import java.util.List;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 15:58:47
 */
public interface PosterService extends IService<PosterEntity> {
    List<PosterEntity> getByOpenIdAndType(String openId,String type);
    List<PosterEntity> getMyProduction(String openId );
    List<PosterEntity> getMyEndingMeeting(Integer pageIndex , Integer pageSize,String openId );
    List<PosterEntity> getMyComingMeeting(Integer pageIndex , Integer pageSize,String openId );
    List<UserEntity> getParticipants(int posterId );
    List<UserEntity> getReaderAvatars(Integer offset , Integer pageSize , int id);
    int save(PosterEntity posterEntity);
}

