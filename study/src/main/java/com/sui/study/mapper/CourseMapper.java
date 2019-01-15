package com.sui.study.mapper;

import com.sui.study.model.Course;

public interface CourseMapper {

    public int saveCourse(Course course);

    public Course selectCourse(Long id);
}
