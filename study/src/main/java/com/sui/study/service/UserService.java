package com.sui.study.service;

import com.sui.study.mapper.UserMapper;
import com.sui.study.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Cacheable(cacheNames = "user",key = "#root.method.name")
    public List<User> selectAll(){
        List<User> userList= userMapper.selectAll();
        return userList;
    }
}
