package com.capgemini.persistence.domain;

import java.util.Date;

public class TwitterMentions {

	private long ID;
	private long groupId;
	private float sentencePolarity;
	private String text;
	private long retweets;
	private long favorites;
	private Date createdAt;
	
	
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public float getSentencePolarity() {
		return sentencePolarity;
	}
	public void setSentencePolarity(float sentencePolarity) {
		this.sentencePolarity = sentencePolarity;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
