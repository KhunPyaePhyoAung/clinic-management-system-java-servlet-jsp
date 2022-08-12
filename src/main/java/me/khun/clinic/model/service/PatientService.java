package me.khun.clinic.model.service;

import java.util.List;

import me.khun.clinic.model.entity.Patient;
import me.khun.clinic.model.entity.User.Status;

public interface PatientService {

	public List<Patient> search(String name, Status status);

	public Patient save(Patient patient);

	public Patient findById(int id);

	public boolean deleteById(int id);

}