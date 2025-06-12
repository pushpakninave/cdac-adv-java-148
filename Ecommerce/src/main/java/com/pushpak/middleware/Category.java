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

import org.apache.catalina.connector.Response;

/**
 * Servlet implementation class Category
 */
@WebServlet("/category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection dbConnection = null;
	    PreparedStatement getCategoryStatement = null;
	    ResultSet res = null;
	    
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    // HTML Header
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Product Categories</title>");
	    out.println("<style>");
	    out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; }");
	    out.println("th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
	    out.println("th { background-color: #4CAF50; color: white; }");
	    out.println("tr:hover { background-color: #f5f5f5; }");
	    out.println("img { border-radius: 5px; }");
	    out.println("</style>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h2 style='text-align:center'>Our Product Categories</h2>");
	    out.println("<table>");
	    out.println("<tr><th>Category</th><th>Description</th><th>Image</th></tr>");
	    
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root", "pushpak123");
			getCategoryStatement = dbConnection.prepareStatement("select * from category");
			res = getCategoryStatement.executeQuery();
			while (res.next()) {
	            out.println("<tr>");
	            out.println("<td><a href='products?categoryId=" + 
	                       res.getInt("category_id") + "' style='text-decoration:none;color:#0066cc;'>" + 
	                       res.getString("category_name") + "</a></td>");
	            out.println("<td>" + res.getString("category_description") + "</td>");
	            out.println("<td><img src='assets/images/" + 
	            		res.getString("category_image_url")+ 
	                       "' height='80' width='80' alt='" + 
	                       res.getString("category_name") + "'/></td>");
	            out.println("</tr>");
	        }
	        
	    } catch (SQLException e) {
	        out.println("<tr><td colspan='3' style='color:red'>Error loading categories: " 
	                   + e.getMessage() + "</td></tr>");
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(res != null) {
					res.close();
				}
				if(getCategoryStatement != null) {
					getCategoryStatement.close();
				}
				if(dbConnection != null) {
					dbConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		out.println("</table>");
	    out.println("</body>");
	    out.println("</html>");
	}

}
