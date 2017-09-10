package com.toptechsol.ipc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.toptechsol.ipc.model.Category;
import com.toptechsol.ipc.model.Item;
import com.toptechsol.ipc.service.CategoryService;
import com.toptechsol.ipc.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/admin/additem", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		Item newItem = new Item();
		newItem.setNode(categoryService.findById(1));
		modelAndView.addObject("newItem", newItem);
		modelAndView.setViewName("admin/additem");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/saveitem", params = { "save" }, method = RequestMethod.POST)
	public String createItem(final Item item, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "admin/additem";
		}
		item.setNode(categoryService.findById(1));
		itemService.save(item);
		model.clear();
		return "redirect:admin/items";
	}

	@RequestMapping(value = "/admin/category/{categoryId}/items", method = RequestMethod.GET)
	public ModelAndView getListofItems(@PathVariable Integer categoryId) {
		ModelAndView modelAndView = new ModelAndView();
		Category selectedCategory = categoryService.findById(categoryId);
			if (selectedCategory!=null){
			modelAndView.addObject("items",this.itemService.findByCategry(categoryId) );
			modelAndView.addObject("selectedCategory",selectedCategory );
			modelAndView.setViewName("admin/items");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/category/{categoryId}/item/{serialNumber}", method = RequestMethod.GET)
	public ModelAndView getItem(@PathVariable Integer categoryId, @PathVariable String serialNumber) {
		ModelAndView modelAndView = new ModelAndView();
		Item item =this.itemService.findBySerialNumberAndCategoryId(serialNumber, categoryId);
		modelAndView.addObject("item",item );
		modelAndView.setViewName("admin/item");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/updateitem", params = { "update" }, method = RequestMethod.POST)
	public String updateItem(final Item item, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "admin/updateitem";
		}
		item.setNode(categoryService.findById(1));
		itemService.save(item);
		model.clear();
		return "redirect:admin/items";
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin/deleteitem/{serialNumber}", method = RequestMethod.DELETE)
	public String deleteItem(@PathVariable String serialNumber) {
		itemService.deleteItem(serialNumber);
		return serialNumber;
	}

}
