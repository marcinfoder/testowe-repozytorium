package com.capgemini.service;

import com.capgemini.persistence.domain.User;

public interface UserService {

	public User getUserByLogin(String login);
	
	public void newUserWithNewGroupAsAdmin(User user, String gname, String desc);
}
