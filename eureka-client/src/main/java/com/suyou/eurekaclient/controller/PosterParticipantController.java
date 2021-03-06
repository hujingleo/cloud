package com.suyou.eurekaclient.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sun.xml.internal.rngom.parse.host.Base;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.entity.PosterParticipantEntity;
import com.suyou.eurekaclient.service.PosterParticipantService;
import com.suyou.eurekaclient.service.PosterService;
import com.suyou.eurekaclient.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
 * @date 2018-09-28 15:58:46
 */
@Slf4j
@RestController
@RequestMapping("posterParticipant")
public class PosterParticipantController {
    @Autowired
    private PosterParticipantService posterParticipantService;
    @Autowired
    private PosterService posterService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public BaseResp list() {
        List<PosterParticipantEntity> list = posterParticipantService.selectList(new EntityWrapper<>());
        return BaseResp.ok(list);
    }


    /**
     * 信息
     */
    @RequestMapping("/getByPosterId")
    public BaseResp info(Integer posterId) {
        PosterParticipantEntity posterParticipantEntity = posterParticipantService.selectOne(new EntityWrapper<PosterParticipantEntity>().eq("poster_id", posterId));
        return BaseResp.ok(posterParticipantEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public BaseResp save(@RequestBody PosterParticipantEntity posterParticipant) {
        try {
            boolean result = posterParticipantService.insert(posterParticipant);
            if (result){
                return BaseResp.ok("添加用户海报关联成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加用户海报关联异常，异常信息为 ： " + e.getMessage());
            return BaseResp.error("添加用户海报关联异常");
        }
        return BaseResp.error("添加用户海报关联异常");
    }

    /**
     * 参加会议
     */
    @RequestMapping("/reserve")
    public BaseResp reserve(String openId,int posterId) {
        if (null==posterService.selectById(posterId)){
            return BaseResp.error("找不到对应的会议，id为 ： "+posterId);
        }
        PosterParticipantEntity entity = new PosterParticipantEntity();
        entity.setType("RESERVED");
        entity.setPosterId(posterId);
        entity.setOpenId(openId);
        entity.setCreatedDate(new Date());
        boolean result = posterParticipantService.insert(entity);
        if (result){
            return BaseResp.ok("添加成功");
        }
        return BaseResp.error("添加失败");
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PosterParticipantEntity posterParticipant) {
        posterParticipantService.updateById(posterParticipant);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public BaseResp delete(String openId ,Integer id) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("created_by",openId);
        map.put("id",id);
        return BaseResp.ok();
    }

}
