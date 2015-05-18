package com.capgemini.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.TwitterCountDao;
import com.capgemini.persistence.UserDao;
import com.capgemini.persistence.domain.TwitterCount;
import com.capgemini.persistence.domain.User;
import com.capgemini.service.AnalyticsService;

@Service("AnalyticsService")
public class AnalyticsServiceImpl implements AnalyticsService {

	@Resource(name = "hibernateTwitterCountDao")
	private TwitterCountDao twitterCount;
	
	@Resource(name = "hibernateUserDao")
	private UserDao userDao;
	
	@Override
	@Transactional
	public List getTwitterCountsByGroupId(String userName) {
		User user = userDao.findByName(userName);
		return twitterCount.getListByGroupId(user.getGroupId());
	}

	@Override
	public TwitterCount getTwitterCountById(long id) {
		return twitterCount.getWith(id);
	}

	@Override
	public List getTwitterCountByDateRange(Date start, Date end) {
		return null;
	}

}
