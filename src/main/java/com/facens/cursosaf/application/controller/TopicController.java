package com.facens.cursosaf.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facens.cursosaf.application.dto.course.CourseDTO;
import com.facens.cursosaf.application.dto.course.CourseInsertDTO;
import com.facens.cursosaf.application.dto.course.CourseUpdateDTO;
import com.facens.cursosaf.application.dto.topic.TopicDTO;
import com.facens.cursosaf.application.dto.topic.TopicInsertDTO;
import com.facens.cursosaf.application.dto.topic.TopicUpdateDTO;
import com.facens.cursosaf.domain.service.CourseService;
import com.facens.cursosaf.domain.service.TopicService;

@RestController
@RequestMapping("/topics")
public class TopicController {
	
	@Autowired
	private TopicService service;
	
	@GetMapping
    public ResponseEntity<List<TopicDTO>> getAll(){
        List<TopicDTO> topics = service.getAll();
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getById(@PathVariable Long id){
    	TopicDTO topic = service.getById(id);
        return ResponseEntity.ok(topic);
    }
    
    @GetMapping("/title/{title}")
    public ResponseEntity<TopicDTO> getByTitle(@PathVariable String title){
    	TopicDTO topic = service.getByTitle(title);
        return ResponseEntity.ok(topic);
    }

    @PostMapping("/user/{idUser}/course/{idCourse}")
    public ResponseEntity<TopicDTO> insertTopic(@RequestBody @Validated TopicInsertDTO topicInsertDTO, @PathVariable Long idUser, @PathVariable Long idCourse){
    	TopicDTO topic = service.insertTopic(topicInsertDTO, idUser, idCourse);
        return ResponseEntity.ok(topic);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TopicDTO> updateTopicById(@PathVariable Long id, @RequestBody @Validated TopicUpdateDTO topicUpdateDTO){
    	TopicDTO topic = service.updateTopicById(id, topicUpdateDTO);
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopicById(@PathVariable Long id){
        service.deleteTopicById(id);
        return ResponseEntity.ok().build();
    }
}
