package me.khun.clinic.model.repo;

import me.khun.clinic.model.entity.User;

public interface UserRepo {

	boolean changePassword(long id, String password);
	
	User findById(long id);
}