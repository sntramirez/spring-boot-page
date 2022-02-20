package com.integracion.crud.dto;

import java.io.Serializable;
import java.util.List;

public class MensajesResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	
	public MensajesResponse(List<MensajeResponse> error) {
		this.error = error;
	}

	private List<MensajeResponse> error;

	public List<MensajeResponse> getError() {
		return error;
	}

	public void setError(List<MensajeResponse> error) {
		this.error = error;
	}

}
