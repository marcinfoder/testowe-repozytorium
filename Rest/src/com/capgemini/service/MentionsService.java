package com.capgemini.service;

import java.util.List;

import com.capgemini.persistence.domain.TwitterMentions;

public interface MentionsService {
	
	public List<TwitterMentions> getMentionsByLogin(String login);
}
