package com.toptechsol.ipc.service;

import java.util.List;

import com.toptechsol.ipc.model.Item;

public interface ItemService {
	Item findBySerialNumber(String SerialNumber);
	void save(Item item);
	List<Item> findAll();
	List<Item> findByCategry();
	Item findBySerialNumberAndCategoryId(String serialNumber, Integer categoryId);
}
