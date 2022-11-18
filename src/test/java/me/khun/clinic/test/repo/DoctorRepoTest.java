package me.khun.clinic.test.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import me.khun.clinic.application.Application;
import me.khun.clinic.model.entity.Address;
import me.khun.clinic.model.entity.Doctor;
import me.khun.clinic.model.entity.DoctorSpecialist;
import me.khun.clinic.model.entity.Gender;
import me.khun.clinic.model.entity.User.Role;
import me.khun.clinic.model.entity.User.Status;
import me.khun.clinic.model.repo.DoctorRepo;
import me.khun.clinic.model.repo.exception.DataAccessException;
import me.khun.clinic.test.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class DoctorRepoTest {
	
	private static DoctorSpecialist ds1;
	private static DoctorSpecialist ds2;
	
	private DoctorRepo repo;
	
	public DoctorRepoTest() {
		repo = Application.getDoctorRepo();
	}

	@BeforeAll
	public static void init() {
		DatabaseInitializer.truncateTables(Application.getDataSource(), "doctor", "address", "user", "doctor_specialist");
		var specialistRepo = Application.getDoctorSpecialistRepo();
		var ds1 = new DoctorSpecialist();
		ds1.setName("OG");
		ds1.setDescription("OG Description");
		
		var ds2 = new DoctorSpecialist();
		ds2.setName("Surgery");
		ds2.setDescription("Surgery Description");
		
		DoctorRepoTest.ds1 = specialistRepo.create(ds1);
		DoctorRepoTest.ds2 = specialistRepo.create(ds2);
	}
		
	@Test
	@Order(1)
	@DisplayName("Find all doctors when there is no data.")
	public void findAllDoctor_ThereIsNoData_EmptyList() {
		var resultList = repo.findAll();
		assertEquals(0, resultList.size());
	}
	
	@Test
	@Order(2)
	@DisplayName("Create a doctor.")
	public void createDoctor_ReturnCreatedDoctor() {
		var address = new Address();
		address.setStreet("12");
		address.setCity("Yangon");
		address.setState("Yangon");
		address.setCountry("Myanmar");
		
		var doctor = new Doctor();
		doctor.setName("Khun Pyae Phyo Aung");
		doctor.setUsername("khunpyaephyoaung");
		doctor.setPassword("khunlay");
		doctor.setEmail("khun@gmail.com");
		doctor.setPhone("09111222333");
		doctor.setDateOfBirth(LocalDate.of(2000, 1, 1));
		doctor.setRegistrationDate(LocalDate.now());
		doctor.setGender(Gender.MALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		var created = repo.create(doctor);
		
		doctor.setId(created.getId());
		doctor.setRegistrationDate(created.getRegistrationDate());
		doctor.getAddress().setId(created.getAddress().getId());
		
		assertEquals(doctor, created);
		assertEquals(created, repo.findById(doctor.getId()));
	}
	
	@Test
	@Order(3)
	@DisplayName("Create a doctor without the non-required fields.")
	public void createDoctor_WithoutNotRequiredFields_ReturnCreatedDoctor() {
		var address = new Address();
		address.setCity("Bago");
		address.setState("Bago");
		
		var doctor = new Doctor();
		doctor.setName("Nan Kham");
		doctor.setUsername("nankham");
		doctor.setPassword("nankham");
		doctor.setPhone("09222222999");
		doctor.setDateOfBirth(LocalDate.of(2002, 1, 1));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.CLOSED);
		doctor.setAddress(address);
		doctor.setSpecialist(ds2);
		
		var created = repo.create(doctor);
		
		doctor.setId(created.getId());
		doctor.setRegistrationDate(created.getRegistrationDate());
		doctor.getAddress().setId(created.getAddress().getId());
		
		assertEquals(doctor, created);
		assertEquals(created, repo.findById(doctor.getId()));
	}
	
	@Test
	@Order(4)
	@DisplayName("Create a doctor with the username that already exists.")
	public void createDoctor_UsernameAlreadyExists_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nan Kham Hom");
		doctor.setUsername("nankham");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(DataAccessException.class, () -> repo.create(doctor));
	}
	
	@Test
	@Order(5)
	@DisplayName("Create a doctor with the email that already exists.")
	public void createDoctor_EmailAlreadyExists_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Khun Pyi Thar Hein");
		doctor.setUsername("khunpyitharhein");
		doctor.setPassword("khunpyithar");
		doctor.setEmail("khun@gmail.com");
		doctor.setPhone("09444444444");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.MALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(DataAccessException.class, () -> repo.create(doctor));
	}
	
	@Test
	@Order(6)
	@DisplayName("Update the doctor.")
	public void updateDoctor() {
		var id = 1L;
		var doctor = repo.findById(id);
		
		doctor.setName("Mg Pyae Phyo Aung");
		doctor.setUsername("mgpyaephyoaung");
		doctor.setEmail("mg@gmail.com");
		doctor.setPhone("09999999999");
		doctor.setGender(Gender.OTHER);
		doctor.setDateOfBirth(LocalDate.of(1999, 10, 15));
		doctor.setStatus(Status.CLOSED);
		doctor.setSpecialist(ds2);
		doctor.getAddress().setCity("Toungoo");
		doctor.getAddress().setState("Bago");
		doctor.getAddress().setStreet("5");
		doctor.getAddress().setCountry("Burma");
		
		var updated = repo.update(doctor);
		
		assertEquals(doctor, updated);
		assertEquals(updated, repo.findById(id));
	}
	
	@Test
	@Order(7)
	@DisplayName("Change the password.")
	public void changePassword() {
		var id = 1L;
		var newPassword = "changedpassword";
		repo.changePassword(id, newPassword);
		
		var doctor = repo.findById(id);
		assertEquals(newPassword, doctor.getPassword());
	}
	
	@Test
	@Order(7)
	@DisplayName("Update the doctor with the username that already exists.")
	public void updateDoctor_UsernameAlreadyExists_ThrowsException() {
		var doctor = repo.findById(1L);
		doctor.setUsername("nankham");
		assertThrows(DataAccessException.class, () -> repo.update(doctor));
	}
	
	@Test
	@Order(8)
	@DisplayName("Update the doctor with the email that already exists.")
	public void updateDoctor_EmailAlreadyExists_ThrowsException() {
		var doctor = repo.findById(2L);
		doctor.setEmail("mg@gmail.com");
		assertThrows(DataAccessException.class, () -> repo.update(doctor));
	}
	
	@Test
	@Order(9)
	@DisplayName("Find a doctor by id that exists.")
	public void findDoctorById_IdExists_ReturnDoctor() {
		var doctor = repo.findById(1L);
		
		assertNotNull(doctor);
		assertEquals("Mg Pyae Phyo Aung", doctor.getName());
		assertEquals("mgpyaephyoaung", doctor.getUsername());
		assertEquals("changedpassword", doctor.getPassword());
		assertEquals("mg@gmail.com", doctor.getEmail());
		assertEquals("09999999999", doctor.getPhone());
		assertEquals(Gender.OTHER, doctor.getGender());
		assertEquals(LocalDate.of(1999, 10, 15), doctor.getDateOfBirth());
		assertEquals(Status.CLOSED, doctor.getStatus());
		assertEquals(ds2, doctor.getSpecialist());
		assertEquals("5", doctor.getAddress().getStreet());
		assertEquals("Toungoo", doctor.getAddress().getCity());
		assertEquals("Bago", doctor.getAddress().getState());
		assertEquals("Burma", doctor.getAddress().getCountry());
	}
	
	@Test
	@Order(10)
	@DisplayName("Find a doctor by id that does not exist.")
	public void findDoctorById_IdNotExists_ReturnNull() {
		var doctor = repo.findById(3L);
		assertNull(doctor);
	}
	
	@Test
	@Order(11)
	@DisplayName("Find all doctors.")
	public void findAllDoctors() {
		var list = List.of(repo.findById(1L), repo.findById(2L));
		var resultList = repo.findAll();
		
		assertEquals(2, resultList.size());
		assertTrue(resultList.containsAll(list));
	}
	
	@Test
	@Order(12)
	@DisplayName("Search the doctors by filter.")
	public void searchDoctorsByFilter() {
		var d1 = repo.findById(1L);
		var d2 = repo.findById(2L);
		var list = List.of(d1, d2);
		
		var result = repo.search(d -> d.getName().equals("Khun Kham Htee"));
		assertEquals(0, result.size());
		
		result = repo.search(d -> d.getName().equals("Mg Pyae Phyo Aung"));
		assertEquals(1, result.size());
		assertTrue(result.contains(d1));
		
		result = repo.search(d -> d.getUsername().contains("nkh"));
		assertEquals(1, result.size());
		assertTrue(result.contains(d2));
		
		result = repo.search(d -> d.getPhone().contains("999"));
		assertEquals(2, result.size());
		assertTrue(result.containsAll(list));
		
		result = repo.search(d -> d.getEmail() != null && d.getEmail().contains("m"));
		assertEquals(1, result.size());
		assertTrue(result.contains(d1));
		
		result = repo.search(d -> d.getGender() == Gender.FEMALE);
		assertEquals(1, result.size());
		assertTrue(result.contains(d2));
		
		result = repo.search(d -> d.getStatus() == Status.CLOSED);
		assertEquals(2, result.size());
		assertTrue(result.containsAll(list));
		
		result = repo.search(d -> d.getAddress().getCity().equals("Toungoo"));
		assertEquals(1, result.size());
		assertTrue(result.contains(d1));
	}
	
	@Test
	@Order(13)
	@DisplayName("Delete the doctor by id that exists.")
	public void deleteDoctorById_IdExists_True() {
		var id = 1L;
		var deleted = repo.deleteById(id);
		assertTrue(deleted);
		assertNull(repo.findById(id));
	}
	
	@Test
	@Order(14)
	@DisplayName("Delete the doctor by id that does not exist.")
	public void deleteDoctorById_IdNotExists_False() {
		var id = 1L;
		var deleted = repo.deleteById(id);
		assertFalse(deleted);
	}
	
	@Test
	@Order(15)
	@DisplayName("Delete the doctor by object.")
	public void deleteDoctorByObject_IdExists_True() {
		var id = 2L;
		var doctor = repo.findById(id);
		var deleted = repo.delete(doctor);
		assertTrue(deleted);
		assertNull(repo.findById(id));
	}

}
