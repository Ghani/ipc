package com.toptechsol.ipc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toptechsol.ipc.model.Category;
import com.toptechsol.ipc.repository.NodeRepository;

@Service("nodeService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private NodeRepository nodeRepository;
	
	@Override
	public Category findById(Integer id) {
		return nodeRepository.findById(id);
	}

	@Override
	public void save(Category category) {
		nodeRepository.save(category);
	}


}
