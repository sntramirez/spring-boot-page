package com.integracion.crud.dto;

import java.io.Serializable;

public class MensajeResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;
	private String message;
	private String campo;

	public MensajeResponse(String code, String message, String campo) {
		this.code = code;
		this.message = message;
		this.campo = campo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

}
