package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sun.xml.internal.rngom.parse.host.Base;
import com.suyou.eurekaclient.entity.PageEntity;
import com.suyou.eurekaclient.entity.PosterEntity;
import com.suyou.eurekaclient.service.PageService;
import com.suyou.eurekaclient.utils.BaseResp;
import com.suyou.eurekaclient.utils.PageUtils;
import com.suyou.eurekaclient.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
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
    @RequestMapping("/list")
    public BaseResp list() {
        List<PageEntity> list = pageService.selectList(new EntityWrapper<PageEntity>().eq("state", 1));
        return BaseResp.ok(list);
    }


    /**
     * 信息
     */
    @RequestMapping("/getById")
    public BaseResp info(Integer id) {
        PageEntity page = pageService.selectById(id);
        return BaseResp.ok(page);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public BaseResp save(@RequestBody PageEntity pageEntity) {
        try {
//            pageEntity.setCreatedDate(new Date());
            boolean result = pageService.insert(pageEntity);
            if (result) {
                return BaseResp.ok("添加banner成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加banner异常，异常信息为 ： " + e.getMessage());
            return BaseResp.error("添加banner异常");
        }
        return BaseResp.error("添加banner失败");
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PageEntity page) {
        pageService.updateById(page);

        return R.ok();
    }


    /**
     * 上/下架
     */
    @GetMapping("/updateState")
    public BaseResp updateState(Integer id,Integer state) {
        try {
            PageEntity entity = pageService.selectById(id);
            if (null==entity){
                return BaseResp.error("找不到id为："+id+"的banner");
            }
            if (0==state&&0==entity.getState()){
                return BaseResp.error("该banner已经下架,请勿重复操作");
            }
            if (1==state&&1==entity.getState()){
                return BaseResp.error("该banner已经上架,请勿重复操作");
            }
            entity.setState(state);
            entity.setUpdatedDate(new Date());
            boolean result = pageService.updateById(entity);
            if (result){
                return BaseResp.ok("操作成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("操作异常，异常信息为："+e.getMessage());
            return BaseResp.ok("操作异常");
        }
        return BaseResp.ok("操作失败");
    }

}
