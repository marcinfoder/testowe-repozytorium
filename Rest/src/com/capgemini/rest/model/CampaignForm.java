package com.capgemini.rest.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class CampaignForm {
	@NotEmpty
	private boolean twitterConnection;
	
	@NotEmpty
	private boolean facebookConnection;
	
	@NotEmpty
	private String name;
	
	@Size(max = 500)
	private String description;
	
	@NotEmpty
	private Date startDate;
	
	@NotEmpty
	private Date endDate;
	
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
	
}
