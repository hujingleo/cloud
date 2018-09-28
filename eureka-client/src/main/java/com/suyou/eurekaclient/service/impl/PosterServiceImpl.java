package com.suyou.eurekaclient.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.PosterDao;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.service.PosterService;
import org.springframework.stereotype.Service;

@Service("posterService")
public class PosterServiceImpl extends ServiceImpl<PosterDao, PosterEntity> implements PosterService {



}
