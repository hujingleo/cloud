package com.suyou.cloud.controller;

import com.suyou.cloud.service.PageService;
import com.suyou.cloud.utils.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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
    @RequestMapping("/list")
    public BaseResp list(){
        return pageService.list();
    }

}
