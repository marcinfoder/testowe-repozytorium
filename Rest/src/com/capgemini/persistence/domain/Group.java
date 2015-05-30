package com.capgemini.persistence.domain;

import java.util.Date;

public class Group {

	private long GroupId;
	
	private String Name;
	
	private String Description;
	
	private Date CreatedAt;
	
	private Date UpdatedAt;

	public long getGroupId() {
		return GroupId;
	}

	public void setGroupId(long groupId) {
		GroupId = groupId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}

	public Date getUpdatedAt() {
		return UpdatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}
	
}
