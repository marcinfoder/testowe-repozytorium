package com.capgemini.rest.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.rest.model.TwitterLoginForm;
import com.capgemini.service.TwitterAccessService;
import com.capgemini.service.UserService;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@Controller
@PropertySource("classpath:/com/capgemini/configuration/twitter4j.properties")
public class TwitterController {

	@Autowired
	private Twitter twitter;
	
	@Autowired
	private TwitterAccessService twitterAccessService;
	
	@Autowired
	private UserService userService; 

	@Value("${oauth.consumerKey}")
	private String consumerKey;

	@Value("${oauth.consumerSecret}")
	private String consumerSecret;

	@RequestMapping(method = RequestMethod.GET, value = "/twitter-login")
	public String getLoginPage(Model model) {
		model.addAttribute("twitterLoginForm", new TwitterLoginForm());

		// twitter.setOAuthConsumer(consumerKey, consumerSecret);
		RequestToken requestToken;
		try {
			requestToken = twitter.getOAuthRequestToken();
			model.addAttribute("consumer", requestToken.getToken());
			model.addAttribute("secret", requestToken.getTokenSecret());
			model.addAttribute("twitterAuthUrl", requestToken.getAuthorizationURL());
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return "twitter-login";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/twitter-login")
	public String getRegisterAccount(@ModelAttribute TwitterLoginForm tweet,
			Model model, Principal principal) {
		try {
			AccessToken at = twitter.getOAuthAccessToken(tweet.getTwitterPin());
			model.addAttribute("accessToken", at.getToken());
			model.addAttribute("consumer", userService.getUserByLogin(principal.getName()));
			twitterAccessService.addTwitterAccess(at.getToken());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return "twitter-login";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/twitter-login2")
	public String getTwitterAccess(Model model, Principal principal) {
		model.addAttribute("twitterLoginForm", new TwitterLoginForm());
        model.addAttribute("consumer", userService.getUserByLogin(principal.getName()).toString());
		model.addAttribute("secret", twitterAccessService.findByLogin(principal.getName()).toString());
		return "twitter-login";
	}

}
