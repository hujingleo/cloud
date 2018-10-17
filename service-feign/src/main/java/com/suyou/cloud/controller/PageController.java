package com.suyou.cloud.controller;

import com.suyou.cloud.entity.PageEntity;
import com.suyou.cloud.service.PageService;
import com.suyou.cloud.utils.BaseResp;
import com.suyou.cloud.utils.JWTUtil;
import com.suyou.cloud.utils.StringTools;
import com.suyou.cloud.utils.qiniu.QiNiuUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
@Slf4j
@RestController
@RequestMapping("page")
public class PageController {
    @Autowired
    private PageService pageService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public BaseResp list(HttpServletRequest request){
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
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
    /**
     * 上传图片至七牛云
     */
    @PostMapping("/upload")
    public BaseResp upload(String image) {
        byte[] images = null;
        try {
            images = StringTools.base64String2ByteFun(image);
        }catch (Exception e){
        e.printStackTrace();
        log.error("base64字符串转byte[]失败");
        return BaseResp.error("base64字符串转byte[]失败");
        }
        String reslut = QiNiuUtils.upload(images);
        return BaseResp.ok(reslut);
    }
    /**
     *上/下架banner
     */
    @GetMapping("/updateState")
    public BaseResp updateState(Integer id,Integer state){
        if (null==id){
            return BaseResp.error("id不能为空");
        }
        if (null==state){
            return BaseResp.error("state不能为空");
        }
        return pageService.updateState(id,state);
    }
}
