package com.integracion.crud.security.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class LoginUsuarioDto implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank
	private String email;
	@NotBlank
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
