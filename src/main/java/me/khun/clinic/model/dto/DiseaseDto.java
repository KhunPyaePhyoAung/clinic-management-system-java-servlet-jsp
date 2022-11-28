package me.khun.clinic.model.dto;

import java.util.List;

import me.khun.clinic.model.entity.Disease;

public class DiseaseDto {
	
	private Long id;

	private String name;

	private String description;
	
	public DiseaseDto() {}
	
	public DiseaseDto(Disease disease) {
		this.id = disease.getId();
		this.name = disease.getName();
		this.description = disease.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static DiseaseDto of(Disease disease) {
		return new DiseaseDto(disease);
	}
	
	public static List<DiseaseDto> ofList(List<Disease> diseaseList) {
		return diseaseList.stream().map(d -> new DiseaseDto(d)).toList();
	}
	
}