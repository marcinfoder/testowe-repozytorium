package com.capgemini.service;

import com.capgemini.persistence.domain.User;

public interface UserService {

	public User getUserByLogin(String login);
	
	public void createNewUserAndGroup(User user, String gname, String desc);
}
