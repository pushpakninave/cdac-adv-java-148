package com.pushpak.middleware;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class AuthenticateV1
 */
@WebServlet(initParams = { 
		@WebInitParam(name = "dbURL", value = "jdbc:mysql://localhost:3306/cdac"),
		@WebInitParam(name = "userName", value = "root"),
		@WebInitParam(name = "password", value = "pushpak123") 
		}, 
		urlPatterns = { "/authenticateV1" })
public class AuthenticateV1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection dbConnection = null;
	PreparedStatement authUserStatement = null;
	ResultSet res = null;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = config.getInitParameter("dbURL");
			String pwd = config.getInitParameter("password");
			String userName = config.getInitParameter("userName");
			dbConnection = DriverManager.getConnection(dbURL, pwd, userName);
			String sql = "SELECT * FROM user_info WHERE uname=? AND pwd=? LIMIT 1";
			authUserStatement = dbConnection.prepareStatement(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new ServletException("Failed to initialize authenticate", e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			if (res != null) {
				res.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("password");
		ResultSet res = null;
		try {
			authUserStatement.setString(1, userName);
			authUserStatement.setString(2, pwd);
			res = authUserStatement.executeQuery();
			if (res.next()) {
				response.sendRedirect("categoryV1");
			} else {
				out.println("Invalid username password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (res != null) {
					res.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
