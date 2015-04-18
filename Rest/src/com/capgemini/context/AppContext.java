package com.capgemini.context;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

public class AppContext {

	private static ApplicationContext ctx;

	/**
	 * Injected from the class "ApplicationContextProvider" which is
	 * automatically loaded during Spring-Initialization.
	 */
	public static void setApplicationContext(
			ApplicationContext applicationContext) {
		ctx = applicationContext;
	}

	/**
	 * Get access to the Spring ApplicationContext from everywhere in your
	 * Application.
	 */
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	public static <T> T getBean(final String name, final Class<T> clazz) {
		return (T) AppContext.getApplicationContext().getBean(name, clazz);
	}

	public static <T> T getBean(final Class<T> clazz) {
		try {
			return (T) AppContext.getApplicationContext().getBean(
					clazz.getSimpleName(), clazz);
		} catch (NoSuchBeanDefinitionException e) {
			return (T) AppContext.getApplicationContext().getBean(
					WordUtils.uncapitalize(clazz.getSimpleName()), clazz);
		}
	}
}
