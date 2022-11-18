package me.khun.clinic.model.repo.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import javax.sql.DataSource;

import me.khun.clinic.application.Application;
import me.khun.clinic.model.entity.DoctorSpecialist;
import me.khun.clinic.model.repo.DoctorSpecialistRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.model.repo.jdbc.DoctorSpecialistRowMapper;
import me.khun.clinic.model.repo.jdbc.PropertyReader;
import me.khun.clinic.model.repo.jdbc.RowMapper;
import me.khun.clinic.util.DBUtils;

public class MySqlDoctorSpecialistRepoImpl implements DoctorSpecialistRepo {

	private String createSpecialistSql;
	
	private String selectAllSpecialistsSql;
	
	private String selectSpecialistByIdSql;

	private String updateSpecialistSql;
	
	private String deleteSpecialistSql;

	private DataSource dataSource;
	
	private RowMapper<DoctorSpecialist> rowMapper;
	
	private PropertyReader sql;
	
	{
		sql = new PropertyReader(Application.SQL_PROPERTIES_FILE_LOCATION);
		
		createSpecialistSql = sql.getValue("db.specialist.create");
		selectAllSpecialistsSql = sql.getValue("db.specialist.selectAll");
		selectSpecialistByIdSql = sql.getValue("db.specialist.selectById");
		updateSpecialistSql = sql.getValue("db.specialist.update");
		deleteSpecialistSql = sql.getValue("db.specialist.delete");
	}

	public MySqlDoctorSpecialistRepoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		this.rowMapper = new DoctorSpecialistRowMapper();
	}

	@Override
	public DoctorSpecialist create(DoctorSpecialist entity) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try  {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

			stmt = connection.prepareStatement(createSpecialistSql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getDescription());

			stmt.execute();

			var resultSet = stmt.getGeneratedKeys();

			if (resultSet.next()) {
				var id = resultSet.getLong(1);
				var result = findById(connection, id);
				DBUtils.close(resultSet);
				connection.commit();
				return result;
			}

		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			DBUtils.close(stmt, connection);
		}

		return null;
	}

	@Override
	public DoctorSpecialist update(DoctorSpecialist entity) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

			stmt = connection.prepareStatement(updateSpecialistSql);

			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getDescription());
			stmt.setLong(3, entity.getId());

			stmt.execute();

			var result = findById(connection, entity.getId());
			connection.commit();
			return result;

		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			DBUtils.close(stmt, connection);
		}
	}

	@Override
	public boolean delete(DoctorSpecialist entity) {
		return deleteById(entity.getId());
	}

	@Override
	public boolean deleteById(Long id) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

			stmt = connection.prepareStatement(deleteSpecialistSql);
			
			stmt.setLong(1, id);

			int count = stmt.executeUpdate();

			connection.commit();
			return count > 0;

		} catch (SQLIntegrityConstraintViolationException e) {
			DBUtils.rollBack(connection);
			throw new DataAccessException(e.getMessage());
		} catch (SQLException e) {
			DBUtils.rollBack(connection);
			throw new DataAccessException(e.getMessage());
		} finally {
			DBUtils.close(stmt, connection);
		}

	}

	@Override
	public DoctorSpecialist findById(Long id) {
		try {
			return findById(dataSource.getConnection(), id);
		} catch (SQLException e) {
			return null;
		}
	}

	private DoctorSpecialist findById(Connection connection, Long id) {
		try (var stmt = connection.prepareStatement(selectSpecialistByIdSql)) {
			stmt.setLong(1, id);
			var resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				return rowMapper.mapRow(resultSet, 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DoctorSpecialist> search(Predicate<DoctorSpecialist> filter) {

		var resultList = new ArrayList<DoctorSpecialist>();

		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			stmt = connection.prepareStatement(selectAllSpecialistsSql);

			var resultSet = stmt.executeQuery();

			var i = 1;
			while (resultSet.next()) {
				resultList.add(rowMapper.mapRow(resultSet, i++));
			}

			DBUtils.close(resultSet);

			connection.commit();

			if (resultList.isEmpty()) {
				return Collections.emptyList();
			}

			if (filter == null) {
				return resultList;
			}

			return resultList.stream().filter(filter).toList();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(stmt, connection);
		}

		return Collections.emptyList();
	}

	@Override
	public List<DoctorSpecialist> findAll() {
		var resultList = new ArrayList<DoctorSpecialist>();

		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			stmt = connection.prepareStatement(selectAllSpecialistsSql);

			var resultSet = stmt.executeQuery();

			var i = 1;
			while (resultSet.next()) {
				resultList.add(rowMapper.mapRow(resultSet, i++));
			}

			DBUtils.close(resultSet);

			connection.commit();

			return resultList;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(stmt, connection);
		}

		return resultList;
	}

}