package com.suyou.cloud.controller;

import com.suyou.cloud.entity.PosterStyleEntity;
import com.suyou.cloud.service.PosterService;
import com.suyou.cloud.service.PosterStyleService;
import com.suyou.cloud.utils.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 15:58:47
 */
@RestController
@RequestMapping("posterStyle")
public class PosterStyleController {
    @Autowired
    private PosterStyleService posterStyleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public BaseResp list(){
        return posterStyleService.list();
    }

    /**
     *获取详情
     */
    @GetMapping("/getById")
    public BaseResp getById(Integer id){
        return posterStyleService.getById(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public BaseResp save(@RequestBody PosterStyleEntity posterStyle) {
        posterStyle.setCreatedDate(new Date());
        return posterStyleService.save(posterStyle);
    }
}
