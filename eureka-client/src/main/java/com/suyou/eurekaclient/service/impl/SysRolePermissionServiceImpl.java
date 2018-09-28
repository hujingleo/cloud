package com.suyou.eurekaclient.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.SysRolePermissionDao;
import com.suyou.eurekaclient.entity.SysRolePermissionEntity;
import com.suyou.eurekaclient.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionDao, SysRolePermissionEntity> implements SysRolePermissionService {


}
