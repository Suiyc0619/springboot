package com.sui.study.service;

import com.sui.study.mapper.CourseMapper;
import com.sui.study.model.Course;
import org.elasticsearch.index.mapper.SourceToParse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@CacheConfig(cacheNames = "course")
@Service
public class CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Cacheable
    public List<Course> selectAllCourse(){
        List<Course> courseList = courseMapper.selectAllCourse();
        return courseList;
    }

    @Cacheable
    public Course selectCourse(Long id){
        Course course = courseMapper.selectCourse(id);
        return course;
    }

    @RabbitListener(queues = "atguigu.emps")
    public void listnCourse(Course course){
        System.out.println(course);
        System.out.println(System.currentTimeMillis());
    }
}
