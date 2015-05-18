package com.capgemini.rest.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.NavigationNames;
import com.capgemini.persistence.domain.TwitterCount;
import com.capgemini.service.AnalyticsService;

@Controller
public class AnalyticsController {

	@Autowired
	AnalyticsService analyticsService;
	
	protected void initBinder(WebDataBinder binder) {
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/displays")
	public String getTwitterDisplays(Model model, Principal principal) {
		List<TwitterCount> twitterCounts = (List<TwitterCount>)analyticsService.getTwitterCountsByGroupId(principal.getName());
		model.addAttribute("twitterCountList", twitterCounts);
		model.addAttribute("page", NavigationNames.DISPLAYS);
		return "display";
	}
	
}
