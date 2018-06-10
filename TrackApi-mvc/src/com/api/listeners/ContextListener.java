package com.api.listeners;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ServletContextAware;



/**
 * This class should be used by non-spring-wired classes if they need access to
 * the application context
 */
public class ContextListener implements ServletContextAware
{
	BeanFactory beanFactory = null;

	public void setServletContext(ServletContext context)
	{		
		beanFactory = new ClassPathXmlApplicationContext("/resources/spring/spring.xml");		
		context.setAttribute("beanFactory", beanFactory);
	
	}

}