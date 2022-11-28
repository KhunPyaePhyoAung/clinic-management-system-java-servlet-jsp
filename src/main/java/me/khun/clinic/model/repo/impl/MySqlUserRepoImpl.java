package me.khun.clinic.model.repo.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import me.khun.clinic.application.Application;
import me.khun.clinic.model.entity.User;
import me.khun.clinic.model.repo.UserRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.model.repo.jdbc.RowMapper;
import me.khun.clinic.model.repo.jdbc.UserRowMapper;
import me.khun.clinic.util.PropertyReader;

public class MySqlUserRepoImpl implements UserRepo {
	
	private String findUserByIdSql;
	
	private String changePasswordSql;

	private DataSource dataSource;
	
	private RowMapper<User> rowMapper;
	
	private PropertyReader sql;
	
	{
		sql = new PropertyReader(Application.SQL_PROPERTIES_FILE_LOCATION);
		
		findUserByIdSql = sql.getValue("db.user.findById");
		changePasswordSql = sql.getValue("db.user.changePassword");
	}

	public MySqlUserRepoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		this.rowMapper = new UserRowMapper();
	}
	
	@Override
	public boolean changePassword(long id, String password) {
		try (
			var connection = dataSource.getConnection();
			var stmt = connection.prepareStatement(changePasswordSql)
		) {
			stmt.setString(1, password);
			stmt.setLong(2, id);
			var count = stmt.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public User findById(long id) {
		try (
			var connection = dataSource.getConnection();
			var stmt = connection.prepareStatement(findUserByIdSql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			
			if (rs.next()) {
				return rowMapper.mapRow(rs, 1);
			}
			
			return null;
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

}