package me.khun.clinic.model.repo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.khun.clinic.model.entity.Disease;

public class DiseaseRowMapper implements RowMapper<Disease> {

	@Override
	public Disease mapRow(ResultSet rs, int row) throws SQLException {
		var disease = new Disease();
		disease.setId(rs.getLong("id"));
		disease.setName(rs.getString("name"));
		disease.setDescription(rs.getString("description"));
		return disease;
	}

}
