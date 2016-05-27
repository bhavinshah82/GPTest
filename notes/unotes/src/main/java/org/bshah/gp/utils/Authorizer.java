package org.bshah.gp.utils;

import java.util.StringTokenizer;

import org.bshah.gp.entity.User;
import org.bshah.gp.hibernate.UserBO;
import org.glassfish.jersey.internal.util.Base64;

public class Authorizer 
{

	private static final String AUTH_PREFIX = "Basic ";
	private static final int INVALID_USER = -1;	
	
	public static boolean authorize(String authValue, int userid)
	{
		if(authValue == null || authValue.equals(""))
			return false;
		authValue = authValue.replaceFirst(AUTH_PREFIX, "");
		if(authValue.equals(""))
			return false;
		String b64DecodedStr = Base64.decodeAsString(authValue);
		StringTokenizer tokenizer = new StringTokenizer(b64DecodedStr, ":");
		String user_email = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		int uid = verifyUserPassword(user_email, password);
		if(uid==INVALID_USER || userid!=uid)
			return false;
		else return true;
	}
	
	public static int verifyUserPassword(String email, String password)
	{
		User user = UserBO.getUserbyemail(email);
		if(user==null)
			return INVALID_USER;
		if(user.getPassword().equals(password))
			return user.getUserId();
		else return INVALID_USER;
	}
}
