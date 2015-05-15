package com.capgemini.persistence;

import java.util.List;

import com.capgemini.persistence.domain.Photo;

public interface PhotoDao {
	
	public boolean add(Photo photo);
	
	public void update(Photo photo);
	
	public Photo deleteWith(long id);
	
	public Photo getWith(long id);
	
	public List getListByGroupId(long groupId);

}
