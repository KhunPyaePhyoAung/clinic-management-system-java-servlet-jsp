package me.khun.clinic.model.entity;

public class Doctor extends User {

	public Doctor() {
	}

	private DoctorSpecialist specialist;

	public DoctorSpecialist getSpecialist() {
		return specialist;
	}

	public void setSpecialist(DoctorSpecialist specialist) {
		this.specialist = specialist;
	}

	
}