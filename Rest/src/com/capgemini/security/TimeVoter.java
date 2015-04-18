package com.capgemini.security;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

public class TimeVoter implements AccessDecisionVoter<Object> {

	public boolean supports(ConfigAttribute attribute) {
		return false;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> attributes) {
		System.out.println("voter");
		int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

		if (16 <= currentHour && currentHour <= 18) {
			return ACCESS_GRANTED;
		}
		return ACCESS_DENIED;
	}

}
