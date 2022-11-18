package me.khun.clinic.model.dto;

import me.khun.clinic.model.entity.DoctorSpecialist;

public class DoctorSpecialistDto extends Dto<DoctorSpecialist> {
	
	private Long id;
	
	private String name;

	private String description;

	public DoctorSpecialistDto() {
	}
	
	public DoctorSpecialistDto(DoctorSpecialist doctorSpecialist) {
		this.id = doctorSpecialist.getId();
		this.name = doctorSpecialist.getName();
		this.description = doctorSpecialist.getDescription();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}