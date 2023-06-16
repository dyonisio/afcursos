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

import com.facens.cursosaf.application.dto.registration.RegistrationUpdateDTO;
import com.facens.cursosaf.domain.service.RegistrationService;
import com.facens.cursosaf.mocks.MocksCourse;
import com.facens.cursosaf.mocks.MocksRegistration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
public class RegistrationControllerTest {
	MockMvc mockMvc;

    @InjectMocks
    @Spy
    private RegistrationController controller;

    @Mock
    private RegistrationService service;
    

    private static final String URI = "/registrations";
    
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter ow;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        ow = objectMapper.writer().withDefaultPrettyPrinter();
    }

    @DisplayName("Deve buscar registro")
    @Test
    void buscarRegistration() throws Exception {
        when(service.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Deve buscar registro por id")
    @Test
    void buscarRegistroId() throws Exception {
        when(service.getById(MocksRegistration.REGISTRATION_ID))
                .thenReturn(MocksRegistration.createDTO(MocksRegistration.COURSE_ID, 
                		MocksRegistration.USER_ID, MocksRegistration.USER_NAME, MocksRegistration.USER_CPF, MocksRegistration.USER_EMAIL,
                		MocksRegistration.COURSE_ID, MocksRegistration.COURSE_NAME, MocksRegistration.COURSE_CATEGORY, MocksRegistration.COURSE_DURATION));

        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/{id}", MocksRegistration.COURSE_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @DisplayName("Deve salvar registro")
    @Test
    void salvarRegistro() throws Exception{
        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.post(URI + "/user/{idUser}/course/{idCourse}", MocksRegistration.USER_ID, MocksRegistration.COURSE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));
    }

    
    @DisplayName("Deve atualizar registro")
    @Test
    void atualizarRegistro() throws Exception{

    	RegistrationUpdateDTO registrationUpdateDTO = MocksRegistration.createUpdateDTO(MocksRegistration.REGISTRATION_FINISHED, MocksRegistration.REGISTRATION_GRADES);

        String requestBody = ow.writeValueAsString(registrationUpdateDTO);

        Mockito.when(service.updateRegistrationById(any(), any()))
                .thenReturn(MocksRegistration.createDTO(MocksRegistration.COURSE_ID, 
                		MocksRegistration.USER_ID, MocksRegistration.USER_NAME, MocksRegistration.USER_CPF, MocksRegistration.USER_EMAIL,
                		MocksRegistration.COURSE_ID, MocksRegistration.COURSE_NAME, MocksRegistration.COURSE_CATEGORY, MocksRegistration.COURSE_DURATION));

        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.patch(URI + "/{id}", MocksCourse.COURSE_ID)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));
    }
    
    @DisplayName("Deve deletar registro")
    @Test
    void deletarRegistro() throws Exception{
        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/{id}", MocksRegistration.COURSE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));
    }
}
