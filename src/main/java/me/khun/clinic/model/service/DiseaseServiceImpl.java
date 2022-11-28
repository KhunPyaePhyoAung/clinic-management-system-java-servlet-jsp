package me.khun.clinic.model.service;

import static me.khun.clinic.util.StringUtils.containsIgnoreCase;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import me.khun.clinic.model.entity.Disease;
import me.khun.clinic.model.entity.validator.DiseaseValidator;
import me.khun.clinic.model.repo.DiseaseRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;

public class DiseaseServiceImpl extends BaseService implements DiseaseService {
	
	private DiseaseRepo repo;

	public DiseaseServiceImpl(DiseaseRepo repo) {
		this.repo = repo;
	}

	@Override
	public Disease save(Disease disease) {
		DiseaseValidator.validate(disease);
		
		try {
			if (disease.getId() == null || disease.getId() < 1) {
				return repo.create(disease);
			}
			
			return repo.update(disease);
			
		} catch (DataAccessException e) {
			handleDataAccessException(e);
		}
		return null;
	}

	@Override
	public Disease findById(long id) {
		try {
			return repo.findById(id);
		} catch (DataAccessException e) {
			handleDataAccessException(e);
		}
		return null;
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

	@Override
	public List<Disease> search(String keyword) {
		
		Predicate<Disease> filter = d -> true;
		
		if (keyword != null) {
			filter = filter.and(e -> containsIgnoreCase(e.getName(), keyword))
					.or(e -> containsIgnoreCase(e.getDescription(), keyword));
		}
		
		try {
			return repo.search(filter);
		} catch (DataAccessException e) {
			handleDataAccessException(e);;
		}
		return Collections.emptyList();
	}

}