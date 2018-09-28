package com.suyou.eurekaclient.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.PosterStyleDao;
import com.suyou.eurekaclient.entity.PosterStyleEntity;
import com.suyou.eurekaclient.service.PosterStyleService;
import org.springframework.stereotype.Service;

@Service("posterStyleService")
public class PosterStyleServiceImpl extends ServiceImpl<PosterStyleDao, PosterStyleEntity> implements PosterStyleService {


}
