package com.capgemini.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Configuration
@PropertySource("classpath:/com/capgemini/configuration/twitter4j.properties")
public class TwitterConfiguration {
	
	@Value("${oauth.consumerKey}")
	private String consumerKey;

	@Value("${oauth.consumerSecret}")
	private String consumerSecret;
	
	@Bean
	@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
	public Twitter twitter() {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		return twitter;
	}
	
//	@Bean
//	@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.INTERFACES)
//	public RequestToken requestToken() {
//		RequestToken token;
//		try {
//			token = twitter().getOAuthRequestToken();
//			return token;
//		} catch (TwitterException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
