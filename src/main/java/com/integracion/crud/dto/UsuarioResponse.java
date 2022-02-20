package com.integracion.crud.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class UsuarioResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private Date created;
	private Date modified;
	private String last_login;
	private String token;
	private boolean isactive;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

}
