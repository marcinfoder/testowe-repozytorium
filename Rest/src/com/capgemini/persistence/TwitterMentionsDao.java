package com.capgemini.persistence;

import java.util.List;

import com.capgemini.persistence.domain.TwitterMentions;

public interface TwitterMentionsDao {
	
	List<TwitterMentions> getMentionsByGroupId(long groupId);

}
