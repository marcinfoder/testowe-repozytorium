package com.capgemini.rest.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.auth.AccessToken;

import com.capgemini.DateComparator;
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
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaigns")
	public String getCampaignsPage(Model model, Principal principal) {
		User user = userService.getUserByLogin(principal.getName());
		List<Campaign> campaignsList = (List<Campaign>) campService
				.getCampaignsByGroupId(user.getGroupId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("campaignList", campaignsList);
		model.addAttribute("currentDate", format.format(new Date()));
		
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
		model.addAttribute("campName", campService.getCampaignById(campId).getName());
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		model.addAttribute("campaignForm", new CampaignForm());
		model.addAttribute("minDate", format.format(new Date()));
		model.addAttribute("page", NavigationNames.CAMPAIGN_ADD);
		model.addAttribute("submited", false);
		model.addAttribute("added", true);
		
		return "campaign-add";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/campaign-add")
	public String getCampaignCreationPage(
			@Valid @ModelAttribute("campaignForm") CampaignForm campaignForm, BindingResult bindingResult, Model model,
			Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "campaign-add";
		}
		
		if(campaignForm.getStartDate().after(campaignForm.getEndDate()))
		{
			model.addAttribute("submited", false);
			model.addAttribute("added", false);
		}
		else
		{
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
			model.addAttribute("added", true);
			model.addAttribute("campId", campaign.getCampaignId());
		}
			
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
		List<CampaignStep> stepsList = (List<CampaignStep>) campService.getStepsByCampaignId(campId); 
		Boolean disableStartDate = false;
		Date minDateEnd, maxDateStart, date = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		minDateEnd = date;
		maxDateStart = camp.getEndDate();
		System.out.println(minDateEnd);
		if(!stepsList.isEmpty())
		{
			System.out.println(minDateEnd + "W srodku");
			for(CampaignStep cs : stepsList)
			{
				if(maxDateStart.after(cs.getStartDate()))
				{
					maxDateStart = cs.getStartDate();
				}
				
				if(minDateEnd.before(cs.getEndDate()))
				{
					minDateEnd = cs.getEndDate();
				}
			}
		}
		System.out.println(maxDateStart + "na staert");
		
		
		if(date.after(maxDateStart))
		{
			disableStartDate = true;
		}
			
		cForm.setFacebookConnection(camp.isFacebookConnection());
		cForm.setTwitterConnection(camp.isTwitterConnection());
		cForm.setName(camp.getName());
		cForm.setDescription(camp.getDescription());
		cForm.setStartDate(camp.getStartDate());
		cForm.setEndDate(camp.getEndDate());
		cForm.setHashTag(camp.getHashTag());
		
		model.addAttribute("campaignForm", cForm);
		model.addAttribute("campId", camp.getCampaignId());
		model.addAttribute("currentDate", format.format(date));
		model.addAttribute("minDateEnd", format.format(minDateEnd));
		model.addAttribute("maxDateStart", format.format(maxDateStart));
		model.addAttribute("disableStartDate", disableStartDate);
		model.addAttribute("updated", true);
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_ADD);
		return "campaign-edit";
	}	

	@RequestMapping(method = RequestMethod.POST, value = "/campaign-edit", params = {"cancel"})
	public String cancelUpdateCampaign(@ModelAttribute CampaignForm campaignForm, @RequestParam String cancel, @RequestParam long campId,  Model model, Principal principal) {
		
	    	model.addAttribute("page", NavigationNames.CAMPAIGN_PREVIEW);
	    	return "redirect:/service/campaigns";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/campaign-edit", params = {"update"})
	public String updateCampaign(@ModelAttribute CampaignForm campaignForm, @RequestParam String update, @RequestParam long campId,  Model model, Principal principal) {
		
		Campaign campaign = campService.getCampaignById(campId);
		
		if(campaignForm.getStartDate() == null)
		{
			campaignForm.setStartDate(campaign.getStartDate());
		}
			
		if(new Date().after(campaign.getEndDate()))
		{
			model.addAttribute("updated", false);
			model.addAttribute("submited", true);
			model.addAttribute("campId", campaign.getCampaignId());
		}
		else if(campaignForm.getStartDate().after(campaignForm.getEndDate()))
		{
			model.addAttribute("updated", false);
			model.addAttribute("submited", false);
			model.addAttribute("campaignForm", campaignForm);
			model.addAttribute("campId", campaign.getCampaignId());
		}
		else
		{			
			model.addAttribute("updated", true);
			model.addAttribute("submited", true);
			campaign.setDescription(campaignForm.getDescription());
			campaign.setName(campaignForm.getName());
			campaign.setStartDate(campaignForm.getStartDate());
			campaign.setEndDate(campaignForm.getEndDate());
			campaign.setFacebookConnection(campaignForm.isFacebookConnection());
			campaign.setTwitterConnection(campaignForm.isTwitterConnection());
			campaign.setHashTag(campaignForm.getHashTag());
			
			try
			{
				campService.campaignUpdate(campaign);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
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
		
		Campaign camp = campService.getCampaignById(campStep.getCampaignId());
		
		Date date = new Date();
		if(date.after(camp.getStartDate()))
		{
			model.addAttribute("minDate", new SimpleDateFormat("yyyy-MM-dd").format(date));
		}
		else
		{
			model.addAttribute("minDate", camp.getStartDate());
		}
		
		cStepForm.setName(campStep.getName());
		cStepForm.setDescription(campStep.getDescription());
		cStepForm.setStartDate(campStep.getStartDate());
		cStepForm.setEndDate(campStep.getEndDate());
		
		model.addAttribute("campaignStepForm", cStepForm);
		model.addAttribute("stepId", campStep.getStepId());
		model.addAttribute("campId", campStep.getCampaignId());	
		model.addAttribute("maxDate", camp.getEndDate());
		model.addAttribute("campId", campStep.getCampaignId());
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		model.addAttribute("updated", true);
		model.addAttribute("submited", false);
		
		return "campaign-step-edit";
	}	
	@RequestMapping(method = RequestMethod.POST, value = "/campaign-step-edit", params = { "cancel" })
	public String cancelUpdateCampaignStep(@Valid @ModelAttribute CampaignStepForm campaignStepForm, @RequestParam String cancel, @RequestParam long stepId, Model model, Principal principal) {
		
	    	model.addAttribute("page", NavigationNames.CAMPAIGN_PREVIEW);
	    	return "redirect:/service/campaigns";
	}
	@RequestMapping(method = RequestMethod.POST, value = "/campaign-step-edit", params = { "update" })
	public String updateCampaignStep(@Valid @ModelAttribute CampaignStepForm campaignStepForm, @RequestParam String update, @RequestParam long stepId, Model model, Principal principal) {
		
		CampaignStep campaignStep = campService.getStepById(stepId);
			
		model.addAttribute("submited", true);
				
		if(new Date().after(campaignStep.getEndDate()))
		{
			model.addAttribute("updated", false);
			model.addAttribute("submited", true);
		}
		else if(campaignStepForm.getStartDate().after(campaignStepForm.getEndDate()))
		{
			model.addAttribute("updated", false);
			model.addAttribute("submited", false);
			model.addAttribute("campaignStepForm", campaignStepForm);
		}
		else
		{			
			campaignStep.setName(campaignStepForm.getName());
			campaignStep.setDescription(campaignStepForm.getDescription());
			campaignStep.setStartDate(campaignStepForm.getStartDate());
			campaignStep.setEndDate(campaignStepForm.getEndDate());
			campaignStep.setHashTag(campaignStepForm.getHashTag());
		
				try
				{
				campService.stepUpdate(campaignStep);
				}
				catch (Exception e) 
				{
				e.printStackTrace();
			    }
				model.addAttribute("updated", true);
				model.addAttribute("submited", true);
		}
		
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		model.addAttribute("campId", campaignStep.getCampaignId());
		return "campaign-step-edit";
	}
///////////////////
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-step-add")
	public String getStepCreationPageWithComboBox(Model model, Principal principal) {
		List<Campaign> campaignList = (List<Campaign>) campService.getCampaignByUserLogin(principal.getName());
		model.addAttribute("comboBox", true);
		model.addAttribute("campaignList", campaignList); 
		model.addAttribute("campaignStepForm", new CampaignStepForm());
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		model.addAttribute("submited", false);
		model.addAttribute("added", true);
		return "campaign-step-add";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/campaign-step-add/{campId}")
	public String getStepCreationPage(Model model, Principal principal, @PathVariable int campId) {
		
		Campaign camp = campService.getCampaignById(campId);
		
		Date date = new Date();
		if(date.after(camp.getStartDate()))
		{
			model.addAttribute("minDate", new SimpleDateFormat("yyyy-MM-dd").format(date));
		}
		else
		{
			model.addAttribute("minDate", camp.getStartDate());
		}
		
		model.addAttribute("campaignStepForm", new CampaignStepForm());
		model.addAttribute("campId", campId);
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		model.addAttribute("maxDate", camp.getEndDate());
		model.addAttribute("submited", false);
		model.addAttribute("added", true);
		
		return "campaign-step-add";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/campaign-step-add")
	public String getCreationPage(
			@Valid @ModelAttribute CampaignStepForm campaignForm, BindingResult result, Model model,
Principal principal) {
		
		if(result.hasErrors()) {
			return "campaign-step-add";
		}
		
		
		if(new Date().after(campaignForm.getEndDate()))
		{
			model.addAttribute("added", false);
			model.addAttribute("submited", true);
		}
		else if(campaignForm.getStartDate().after(campaignForm.getEndDate()))
		{
			model.addAttribute("added", false);
			model.addAttribute("submited", false);
			model.addAttribute("campaignForm", campaignForm);
		}
		else
		{			
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
			model.addAttribute("added", true);
			model.addAttribute("campId", campaign.getCampaignId());
		}
			
		model.addAttribute("page", NavigationNames.CAMPAIGN_STEP_ADD);
		return "campaign-step-add";
	}

}
