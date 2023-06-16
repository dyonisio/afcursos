package com.facens.cursosaf.mocks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.facens.cursosaf.application.dto.topic.TopicDTO;
import com.facens.cursosaf.application.dto.topic.TopicInsertDTO;
import com.facens.cursosaf.application.dto.topic.TopicUpdateDTO;
import com.facens.cursosaf.domain.entities.Topic;
import com.facens.cursosaf.domain.enums.StatusTopic;

public class MocksTopic {
	public static final Long TOPIC_ID = 1L;
	public static final Long COURSE_ID = 1L;
	public static final Long USER_ID = 1L;
	public static final String TOPIC_TITLE = "Teste de Topico";
	public static final String TOPIC_TEXT = "Teste de texto de tpico";
	public static final LocalDateTime TOPIC_CREATEDAT = LocalDateTime.now();
	public static final StatusTopic TOPIC_STATUS = StatusTopic.NOT_ANSWERED;
	public static final String USER_NAME=  "Mateus";
    public static final String USER_CPF = "49014753802";
    public static final String USER_EMAIL = "mateeusdyonisio@gmail.com";
    public static final String COURSE_NAME=  "Matematica";
    public static final String COURSE_CATEGORY = "Exatas";
    public static final Integer COURSE_DURATION = 2;
	
	public static Topic create(Long topicId, String title, String text, LocalDateTime createdAt, StatusTopic status,
    		Long userId, String userName, String userCPF, String userEmail,
    		Long courseId, String courseName, String courseCategory,  Integer courseDuration){
        return Topic.builder()
                .id(topicId)
                .title(title)
                .text(text)
                .createdAt(createdAt)
                .status(status)
                .user(MocksUser.create(userId, userName, userCPF, userEmail))
                .course(MocksCourse.create(courseId, courseName, courseCategory, courseDuration))
                .build();
    }

    public static TopicDTO createDTO(Long topicId, String title, String text, LocalDateTime createdAt, StatusTopic status,
    		Long userId, String userName, String userCPF, String userEmail,
    		Long courseId, String courseName, String courseCategory,  Integer courseDuration){
        return TopicDTO.builder()
        		.id(topicId)
                .title(title)
                .text(text)
                .createdAt(createdAt)
                .status(status)
                .user(MocksUser.create(userId, userName, userCPF, userEmail))
                .course(MocksCourse.create(courseId, courseName, courseCategory, courseDuration))
                .build();
    }

    public static TopicInsertDTO createInsertDTO(String title, String text) {
        return TopicInsertDTO.builder()
        		.title(title)
        		.text(text)
        		.build();
    }

    public static TopicUpdateDTO createUpdateDTO(String title, String text) {
        return TopicUpdateDTO.builder()
        		.title(title)
                .text(text)
                .build();
    }
}
