package com.capgemini.rest.controller;

import java.security.Principal;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

import com.capgemini.NavigationNames;
import com.capgemini.persistence.domain.Campaign;
import com.capgemini.persistence.domain.CampaignStep;
import com.capgemini.persistence.domain.Message;
import com.capgemini.persistence.domain.TwitterAccess;
import com.capgemini.persistence.domain.User;
import com.capgemini.rest.model.CampaignStepForm;
import com.capgemini.rest.model.MessageForm;
import com.capgemini.service.CampaignService;
import com.capgemini.service.MessageService;
import com.capgemini.service.TwitterAccessService;
import com.capgemini.service.UserService;

@Controller
public class MessagesController 
{
		
	@Autowired
	private Twitter twitter;
	
	@Autowired
	private TwitterAccessService twitterAccessService;
	
	@Autowired
	CampaignService campService;

	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/messages")
	public String getMessagesPageWithComboBox(Model model, Principal principal) {
		List<Campaign> campaignList = (List<Campaign>) campService.getCampaignByUserLogin(principal.getName());
		List<CampaignStep> campaignStepList = (List<CampaignStep>) campService.getStepsByCampaignId(campaignList.get(0).getCampaignId());
		model.addAttribute("comboBox1", true);
		model.addAttribute("comboBox2", true);
		model.addAttribute("campaignList", campaignList); 
		model.addAttribute("campaignStepList", campaignStepList); 
		model.addAttribute("MessageForm", new MessageForm());
		
		List<Message> messageList = (List<Message>) messageService.getMessageByCampaignId(8);
	       
		model.addAttribute("Tweets", messageList);
		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "messages";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/messages/{campId}")
	public String getMessagesPageWithComboBoxByCampId(@PathVariable long campId, Model model, Principal principal) {
		List<Campaign> campaignList = (List<Campaign>) campService.getCampaignByUserLogin(principal.getName());
		
		
		int index = 0;
		for(Campaign camp : campaignList)
		{
			if(camp.getCampaignId() == campId)
			{
				break; 
			}
			index++;
		}
		
		List<CampaignStep> campaignStepList = (List<CampaignStep>) campService.getStepsByCampaignId(campaignList.get(index).getCampaignId());
		List<Message> messageList = (List<Message>) messageService.getMessageByCampaignId(campaignList.get(index).getCampaignId());
	    String campName = campService.getCampaignById(campId).getName(); 
	    System.out.println(campName);
		model.addAttribute("comboBox1", true);
		model.addAttribute("comboBox2", true);
		model.addAttribute("campaignList", campaignList); 
		model.addAttribute("campaignStepList", campaignStepList); 
		model.addAttribute("MessageForm", new MessageForm());
		model.addAttribute("campaign", campName);

			 
		model.addAttribute("Tweets", messageList);
					
		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "messages";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-step-messages/{campId}/{stepId}")
	public String getMessagesByStep(@PathVariable long campId, @PathVariable long stepId,  Model model, Principal principal) {
		
		List<Message> messageList = (List<Message>) messageService.getMessageByCampaignIdByStepId(campId, stepId);
		   
		model.addAttribute("messageList", messageList);
		model.addAttribute("campId", campId);	
		model.addAttribute("page", "campaign-messages");
		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "campaign-step-messages";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/messages")
	public String sendMessage(@ModelAttribute MessageForm messageForm, @RequestParam String button, Model model, Principal principal) {
		
		Message message = new Message();
		User user = userService.getUserByLogin(principal.getName());
		    
		try 
		{
			
		    if(button.equals("Dodaj"))
		    {
				message.setTwitterPublishAt(messageForm.getPublishDate());
				message.setTwitterPublished(false);
				message.setFacebookPublishAt(messageForm.getPublishDate());
				message.setFacebookPublished(false);
		    }
		    else if(button.equals("Wyslij"))
		    {
		    	TwitterAccess ta = twitterAccessService.findByLogin(principal.getName());
				twitter.setOAuthAccessToken(new AccessToken(ta.getAccessToken(), ta.getAccessTokenSecret()));	
				twitter.updateStatus(messageForm.getText());
				
				message.setTwitterPublishAt(new Date());
				message.setTwitterPublished(true);
				message.setFacebookPublishAt(new Date());
				message.setFacebookPublished(true);
		    }
		    else
		    {  	
		    }	
						
		message.setCampaignId(messageForm.getCampaignId());
		message.setStepId(messageForm.getStepId());		
		message.setGroupId(user.getGroupId());
		message.setUserId(user.getId());
		message.setCreatedAt(new Date());
		
		message.setTwitterContent(messageForm.getText());			
		message.setFacebookContent(messageForm.getText());  //na razie tylko twitter	
		
		messageService.addMessage(message);
	    } 
		catch (Exception e) 
		{
		e.printStackTrace();
	    }
		
		model.addAttribute("messageForm", new MessageForm());
		model.addAttribute("success", true);
		//model.addAttribute("campId", campaign.getCampaignId());
		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "messages";
	}
	
	


}