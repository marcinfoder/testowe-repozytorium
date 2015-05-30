package com.capgemini.persistence;

import java.util.List;

import com.capgemini.persistence.domain.Authority;

public interface AuthorityDao {
	public boolean createAuthority(Authority auth);
	
	public void updateAuthority(Authority auth);
	
	public Authority deleteAuthority(long auth);
	
	public List<Authority> getAuthoritiesListByUserId(long userId);
}
