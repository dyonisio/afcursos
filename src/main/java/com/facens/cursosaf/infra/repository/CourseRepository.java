package com.facens.cursosaf.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.facens.cursosaf.domain.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	Optional<Course> findByName(String name);
}
