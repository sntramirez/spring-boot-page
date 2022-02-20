package com.integracion.crud.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.integracion.crud.util.Constants;

public class UsuarioRequest {

	@NotNull
	@NotBlank
	@Size(min = Constants.VALOR_SIZE_MIN_NAME, max = Constants.VALOR_SIZE_MAX_NAME)
	private String name;

	@NotNull
	@NotBlank
	@Size(max = Constants.VALOR_SIZE_MAX_EMAIL)
	@Pattern(regexp = Constants.REGEXP_EMAIL, message = Constants.MENSAJE_ERROR_REGEXP_EMAIL)
	private String email;

	@NotNull
	@NotBlank
	@Pattern(regexp = Constants.REGEXP_PASSWORD, message = Constants.MENSAJE_ERROR_REGEXP_PASSWORD)
	private String password;

	private Set<String> roles = new HashSet<>();

	private Collection<TelefonoRequest> phones;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Collection<TelefonoRequest> getPhones() {
		return phones;
	}

	public void setPhones(Collection<TelefonoRequest> phones) {
		this.phones = phones;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
