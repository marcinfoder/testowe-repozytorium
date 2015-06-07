package com.capgemini.persistence.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TwitterCount {
	private long countsId;
	private long groupId;
	private long tweets;
	private long retweets;
	private long favorites;
	private Date date;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("countsId:").append(countsId).
		append("groupId:").append(groupId).
		append("tweets:").append(tweets).
		append("retweets:").append(retweets).
		append("favorites:").append(favorites).
		append("date:").append(date);
		return sb.toString();
	}

	public long getCountsId() {
		return countsId;
	}

	public void setCountsId(long countsId) {
		this.countsId = countsId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getTweets() {
		return tweets;
	}

	public void setTweets(long tweets) {
		this.tweets = tweets;
	}

	public long getRetweets() {
		return retweets;
	}

	public void setRetweets(long retweets) {
		this.retweets = retweets;
	}

	public long getFavorites() {
		return favorites;
	}

	public void setFavorites(long favorites) {
		this.favorites = favorites;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
