package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.service.PosterService;
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
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 15:58:47
 */
@RestController
@RequestMapping("poster")
public class PosterController {
    @Autowired
    private PosterService posterService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public BaseResp list() {
        List<PosterEntity> list = posterService.selectList(new EntityWrapper<PosterEntity>());
        return BaseResp.ok(list);
    }


    /**
     * 详情
     */
    @RequestMapping("/getById")
    public BaseResp info(Integer id) {
        PosterEntity posterEntity = posterService.selectById(id);
        return BaseResp.ok(posterEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:poster:save")
    public R save(@RequestBody PosterEntity poster) {
        posterService.insert(poster);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:poster:update")
    public R update(@RequestBody PosterEntity poster) {
        posterService.updateById(poster);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:poster:delete")
    public R delete(@RequestBody Integer[] ids) {
        posterService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
