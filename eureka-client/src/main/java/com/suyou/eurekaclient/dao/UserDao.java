package com.suyou.eurekaclient.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.suyou.eurekaclient.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 15:58:47
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
