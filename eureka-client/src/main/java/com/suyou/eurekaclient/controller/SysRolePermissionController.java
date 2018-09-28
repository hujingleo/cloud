package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.Map;

import com.suyou.eurekaclient.entity.SysRolePermissionEntity;
import com.suyou.eurekaclient.service.SysRolePermissionService;
import com.suyou.eurekaclient.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 14:28:32
 */
@RestController
@RequestMapping("generator/sysrolepermission")
public class SysRolePermissionController {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 列表
     */
//    @RequestMapping("/list")
//    @RequiresPermissions("generator:sysrolepermission:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = sysRolePermissionService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    @RequestMapping("/info/{oid}")
    @RequiresPermissions("generator:sysrolepermission:info")
    public R info(@PathVariable("oid") Long oid){
			SysRolePermissionEntity sysRolePermission = sysRolePermissionService.selectById(oid);

        return R.ok().put("sysRolePermission", sysRolePermission);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:sysrolepermission:save")
    public R save(@RequestBody SysRolePermissionEntity sysRolePermission){
			sysRolePermissionService.insert(sysRolePermission);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:sysrolepermission:update")
    public R update(@RequestBody SysRolePermissionEntity sysRolePermission){
			sysRolePermissionService.updateById(sysRolePermission);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:sysrolepermission:delete")
    public R delete(@RequestBody Long[] oids){
			sysRolePermissionService.deleteBatchIds(Arrays.asList(oids));

        return R.ok();
    }

}
