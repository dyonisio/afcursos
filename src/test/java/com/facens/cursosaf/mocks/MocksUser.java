package com.facens.cursosaf.mocks;

import com.facens.cursosaf.application.dto.user.UserDTO;
import com.facens.cursosaf.application.dto.user.UserInsertDTO;
import com.facens.cursosaf.application.dto.user.UserUpdateDTO;
import com.facens.cursosaf.domain.entities.User;
import com.facens.cursosaf.domain.valueobj.CPF;
import com.facens.cursosaf.domain.valueobj.Email;

public class MocksUser {
	public static final Long USER_ID = 1L;
    public static final String USER_NAME=  "Mateus";
    public static final String USER_CPF = "49014753802";
    public static final String USER_EMAIL = "mateeusdyonisio@gmail.com";

    public static User create(Long userId, String userName, String userCPF, String userEmail){
        return User.builder()
                .id(userId)
                .name(userName)
                .cpf(new CPF(userCPF))
                .email(new Email(userEmail))
                .build();
    }

    public static UserDTO createDTO(Long userId, String userName, String userCPF, String userEmail) {
        return UserDTO.builder()
        		.id(userId)
                .name(userName)
                .cpf(new CPF(userCPF).getCpf())
                .email(new Email(userEmail).getEmail())
                .build();
    }

    public static UserInsertDTO createInsertDTO(String userName, String userCPF, String userEmail) {
        return UserInsertDTO.builder()
        		.name(userName)
                .cpf(new CPF(userCPF).getCpf())
                .email(new Email(userEmail).getEmail())
                .build();
    }

    public static UserUpdateDTO createUpdateDTO(String userName, String userCPF, String userEmail) {
        return UserUpdateDTO.builder()
        		.name(userName)
                .email(new Email(userEmail).getEmail())
                .build();
    }
}
