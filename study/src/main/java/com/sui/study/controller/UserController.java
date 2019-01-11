package com.sui.study.controller;

import com.sui.study.mapper.UserMapper;
import com.sui.study.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/selectUser/{id}")
    public User selectUser(@PathVariable("id") Long id){
        return userMapper.selectUser(id);
    }

    @RequestMapping("/saveUser")
    public User saveUser(User user){
        userMapper.saveUser(user);
        return user;
    }

    @RequestMapping("/selectAll")
    public List<User> selectAll(){
        List<User> userList= userMapper.selectAll();
        return userList;
    }
}
