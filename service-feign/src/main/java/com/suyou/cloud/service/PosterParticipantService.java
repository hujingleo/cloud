package com.suyou.cloud.service;

import com.suyou.cloud.entity.PosterParticipantEntity;
import com.suyou.cloud.utils.BaseResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-user")
public interface PosterParticipantService {
    @RequestMapping(value = "/posterParticipant/list",method = RequestMethod.GET)
    BaseResp list();
    @GetMapping(value = "/posterParticipant/getByPosterId")
    BaseResp getByPosterId(@RequestParam(value = "posterId")Integer posterId);
    @PostMapping(value = "/posterParticipant/save")
    BaseResp save(@RequestBody PosterParticipantEntity posterParticipantEntity);
}
