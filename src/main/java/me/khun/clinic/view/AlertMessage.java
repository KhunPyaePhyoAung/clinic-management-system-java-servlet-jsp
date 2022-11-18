package me.khun.clinic.view;

public class AlertMessage {
	
	public enum Status {
		SUCCESS, ERROR, WARNING, INFO
	}
	
	private String message;
	private Status status;
	
	public AlertMessage() {}
	
	public AlertMessage(String message, Status status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getType() {
		return status == null ? null : status.toString().toLowerCase();
	}
}
