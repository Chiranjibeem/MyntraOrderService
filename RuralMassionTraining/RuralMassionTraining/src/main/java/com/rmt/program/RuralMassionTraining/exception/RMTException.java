package com.rmt.program.RuralMassionTraining.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RMTException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public RMTException(String message) {
		super();
		this.message = message;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
