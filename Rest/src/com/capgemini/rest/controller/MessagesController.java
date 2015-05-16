package com.capgemini.rest.controller;

import java.security.Principal;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

import com.capgemini.DateComparator;
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
	public String getMessages(Model model, Principal principal) {
		List<Campaign> campaignList = (List<Campaign>) campService.getCampaignByUserLogin(principal.getName());
		List<CampaignStep> campaignStepList = (List<CampaignStep>) campService.getStepsByCampaignId(campaignList.get(0).getCampaignId());
		
		model.addAttribute("campaignList", campaignList); 
		model.addAttribute("campaignStepList", campaignStepList); 
		model.addAttribute("MessageForm", new MessageForm());
		model.addAttribute("currDate", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
		
		List<Message> messageList = (List<Message>) messageService.getMessageByCampaignId(8);
	    Collections.sort(messageList, new DateComparator());
	    
		model.addAttribute("Tweets", messageList);
		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "messages";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/messages/{campId}")
	public String getMessagesByCampId(@PathVariable long campId, Model model, Principal principal) {
		List<Campaign> campaignList = (List<Campaign>) campService.getCampaignByUserLogin(principal.getName());
			
		List<CampaignStep> campaignStepList = (List<CampaignStep>) campService.getStepsByCampaignId(campId);
		List<Message> messageList = (List<Message>) messageService.getMessageByCampaignId(campId);
	    
		model.addAttribute("campaignList", campaignList); 
		model.addAttribute("campaignStepList", campaignStepList); 
		model.addAttribute("MessageForm", new MessageForm());
		model.addAttribute("campId", campId);
			 
		model.addAttribute("Tweets", messageList);
					
		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "messages";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/campaign-step-messages/{campId}/{stepId}")
	public String getMessagesByStep(@PathVariable long campId, @PathVariable long stepId,  Model model, Principal principal) {
		
		List<Message> messageList = (List<Message>) messageService.getMessageByCampaignIdByStepId(campId, stepId);
		Collections.sort(messageList, new DateComparator());  
		
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
				Status status = twitter.updateStatus(messageForm.getText());
				
				message.setTwitterPublishAt(new Date());
				message.setTwitterPublished(true);
				message.setFacebookPublishAt(new Date());
				message.setFacebookPublished(true);
				message.setTweetId(status.getId());
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
		model.addAttribute("page", NavigationNames.CAMPAIGN_MESSAGES);
		return "messages";
	}
	
}
