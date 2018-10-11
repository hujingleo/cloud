package com.suyou.cloud.controller;

import com.suyou.cloud.entity.PosterParticipantEntity;
import com.suyou.cloud.entity.PosterStyleEntity;
import com.suyou.cloud.service.PosterParticipantService;
import com.suyou.cloud.utils.BaseResp;
import com.suyou.cloud.utils.JWTUtil;
import com.suyou.cloud.utils.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 15:58:47
 */
@RestController
@RequestMapping("posterParticipant")
public class PosterParticipantController {
    @Autowired
    private PosterParticipantService posterParticipantService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public BaseResp list(){
        return posterParticipantService.list();
    }

    /**
     *获取详情
     */
    @GetMapping("/getByPosterId")
    public BaseResp getById(Integer posterId){
        return posterParticipantService.getByPosterId(posterId);
    }


    /**
     * 收藏/浏览海报
     */
    @PostMapping("/save")
    public BaseResp save(HttpServletRequest request,Integer posterId, String type) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)){
            return BaseResp.error(-3,"token非法");
        }
        PosterParticipantEntity posterParticipant = new PosterParticipantEntity();
        posterParticipant.setCreatedDate(new Date());
        posterParticipant.setOpenId(openId);
        posterParticipant.setPosterId(posterId);
        posterParticipant.setType(type);
        return posterParticipantService.save(posterParticipant);
    }
    /**
     * 参加会议
     */
    @PostMapping("/reserve")
    public BaseResp reserve(HttpServletRequest request,Integer posterId) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)){
            return BaseResp.error(-3,"token非法");
        }
        return posterParticipantService.reserve(openId,posterId);
    }
}
