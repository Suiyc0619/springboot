package com.sui.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SqlController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @RequestMapping("/select")
    public Map<String,Object> select(){
        List<Map<String,Object>> map = jdbcTemplate.queryForList("select * from user");
        return map.get(0);
    }


}
