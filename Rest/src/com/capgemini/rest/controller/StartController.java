package com.capgemini.rest.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.NavigationNames;
import com.capgemini.persistence.domain.TwitterAccess;
import com.capgemini.service.TwitterAccessService;

@Controller
public class StartController {

	@Autowired
	private TwitterAccessService taService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/start")
	public String start(Model model, Principal principal, HttpSession session) {
		
		TwitterAccess ta = taService.findByLogin(principal.getName());
		if(ta == null)
		{
			session.setAttribute("connected", false);
			return "redirect:/service/twitter-login";
		}
		
		session.setAttribute("connected", true);
		model.addAttribute("page", NavigationNames.HOME);
		return "start";
	}
}
