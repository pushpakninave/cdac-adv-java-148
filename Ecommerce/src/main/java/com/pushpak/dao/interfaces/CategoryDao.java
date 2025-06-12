package com.pushpak.dao.interfaces;

import java.util.Iterator;

import com.pushpak.entity.Category;

public interface CategoryDao {
	public Iterator<Category> getAllCategories();
}
