package me.khun.clinic.model.exception;

import me.khun.clinic.application.ApplicationException;

public class DatabaseException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public DatabaseException(String message) {
		super(message);
	}

}
