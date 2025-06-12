package com.pushpak.middleware;

import jakarta.servlet.ServletException;
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
 * Servlet implementation class Authenticate
 */
@WebServlet("/authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		
		Connection dbConnection = null;
	    PreparedStatement authUserStatement = null;
	    ResultSet res = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        dbConnection = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/cdac", "root", "pushpak123");
	        
	        String sql = "SELECT 1 FROM user_info WHERE uname=? AND pwd=?";
	        authUserStatement = dbConnection.prepareStatement(sql);
	        authUserStatement.setString(1, userName);
	        authUserStatement.setString(2, password);
	        
	        res = authUserStatement.executeQuery();
	        
	        if (res.next()) {
	        	response.sendRedirect("category");
	            out.println("Welcome to the online shopping site");
	        } else {
	            out.println("<font color='red'>Invalid username or password</font>");
	        }
	        
	    } catch (ClassNotFoundException e) {
	        out.println("<font color='red'>Database driver not found</font>");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        out.println("<font color='red'>Database error occurred</font>");
	        e.printStackTrace();
	    } finally {
	        // resource cleanup
	        try {
	            if (res != null) res.close();
	            if (authUserStatement != null) authUserStatement.close();
	            if (dbConnection != null) dbConnection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
