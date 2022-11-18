package me.khun.clinic.model.entity.validator;

import me.khun.clinic.model.entity.Doctor;
import me.khun.clinic.model.service.exception.InvalidFieldException;

public class DoctorValidator  extends UserValidator {
	
	public static final boolean SPECIALIST_NULLABLE = false;

	public static void validate(Doctor doctor) {
		
		UserValidator.validate(doctor);
		
		var specialist = doctor.getSpecialist();
		
		if (!SPECIALIST_NULLABLE && specialist == null) {
			throw new InvalidFieldException("specialist", "Specialist cannot be empty.");
		}

	}
}
