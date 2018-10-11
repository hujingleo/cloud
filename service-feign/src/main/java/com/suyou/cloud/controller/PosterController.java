package com.suyou.cloud.controller;

import com.suyou.cloud.entity.PosterEntity;
import com.suyou.cloud.service.PageService;
import com.suyou.cloud.service.PosterService;
import com.suyou.cloud.utils.BaseResp;
import com.suyou.cloud.utils.JWTUtil;
import com.suyou.cloud.utils.StringTools;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseResp getById(HttpServletRequest request , Integer id){
        String openId = JWTUtil.getCurrentUserOpenId(request);
        return posterService.getById(openId,id);
    }

    /**
     * 我的收藏
     */
    @RequestMapping("/getByOpenIdAndType")
    public BaseResp getByOpenIdAndType(HttpServletRequest request,String type) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        return posterService.getByOpenIdAndType(openId,type);
    }
    /**
     * 我的制作
     */
    @RequestMapping("/getMyPoster")
    public BaseResp getMyPoster(HttpServletRequest request) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        return posterService.getMyPoster(openId);
    }
    /**
     * 保存
     */
    @RequestMapping("/save")
    public BaseResp save(@RequestBody PosterEntity poster,HttpServletRequest request) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (!StringTools.isNullOrEmpty(openId)){
            poster.setCreatedBy(openId);
        }
        poster.setCreatedDate(new Date());
        if (null!=poster.getPictureList()&&!poster.getPictureList().isEmpty()){
            String pictures = String.join(",", poster.getPictureList());
            poster.setPictures(pictures);
            poster.setPictureList(null);
        }
        return posterService.save(poster);
    }
}
