package me.khun.clinic.model.repo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	T mapRow(ResultSet rs, int row) throws SQLException;
}
