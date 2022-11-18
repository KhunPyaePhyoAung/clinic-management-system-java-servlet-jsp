package me.khun.clinic.model.repo.impl;

import java.util.List;
import java.util.function.Predicate;

import me.khun.clinic.model.entity.Patient;
import me.khun.clinic.model.repo.PatientRepo;

public class MySqlPatientRepoImpl implements PatientRepo {

	public MySqlPatientRepoImpl() {
	}

	@Override
	public Patient create(Patient entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient update(Patient entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Patient entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Patient findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> search(Predicate<Patient> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}