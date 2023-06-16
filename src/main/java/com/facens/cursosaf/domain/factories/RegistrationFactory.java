package com.facens.cursosaf.domain.factories;

import java.util.Objects;

import com.facens.cursosaf.application.dto.registration.RegistrationDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationInsertDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationUpdateDTO;
import com.facens.cursosaf.domain.entities.Registration;

public class RegistrationFactory {
	public static Registration createRegistration(RegistrationInsertDTO registrationInsertDTO){
        return Registration.builder()
                .build();
    }

    public static RegistrationDTO createRegistrationDTO(Registration registration){
        return RegistrationDTO.builder()
                .id(registration.getId())
                .user(registration.getUser())
                .course(registration.getCourse())
                .finished(registration.getFinished())
                .grades(registration.getGrades())
                .build();
    }

    public static Registration createFromUpdateDTO(RegistrationUpdateDTO registrationUpdateDTO, RegistrationDTO registrationDTO) {
        return Registration.builder()
                .id(registrationDTO.getId())
                .user(registrationDTO.getUser())
                .course(registrationDTO.getCourse())
                .finished(Objects.nonNull(registrationUpdateDTO.getFinished()) ? registrationUpdateDTO.getFinished() : registrationDTO.getFinished())
                .grades(Objects.nonNull(registrationUpdateDTO.getGrades()) ? registrationUpdateDTO.getGrades() : registrationDTO.getGrades())
                .build();
    }
}
