package com.facens.cursosaf.domain.service.implementacao;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facens.cursosaf.application.dto.user.UserDTO;
import com.facens.cursosaf.application.dto.user.UserInsertDTO;
import com.facens.cursosaf.application.dto.user.UserUpdateDTO;
import com.facens.cursosaf.application.exception.BadRequestException;
import com.facens.cursosaf.domain.entities.User;
import com.facens.cursosaf.domain.factories.UserFactory;
import com.facens.cursosaf.domain.service.UserService;
import com.facens.cursosaf.infra.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class UserServiceImplementacao implements UserService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
    private UserRepository repository;

	@Override
	public List<UserDTO> getAll() {
		List<User> users = repository.findAll();
        return users.stream().map(UserFactory::createClientDTO).collect(Collectors.toList());
	}

	@Override
	public UserDTO getById(Long id) {		
		User user = repository.findById(id).orElseThrow(() -> new BadRequestException("Não foi encontrado nenhum usuário!", BAD_REQUEST));
        return UserFactory.createClientDTO(user);
	}

	@Override
	public UserDTO getByCpf(String cpf) {
		return UserFactory.createClientDTO(repository.findByCpf(cpf));
	}

	@Override
	public UserDTO insertUser(UserInsertDTO userInsertDTO) {        
        User user = UserFactory.createUser(userInsertDTO);
        
        user.setPremium(false);
        user.setCourses(0);
        user.setFinishedCourses(0);
        user.setCoins(0);
        
        User savedUser = repository.save(user);

        return UserFactory.createClientDTO(savedUser);
	}

	@Override
	public UserDTO updateUserById(Long id, UserUpdateDTO userUpdateDTO) {
		UserDTO hasUser  = getById(id);
		if(hasUser == null){
            throw new BadRequestException("Não há como atualizar o usuário inexistente!", BAD_REQUEST);
        }
		
        User user = UserFactory.createFromUpdateDTO(userUpdateDTO, hasUser);
        User savedUser = repository.save(user);
        return UserFactory.createClientDTO(savedUser);
	}

	@Override
	public void deleteUserById(Long id) {
		UserDTO hasUser  = getById(id);
		if(hasUser == null){
            throw new BadRequestException("Não há como excluir o usuário inexistente!", BAD_REQUEST);
        }
		
        repository.deleteById(hasUser.getId());		
	}
}
