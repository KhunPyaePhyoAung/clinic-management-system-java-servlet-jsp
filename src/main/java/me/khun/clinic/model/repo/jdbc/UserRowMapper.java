package me.khun.clinic.model.repo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.khun.clinic.model.entity.Address;
import me.khun.clinic.model.entity.Gender;
import me.khun.clinic.model.entity.User;
import me.khun.clinic.model.entity.User.Role;
import me.khun.clinic.model.entity.User.Status;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int row) throws SQLException {
		var address = new Address();
		address.setId(rs.getLong("address_id"));
		address.setStreet(rs.getString("street"));
		address.setCity(rs.getString("city"));
		address.setState(rs.getString("state"));
		address.setCountry(rs.getString("country"));
		
		
		var user = new User();
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setRegistrationDate(rs.getDate("registration_date").toLocalDate());
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
		user.setGender(Gender.valueOf(rs.getString("gender")));
		user.setRole(Role.valueOf(rs.getString("role")));
		user.setStatus(Status.valueOf(rs.getString("status")));
		user.setAddress(address);
		
		return user;
	}

}
