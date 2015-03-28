package com.capgemini.persistence;

import com.capgemini.persistence.domain.User;

public interface IUserDao {

	public User findByName(String name);
}
