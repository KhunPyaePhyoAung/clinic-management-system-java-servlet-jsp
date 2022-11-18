package me.khun.clinic.model.dto;

import java.time.LocalDate;

import me.khun.clinic.model.entity.Gender;
import me.khun.clinic.model.entity.User;
import me.khun.clinic.model.entity.User.Role;
import me.khun.clinic.model.entity.User.Status;

public class UserDto extends Dto<User> {

	protected Long id;

	protected String name;

	protected String username;
	
	protected String password;

	protected LocalDate registrationDate;

	protected String email;

	protected String phone;

	protected LocalDate dateOfBirth;

	protected String street;

	protected String city;

	protected String state;

	protected String country;

	protected Gender gender;

	protected Role role;

	protected Status status;
	
	public UserDto() {
		
	}
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.registrationDate = user.getRegistrationDate();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.dateOfBirth = user.getDateOfBirth();
		this.street = user.getAddress().getStreet();
		this.city = user.getAddress().getCity();
		this.state = user.getAddress().getState();
		this.country = user.getAddress().getCountry();
		this.gender = user.getGender();
		this.role = user.getRole();
		this.status = user.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}