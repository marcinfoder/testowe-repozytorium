package com.capgemini.rest.controller;

import java.security.Principal;


import java.util.ArrayList;
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







import com.capgemini.persistence.domain.TwitterAccess;
import com.capgemini.rest.model.MessagesForm;
import com.capgemini.service.TwitterAccessService;

@Controller
public class MessagesController 
{
		
	@Autowired
	private Twitter twitter;
	
	@Autowired
	private TwitterAccessService twitterAccessService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/messages")
	public String emptyPage(Model model, Principal user) {
		try 
		{
			TwitterAccess ta = twitterAccessService.findByLogin(user.getName());
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
		
		model.addAttribute("messagesForm", new MessagesForm());
		return "messages";
	}
	
	



	@RequestMapping(method = RequestMethod.POST, value = "/send")
	public String send(@Valid @ModelAttribute("messagesForm") MessagesForm message, BindingResult bindingResult, Model model, Principal user) 
	{
		if (bindingResult.hasErrors()) {
			return "messages";
		}

		String wiadomosc = message.getTweet();
		
		try {
			TwitterAccess ta = twitterAccessService.findByLogin(user.getName());
			twitter.setOAuthAccessToken(new AccessToken(ta.getAccessToken(), ta.getAccessTokenSecret()));	
			twitter.updateStatus(wiadomosc);
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		//model.addAttribute("tweets", tweets);
		return "messages"; 
	}


}