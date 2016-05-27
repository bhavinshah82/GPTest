package org.bshah.gp.utils;

import java.util.Date;

import org.bshah.gp.entity.User;
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

	}
}
