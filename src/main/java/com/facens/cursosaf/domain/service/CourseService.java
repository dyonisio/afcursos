package com.facens.cursosaf.domain.service;

import java.util.List;

import com.facens.cursosaf.application.dto.course.CourseDTO;
import com.facens.cursosaf.application.dto.course.CourseInsertDTO;
import com.facens.cursosaf.application.dto.course.CourseUpdateDTO;

public interface CourseService {
	List<CourseDTO> getAll();
	CourseDTO getById(Long id);
	CourseDTO getByName(String name);
	CourseDTO insertCourse(CourseInsertDTO courseInsertDTO);
	CourseDTO updateCourseById(Long id, CourseUpdateDTO courseUpdateDTO);
    void deleteCourseById(Long id);
}
