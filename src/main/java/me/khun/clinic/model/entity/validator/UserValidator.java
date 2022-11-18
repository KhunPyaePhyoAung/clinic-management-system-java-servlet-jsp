package me.khun.clinic.model.entity.validator;

import me.khun.clinic.model.entity.User;
import me.khun.clinic.model.service.exception.InvalidFieldException;
import me.khun.clinic.util.StringUtils;

public class UserValidator {
	
	public static final boolean NAME_EMPTYABLE = false;
	public static final boolean NAME_NULLABLE = false;
	public static final int MAX_NAME_LENGTH = 50;
	public static final int MIN_NAME_LENGTH = 1;
	
	public static final boolean USERNAME_EMPTYABLE = false;
	public static final boolean USERNAME_NULLABLE = false;
	public static final int MAX_USERNAME_LENGTH = 50;
	public static final int MIN_USERNAME_LENGTH = 5;
	
	public static final boolean PASSWORD_EMPTYABLE = false;
	public static final boolean PASSWORD_NULLABLE = false;
	public static final int MAX_PASSWORD_LENGTH = 50;
	public static final int MIN_PASSWORD_LENGTH = 5;
	
	public static final boolean EMAIL_EMPTYABLE = true;
	public static final boolean EMAIL_NULLABLE = true;
	public static final int MAX_EMAIL_LENGTH = 255;
	
	public static final boolean PHONE_EMPTYABLE = false;
	public static final boolean PHONE_NULLABLE = false;
	public static final int MAX_PHONE_LENGTH = 15;
	public static final int MIN_PHONE_LENGTH = 5;
	
	public static final boolean REGISTRATION_DATE_NULLABLE = true;
	
	public static final boolean DATE_OF_BIRTH_NULLABLE = false;
	
	public static final boolean GENDER_NULLABLE = false;
	
	public static final boolean ROLE_NULLABLE = false;
	
	public static final boolean STATUS_NULLABLE = false;
	
	public static final boolean ADDRESS_NULLABLE = false;

	public static void validate(User user) {
		var name = StringUtils.isNullOrBlank(user.getName()) ? null : user.getName().trim();
		var username = StringUtils.isNullOrBlank(user.getUsername()) ? null : user.getUsername().trim();
		var password = StringUtils.isNullOrBlank(user.getPassword()) ? null : user.getPassword().trim();
		var email = StringUtils.isNullOrBlank(user.getEmail()) ? null : user.getEmail().trim();
		var phone = StringUtils.isNullOrBlank(user.getPhone()) ? null : user.getPhone().trim();
		var registrationDate = user.getRegistrationDate();
		var dateOfBirth = user.getDateOfBirth();
		var gender = user.getGender();
		var role = user.getRole();
		var status = user.getStatus();
		var address = user.getAddress();
		
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		
		if (!NAME_NULLABLE && name == null) {
			throw new InvalidFieldException("name", "Name cannot be empty.");
		}
		
		if (!NAME_EMPTYABLE && name != null && name.isBlank()) {
			throw new InvalidFieldException("name", "Name cannot be empty.");
		}
		
		if (name != null && name.length() < MIN_NAME_LENGTH) {
			throw new InvalidFieldException("name", "Name must be at least %d character(s).".formatted(MIN_NAME_LENGTH));
		}
		
		if (name != null && name.length() > MAX_NAME_LENGTH) {
			throw new InvalidFieldException("name", "Name cannot exceed %d character(s).".formatted(MAX_NAME_LENGTH));
		}
		
		if (!USERNAME_NULLABLE && username == null) {
			throw new InvalidFieldException("username", "Username cannot be empty.");
		}
		
		if (!USERNAME_EMPTYABLE && username != null && username.isBlank()) {
			throw new InvalidFieldException("username", "Username cannot be empty.");
		}
		
		if (username != null && username.length() < MIN_USERNAME_LENGTH) {
			throw new InvalidFieldException("username", "Username must be at least %d character(s)".formatted(MIN_USERNAME_LENGTH));
		}
		
		if (username != null && username.length() > MAX_USERNAME_LENGTH) {
			throw new InvalidFieldException("username", "Username cannot exceed %d character(s).".formatted(MAX_USERNAME_LENGTH));
		}
		
		if (!PASSWORD_NULLABLE && password == null) {
			throw new InvalidFieldException("passwrod", "Passwod cannot be empty.");
		}
		
		if (!PASSWORD_EMPTYABLE && password != null && password.isBlank()) {
			throw new InvalidFieldException("passwrod", "Password cannot be empty.");
		}
		
		if (password != null && password.length() < MIN_PASSWORD_LENGTH) {
			throw new InvalidFieldException("passwrod", "Password must be at least %d character(s).".formatted(MIN_PASSWORD_LENGTH));
		}
		
		if (password != null && password.length() > MAX_PASSWORD_LENGTH) {
			throw new InvalidFieldException("password", "Password cannot exceed %d character(s).".formatted(MAX_PASSWORD_LENGTH));
		}
		
		if (!EMAIL_NULLABLE && email == null) {
			throw new InvalidFieldException("email", "Email cannot be empty.");
		}
		
		if (!EMAIL_EMPTYABLE && email != null && email.isBlank()) {
			throw new InvalidFieldException("email", "Email cannot be empty.");
		}
		
		if (email != null && email.length() > MAX_EMAIL_LENGTH) {
			throw new InvalidFieldException("password", "Password cannot exceed %d character(s).".formatted(MAX_EMAIL_LENGTH));
		}
		
		if (StringUtils.notNullOrBlank(email) && !StringUtils.isEmail(email)) {
			throw new InvalidFieldException("email", "Invalid email.");
		}
		
		if (!PHONE_NULLABLE && phone == null) {
			throw new InvalidFieldException("phone", "Phone number cannot be empty.");
		}
		
		if (!PHONE_EMPTYABLE && phone != null && phone.isBlank()) {
			throw new InvalidFieldException("phone", "Phone number cannot be empty.");
		}
		
		if (phone != null && phone.length() < MIN_PHONE_LENGTH) {
			throw new InvalidFieldException("phone", "Phone number must be at least %d character(s).".formatted(MIN_PHONE_LENGTH));
		}
		
		if (phone != null && phone.length() > MAX_PHONE_LENGTH) {
			throw new InvalidFieldException("phone", "Phone number cannot exceed %d character(s).".formatted(MAX_PHONE_LENGTH));
		}
		
		if (StringUtils.notNullOrBlank(phone) && !StringUtils.isPhone(phone)) {
			throw new InvalidFieldException("phone", "Invalid phone number.");
		}
		
		if (!REGISTRATION_DATE_NULLABLE && registrationDate == null) {
			throw new InvalidFieldException("registrationDate", "Registration date cannot be empty.");
		}
		
		if (!DATE_OF_BIRTH_NULLABLE && dateOfBirth == null) {
			throw new InvalidFieldException("dateOfBorth", "Date of birth cannot be empty.");
		}
		
		if (!GENDER_NULLABLE && gender == null) {
			throw new InvalidFieldException("gender", "Gender cannot be empty");
		}
		
		if (!ROLE_NULLABLE && role == null) {
			throw new InvalidFieldException("role", "Role cannot be empty.");
		}
		
		if (!STATUS_NULLABLE && status == null) {
			throw new InvalidFieldException("status", "Status cannot be empty");
		}
		
		if (!ADDRESS_NULLABLE && address == null) {
			throw new InvalidFieldException("address", "Address cannot be empty");
		}
		
		if (address != null) {
			AddressValidator.validate(user.getAddress());			
		}
	}
}
