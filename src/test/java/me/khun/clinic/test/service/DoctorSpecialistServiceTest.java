package me.khun.clinic.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import me.khun.clinic.application.Application;
import me.khun.clinic.model.entity.DoctorSpecialist;
import me.khun.clinic.model.repo.impl.MySqlDoctorSpecialistRepoImpl;
import me.khun.clinic.model.service.DoctorSpecialistService;
import me.khun.clinic.model.service.exception.DuplicateEntityException;
import me.khun.clinic.model.service.exception.InvalidFieldException;
import me.khun.clinic.model.service.impl.DoctorSpecialistServiceImpl;
import me.khun.clinic.test.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class DoctorSpecialistServiceTest {
	
	private DoctorSpecialistService service;
	
	public DoctorSpecialistServiceTest() {
		service = new DoctorSpecialistServiceImpl(
					new MySqlDoctorSpecialistRepoImpl(Application.getDataSource())
				);
	}
	
	@BeforeAll
	@AfterAll
	public static void truncateTable() {
		DatabaseInitializer.truncateTables(Application.getDataSource(), "doctor", "address", "user", "doctor_specialist");
	}

	@Test
	@Order(1)
	@DisplayName("Search the doctor specialists with the null keyword when there is no data.")
	public void givenNullKeyword_SearchDoctorSpecialists_ThereIsNoData_EmptyList() {
		var result = service.search(null);
		assertEquals(0, result.size());
	}
	
	@Test
	@Order(2)
	@DisplayName("Search the doctor specialists with a keyword when there is no data.")
	public void givenKeyword_SearchDoctorSpecialists_ThereIsNoData_EmptyList() {
		var result = service.search("OG");
		assertEquals(0, result.size());
		
		result = service.search("");
		assertEquals(0, result.size());
	}
	
	@Test
	@Order(3)
	@DisplayName("Create a doctor specialist.")
	public void createDoctorSpecialist_ReturnCreatedDoctorSpecialist() {
		var ds = new DoctorSpecialist();
		ds.setName("      OG     ");
		ds.setDescription("     OG Description     ");
		var saved = service.save(ds);
		
		ds.setId(saved.getId());
		
		assertEquals(ds, saved);
		assertEquals(saved, service.findById(ds.getId()));
	}
	
	@Test
	@Order(4)
	@DisplayName("Create a doctor specialist without the description.")
	public void createDoctorSpecialist_WithoutDescription_ReturnCreatedDoctorSpecialist() {
		var ds = new DoctorSpecialist();
		ds.setName("Surgery");
		var saved = service.save(ds);
		
		ds.setId(saved.getId());
		
		assertEquals(ds, saved);
		assertEquals(saved, service.findById(ds.getId()));
	}
	
	@Test
	@Order(5)
	@DisplayName("Create a doctor specialist without the name.")
	public void createDoctorSpecialist_WithoutName_ThrowsException() {
		var ds = new DoctorSpecialist();
		ds.setDescription("Description");
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(6)
	@DisplayName("Create a doctor specialist with the name of null value.")
	public void createDoctorSpecialist_NameIsNull_ThrowsException() {
		var ds = new DoctorSpecialist();
		ds.setName(null);
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(7)
	@DisplayName("Create a doctor specialist with the empty name.")
	public void createDoctorSpecialist_NameIsEmpty_ThrowsException() {
		var ds = new DoctorSpecialist();
		ds.setName("");
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(8)
	@DisplayName("Create a doctor specialist with the name of length exceeding max length.")
	public void createDoctorSpecialist_NameLengthExceedsMaxLength_ThrowsException() {
		var ds = new DoctorSpecialist();
		ds.setName("""
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				""");
		
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(9)
	@DisplayName("Create a doctor specialist with the description of length exceeding max length.")
	public void createDoctorSpecialist_DescriptionLengthExceedsMaxLength_ThrowsException() {
		var ds = new DoctorSpecialist();
		ds.setDescription("""
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				""");
		
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(10)
	@DisplayName("Create a doctor specialist with the name that already exists.")
	public void createDoctorSpecialist_NameAlreadyExists_ThrowsException() {
		var ds = new DoctorSpecialist();
		ds.setName("Surgery");
		
		assertThrows(DuplicateEntityException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(11)
	@DisplayName("Update a doctor specialist.")
	public void updateDoctorSpecialist_ReturnUpdatedDoctorSpecialist() {
		var id = 2L;
		var ds = service.findById(id);
		ds.setName("    Surgery Updated    ");
		ds.setDescription("    Surgery Description    ");
		var saved = service.save(ds);
		
		assertEquals(ds, saved);
		assertEquals(saved, service.findById(id));
	}
	
	@Test
	@Order(12)
	@DisplayName("Update a doctor specialist with the name of null value.")
	public void updateDoctorSpecialist_NameIsNull_ThrowsException() {
		var ds = service.findById(2L);
		ds.setName(null);
		ds.setDescription("Surgery Description");
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(13)
	@DisplayName("Update a doctor specialist with the empty name.")
	public void updateDoctorSpecialist_NameIsEmpty_ThrowsException() {
		var ds = service.findById(2L);
		ds.setName("");
		ds.setDescription("Surgery Description");
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(14)
	@DisplayName("Update a doctor specialist with the name of length exceeding 100.")
	public void updateDoctorSpecialist_NameLengthExceeds100_ThrowsException() {
		var ds = service.findById(2L);
		ds.setName("""
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				""");
		ds.setDescription("Surgery Description");
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(15)
	@DisplayName("Update a doctor specialist with the name that already exists.")
	public void updateDoctorSpecialist_NameAlreadyExists_ThrowsException() {
		var ds = service.findById(2L);
		ds.setName("OG");
		ds.setDescription("Surgery Description");
		assertThrows(DuplicateEntityException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(16)
	@DisplayName("Update a doctor specialist with the description of length exceeding max length.")
	public void updateDoctorSpecialist_DescriptionLengthExceedsMaxLength_ThrowsException() {
		var ds = service.findById(2L);
		ds.setDescription("""
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				""");
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(17)
	@DisplayName("Find a doctor specialist by id that exists.")
	public void findDoctorSpecialistById_IdExists_ReturnResult() {
		var id = 1L;
		var result = service.findById(id);
		
		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("OG", result.getName());
		assertEquals("OG Description", result.getDescription());
		
		id = 2L;
		result = service.findById(id);
		
		assertNotNull(result);
		assertEquals(2L, result.getId());
		assertEquals("Surgery Updated", result.getName());
		assertEquals("Surgery Description", result.getDescription());
	}
	
	@Test
	@Order(18)
	@DisplayName("Find a doctor specialist by id that does not exist.")
	public void findDoctorSpecialistById_IdNotExists_Null() {
		var id = 3L;
		var result = service.findById(id);
		assertNull(result);
	}
	
	@Test
	@Order(19)
	@DisplayName("Search the doctor specialists with a keyword.")
	public void givenKeyword_SearchDoctorSpecialists_ReturnList() {
		
		var ds1 = service.findById(1L);
		var ds2 = service.findById(2L);
		
		var list = List.of(ds1, ds2);
		
		var keyword = "OG";
		var resultList = service.search(keyword);;
		assertEquals(1, resultList.size());
		assertTrue(resultList.contains(ds1));
		
		keyword = "u";
		resultList = service.search(keyword);
		assertEquals(1, resultList.size());
		assertTrue(resultList.contains(ds2));
		
		keyword = "g";
		resultList = service.search(keyword);
		assertEquals(2, resultList.size());
		assertTrue(resultList.containsAll(list));
		
		keyword = "description";
		resultList = service.search(keyword);
		assertEquals(2, resultList.size());
		assertTrue(resultList.containsAll(list));
		
		keyword = "";
		resultList = service.search(keyword);
		assertEquals(2, resultList.size());
		assertTrue(resultList.containsAll(list));
		
		keyword = "f";
		resultList = service.search(keyword);
		assertEquals(0, resultList.size());
	}
	
	@Test
	@Order(20)
	@DisplayName("Delete a doctor specialist by id that exists.")
	public void deleteById_IdExists_True() {
		var id = 1L;
		var deleted = service.deleteById(id);
		assertTrue(deleted);
		assertNull(service.findById(id));
	}
	
	@Test
	@Order(21)
	@DisplayName("Delete a doctor specialist by id that does not exist.")
	public void deleteById_IdNotExists_False() {
		var deleted = service.deleteById(1L);
		assertFalse(deleted);
	}
}
