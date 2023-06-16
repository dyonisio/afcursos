package com.facens.cursosaf.domain.service;

import java.util.List;

import com.facens.cursosaf.application.dto.topic.TopicDTO;
import com.facens.cursosaf.application.dto.topic.TopicInsertDTO;
import com.facens.cursosaf.application.dto.topic.TopicUpdateDTO;

public interface TopicService {
	List<TopicDTO> getAll();
	TopicDTO getById(Long id);
	TopicDTO getByTitle(String title);
	TopicDTO insertTopic(TopicInsertDTO topicInsertDTO, Long idUser, Long idCourse);
	TopicDTO updateTopicById(Long id, TopicUpdateDTO topicUpdateDTO);
    void deleteTopicById(Long id);
}
