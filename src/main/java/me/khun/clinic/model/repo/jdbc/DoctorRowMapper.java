package me.khun.clinic.model.repo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.khun.clinic.model.entity.Address;
import me.khun.clinic.model.entity.Doctor;
import me.khun.clinic.model.entity.DoctorSpecialist;
import me.khun.clinic.model.entity.Gender;
import me.khun.clinic.model.entity.User.Role;
import me.khun.clinic.model.entity.User.Status;

public class DoctorRowMapper implements RowMapper<Doctor> {

	@Override
	public Doctor mapRow(ResultSet rs, int row) throws SQLException {
		var address = new Address();
		address.setId(rs.getLong("address_id"));
		address.setStreet(rs.getString("street"));
		address.setCity(rs.getString("city"));
		address.setState(rs.getString("state"));
		address.setCountry(rs.getString("country"));
		
		var specialist = new DoctorSpecialist();
		specialist.setId(rs.getLong("doctor_specialist_id"));
		specialist.setName(rs.getString("specialist_name"));
		specialist.setDescription(rs.getString("specialist_description"));
		
		var doctor = new Doctor();
		doctor.setId(rs.getLong("id"));
		doctor.setName(rs.getString("name"));
		doctor.setUsername(rs.getString("username"));
		doctor.setPassword(rs.getString("password"));
		doctor.setRegistrationDate(rs.getDate("registration_date").toLocalDate());
		doctor.setEmail(rs.getString("email"));
		doctor.setPhone(rs.getString("phone"));
		doctor.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
		doctor.setGender(Gender.valueOf(rs.getString("gender")));
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.valueOf(rs.getString("status")));
		doctor.setAddress(address);
		doctor.setSpecialist(specialist);
		return doctor;
	}
}
