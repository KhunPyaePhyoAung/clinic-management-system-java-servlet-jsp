package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.MEDICINES_DELETE_URL;
import static me.khun.clinic.application.Application.MEDICINES_EDIT_URL;
import static me.khun.clinic.application.Application.MEDICINES_JSP_LOCATION;
import static me.khun.clinic.application.Application.MEDICINES_RESTORE_URL;
import static me.khun.clinic.application.Application.MEDICINES_SAVE_URL;
import static me.khun.clinic.application.Application.MEDICINES_SEARCH_URL;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
	urlPatterns = {
		MEDICINES_SEARCH_URL,
		MEDICINES_EDIT_URL,
		MEDICINES_SAVE_URL,
		MEDICINES_DELETE_URL,
		MEDICINES_RESTORE_URL
	}
)
public class MedicineController extends BaseController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case MEDICINES_SEARCH_URL -> {
				searchMedicines(req, resp);
			}
			
			case MEDICINES_EDIT_URL -> {
				navigateToEditMedicinePage(req, resp);
			}
			
			case MEDICINES_DELETE_URL -> {
				deleteMedicine(req, resp);
			}
			
			case MEDICINES_RESTORE_URL -> {
				restoreMedicine(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case MEDICINES_SAVE_URL -> {
				saveMedicine(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}
	
	private void restoreMedicine(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, MEDICINES_SEARCH_URL);
	}

	private void deleteMedicine(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, MEDICINES_SEARCH_URL);
	}

	private void navigateToEditMedicinePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Medicines")
				.setViewTitle("Medicines")
				.setActiveMenu("medicines")
				.setView(MEDICINES_JSP_LOCATION)
				.build()
			);
	}

	private void saveMedicine(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, MEDICINES_SEARCH_URL);
	}

	private void searchMedicines(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Medicines")
				.setViewTitle("Medicines")
				.setActiveMenu("medicines")
				.setView(MEDICINES_JSP_LOCATION)
				.build()
			);
	}
}
