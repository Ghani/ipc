package com.toptechsol.ipc.service;

import com.toptechsol.ipc.model.Category;

public interface NodeService {
	public Category loadTree(Integer id);
	public void save(Category category);
}
