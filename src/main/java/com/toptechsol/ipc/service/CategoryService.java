package com.toptechsol.ipc.service;

import com.toptechsol.ipc.model.Category;

public interface CategoryService {
	public Category findById(String id);
	public Category save(Category category);
	public void delete(Category category);
}
