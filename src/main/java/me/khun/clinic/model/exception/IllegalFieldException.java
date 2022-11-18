package me.khun.clinic.model.exception;

import me.khun.clinic.application.ApplicationException;

public class IllegalFieldException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	private final String fieldName;
	
	public IllegalFieldException(String fieldName, String message) {
		super(message);
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
}
