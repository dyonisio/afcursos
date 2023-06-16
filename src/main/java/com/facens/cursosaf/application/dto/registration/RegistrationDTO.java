package com.facens.cursosaf.application.dto.registration;

import java.util.ArrayList;
import java.util.List;

import com.facens.cursosaf.domain.entities.Course;
import com.facens.cursosaf.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
	private Long id;
	private User user;
	private Course course;
	private Boolean finished;
	private List<Float> grades = new ArrayList<Float>();
}
