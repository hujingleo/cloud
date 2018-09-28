package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.Map;

import com.suyou.eurekaclient.entity.PosterStyleEntity;
import com.suyou.eurekaclient.service.PosterStyleService;
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
 * @date 2018-09-28 15:58:46
 */
@RestController
@RequestMapping("generator/posterstyle")
public class PosterStyleController {
    @Autowired
    private PosterStyleService posterStyleService;

    /**
     * 列表
     */
//    @RequestMapping("/list")
//    @RequiresPermissions("generator:posterstyle:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = posterStyleService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:posterstyle:info")
    public R info(@PathVariable("id") Integer id){
			PosterStyleEntity posterStyle = posterStyleService.selectById(id);

        return R.ok().put("posterStyle", posterStyle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:posterstyle:save")
    public R save(@RequestBody PosterStyleEntity posterStyle){
			posterStyleService.insert(posterStyle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:posterstyle:update")
    public R update(@RequestBody PosterStyleEntity posterStyle){
			posterStyleService.updateById(posterStyle);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:posterstyle:delete")
    public R delete(@RequestBody Integer[] ids){
			posterStyleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
