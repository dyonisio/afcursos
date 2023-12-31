package com.facens.cursosaf.mocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.facens.cursosaf.application.dto.registration.RegistrationDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationInsertDTO;
import com.facens.cursosaf.application.dto.registration.RegistrationUpdateDTO;
import com.facens.cursosaf.domain.entities.Registration;

public class MocksRegistration {
	public static final Long REGISTRATION_ID = 1L;
	public static final Long USER_ID = 1L;
	public static final Long COURSE_ID = 1L;
    public static final String USER_NAME=  "Mateus";
    public static final String USER_CPF = "49014753802";
    public static final String USER_EMAIL = "mateeusdyonisio@gmail.com";
    public static final String COURSE_NAME=  "Matematica";
    public static final String COURSE_CATEGORY = "Exatas";
    public static final Integer COURSE_DURATION = 2;
    public static final Boolean REGISTRATION_FINISHED = true;
    public static final List<Float> REGISTRATION_GRADES = new ArrayList<>(Arrays.asList(9F, 10F));

    public static Registration create(Long registrationId,
    		Long userId, String userName, String userCPF, String userEmail,
    		Long courseId, String courseName, String courseCategory,  Integer courseDuration){
        return Registration.builder()
                .id(registrationId)
                .user(MocksUser.create(userId, userName, userCPF, userEmail))
                .course(MocksCourse.create(courseId, courseName, courseCategory, courseDuration))
                .finished(false)
                .grades(new ArrayList<>())
                .build();
    }

    public static RegistrationDTO createDTO(Long registrationId,
    		Long userId, String userName, String userCPF, String userEmail,
    		Long courseId, String courseName, String courseCategory,  Integer courseDuration){
        return RegistrationDTO.builder()
        		.id(registrationId)
                .user(MocksUser.create(userId, userName, userCPF, userEmail))
                .course(MocksCourse.create(courseId, courseName, courseCategory, courseDuration))
                .finished(false)
                .grades(new ArrayList<>())
                .build();
    }

    public static RegistrationInsertDTO createInsertDTO() {
        return RegistrationInsertDTO.builder().build();
    }

    public static RegistrationUpdateDTO createUpdateDTO(Boolean finished, List<Float> grades) {
        return RegistrationUpdateDTO.builder()
        		.finished(finished)
                .grades(grades)
                .build();
    }
}
