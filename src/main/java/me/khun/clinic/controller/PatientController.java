package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.*;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
	urlPatterns = {
		PATIENTS_SEARCH_URL,
		PATIENT_EDIT_URL,
		PATIENT_SAVE_URL,
		PATIENT_DELETE_URL,
		PATIENT_PROFILE_URL
	}
)
public class PatientController extends BaseController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case PATIENTS_SEARCH_URL -> {
				searchPatients(req, resp);
			}
			
			case PATIENT_EDIT_URL -> {
				navigateToEditPatientPage(req, resp);
			}
			
			case PATIENT_DELETE_URL -> {
				deletePatient(req, resp);
			}
			
			case PATIENT_PROFILE_URL -> {
				navigateToPatientProfilePage(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
			
			case PATIENT_SAVE_URL -> {
				savePatient(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}
	
	private void navigateToPatientProfilePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Patient Profile")
				.setViewTitle("Patient Profile")
				.setActiveMenu("patients")
				.setView(PATIENT_PROFILE_JSP_LOCATION)
				.build()
			);
	}

	private void savePatient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, PATIENTS_SEARCH_URL);
	}

	private void deletePatient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, PATIENTS_SEARCH_URL);
	}

	private void navigateToEditPatientPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var idParam = req.getParameter("id");
		
		var navBuilder = new NavigationInfo.Builder();
		navBuilder.setRequest(req)
				  .setResponse(resp)
				  .setActiveMenu("patients");
		
		if (idParam == null) {
			navBuilder.setPageTitle("Add New Patient")
					  .setViewTitle("Add New Patient")
					  .setView(ADD_PATIENT_JSP_LOCATION);
		} else {
			navBuilder.setPageTitle("Edit Patient")
			  		  .setViewTitle("Edit Patient")
			  		  .setView(EDIT_PATIENT_JSP_LOCATION);
		}
		
		navigate(navBuilder.build());
	}

	private void searchPatients(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Patients")
				.setViewTitle("Patients")
				.setActiveMenu("patients")
				.setView(PATIENTS_JSP_LOCATION)
				.build()
			);
	}
}
