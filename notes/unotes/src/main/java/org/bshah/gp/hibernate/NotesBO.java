package org.bshah.gp.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bshah.gp.entity.Notes;
import org.bshah.gp.entity.User;
import org.bshah.gp.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class NotesBO
{

	public NotesBO()
	{
	}

	@SuppressWarnings("unchecked")
	public static List<Notes> getAllUserNotes(int userid)
	{
		Session session = null;

		List<Notes> notesList = new ArrayList<Notes>();
		try
		{
			session = HibernateUtils.openSession();
			User user = UserBO.getUser(userid);
			notesList = (ArrayList<Notes>) session.createCriteria(Notes.class).add(Restrictions.eq("user", user))
					.list();
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

		return notesList;
	}

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

	@SuppressWarnings("unchecked")
	public static void updateUserNote(int userid, int noteid, Notes updatedNote)
	{
		System.out.println("Updating user note id = " +noteid);
		Session session = null;
		List<Notes> notesList = new ArrayList<Notes>();
		Notes note = null;
		try
		{
			session = HibernateUtils.openSession();
			Transaction tx = session.beginTransaction();
			User user = UserBO.getUser(userid);
			notesList = (ArrayList<Notes>) session.createCriteria(Notes.class).add(Restrictions.eq("user", user))
					.add(Restrictions.eq("noteId", noteid)).list();
			System.out.println("noteslist size = "+notesList.size());

			if (notesList.size() == 0)
			{
				// throw new Exception("Unauthorized");
			}
			else
			{
				note = notesList.get(0);
				System.out.println("Updating note - "+note.getNoteId());
				note.setLastUpdateTime(new Date());
				note.setNote(updatedNote.getNote());
				note.setTitle(updatedNote.getTitle());
				session.update(note);
			}
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
	}

	public static void addUserNote(int id, Notes note)
	{
		User user = UserBO.getUser(id);
		note.setUser(user);
		Date date = new Date();
		note.setCreationTime(date);
		note.setLastUpdateTime(date);
		Session session = null;
		try
		{
			session = HibernateUtils.openSession();
			Transaction tx = session.beginTransaction();
			session.save(note);
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
	}

	public static void deleteAllNotes(int id)
	{
		Session session = null;
		try
		{
			session = HibernateUtils.openSession();
			session.beginTransaction();
			String hql = "delete from Notes where userId = " + id;
			Query query = session.createQuery(hql);
			query.executeUpdate();
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
	}

	public static void deleteNote(int userid, int noteid)
	{
		Session session = null;
		try
		{
			session = HibernateUtils.openSession();
			Transaction tx = session.beginTransaction();
			User user = UserBO.getUser(userid);
			Notes note = (Notes) session.get(Notes.class, noteid);
			@SuppressWarnings("unchecked")
			ArrayList<Notes> notesList = (ArrayList<Notes>) session.createCriteria(Notes.class)
					.add(Restrictions.eq("user", user)).add(Restrictions.eq("noteId", noteid)).list();

			if (notesList.size() == 0)
			{
				return;
			}
			note = notesList.get(0);
			session.delete(note);
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
	}

}
