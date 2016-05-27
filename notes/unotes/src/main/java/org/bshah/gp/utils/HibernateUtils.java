package org.bshah.gp.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils
{
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory()
	{
		try
		{
			if (sessionFactory == null)
			{
				synchronized (HibernateUtils.class)
				{
					sessionFactory = new Configuration().configure().buildSessionFactory();
				}
			}
			return sessionFactory;
		}
		catch (Throwable ex)
		{
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw ex;
		}
	}
}
