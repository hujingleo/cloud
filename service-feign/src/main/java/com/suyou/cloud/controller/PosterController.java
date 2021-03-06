package com.suyou.cloud.controller;

import com.suyou.cloud.entity.PosterEntity;
import com.suyou.cloud.service.PosterService;
import com.suyou.cloud.utils.BaseResp;
import com.suyou.cloud.utils.JWTUtil;
import com.suyou.cloud.utils.StringTools;
import com.suyou.cloud.utils.qiniu.QiNiuUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @GetMapping("/list")
    public BaseResp list() {
        return posterService.list();
    }

    /**
     * 获取详情
     */
    @GetMapping("/getById")
    public BaseResp getById(HttpServletRequest request, Integer id) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.getById(openId, id);
    }

    /**
     * 获取已读用户
     */
    @RequestMapping("/getReaders")
    public BaseResp getReaders(Integer pageIndex , Integer pageSize , int id) {
        return posterService.getReaders(pageIndex,pageSize,id);
    }

    /**
     * 我的收藏
     */
    @RequestMapping("/getByOpenIdAndType")
    public BaseResp getByOpenIdAndType(HttpServletRequest request, String type) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.getByOpenIdAndType(openId, type);
    }

    /**
     * 我的制作
     */
    @RequestMapping("/getMyPoster")
    public BaseResp getMyPoster(HttpServletRequest request) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.getMyPoster(openId);
    }

    /**
     * 我的作品
     */
    @RequestMapping("/getMyProduction")
    public BaseResp getMyProduction(Integer pageIndex , Integer pageSize,HttpServletRequest request) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.getMyProduction(pageIndex,pageSize,openId);
    }


    /**
     * 参加会议
     */
    @RequestMapping("/reserveMeeting")
    public BaseResp reserveMeeting(HttpServletRequest request ,int id) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)){
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.reserveMeeting(openId,id);
    }

    /**
     * 我的会议--指已经参加且结束的会议
     */
    @RequestMapping("/getMyEndingMeeting")
    public BaseResp getMyEndingMeeting(Integer pageIndex , Integer pageSize,HttpServletRequest request) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)){
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.getMyEndingMeeting(pageIndex,pageSize,openId);
    }

    /**
     * 待办会议--指已经参加还未结束的会议
     */
    @RequestMapping("/getMyComingMeeting")
    public BaseResp getMyComingMeeting(Integer pageIndex , Integer pageSize,HttpServletRequest request) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)){
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.getMyComingMeeting(pageIndex,pageSize,openId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public BaseResp save(@RequestBody PosterEntity poster, HttpServletRequest request) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        if (StringTools.isNullOrEmpty(poster.getName())){
            return BaseResp.error("名字不能为空");
        }
        if (StringTools.isNullOrEmpty(poster.getType())){
            return BaseResp.error("类型不能为空");
        }
        if (StringTools.isNullOrEmpty(poster.getContent())){
            return BaseResp.error("内容不能为空");
        }
        if (StringTools.isNullOrEmpty(poster.getTitle())){
            return BaseResp.error("标题不能为空");
        }
        poster.setCreatedBy(openId);
        poster.setCreatedDate(new Date());
        if (null != poster.getPictureList() && !poster.getPictureList().isEmpty()) {
            String pictures = String.join(",", poster.getPictureList());
            poster.setPictures(pictures);
            poster.setPictureList(null);
        }
        return posterService.save(poster);
    }

    /**
     * 我的制作
     */
    @RequestMapping("/getQiNiuUpToken")
    public BaseResp getQiNiuUpToken() {
        String upToken = QiNiuUtils.getToken();
        if (StringTools.isNullOrEmpty(upToken)) {
            return BaseResp.error("获取七牛token失败");
        }
        return BaseResp.ok(upToken);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public BaseResp update(HttpServletRequest request, @RequestBody PosterEntity poster) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.update(poster);
    }


    /**
     * 删除我的作品
     */
    @RequestMapping("/deleteMyProduction")
    public BaseResp deleteMyProductionRecord(HttpServletRequest request, Integer id) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.deleteMyProduction(openId,id);
    }
    /**
     * 删除待办会议
     */
    @RequestMapping("/deleteMyComingMeetingRecord")
    public BaseResp deleteMyComingMeetingRecord(HttpServletRequest request, Integer id) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.deleteMyComingMeetingRecord(openId,id);
    }

    /**
     * 删除我的会议
     */
    @RequestMapping("/deleteMyEndingMeetingRecord")
    public BaseResp deleteMyEndingMeetingRecord(HttpServletRequest request, Integer id) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return posterService.deleteMyEndingMeetingRecord(openId,id);
    }
    /**
     *上/下架海报
     */
    @GetMapping("/updateState")
    public BaseResp updateState(Integer id,Integer state){
        return posterService.updateState(id,state);
    }
}
