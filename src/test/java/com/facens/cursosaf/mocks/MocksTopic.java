package com.facens.cursosaf.mocks;

import java.time.LocalDateTime;
import com.facens.cursosaf.application.dto.topic.TopicDTO;
import com.facens.cursosaf.application.dto.topic.TopicInsertDTO;
import com.facens.cursosaf.application.dto.topic.TopicUpdateDTO;
import com.facens.cursosaf.domain.entities.Topic;
import com.facens.cursosaf.domain.enums.StatusTopic;

public class MocksTopic {
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
