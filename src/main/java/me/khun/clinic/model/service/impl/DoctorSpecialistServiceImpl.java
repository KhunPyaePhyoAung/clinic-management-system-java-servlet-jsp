package me.khun.clinic.model.service.impl;

import static me.khun.clinic.util.StringUtils.containsIgnoreCase;

import java.util.List;
import java.util.function.Predicate;

import me.khun.clinic.model.entity.DoctorSpecialist;
import me.khun.clinic.model.entity.validator.DoctorSpecialistValidator;
import me.khun.clinic.model.repo.DoctorSpecialistRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.model.service.BaseService;
import me.khun.clinic.model.service.DoctorSpecialistService;
import me.khun.clinic.model.service.exception.ServiceException;

public class DoctorSpecialistServiceImpl extends BaseService implements DoctorSpecialistService {
	
	private DoctorSpecialistRepo repo;

	public DoctorSpecialistServiceImpl(DoctorSpecialistRepo repo) {
		this.repo = repo;
	}

	@Override
	public List<DoctorSpecialist> search(String keyword) {
		Predicate<DoctorSpecialist> filter  = e -> true;
		
		if (keyword != null) {
			filter = filter.and(e -> containsIgnoreCase(e.getName(), keyword))
					.or(e -> containsIgnoreCase(e.getDescription(), keyword));
		}
		
		return repo.search(filter);
	}

	@Override
	public DoctorSpecialist save(DoctorSpecialist entity) throws ServiceException {
		DoctorSpecialistValidator.validate(entity);
		try {
			if (entity.getId() == null) {
				return repo.create(entity);
			}
			return repo.update(entity);
		} catch (DataAccessException e) {
			handleDataAccessException(e);
		}
		return null;
	}

	@Override
	public DoctorSpecialist findById(long id) {
		return repo.findById(id);
	}

	@Override
	
	public boolean deleteById(long id) {
		try {
			return repo.deleteById(id);
		} catch (DataAccessException e) {
			handleDataAccessException(e);
		}
		return false;
		
	}

}