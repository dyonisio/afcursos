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
import com.facens.cursosaf.domain.service.TopicService;
import com.facens.cursosaf.mocks.MocksCourse;
import com.facens.cursosaf.mocks.MocksTopic;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
public class TopicControllerTest {
	MockMvc mockMvc;

    @InjectMocks
    @Spy
    private TopicController controller;

    @Mock
    private TopicService service;

    private static final String URI = "/topics";
    
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter ow;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        ow = objectMapper.writer().withDefaultPrettyPrinter();
    }

    @DisplayName("Deve buscar topicos")
    @Test
    void buscarCursos() throws Exception {
        when(service.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Deve buscar topico por id")
    @Test
    void buscarTopicoId() throws Exception {
        when(service.getById(MocksTopic.TOPIC_ID))
                .thenReturn(MocksTopic.createDTO(MocksTopic.TOPIC_ID, MocksTopic.TOPIC_TITLE, MocksTopic.TOPIC_TEXT, MocksTopic.TOPIC_CREATEDAT, MocksTopic.TOPIC_STATUS,
                		MocksTopic.USER_ID, MocksTopic.USER_NAME, MocksTopic.USER_CPF, MocksTopic.USER_EMAIL,
                		MocksTopic.COURSE_ID, MocksTopic.COURSE_NAME, MocksTopic.COURSE_CATEGORY, MocksTopic.COURSE_DURATION));

        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/{id}", MocksTopic.TOPIC_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @DisplayName("Deve salvar topico")
    @Test
    void salvarTopico() throws Exception{

        CourseInsertDTO courseInsertDTO = MocksCourse.createInsertDTO(MocksCourse.COURSE_NAME, MocksCourse.COURSE_CATEGORY, MocksCourse.COURSE_DURATION);

        String requestBody = ow.writeValueAsString(courseInsertDTO);

        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));

    }
    
    @DisplayName("Deve buscar topico por titulo")
    @Test
    void buscarTopicoTitulo() throws Exception {
        when(service.getByTitle(MocksTopic.TOPIC_TITLE))
                .thenReturn(MocksTopic.createDTO(MocksTopic.TOPIC_ID, MocksTopic.TOPIC_TITLE, MocksTopic.TOPIC_TEXT, MocksTopic.TOPIC_CREATEDAT, MocksTopic.TOPIC_STATUS,
                		MocksTopic.USER_ID, MocksTopic.USER_NAME, MocksTopic.USER_CPF, MocksTopic.USER_EMAIL,
                		MocksTopic.COURSE_ID, MocksTopic.COURSE_NAME, MocksTopic.COURSE_CATEGORY, MocksTopic.COURSE_DURATION));

        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/title/{title}", MocksTopic.TOPIC_TITLE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @DisplayName("Deve atualizar topico")
    @Test
    void atualizarTopico() throws Exception{

        TopicUpdateDTO courseUpdateDTO = MocksTopic.createUpdateDTO(MocksTopic.TOPIC_TITLE, MocksTopic.TOPIC_TEXT);

        String requestBody = ow.writeValueAsString(courseUpdateDTO);

        Mockito.when(service.updateTopicById(any(), any()))
                .thenReturn(MocksTopic.createDTO(MocksTopic.TOPIC_ID, MocksTopic.TOPIC_TITLE, MocksTopic.TOPIC_TEXT, MocksTopic.TOPIC_CREATEDAT, MocksTopic.TOPIC_STATUS,
                		MocksTopic.USER_ID, MocksTopic.USER_NAME, MocksTopic.USER_CPF, MocksTopic.USER_EMAIL,
                		MocksTopic.COURSE_ID, MocksTopic.COURSE_NAME, MocksTopic.COURSE_CATEGORY, MocksTopic.COURSE_DURATION));

        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.patch(URI + "/{id}", MocksCourse.COURSE_ID)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));
    }
    
    @DisplayName("Deve deletar topico")
    @Test
    void deletarTopico() throws Exception{
        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/{id}", MocksTopic.COURSE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));
    }
}
