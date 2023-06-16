package com.facens.cursosaf.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.facens.cursosaf.domain.entities.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{
	@Query(value = "SELECT * FROM TB_TOPIC WHERE TITLE = ?1", nativeQuery = true)
	Topic findByTitle(String title);
}
