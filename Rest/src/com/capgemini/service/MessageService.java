package com.capgemini.service;

import java.util.List;

import com.capgemini.persistence.domain.Message;

public interface MessageService {
	
	public List<?> getMessageByCampaignId(long campaignId);
	
	public List<?> getMessageByStepId(long stepId);
	
	public Message getMessageById(long messageId);
	
	public boolean addMessage(Message message);
	
}
