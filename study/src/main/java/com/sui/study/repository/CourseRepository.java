package com.sui.study.repository;

import com.sui.study.model.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CourseRepository extends ElasticsearchRepository<Course,Long>{

}
