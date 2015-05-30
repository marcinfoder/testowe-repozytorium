package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.AuthorityDao;
import com.capgemini.persistence.UserDao;
import com.capgemini.persistence.domain.Authority;
import com.capgemini.persistence.domain.User;
import com.capgemini.service.AuthorityService;

@Service("AuthorityService")
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	private AuthorityDao authDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public void createAuthority(long userId, String role) {
		Authority auth = new Authority();
		auth.setRole(role);
		auth.setUserId(userId);
		authDao.createAuthority(auth);
	}

	@Override
	@Transactional
	public void deleteAuthority(long authId) {
		authDao.deleteAuthority(authId);
	}

	@Override
	@Transactional
	public List<Authority> getAuthListByUserLogin(String login) {
		User user = userDao.findByName(login);
		if(user != null)
		{
			return authDao.getAuthoritiesListByUserId(user.getId());
		}
		return null; 
	}

	@Override
	public List<Authority> getAuthListByUserId(long userId) {
		return authDao.getAuthoritiesListByUserId(userId);
	}
	
}
