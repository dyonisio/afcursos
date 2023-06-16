package com.facens.cursosaf.application.dto.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateDTO {
	private String name;
	private String category;
	private Integer duration;
}
