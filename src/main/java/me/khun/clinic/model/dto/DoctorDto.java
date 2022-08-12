package me.khun.clinic.model.dto;

public class DoctorDto extends UserDto {

	private DoctorSpecialistDto specialist;

	public DoctorSpecialistDto getSpecialist() {
		return specialist;
	}

	public void setSpecialist(DoctorSpecialistDto specialist) {
		this.specialist = specialist;
	}
	
	public String getSpecialistName() {
		return specialist == null ? null : specialist.getName();
	}
	
	public String getSpecialistDescription() {
		return specialist == null ? null : specialist.getDescription();
	}
	
}