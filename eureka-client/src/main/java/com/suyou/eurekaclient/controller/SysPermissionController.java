package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.Map;

import com.suyou.eurekaclient.entity.SysPermissionEntity;
import com.suyou.eurekaclient.service.SysPermissionService;
import com.suyou.eurekaclient.utils.PageUtils;
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
@RequestMapping("generator/syspermission")
public class SysPermissionController {
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:syspermission:list")
    public R list(@RequestParam Map<String, Object> params){

        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{oid}")
    @RequiresPermissions("generator:syspermission:info")
    public R info(@PathVariable("oid") Long oid){
			SysPermissionEntity sysPermission = sysPermissionService.selectById(oid);

        return R.ok().put("sysPermission", sysPermission);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:syspermission:save")
    public R save(@RequestBody SysPermissionEntity sysPermission){
			sysPermissionService.insert(sysPermission);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:syspermission:update")
    public R update(@RequestBody SysPermissionEntity sysPermission){
			sysPermissionService.updateById(sysPermission);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:syspermission:delete")
    public R delete(@RequestBody Long[] oids){
			sysPermissionService.deleteBatchIds(Arrays.asList(oids));

        return R.ok();
    }

}
