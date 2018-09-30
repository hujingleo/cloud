package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.suyou.eurekaclient.entity.PageEntity;
import com.suyou.eurekaclient.service.PageService;
import com.suyou.eurekaclient.utils.BaseResp;
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
 * @date 2018-09-28 15:58:47
 */
@RestController
@RequestMapping("page")
public class PageController {
    @Autowired
    private PageService pageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public BaseResp list(){
        List<PageEntity> list = pageService.selectList(new EntityWrapper<>());
        return BaseResp.ok(list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:page:info")
    public R info(@PathVariable("id") Integer id){
			PageEntity page = pageService.selectById(id);

        return R.ok().put("page", page);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:page:save")
    public R save(@RequestBody PageEntity page){
			pageService.insert(page);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:page:update")
    public R update(@RequestBody PageEntity page){
			pageService.updateById(page);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:page:delete")
    public R delete(@RequestBody Integer[] ids){
			pageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
