package me.khun.clinic.model.repo.impl;

import java.util.List;
import java.util.function.Predicate;

import me.khun.clinic.model.entity.Prescription;
import me.khun.clinic.model.repo.PrescriptionRepo;

public class MysqlPrescriptionRepoImpl implements PrescriptionRepo {

	public MysqlPrescriptionRepoImpl() {
	}

	@Override
	public Prescription create(Prescription entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prescription update(Prescription entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Prescription entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Prescription findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prescription> search(Predicate<Prescription> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prescription> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}