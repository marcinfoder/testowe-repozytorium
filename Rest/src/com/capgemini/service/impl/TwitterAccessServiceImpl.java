package com.capgemini.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.IUserDao;
import com.capgemini.persistence.TwitterAccessDao;
import com.capgemini.persistence.domain.TwitterAccess;
import com.capgemini.service.TwitterAccessService;

@Service("twitterAccessService")
public class TwitterAccessServiceImpl implements TwitterAccessService {

	@Resource(name = "hibernateTwitterAccessDao")
	private TwitterAccessDao twitterDao;
	
	@Resource(name = "hibernateUserDao")
	private IUserDao userDao;
	
	@Override
	@Transactional
	public TwitterAccess findByLogin(String login) {
		com.capgemini.persistence.domain.User user = userDao.findByName(login);
		return twitterDao.getWithLogin(user.getId());
	}

	@Override
	@Transactional
	public void addTwitterAccess(String accessToken) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		com.capgemini.persistence.domain.User foundUser = userDao.findByName(login);
		
		TwitterAccess twitterAccess = new TwitterAccess();
		twitterAccess.setId(foundUser.getId());
		twitterAccess.setAccessToken(accessToken);
		twitterAccess.setActive(true);
		
		twitterDao.add(twitterAccess);
	}
	
}
