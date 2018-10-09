package com.suyou.eurekaclient.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.PosterParticipantDao;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.entity.PosterParticipantEntity;
import com.suyou.eurekaclient.service.PosterParticipantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("posterParticipantService")
public class PosterParticipantServiceImpl extends ServiceImpl<PosterParticipantDao, PosterParticipantEntity> implements PosterParticipantService {


}
