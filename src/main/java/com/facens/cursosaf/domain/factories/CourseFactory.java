package com.facens.cursosaf.domain.factories;

import java.util.Objects;

import com.facens.cursosaf.application.dto.course.CourseDTO;
import com.facens.cursosaf.application.dto.course.CourseInsertDTO;
import com.facens.cursosaf.application.dto.course.CourseUpdateDTO;
import com.facens.cursosaf.domain.entities.Course;

public class CourseFactory {
	public static Course createCourse(CourseInsertDTO courseInsertDTO){
        return Course.builder()
        		.name(courseInsertDTO.getName())
        		.category(courseInsertDTO.getCategory())
        		.duration(courseInsertDTO.getDuration())
                .build();
    }

    public static CourseDTO createCourseDTO(Course course){
        return CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
        		.category(course.getCategory())
        		.duration(course.getDuration())
                .build();
    }

    public static Course createFromUpdateDTO(CourseUpdateDTO courseUpdateDTO, CourseDTO courseDTO) {
        return Course.builder()
                .id(courseDTO.getId())
                .name(Objects.nonNull(courseUpdateDTO.getName()) ? courseUpdateDTO.getName() : courseDTO.getName())
                .category(Objects.nonNull(courseUpdateDTO.getCategory()) ? courseUpdateDTO.getCategory() : courseDTO.getCategory())
                .duration(Objects.nonNull(courseUpdateDTO.getDuration()) ? courseUpdateDTO.getDuration() : courseDTO.getDuration())
                .build();
    }
}
