package com.facens.cursosaf.domain.factories;

import java.util.Objects;

import com.facens.cursosaf.application.dto.user.UserDTO;
import com.facens.cursosaf.application.dto.user.UserInsertDTO;
import com.facens.cursosaf.application.dto.user.UserUpdateDTO;
import com.facens.cursosaf.domain.entities.User;
import com.facens.cursosaf.domain.valueobj.CPF;
import com.facens.cursosaf.domain.valueobj.Email;

public class UserFactory {
	public static User createUser(UserInsertDTO userInsertDTO){
        return User.builder()
                .name(userInsertDTO.getName())
                .cpf(CPF.builder().cpf(userInsertDTO.getCpf()).build())
                .email(Email.builder().email(userInsertDTO.getEmail()).build())
                .password(userInsertDTO.getPassword())
                .build();
    }

    public static UserDTO createClientDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .cpf(user.getCpf().getCpf())
                .name(user.getName())
                .email(user.getEmail().getEmail())
                .premium(user.getPremium())
                .courses(user.getCourses())
                .finishedCourses(user.getFinishedCourses())
                .coins(user.getCoins())
                .build();
    }

    public static User createFromUpdateDTO(UserUpdateDTO userUpdateDTO, UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .cpf(new CPF(userDTO.getCpf()))
                .name(userUpdateDTO.getName())
                .email(new Email(Objects.nonNull(userUpdateDTO.getEmail()) ? userUpdateDTO.getEmail() : userDTO.getEmail()))
                .premium(userDTO.getPremium())
                .courses(userDTO.getCourses())
                .finishedCourses(userDTO.getFinishedCourses())
                .coins(userDTO.getCoins())
                .build();
    }
}
