package com.toptechsol.ipc.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.toptechsol.ipc.model.Category;
import com.toptechsol.ipc.model.Certificate;
import com.toptechsol.ipc.model.Item;
import com.toptechsol.ipc.service.CategoryService;
import com.toptechsol.ipc.service.CertificateService;
import com.toptechsol.ipc.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CertificateService certificateService;
	
	private static String UPLOADED_FOLDER = "/home/abdelghani/dev/projects/toptechsol/ipc/temp/";

	@RequestMapping(value = "/admin/category/{categoryId}/additem", method = RequestMethod.GET)
	public ModelAndView additem(@PathVariable Integer categoryId) {
		ModelAndView modelAndView = new ModelAndView();
		Item newItem = new Item();
		newItem.setCategory(categoryService.findById(categoryId));
		modelAndView.addObject("newItem", newItem);
		modelAndView.setViewName("admin/additem");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/category/{categoryId}/saveitem", params = { "save" }, method = RequestMethod.POST)
	public String createItem(@PathVariable Integer categoryId, final Item item, final BindingResult bindingResult, final ModelMap model, @RequestParam("certificat") MultipartFile uploadfile) {
		if (bindingResult.hasErrors()) {
			return "admin/additem";
		}
		item.setCategory(categoryService.findById(categoryId));
		Item savedItem  = itemService.save(item);
		if (savedItem!=null){
			try {
				saveUploadedFiles(uploadfile.getBytes(), savedItem.getSerialNumber() + "_"  + uploadfile.getOriginalFilename());
				certificateService.save(new Certificate(savedItem.getId(),savedItem.getSerialNumber() + "_" + uploadfile.getOriginalFilename()));
			} catch (IOException e) {
				// To do
			}
			model.clear();
		}
		return "redirect:/admin/category/" +categoryId + "/items";
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

	@RequestMapping(value = "/admin/category/{categoryId}/item/{id}", method = RequestMethod.GET)
	public ModelAndView getItem(@PathVariable Integer categoryId, @PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		Item item =this.itemService.findByIdAndCategoryId(id, categoryId);
		modelAndView.addObject("item",item );
		modelAndView.setViewName("admin/item");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/category/{categoryId}/updateitem", params = { "update" }, method = RequestMethod.POST)
	public String updateItem(@PathVariable Integer categoryId,final Item item, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "admin/updateitem";
		}
		item.setCategory(categoryService.findById(categoryId));
		itemService.save(item);
		model.clear();
		return "redirect:/admin/category/" +categoryId + "/items";
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin/deleteitem/{id}", method = RequestMethod.DELETE)
	public Long deleteItem(@PathVariable Long  id) {
		itemService.deleteItem(id);
		return id;
	}
	
	private void saveUploadedFiles(byte[] bytes, String fileName) throws IOException {
            Path path = Paths.get(UPLOADED_FOLDER + fileName );
            Files.write(path, bytes);


    }

}
