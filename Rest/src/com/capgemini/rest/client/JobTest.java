package com.capgemini.rest.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(inheritLocations = true, locations = {
		"file:webapp/WEB-INF/rest-servlet.xml",
		"file:webapp/WEB-INF/rest-context.xml" })
public class JobTest {

	@Test
	public void test() {
	}

	public static void main(String[] args) {
		new FileSystemXmlApplicationContext(new String[] {
				"file:webapp/WEB-INF/rest-servlet.xml",
				"file:webapp/WEB-INF/rest-context.xml" });
	}
}
