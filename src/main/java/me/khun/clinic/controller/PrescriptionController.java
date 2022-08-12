package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.*;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.khun.clinic.util.StringUtils;

@WebServlet(
	urlPatterns = {
		PRESCRIPTIONS_SEARCH_URL,
		PRESCRIPTION_EDIT_URL,
		PRESCRIPTION_SAVE_URL,
		PRESCRIPTION_DETAILS_URL,
		PRESCRIPTION_DELETE_URL
	}
)
public class PrescriptionController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case PRESCRIPTIONS_SEARCH_URL -> {
				searchPrescriptions(req, resp);
			}
			
			case PRESCRIPTION_EDIT_URL -> {
				navigateToEditPrescriptionPage(req, resp);
			}
			
			case PRESCRIPTION_DETAILS_URL -> {
				navigateToPrescriptionDetailsPage(req, resp);
			}
			
			case PRESCRIPTION_DELETE_URL -> {
				deletePrescription(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case PRESCRIPTION_SAVE_URL -> {
				savePrescription(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}

	private void savePrescription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redirect(resp, PRESCRIPTIONS_SEARCH_URL);
	}
	
	private void navigateToPrescriptionDetailsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Prescription Details")
				.setViewTitle("Prescription Details")
				.setActiveMenu("prescriptions")
				.setView(PRESCRIPTION_DETAILS_JSP_LOCATION)
				.build()
			);
	}

	private void deletePrescription(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, PRESCRIPTIONS_SEARCH_URL);
	}

	private void navigateToEditPrescriptionPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var idParam = req.getParameter("id");
		
		var title = StringUtils.isNullOrBlank(idParam) ? "Add New Prescription" : "Edit Prescription";
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle(title)
				.setViewTitle(title)
				.setActiveMenu("prescriptions")
				.setView(EDIT_PRESCRIPTION_JSP_LOCATION)
				.build()
			);
	}

	private void searchPrescriptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Prescriptions")
				.setViewTitle("Prescriptions")
				.setActiveMenu("prescriptions")
				.setView(PRESCRIPTIONS_JSP_LOCATION)
				.build()
			);
	}

}
