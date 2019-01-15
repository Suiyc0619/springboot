package com.sui.study.controller;

import com.sui.study.mapper.CourseMapper;
import com.sui.study.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @Autowired
    CourseMapper courseMapper;

    @RequestMapping("/saveCourse")
    public Course saveCourse(Course course){
        courseMapper.saveCourse(course);
        return course;
    }

    @RequestMapping("/selectCourse/{id}")
    public Course selectCourse(@PathVariable("id") Long id){
        Course course = courseMapper.selectCourse(id);
        return course;
    }

}
