package com.capgemini.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.GroupDao;
import com.capgemini.persistence.UserDao;
import com.capgemini.persistence.domain.User;
import com.capgemini.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="hibernateUserDao")
	UserDao userDao;
	
	@Resource(name="hibernateGroupDao")
	GroupDao groupDao;
	
	@Override
	@Transactional
	public User getUserByLogin(String login) {
		return userDao.findByName(login);
	}

	@Override
	@Transactional
	public void createNewUserAndGroup(User user, String gname, String desc) {
		long groupId = groupDao.createGroup(gname, desc);
		user.setGroupId(groupId);
		userDao.addUser(user);
	}
}
