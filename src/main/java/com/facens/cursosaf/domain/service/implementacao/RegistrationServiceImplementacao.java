package com.facens.cursosaf.domain.service.implementacao;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facens.cursosaf.application.dto.course.CourseDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationInsertDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationUpdateDTO;
import com.facens.cursosaf.application.dto.user.UserDTO;
import com.facens.cursosaf.application.exception.BadRequestException;
import com.facens.cursosaf.domain.entities.Course;
import com.facens.cursosaf.domain.entities.Registration;
import com.facens.cursosaf.domain.entities.User;
import com.facens.cursosaf.domain.factories.CourseFactory;
import com.facens.cursosaf.domain.factories.RegistrationFactory;
import com.facens.cursosaf.domain.factories.UserFactory;
import com.facens.cursosaf.domain.service.CourseService;
import com.facens.cursosaf.domain.service.RegistrationService;
import com.facens.cursosaf.domain.service.UserService;
import com.facens.cursosaf.infra.repository.CourseRepository;
import com.facens.cursosaf.infra.repository.RegistrationRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RegistrationServiceImplementacao implements RegistrationService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
    private RegistrationRepository repository;
	@Autowired
    private UserService userService;
	@Autowired
    private CourseService courseService;
	
	@Override
	public List<RegistrationDTO> getAll() {
		List<Registration> registrations = repository.findAll();
        return registrations.stream().map(RegistrationFactory::createRegistrationDTO).collect(Collectors.toList());
	}

	@Override
	public RegistrationDTO getById(Long id) {
		Registration registration = repository.findById(id).orElseThrow(() -> new BadRequestException("Não foi encontrado nenhum registro!", BAD_REQUEST));
        return RegistrationFactory.createRegistrationDTO(registration);
	}

	@Override
	public RegistrationDTO insertRegistration(RegistrationInsertDTO registrationInsertDTO, Long idUser, Long idCourse) {
		Registration registration = RegistrationFactory.createRegistration(registrationInsertDTO);
	       
		UserDTO userDTO = userService.getById(idUser);
        User user = UserFactory.createFromUpdateDTO(null, userDTO);
        registration.setUser(user);
        
        CourseDTO courseDTO = courseService.getById(idCourse);
        Course course = CourseFactory.createFromUpdateDTO(null, courseDTO);
        registration.setCourse(course);
        
        registration.setFinished(false);
        registration.setGrades(new ArrayList<>());
		
		Registration savedRegistration = repository.save(registration);

        return RegistrationFactory.createRegistrationDTO(savedRegistration);
	}

	@Override
	public RegistrationDTO updateRegistrationById(Long id, RegistrationUpdateDTO registrationUpdateDTO) {
		RegistrationDTO hasRegistration  = getById(id);
		if(hasRegistration == null){
            throw new BadRequestException("Não há como atualizar o registro inexistente!", BAD_REQUEST);
        }
		
		Registration registration = RegistrationFactory.createFromUpdateDTO(registrationUpdateDTO, hasRegistration);
		Registration savedRegistration = repository.save(registration);
        return RegistrationFactory.createRegistrationDTO(savedRegistration);
	}

	@Override
	public void deleteRegistrationById(Long id) {
		RegistrationDTO hasRegistration  = getById(id);
		if(hasRegistration == null){
            throw new BadRequestException("Não há como excluir o registro inexistente!", BAD_REQUEST);
        }
		
        repository.deleteById(hasRegistration.getId());	
	}
}
