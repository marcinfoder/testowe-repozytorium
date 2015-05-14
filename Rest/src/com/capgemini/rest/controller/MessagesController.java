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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

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
		
		try 
		{
			TwitterAccess ta = twitterAccessService.findByLogin(principal.getName());
			twitter.setOAuthAccessToken(new AccessToken(ta.getAccessToken(), ta.getAccessTokenSecret()));

			ArrayList<String> tweetList = new ArrayList<String>();
	        try 
	        {
	        	
	         //   Query query = new Query(user.getName());
	            Query query = new Query("Cotytunato");
	            QueryResult result;
	            do 
	            {
	                result = twitter.search(query);
	                List<Status> tweets2 = result.getTweets();
	                for (Status tweet : tweets2) 
	                {
	                    tweetList.add(tweet.getText());            
	                }
	            } while ((query = result.nextQuery()) != null);
	        } 
	        catch (TwitterException te) 
	        {
	            te.printStackTrace();
	        }
	        
			model.addAttribute("Tweets", tweetList);
			
		} catch (Exception e) 
		{		
			e.printStackTrace();
		}
		
		return "messages";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/messages")
	public String getCreationPage(@ModelAttribute MessageForm messageForm, Model model, Principal principal) {
		
		Message message = new Message();
		User user = userService.getUserByLogin(principal.getName());
		
		message.setCampaignId(messageForm.getCampaignId());
		System.out.println(messageForm.getCampaignId());
		message.setStepId(messageForm.getStepId());
		System.out.println(messageForm.getStepId());
		message.setCreatedAt(new Date());
		System.out.println(new Date());
		message.setGroupId(user.getGroupId());
		message.setUserId(user.getId());

		message.setTwitterContent(messageForm.getText());
		message.setTwitterPublishAt(new Date()); //data teraz
		message.setTwitterPublished(true);
		message.setFacebookContent(messageForm.getText());  //na razie tylko tweeter
		message.setFacebookPublishAt(new Date());
		message.setFacebookPublished(true);
		
		
		try {
//		TwitterAccess ta = twitterAccessService.findByLogin(principal.getName());
//		twitter.setOAuthAccessToken(new AccessToken(ta.getAccessToken(), ta.getAccessTokenSecret()));	
//		twitter.updateStatus(messageForm.getText());
		messageService.addMessage(message);
	    } catch (Exception e) 
		{
		e.printStackTrace();
	    }
		

		model.addAttribute("messageForm", new MessageForm());
		model.addAttribute("success", true);
		//model.addAttribute("campId", campaign.getCampaignId());
		return "messages";
	}
	
//	@RequestMapping(method = RequestMethod.GET, value = "/messages")
//	public String emptyPage(Model model, Principal user) {
//		try 
//		{
//			TwitterAccess ta = twitterAccessService.findByLogin(user.getName());
//			twitter.setOAuthAccessToken(new AccessToken(ta.getAccessToken(), ta.getAccessTokenSecret()));
//
//			ArrayList<String> tweetList = new ArrayList<String>();
//	        try 
//	        {
//	        	
//	         //   Query query = new Query(user.getName());
//	            Query query = new Query("Cotytunato");
//	            QueryResult result;
//	            do 
//	            {
//	                result = twitter.search(query);
//	                List<Status> tweets2 = result.getTweets();
//	                for (Status tweet : tweets2) 
//	                {
//	                    tweetList.add(tweet.getText());            
//	                }
//	            } while ((query = result.nextQuery()) != null);
//	        } 
//	        catch (TwitterException te) 
//	        {
//	            te.printStackTrace();
//	        }
//	        
//			model.addAttribute("Tweets", tweetList);
//			
//		} catch (Exception e) 
//		{		
//			e.printStackTrace();
//		}
//		
//		model.addAttribute("messagesForm", new MessageForm());
//		return "messages";
//	}
	
	



//	@RequestMapping(method = RequestMethod.POST, value = "/send")
//	public String send(@Valid @ModelAttribute("messagesForm") MessageForm message, BindingResult bindingResult, Model model, Principal user) 
//	{
//		if (bindingResult.hasErrors()) {
//			return "messages";
//		}
//
//		String wiadomosc = message.getContent();
//		
//		try {
//			TwitterAccess ta = twitterAccessService.findByLogin(user.getName());
//			twitter.setOAuthAccessToken(new AccessToken(ta.getAccessToken(), ta.getAccessTokenSecret()));	
//			twitter.updateStatus(wiadomosc);
//		} catch (TwitterException e) {
//			e.printStackTrace();
//		}
//
//		//model.addAttribute("tweets", tweets);
//		return "messages"; 
//	}


}