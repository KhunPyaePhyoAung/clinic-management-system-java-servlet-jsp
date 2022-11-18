package me.khun.clinic.model.entity.validator;

import me.khun.clinic.model.entity.DoctorSpecialist;
import me.khun.clinic.model.service.exception.InvalidFieldException;

public class DoctorSpecialistValidator {
	
	public static final boolean NAME_NULLABLE = false;
	public static final boolean NAME_EMPTYABLE = false;
	public static final int MIN_NAME_LENGTH = 1;
	public static final int MAX_NAME_LENGTH = 100;
	
	public static final boolean DESCRIPTION_NULLABLE = true;
	public static final int MIN_DESCRIPTION_LENGTH = 0;
	public static final int MAX_DESCRIPTION_LENGTH = 255;
	
	public static void validate(DoctorSpecialist entity) {
		
		var name = entity.getName() == null ? null : entity.getName().trim();
		entity.setName(name);
		var description = entity.getDescription() == null ? null : entity.getDescription().trim();
		entity.setDescription(description);
		
		if (!NAME_NULLABLE && name == null) {
			throw new InvalidFieldException("name", "Name cannot be empty.");
		}
		
		if (!NAME_EMPTYABLE && name != null && name.isBlank()) {
			throw new InvalidFieldException("name", "Name cannot be empty.");
		}
		
		if (name != null && name.length() < MIN_NAME_LENGTH) {
			throw new InvalidFieldException("name", "Name should be at least %d character(s).".formatted(MIN_NAME_LENGTH));
		}
		
		if (name != null && name.length() > MAX_NAME_LENGTH) {
			throw new InvalidFieldException("name", "Name should not exceed %d character(s).".formatted(MAX_NAME_LENGTH));
		}
		
		if (!DESCRIPTION_NULLABLE && description == null) {
			throw new InvalidFieldException("description", "Description cannot be empty.");
		}
		
		if (description != null && description.length() < MIN_DESCRIPTION_LENGTH) {
			throw new InvalidFieldException("description", "Description should be at least %d character(s).".formatted(MIN_DESCRIPTION_LENGTH));
		}
		
		if (description != null && description.length() > MAX_NAME_LENGTH) {
			throw new InvalidFieldException("description", "Name should not exceed %d character(s).".formatted(MAX_NAME_LENGTH));
		}
	}
}
