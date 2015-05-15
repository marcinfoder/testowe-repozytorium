package com.capgemini.persistence;

import java.util.List;

import com.capgemini.persistence.domain.Gallery;

public interface GalleryDao {
	public boolean add(Gallery gallery);
	
	public void update(Gallery gallery);
	
	public Gallery deleteWith(long id);
	
	public Gallery getWith(long id);
	
	public List getListByGroupId(long groupId);
	
	public List getListByCampaign(long campaignId);
	
}
