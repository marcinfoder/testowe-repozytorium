package com.capgemini.rest.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.persistence.domain.User;
import com.capgemini.service.UserService;


@Controller
public class MyAccountController {

	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/myaccount")
	public String emptyPage(Model model, Principal princ) {
		User user = userService.getUserByLogin(princ.getName());
		
		model.addAttribute("msg", user.toString());
		return "myaccount";
	}

}
