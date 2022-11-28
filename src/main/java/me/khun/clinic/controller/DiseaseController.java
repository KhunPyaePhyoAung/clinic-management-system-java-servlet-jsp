package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.DISEASES_JSP_LOCATION;
import static me.khun.clinic.application.Application.DISEASES_SAVE_URL;
import static me.khun.clinic.application.Application.DISEASES_SEARCH_URL;
import static me.khun.clinic.application.Application.DISEASE_DELETE_URL;
import static me.khun.clinic.application.Application.DISEASE_EDIT_URL;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.khun.clinic.application.Application;
import me.khun.clinic.model.dto.DiseaseDto;
import me.khun.clinic.model.entity.Disease;
import me.khun.clinic.model.service.DiseaseService;
import me.khun.clinic.model.service.exception.ServiceException;
import me.khun.clinic.util.StringUtils;

@WebServlet(
	urlPatterns = {
		DISEASES_SEARCH_URL,
		DISEASE_EDIT_URL,
		DISEASES_SAVE_URL,
		DISEASE_DELETE_URL
	}
)
public class DiseaseController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private DiseaseService diseaseService;
	
	@Override
	public void init() throws ServletException {
		diseaseService = Application.getDiseaseService();
	}
	
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
		var session = req.getSession();
		
		var id  = StringUtils.parseLong(req.getParameter("id"));
		
		var disease = (Disease) cutAttributeFromSession(session, "disease");
		
		req.setAttribute("disease", disease == null ? id == null ? DiseaseDto.of(new Disease()) : diseaseService.findById(id) : disease);
		req.setAttribute("diseaseFormException", cutAttributeFromSession(session, "diseaseFormException"));
		
		var diseases = diseaseService.search(null);
		
		req.setAttribute("diseases", DiseaseDto.ofList(diseases));
		
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
		var id = StringUtils.parseLong(req.getParameter("id"));
		var name = req.getParameter("name");
		var description = req.getParameter("description");
		
		var disease = new Disease();
		disease.setId(id);
		disease.setName(name);
		disease.setDescription(description);
		
		var session = req.getSession();
		
		try {
			diseaseService.save(disease);
		} catch (ServiceException e) {
			session.setAttribute("diseaseFormException", e);
			session.setAttribute("disease", disease);
			redirect(resp, "%s%s".formatted(DISEASE_EDIT_URL, (disease.getId() == null || disease.getId() == 0) ? "" : "?id=" + disease.getId()));
			return;
		}
		redirect(resp, DISEASES_SEARCH_URL);
	}
	
	private void deleteDisease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, DISEASES_SEARCH_URL);
	}

	private void searchDiseases(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var keyword = req.getParameter("k");
		
		var diseases = diseaseService.search(keyword);
		
		req.setAttribute("diseases", DiseaseDto.ofList(diseases));
		
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
