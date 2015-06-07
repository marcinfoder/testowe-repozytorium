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
		return generateMissingDates(counts);
	}

	@Override
	@Transactional
	public List<TwitterCount> getTwitterCountsDailyByGroupId(String username, Date from, Date to) {
		
		User user = userDao.findByName(username);
		List<TwitterCount> counts = twitterCount.getDailyListByGroupId(user.getGroupId(), from, to);
		return generateMissingDates(counts);
	}
	
	@Override
	@Transactional
	public List getTwitterCountsHourlyByGroupId(String username) {
		User user = userDao.findByName(username);
		return twitterCount.getHourlyListByGroupId(user.getGroupId());
	}

	@Override
	@Transactional
	public List getTwitterCountsHourlyByGroupId(String username, Date from,
			Date to) {
		User user = userDao.findByName(username);
		return twitterCount.getHourlyListByGroupId(user.getGroupId(), from, to);
	}

	private List<TwitterCount> generateMissingDates(List<TwitterCount> counts) {
		List<TwitterCount> result = new ArrayList<TwitterCount>();
		Calendar cal = Calendar.getInstance();

		if (counts.size() > 1) {
			Iterator<TwitterCount> first = counts.iterator();
			Iterator<TwitterCount> second = counts.iterator();
			if (second.hasNext()) {
				second.next();

				do {
					TwitterCount twFirst = first.next();
					TwitterCount twSecond = second.next();

					Date dateFirst = twFirst.getDate();
					Date dateSecond = twSecond.getDate();
					result.add(twFirst); //dodajemy docelowe

					cal.setTime(dateFirst);
					dateFirst = incrementDate(cal);

					insertMissingDates(result, dateFirst, dateSecond, cal, twFirst);
				} while (second.hasNext());
				
				TwitterCount tw = counts.get(counts.size()-1);
				result.add(tw);
				Date firstDate = tw.getDate();
				cal.setTime(firstDate);
				firstDate = incrementDate(cal);
				Date newDate = new Date();
				new SimpleDateFormat("dd/MM/yyyy").format(newDate);
				insertMissingDates(result, firstDate, newDate, cal, tw);
			}

		}
		else if(counts.isEmpty() == false)
		{
			TwitterCount tw = counts.get(counts.size()-1);
			result.add(tw);
			Date firstDate = tw.getDate();
			cal.setTime(firstDate);
			firstDate = incrementDate(cal);
			Date newDate = new Date();
			new SimpleDateFormat("dd/MM/yyyy").format(newDate);
			insertMissingDates(result, firstDate, newDate, cal, tw);
		}

		return result;
	}

	private void insertMissingDates(List<TwitterCount> result, Date dateFirst, Date dateSecond, Calendar cal, TwitterCount twCount) {
		
		while (dateFirst.compareTo(dateSecond) < 0) {
			addMissingDate(result, dateFirst, twCount);

			dateFirst = incrementDate(cal);
		}
	}

	private Date incrementDate(Calendar cal) {
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	private void addMissingDate(List<TwitterCount> result, Date dateFirst, TwitterCount twCount) {
		TwitterCount tw = new TwitterCount();
		tw.setCountsId(twCount.getCountsId());
		tw.setFavorites(twCount.getFavorites());
		tw.setGroupId(twCount.getGroupId());
		tw.setRetweets(twCount.getRetweets());
		tw.setTweets(twCount.getTweets());
		tw.setDate(dateFirst);

		result.add(tw);
	}
}
