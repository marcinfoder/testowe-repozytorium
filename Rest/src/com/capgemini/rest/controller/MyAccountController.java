package com.capgemini.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MyAccountController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/myaccount")
	public String emptyPage(Model model) {
		model.addAttribute("msg", "adsasdasdasdasdasdasdads");
		return "myaccount";
	}

}
