package me.khun.clinic.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

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
import me.khun.clinic.model.service.DoctorService;
import me.khun.clinic.model.service.exception.DuplicateEntityException;
import me.khun.clinic.model.service.exception.InvalidFieldException;
import me.khun.clinic.model.service.exception.ServiceException;
import me.khun.clinic.test.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class DoctorServiceTest {
	
	private DoctorService service;
	private static DoctorSpecialist ds1;
	private static DoctorSpecialist ds2;
	
	public DoctorServiceTest() {
		service = Application.getDoctorService();
	}
	
	@BeforeAll
	public static void init() {
		DatabaseInitializer.truncateTables(Application.getDataSource(), "doctor", "address", "user", "doctor_specialist");
		
		var dsService = Application.getDoctorSpecialistService();
		
		var ds1 = new DoctorSpecialist();
		ds1.setName("OG");
		ds1.setDescription("OG Description");
		
		var ds2 = new DoctorSpecialist();
		ds2.setName("Surgery");
		ds2.setDescription("Surgery Description");
		
		DoctorServiceTest.ds1 = dsService.save(ds1);
		DoctorServiceTest.ds2 = dsService.save(ds2);
	}
	
	@Test
	@Order(1)
	@DisplayName("Search the doctor specialists with the null keyword when there is no data.")
	public void searchDoctor_ThereIsNoData_EmptyList() {
		var result = service.search(null, null);
		assertEquals(0, result.size());
		
		result = service.search("", null);
		assertEquals(0, result.size());
		
		result = service.search(null, Status.ACTIVE);
		assertEquals(0, result.size());
		
		result = service.search(null, Status.CLOSED);
		assertEquals(0, result.size());
		
		result = service.search("", Status.ACTIVE);
		assertEquals(0, result.size());
		
		result = service.search("", Status.CLOSED);
		assertEquals(0, result.size());
		
		result = service.search("Khun", null);
		assertEquals(0, result.size());
		
		result = service.search("Khun", Status.ACTIVE);
		assertEquals(0, result.size());
		
		result = service.search("Khun", Status.CLOSED);
		assertEquals(0, result.size());
	}
	
	@Test
	@Order(2)
	@DisplayName("Create a doctor.")
	public void createDoctor_ReturnCreatedDoctor() {
		var address = new Address();
		address.setStreet("     12     ");
		address.setCity("     Yangon     ");
		address.setState("     Yangon     ");
		address.setCountry("     Myanmar     ");
		
		var doctor = new Doctor();
		doctor.setName("     Khun Pyae Phyo Aung     ");
		doctor.setUsername("     khunpyaephyoaung     ");
		doctor.setPassword("     khunlay     ");
		doctor.setEmail("     khun@gmail.com     ");
		doctor.setPhone("     09111222333     ");
		doctor.setDateOfBirth(LocalDate.of(2000, 1, 1));
		doctor.setRegistrationDate(LocalDate.now());
		doctor.setGender(Gender.MALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		var created = service.save(doctor);
		
		address.setId(created.getAddress().getId());
		address.setStreet("12");
		address.setCity("Yangon");
		address.setState("Yangon");
		address.setCountry("Myanmar");
		
		doctor.setId(created.getId());
		doctor.setName("Khun Pyae Phyo Aung");
		doctor.setUsername("khunpyaephyoaung");
		doctor.setPassword("khunlay");
		doctor.setEmail("khun@gmail.com");
		doctor.setPhone("09111222333");
		doctor.setRegistrationDate(created.getRegistrationDate());
		
		
		assertEquals(doctor, created);
		assertEquals(created, service.findById(doctor.getId()));
	}
	
	@Test
	@Order(3)
	@DisplayName("Create a doctor without the non-required fields.")
	public void createDoctor_WithoutNotRequiredFields_ReturnCreatedDoctor() {
		var address = new Address();
		address.setStreet("");
		address.setCity("Bago");
		address.setState("Bago");
		
		var doctor = new Doctor();
		doctor.setName("Nan Kham");
		doctor.setUsername("nankham");
		doctor.setPassword("nankham");
		doctor.setPhone("09222222999");
		doctor.setEmail("");
		doctor.setDateOfBirth(LocalDate.of(2002, 1, 1));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.CLOSED);
		doctor.setAddress(address);
		doctor.setSpecialist(ds2);
		
		var created = service.save(doctor);
		
		address.setId(created.getId());
		address.setStreet(null);
		
		doctor.setId(created.getId());
		doctor.setEmail(null);
		doctor.setRegistrationDate(created.getRegistrationDate());
		
		assertEquals(doctor, created);
		assertEquals(created, service.findById(doctor.getId()));
	}
	
	@Test
	@Order(4)
	@DisplayName("Should throw the exception when creating a doctor with the name of null value.")
	public void createDoctor_NameIsNull_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName(null);
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(5)
	@DisplayName("Should thorw the exception when creating a doctor with the empty name.")
	public void createDoctor_NameIsEmpty_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}

	@Test
	@Order(6)
	@DisplayName("Should throw the exception when creating a doctor with the username that already exists.")
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
		
		assertThrows(DuplicateEntityException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(7)
	@DisplayName("Should throw the exception when creating a doctor with the email that already exists.")
	public void createDoctor_EmailAlreadyExists_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("khun@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(DuplicateEntityException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(8)
	@DisplayName("Should throw the exception when creating a doctor with the name of length exceeding max length.")
	public void createDoctor_NameLengthExceedsMaxLength_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("""
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				""");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(9)
	@DisplayName("Should throw the exception when creating a doctor with the username of null value.")
	public void createDoctor_UsernameIsNull_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername(null);
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(10)
	@DisplayName("Should throw the exception when creating a doctor with the empty username.")
	public void createDoctor_UsernameIsEmpty_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(11)
	@DisplayName("Should throw the exception when creating a doctor with the username of length less than min length.")
	public void createDoctor_UsernameLengthIsLessThanMinLength_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nan");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(12)
	@DisplayName("Should throw the exception when creating a doctor with the username of length exceeding max length.")
	public void createDoctor_UsernameLengthExceedsMaxLength_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("""
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				""");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(13)
	@DisplayName("Should throw the exception when creating a doctor with the password of null value.")
	public void createDoctor_PasswordIsNull_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword(null);
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(14)
	@DisplayName("Should throw the exception when creating a doctor with the empty password.")
	public void createDoctor_PasswordIsEmpty_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(15)
	@DisplayName("Should throw the exception when creating a doctor with the password of length less than min length.")
	public void createDoctor_PasswordLengthIsLessThanMinLength_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nang");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(16)
	@DisplayName("Should throw the exception when creating a doctor with the password of length exceeding max length.")
	public void createDoctor_PasswordLengthExceedsMaxLength_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("""
				abcdefghijklmnopqrstuvwxyz
				abcdefghijklmnopqrstuvwxyz
				""");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(17)
	@DisplayName("Should throw the exception when creating a doctor with the email of length of exceeding max length.")
	public void createDoctor_EmailLengthExceedsMaxLength_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz@gmail.com");
		doctor.setPhone("09333333333");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(18)
	@DisplayName("Should throw the exception when creating a doctor with the phone of null value.")
	public void createDoctor_PhoneIsNull_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone(null);
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(19)
	@DisplayName("Should throw the exception when creating a doctor with the empty phone.")
	public void createDoctor_PhoneIsEmpty_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(20)
	@DisplayName("Should throw the exception when creating a doctor with the phone of length less than min length.")
	public void createDoctor_PhoneLengthIsLessThanMinLength_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("0987");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(21)
	@DisplayName("Should throw the exception when creating a doctor with the phone of length of exceeding max length.")
	public void createDoctor_PhoneLengthExceedsMaxLength_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("0999999999999999");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(22)
	@DisplayName("Should throw the exception when creating a doctor with the data of birth of null value.")
	public void createDoctor_DateOfBirthIsNull_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(null);
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(23)
	@DisplayName("Should throw the exception when creating a doctor with the gender of null value.")
	public void createDoctor_GenderIsNull_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(null);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(24)
	@DisplayName("Should throw the exception when creating a doctor with the status of null value.")
	public void createDoctor_StatusIsNull_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(null);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(25)
	@DisplayName("Should throw the exception when creating a doctor with the specialist of null value.")
	public void createDoctor_SpecialistIsNull_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(null);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(26)
	@DisplayName("Should throw the exception when creating a doctor with the address of null value.")
	public void createDoctor_AddressIsNull_ThrowsException() {
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(null);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(27)
	@DisplayName("Should throw the exception when creating a doctor with the street of length exceeding max length.")
	public void createDoctor_AddressStreetLengthExceedsMaxLength_ThrowsException() {
		var address = new Address();
		address.setStreet("""
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				""");
		address.setCity("Mandalay");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(28)
	@DisplayName("Should throw the exception when creating a doctor with the city of null value.")
	public void createDoctor_AddressCityIsNull_ThrowsException() {
		var address = new Address();
		address.setCity(null);
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(29)
	@DisplayName("Should throw the exception when creating a doctor with the empty city.")
	public void createDoctor_AddressCityIsEmpty_ThrowsException() {
		var address = new Address();
		address.setCity("");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(30)
	@DisplayName("Should throw the exception when creating a doctor with the city of length exceeding max length.")
	public void createDoctor_AddressCityLengthExceedsMaxLength_ThrowsException() {
		var address = new Address();
		address.setCity("""
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				""");
		address.setState("Mandalay");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(31)
	@DisplayName("Should throw the exception when creating a doctor with the state of null value.")
	public void createDoctor_AddressStateIsNull_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState(null);
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(32)
	@DisplayName("Should throw the exception when creating a doctor with the empty state.")
	public void createDoctor_AddressStateIsEmpty_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(33)
	@DisplayName("Should throw the exception when creating a doctor with the state of length exceeding max length.")
	public void createDoctor_AddressStateLengthExceedsMaxLength_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("""
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				""");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(34)
	@DisplayName("Should throw the exception when creating a doctor with the country of length exceeding max length.")
	public void createDoctor_AddressCountryLengthExceedsMaxLength_ThrowsException() {
		var address = new Address();
		address.setCity("Mandalay");
		address.setState("Mandalay");
		address.setCountry("""
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				abcdefghijklmnopqrstuvwxy
				""");
		
		var doctor = new Doctor();
		doctor.setName("Nam Kham Hom");
		doctor.setUsername("nankhamhom");
		doctor.setPassword("nankhamlay");
		doctor.setEmail("nankham@gmail.com");
		doctor.setPhone("09987654321");
		doctor.setDateOfBirth(LocalDate.of(1998, 2, 2));
		doctor.setGender(Gender.FEMALE);
		doctor.setRole(Role.DOCTOR);
		doctor.setStatus(Status.ACTIVE);
		doctor.setAddress(address);
		doctor.setSpecialist(ds1);
		
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(35)
	@DisplayName("Update the doctor.")
	public void updateDoctor() {
		var doctor = service.findById(1L);
		
		doctor.setName("     Khun Pyi Thar Hein     ");
		doctor.setUsername("     khunpyitharhein     ");
		doctor.setEmail("     khunpyithar@gmail.com     ");
		doctor.setPhone("     09123456789     ");
		doctor.setDateOfBirth(LocalDate.of(1998, 10, 15));
		doctor.setGender(Gender.OTHER);
		doctor.setStatus(Status.CLOSED);
		doctor.setSpecialist(ds2);
		doctor.getAddress().setStreet("     Mingalar Street     ");
		doctor.getAddress().setCity("     Mandalay     ");
		doctor.getAddress().setState("     Mandalay     ");
		doctor.getAddress().setCountry("     Burma     ");
		
		var updated = service.save(doctor);
		
		doctor.setName("Khun Pyi Thar Hein");
		doctor.setUsername("khunpyitharhein");
		doctor.setEmail("khunpyithar@gmail.com");
		doctor.setPhone("09123456789");
		doctor.setDateOfBirth(LocalDate.of(1998, 10, 15));
		doctor.setGender(Gender.OTHER);
		doctor.setStatus(Status.CLOSED);
		doctor.setSpecialist(ds2);
		doctor.getAddress().setStreet("Mingalar Street");
		doctor.getAddress().setCity("Mandalay");
		doctor.getAddress().setState("Mandalay");
		doctor.getAddress().setCountry("Burma");
		
		assertEquals(doctor, updated);
		assertEquals(updated, service.findById(doctor.getId()));
		
	}
	
	@Test
	@Order(35)
	@DisplayName("Update the doctor without the non-required fields.")
	public void updateDoctor_WithoutNotRequiredFields_ReturnCreatedDoctor() {
		var doctor = service.findById(2L);
		doctor.setName("     U Kyaw Thi Ha     ");
		doctor.setUsername("     ukyawthiha     ");
		doctor.setPhone("     09223344556     ");
		doctor.setEmail("     ");
		doctor.setDateOfBirth(LocalDate.of(1996, 5, 7));
		doctor.setGender(Gender.MALE);
		doctor.setStatus(Status.ACTIVE);
		doctor.getAddress().setStreet("     ");
		doctor.getAddress().setCity("     Toungoo     ");
		doctor.getAddress().setState("     Pago     ");
		
		var updated = service.save(doctor);
		
		doctor.setName("U Kyaw Thi Ha");
		doctor.setUsername("ukyawthiha");
		doctor.setPhone("09223344556");
		doctor.setEmail(null);
		doctor.setDateOfBirth(LocalDate.of(1996, 5, 7));
		doctor.setGender(Gender.MALE);
		doctor.setStatus(Status.ACTIVE);
		doctor.getAddress().setStreet(null);
		doctor.getAddress().setCity("Toungoo");
		doctor.getAddress().setState("Pago");
		
		assertEquals(doctor, updated);
		assertEquals(updated, service.findById(doctor.getId()));
	}
	
	@Test
	@Order(36)
	@DisplayName("Change the password")
	public void changePassword() {
		var id = 2L;
		assumeTrue(service.changePassword(id, "nankham", "   ukyawthiha  "));
		var doctor = service.findById(id);
		assertEquals("ukyawthiha", doctor.getPassword());
	}
	
	@Test
	@Order(37)
	@DisplayName("Should throw the exception when changing the password with the old password of null value.")
	public void changePassword_OldPasswordIsNull_ThrowsException() {
		var id = 2L;
		assertThrows(ServiceException.class, () -> service.changePassword(id, null, "thihakyaw"));
	}
	
	@Test
	@Order(37)
	@DisplayName("Should throw the exception when changing the password with the empty old password.")
	public void changePassword_OldPasswordIsEmpty_ThrowsException() {
		var id = 2L;
		assertThrows(ServiceException.class, () -> service.changePassword(id, "  ", "thihakyaw"));
	}
	
	@Test
	@Order(37)
	@DisplayName("Should throw the exception when changing the password with the incorrect old password.")
	public void changePassword_OldPasswordIsIncorrect_ThrowsException() {
		var id = 2L;
		assertThrows(ServiceException.class, () -> service.changePassword(id, "kyawthiha", "thihakyaw"));
	}
	
	@Test
	@Order(37)
	@DisplayName("Should throw the exception when changing the password with the new password of null value.")
	public void changePassword_NewPasswordIsNull_ThrowsException() {
		var id = 2L;
		assertThrows(ServiceException.class, () -> service.changePassword(id, "ukyawthiha", null));
	}
	
	@Test
	@Order(37)
	@DisplayName("Should throw the exception when changing the password with the empty new password.")
	public void changePassword_NewPasswordIsEmpty_ThrowsException() {
		var id = 2L;
		assertThrows(ServiceException.class, () -> service.changePassword(id, "ukyawthiha", "   "));
	}
	
	@Test
	@Order(37)
	@DisplayName("Should throw the exception when changing the password with the empty new password.")
	public void changePassword_NewPasswordLengthLessThanMinLength_ThrowsException() {
		var id = 2L;
		assertThrows(ServiceException.class, () -> service.changePassword(id, "ukyawthiha", "kyaw"));
	}
	
	@Test
	@Order(37)
	@DisplayName("Should throw the exception when changing the password with the empty new password.")
	public void changePassword_NewPasswordLengthExceedsMaxLength_ThrowsException() {
		var id = 2L;
		assertThrows(ServiceException.class, () -> service.changePassword(id, "ukyawthiha", "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
	}
	
	@Test
	@Order(37)
	@DisplayName("Should throw the exception when updating the doctor with the name of null value.")
	public void updateDoctor_NameIsNull_ThrowsException() {
		var doctor = service.findById(1L);
		doctor.setName(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(38)
	@DisplayName("Should throw the exception when updating the doctor with the empty name.")
	public void updateDoctor_NameIsEmpty_ThrowsException() {
		var doctor = service.findById(1L);
		doctor.setName("     ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}

	@Test
	@Order(39)
	@DisplayName("Should throw the exception when updating the doctor with the username that already exists.")
	public void updateDoctor_UsernameAlreadyExists_ThrowsException() {
		var doctor = service.findById(1L);
		doctor.setUsername("ukyawthiha     ");
		assertThrows(DuplicateEntityException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(40)
	@DisplayName("Should throw the exception when updating the doctor with the email that already exists.")
	public void updateDoctor_EmailAlreadyExists_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setEmail(" khunpyithar@gmail.com ");
		assertThrows(DuplicateEntityException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(41)
	@DisplayName("Should throw the exception when updating the doctor with the name of length exceeding max length.")
	public void updateDoctor_NameLengthExceedsMaxLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(42)
	@DisplayName("Should throw the exception when updating the doctor with the username of null value.")
	public void updateDoctor_UsernameIsNull_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setUsername(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(43)
	@DisplayName("Should throw the exception when updating the doctor with the empty username.")
	public void updateDoctor_UsernameIsEmpty_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setUsername("  ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(44)
	@DisplayName("Should throw the exception when updating the doctor with the username of length less than min length.")
	public void updateDoctor_UsernameLengthIsLessThanMinLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setUsername(" kyaw ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(45)
	@DisplayName("Should throw the exception when updating the doctor with the username of length exceeding max length.")
	public void updateDoctor_UsernameLengthExceedsMaxLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setUsername(" abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(46)
	@DisplayName("Should throw the exception when updating the doctor with the password of null value.")
	public void updateDoctor_PasswordIsNull_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setPassword(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(47)
	@DisplayName("Should throw the exception when updating the doctor with the empty password.")
	public void updateDoctor_PasswordIsEmpty_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setPassword("  ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(48)
	@DisplayName("Should throw the exception when updating the doctor with the password of length less than min length.")
	public void updateDoctor_PasswordLengthIsLessThanMinLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setPassword(" kyaw ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(49)
	@DisplayName("Should throw the exception when updating the doctor with the password exceeding max length.")
	public void updateDoctor_PasswordLengthExceedsMaxLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setPassword(" abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(50)
	@DisplayName("Should throw the exception when updating the doctor with the email of length exceeding max length.")
	public void updateDoctor_EmailLengthExceedsMaxLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setEmail("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz@gmail.com");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(51)
	@DisplayName("Should throw the exception when updating the doctor with the phone of null value.")
	public void updateDoctor_PhoneIsNull_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setPhone(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(52)
	@DisplayName("Should throw the exception when updating the doctor with the empty phone.")
	public void updateDoctor_PhoneIsEmpty_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setPhone(" ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(53)
	@DisplayName("Should throw the exception when updating the doctor with the phone of length less than min length.")
	public void updateDoctor_PhoneLengthIsLessThanMinLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setPhone("0925");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(54)
	@DisplayName("Should throw the exception when updating the doctor with the phone of length exceeding max length.")
	public void updateDoctor_PhoneLengthExceedsMaxLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setPhone(" 0912345678987654 ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(55)
	@DisplayName("Should throw the exception when updating the doctor with the date of birth of null value.")
	public void updateDoctor_DateOfBirthIsNull_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setDateOfBirth(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(56)
	@DisplayName("Should throw the exception when updating the doctor with the gender of null value.")
	public void updateDoctor_GenderIsNull_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setGender(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(57)
	@DisplayName("Should throw the exception when updating the doctor with the status of null value.")
	public void updateDoctor_StatusIsNull_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setStatus(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(58)
	@DisplayName("Should throw the exception when updating the doctor with the specialist of null value.")
	public void updateDoctor_SpecialistIsNull_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setSpecialist(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(59)
	@DisplayName("Should throw the exception when updating the doctor with the address of null value.")
	public void updateDoctor_AddressIsNull_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.setAddress(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(60)
	@DisplayName("Should throw the exception when updating the doctor with the street of length exceeding max length.")
	public void updateDoctor_AddressStreetLengthExceedsMaxLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.getAddress().setStreet(" abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(61)
	@DisplayName("Should throw the exception when updating the doctor with the city of null value.")
	public void updateDoctor_AddressCityIsNull_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.getAddress().setCity(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(62)
	@DisplayName("Should throw the exception when updating the doctor with the empty city.")
	public void updateDoctor_AddressCityIsEmpty_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.getAddress().setCity(" ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(63)
	@DisplayName("Should throw the exception when updating the doctor with the city of length exceeding max length.")
	public void updateDoctor_AddressCityLengthExceedsMaxLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.getAddress().setCity("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(64)
	@DisplayName("Should throw the exception when updating the doctor with the state of null value.")
	public void updateDoctor_AddressStateIsNull_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.getAddress().setState(null);
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(65)
	@DisplayName("Should throw the exception when updating the doctor with the empty state.")
	public void updateDoctor_AddressStateIsEmpty_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.getAddress().setState(" ");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(66)
	@DisplayName("Should throw the exception when updating the doctor with the state of length exceeding max length.")
	public void updateDoctor_AddressStateLengthExceedsMaxLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.getAddress().setState("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(67)
	@DisplayName("Should throw the exception when updating the doctor with the country of length exceeding max length.")
	public void updateDoctor_AddressCountryLengthExceedsMaxLength_ThrowsException() {
		var doctor = service.findById(2L);
		doctor.getAddress().setCountry("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
		assertThrows(InvalidFieldException.class, () -> service.save(doctor));
	}
	
	@Test
	@Order(68)
	@DisplayName("Search the doctors with the keyword and status.")
	public void searchDoctor_WithKeywordAndStatus() {
		var doctor1 = service.findById(1L);
		var doctor2 = service.findById(2L);
		var list = List.of(doctor1, doctor2);
		
		var result = service.search(null, null);
		assertEquals(2, result.size());
		assertTrue(result.containsAll(list));
		
		result = service.search(null, Status.ACTIVE);
		assertEquals(1, result.size());
		assertTrue(result.contains(doctor2));
		
		result = service.search(null, Status.CLOSED);
		assertEquals(1, result.size());
		assertTrue(result.contains(doctor1));
		
		result = service.search("th", Status.ACTIVE);
		assertEquals(1, result.size());
		assertTrue(result.contains(doctor2));
		
		result = service.search("th", Status.CLOSED);
		assertEquals(1, result.size());
		assertTrue(result.contains(doctor1));
		
		result = service.search("th", null);
		assertEquals(2, result.size());
		assertTrue(result.containsAll(list));
		
		result = service.search("khun", null);
		assertEquals(1, result.size());
		assertTrue(result.contains(doctor1));
		
		result = service.search("khun", Status.ACTIVE);
		assertEquals(0, result.size());
		
		result = service.search("khun", Status.CLOSED);
		assertEquals(1, result.size());
		assertTrue(result.contains(doctor1));
		
		result = service.search("kyaw", null);
		assertEquals(1, result.size());
		assertTrue(result.contains(doctor2));
		
		result = service.search("kyaw", Status.ACTIVE);
		assertEquals(1, result.size());
		assertTrue(result.contains(doctor2));
		
		result = service.search("kyaw", Status.CLOSED);
		assertEquals(0, result.size());
	}
	
	@Test
	@Order(69)
	@DisplayName("Find the doctor by the id that exists.")
	public void findDoctorById_IdExists() {
		var doctor = service.findById(1L);
		assertNotNull(doctor);
	}
	
	@Test
	@Order(70)
	@DisplayName("Find the doctor by the id that doesn't exists.")
	public void findDoctorById_IdNotExists() {
		var doctor = service.findById(3L);
		assertNull(doctor);
	}
	
	@Test
	@Order(71)
	@DisplayName("Delete the doctor by the id that exists.")
	public void deleteDoctorById_IdExists_True() {
		var id = 1L;
		var deleted = service.deleteById(id);
		assertTrue(deleted);
		assertNull(service.findById(id));
	}
	
	@Test
	@Order(72)
	@DisplayName("Delete the doctor by the id that doesn't exists.")
	public void deleteDoctorById_IdNotExists_False() {
		var id = 3L;
		var deleted = service.deleteById(id);
		assertFalse(deleted);
		assertNull(service.findById(id));
	}
	
}
