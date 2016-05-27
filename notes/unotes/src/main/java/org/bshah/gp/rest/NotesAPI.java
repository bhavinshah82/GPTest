package org.bshah.gp.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bshah.gp.entity.NoteWrapper;
import org.bshah.gp.entity.Notes;
import org.bshah.gp.hibernate.NotesBO;

@Path("user/{userid}/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotesAPI 
{
	@GET
	public List<NoteWrapper> getAllUserNotes(@PathParam("userid") int userid)
	{
		List<Notes> notesList = NotesBO.getAllUserNotes(userid);
		List<NoteWrapper> noteWrapperList = new ArrayList<NoteWrapper>();
		for(Notes note: notesList)
			noteWrapperList.add(new NoteWrapper(note));
		return noteWrapperList;
	}

	@GET
	@Path("/{noteid}")
	public NoteWrapper getUserNotes(@PathParam("userid") int userid, @PathParam("noteid") int noteid)
	{

		NoteWrapper notewrapper = new NoteWrapper(NotesBO.getNote(userid, noteid));
		return notewrapper;
	}

	@POST
	public void addUserNote(@PathParam("userid") int userid, Notes note)
	{
		NotesBO.addUserNote(userid, note);
	}
	
	@DELETE
	public void deleteAllUserNotes(@PathParam("userid") int userid)
	{
		NotesBO.deleteAllNotes(userid);
	}
	

	@DELETE
	@Path("/{noteid}")
	public void deleteUserNote(@PathParam("userid") int userid, @PathParam("noteid") int noteid)
	{
		NotesBO.deleteNote(userid, noteid);
	}
	
	@PUT
	@Path("/{noteid}")
	public void updateNote(@PathParam("userid") int userid, @PathParam("noteid") int noteid, Notes updatedNote) 
	{
		NotesBO.updateUserNote(userid, noteid, updatedNote);
	}


}
