package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.DOCTOR_SPECIALISTS_DELETE_URL;
import static me.khun.clinic.application.Application.DOCTOR_SPECIALISTS_EDIT_URL;
import static me.khun.clinic.application.Application.DOCTOR_SPECIALISTS_SAVE_URL;
import static me.khun.clinic.application.Application.DOCTOR_SPECIALISTS_SEARCH_URL;
import static me.khun.clinic.application.Application.SPECIALISTS_JSP_LOCATION;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.khun.clinic.application.Application;
import me.khun.clinic.model.entity.DoctorSpecialist;
import me.khun.clinic.model.entity.mapper.EntityMapper;
import me.khun.clinic.model.exception.EntityNotFoundException;
import me.khun.clinic.model.service.DoctorSpecialistService;
import me.khun.clinic.model.service.exception.EntityCanNotBeDeletedException;
import me.khun.clinic.model.service.exception.ServiceException;
import me.khun.clinic.util.StringUtils;
import me.khun.clinic.view.AlertMessage;
import me.khun.clinic.view.AlertMessage.Status;

@WebServlet(
	urlPatterns = {
		DOCTOR_SPECIALISTS_SEARCH_URL,
		DOCTOR_SPECIALISTS_EDIT_URL,
		DOCTOR_SPECIALISTS_SAVE_URL,
		DOCTOR_SPECIALISTS_DELETE_URL
	}
)
public class DoctorSpecialistController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private DoctorSpecialistService doctorSpecialistService;
	
	@Override
	public void init() throws ServletException {
		doctorSpecialistService = Application.getDoctorSpecialistService();
	}
	
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

	private void deleteDoctorSpecialist(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		var idParam = req.getParameter("id");
		
		Long id = StringUtils.parseLong(idParam);
		
		try {
			doctorSpecialistService.deleteById(id);
		} catch (EntityCanNotBeDeletedException e) {
			req.getSession().setAttribute("alertMessage", new AlertMessage(e.getMessage(), Status.ERROR));
		}
		
		redirect(resp, DOCTOR_SPECIALISTS_SEARCH_URL);
	}

	private void navigationToEditDoctorSpecialistPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var session = req.getSession();
		req.setAttribute("doctorSpecialistFormException", cutAttributeFromSession(session, "doctorSpecialistFormException"));
		var doctorSpecialist = (DoctorSpecialist) cutAttributeFromSession(session, "doctorSpecialist");
		navigationToEditDoctorSpecialistPage(doctorSpecialist, req, resp);
	}
	
	private void navigationToEditDoctorSpecialistPage(DoctorSpecialist specialist, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (specialist == null) {
			var idParam = req.getParameter("id");
			
			DoctorSpecialist doctorSpecialist = null;
			
			Long id;
			
			try {
				id = Long.parseLong(idParam);
				doctorSpecialist = doctorSpecialistService.findById(id);
			} catch (NumberFormatException e) {
				doctorSpecialist =  new DoctorSpecialist();
			} catch (EntityNotFoundException e) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getEntityName() + " not found.");
				return;
			}
			
			req.setAttribute("doctorSpecialist", EntityMapper.toDoctorSpecialistDto(doctorSpecialist));
		} else {
			req.setAttribute("doctorSpecialist", EntityMapper.toDoctorSpecialistDto(specialist));
		}
		
		searchDoctorSpecialists(req, resp);
	}

	private void saveDoctorSpecialist(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		var idParam = req.getParameter("id");
		var nameParam = req.getParameter("name");
		var descriptionParam = req.getParameter("description");
		
		Long id = StringUtils.parseLong(idParam);
		
		var doctorSpecialist = new DoctorSpecialist(id, nameParam, descriptionParam);
		
		var session = req.getSession();
		
		try {
			doctorSpecialistService.save(doctorSpecialist);
		} catch (ServiceException e) {
			session.setAttribute("doctorSpecialistFormException", e);
			session.setAttribute("doctorSpecialist", doctorSpecialist);
			redirect(resp, "%s%s".formatted(DOCTOR_SPECIALISTS_EDIT_URL, (id == null || id == 0) ? "" : "?id=" + id));
			return;
		}
		
		redirect(resp, DOCTOR_SPECIALISTS_SEARCH_URL);
	}

	private void searchDoctorSpecialists(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var keywordParam = req.getParameter("k");
		
		var doctorSpecialists = doctorSpecialistService.search(keywordParam);
		
		var doctorSpecialistDtos = EntityMapper.toDoctorSpecialistDtoList(doctorSpecialists);
		
		var session = req.getSession();
		
		req.setAttribute("specialists", doctorSpecialistDtos);
		req.setAttribute("alertMessage", cutAttributeFromSession(session, "alertMessage"));
		
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