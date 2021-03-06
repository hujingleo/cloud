package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.suyou.eurekaclient.entity.SysUserEntity;
import com.suyou.eurekaclient.service.SysUserService;
import com.suyou.eurekaclient.utils.BaseResp;
import com.suyou.eurekaclient.utils.PageUtils;
import com.suyou.eurekaclient.utils.R;
import com.suyou.eurekaclient.utils.StringTool;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 14:28:32
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public BaseResp list(){
        List<SysUserEntity> list= sysUserService.selectList(new EntityWrapper<SysUserEntity>());
        return BaseResp.ok(list);
    }


    /**
     * 信息
     */
    @RequestMapping("/login")
    public BaseResp info(String username , String password){
			SysUserEntity sysUser = sysUserService.selectOne(new EntityWrapper<SysUserEntity>().eq("username",username));
           if (null==sysUser){
               return BaseResp.error("账号不存在");
           }
            if (sysUser.getPassword().equalsIgnoreCase(password)){
                return BaseResp.ok("登录成功");
            }
        return BaseResp.error("密码错误");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:sysuser:save")
    public R save(@RequestBody SysUserEntity sysUser){
			sysUserService.insert(sysUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:sysuser:update")
    public R update(@RequestBody SysUserEntity sysUser){
			sysUserService.updateById(sysUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:sysuser:delete")
    public R delete(@RequestBody Integer[] oids){
			sysUserService.deleteBatchIds(Arrays.asList(oids));

        return R.ok();
    }

//    public static void main(String[] args) {
//        String password = StringTool.Encrypt("123456", "MD5");
//        System.out.println("MD5加密后得到: "+password);
//    }
}
