package com.capgemini.monitor;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MonitorAction {

	public static void main(String[] args) {
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(
				"file:webapp/WEB-INF/applicationContext.xml");
		RequestMonitor monitor = (RequestMonitor) context
				.getBean("requestMonitor");
		monitor.start();
	}
}
