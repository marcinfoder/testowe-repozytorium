package com.capgemini.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.UserRoles;
import com.capgemini.persistence.AuthorityDao;
import com.capgemini.persistence.GroupDao;
import com.capgemini.persistence.UserDao;
import com.capgemini.persistence.domain.Authority;
import com.capgemini.persistence.domain.User;
import com.capgemini.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="hibernateUserDao")
	UserDao userDao;
	
	@Resource(name="hibernateGroupDao")
	GroupDao groupDao;
	
	@Resource(name="hibernateAuthorityDao")
	AuthorityDao authDao;
	
	@Override
	@Transactional
	public User getUserByLogin(String login) {
		return userDao.findByName(login);
	}

	@Override
	@Transactional
	public void newUserWithNewGroupAsAdmin(User user, String gname, String desc) {
		long groupId = groupDao.createGroup(gname, desc);
		
		user.setGroupId(groupId);
		userDao.addUser(user);
		
		Authority auth = new Authority();
		auth.Admin();
		auth.setUserId(user.getId());
		authDao.createAuthority(auth);
	}
}
