package com.api.util;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;

public class SpringBeans
{
	/**
	 * @param request - HttpServletRequest
	 * @param beanId - defined in spring.xml
	 * @return Object of the bean refers
	 */
	public static Object getBeanFromBeanFactory(HttpServletRequest request,String beanId)
	{
		Logger logger = Logger.getLogger(SpringBeans.class);

		BeanFactory beanfactory=(BeanFactory) request.getSession().getServletContext().getAttribute("beanFactory");
		logger.info("testing  param value 4 : " + beanId);

		return beanfactory.getBean(beanId);
	}
	
}
