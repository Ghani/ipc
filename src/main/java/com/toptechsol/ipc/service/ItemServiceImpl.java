package com.toptechsol.ipc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toptechsol.ipc.model.Item;
import com.toptechsol.ipc.repository.ItemRepository;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item findBySerialNumber(String SerialNumber) {
		return itemRepository.findOne(SerialNumber);
	}

	@Override
	public void save(Item item) {
		itemRepository.save(item);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public List<Item> findByCategry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item findBySerialNumberAndCategoryId(String serialNumber, Integer categoryId) {
		return itemRepository.findBySerialNumberAndCategoryId(serialNumber, categoryId);
	}

}
