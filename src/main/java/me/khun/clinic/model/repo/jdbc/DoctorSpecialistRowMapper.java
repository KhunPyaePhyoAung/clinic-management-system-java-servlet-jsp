package me.khun.clinic.model.repo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.khun.clinic.model.entity.DoctorSpecialist;

public class DoctorSpecialistRowMapper implements RowMapper<DoctorSpecialist> {

	@Override
	public DoctorSpecialist mapRow(ResultSet rs, int row) throws SQLException {
		return new DoctorSpecialist(rs.getLong("id"), rs.getString("name"), rs.getString("description"));
	}

}
