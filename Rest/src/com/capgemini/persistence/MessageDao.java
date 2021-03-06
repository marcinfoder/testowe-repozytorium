package com.capgemini.persistence;

import java.util.List;

import com.capgemini.persistence.domain.CampaignStep;
import com.capgemini.persistence.domain.Message;

public interface MessageDao {
	
	public List getListByCampaignId(long campaignId);
	
	public List getListByStepId(long stepId);
	
	public List getListByCampaignIdByStepId(long campaignId, long stepId);
	
	public Message getWith(long messageId);
	
	public boolean add(Message message);
	
	public Message deleteWith(Long messageId);

}
