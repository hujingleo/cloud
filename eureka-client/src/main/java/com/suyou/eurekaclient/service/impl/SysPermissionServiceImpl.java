package com.suyou.eurekaclient.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.SysPermissionDao;
import com.suyou.eurekaclient.entity.SysPermissionEntity;
import com.suyou.eurekaclient.service.SysPermissionService;
import org.springframework.stereotype.Service;

@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermissionEntity> implements SysPermissionService {



}
