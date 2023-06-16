package com.facens.cursosaf.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.facens.cursosaf.domain.entities.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {}