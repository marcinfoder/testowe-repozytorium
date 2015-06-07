package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.TwitterMentionsDao;
import com.capgemini.persistence.UserDao;
import com.capgemini.persistence.domain.TwitterMentions;
import com.capgemini.persistence.domain.User;
import com.capgemini.service.MentionsService;

@Service("MentionsService")
public class MentionsServiceImpl implements MentionsService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TwitterMentionsDao twMentionsDao;
	
	@Override
	@Transactional
	public List<TwitterMentions> getMentionsByLogin(String login) {
		User user = userDao.findByName(login);
		return twMentionsDao.getMentionsByGroupId(user.getGroupId());
	}

}
