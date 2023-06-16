package com.facens.cursosaf.domain.service.implementacao;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facens.cursosaf.application.dto.course.CourseDTO;
import com.facens.cursosaf.application.dto.topic.TopicDTO;
import com.facens.cursosaf.application.dto.topic.TopicInsertDTO;
import com.facens.cursosaf.application.dto.topic.TopicUpdateDTO;
import com.facens.cursosaf.application.dto.user.UserDTO;
import com.facens.cursosaf.application.exception.BadRequestException;
import com.facens.cursosaf.domain.entities.Course;
import com.facens.cursosaf.domain.entities.Topic;
import com.facens.cursosaf.domain.entities.User;
import com.facens.cursosaf.domain.enums.StatusTopic;
import com.facens.cursosaf.domain.factories.CourseFactory;
import com.facens.cursosaf.domain.factories.TopicFactory;
import com.facens.cursosaf.domain.factories.UserFactory;
import com.facens.cursosaf.domain.service.CourseService;
import com.facens.cursosaf.domain.service.TopicService;
import com.facens.cursosaf.domain.service.UserService;
import com.facens.cursosaf.infra.repository.TopicRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TopicServiceImplementacao implements TopicService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
    private TopicRepository repository;
	@Autowired
    private UserService userService;
	@Autowired
    private CourseService courseService;
	
	@Override
	public List<TopicDTO> getAll() {
		List<Topic> topics = repository.findAll();
        return topics.stream().map(TopicFactory::createTopicDTO).collect(Collectors.toList());
	}

	@Override
	public TopicDTO getById(Long id) {
		Topic topic = repository.findById(id).orElseThrow(() -> new BadRequestException("Não foi encontrado nenhum tópico!", BAD_REQUEST));
        return TopicFactory.createTopicDTO(topic);
	}

	@Override
	public TopicDTO getByTitle(String title) {
		return TopicFactory.createTopicDTO(repository.findByTitle(title));
	}

	@Override
	public TopicDTO insertTopic(TopicInsertDTO topicInsertDTO, Long idUser, Long idCourse) {
		Topic topic = TopicFactory.createTopic(topicInsertDTO);
        
		topic.setCreatedAt(LocalDateTime.now());
        topic.setStatus(StatusTopic.NOT_ANSWERED);
        
        UserDTO userDTO = userService.getById(idUser);
        User user = UserFactory.createFromUpdateDTO(null, userDTO);
        topic.setUser(user);
        
        CourseDTO courseDTO = courseService.getById(idCourse);
        Course course = CourseFactory.createFromUpdateDTO(null, courseDTO);
        topic.setCourse(course);
        
        user.setFinishedCourses(0);
        user.setCoins(0);
        
        Topic savedTopic = repository.save(topic);

        return TopicFactory.createTopicDTO(savedTopic);
	}

	@Override
	public TopicDTO updateTopicById(Long id, TopicUpdateDTO topicUpdateDTO) {
		TopicDTO hasTopic  = getById(id);
		if(hasTopic == null){
            throw new BadRequestException("Não há como atualizar o tópico inexistente!", BAD_REQUEST);
        }
		
        Topic topic = TopicFactory.createFromUpdateDTO(topicUpdateDTO, hasTopic);
        Topic savedTopic = repository.save(topic);
        return TopicFactory.createTopicDTO(savedTopic);
	}

	@Override
	public void deleteTopicById(Long id) {
		TopicDTO hasTopic  = getById(id);
		if(hasTopic == null){
            throw new BadRequestException("Não há como excluir o tópico inexistente!", BAD_REQUEST);
        }
		
        repository.deleteById(hasTopic.getId());	
	}

}
