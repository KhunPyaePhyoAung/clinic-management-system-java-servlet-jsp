package me.khun.clinic.model.dto;

public class MedicineDosageDto {

	public MedicineDosageDto() {
	}

	private Long id;

	private float morningDosage;

	private float afternoonDosage;

	private float eveningDosage;

	private float nightDosage;

	private int quantity;

	private int duration;

	private String comment;
	
	private MedicineDto medicine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getMorningDosage() {
		return morningDosage;
	}

	public void setMorningDosage(float morningDosage) {
		this.morningDosage = morningDosage;
	}

	public float getAfternoonDosage() {
		return afternoonDosage;
	}

	public void setAfternoonDosage(float afternoonDosage) {
		this.afternoonDosage = afternoonDosage;
	}

	public float getEveningDosage() {
		return eveningDosage;
	}

	public void setEveningDosage(float eveningDosage) {
		this.eveningDosage = eveningDosage;
	}

	public float getNightDosage() {
		return nightDosage;
	}

	public void setNightDosage(float nightDosage) {
		this.nightDosage = nightDosage;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public MedicineDto getMedicine() {
		return medicine;
	}

	public void setMedicine(MedicineDto medicine) {
		this.medicine = medicine;
	}

	public String getMedicineName() {
		return medicine == null ? null : medicine.getName();
	}
	
	public String getMedicineDescription() {
		return medicine == null ? null : medicine.getDescription();
	}
	
}