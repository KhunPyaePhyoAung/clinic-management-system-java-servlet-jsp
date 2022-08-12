package me.khun.clinic.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class Prescription {

	public Prescription() {
	}

	private Long id;

	private LocalDateTime visitDateTime;

	private LocalDate nextVisitDate;

	private int systolicBloodPressure;

	private int diastolicBloodPressure;

	private float temperature;

	private int pulseRate;

	private String note;

	private Patient patient;

	private Doctor doctor;

	private Disease disease;

	private Set<MedicineDosage> medicineDosageList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getVisitDateTime() {
		return visitDateTime;
	}

	public void setVisitDateTime(LocalDateTime visitDateTime) {
		this.visitDateTime = visitDateTime;
	}

	public LocalDate getNextVisitDate() {
		return nextVisitDate;
	}

	public void setNextVisitDate(LocalDate nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}

	public int getSystolicBloodPressure() {
		return systolicBloodPressure;
	}

	public void setSystolicBloodPressure(int systolicBloodPressure) {
		this.systolicBloodPressure = systolicBloodPressure;
	}

	public int getDiastolicBloodPressure() {
		return diastolicBloodPressure;
	}

	public void setDiastolicBloodPressure(int diastolicBloodPressure) {
		this.diastolicBloodPressure = diastolicBloodPressure;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public int getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(int pulseRate) {
		this.pulseRate = pulseRate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Set<MedicineDosage> getMedicineDosageList() {
		return medicineDosageList;
	}

	public void setMedicineDosageList(Set<MedicineDosage> medicineDosageList) {
		this.medicineDosageList = medicineDosageList;
	}

	
}