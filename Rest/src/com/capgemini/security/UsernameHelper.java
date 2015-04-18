package com.capgemini.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsernameHelper {

	public static String getUsername() {
		SecurityContext context = SecurityContextHolder
				.getContextHolderStrategy().getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication != null) {
			return authentication.getName();
		}
		return "";
	}

}
