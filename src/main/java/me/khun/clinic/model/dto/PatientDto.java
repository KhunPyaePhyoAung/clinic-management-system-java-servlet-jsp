package me.khun.clinic.model.dto;

import me.khun.clinic.model.entity.BloodGroup;

public class PatientDto extends UserDto {

	private BloodGroup bloodGroup;

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	
}