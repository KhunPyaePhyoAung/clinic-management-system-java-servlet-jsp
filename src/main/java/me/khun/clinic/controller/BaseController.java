package me.khun.clinic.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.khun.clinic.application.Application;

public abstract class BaseController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void navigate(NavigationInfo navInfo) throws ServletException, IOException {
		getServletContext()
		.getRequestDispatcher(Application.INDEX_JSP_LOCATION)
		.forward(navInfo.getRequest(), navInfo.getResponse());
	}

	protected void redirect(HttpServletResponse resp, String path) throws IOException {
		resp.sendRedirect(getServletContext().getContextPath().concat(path));
	}

	protected static class NavigationInfo {

		private HttpServletRequest request;

		private HttpServletResponse response;

		private String pageTitle;

		private String viewTitle;

		private String activeMenu;

		private String view;

		private NavigationInfo(HttpServletRequest request, HttpServletResponse response,
								String pateTitle, String viewTitle,
								String activeMenu, String view) {

			this.request = request;
			this.response = response;
			this.pageTitle = pateTitle;
			this.viewTitle = viewTitle;
			this.activeMenu = activeMenu;
			this.view = view;
		}

		public HttpServletRequest getRequest() {
			return request;
		}

		public HttpServletResponse getResponse() {
			return response;
		}

		public String getPageTitle() {
			return pageTitle;
		}

		public String getViewTitle() {
			return viewTitle;
		}

		public String getActiveMenu() {
			return activeMenu;
		}

		public String getView() {
			return view;
		}

		public static class Builder {

			private HttpServletRequest request;

			private HttpServletResponse response;

			private String pageTitle;

			private String viewTitle;

			private String activeMenu;

			private String view;

			public Builder setRequest(HttpServletRequest request) {
				this.request = request;
				return this;
			}

			public Builder setResponse(HttpServletResponse response) {
				this.response = response;
				return this;
			}

			public Builder setPageTitle(String pageTitle) {
				this.pageTitle = pageTitle;
				return this;
			}

			public Builder setViewTitle(String viewTitle) {
				this.viewTitle = viewTitle;
				return this;
			}

			public Builder setActiveMenu(String activeMenu) {
				this.activeMenu = activeMenu;
				return this;
			}

			public Builder setView(String view) {
				this.view = view;
				return this;
			}

			public NavigationInfo build() {
				request.setAttribute("pageTitle", pageTitle);
				request.setAttribute("viewTitle", viewTitle);
				request.setAttribute("activeMenu", activeMenu);
				request.setAttribute("content", view);

				return new NavigationInfo(request, response, pageTitle, viewTitle, activeMenu, view);
			}

		}

	}

}