package com.sui.study;

import com.sui.study.mapper.CourseMapper;
import com.sui.study.model.Course;
import com.sui.study.model.User;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
public class StudyApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	RedisTemplate<Object,Object> redisTemplate;

	@Autowired
	CourseMapper courseMapper;
	@Test
	public void contextLoads() {
		System.out.println(dataSource.getClass());
	}

	@Test
	public void redisTest(){
		Course course = courseMapper.selectCourse(1L);
		redisTemplate.opsForValue().set("course-1",course);
	}


}


