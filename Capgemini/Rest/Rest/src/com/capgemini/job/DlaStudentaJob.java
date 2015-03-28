package com.capgemini.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;

import com.capgemini.context.AppContext;

public class DlaStudentaJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		String url = (String) context.getJobDetail().getJobDataMap().get("www");
		RestTemplate restTemplate = AppContext.getBean(RestTemplate.class);
		String html = restTemplate.getForObject(url, String.class);
		System.out.println(html);
	}
}
