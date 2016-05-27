package org.bshah.gp.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bshah.gp.entity.Notes;
import org.bshah.gp.entity.User;
import org.bshah.gp.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class NotesBO 
{
	@SuppressWarnings("unchecked")
	public static Notes getNote(int userid, int noteid)
	{
		Session session = null;
		Notes note = null;
		List<Notes> notesList = new ArrayList<Notes>();
		try
		{
			session = HibernateUtils.openSession();
			User user = UserBO.getUser(userid);
			notesList = (ArrayList<Notes>) session.createCriteria(Notes.class).add(Restrictions.eq("user", user))
					.add(Restrictions.eq("noteId", noteid)).list();

			if (notesList.size() != 0)
			{
				note = notesList.get(0);
			}
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			HibernateUtils.closeSession(session);
		}

		return note;
	}

	public static int addUserNote(int id, Notes note)
	{
		User user = UserBO.getUser(id);
		note.setUser(user);
		Date date = new Date();
		note.setCreationTime(date);
		note.setLastUpdateTime(date);
		int noteid = -1;
		Session session = null;
		try
		{
			session = HibernateUtils.openSession();
			Transaction tx = session.beginTransaction();
			noteid=(int) session.save(note);
			tx.commit();
		}
		catch (HibernateException e)
		{
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		finally
		{
			HibernateUtils.closeSession(session);
		}
		return noteid;
	}

}
