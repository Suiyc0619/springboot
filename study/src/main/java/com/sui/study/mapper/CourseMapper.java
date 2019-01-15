package com.sui.study.mapper;

import com.sui.study.model.Course;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface CourseMapper {

    public int saveCourse(Course course);

    public Course selectCourse(Long id);

    public List<Course> selectAllCourse();
}
