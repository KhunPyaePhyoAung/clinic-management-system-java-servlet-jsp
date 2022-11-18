package me.khun.clinic.model.service;

import java.util.List;

import me.khun.clinic.model.entity.DoctorSpecialist;

public interface DoctorSpecialistService {

	public List<DoctorSpecialist> search(String keyword);

	public DoctorSpecialist save(DoctorSpecialist spec);

	public DoctorSpecialist findById(long id);

	public boolean deleteById(long id);

}