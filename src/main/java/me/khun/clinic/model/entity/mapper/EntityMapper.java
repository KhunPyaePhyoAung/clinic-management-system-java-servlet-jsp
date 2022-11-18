package me.khun.clinic.model.entity.mapper;

import java.util.Collection;
import java.util.List;

import me.khun.clinic.model.dto.DoctorDto;
import me.khun.clinic.model.dto.DoctorSpecialistDto;
import me.khun.clinic.model.entity.Doctor;
import me.khun.clinic.model.entity.DoctorSpecialist;

public class EntityMapper {
	
	public static DoctorSpecialistDto toDoctorSpecialistDto(DoctorSpecialist doctorSpecialist) {
		return new DoctorSpecialistDto(doctorSpecialist);
	}
	
	public static List<DoctorSpecialistDto> toDoctorSpecialistDtoList(Collection<DoctorSpecialist> doctorSpecialists) {
		return doctorSpecialists.stream().map(e -> toDoctorSpecialistDto(e)).toList();
	}
	
	public static DoctorDto toDoctorDto(Doctor doctor) {
		return new DoctorDto(doctor);
	}
	
	public static List<DoctorDto> toDoctorDtoList(Collection<Doctor> doctors) {
		return doctors.stream().map(e -> toDoctorDto(e)).toList();
	}

}
