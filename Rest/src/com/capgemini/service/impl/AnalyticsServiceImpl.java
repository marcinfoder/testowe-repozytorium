package com.capgemini.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.DateGenerator;
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
	@Transactional
	public List<TwitterCount> getTwitterCountsDailyByGroupId(String username) {
		
		User user = userDao.findByName(username);
		List<TwitterCount> counts = twitterCount.getDailyListByGroupId(user.getGroupId());
		DateGenerator dg = new DateGenerator();
		return dg.generateMissingDates(counts);
	}

	@Override
	@Transactional
	public List<TwitterCount> getTwitterCountsDailyByGroupId(String username, Date from, Date to) {
		
		User user = userDao.findByName(username);
		List<TwitterCount> counts = twitterCount.getDailyListByGroupId(user.getGroupId(), from, to);
		DateGenerator dg = new DateGenerator();
		return dg.generateMissingDates(counts);
	}
	
	@Override
	@Transactional
	public List getTwitterCountsHourlyByGroupId(String username) {
		User user = userDao.findByName(username);
		List<TwitterCount> counts = twitterCount.getHourlyListByGroupId(user.getGroupId());
		DateGenerator dg = new DateGenerator(false, false);
		return dg.generateMissingDates(counts);
	}

	@Override
	@Transactional
	public List getTwitterCountsHourlyByGroupId(String username, Date from,
			Date to) {
		User user = userDao.findByName(username);
		List<TwitterCount> counts = twitterCount.getHourlyListByGroupId(user.getGroupId(), from, to);
		DateGenerator dg = new DateGenerator(false, false);
		return dg.generateMissingDates(counts);
	}

}
