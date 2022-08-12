package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.*;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
	urlPatterns = {
		DOCTOR_SPECIALISTS_SEARCH_URL,
		DOCTOR_SPECIALISTS_EDIT_URL,
		DOCTOR_SPECIALISTS_SAVE_URL,
		DOCTOR_SPECIALISTS_DELETE_URL,
		DOCTOR_SPECIALISTS_RESTORE_URL
	}
)
public class DoctorSpecialistController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case DOCTOR_SPECIALISTS_SEARCH_URL -> {
				searchDoctorSpecialists(req, resp);
			}
			
			case DOCTOR_SPECIALISTS_EDIT_URL -> {
				navigationToEditDoctorSpecialistPage(req, resp);
			}
			
			case DOCTOR_SPECIALISTS_DELETE_URL -> {
				deleteDoctorSpecialist(req, resp);
			}
			
			case DOCTOR_SPECIALISTS_RESTORE_URL -> {
				restoreDoctorSpecialist(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case DOCTOR_SPECIALISTS_SAVE_URL -> {
				saveDoctorSpecialist(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}
	
	private void restoreDoctorSpecialist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, DOCTOR_SPECIALISTS_SEARCH_URL);
	}

	private void deleteDoctorSpecialist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, DOCTOR_SPECIALISTS_SEARCH_URL);
	}

	private void navigationToEditDoctorSpecialistPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Specialists")
				.setViewTitle("Specialists")
				.setActiveMenu("specialists")
				.setView(SPECIALISTS_JSP_LOCATION)
				.build()
			);
	}

	private void saveDoctorSpecialist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, DOCTOR_SPECIALISTS_SEARCH_URL);
	}

	private void searchDoctorSpecialists(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Specialists")
				.setViewTitle("Specialists")
				.setActiveMenu("specialists")
				.setView(SPECIALISTS_JSP_LOCATION)
				.build()
			);
	}

}