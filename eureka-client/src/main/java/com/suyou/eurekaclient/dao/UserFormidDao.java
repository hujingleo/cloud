package com.suyou.eurekaclient.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.suyou.eurekaclient.entity.UserFormidEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-31 10:52:33
 */
@Mapper
public interface UserFormidDao extends BaseMapper<UserFormidEntity> {
	UserFormidEntity getByFormId(@Param("form_id") String form_id);
	UserFormidEntity getByOpenId(@Param("open_id") String open_id);
}
