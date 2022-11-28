package me.khun.clinic.test.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import me.khun.clinic.application.Application;
import me.khun.clinic.model.entity.Disease;
import me.khun.clinic.model.repo.DiseaseRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.test.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class DiseaseRepoTest {
	
	private DiseaseRepo repo;
	
	public DiseaseRepoTest() {
		repo = Application.getDiseaseRepo();
	}
	
	@BeforeAll
	public static void init() {
		DatabaseInitializer.truncateTables(Application.getDataSource(), "disease");
	}
	
	@Test
	@Order(1)
	@DisplayName("Find all diseases when there is no data and return empty list.")
	public void findAllDisease_ThereIsNoData_EmptyList() {
		var dsList = repo.findAll();
		assertEquals(0, dsList.size());
	}
	
	@Test
	@Order(2)
	@DisplayName("Create a disease.")
	public void createDisease() {
		var ds = new Disease();
		ds.setName("Headache");
		ds.setDescription("Headache Description");
		var created = repo.create(ds);
		
		ds.setId(created.getId());

		assertEquals(ds, created);
		assertEquals(created, repo.findById(ds.getId()));
	}
	
	@Test
	@Order(3)
	@DisplayName("Create a disease without the non-required fields.")
	public void createDisease_WithoutNonRequiredFields() {
		var ds = new Disease();
		ds.setName("Allergies");
		var created = repo.create(ds);
		
		ds.setId(created.getId());
		
		assertEquals(ds, created);
		assertEquals(created, repo.findById(ds.getId()));
	}
	
	@Test
	@Order(4)
	@DisplayName("Should throw the exception when creating a disease with the name that already exists.")
	public void createDisease_NameAlreadyExists_ThrowsException() {
		var ds = new Disease();
		ds.setName("Headache");
		assertThrows(DataAccessException.class, () -> repo.create(ds));
	}
	
	@Test
	@Order(5)
	@DisplayName("Find all diseases.")
	public void findAllDiseases() {
		var dsList = repo.findAll();
		assertEquals(2, dsList.size());
		
		var list = List.of(
				repo.findById(1L),
				repo.findById(2L)
				);
		assertTrue(dsList.containsAll(list));
	}
	
	@Test
	@Order(6)
	@DisplayName("Find a disease by id that exists.")
	public void findDiseaseById_IdExists_ReturnDisease() {
		var ds = repo.findById(1L);
		assertEquals(1L, ds.getId());
		assertEquals("Headache", ds.getName());
		assertEquals("Headache Description", ds.getDescription());
	}
	
	@Test
	@Order(7)
	@DisplayName("Find a disease by id that does not exist.")
	public void findDiseaseById_IdNotExists_ThrowsException() {
		var ds = repo.findById(3L);
		assertNull(ds);
	}
	
	@Test
	@Order(8)
	@DisplayName("Update the disease.")
	public void updateDisease() {
		var ds = repo.findById(2L);
		ds.setName("Allergies Updated");
		ds.setDescription("Allergies Description");
		var updated = repo.update(ds);
		
		assertEquals(ds, updated);
		assertEquals(updated, repo.findById(ds.getId()));
	}
	
	@Test
	@Order(9)
	@DisplayName("Update the disease with the name that already exists.")
	public void updateDisease_NameAlreadyExists_ThrowsException() {
		var ds = repo.findById(2L);
		ds.setName("Headache");
		assertThrows(DataAccessException.class, () -> repo.create(ds));
	}
	
	@Test
	@Order(10)
	@DisplayName("Search the diseases by a filter.")
	public void searchDoctorSpecialistsByFilter() {
		
		var ds1 = repo.findById(1L);
		var ds2 = repo.findById(2L);
		
		var list = List.of(ds1, ds2);
		
		Predicate<Disease> filter = ds -> ds.getName().contains("ada");
		var result = repo.search(filter);
		assertEquals(1, result.size());
		assertTrue(result.contains(ds1));
		
		filter = ds -> ds.getName().contains("Alle");
		result = repo.search(filter);
		assertEquals(1, result.size());
		assertTrue(result.contains(ds2));
		
		filter = ds -> ds.getName().contains("e");
		result = repo.search(filter);
		assertEquals(2, result.size());
		assertTrue(result.containsAll(list));
		
		filter = ds -> ds.getDescription().contains("Description");
		result = repo.search(filter);
		assertEquals(2, result.size());
		assertTrue(result.containsAll(list));
		
		filter = ds -> ds.getName().contains("Stomach");
		result = repo.search(filter);
		assertEquals(0, result.size());
		
		filter = ds -> ds.getDescription().contains("Stomach");
		result = repo.search(filter);
		assertEquals(0, result.size());
	}
	
	@Test
	@Order(12)
	@DisplayName("Delete the disease by id that exists.")
	public void deleteDiseaseById_IdExists_False() {
		var deleted = repo.deleteById(1L);
		assertTrue(deleted);
	}

	@Test
	@Order(12)
	@DisplayName("Delete the disease by id that does not exist.")
	public void deleteDiseaseById_IdNotExists_False() {
		var deleted = repo.deleteById(3L);
		assertFalse(deleted);
	}
	
	@Test
	@Order(13)
	@DisplayName("Delete the disease by object.")
	public void deleteDiseaseByObject_IdExists_True() {
		var id = 2L;
		var ds = repo.findById(id);
		var deleted = repo.delete(ds);
		assertTrue(deleted);
		assertNull(repo.findById(id));
	}
	
	@Test
	@Order(14)
	@DisplayName("Should return empty list after deleted all entities.")
	public void findAllDiseases_ThereIsNoData() {
		var dsList = repo.findAll();
		assertEquals(0, dsList.size());
	}
}
