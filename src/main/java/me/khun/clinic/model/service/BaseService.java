package me.khun.clinic.model.service;

import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.model.service.exception.DuplicateEntityException;
import me.khun.clinic.model.service.exception.EntityCanNotBeDeletedException;
import me.khun.clinic.model.service.exception.ServiceException;
import me.khun.clinic.util.StringUtils;

public class BaseService {

	protected void handleDataAccessException(DataAccessException e) throws ServiceException {
		var message = e.getMessage();
		
		if (StringUtils.notNullOrBlank(message)) {
			if (message.startsWith("Duplicate entry")) {
				throw new DuplicateEntityException("The duplicate item exists.");
			} else if (message.startsWith("Cannot delete or update a parent row")) {
				throw new EntityCanNotBeDeletedException("Could not delete this item.");
			}
		}
		
		e.printStackTrace();
	}
}
