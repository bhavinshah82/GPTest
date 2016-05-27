package org.bshah.gp.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bshah.gp.entity.User;
import org.bshah.gp.hibernate.UserBO;

@Path("user")
public class UserAPI 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers()
	{
		List<User> userList = UserBO.getAllUsers();
		return userList;
	}
}
