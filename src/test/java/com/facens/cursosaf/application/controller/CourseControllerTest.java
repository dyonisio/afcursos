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
import com.facens.cursosaf.application.dto.course.CourseUpdateDTO;
import com.facens.cursosaf.domain.service.CourseService;
import com.facens.cursosaf.mocks.MocksCourse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
public class CourseControllerTest {
	MockMvc mockMvc;

    @InjectMocks
    @Spy
    private CourseController controller;

    @Mock
    private CourseService service;

    private static final String URI = "/courses";
    
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter ow;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        ow = objectMapper.writer().withDefaultPrettyPrinter();
    }

    @DisplayName("Deve buscar cursos")
    @Test
    void buscarCursos() throws Exception {
        when(service.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Deve buscar curso por id")
    @Test
    void buscarCursoId() throws Exception {
        when(service.getById(MocksCourse.COURSE_ID))
                .thenReturn(MocksCourse.createDTO(MocksCourse.COURSE_ID, MocksCourse.COURSE_NAME, MocksCourse.COURSE_CATEGORY, MocksCourse.COURSE_DURATION));

        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/{id}", MocksCourse.COURSE_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @DisplayName("Deve salvar curso")
    @Test
    void salvarCurso() throws Exception{

        CourseInsertDTO courseInsertDTO = MocksCourse.createInsertDTO(MocksCourse.COURSE_NAME, MocksCourse.COURSE_CATEGORY, MocksCourse.COURSE_DURATION);

        String requestBody = ow.writeValueAsString(courseInsertDTO);

        Mockito.when(service.insertCourse(any()))
                .thenReturn(MocksCourse.createDTO(MocksCourse.COURSE_ID, MocksCourse.COURSE_NAME, MocksCourse.COURSE_CATEGORY, MocksCourse.COURSE_DURATION));

        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.post(URI)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));

    }
    
    @DisplayName("Deve buscar curso por nome")
    @Test
    void buscarCursoName() throws Exception {
        when(service.getByName(MocksCourse.COURSE_NAME))
                .thenReturn(MocksCourse.createDTO(MocksCourse.COURSE_ID, MocksCourse.COURSE_NAME, MocksCourse.COURSE_CATEGORY, MocksCourse.COURSE_DURATION));

        mockMvc.perform(MockMvcRequestBuilders.get(URI + "/name/{name}", MocksCourse.COURSE_NAME)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @DisplayName("Deve atualizar produto")
    @Test
    void atualizarCurso() throws Exception{

        CourseUpdateDTO courseUpdateDTO = MocksCourse.createUpdateDTO(MocksCourse.COURSE_NAME, MocksCourse.COURSE_CATEGORY, MocksCourse.COURSE_DURATION);

        String requestBody = ow.writeValueAsString(courseUpdateDTO);

        Mockito.when(service.updateCourseById(any(), any()))
                .thenReturn(MocksCourse.createDTO(MocksCourse.COURSE_ID, MocksCourse.COURSE_NAME, MocksCourse.COURSE_CATEGORY, MocksCourse.COURSE_DURATION));

        assertNotNull(mockMvc.perform(MockMvcRequestBuilders.patch(URI + "/{id}", MocksCourse.COURSE_ID)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)));
    }
}
