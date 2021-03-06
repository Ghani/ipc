package com.toptechsol.ipc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toptechsol.ipc.model.Category;
import com.toptechsol.ipc.model.Certificate;
import com.toptechsol.ipc.model.JsTreeNode;
import com.toptechsol.ipc.model.State;
import com.toptechsol.ipc.model.User;
import com.toptechsol.ipc.service.CategoryService;
import com.toptechsol.ipc.service.CertificateService;
import com.toptechsol.ipc.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CertificateService certificateService;
	
    @Autowired
    private ObjectMapper mapper;
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		List<Certificate> expiredDocuments =this.certificateService.listExpiredDocument(Calendar.getInstance().getTime());
		Calendar calendar = Calendar.getInstance();
		Date startDate = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		Date endDate = calendar.getTime();
		System.out.println("startDate: " + startDate  + " endDate: " + endDate);
		List<Certificate> documentsWillExpire =this.certificateService.listDocumentWillExpireInMonths(startDate,endDate);
		modelAndView.addObject("expiredDocuments",expiredDocuments );
		modelAndView.addObject("documentsWillExpire",documentsWillExpire );
		/*
		Category category = categoryService.findById("root");
		modelAndView.addObject("categories", category);
		Category newCategory = new Category();
		modelAndView.addObject("newCategory", newCategory);
		modelAndView.addObject("treeData", getJsonStringTree(category, String.valueOf(category.getId())));
		*/
		return modelAndView;
	}
	
	public static String getJsonStringTree(Category parent, String selectedItem)  {

		if (parent == null) {
			return "";
		}

		ObjectMapper objMapper = new ObjectMapper();
		String strTreeRoot = "";

		try {

			JsTreeNode treeRoot = new JsTreeNode();

			treeRoot.setId(String.valueOf(parent.getId()));
			//treeRoot.setIcon("glyphicon glyphicon-leaf");
			treeRoot.setText(parent.getName());
			State state = new State();
			state.setOpened(true);
			if (treeRoot.getId().equals(selectedItem)) {
				//state.setSelected(true);
			}
			treeRoot.setState(state);
			treeRoot.setChildren(new ArrayList<JsTreeNode>());

			for (Category item : parent.getChildren()) {
				JsTreeNode child = addChild(item, selectedItem, false);
				treeRoot.getChildren().add(child);
			}
			if (treeRoot.getChildren() != null) {
				if (treeRoot.getChildren().size() > 0) {
					strTreeRoot = objMapper.writeValueAsString(treeRoot);
				}
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "[" + strTreeRoot + "]";
	}

	
	public static JsTreeNode addChild(Category item, String selectedItem, boolean selected) {
		JsTreeNode child = new JsTreeNode();
		child.setId(String.valueOf(item.getId()));
		//child.setIcon("glyphicon glyphicon-leaf");
		child.setText(item.getName());
		State state = new State();
		state.setOpened(true);
		if (child.getId() != null) {
			if (child.getId().equals(selectedItem)) {
				state.setSelected(true);
			} else {
				state.setSelected(selected);
			}
		}
		child.setState(state);
		child.setChildren(new ArrayList<JsTreeNode>());
		for (Category itemChild : item.getChildren()) {
			JsTreeNode childChild = addChild(itemChild, selectedItem, false);
			child.getChildren().add(childChild);
		}
		return child;
	}
	
	@RequestMapping(value="/admin/tree", method = RequestMethod.GET)
	public @ResponseBody String getTreeInJSON() {
		Category category = categoryService.findById("root");
		return  getJsonStringTree(category, String.valueOf(category.getId()));

	}
	
	@ResponseBody
	@RequestMapping(value = "/admin/category/{parentCategoryId}/{categoryId}/{categoryName}/addcategory",  method = RequestMethod.POST)
	public String addUpdateCategory(@PathVariable String parentCategoryId,@PathVariable String categoryId,@PathVariable String categoryName ) {
		System.out.println("categoryId: " + categoryId);
		Category category = categoryService.findById(categoryId);
		if (category!=null){
			category.setName(categoryName);
			categoryService.save(category);
			return category.getId();
		}else{
			Category parentCategory = categoryService.findById(parentCategoryId);
			if (parentCategory!=null){
				Category newCategory = new Category();
				newCategory.setParent(parentCategory);
				newCategory.setName(categoryName);
				newCategory.setId(categoryId);
				newCategory.setDescription(categoryName);
				newCategory.setParentId(parentCategoryId);
				newCategory  = categoryService.save(newCategory);
				return newCategory.getId();
			}
		}
		return "error";
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin/category/{categoryId}",  method = RequestMethod.DELETE)
	public Integer deleteCategory(@PathVariable String categoryId ) {
		Category category = categoryService.findById(categoryId);
		if (category!=null){
			categoryService.delete(category);
			return 1;
		}
		return 0;
	}
}
