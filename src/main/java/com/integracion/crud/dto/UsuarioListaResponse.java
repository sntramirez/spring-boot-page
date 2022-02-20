package com.integracion.crud.dto;

import java.io.Serializable;
import java.util.Date;

public class UsuarioListaResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private Date created;
	private Date modified;
	private String last_login;
	private boolean isactive;


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

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
