package me.khun.clinic.model.service;

import java.util.List;

import me.khun.clinic.model.entity.DoctorSpecialist;

public interface DoctorSpecialistService {

	public List<DoctorSpecialist> search(String keyword, Boolean deleted);

	public DoctorSpecialist save(DoctorSpecialist spec);

	public DoctorSpecialist findById(long id);

	public boolean deleteById(long id);

	public boolean restoreById(long id);

}