package me.khun.clinic.model.service;

import java.util.List;

import me.khun.clinic.model.entity.Doctor;
import me.khun.clinic.model.entity.User.Status;

public interface DoctorService {

	public List<Doctor> search(String name, Status status);

	public Doctor save(Doctor doctor);

	public Doctor findById(long id);

	public boolean deleteById(long id);

}