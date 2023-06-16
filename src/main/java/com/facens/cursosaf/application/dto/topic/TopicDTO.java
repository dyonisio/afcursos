package com.facens.cursosaf.application.dto.topic;

import java.time.LocalDateTime;

import com.facens.cursosaf.domain.entities.Course;
import com.facens.cursosaf.domain.entities.User;
import com.facens.cursosaf.domain.enums.StatusTopic;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO {
	private Long id;
    private String title;
    private String text;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private StatusTopic status;
    private User user;
	private Course course;
}
