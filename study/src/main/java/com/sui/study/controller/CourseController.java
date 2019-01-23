package com.sui.study.controller;

import com.sui.study.mapper.CourseMapper;
import com.sui.study.model.Course;
import com.sui.study.repository.CourseRepository;
import com.sui.study.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/saveCourse")
    public Course saveCourse(Course course){
        courseMapper.saveCourse(course);
        return course;
    }

    @RequestMapping("/selectCourse/{id}")
    public Course selectCourse(@PathVariable("id") Long id){
        Course course = courseService.selectCourse(id);
        return course;
    }

    @RequestMapping("/selectAllCourse")
    public List<Course> selectAllCourse(){
        List<Course> courseList = courseService.selectAllCourse();
        return courseList;
    }

    @RequestMapping("/elasticCourse")
    public void elasticTest(){
        Course course = courseMapper.selectCourse(1L);
        courseRepository.index(course);
    }

}
