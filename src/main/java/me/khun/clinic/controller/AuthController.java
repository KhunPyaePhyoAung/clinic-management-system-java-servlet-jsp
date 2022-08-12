package me.khun.clinic.controller;

import static me.khun.clinic.application.Application.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import java.io.IOException;

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

	private void login(HttpServletRequest req, HttpServletResponse resp) {
		// TODO implement here
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) {
		// TODO implement here
	}

}