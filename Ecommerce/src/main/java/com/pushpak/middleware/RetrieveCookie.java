package com.pushpak.middleware;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class RetrieveCookie
 */
@WebServlet("/retrieveCookie")
public class RetrieveCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		Cookie[] arrCookies = request.getCookies();
		
		if(arrCookies != null) {
			for(Cookie objCookie : arrCookies) {
				out.println("Name : " + objCookie.getName());
				out.println("Value : " + objCookie.getValue());
			}
		}else {
			out.println("No cookies available");
		}
	}

}
