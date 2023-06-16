package com.facens.cursosaf.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facens.cursosaf.application.dto.course.CourseDTO;
import com.facens.cursosaf.application.dto.course.CourseInsertDTO;
import com.facens.cursosaf.application.dto.course.CourseUpdateDTO;
import com.facens.cursosaf.domain.service.CourseService;


@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService service;
	
	@GetMapping
    public ResponseEntity<List<CourseDTO>> getAll(){
        List<CourseDTO> courses = service.getAll();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable Long id){
    	CourseDTO course = service.getById(id);
        return ResponseEntity.ok(course);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<CourseDTO> getByName(@PathVariable String name){
    	CourseDTO course = service.getByName(name);
        return ResponseEntity.ok(course);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> insertCourse(@RequestBody @Validated CourseInsertDTO courseInsertDTO){
    	CourseDTO course = service.insertCourse(courseInsertDTO);
        return ResponseEntity.ok(course);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourseById(@PathVariable Long id, @RequestBody @Validated CourseUpdateDTO courseUpdateDTO){
    	CourseDTO course = service.updateCourseById(id, courseUpdateDTO);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long id){
        service.deleteCourseById(id);
        return ResponseEntity.ok().build();
    }
}
