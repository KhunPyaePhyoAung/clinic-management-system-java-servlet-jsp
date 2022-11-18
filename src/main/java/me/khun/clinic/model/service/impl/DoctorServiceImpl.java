package me.khun.clinic.model.service.impl;

import static me.khun.clinic.util.StringUtils.containsIgnoreCase;

import java.util.List;
import java.util.function.Predicate;

import me.khun.clinic.model.entity.Doctor;
import me.khun.clinic.model.entity.User.Status;
import me.khun.clinic.model.entity.validator.DoctorValidator;
import me.khun.clinic.model.repo.DoctorRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.model.service.BaseService;
import me.khun.clinic.model.service.DoctorService;
import me.khun.clinic.model.service.exception.ServiceException;
import me.khun.clinic.util.StringUtils;

public class DoctorServiceImpl extends BaseService implements DoctorService {
	
	private DoctorRepo repo;

	public DoctorServiceImpl(DoctorRepo repo) {
		this.repo = repo;
	}

	@Override
	public List<Doctor> search(String keyword, Status status) {
		Predicate<Doctor> filter = d -> true;
		
		if (StringUtils.notNullOrBlank(keyword)) {
			
			filter = filter.and(d -> containsIgnoreCase(d.getName(), keyword))
					.or(d -> (containsIgnoreCase(d.getSpecialist().getName(), keyword)))
					.or(d-> containsIgnoreCase(d.getEmail(), keyword))
					.or(d -> containsIgnoreCase(d.getPhone(), keyword))
					.or(d -> containsIgnoreCase(d.getAddress().getStreet(), keyword))
					.or(d -> containsIgnoreCase(d.getAddress().getCity(), keyword))
					.or(d -> containsIgnoreCase(d.getAddress().getState(), keyword))
					.or(d -> containsIgnoreCase(d.getAddress().getCountry(), keyword));
		}
		
		if (status != null) {
			filter = filter.and(d -> d.getStatus() == status);
		}
		
		return repo.search(filter);
	}

	@Override
	public Doctor save(Doctor doctor) {
		DoctorValidator.validate(doctor);
		try {
			if (doctor.getId() == null) {
				return repo.create(doctor);
			}
			return repo.update(doctor);
		} catch (DataAccessException e) {
			handleDataAccessException(e);
		}
		return repo.create(doctor);
	}

	@Override
	public Doctor findById(long id) {
		return repo.findById(id);
	}

	@Override
	public boolean deleteById(long id) {
		return repo.deleteById(id);
	}

	@Override
	public boolean changePassword(long id, String oldPassword, String newPassword) {
		var doctor = findById(id);
		
		if (!doctor.getPassword().equals(oldPassword)) {
			throw new ServiceException("The password is incorrect.");
		}
		
		doctor.setPassword(newPassword);
		
		DoctorValidator.validate(doctor);
		
		if (doctor.getPassword().equals(oldPassword)) {
			throw new ServiceException("The new password cannot be same as the old password.");
		}
		
		return repo.changePassword(id, doctor.getPassword());
	}

}