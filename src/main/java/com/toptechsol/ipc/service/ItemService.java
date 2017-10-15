package com.toptechsol.ipc.service;

import java.util.List;

import com.toptechsol.ipc.model.Item;

public interface ItemService {
	Item findById(Long id);
	Item save(Item item);
	List<Item> findAll();
	List<Item> findByCategry(String categoryId);
	Item findByIdAndCategoryId(Long id, String categoryId);
	void deleteItem(Long id);
}
