package com.toptechsol.ipc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
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
import com.toptechsol.ipc.validation.FileValidator;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CertificateService certificateService;
	
	@Autowired
	FileValidator fileValidator;


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
	public String createItem(@PathVariable Integer categoryId, final Item item, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "admin/additem";
		}
		item.setCategory(categoryService.findById(categoryId));
		Item savedItem  = itemService.save(item);
		if (savedItem!=null){
			model.clear();
			return "redirect:/admin/category/" +categoryId + "/item/"+ savedItem.getId();
		}
		return null;
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
		Certificate certificate = new Certificate();
		modelAndView.addObject("item",item );
		modelAndView.addObject("newCertificate",certificate );
		modelAndView.setViewName("admin/item");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/category/{categoryId}/item/{id}/certificate", params = { "upload" }, method = RequestMethod.POST)
	public ModelAndView addCertificate(@PathVariable Integer categoryId, @PathVariable Long id, final Certificate certificate,final BindingResult bindingResult,@RequestParam("file") MultipartFile uploadfile) {
		ModelAndView modelAndView = new ModelAndView();
		Item item =this.itemService.findByIdAndCategoryId(id, categoryId);
		if (item !=null){
			try {
				certificate.setId(null);
				certificate.setContent(uploadfile.getBytes());
				certificate.setFilename(uploadfile.getOriginalFilename());
				certificate.setFileType(uploadfile.getContentType());
				certificate.setItem(item);
				certificate.setExpiryDate(certificate.getDate());
				certificateService.save(certificate);
				item.getCertificates().add(certificate);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		modelAndView.addObject("item",item );
		modelAndView.addObject("newCertificate", new Certificate());
		modelAndView.setViewName("admin/item");
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/category/{categoryId}/item/{itemId}/downloadcertificate/{id}", method = RequestMethod.GET)
	public void downloadDocument(@PathVariable Integer categoryId,@PathVariable Long itemId,@PathVariable Long id, HttpServletResponse response) {
		Certificate certificate =this.certificateService.findById(id);
		if (certificate!=null && certificate.getContent()!=null){
			 response.setContentType(certificate.getFileType());
		     response.setContentLength(certificate.getContent().length);
		     response.setHeader("Content-Disposition","attachment; filename=\"" + certificate.getFilename() +"\"");
		     try {
				FileCopyUtils.copy(certificate.getContent(), response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		}
	}
	
	@RequestMapping(value = "/admin/category/{categoryId}/item/{itemId}", params = { "update" }, method = RequestMethod.POST)
	public String updateItem(@PathVariable Integer categoryId,@PathVariable Long itemId,final Item item, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "admin/updateitem";
		}
		item.setId(itemId);
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
	
	@ResponseBody
	@RequestMapping(value = "/admin/deletedocument/{id}", method = RequestMethod.DELETE)
	public Long deleteDocument(@PathVariable Long  id) {
		this.certificateService.deleteCertificate(id);
		return id;
	}
}
