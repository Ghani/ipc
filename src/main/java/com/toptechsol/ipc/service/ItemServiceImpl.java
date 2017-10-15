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
	public Item findById(Long id) {
		return itemRepository.findOne(id);
	}

	@Override
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public List<Item> findByCategry(String categoryId) {
		return itemRepository.findByCategoryId(categoryId);
	}

	@Override
	public Item findByIdAndCategoryId(Long id, String categoryId) {
		return itemRepository.findByIdAndCategoryId(id, categoryId);
	}

	@Override
	public void deleteItem(Long id) {
		itemRepository.delete(id);		
	}

}
