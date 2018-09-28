package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.Map;

import com.suyou.eurekaclient.entity.PosterParticipantEntity;
import com.suyou.eurekaclient.service.PosterParticipantService;
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
@RequestMapping("generator/posterparticipant")
public class PosterParticipantController {
    @Autowired
    private PosterParticipantService posterParticipantService;

    /**
     * 列表
//     */
//    @RequestMapping("/list")
//    @RequiresPermissions("generator:posterparticipant:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = posterParticipantService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:posterparticipant:info")
    public R info(@PathVariable("id") Integer id){
			PosterParticipantEntity posterParticipant = posterParticipantService.selectById(id);

        return R.ok().put("posterParticipant", posterParticipant);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:posterparticipant:save")
    public R save(@RequestBody PosterParticipantEntity posterParticipant){
			posterParticipantService.insert(posterParticipant);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:posterparticipant:update")
    public R update(@RequestBody PosterParticipantEntity posterParticipant){
			posterParticipantService.updateById(posterParticipant);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:posterparticipant:delete")
    public R delete(@RequestBody Integer[] ids){
			posterParticipantService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}