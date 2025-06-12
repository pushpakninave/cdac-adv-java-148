package com.pushpak.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pushpak.dao.interfaces.CategoryDao;
import com.pushpak.entity.Category;

public class CategoryDAOImpl implements CategoryDao {

	Connection dbConnection = null;
	PreparedStatement categoryStatement = null;

	public CategoryDAOImpl() {
		// initiate database connection.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root", "pushpak123");
			categoryStatement = dbConnection.prepareStatement("select * from category");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Category> getAllCategories() {
		ResultSet res = null;
		try {
			res = categoryStatement.executeQuery();
			List<Category> categoryList = new ArrayList<>();
			while (res.next()) {
				Category categoryObj = new Category(res.getInt(1), res.getString(2), res.getString(3),
						res.getString(4));
				categoryList.add(categoryObj);
			}
			return categoryList.iterator();
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
		
		return null;
	}

}
