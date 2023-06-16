package com.facens.cursosaf.application.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.facens.cursosaf.application.dto.course.CourseInsertDTO;
import com.facens.cursosaf.application.dto.topic.TopicUpdateDTO;
import com.facens.cursosaf.application.dto.user.UserInsertDTO;
import com.facens.cursosaf.application.dto.user.UserUpdateDTO;
import com.facens.cursosaf.domain.service.TopicService;
import com.facens.cursosaf.domain.service.UserService;
import com.facens.cursosaf.mocks.MocksCourse;
import com.facens.cursosaf.mocks.MocksTopic;
import com.facens.cursosaf.mocks.MocksUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
public class UserControllerTest {
	MockMvc mockMvc;

    @InjectMocks
    @Spy
    private UserController controller;

    @Mock
    private UserService service;

    private static final String URI = "/users";
    
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter ow;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        ow = objectMapper.writer().withDefaultPrettyPrinter();
    }

    @DisplayName("Deve buscar users")
    @Test
    void buscarUsers() throws Exception {
        when(service.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Deve buscar users por id")
    @Test
    void buscarUserId() throws Exception {
        when(service.getById(MocksUser.USER_ID))
                .thenReturn(MocksUser.createDTO(MocksUser.USER_ID, MocksUser.USER_NAME, MocksUser.USER_CPF, MocksUser.USER_EMAIL));

        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/{id}", MocksUser.USER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @DisplayName("Deve salvar usuario")
    @Test
    void salvarUsuario() throws Exception{

        UserInsertDTO userInsertDTO = MocksUser.createInsertDTO(MocksUser.USER_NAME, MocksUser.USER_CPF, MocksUser.USER_EMAIL);

        String requestBody = ow.writeValueAsString(userInsertDTO);

        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));

    }
    
    @DisplayName("Deve buscar usuario por cpf")
    @Test
    void buscarUsuarioCPF() throws Exception {
        when(service.getByCpf(MocksUser.USER_CPF))
                .thenReturn(MocksUser.createDTO(MocksUser.USER_ID, MocksUser.USER_NAME, MocksUser.USER_CPF, MocksUser.USER_EMAIL));

        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/cpf/{cpf}", MocksUser.USER_CPF)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @DisplayName("Deve atualizar usuario")
    @Test
    void atualizarUsuario() throws Exception{

        UserUpdateDTO userUpdateDTO = MocksUser.createUpdateDTO(MocksUser.USER_NAME, MocksUser.USER_CPF, MocksUser.USER_EMAIL);

        String requestBody = ow.writeValueAsString(userUpdateDTO);

        Mockito.when(service.updateUserById(any(), any()))
                .thenReturn(MocksUser.createDTO(MocksUser.USER_ID, MocksUser.USER_NAME, MocksUser.USER_CPF, MocksUser.USER_EMAIL));

        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.patch(URI + "/{id}", MocksUser.USER_ID)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));
    }
    
    @DisplayName("Deve deletar topico")
    @Test
    void deletarUsuario() throws Exception{
        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/{id}", MocksUser.USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));
    }
}
