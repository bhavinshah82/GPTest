package org.bshah.gp.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.bshah.gp.entity.User;
import org.bshah.gp.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserBO 
{
	public static User addDummyUser(User user)
	{
		Session session = null;
		try
		{
			session = HibernateUtils.openSession();
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			HibernateUtils.closeSession(session);
		}
		return user;
	}
	
	public static User getUser(int id)
	{
		Session session = null;
		User user = null;
		try
		{
			session = HibernateUtils.openSession();
			user = (User) session.get(User.class, id);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			HibernateUtils.closeSession(session);
		}
		return user;
	}

	public static User getUserbyemail(String email)
	{
		Session session = null;
		User user = null;
		try
		{
			session = HibernateUtils.openSession();
			@SuppressWarnings("unchecked")
			List<User> userlist =  session.createCriteria(User.class).add(Restrictions.eq("emailId", email)).list();
			if(userlist.size()==0)
				return null;
			else user = userlist.get(0);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			HibernateUtils.closeSession(session);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public static List<User> getAllUsers()
	{
		Session session = null;
		List<User> users = new ArrayList<User>();
		try
		{
			session = HibernateUtils.openSession();
			users = session.createCriteria(User.class).list();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			HibernateUtils.closeSession(session);
		}
		return users;
	}

}
