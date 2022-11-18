package me.khun.clinic.model.service.exception;

public class InvalidFieldException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	private final String FIELD_NAME;

	public InvalidFieldException(String fieldName, String message) {
		super(message);
		this.FIELD_NAME = fieldName;
	}
	
	public String getFieldName() {
		return FIELD_NAME;
	}

}
