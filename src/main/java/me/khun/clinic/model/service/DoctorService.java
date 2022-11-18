package me.khun.clinic.model.service;

import java.util.List;

import me.khun.clinic.model.entity.Doctor;
import me.khun.clinic.model.entity.User.Status;

public interface DoctorService extends UserService {

	List<Doctor> search(String name, Status status);

	Doctor save(Doctor doctor);

	Doctor findById(long id);

	boolean deleteById(long id);
	
	boolean changePassword(long id, String oldPassword, String newPassword);

}