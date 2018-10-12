package com.suyou.eurekaclient.controller;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.entity.PosterParticipantEntity;
import com.suyou.eurekaclient.entity.UserEntity;
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
 * @date 2018-09-28 15:58:47
 */
@Slf4j
@RestController
@RequestMapping("poster")
public class PosterController {
    @Autowired
    private PosterService posterService;
    @Autowired
    private PosterParticipantService posterParticipantService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public BaseResp list() {
        try {
            List<PosterEntity> list = posterService.selectList(new EntityWrapper<PosterEntity>());
            return BaseResp.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("海报列表接口异常，异常信息为：" + e.getMessage());
            return BaseResp.error("海报列表接口异常");
        }
    }


    /**
     * 详情
     */
    @RequestMapping("/getById")
    public BaseResp info(String openId, Integer id) {
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        try {
            PosterEntity posterEntity = posterService.selectById(id);
            if (null == posterEntity) {
                return BaseResp.error("找不到对应的海报，id为：" + id);
            }
            HashMap<String,Object> map = new HashMap<>();
            map.put("open_id",openId);
            map.put("poster_id",posterEntity.getId());
            map.put("type","READ");
            List<PosterParticipantEntity> list = posterParticipantService.selectList(new EntityWrapper<PosterParticipantEntity>().allEq(map));
            if (null==list||list.isEmpty()) {
                PosterParticipantEntity posterParticipantEntity = new PosterParticipantEntity();
                posterParticipantEntity.setOpenId(openId);
                posterParticipantEntity.setPosterId(posterEntity.getId());
                posterParticipantEntity.setType("READ");
                posterParticipantEntity.setCreatedDate(new Date());
                boolean result = posterParticipantService.insert(posterParticipantEntity);
                if (!result) {
                    log.error("海报详情接口插入访问记录失败，openId为：" + openId);
                }
            }

            return BaseResp.ok(posterEntity);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("海报详情接口异常，异常信息为：" + e.getMessage());
            return BaseResp.error("获取海报详情异常");
        }
    }


    /**
     * 获取已读用户
     */
    @RequestMapping("/getReaders")
    public BaseResp getReaders(Integer pageIndex , Integer pageSize , int id) {
        if (null==pageIndex){
            pageIndex=1;
        }
        if (null==pageSize){
            pageSize=10;
        }
        Integer offset = (pageIndex-1)*pageSize;
        List<UserEntity> readerAvatars = posterService.getReaderAvatars(offset,pageSize,id);
        return BaseResp.ok(readerAvatars);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public BaseResp save(@RequestBody PosterEntity poster) {
        try {
            int updateResult  = posterService.save(poster);
            if(1!=updateResult){
                return BaseResp.error("插入失败");
            }
                BaseResp baseResp = new BaseResp();
                baseResp.setMsg("插入成功");
                baseResp.setData(poster.getId());
                return baseResp;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加海报异常，异常信息为 ： " + e.getMessage());
        }
        return BaseResp.error("添加海报失败");
    }


    /**
     * 我的收藏
     */
    @RequestMapping("/getByOpenIdAndType")
    public BaseResp getByOpenIdAndType(String openId,String type) {
        if (StringTools.isNullOrEmpty(type)){
            return BaseResp.error("type参数不能为空");
        }
        if (StringTools.isNullOrEmpty(openId)){
            return BaseResp.error(-3, "token invalid.");
        }
        List<PosterEntity> list = posterService.getByOpenIdAndType(openId,type);
        return BaseResp.ok(list);
    }

    /**
     * 我的制作
     */
    @RequestMapping("/getMyPoster")
    public BaseResp getMyPoster(String openId) {
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        List<PosterEntity> list = posterService.selectList(new EntityWrapper<PosterEntity>().eq("created_by", openId));
        return BaseResp.ok(list);
    }
    /**
     * 我的作品
     */
    @RequestMapping("/getMyProduction")
    public BaseResp getMyProduction(String openId) {
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        List<PosterEntity> list = posterService.getMyProduction(openId);
        return BaseResp.ok(list);
    }

    /**
     * 我的会议
     */
    @RequestMapping("/getMyMeeting")
    public BaseResp getMyMeeting(String openId) {
        List<PosterEntity> list = posterService.getMyMeeting(openId);
        return BaseResp.ok(list);
    }

    /**
     * 获取海报参与者
     */
    @RequestMapping("/getParticipants")
    public BaseResp getParticipants(int posterId) {
        List<UserEntity> list = posterService.getParticipants(posterId);
        return BaseResp.ok(list);
    }

    /**
     * 参加会议
     */
    @RequestMapping("/reserveMeeting")
    public BaseResp reserveMeeting(String openId ,int id) {
        PosterEntity posterEntity = posterService.selectById(id);
        if (null==posterEntity||!posterEntity.getType().equalsIgnoreCase("MEETING")){
            return BaseResp.error("找不到id为 : "+id+" 的会议");
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("open_id",openId);
        map.put("type","RESERVED");
        map.put("poster_id",id);
        List<PosterParticipantEntity> list = posterParticipantService.selectList(new EntityWrapper<PosterParticipantEntity>().allEq(map));
        if (!list.isEmpty()){
            return BaseResp.ok("您已经参加了改会议,请勿重复参加");
        }
        PosterParticipantEntity entity = new PosterParticipantEntity();
        entity.setOpenId(openId);
        entity.setCreatedDate(new Date());
        entity.setPosterId(id);
        entity.setType("RESERVED");
        boolean result = posterParticipantService.insert(entity);
        if (result){
            return BaseResp.ok();
        }
        return BaseResp.error("参加会议失败");
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public BaseResp update(@RequestBody PosterEntity poster) {
        if (0==poster.getId()||null==posterService.selectById(poster.getId())){
            log.error("更新id非法，id为 ："+poster.getId());
            return BaseResp.error("更新id非法，id为 ："+poster.getId());
        }
        try {

        }catch (Exception e){
            e.printStackTrace();
            log.error("更新失败，id为 ： "+poster.getId());
        }
        boolean result = posterService.updateById(poster);
        if (result){
            BaseResp baseResp = new BaseResp();
            baseResp.setData(poster.getId());
            baseResp.setMsg("success");
            return baseResp;
        }
        return BaseResp.error("更新失败");
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        posterService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
