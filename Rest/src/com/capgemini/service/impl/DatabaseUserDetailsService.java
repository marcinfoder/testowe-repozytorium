package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.IUserDao;
import com.capgemini.persistence.domain.User;

@Service("databaseUserService")
@EnableTransactionManagement
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		User user = userDao.findByName(name);
		if (user == null) {
			throw new UsernameNotFoundException("User " + name + " not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if ("rod".equals(name)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authorities.add(new SimpleGrantedAuthority("guest"));
		}
		if ("scott".equals(name)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return new org.springframework.security.core.userdetails.User(name,
				user.getPassword(), authorities);
	}
}
