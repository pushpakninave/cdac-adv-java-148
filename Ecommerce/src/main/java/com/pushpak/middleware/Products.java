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
 * Servlet implementation class Products
 */
@WebServlet("/products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
	    PreparedStatement productList = null;
	    ResultSet res = null;
	    
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
	    out.println("<table border='1'>");
	    out.println("<tr>");
	    out.println("<th>Name</th>");
	    out.println("<th>Description</th>");
	    out.println("<th>Price</th>");
	    out.println("<th>Image</th>");
	    out.println("</tr>");
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/cdac", "root", "pushpak123");
			productList = dbConnection.prepareStatement("SELECT * FROM products WHERE category_id=?");
			
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			productList.setInt(1, categoryId);
			res = productList.executeQuery();
			if(!res.next()) {
				out.println("<font color='red'>Category not found</font>");

			}
			while (res.next()) {
		        out.println("<tr>");
		        out.println("<td>" + res.getString("product_name") + "</td>");
		        out.println("<td>" + res.getString("product_description") + "</td>");
		        out.println("<td>$" + res.getBigDecimal("product_price") + "</td>");   
		        out.println("<td><img src='assets/images/" + 
	            		res.getString("image_url")+ 
	                       " ' height='80' width='80' alt='" + 
	                       res.getString("product_name") + "'/></td>");
		        out.println("</tr>");
		    }
		}catch(ClassNotFoundException e) {
			out.println("<font color='red'>Database driver not found</font>");
	        e.printStackTrace();
		}
	    catch (SQLException e) {
			out.println("<tr><td colspan='4' style='color:red'>Error loading products: " + 
		               e.getMessage() + "</td></tr>");
			e.printStackTrace();
		}finally {
			try {
				if (res != null) res.close();
	            if (productList != null) productList.close();
	            if (dbConnection != null) dbConnection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	    
	    out.println("</table>");
	    out.println("</body></html>");
	}

}
