package me.khun.clinic.model.service.exception;

public class DuplicateEntityException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public DuplicateEntityException(String message) {
		super(message);
	}

}
