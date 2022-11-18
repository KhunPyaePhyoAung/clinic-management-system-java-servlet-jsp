package me.khun.clinic.model.service;

public interface UserService {

	public boolean changePassword(long id, String oldPassword, String newPassword);

}