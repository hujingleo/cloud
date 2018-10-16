package com.suyou.eurekaclient.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.AccessTokenDao;
import com.suyou.eurekaclient.dao.RemindDao;
import com.suyou.eurekaclient.entity.AccessTokenEntity;
import com.suyou.eurekaclient.entity.RemindEntity;
import com.suyou.eurekaclient.service.AccessTokenService;
import com.suyou.eurekaclient.service.RemindService;
import com.suyou.eurekaclient.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * 获取微信接口凭证access_token
 */
@Slf4j
@Service("remindService")
public class RemindServiceImpl extends ServiceImpl<RemindDao, RemindEntity> implements RemindService {
}
