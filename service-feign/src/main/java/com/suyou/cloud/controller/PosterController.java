package com.suyou.cloud.controller;

import com.suyou.cloud.entity.PosterEntity;
import com.suyou.cloud.service.PageService;
import com.suyou.cloud.service.PosterService;
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
@RequestMapping("poster")
public class PosterController {
    @Autowired
    private PosterService posterService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public BaseResp list(){
        return posterService.list();
    }

    /**
     *获取详情
     */
    @GetMapping("/getById")
    public BaseResp getById(Integer id){
        return posterService.getById(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public BaseResp save(@RequestBody PosterEntity poster) {
        poster.setCreatedDate(new Date());
        return posterService.save(poster);
    }
}
