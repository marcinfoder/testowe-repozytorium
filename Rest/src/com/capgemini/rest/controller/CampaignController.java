package com.capgemini.rest.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.persistence.CampaignDao;
import com.capgemini.persistence.domain.Campaign;
import com.capgemini.persistence.domain.User;
import com.capgemini.service.CampaignService;
import com.capgemini.service.UserService;

@Controller
public class CampaignController {

	@Autowired
	CampaignService campService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaigns")
	public String getCampaignsPage(Model model, Principal principal) {
		User user = userService.getUserByLogin(principal.getName());
		List<Campaign> campaignsList = (List<Campaign>) campService.getCampaignsByGroupId(user.getGroupId());
		model.addAttribute("campaignList", campaignsList);
		return "campaign";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-steps/{campId}")
	public String getStepsPage(Model model, Principal principal, @PathVariable long campId) {
		List<Campaign> stepList = (List<Campaign>) campService.getStepsByCampaignId(campId);
		model.addAttribute("stepList", stepList);
		return "campaign-steps";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-add")
	public String getCampaignCreationPage(Model model, Principal principal) {
		
		return "campaign-add";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-step-add")
	public String getStepCreationPage(Model model, Principal principal) {
		
		return "campaign-step-add";
	}
	
	
	
}
