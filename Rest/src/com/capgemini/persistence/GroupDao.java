package com.capgemini.persistence;

import com.capgemini.persistence.domain.Group;

public interface GroupDao {
	
	public long createGroup(String name, String description);

}
