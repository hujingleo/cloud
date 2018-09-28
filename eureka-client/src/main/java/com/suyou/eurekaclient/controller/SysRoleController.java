package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.Map;

import com.suyou.eurekaclient.entity.SysRoleEntity;
import com.suyou.eurekaclient.service.SysRoleService;
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
@RequestMapping("generator/sysrole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 列表
     */
//    @RequestMapping("/list")
//    @RequiresPermissions("generator:sysrole:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = sysRoleService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    @RequestMapping("/info/{oid}")
    @RequiresPermissions("generator:sysrole:info")
    public R info(@PathVariable("oid") Long oid){
			SysRoleEntity sysRole = sysRoleService.selectById(oid);

        return R.ok().put("sysRole", sysRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:sysrole:save")
    public R save(@RequestBody SysRoleEntity sysRole){
			sysRoleService.insert(sysRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:sysrole:update")
    public R update(@RequestBody SysRoleEntity sysRole){
			sysRoleService.updateById(sysRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:sysrole:delete")
    public R delete(@RequestBody Long[] oids){
			sysRoleService.deleteBatchIds(Arrays.asList(oids));

        return R.ok();
    }

}
