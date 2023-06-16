package com.facens.cursosaf.mocks;

import com.facens.cursosaf.application.dto.course.CourseDTO;
import com.facens.cursosaf.application.dto.course.CourseInsertDTO;
import com.facens.cursosaf.application.dto.course.CourseUpdateDTO;
import com.facens.cursosaf.domain.entities.Course;

public class MocksCourse {
	public static final Long COURSE_ID = 1L;
    public static final String COURSE_NAME=  "Matematica";
    public static final String COURSE_CATEGORY = "Exatas";
    public static final Integer COURSE_DURATION = 2;

    public static Course create(Long courseId, String courseName, String courseCategory, Integer courseDuration){
        return Course.builder()
                .id(courseId)
                .name(courseName)
                .category(courseCategory)
                .duration(courseDuration)
                .build();
    }

    public static CourseDTO createDTO(Long courseId, String courseName, String courseCategory, Integer courseDuration) {
        return CourseDTO.builder()
                .id(courseId)
                .name(courseName)
                .category(courseCategory)
                .duration(courseDuration)
                .build();
    }

    public static CourseInsertDTO createInsertDTO(String courseName, String courseCategory, Integer courseDuration) {
        return CourseInsertDTO.builder()
        		.name(courseName)
                .category(courseCategory)
                .duration(courseDuration)
                .build();
    }

    public static CourseUpdateDTO createUpdateDTO(String courseName, String courseCategory, Integer courseDuration) {
        return CourseUpdateDTO.builder()
        		.name(courseName)
                .category(courseCategory)
                .duration(courseDuration)
                .build();
    }
}
