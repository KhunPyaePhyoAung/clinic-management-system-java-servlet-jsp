package me.khun.clinic.model.service;

import me.khun.clinic.model.entity.User;

public interface UserService {

	public boolean updatePassword(String username, String password);

	public User save(User user);

}