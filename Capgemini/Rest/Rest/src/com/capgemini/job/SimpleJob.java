package com.capgemini.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.capgemini.context.AppContext;
import com.capgemini.persistence.IBookDao;
import com.capgemini.service.IBookService;

public class SimpleJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println(context.getJobDetail().getJobDataMap().get("param"));
		try {
			IBookService service = (IBookService) context.getScheduler()
					.getContext().get("bookService");
			System.out.println(service.findAll());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		IBookDao dao = AppContext.getBean("bookDao", IBookDao.class);
		System.out.println(dao.list().getOwner());
		System.out.println("parse some html pages");
	}
}
