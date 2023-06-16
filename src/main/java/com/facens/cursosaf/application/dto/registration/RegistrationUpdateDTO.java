package com.facens.cursosaf.application.dto.registration;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationUpdateDTO {
	private Boolean finished;
	private List<Float> grades = new ArrayList<Float>();
}
