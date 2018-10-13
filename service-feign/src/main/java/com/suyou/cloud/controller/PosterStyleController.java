package com.suyou.cloud.controller;

import com.suyou.cloud.entity.PosterStyleEntity;
import com.suyou.cloud.service.PosterService;
import com.suyou.cloud.service.PosterStyleService;
import com.suyou.cloud.utils.BaseResp;
import com.suyou.cloud.utils.StringTools;
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
     *下架风格
     */
    @PostMapping("/updateStatus")
    public BaseResp unShelve(Integer id,Integer status){
        return posterStyleService.updateStatus(id,status);
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
