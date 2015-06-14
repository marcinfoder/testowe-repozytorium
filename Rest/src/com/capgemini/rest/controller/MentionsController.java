package com.capgemini.rest.controller;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.NavigationNames;
import com.capgemini.PositivesNeutralNegatives;
import com.capgemini.persistence.domain.TwitterMentions;
import com.capgemini.service.MentionsService;

@Controller
public class MentionsController {
	
	@Autowired
	MentionsService mentionService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/mentions")
	public String getMentionsPage(Model model, Principal principal) {
	 
		List<TwitterMentions> twMentions = mentionService.getMentionsByLogin(principal.getName());	
		
		Map<String, PositivesNeutralNegatives> months = new TreeMap<String, PositivesNeutralNegatives>();
		List<TwitterMentions> twPositive = new ArrayList<TwitterMentions>();
		List<TwitterMentions> twNegative = new ArrayList<TwitterMentions>();
		Calendar cal = Calendar.getInstance();
		
		for(TwitterMentions tw : twMentions)
		{
			cal.setTime(tw.getCreatedAt());
			SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
			
			PositivesNeutralNegatives value = months.getOrDefault(sdf.format(cal.getTime()), new PositivesNeutralNegatives());
			if(tw.getSentencePolarity() >= 1)
			{
				twPositive.add(tw);
				value.positive++;
			}
			else if(tw.getSentencePolarity() <= -1)
			{
				twNegative.add(tw);
				value.negative++;
			}
			months.put(sdf.format(cal.getTime()), value);
		}
		model.addAttribute("totalPositive", twPositive.size());
		model.addAttribute("totalNegative", twNegative.size());
		model.addAttribute("months", months);
		model.addAttribute("mentionsPositive",twPositive);
		model.addAttribute("mentionsNegative",twNegative);
		
		model.addAttribute("page", NavigationNames.OPINIONS);
		return "mentions";
	}
	
}
