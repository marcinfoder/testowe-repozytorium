package com.capgemini.rest.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.imageio.spi.RegisterableService;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.persistence.domain.User;
import com.capgemini.rest.model.RegistrationForm;
import com.capgemini.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public String getRegistrationPage(Model model) {
		model.addAttribute("registerForm", new RegistrationForm());
		return "registration";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public String tryRegister(@Valid @ModelAttribute("registerForm") RegistrationForm form, BindingResult bind, Model model){

		if(bind.hasErrors()){
			return "registration";
		}
		
        if(userService.getUserByLogin(form.getUsername()) != null) {
			model.addAttribute("exist", true);
			return "registration";
		}

		User user = new User();
		String hash = createHash(form.getPassword());
		user.setPassword(hash);
		user.setLogin(form.getUsername());
		user.setLastName(form.getLastname());
		user.setFirstName(form.getFirstname());
		user.setEmail(form.getEmail());
		user.setPhoneNumber(form.getPhonenumber());
		user.setActive(true);	
		
		userService.createNewUserAndGroup(user, form.getGroupname(), form.getDescription());
		
		return "redirect:/service/login";
	}

	private String createHash(String pass) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());

			byte[] data = md.digest();
			StringBuilder sb = new StringBuilder();
			for(byte b : data){
        			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
}
