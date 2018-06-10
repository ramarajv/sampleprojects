package com.track.api.dao;

import org.hibernate.SessionFactory;


public class AbstractService {

	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory)

	{
		this.factory = factory;
	}

}