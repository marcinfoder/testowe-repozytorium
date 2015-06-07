package com.capgemini;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.capgemini.persistence.domain.TwitterCount;

public class DateGenerator {

	private boolean days = true;
	private boolean tillNow = true;

	public DateGenerator() {
	}

	public DateGenerator(boolean days, boolean tillNow) {
		this.days = days;
		this.tillNow = tillNow;
	}

	public List<TwitterCount> generateMissingDates(List<TwitterCount> counts) {
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

					cal.setTime(dateFirst);
					if (days == false) {
						cal.set(Calendar.MINUTE, 0);
						twFirst.setDate(cal.getTime());
					}
					result.add(twFirst); // dodajemy docelowe
					dateFirst = incrementDate(cal);

					insertMissingDates(result, dateFirst, dateSecond, cal,
							twFirst);
				} while (second.hasNext());

				if (tillNow) {
					TwitterCount tw = counts.get(counts.size() - 1);
					Date firstDate = tw.getDate();
					cal.setTime(firstDate);
					if (days == false) {
						cal.set(Calendar.MINUTE, 0);
						tw.setDate(cal.getTime());
					}
					result.add(tw);
					firstDate = incrementDate(cal);
					Date newDate = formatNewDate();
					insertMissingDates(result, firstDate, newDate, cal, tw);
				}
			}

		} else if (counts.isEmpty() == false) {
			if (tillNow) {
				TwitterCount tw = counts.get(counts.size() - 1);
				Date firstDate = tw.getDate();
				cal.setTime(firstDate);
				if (days == false) {
					cal.set(Calendar.MINUTE, 0);
					tw.setDate(cal.getTime());
				}
				result.add(tw);
				firstDate = incrementDate(cal);
				Date newDate = formatNewDate();
				insertMissingDates(result, firstDate, newDate, cal, tw);
			}
		}

		return result;
	}

	private Date formatNewDate() {
		Date newDate = new Date();
		if (days) {
			new SimpleDateFormat("dd/MM/yyyy").format(newDate);
		} else {
			new SimpleDateFormat("dd/MM/yyyy HH").format(newDate);
		}
		return newDate;
	}

	private void insertMissingDates(List<TwitterCount> result, Date dateFirst,
			Date dateSecond, Calendar cal, TwitterCount twCount) {

		while (dateFirst.compareTo(dateSecond) < 0) {
			addMissingDate(result, dateFirst, twCount);

			dateFirst = incrementDate(cal);
		}
	}

	private Date incrementDate(Calendar cal) {
		if (days) {
			cal.add(Calendar.DATE, 1);
		} else {
			cal.add(Calendar.HOUR, 1);
		}
		return cal.getTime();
	}

	private void addMissingDate(List<TwitterCount> result, Date dateFirst,
			TwitterCount twCount) {
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
