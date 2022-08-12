package me.khun.clinic.model.entity;

public class Patient extends User {

	public Patient() {
	}

	private BloodGroup bloodGroup;

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	
}