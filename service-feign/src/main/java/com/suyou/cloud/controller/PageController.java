package com.suyou.cloud.controller;

import com.suyou.cloud.entity.PageEntity;
import com.suyou.cloud.service.PageService;
import com.suyou.cloud.utils.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


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
    @GetMapping("/list")
    public BaseResp list(){
        return pageService.list();
    }
    /**
     *获取详情
     */
    @GetMapping("/getById")
    public BaseResp getById(Integer id){
        return pageService.getById(id);
    }
    /**
     * 保存
     */
    @PostMapping("/save")
    public BaseResp save(@RequestBody PageEntity pageEntity) {
        pageEntity.setCreatedDate(new Date());
        return pageService.save(pageEntity);
    }
}
