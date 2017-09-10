package com.toptechsol.ipc.service;

import com.toptechsol.ipc.model.Category;

public interface CategoryService {
	public Category findById(Integer id);
	public void save(Category category);
}
