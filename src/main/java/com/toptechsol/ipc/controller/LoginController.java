package com.toptechsol.ipc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.toptechsol.ipc.model.Category;
import com.toptechsol.ipc.model.User;
import com.toptechsol.ipc.service.ItemService;
import com.toptechsol.ipc.service.NodeService;
import com.toptechsol.ipc.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private ItemService itemService;
	
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
		Category category = nodeService.loadTree(1);
		modelAndView.addObject("nodes", category);
		Category newNode = new Category();
		modelAndView.addObject("newNode", newNode);
		/*
		Category newNode = new Category();
		newNode.setName("test me" + System.currentTimeMillis());
		newNode.setDescription("Description" + System.currentTimeMillis());
		node.getChildren().add(newNode);
		nodeService.save(node);
		
		Item newItem = new Item();
		newItem.setSerialNumber("XSSDSD125" + System.currentTimeMillis());
		newItem.setName("Name" + System.currentTimeMillis());
		newItem.setDescription("Description" + System.currentTimeMillis());
		newItem.setCertificationDate(new Timestamp(System.currentTimeMillis()));
		newItem.setModified(new Timestamp(System.currentTimeMillis()));
		newItem.setCreated(new Timestamp(System.currentTimeMillis()));
		newItem.setNote("BSDfzlkdsfm aslskfdnlskdfjsd" + System.currentTimeMillis());
		newItem.setNode(node);
		itemService.save(newItem);
		*/
		return modelAndView;
	}
}
