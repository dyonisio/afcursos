package com.facens.cursosaf.domain.service;

import java.util.List;

import com.facens.cursosaf.application.dto.user.UserDTO;
import com.facens.cursosaf.application.dto.user.UserInsertDTO;
import com.facens.cursosaf.application.dto.user.UserUpdateDTO;

public interface UserService {
	List<UserDTO> getAll();
	UserDTO getById(Long id);
	UserDTO getByCpf(String cpf);
	UserDTO insertUser(UserInsertDTO userInsertDTO);
	UserDTO updateUserById(Long id, UserUpdateDTO userUpdateDTO);
    void deleteUserById(Long id);
}
