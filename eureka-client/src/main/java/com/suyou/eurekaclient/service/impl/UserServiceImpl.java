package com.suyou.eurekaclient.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.suyou.eurekaclient.dao.UserDao;
import com.suyou.eurekaclient.entity.UserEntity;
import com.suyou.eurekaclient.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {


    @Override
    public UserEntity queryByOpenId(String open_id) {
        return null;
    }
}
