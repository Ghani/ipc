package com.toptechsol.ipc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.toptechsol.ipc.model.Item;
import com.toptechsol.ipc.service.ItemService;
import com.toptechsol.ipc.service.NodeService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private NodeService nodeService;

	@RequestMapping(value = "/admin/additem", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		Item newItem = new Item();
		newItem.setNode(nodeService.loadTree(1));
		modelAndView.addObject("newItem", newItem);
		modelAndView.setViewName("admin/additem");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/saveitem", params = { "save" }, method = RequestMethod.POST)
	public String createItem(final Item item, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "admin/additem";
		}
		item.setNode(nodeService.loadTree(1));
		itemService.save(item);
		model.clear();
		return "redirect:admin/items";
	}

	@ModelAttribute("items")
	@RequestMapping(value = "/admin/items", method = RequestMethod.GET)
	public List<Item> getListofItems() {
		return this.itemService.findAll();
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
		item.setNode(nodeService.loadTree(1));
		itemService.save(item);
		model.clear();
		return "redirect:admin/items";
	}

}
