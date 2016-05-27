package org.bshah.gp.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bshah.gp.entity.Notes;
import org.bshah.gp.hibernate.NotesBO;

@Path("user/{userid}/notes")
public class NotesAPI 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Notes> getAllUserNotes(@PathParam("userid") int userid)
	{
		List<Notes> notesList = NotesBO.getAllUserNotes(userid);
		return notesList;
	}

}
