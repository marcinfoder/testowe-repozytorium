package com.capgemini.persistence;

import com.capgemini.persistence.domain.User;

public interface UserDao {

	public User findByName(String name);
}
