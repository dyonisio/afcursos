package com.facens.cursosaf.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertDTO {
	private String cpf;
    private String name;
    private String email;
    private String password;
}
