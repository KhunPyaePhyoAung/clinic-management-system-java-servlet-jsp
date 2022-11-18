package me.khun.clinic.model.service.exception;

import me.khun.clinic.application.ApplicationException;

public class ServiceException extends ApplicationException {
	
	private static final long serialVersionUID = 1L;
	
	
	public ServiceException() {
	}
	
	public ServiceException(String message) {
		super(message);
	}

}
