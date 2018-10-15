
package com.suyou.eurekaclient.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.suyou.eurekaclient.entity.AccessTokenEntity;
import com.suyou.eurekaclient.entity.RemindEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface RemindDao extends BaseMapper<RemindEntity> {
}
