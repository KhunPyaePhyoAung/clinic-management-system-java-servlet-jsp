package me.khun.clinic.model.repo;

import me.khun.clinic.model.entity.Doctor;

public interface DoctorRepo extends UserRepo, BaseRepo<Long, Doctor> {

	public Doctor findById(long id);
}