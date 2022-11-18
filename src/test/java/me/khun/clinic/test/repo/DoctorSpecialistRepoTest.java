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
import me.khun.clinic.model.entity.DoctorSpecialist;
import me.khun.clinic.model.repo.DoctorSpecialistRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.test.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class DoctorSpecialistRepoTest {

	private DoctorSpecialistRepo repo;
	
	public DoctorSpecialistRepoTest() {
		repo = Application.getDoctorSpecialistRepo();
	}

	@BeforeAll
	public static void init() {
		DatabaseInitializer.truncateTables(Application.getDataSource(), "doctor", "address", "user", "doctor_specialist");
	}
	
	@Test
	@Order(1)
	@DisplayName("Find all doctor specialists when there is no data and return empty list.")
	public void findAllDoctorSpecialists_ThereIsNoData_EmptyList() {
		var dsList = repo.findAll();
		assertEquals(0, dsList.size());
	}
	
	@Test
	@Order(2)
	@DisplayName("Create a doctor specialist.")
	public void createDoctorSpecialist() {
		var ds = new DoctorSpecialist();
		ds.setName("OG");
		ds.setDescription("OG Description");
		var created = repo.create(ds);
		
		ds.setId(created.getId());

		assertEquals(ds, created);
		assertEquals(created, repo.findById(ds.getId()));
	}
	
	@Test
	@Order(3)
	@DisplayName("Create a doctor specialist without the description.")
	public void createDoctorSpecialist_WithoutDescription() {
		var ds = new DoctorSpecialist();
		ds.setName("Surgery");
		var created = repo.create(ds);
		
		ds.setId(created.getId());
		
		assertEquals(ds, created);
		assertEquals(created, repo.findById(ds.getId()));
	}
	
	@Test
	@Order(4)
	@DisplayName("Create a doctor specialist with the name that already exists.")
	public void createDoctorSpecialist_NameAlreadyExists_ThrowsException() {
		var ds = new DoctorSpecialist();
		ds.setName("OG");
		assertThrows(DataAccessException.class, () -> repo.create(ds));
	}
	
	@Test
	@Order(5)
	@DisplayName("Find all doctor specialists.")
	public void findAllDoctorSpecialists() {
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
	@DisplayName("Find a doctor specialist by id that exists.")
	public void findDoctorSpecialistById_IdExists_ReturnDoctorSpecialist() {
		var ds = repo.findById(1L);
		assertEquals(1L, ds.getId());
		assertEquals("OG", ds.getName());
		assertEquals("OG Description", ds.getDescription());
	}
	
	@Test
	@Order(7)
	@DisplayName("Find a doctor specialist by id that does not exist.")
	public void findDoctorSpecialistById_IdNotExists_ThrowsException() {
		var ds = repo.findById(3L);
		assertNull(ds);
	}
	
	@Test
	@Order(8)
	@DisplayName("Update the doctor specialist.")
	public void updateDoctorSpecialist() {
		var ds = repo.findById(2L);
		ds.setName("Surgery Updated");
		ds.setDescription("Surgery Description");
		var updated = repo.update(ds);
		
		assertEquals(ds, updated);
		assertEquals(updated, repo.findById(ds.getId()));
	}
	
	@Test
	@Order(9)
	@DisplayName("Update the doctor specialist with the name that already exists.")
	public void updateDoctorSpecialist_NameAlreadyExists_ThrowsException() {
		var ds = repo.findById(2L);
		ds.setName("OG");
		assertThrows(DataAccessException.class, () -> repo.create(ds));
	}
	
	@Test
	@Order(10)
	@DisplayName("Search the doctor specialists by a filter.")
	public void searchDoctorSpecialistsByFilter() {
		
		var ds1 = repo.findById(1L);
		var ds2 = repo.findById(2L);
		
		var list = List.of(ds1, ds2);
		
		Predicate<DoctorSpecialist> filter = ds -> ds.getName().contains("OG");
		var result = repo.search(filter);
		assertEquals(1, result.size());
		assertTrue(result.contains(ds1));
		
		filter = ds -> ds.getName().contains("g");
		result = repo.search(filter);
		assertEquals(1, result.size());
		assertTrue(result.contains(ds2));
		
		filter = ds -> ds.getDescription().contains("Description");
		result = repo.search(filter);
		assertEquals(2, result.size());
		assertTrue(result.containsAll(list));
		
		filter = ds -> ds.getName().contains("head");
		result = repo.search(filter);
		assertEquals(0, result.size());
		
		filter = ds -> ds.getDescription().contains("head");
		result = repo.search(filter);
		assertEquals(0, result.size());
	}
	
	@Test
	@Order(11)
	@DisplayName("Delete the doctor specialist by id that exists.")
	public void deleteDoctorSpecialistById_IdExists_True() {
		var id = 1L;
		var deleted = repo.deleteById(id);
		assertTrue(deleted);
		assertNull(repo.findById(id));
	}
	
	@Test
	@Order(12)
	@DisplayName("Delete the doctor specialist by id that does not exist.")
	public void deleteDoctorSpecialistById_IdNotExists_False() {
		var deleted = repo.deleteById(3L);
		assertFalse(deleted);
	}
	
	@Test
	@Order(13)
	@DisplayName("Delete Doctor Specialist by object.")
	public void deleteDoctorSpecialistByObject_IdExists_True() {
		var id = 2L;
		var ds = repo.findById(id);
		var deleted = repo.delete(ds);
		assertTrue(deleted);
		assertNull(repo.findById(id));
	}
	
	@Test
	@Order(14)
	@DisplayName("Should return empty list after deleted all entities.")
	public void findAllDoctorSpecialists_ThereIsNoData() {
		var dsList = repo.findAll();
		assertEquals(0, dsList.size());
	}
	
}
