package com.capgemini.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.UserDao;
import com.capgemini.persistence.TwitterAccessDao;
import com.capgemini.persistence.domain.TwitterAccess;
import com.capgemini.persistence.domain.User;
import com.capgemini.service.TwitterAccessService;

@Service("taService")
public class TwitterAccessServiceImpl implements TwitterAccessService {

	@Resource(name = "hibernateTwitterAccessDao")
	private TwitterAccessDao twitterDao;

	@Resource(name = "hibernateUserDao")
	private UserDao userDao;

	@Override
	@Transactional
	public TwitterAccess findByLogin(String login) {
		User user = userDao.findByName(login);
		return twitterDao.getWithGroup(user.getGroupId());
	}

	@Override
	@Transactional
	public void addTwitterAccess(String accessToken, String accessTokenSecret) {

		String login = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		User foundUser = userDao.findByName(login);
		
		TwitterAccess ta = new TwitterAccess(); 
		ta.setGroupId(foundUser.getGroupId());
		ta.setAccessToken(accessToken);
		ta.setAccessTokenSecret(accessTokenSecret);
		ta.setActive(true);
		ta.setCreatedAt(new Date());
		
		twitterDao.add(ta);
	}

	@Override
	public void updateTwitterAccess(long twitterAccessId, String accessToken, String accessTokenSecret) {
		TwitterAccess ta = twitterDao.getWith(twitterAccessId);
		ta.setAccessToken(accessToken);
		ta.setAccessTokenSecret(accessTokenSecret);
		ta.setUpdatedAt(new Date());
		
		twitterDao.update(ta);
	}

	@Override
	public void deactivateTwitterAccess(long twitterAccessId) {
		TwitterAccess ta = twitterDao.getWith(twitterAccessId);
		ta.setActive(false);
		
		twitterDao.update(ta);
	}

}
