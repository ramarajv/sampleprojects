/**
 * 
 */
package com.api.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.api.dao.AbstractService;
import com.api.model.UserMaster;
import com.api.service.IUserService;

/**
 * @author RAM
 *
 */
public class UserServiceImpl extends AbstractService implements IUserService {

	@Override
	public String createUserMaster(UserMaster user) {
		
		SessionFactory factory= getFactory();
		Transaction trans=null;
		Session session=null;
		try
		{
			session=factory.openSession();
			trans=session.beginTransaction();
			session.saveOrUpdate(user);
			trans.commit();
			
		}catch (Exception e) {
			trans.rollback();
			System.out.println(" addEmployee method  : "+e.getMessage());
			// TODO: handle exception
		}
		finally
		{
			session.close();
		}
		return null;
	}

}
