package com.capgemini.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.CampaignDao;
import com.capgemini.persistence.CampaignStepDao;
import com.capgemini.persistence.MessageDao;
import com.capgemini.persistence.domain.Message;
import com.capgemini.service.MessageService;

@Service("Message")
public class MessageServiceImpl implements MessageService{
	
	@Resource(name = "hibernateCampaignDao")
	private CampaignDao campaign;
	
	@Resource(name = "hibernateCampaignStepDao")
	private CampaignStepDao campaignStep;
	
	@Resource(name = "hibernateMessageDao")
	private MessageDao message;

	@Override
	@Transactional
	public List<?> getMessageByCampaignId(long campaignId) {
		List<?> messages = message.getListByCampaignId(campaignId);
		return messages;
	}
	
	@Override
	@Transactional
	public List<?> getMessageByStepId(long stepId) {
		List<?> messages = message.getListByStepId(stepId);
		return messages;
	}
	
	@Override
	@Transactional
	public Message getMessageById(long messageId) {
		return message.getWith(messageId);
	}

	@Override
	@Transactional
	public boolean addMessage(Message message) {
		this.message.add(message);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteMessageById(long messageId) {
		
		Message ms = message.deleteWith(messageId);
		
		if(ms == null)
		{
			return false;
		}
		else 
		return true;
		
		
	}

}
