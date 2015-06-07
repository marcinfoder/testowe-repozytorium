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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.DateComparator;
import com.capgemini.NavigationNames;
import com.capgemini.persistence.domain.TwitterCount;
import com.capgemini.service.AnalyticsService;

@Controller
public class AnalyticsController {

	@Autowired
	AnalyticsService analyticsService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/displays")
	public String getTwitterDisplays(Model model, Principal principal) {
		List<TwitterCount> twitterCounts = (List<TwitterCount>)analyticsService.getTwitterCountsByGroupId(principal.getName());
		
		model.addAttribute("pattern", DateComparator.DATE_AND_TIME_FORMAT);
		if(twitterCounts.size() > 20)
		{
			model.addAttribute("twitterCountList", twitterCounts.subList(twitterCounts.size()- 21, twitterCounts.size()-1));
		}
		else
		{
			model.addAttribute("twitterCountList", twitterCounts);
		}
		
		model.addAttribute("page", NavigationNames.DISPLAYS);
		return "display";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/displays/daily")
	public String getTwitterDisplaysDaily(Model model, Principal principal) {
		List<TwitterCount> twitterCounts = (List<TwitterCount>)analyticsService.getTwitterCountsDailyByGroupId(principal.getName());
		model.addAttribute("pattern", DateComparator.DATE_FORMAT);
		
		if(twitterCounts.size() > 20)
		{
			model.addAttribute("twitterCountList", twitterCounts.subList(twitterCounts.size()- 21, twitterCounts.size()-1));
		}
		else
		{
			model.addAttribute("twitterCountList", twitterCounts);
		}
		
		model.addAttribute("page", NavigationNames.DISPLAYS);
		return "display";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/displays/hourly")
	public String getTwitterDisplaysHourly(Model model, Principal principal) {
		List<TwitterCount> twitterCounts = (List<TwitterCount>)analyticsService.getTwitterCountsDailyByGroupId(principal.getName());
		
		model.addAttribute("pattern", DateComparator.DATE_AND_TIME_FORMAT);
		if(twitterCounts.size() > 20)
		{
			model.addAttribute("twitterCountList", twitterCounts.subList(twitterCounts.size()- 21, twitterCounts.size()-1));
		}
		else
		{
			model.addAttribute("twitterCountList", twitterCounts);
		}
		
		model.addAttribute("page", NavigationNames.DISPLAYS);
		return "display";
	}
	
}
