package me.khun.clinic.model.repo;

import java.util.List;
import java.util.function.Predicate;

public interface BaseRepo<ID, T> {

	public T create(T entity );

	public T update(T entity);

	public boolean delete(T entity);

	public boolean deleteById(ID id);

	public T findById(ID id);

	public List<T> search(Predicate<T> filter);
	
	public List<T> findAll();

}