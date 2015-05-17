package com.capgemini.rest.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.auth.AccessToken;

import com.capgemini.NavigationNames;
import com.capgemini.persistence.CampaignDao;
import com.capgemini.persistence.domain.Campaign;
import com.capgemini.persistence.domain.CampaignStep;
import com.capgemini.persistence.domain.TwitterAccess;
import com.capgemini.persistence.domain.User;
import com.capgemini.rest.model.CampaignForm;
import com.capgemini.rest.model.CampaignStepForm;
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
		List<Campaign> campaignsList = (List<Campaign>) campService
				.getCampaignsByGroupId(user.getGroupId());
		
		model.addAttribute("campaignList", campaignsList);
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_PREVIEW);
		return "campaign";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/campaign-steps/{campId}")
	public String getStepsPage(Model model, Principal principal,
			@PathVariable long campId) {
		List<Campaign> stepList = (List<Campaign>) campService
				.getStepsByCampaignId(campId);
		model.addAttribute("stepList", stepList);
		model.addAttribute("campId", campId);

		model.addAttribute("page", NavigationNames.CAMPAIGN_PREVIEW);
		return "campaign-steps";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/campaign-step-delete")
	public String removeStep(Model model, @RequestParam("stepId") int stepId,
			@RequestParam("campaignId") int campaignId) {
		campService.deleteStepById(stepId);
		return "redirect:/service/campaign-steps/" + campaignId;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/campaign-delete")
	public String removeStep(Model model,
			@RequestParam("campaignId") int campaignId) {
		campService.deleteCampaignById(campaignId);
		return "redirect:/service/campaigns";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/campaign-add")
	public String getCampaignCreationPage(Model model, Principal principal) {
		model.addAttribute("campaignForm", new CampaignForm());
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_ADD);
		return "campaign-add";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/campaign-add")
	public String getCampaignCreationPage(
			@ModelAttribute CampaignForm campaignForm, Model model,
			Principal principal) {
		User user = userService.getUserByLogin(principal.getName());
		
		Campaign campaign = new Campaign();
		campaign.setCreatedAt(new Date());
		campaign.setGroupId(user.getGroupId());
		campaign.setName(campaignForm.getName());
		campaign.setDescription(campaignForm.getDescription());
		campaign.setStartDate(campaignForm.getStartDate());
		campaign.setEndDate(campaignForm.getEndDate());
		campaign.setFacebookConnection(campaignForm.isFacebookConnection());
		campaign.setTwitterConnection(campaignForm.isTwitterConnection());
		campaign.setHashTag(campaignForm.getHashTag());
		
		campService.addCampaign(campaign);

		model.addAttribute("campaignForm", new CampaignForm());
		model.addAttribute("submited", true);
		model.addAttribute("campId", campaign.getCampaignId());
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_ADD);
		return "campaign-add";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-edit")
	public String getCampaignEditionPage(Model model, Principal principal) {
		model.addAttribute("campaignForm", new CampaignForm());
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_ADD);
		return "campaign-edit";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-edit/{campId}")
	public String getCampaignEditionPageId(@PathVariable long campId, Model model, Principal principal) {
		
		Campaign camp = campService.getCampaignById(campId);	
		CampaignForm cForm = new CampaignForm();
		
		cForm.setFacebookConnection(camp.isFacebookConnection());
		cForm.setTwitterConnection(camp.isTwitterConnection());
		cForm.setName(camp.getName());
		cForm.setDescription(camp.getDescription());
		cForm.setStartDate(camp.getStartDate());
		cForm.setEndDate(camp.getEndDate());
		
		model.addAttribute("campaignForm", cForm);
		model.addAttribute("campId", camp.getCampaignId());
			
		model.addAttribute("page", NavigationNames.CAMPAIGN_ADD);
		return "campaign-edit";
	}	

	@RequestMapping(method = RequestMethod.POST, value = "/campaign-edit")
	public String getCampaignEditionPage(@ModelAttribute CampaignForm campaignForm, @RequestParam String button, @RequestParam long campId,  Model model, Principal principal) {
		
		Campaign campaign = campService.getCampaignById(campId);
		
		campaign.setName(campaignForm.getName());
		campaign.setDescription(campaignForm.getDescription());
		campaign.setStartDate(campaignForm.getStartDate());
		campaign.setEndDate(campaignForm.getEndDate());
		campaign.setFacebookConnection(campaignForm.isFacebookConnection());
		campaign.setTwitterConnection(campaignForm.isTwitterConnection());
		campaign.setHashTag(campaignForm.getHashTag());
		
	    if(button.equals("Aktualizuj"))
	    {
			try
			{
			campService.campaignUpdate(campaign);
			}
			catch (Exception e) 
			{
			e.printStackTrace();
		    }
	    }
	    else if(button.equals("Anuluj"))
	    {
	    	model.addAttribute("page", NavigationNames.CAMPAIGN_PREVIEW);
	    	return "campaigns";
	    }
	    else
	    {  	
	    }
		
		model.addAttribute("submited", true);
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_PREVIEW);
		return "campaign-edit";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-step-edit")
	public String getCampaignStepEditionPage(Model model, Principal principal) {
		model.addAttribute("campaignStepForm", new CampaignStepForm());
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		return "campaign-step-edit";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-step-edit/{stepId}")
	public String getCampaignStepEditionPageId( @PathVariable long stepId, Model model, Principal principal) {
		
		CampaignStep campStep = campService.getStepById(stepId);
		CampaignStepForm cStepForm = new CampaignStepForm();
		
		cStepForm.setName(campStep.getName());
		cStepForm.setDescription(campStep.getDescription());
		cStepForm.setStartDate(campStep.getStartDate());
		cStepForm.setEndDate(campStep.getEndDate());
		
		model.addAttribute("campaignStepForm", cStepForm);
		model.addAttribute("stepId", campStep.getStepId());
		model.addAttribute("campId", campStep.getCampaignId());
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		return "campaign-step-edit";
	}	

	@RequestMapping(method = RequestMethod.POST, value = "/campaign-step-edit")
	public String getCampaignStepEditionPage(@ModelAttribute CampaignStepForm campaignStepForm, @RequestParam String button, @RequestParam long stepId, Model model, Principal principal) {
		
		CampaignStep campaignStep = campService.getStepById(stepId);
		
		campaignStep.setName(campaignStepForm.getName());
		campaignStep.setDescription(campaignStepForm.getDescription());
		campaignStep.setStartDate(campaignStepForm.getStartDate());
		campaignStep.setEndDate(campaignStepForm.getEndDate());
		campaignStep.setHashTag(campaignStepForm.getHashTag());
	
	    if(button.equals("Aktualizuj"))
	    {
			try
			{
			campService.stepUpdate(campaignStep);
			}
			catch (Exception e) 
			{
			e.printStackTrace();
		    }
	    }
	    else if(button.equals("Anuluj"))
	    {
	    	model.addAttribute("page", NavigationNames.CAMPAIGN_PREVIEW);
	    	return "campaign-steps";
	    }
	    else
	    {  	
	    }
		
		model.addAttribute("submited", true);
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		return "campaign-step-edit";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/campaign-step-add")
	public String getStepCreationPageWithComboBox(Model model, Principal principal) {
		List<Campaign> campaignList = (List<Campaign>) campService.getCampaignByUserLogin(principal.getName());
		model.addAttribute("comboBox", true);
		model.addAttribute("campaignList", campaignList); 
		model.addAttribute("campaignStepForm", new CampaignStepForm());
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		return "campaign-step-add";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/campaign-step-add/{campId}")
	public String getStepCreationPage(Model model, Principal principal,
			@PathVariable int campId) {
		model.addAttribute("campaignStepForm", new CampaignStepForm());
		model.addAttribute("campId", campId);
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		return "campaign-step-add";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/campaign-step-add")
	public String getCreationPage(
			@ModelAttribute CampaignStepForm campaignForm, Model model,
			Principal principal) {
		CampaignStep campaign = new CampaignStep();
		campaign.setCampaignId(campaignForm.getCampaignId());
		campaign.setName(campaignForm.getName());
		campaign.setDescription(campaignForm.getDescription());
		campaign.setStartDate(campaignForm.getStartDate());
		campaign.setEndDate(campaignForm.getEndDate());
		campaign.setHashTag(campaignForm.getHashTag());
		campService.addStep(campaign);

		model.addAttribute("campaignStepForm", new CampaignStepForm());
		model.addAttribute("submited", true);
		model.addAttribute("campId", campaign.getCampaignId());
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		return "campaign-step-add";
	}

}
