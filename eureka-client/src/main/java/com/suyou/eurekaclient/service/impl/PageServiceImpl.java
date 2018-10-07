package com.suyou.eurekaclient.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.PageDao;
import com.suyou.eurekaclient.entity.PageEntity;
import com.suyou.eurekaclient.service.PageService;
import com.suyou.eurekaclient.utils.PageUtils;
import com.suyou.eurekaclient.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service("pageService")
public class PageServiceImpl extends ServiceImpl<PageDao, PageEntity> implements PageService {


    @Override
    public int save(PageEntity pageEntity) {
        return 0;
    }
}
