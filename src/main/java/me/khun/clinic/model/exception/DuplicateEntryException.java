package me.khun.clinic.model.exception;

public class DuplicateEntryException extends DatabaseException {
	
	private static final long serialVersionUID = 1L;
	
	public final String key;
	
	public final String value;

	public DuplicateEntryException(String key, String value, String message) {
		super(message);
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}
}
