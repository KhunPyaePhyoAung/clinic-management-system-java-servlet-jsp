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
import me.khun.clinic.model.entity.Disease;
import me.khun.clinic.model.service.DiseaseService;
import me.khun.clinic.model.service.exception.DuplicateEntityException;
import me.khun.clinic.model.service.exception.InvalidFieldException;
import me.khun.clinic.test.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class DiseaseServiceTest {

	private DiseaseService service;
	
	public DiseaseServiceTest() {
		this.service = Application.getDiseaseService();
	}
	
	@BeforeAll
	@AfterAll
	public static void truncateTable() {
		DatabaseInitializer.truncateTables(Application.getDataSource(), "disease");
	}

	@Test
	@Order(1)
	@DisplayName("Search the diseases with the null keyword when there is no data.")
	public void givenNullKeyword_SearchDiseases_ThereIsNoData_EmptyList() {
		var result = service.search(null);
		assertEquals(0, result.size());
	}
	
	@Test
	@Order(2)
	@DisplayName("Search the diseases with a keyword when there is no data.")
	public void givenKeyword_SearchDiseases_ThereIsNoData_EmptyList() {
		var result = service.search("OG");
		assertEquals(0, result.size());
		
		result = service.search("");
		assertEquals(0, result.size());
	}
	
	@Test
	@Order(3)
	@DisplayName("Create a disease.")
	public void createDisease_ReturnCreatedDisease() {
		var ds = new Disease();
		ds.setName("      Headache     ");
		ds.setDescription("     Headache Description     ");
		var saved = service.save(ds);
		
		ds.setId(saved.getId());
		
		assertEquals(ds, saved);
		assertEquals(saved, service.findById(ds.getId()));
	}
	
	@Test
	@Order(4)
	@DisplayName("Create a disease without the description.")
	public void createDisease_WithoutDescription_ReturnCreatedDisease() {
		var ds = new Disease();
		ds.setName("Allergies");
		var saved = service.save(ds);
		
		ds.setId(saved.getId());
		
		assertEquals(ds, saved);
		assertEquals(saved, service.findById(ds.getId()));
	}
	
	@Test
	@Order(5)
	@DisplayName("Should throw the exception when creating a disease without the name.")
	public void createDisease_WithoutName_ThrowsException() {
		var ds = new Disease();
		ds.setDescription("Description");
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(6)
	@DisplayName("Should throw the exception when creating a disease with the name of null value.")
	public void createDisease_NameIsNull_ThrowsException() {
		var ds = new Disease();
		ds.setName(null);
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(7)
	@DisplayName("Should throw the exception when creating a disease with the empty name.")
	public void createDisease_NameIsEmpty_ThrowsException() {
		var ds = new Disease();
		ds.setName("");
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(8)
	@DisplayName("Should throw the exception when creating a disease with the name of length exceeding max length.")
	public void createDisease_NameLengthExceedsMaxLength_ThrowsException() {
		var ds = new Disease();
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
	@DisplayName("Should throw the exception when creating a disease with the description of length exceeding max length.")
	public void createDisease_DescriptionLengthExceedsMaxLength_ThrowsException() {
		var ds = new Disease();
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
	@DisplayName("Should throw the exception when creating a disease with the name that already exists.")
	public void createDisease_NameAlreadyExists_ThrowsException() {
		var ds = new Disease();
		ds.setName("Allergies");
		
		assertThrows(DuplicateEntityException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(11)
	@DisplayName("Update a disease.")
	public void updateDisease_ReturnUpdatedDisease() {
		var id = 2L;
		var ds = service.findById(id);
		ds.setName("    Allergies Updated    ");
		ds.setDescription("    Allergies Description    ");
		var saved = service.save(ds);
		
		assertEquals(ds, saved);
		assertEquals(saved, service.findById(id));
	}
	
	@Test
	@Order(12)
	@DisplayName("Should throw the exception when updating a disease with the name of null value.")
	public void updateDisease_NameIsNull_ThrowsException() {
		var ds = service.findById(2L);
		ds.setName(null);
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(13)
	@DisplayName("Should throw the exception when updating a disease with the empty name.")
	public void updateDisease_NameIsEmpty_ThrowsException() {
		var ds = service.findById(2L);
		ds.setName("");
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(14)
	@DisplayName("Should throw the exception when updating a disease with the name of length exceeding 100.")
	public void updateDisease_NameLengthExceeds100_ThrowsException() {
		var ds = service.findById(2L);
		ds.setName("""
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				""");
		assertThrows(InvalidFieldException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(15)
	@DisplayName("Should throw the exception when updating a disease with the name that already exists.")
	public void updateDisease_NameAlreadyExists_ThrowsException() {
		var ds = service.findById(2L);
		ds.setName("Headache");
		assertThrows(DuplicateEntityException.class, () -> service.save(ds));
	}
	
	@Test
	@Order(16)
	@DisplayName("Update a disease with the description of length exceeding max length.")
	public void updateDisease_DescriptionLengthExceedsMaxLength_ThrowsException() {
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
	@DisplayName("Find a disease by id that exists.")
	public void findDiseaseById_IdExists_ReturnResult() {
		var id = 1L;
		var result = service.findById(id);
		
		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Headache", result.getName());
		assertEquals("Headache Description", result.getDescription());
		
		id = 2L;
		result = service.findById(id);
		
		assertNotNull(result);
		assertEquals(2L, result.getId());
		assertEquals("Allergies Updated", result.getName());
		assertEquals("Allergies Description", result.getDescription());
	}
	
	@Test
	@Order(18)
	@DisplayName("Find a disease by id that does not exist.")
	public void findDiseaseById_IdNotExists_Null() {
		var id = 3L;
		var result = service.findById(id);
		assertNull(result);
	}
	
	@Test
	@Order(19)
	@DisplayName("Search the diseases with a keyword.")
	public void givenKeyword_SearchDiseases_ReturnList() {
		
		var ds1 = service.findById(1L);
		var ds2 = service.findById(2L);
		
		var list = List.of(ds1, ds2);
		
		var keyword = "head";
		var resultList = service.search(keyword);;
		assertEquals(1, resultList.size());
		assertTrue(resultList.contains(ds1));
		
		keyword = "ler";
		resultList = service.search(keyword);
		assertEquals(1, resultList.size());
		assertTrue(resultList.contains(ds2));
		
		keyword = "e";
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
	@DisplayName("Delete a disease by id that exists.")
	public void deleteById_IdExists_True() {
		var id = 1L;
		var deleted = service.deleteById(id);
		assertTrue(deleted);
		assertNull(service.findById(id));
	}
	
	@Test
	@Order(21)
	@DisplayName("Delete a disease by id that does not exist.")
	public void deleteById_IdNotExists_False() {
		var deleted = service.deleteById(1L);
		assertFalse(deleted);
	}
}
