package com.capgemini.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.NavigationNames;

@Controller
public class StartController {

	@RequestMapping(method = RequestMethod.GET, value = "/start")
	public String start(Model model) {
		model.addAttribute("page", NavigationNames.HOME);
		return "start";
	}
}
