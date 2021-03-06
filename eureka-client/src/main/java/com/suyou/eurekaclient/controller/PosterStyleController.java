package com.suyou.eurekaclient.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.suyou.eurekaclient.entity.PosterStyleEntity;
import com.suyou.eurekaclient.service.PosterStyleService;
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
 * @date 2018-09-28 15:58:46
 */
@Slf4j
@RestController
@RequestMapping("posterStyle")
public class PosterStyleController {
    @Autowired
    private PosterStyleService posterStyleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public BaseResp list(String type) {
        List<PosterStyleEntity> list = posterStyleService.selectList(new EntityWrapper<PosterStyleEntity>().eq("type",type));
        return BaseResp.ok(list);
    }


    /**
     * 信息
     */
    @RequestMapping("/getById")
    public BaseResp info(Integer id) {
        PosterStyleEntity posterStyle = posterStyleService.selectById(id);
        return BaseResp.ok(posterStyle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public BaseResp save(@RequestBody PosterStyleEntity posterStyle) {
        try {
            boolean result =         posterStyleService.insert(posterStyle);
        if (result){
            return BaseResp.ok("添加海报风格成功");
        }
        }catch (Exception e){
            e.printStackTrace();
            log.error("添加海报风格异常，异常信息为： "+ e.getMessage());
            return BaseResp.error("添加海报风格异常");
        }
        return BaseResp.error("添加海报风格失败");
    }

    /**
     * 修改
     */
    @GetMapping("/updateState")
    public BaseResp updateState(Integer id,Integer state) {
        try {
            PosterStyleEntity posterStyleEntity = posterStyleService.selectById(id);
            if (null==posterStyleEntity){
                return BaseResp.error("找不到id为："+id+"的风格");
            }
            if (0==state&&0==posterStyleEntity.getState()){
                return BaseResp.error("改风格已经下架,请勿重复操作");
            }
            if (1==state&&1==posterStyleEntity.getState()){
                return BaseResp.error("改风格已经上架,请勿重复操作");
            }
            posterStyleEntity.setState(state);
            posterStyleEntity.setUpdatedDate(new Date());
            boolean result = posterStyleService.updateById(posterStyleEntity);
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

    /**
     * 修改
     */
    @RequestMapping("/update")
    public BaseResp update(@RequestBody PosterStyleEntity posterStyle) {
        if (0==posterStyle.getId()||null==posterStyleService.selectById(posterStyle.getId())){
            log.error("更新id非法，id为 ："+posterStyle.getId());
            return BaseResp.error("更新id非法，id为 ："+posterStyle.getId());
        }
        try {

        }catch (Exception e){
            e.printStackTrace();
            log.error("更新失败，id为 ： "+posterStyle.getId());
        }
        boolean result = posterStyleService.updateById(posterStyle);
        if (result){
            BaseResp baseResp = new BaseResp();
            baseResp.setData(posterStyle.getId());
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
        posterStyleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
