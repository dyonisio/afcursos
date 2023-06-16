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
import com.facens.cursosaf.application.dto.registration.RegistrationDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationInsertDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationUpdateDTO;
import com.facens.cursosaf.domain.service.RegistrationService;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@GetMapping
    public ResponseEntity<List<RegistrationDTO>> getAll(){
        List<RegistrationDTO> registrations = service.getAll();
        return ResponseEntity.ok(registrations);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<RegistrationDTO> getById(@PathVariable Long id){
    	RegistrationDTO registration = service.getById(id);
        return ResponseEntity.ok(registration);
    }

    @PostMapping("/user/{idUser}/course/{idCourse}")
    public ResponseEntity<RegistrationDTO> insertRegistration(@RequestBody @Validated RegistrationInsertDTO registrationInsertDTO, @PathVariable Long idUser, @PathVariable Long idCourse){
    	RegistrationDTO registration = service.insertRegistration(registrationInsertDTO, idUser, idCourse);
        return ResponseEntity.ok(registration);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RegistrationDTO> updateRegistrationById(@PathVariable Long id, @RequestBody @Validated RegistrationUpdateDTO registrationUpdateDTO){
    	RegistrationDTO registration = service.updateRegistrationById(id, registrationUpdateDTO);
        return ResponseEntity.ok(registration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistrationById(@PathVariable Long id){
        service.deleteRegistrationById(id);
        return ResponseEntity.ok().build();
    }
}
