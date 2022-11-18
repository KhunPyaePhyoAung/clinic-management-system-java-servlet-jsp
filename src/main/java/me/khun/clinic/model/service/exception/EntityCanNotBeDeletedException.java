package me.khun.clinic.model.service.exception;

public class EntityCanNotBeDeletedException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public EntityCanNotBeDeletedException() {
		
	}
	
	public EntityCanNotBeDeletedException(String message) {
		super(message);
	}

}
