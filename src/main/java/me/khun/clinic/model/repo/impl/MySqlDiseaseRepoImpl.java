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
import me.khun.clinic.model.entity.Disease;
import me.khun.clinic.model.repo.DiseaseRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.model.repo.jdbc.DiseaseRowMapper;
import me.khun.clinic.model.repo.jdbc.RowMapper;
import me.khun.clinic.util.DBUtils;
import me.khun.clinic.util.PropertyReader;

public class MySqlDiseaseRepoImpl implements DiseaseRepo {
	
	private DataSource dataSource;
	
	private RowMapper<Disease> rowMapper;

	private String createDiseaseSql;

	private String updateDiseaseSql;

	private String deleteDiseaseSql;

	private String selectDiseaseByIdSql;

	private String selectAllDiseasesSql;
	
	private PropertyReader sql;
	
	{
		sql = new PropertyReader(Application.SQL_PROPERTIES_FILE_LOCATION);
		
		createDiseaseSql = sql.getValue("db.disease.create");
		updateDiseaseSql = sql.getValue("db.disease.update");
		deleteDiseaseSql = sql.getValue("db.disease.delete");
		selectDiseaseByIdSql = sql.getValue("db.disease.selectById");
		selectAllDiseasesSql = sql.getValue("db.disease.selectAll");
	}

	public MySqlDiseaseRepoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
		this.rowMapper = new DiseaseRowMapper();
	}

	@Override
	public Disease create(Disease entity) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try  {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

			stmt = connection.prepareStatement(createDiseaseSql, Statement.RETURN_GENERATED_KEYS);

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
	public Disease update(Disease entity) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

			stmt = connection.prepareStatement(updateDiseaseSql);

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
	public boolean delete(Disease entity) {
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

			stmt = connection.prepareStatement(deleteDiseaseSql);
			
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
	public Disease findById(Long id) {
		try {
			return findById(dataSource.getConnection(), id);
		} catch (SQLException e) {
			return null;
		}
	}
	
	private Disease findById(Connection connection, long id) {
		try (var stmt = connection.prepareStatement(selectDiseaseByIdSql)) {
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
	public List<Disease> search(Predicate<Disease> filter) {
		var resultList = new ArrayList<Disease>();

		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			stmt = connection.prepareStatement(selectAllDiseasesSql);

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
	public List<Disease> findAll() {
		var resultList = new ArrayList<Disease>();

		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			stmt = connection.prepareStatement(selectAllDiseasesSql);

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