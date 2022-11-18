package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.ADD_DOCTOR_JSP_LOCATION;
import static me.khun.clinic.application.Application.DOCTORS_JSP_LOCATION;
import static me.khun.clinic.application.Application.DOCTORS_SEARCH_URL;
import static me.khun.clinic.application.Application.DOCTOR_DELETE_URL;
import static me.khun.clinic.application.Application.DOCTOR_EDIT_URL;
import static me.khun.clinic.application.Application.DOCTOR_PROFILE_JSP_LOCATION;
import static me.khun.clinic.application.Application.DOCTOR_PROFILE_URL;
import static me.khun.clinic.application.Application.DOCTOR_SAVE_URL;
import static me.khun.clinic.application.Application.EDIT_DOCTOR_JSP_LOCATION;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.khun.clinic.application.Application;
import me.khun.clinic.model.entity.Address;
import me.khun.clinic.model.entity.Doctor;
import me.khun.clinic.model.entity.Gender;
import me.khun.clinic.model.entity.User.Role;
import me.khun.clinic.model.entity.User.Status;
import me.khun.clinic.model.entity.mapper.EntityMapper;
import me.khun.clinic.model.service.DoctorService;
import me.khun.clinic.model.service.DoctorSpecialistService;
import me.khun.clinic.model.service.exception.ServiceException;
import me.khun.clinic.util.DateUtils;
import me.khun.clinic.util.StringUtils;

@WebServlet(
	urlPatterns = {
		DOCTORS_SEARCH_URL,
		DOCTOR_EDIT_URL,
		DOCTOR_SAVE_URL,
		DOCTOR_DELETE_URL,
		DOCTOR_PROFILE_URL
	}
)
public class DoctorController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private DoctorService doctorService;
	
	private DoctorSpecialistService doctorSpecialistService;
	
	@Override
	public void init() throws ServletException {
		doctorService = Application.getDoctorService();
		doctorSpecialistService = Application.getDoctorSpecialistService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case DOCTORS_SEARCH_URL -> {
				searchDoctors(req, resp);
			}
			
			case DOCTOR_EDIT_URL -> {
				navigateToEditDoctorPage(req, resp);
			}
			
			case DOCTOR_DELETE_URL -> {
				deleteDoctor(req, resp);
			}
			
			case DOCTOR_PROFILE_URL -> {
				navigateToDoctorProfilePage(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case DOCTOR_SAVE_URL -> {
				saveDoctor(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}
	
	private void saveDoctor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var idParam = req.getParameter("id");
		var nameParam = req.getParameter("name");
		var usernameParam = req.getParameter("username");
		var emailParam = req.getParameter("email");
		var passwordParam = req.getParameter("password");
		var phoneParam = req.getParameter("phone");
		var genderParam = req.getParameter("gender");
		var dateOfBirthParam = req.getParameter("dateOfBirth");
		var specialistParam = req.getParameter("specialist");
		var statusParam = req.getParameter("status");
		var streetParam = req.getParameter("street");
		var cityParam = req.getParameter("city");
		var stateParam = req.getParameter("state");
		var countryParam = req.getParameter("country");
		
		Long id = StringUtils.parseLong(idParam);
		
		var doctor = new Doctor();
		var address = new Address();
		
		if (id != null) {
			doctor = doctorService.findById(id);
			address = doctor.getAddress();
			doctor.setStatus(Status.valueOf(statusParam));
		} else {
			doctor.setUsername(usernameParam);
			doctor.setPassword(passwordParam);
			doctor.setStatus(Status.ACTIVE);
		}
		
		doctor.setName(nameParam);
		doctor.setEmail(emailParam);
		doctor.setPhone(phoneParam);
		doctor.setDateOfBirth(DateUtils.parseLocalDate(dateOfBirthParam, "yyyy-MM-dd"));
		doctor.setGender(genderParam == null ? null : Gender.valueOf(genderParam));
		doctor.setRole(Role.DOCTOR);
		
		address.setStreet(streetParam);
		address.setCity(cityParam);
		address.setState(stateParam);
		address.setCountry(countryParam);
		
		doctor.setAddress(address);
		
		var specialist = Application.getDoctorSpecialistService().findById(StringUtils.parseLong(specialistParam));
		
		doctor.setSpecialist(specialist);
		
		var doctorDto = EntityMapper.toDoctorDto(doctor);
		
		var session = req.getSession();
		
		try {
			doctorService.save(doctor);
		} catch (ServiceException e) {
			session.setAttribute("doctorFormException", e);
			session.setAttribute("doctor", doctorDto);
			redirect(resp, "%s%s".formatted(DOCTOR_EDIT_URL, (id == null || id == 0) ? "" : "?id=" + id));
			return;
		}
		
		redirect(resp, DOCTORS_SEARCH_URL);
	}

	private void deleteDoctor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		var idParam = req.getParameter("id");
		var id = StringUtils.parseLong(idParam);
		
		if (id == null) {
			//TODO implement error
		}
		
		try {
			doctorService.deleteById(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		redirect(resp, DOCTORS_SEARCH_URL);
	}

	private void searchDoctors(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var keywordParam = req.getParameter("k");
		var statusParam = req.getParameter("s");
		
		Status status = null;
		
		if (statusParam != null) {
			status = switch (statusParam) {
						case "active" -> Status.ACTIVE;
						case "closed" -> Status.CLOSED;
						default -> null;
					  };
		}
		
		var doctors = doctorService.search(keywordParam, status);
		
		var doctorDtos = EntityMapper.toDoctorDtoList(doctors);
		
		req.setAttribute("doctors", doctorDtos);
		
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Doctors")
				.setViewTitle("Doctors")
				.setActiveMenu("doctors")
				.setView(DOCTORS_JSP_LOCATION)
				.build()
			);
	}
	
	private void navigateToDoctorProfilePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var id = StringUtils.parseLong(req.getParameter("id"));
		var doctor = doctorService.findById(id);
		
		req.setAttribute("doctor", EntityMapper.toDoctorDto(doctor));
		
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Doctor Profile")
				.setViewTitle("Doctor Profile")
				.setActiveMenu("doctors")
				.setView(DOCTOR_PROFILE_JSP_LOCATION)
				.build()
			);
	}
	
	private void navigateToEditDoctorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var idParam = req.getParameter("id");
		
		Long id = StringUtils.parseLong(idParam);
		
		var session = req.getSession();
		
		req.setAttribute("specialists", doctorSpecialistService.search(null));
		req.setAttribute("doctor", cutAttributeFromSession(session, "doctor"));
		req.setAttribute("doctorFormException", cutAttributeFromSession(session, "doctorFormException"));
		
		var navBuilder = new NavigationInfo.Builder();
		navBuilder.setRequest(req)
				  .setResponse(resp)
				  .setActiveMenu("doctors");
		
		if (id == null) {
			navBuilder.setPageTitle("Add New Doctor")
					  .setViewTitle("Add New Doctor")
					  .setView(ADD_DOCTOR_JSP_LOCATION);
		} else {
			var doctorAttribute = req.getAttribute("doctor");
			req.setAttribute("doctor", doctorAttribute == null ? EntityMapper.toDoctorDto(doctorService.findById(id)) : doctorAttribute );
			navBuilder.setPageTitle("Edit Doctor")
			  		  .setViewTitle("Edit Doctor")
			  		  .setView(EDIT_DOCTOR_JSP_LOCATION);
		}
		
		navigate(navBuilder.build());
	}

}
