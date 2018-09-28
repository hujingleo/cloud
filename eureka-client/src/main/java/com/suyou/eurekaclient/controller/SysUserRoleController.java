package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.Map;

import com.suyou.eurekaclient.entity.SysUserRoleEntity;
import com.suyou.eurekaclient.service.SysUserRoleService;
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
@RequestMapping("generator/sysuserrole")
public class SysUserRoleController {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 列表
     */
//    @RequestMapping("/list")
//    @RequiresPermissions("generator:sysuserrole:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = sysUserRoleService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    @RequestMapping("/info/{oid}")
    @RequiresPermissions("generator:sysuserrole:info")
    public R info(@PathVariable("oid") Integer oid){
			SysUserRoleEntity sysUserRole = sysUserRoleService.selectById(oid);

        return R.ok().put("sysUserRole", sysUserRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:sysuserrole:save")
    public R save(@RequestBody SysUserRoleEntity sysUserRole){
			sysUserRoleService.insert(sysUserRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:sysuserrole:update")
    public R update(@RequestBody SysUserRoleEntity sysUserRole){
			sysUserRoleService.updateById(sysUserRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:sysuserrole:delete")
    public R delete(@RequestBody Integer[] oids){
			sysUserRoleService.deleteBatchIds(Arrays.asList(oids));

        return R.ok();
    }

}
