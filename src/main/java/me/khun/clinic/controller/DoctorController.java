package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.*;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		redirect(resp, DOCTORS_SEARCH_URL);
	}

	private void deleteDoctor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, DOCTORS_SEARCH_URL);
	}

	private void searchDoctors(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		
		var navBuilder = new NavigationInfo.Builder();
		navBuilder.setRequest(req)
				  .setResponse(resp)
				  .setActiveMenu("doctors");
		
		if (idParam == null) {
			navBuilder.setPageTitle("Add New Doctor")
					  .setViewTitle("Add New Doctor")
					  .setView(ADD_DOCTOR_JSP_LOCATION);
		} else {
			navBuilder.setPageTitle("Edit Doctor")
			  		  .setViewTitle("Edit Doctor")
			  		  .setView(EDIT_DOCTOR_JSP_LOCATION);
		}
		
		navigate(navBuilder.build());
	}

}
