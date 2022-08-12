package me.khun.clinic.model.service;

import java.time.LocalDate;
import java.util.List;

import me.khun.clinic.model.entity.Prescription;

public class PrescriptionServiceImpl implements PrescriptionService {

	public PrescriptionServiceImpl() {
	}

	public List<Prescription> search(long patientId, long doctorId, LocalDate from, LocalDate to) {
		// TODO implement here
		return null;
	}

	public Prescription save(Prescription prescription) {
		// TODO implement here
		return null;
	}

	public Prescription findById(int id) {
		// TODO implement here
		return null;
	}

	public boolean deleteById(int id) {
		// TODO implement here
		return false;
	}

}