package me.khun.clinic.model.repo.exception;

import me.khun.clinic.application.ApplicationException;

public class DataAccessException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public DataAccessException(String message) {
		super(message);
	}

}
