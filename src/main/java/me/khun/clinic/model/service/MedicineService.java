package me.khun.clinic.model.service;

import java.util.List;

import me.khun.clinic.model.entity.Medicine;

public interface MedicineService {

	public List<Medicine> search(String keyword, Boolean deleted);

	public Medicine save(Medicine medicine);

	public Medicine findById(long id);

	public boolean deleteById(long id);

	public boolean restoreById(long id);

}