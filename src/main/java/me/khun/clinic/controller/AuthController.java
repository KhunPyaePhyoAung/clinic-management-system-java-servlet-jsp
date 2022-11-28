package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.LOGIN_URL;
import static me.khun.clinic.application.Application.LOGOUT_URL;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
	urlPatterns = {
		LOGIN_URL,
		LOGOUT_URL
	}
)
public class AuthController extends BaseController {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		
			case LOGIN_URL -> {
				navigateToLoginPage(req, resp);
			}
			
			default -> {
				throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
			}
				
		}
	}

	private void navigateToLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	@SuppressWarnings("unused")
	private void login(HttpServletRequest req, HttpServletResponse resp) {
		// TODO implement here
	}

	@SuppressWarnings("unused")
	private void logout(HttpServletRequest req, HttpServletResponse resp) {
		// TODO implement here
	}

}