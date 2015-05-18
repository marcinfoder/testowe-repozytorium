package com.capgemini.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.persistence.domain.TwitterCount;

@Service("AnalyticsService")
public interface AnalyticsService {

	public List getTwitterCountsByGroupId(String username);
	
	public TwitterCount getTwitterCountById(long id);
	
	public List getTwitterCountByDateRange(Date start, Date end);
	
}
