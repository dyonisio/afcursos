package com.facens.cursosaf.domain.service;

import java.util.List;

import com.facens.cursosaf.application.dto.registration.RegistrationDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationInsertDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationUpdateDTO;

public interface RegistrationService {
	List<RegistrationDTO> getAll();
	RegistrationDTO getById(Long id);
	RegistrationDTO insertRegistration(RegistrationInsertDTO registrationInsertDTO, Long idUser, Long idCourse);
	RegistrationDTO updateRegistrationById(Long id, RegistrationUpdateDTO registrationUpdateDTO);
    void deleteRegistrationById(Long id);
}
