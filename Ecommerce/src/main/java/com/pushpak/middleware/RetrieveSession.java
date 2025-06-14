package com.pushpak.middleware;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class RetrieveSession
 */
@WebServlet("/RetrieveSession")
public class RetrieveSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if (session == null) {
			out.println("You are not authenticated");
		} else {
			out.println("User name: " + session.getAttribute("userName"));
			out.println("Id: " + session.getId());
			out.println("Creation time: " + session.getCreationTime());
			out.println("last accessed time: " + session.getLastAccessedTime());
			out.println("Inactive interval: " + session.getMaxInactiveInterval());
		}
	}

}
