package com.facens.cursosaf.domain.factories;

import java.util.Objects;

import com.facens.cursosaf.application.dto.topic.TopicDTO;
import com.facens.cursosaf.application.dto.topic.TopicInsertDTO;
import com.facens.cursosaf.application.dto.topic.TopicUpdateDTO;
import com.facens.cursosaf.domain.entities.Topic;

public class TopicFactory {
	public static Topic createTopic(TopicInsertDTO topicInsertDTO){
        return Topic.builder()
                .title(topicInsertDTO.getTitle())
                .text(topicInsertDTO.getText())
                .build();
    }

    public static TopicDTO createTopicDTO(Topic topic){
        return TopicDTO.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .text(topic.getText())
                .createdAt(topic.getCreatedAt())
                .status(topic.getStatus())
                .user(topic.getUser())
                .course(topic.getCourse())
                .build();
    }

    public static Topic createFromUpdateDTO(TopicUpdateDTO topicUpdateDTO, TopicDTO topicDTO) {
        return Topic.builder()
                .id(topicDTO.getId())
                .title(Objects.nonNull(topicUpdateDTO.getTitle()) ? topicUpdateDTO.getTitle() : topicDTO.getTitle())
                .text(Objects.nonNull(topicUpdateDTO.getText()) ? topicUpdateDTO.getText() : topicDTO.getText())
                .createdAt(topicDTO.getCreatedAt())
                .status(topicDTO.getStatus())
                .user(topicDTO.getUser())
                .course(topicDTO.getCourse())
                .build();
    }
}
