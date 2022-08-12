package me.khun.clinic.model.service;

import java.time.LocalDate;
import java.util.List;

import me.khun.clinic.model.entity.Prescription;

public interface PrescriptionService {

	public List<Prescription> search(long patientId, long doctorId, LocalDate from, LocalDate to);

	public Prescription save(Prescription prescription);

	public Prescription findById(int id);

	public boolean deleteById(int id);

}