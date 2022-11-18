package me.khun.clinic.model.dto;

import me.khun.clinic.model.entity.Doctor;

public class DoctorDto extends UserDto {

	private DoctorSpecialistDto specialist;
	
	public DoctorDto() {
		
	}
	
	public DoctorDto(Doctor doctor) {
		super(doctor);
		this.specialist = new DoctorSpecialistDto(doctor.getSpecialist());
	}

	public DoctorSpecialistDto getSpecialist() {
		return specialist;
	}

	public void setSpecialist(DoctorSpecialistDto specialist) {
		this.specialist = specialist;
	}
	
	public Long getSpecialistId() {
		return specialist.getId();
	}
	
	public String getSpecialistName() {
		return specialist == null ? null : specialist.getName();
	}
	
	public String getSpecialistDescription() {
		return specialist == null ? null : specialist.getDescription();
	}
	
}