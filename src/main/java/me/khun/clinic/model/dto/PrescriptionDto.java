package me.khun.clinic.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class PrescriptionDto {

	public PrescriptionDto() {
	}

	private Long id;

	private LocalDateTime visitDateTime;

	private LocalDate nextVisitDate;

	private int systolicBloodPressure;

	private int diastolicBloodPressure;

	private float temperature;

	private int pulseRate;

	private String note;

	private PatientDto patient;

	private DoctorDto doctor;

	private DiseaseDto disease;

	private Set<MedicineDosageDto> medicineDosageList;

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

	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}
	
	public Long getPatientId() {
		return patient == null ? null : patient.getId();
	}
	
	public String getPatientName() {
		return patient == null ? null : patient.getName();
	}
	
	public String getPatientUsername() {
		return patient == null ? null : patient.getUsername();
	}

	public DoctorDto getDoctor() {
		return doctor;
	}

	public void Doctor(DoctorDto doctor) {
		this.doctor = doctor;
	}
	
	public Long getDoctorId() {
		return doctor == null ? null : doctor.getId();
	}
	
	public String getDoctorName() {
		return doctor == null ? null : doctor.getName();
	}
	
	public String getDoctorUsername() {
		return doctor == null ? null : doctor.getUsername();
	}

	public DiseaseDto getDisease() {
		return disease;
	}

	public void setDisease(DiseaseDto disease) {
		this.disease = disease;
	}
	
	public Long getDiseaseId() {
		return disease == null ? null : disease.getId();
	}
	
	public String getDiseaseName() {
		return disease == null ? null : disease.getName();
	}
	
	public String getDiseaseDescription() {
		return disease == null ? null : disease.getDescription();
	}

	public Set<MedicineDosageDto> getMedicineDosageList() {
		return medicineDosageList;
	}

	public void setMedicineDosageList(Set<MedicineDosageDto> medicineDosageList) {
		this.medicineDosageList = medicineDosageList;
	}

	
}