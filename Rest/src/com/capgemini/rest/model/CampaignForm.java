package com.capgemini.rest.model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class CampaignForm {
	private boolean twitterConnection;
	
	private boolean facebookConnection;
	
	@NotEmpty(message = "{camp.err.name}")
	private String name;
	
	@Size(max = 500, message = "{camp.err.desc}")
	private String description;
	
	@NotNull(message = "{camp.err.startDate}")
//	@Future(message = "{camp.err.startDateFuture}")
	private Date startDate;
	
	@NotNull(message = "{camp.err.endDate}")
//	@Future(message = "{camp.err.endDateFuture}")
	private Date endDate;
	
	private String hashTag;
	
	public boolean isTwitterConnection() {
		return twitterConnection;
	}
	public void setTwitterConnection(boolean twitterConnection) {
		this.twitterConnection = twitterConnection;
	}
	public boolean isFacebookConnection() {
		return facebookConnection;
	}
	public void setFacebookConnection(boolean facebookConnection) {
		this.facebookConnection = facebookConnection;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getHashTag() {
		return hashTag;
	}
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag.replaceAll("\\s+", "");
	}
	
}
