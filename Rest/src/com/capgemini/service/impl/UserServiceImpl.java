package com.capgemini.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.IUserDao;
import com.capgemini.persistence.domain.User;
import com.capgemini.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="hibernateUserDao")
	IUserDao userDao;
	
	@Override
	@Transactional
	public User getUserByLogin(String login) {
		return userDao.findByName(login);
	}

}
