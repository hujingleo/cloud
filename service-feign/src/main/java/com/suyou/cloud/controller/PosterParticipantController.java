package com.suyou.cloud.controller;

import com.suyou.cloud.service.PosterParticipantService;
import com.suyou.cloud.utils.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    @RequestMapping("/list")
    public BaseResp list(){
        return posterParticipantService.list();
    }

    /**
     *获取详情
     */
    @GetMapping("/getById")
    public BaseResp getById(Integer id){
        return posterParticipantService.getById(id);
    }
}
