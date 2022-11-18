package me.khun.clinic.model.entity;

import java.util.Objects;

public class Doctor extends User {

	private DoctorSpecialist specialist;
	
	public Doctor() {
	}

	public DoctorSpecialist getSpecialist() {
		return specialist;
	}

	public void setSpecialist(DoctorSpecialist specialist) {
		this.specialist = specialist;
	}
	
	@Override
	public void setRole(Role role) {
		this.role = Role.DOCTOR;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(specialist);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(specialist, other.specialist);
	}

	
}