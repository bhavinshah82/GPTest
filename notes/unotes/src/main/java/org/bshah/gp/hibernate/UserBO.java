package org.bshah.gp.hibernate;

import org.bshah.gp.entity.User;
import org.bshah.gp.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserBO 
{
	public static User addDummyUser(User user)
	{
		Session session = null;
		try
		{
			session = HibernateUtils.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			if (null != session && session.isOpen())
			{
				System.out.println("Closing session");
				session.close();
			}
		}
		return user;
	}
	
	public static User getUser(int id)
	{
		Session session = null;
		User user = null;
		try
		{
			session = HibernateUtils.getSessionFactory().openSession();
			user = (User) session.get(User.class, id);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			if (null != session && session.isOpen())
			{
				System.out.println("Closing session");
				session.close();
			}
		}
		return user;
	}

}
