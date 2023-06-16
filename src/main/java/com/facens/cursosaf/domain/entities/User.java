package com.facens.cursosaf.domain.entities;


import java.util.Objects;

import com.facens.cursosaf.domain.valueobj.CPF;
import com.facens.cursosaf.domain.valueobj.Email;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "TB_USER")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Embedded
	private CPF cpf;
	private String name;
	@Embedded
	private Email email;
	private String password;
	private Boolean premium;
	private Integer courses;
	private Integer finishedCourses;
	private Integer coins;
	
	@Override
	public int hashCode() {
		return Objects.hash(coins, courses, cpf, email, finishedCourses, id, name, password, premium);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(coins, other.coins) && Objects.equals(courses, other.courses)
				&& Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(finishedCourses, other.finishedCourses) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(premium, other.premium);
	}
}
