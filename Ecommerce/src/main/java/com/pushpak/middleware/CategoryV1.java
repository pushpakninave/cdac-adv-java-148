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
import java.util.Iterator;

import com.pushpak.dao.impl.CategoryDAOImpl;
import com.pushpak.dao.interfaces.CategoryDao;
import com.pushpak.entity.Category;

/**
 * Servlet implementation class CategoryV1
 */
@WebServlet("/categoryV1")
public class CategoryV1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    CategoryDao categoryDAO = new CategoryDAOImpl();
	    Iterator<Category> categoryList = categoryDAO.getAllCategories();
	    
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
	    
	    while (categoryList.hasNext()) {
	    	Category category = categoryList.next();
            out.println("<tr>");
            out.println("<td><a href='products?categoryId=" + 
            		category.getCategoryId() + "' style='text-decoration:none;color:#0066cc;'>" + 
                       category.getCategoryName() + "</a></td>");
            out.println("<td>" + category.getCategoryDescription() + "</td>");
            out.println("<td><img src='assets/images/" + 
            		category.getCategoryImageUrl()+ 
                       "' height='80' width='80' alt='" + 
                       category.getCategoryName() + "'/></td>");
            out.println("</tr>");
        }
	
		out.println("</table>");
	    out.println("</body>");
	    out.println("</html>");
	}
}
