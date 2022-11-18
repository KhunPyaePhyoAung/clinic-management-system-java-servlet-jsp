package me.khun.clinic.model.repo.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import javax.sql.DataSource;

import me.khun.clinic.application.Application;
import me.khun.clinic.model.entity.Doctor;
import me.khun.clinic.model.repo.DoctorRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.model.repo.jdbc.DoctorRowMapper;
import me.khun.clinic.model.repo.jdbc.PropertyReader;
import me.khun.clinic.model.repo.jdbc.RowMapper;
import me.khun.clinic.util.DBUtils;

public class MySqlDoctorRepoImpl extends MySqlUserRepoImpl implements DoctorRepo {
	
	private String createUserSql;
	
	private String createAddressSql;
	
	private String createDoctorSql;
	
	private String selectAllDoctorSql;

	private String selectDoctorByIdSql;

	private String deleteUserSql;

	private String updateDoctorSql;
	
	private DataSource dataSource;
	
	private RowMapper<Doctor> rowMapper;
	
	private PropertyReader sql;
	
	{
		sql = new PropertyReader(Application.SQL_PROPERTIES_FILE_LOCATION);
		
		createUserSql = sql.getValue("db.user.create");
		createAddressSql = sql.getValue("db.address.create");
		createDoctorSql = sql.getValue("db.doctor.create");
		selectAllDoctorSql = sql.getValue("db.doctor.selectAll");
		selectDoctorByIdSql = sql.getValue("db.doctor.selectById");
		deleteUserSql = sql.getValue("db.user.delete");
		updateDoctorSql = sql.getValue("db.doctor.update");
	}

	public MySqlDoctorRepoImpl(DataSource dataSource) {
		super(dataSource);
		this.dataSource = dataSource;
		this.rowMapper = new DoctorRowMapper();
	}

	@Override
	public Doctor create(Doctor doctor) {
		
		var address = doctor.getAddress();
		var specialist = doctor.getSpecialist();
		
		Connection connection = null;
		PreparedStatement stmt = null;
		PreparedStatement createUserStmt = null;
		PreparedStatement createAddressStmt = null;
		 try {
			 connection = dataSource.getConnection();
			 connection.setAutoCommit(false);
			 connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			 
			 createUserStmt = connection.prepareStatement(createUserSql, Statement.RETURN_GENERATED_KEYS);
			 createUserStmt.setString(1, doctor.getName());
			 createUserStmt.setString(2, doctor.getUsername());
			 createUserStmt.setString(3, doctor.getPassword());
			 createUserStmt.setString(4, doctor.getEmail());
			 createUserStmt.setString(5, doctor.getPhone());
			 createUserStmt.setDate(6, Date.valueOf(doctor.getDateOfBirth()));
			 createUserStmt.setString(7, doctor.getGender().name());
			 createUserStmt.setString(8, doctor.getRole().name());
			 createUserStmt.setString(9, doctor.getStatus().name());
			 createUserStmt.execute();
			 
			 var resultSet = createUserStmt.getGeneratedKeys();
			 if (resultSet.next()) {
				 var userId = resultSet.getLong(1);
				 
				 createAddressStmt = connection.prepareStatement(createAddressSql);
				 createAddressStmt.setLong(1, userId);
				 createAddressStmt.setString(2, address.getStreet());
				 createAddressStmt.setString(3, address.getCity());
				 createAddressStmt.setString(4, address.getState());
				 createAddressStmt.setString(5, address.getCountry());
				 createAddressStmt.execute();
				 
				 stmt = connection.prepareStatement(createDoctorSql, Statement.RETURN_GENERATED_KEYS);
				 
				 stmt.setLong(1, userId);
				 stmt.setLong(2, specialist.getId());
				 
				 if (stmt.executeUpdate() > 0) {
					 var result = findById(connection, userId);
					 DBUtils.close(resultSet);
					 connection.commit();
					 return result;
				 }
			 }
			 
		 } catch (SQLException e) {
			 try {
				 if (connection != null) {
					 connection.rollback();
				 }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 throw new DataAccessException(e.getMessage());
		 } finally {
			 DBUtils.close(createUserStmt, stmt, connection);
		 }
		 
		 return null;
	}

	private Doctor findById(Connection connection, long id) {
		
		try (
			var stmt = connection.prepareStatement(selectDoctorByIdSql);
		 ) {
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
	public Doctor update(Doctor entity) {
		Connection connection = null;
		PreparedStatement stmt = null;
		 try {
			 connection = dataSource.getConnection();
			 connection.setAutoCommit(false);
			 connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			 
			 stmt = connection.prepareStatement(updateDoctorSql);
			 
			 stmt.setString(1, entity.getName());
			 stmt.setString(2, entity.getUsername());
			 stmt.setString(3, entity.getEmail());
			 stmt.setString(4, entity.getPhone());
			 stmt.setDate(5, Date.valueOf(entity.getDateOfBirth()));
			 stmt.setString(6, entity.getGender().name());
			 stmt.setString(7, entity.getStatus().name());
			 stmt.setString(8, entity.getAddress().getStreet());
			 stmt.setString(9, entity.getAddress().getCity());
			 stmt.setString(10, entity.getAddress().getState());
			 stmt.setString(11, entity.getAddress().getCountry());
			 stmt.setLong(12, entity.getSpecialist().getId());
			 stmt.setLong(13, entity.getId());
			 
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
	public boolean delete(Doctor entity) {
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
			 
			 stmt = connection.prepareStatement(deleteUserSql);
			 
			 stmt.setLong(1, id);
			 
			 var count = stmt.executeUpdate();
			 
			 connection.commit();
			 return count > 0;
			 
		 } catch (SQLException e) {
			 DBUtils.rollBack(connection);
			 throw new DataAccessException(e.getMessage());
		 } finally {
			 DBUtils.close(stmt, connection);
		 }
		 
	}

	@Override
	public Doctor findById(Long id) {
		try {
			return findById(dataSource.getConnection(), id);
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Doctor> search(Predicate<Doctor> filter) {
		var resultList = new ArrayList<Doctor>();
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		 try {
			 connection = dataSource.getConnection();
			 connection.setAutoCommit(false);
			 connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			 
			 stmt = connection.prepareStatement(selectAllDoctorSql);
			 
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
	public Doctor findById(long id) {
		try {
			return findById(dataSource.getConnection(), id);
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Doctor> findAll() {
		return search(null);
	}

}