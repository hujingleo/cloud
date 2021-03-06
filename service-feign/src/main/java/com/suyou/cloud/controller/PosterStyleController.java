package com.suyou.cloud.controller;

import com.suyou.cloud.entity.PosterEntity;
import com.suyou.cloud.entity.PosterStyleEntity;
import com.suyou.cloud.service.PosterService;
import com.suyou.cloud.service.PosterStyleService;
import com.suyou.cloud.utils.BaseResp;
import com.suyou.cloud.utils.JWTUtil;
import com.suyou.cloud.utils.StringTools;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
@RequestMapping("posterStyle")
public class PosterStyleController {
    @Autowired
    private PosterStyleService posterStyleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public BaseResp list(String type){
        if (StringTools.isNullOrEmpty(type)){
            return BaseResp.error("type参数不能为空");
        }
        return posterStyleService.list(type);
    }

    /**
     *获取详情
     */
    @GetMapping("/getById")
    public BaseResp getById(Integer id){
        return posterStyleService.getById(id);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public BaseResp update(HttpServletRequest request, @RequestBody PosterStyleEntity entity) {
        String openId = JWTUtil.getCurrentUserOpenId(request);
        if (StringTools.isNullOrEmpty(openId)) {
            return BaseResp.error(-3, "token invalid.");
        }
        return posterStyleService.update(entity);
    }

    /**
     *上/下架风格
     */
    @GetMapping("/updateState")
    public BaseResp updateState(Integer id,Integer state){
        return posterStyleService.updateState(id,state);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public BaseResp save(@RequestBody PosterStyleEntity posterStyle) {
        if (StringTools.isNullOrEmpty(posterStyle.getName())){
            return BaseResp.error("风格名称不能为空");
        }
        if (StringTools.isNullOrEmpty(posterStyle.getContent())){
            return BaseResp.error("风格内容不能为空");
        }
        if (StringTools.isNullOrEmpty(posterStyle.getStructure())){
            return BaseResp.error("风格结构不能为空");
        }
//        if (StringTools.isNullOrEmpty(posterStyle.getImageUrl())){
//            return BaseResp.error("风格图片不能为空");
//        }
        if (StringTools.isNullOrEmpty(posterStyle.getType())){
            return BaseResp.error("风格类型不能为空");
        }
        posterStyle.setCreatedDate(new Date());
        return posterStyleService.save(posterStyle);
    }
}
