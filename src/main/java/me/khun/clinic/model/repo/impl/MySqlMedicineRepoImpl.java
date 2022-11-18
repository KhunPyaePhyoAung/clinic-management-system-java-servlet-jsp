package me.khun.clinic.model.repo.impl;

import java.util.List;
import java.util.function.Predicate;

import me.khun.clinic.model.entity.Medicine;
import me.khun.clinic.model.repo.MedicineRepo;

public class MySqlMedicineRepoImpl implements MedicineRepo {

	public MySqlMedicineRepoImpl() {
	}

	@Override
	public Medicine create(Medicine entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medicine update(Medicine entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Medicine entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Medicine findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> search(Predicate<Medicine> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}