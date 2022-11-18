package me.khun.clinic.model.exception;

import me.khun.clinic.application.ApplicationException;

public class EntityNotFoundException extends ApplicationException {

	private static final long serialVersionUID = 1L;
	
	private final String entityName;

	public EntityNotFoundException(String entityName, String message) {
		super(message);
		this.entityName = entityName;
	}

	public String getEntityName() {
		return entityName;
	}
}
