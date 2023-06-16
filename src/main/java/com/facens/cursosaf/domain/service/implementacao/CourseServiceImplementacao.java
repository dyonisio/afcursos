package com.facens.cursosaf.domain.service.implementacao;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facens.cursosaf.application.dto.course.CourseDTO;
import com.facens.cursosaf.application.dto.course.CourseInsertDTO;
import com.facens.cursosaf.application.dto.course.CourseUpdateDTO;
import com.facens.cursosaf.application.exception.BadRequestException;
import com.facens.cursosaf.domain.entities.Course;
import com.facens.cursosaf.domain.factories.CourseFactory;
import com.facens.cursosaf.domain.service.CourseService;
import com.facens.cursosaf.infra.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CourseServiceImplementacao implements CourseService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
    private CourseRepository repository;
	
	@Override
	public List<CourseDTO> getAll() {
		List<Course> courses = repository.findAll();
        return courses.stream().map(CourseFactory::createCourseDTO).collect(Collectors.toList());
	}

	@Override
	public CourseDTO getById(Long id) {
		Course course = repository.findById(id).orElseThrow(() -> new BadRequestException("Não foi encontrado nenhum curso!", BAD_REQUEST));
        return CourseFactory.createCourseDTO(course);
	}

	@Override
	public CourseDTO getByName(String name) {
		Optional<Course> course = repository.findByName(name);
        return course.map(CourseFactory::createCourseDTO).orElse(null);
	}

	@Override
	public CourseDTO insertCourse(CourseInsertDTO courseInsertDTO) {
		Course course = CourseFactory.createCourse(courseInsertDTO);
       
		Course savedCourse = repository.save(course);

        return CourseFactory.createCourseDTO(savedCourse);
	}

	@Override
	public CourseDTO updateCourseById(Long id, CourseUpdateDTO courseUpdateDTO) {
		CourseDTO hasCourse  = getById(id);
		if(hasCourse == null){
            throw new BadRequestException("Não há como atualizar o curso inexistente!", BAD_REQUEST);
        }
		
        Course course = CourseFactory.createFromUpdateDTO(courseUpdateDTO, hasCourse);
        Course savedCourse= repository.save(course);
        return CourseFactory.createCourseDTO(savedCourse);
	}

	@Override
	public void deleteCourseById(Long id) {
		CourseDTO hasCourse  = getById(id);
		if(hasCourse == null){
            throw new BadRequestException("Não há como excluir o curso inexistente!", BAD_REQUEST);
        }
		
        repository.deleteById(hasCourse.getId());	
	}

}
