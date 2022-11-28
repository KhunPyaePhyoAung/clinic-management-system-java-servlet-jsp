package me.khun.clinic.model.service.impl;

import me.khun.clinic.model.entity.validator.UserValidator;
import me.khun.clinic.model.repo.UserRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.model.service.BaseService;
import me.khun.clinic.model.service.UserService;
import me.khun.clinic.model.service.exception.ServiceException;
import me.khun.clinic.util.StringUtils;

public class UserServiceImpl extends BaseService implements UserService {
	
	private UserRepo repo;
	
	public UserServiceImpl(UserRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public boolean changePassword(long id, String oldPassword, String newPassword) {
		
		if (StringUtils.isNullOrBlank(oldPassword)) {
			throw new ServiceException("The old password cannot be empty.");
		}
		
		if (StringUtils.isNullOrBlank(newPassword)) {
			throw new ServiceException("The new password cannot be empty.");
		}
		try {
			var user = repo.findById(id);
			var password = user.getPassword();
			
			user.setPassword(oldPassword);
			UserValidator.validate(user);
			
			user.setPassword(newPassword);
			UserValidator.validate(user);
			
			if (!password.equals(oldPassword)) {
				throw new ServiceException("The password is incorrect.");
			}
			
			if (newPassword.equals(oldPassword)) {
				throw new ServiceException("The new password cannot be same as the old password.");
			}
			return repo.changePassword(id, newPassword);
		} catch (DataAccessException e) {
			handleDataAccessException(e);
		}
		
		return false;
	}
	
	


}