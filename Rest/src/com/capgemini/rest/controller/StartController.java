package com.capgemini.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartController {

	@RequestMapping(method = RequestMethod.GET, value = "/start")
	public String start() {
		return "start";
	}

}
