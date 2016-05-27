package org.bshah.gp.utils;

import java.util.Date;

import org.bshah.gp.entity.Notes;
import org.bshah.gp.entity.User;
import org.bshah.gp.hibernate.NotesBO;
import org.bshah.gp.hibernate.UserBO;

public class Test 
{
	public static void main(String[] args) 
	{
		Date date = new Date();
		User user1 = new User("bs@gmail.com", "password", date, date);
		UserBO.addDummyUser(user1);
		date = new Date();
		User user2 = new User("bshah@gmail.com", "password", date, date);
		UserBO.addDummyUser(user2);

		Notes note = new Notes();
		note.setTitle("note1");
		note.setNote("hello world");
		int noteid = NotesBO.addUserNote(user1.getUserId(), note);
		System.out.println("note id is " + noteid);
		
		note = null;
		note = NotesBO.getNote(user1.getUserId(), noteid);
				
	}
}
