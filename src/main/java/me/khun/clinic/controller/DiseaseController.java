package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.*;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
	urlPatterns = {
		DISEASES_SEARCH_URL,
		DISEASE_EDIT_URL,
		DISEASES_SAVE_URL,
		DISEASE_DELETE_URL,
		DISEASE_RESTORE_URL
	}
)
public class DiseaseController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case DISEASES_SEARCH_URL -> {
				searchDiseases(req, resp);
			}
			
			case DISEASE_EDIT_URL -> {
				navigateToEditDiseasePage(req, resp);
			}
			
			case DISEASE_DELETE_URL -> {
				deleteDisease(req, resp);
			}
			
			case DISEASE_RESTORE_URL -> {
				restoreDisease(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case DISEASES_SAVE_URL -> {
				saveDisease(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}

	private void navigateToEditDiseasePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Diseases")
				.setViewTitle("Diseases")
				.setActiveMenu("diseases")
				.setView(DISEASES_JSP_LOCATION)
				.build()
			);
	}

	private void saveDisease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, DISEASES_SEARCH_URL);
	}
	
	private void deleteDisease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, DISEASES_SEARCH_URL);
	}

	private void restoreDisease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, DISEASES_SEARCH_URL);
	}

	private void searchDiseases(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Diseases")
				.setViewTitle("Diseases")
				.setActiveMenu("diseases")
				.setView(DISEASES_JSP_LOCATION)
				.build()
			);
	}

}
