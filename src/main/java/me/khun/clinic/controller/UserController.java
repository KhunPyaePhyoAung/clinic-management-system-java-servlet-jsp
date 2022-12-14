package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.ADD_DOCTOR_JSP_LOCATION;
import static me.khun.clinic.application.Application.CHANGE_PASSWORD_JSP_LOCATION;
import static me.khun.clinic.application.Application.CHANGE_PASSWORD_URL;
import static me.khun.clinic.application.Application.DOCTOR_PROFILE_JSP_LOCATION;
import static me.khun.clinic.application.Application.HOME_URL;
import static me.khun.clinic.application.Application.PROFILE_EDIT_URL;
import static me.khun.clinic.application.Application.PROFILE_SAVE_URL;
import static me.khun.clinic.application.Application.PROFILE_URL;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.khun.clinic.application.Application;
import me.khun.clinic.model.service.UserService;
import me.khun.clinic.model.service.exception.ServiceException;
import me.khun.clinic.util.StringUtils;

@WebServlet(
	urlPatterns = {
		HOME_URL,
		CHANGE_PASSWORD_URL,
		PROFILE_URL,
		PROFILE_EDIT_URL,
		PROFILE_SAVE_URL
	}
)
public class UserController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	
	@Override
	public void init() {
		this.userService = Application.getUserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
			case HOME_URL -> {
				loadHome(req, resp);
			}
			
			case CHANGE_PASSWORD_URL -> {
				navigateToChangePasswordPage(req, resp);
			}
			
			case PROFILE_URL -> {
				navigateToProfilePage(req, resp);
			}
			
			case PROFILE_EDIT_URL -> {
				navigateToEditProfilePage(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
			
			case CHANGE_PASSWORD_URL -> {
				savePassword(req, resp);
			}
			
			case PROFILE_SAVE_URL -> {
				saveProfile(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
		}
	}
	
	private void saveProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		redirect(resp, PROFILE_URL);
	}

	private void navigateToEditProfilePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Edit Your Profile")
				.setViewTitle("Edit Your Profile")
				.setActiveMenu("user")
				.setView(ADD_DOCTOR_JSP_LOCATION)
				.build()
			);
	}

	private void savePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		var id = StringUtils.parseLong(req.getParameter("id"));
		var oldPassword = req.getParameter("oldPassword");
		var newPassword = req.getParameter("newPassword");
		
		try {
			userService.changePassword(id, oldPassword, newPassword);
		} catch (ServiceException e) {
			req.setAttribute("exception", e);
			navigateToChangePasswordPage(req, resp);
			return;
		}
		
		redirect(resp, HOME_URL);
	}

	private void navigateToProfilePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Your Profile")
				.setViewTitle("Your Profile")
				.setActiveMenu("user")
				.setView(DOCTOR_PROFILE_JSP_LOCATION)
				.build()
			);
	}

	private void navigateToChangePasswordPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Change Password")
				.setViewTitle("ChangePassword")
				.setActiveMenu("user")
				.setView(CHANGE_PASSWORD_JSP_LOCATION)
				.build()
			);
	}

	private void loadHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		navigate(new NavigationInfo.Builder()
				.setRequest(req)
				.setResponse(resp)
				.setPageTitle("Home")
				.setViewTitle("Home")
				.setActiveMenu("home")
				.build()
			);
	}

}
