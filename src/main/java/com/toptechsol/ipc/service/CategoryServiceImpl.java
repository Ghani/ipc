package com.toptechsol.ipc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toptechsol.ipc.model.Category;
import com.toptechsol.ipc.repository.CategoryRepository;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category findById(Integer id) {
		return categoryRepository.findById(id);
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}


}
