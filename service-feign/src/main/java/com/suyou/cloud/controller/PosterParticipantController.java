package com.suyou.cloud.controller;

import com.suyou.cloud.entity.PosterParticipantEntity;
import com.suyou.cloud.entity.PosterStyleEntity;
import com.suyou.cloud.service.PosterParticipantService;
import com.suyou.cloud.utils.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 保存
     */
    @PostMapping("/save")
    public BaseResp save(Integer userId,Integer posterId,int type) {
        PosterParticipantEntity posterParticipant = new PosterParticipantEntity();
        posterParticipant.setCreatedDate(new Date());
        posterParticipant.setUserId(userId);
        posterParticipant.setPosterId(posterId);
        posterParticipant.setType(type);
        return posterParticipantService.save(posterParticipant);
    }
}
