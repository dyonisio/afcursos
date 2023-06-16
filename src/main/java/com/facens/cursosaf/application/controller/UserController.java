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

import com.facens.cursosaf.application.dto.user.UserDTO;
import com.facens.cursosaf.application.dto.user.UserInsertDTO;
import com.facens.cursosaf.application.dto.user.UserUpdateDTO;
import com.facens.cursosaf.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping
    public ResponseEntity<List<UserDTO>> getAll(){
        List<UserDTO> users = service.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
    	UserDTO user = service.getById(id);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserDTO> getByCpf(@PathVariable String cpf){
    	UserDTO user = service.getByCpf(cpf);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insertUser(@RequestBody @Validated UserInsertDTO userInsertDTO){
    	UserDTO user = service.insertUser(userInsertDTO);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long id, @RequestBody @Validated UserUpdateDTO userUpdateDTO){
    	UserDTO user = service.updateUserById(id, userUpdateDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        service.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
