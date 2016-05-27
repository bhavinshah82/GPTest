package org.bshah.gp.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bshah.gp.entity.NoteWrapper;
import org.bshah.gp.entity.Notes;
import org.bshah.gp.hibernate.NotesBO;
import org.bshah.gp.utils.Authorizer;
import org.bshah.gp.utils.RestException;

@Path("user/{userid}/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotesAPI {

	private static final String AUTH_KEY = "Authorization"; 
	
	public NotesAPI()
	{}
	
	public NotesAPI(@HeaderParam(AUTH_KEY) String authKey, @PathParam("userid") String userid)
	{
//		System.out.println("auth key is "+ authKey);
		int uid;
		try
		{
			uid = Integer.parseInt(userid);
		}
		catch(NumberFormatException e)
		{
			throw new RestException(Response.Status.BAD_REQUEST, "Invalid user id");
		}
		if(!Authorizer.authorize(authKey, uid))
		{
			throw new RestException(Response.Status.UNAUTHORIZED, "Invalid username/password");
		}
	}

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
	public NoteWrapper getUserNotes(@PathParam("userid") int userid, @PathParam("noteid") String noteid)
	{
		int nid;
		try
		{
			nid = Integer.parseInt(noteid);
		}
		catch(NumberFormatException e)
		{
			throw new RestException(Response.Status.BAD_REQUEST, "Invalid note id");
		}
		NoteWrapper notewrapper = new NoteWrapper(NotesBO.getNote(userid, nid));
		return notewrapper;
	}

	@POST
	public void addUserNote(@PathParam("userid") int userid, Notes note)
	{
		if(note==null || note.getTitle() == null )
			throw new RestException(Response.Status.BAD_REQUEST, "Note Data Missing");
		NotesBO.addUserNote(userid, note);
	}
	
	@DELETE
	public void deleteAllUserNotes(@PathParam("userid") int userid)
	{
		NotesBO.deleteAllNotes(userid);
	}
	

	@DELETE
	@Path("/{noteid}")
	public void deleteUserNote(@PathParam("userid") int userid, @PathParam("noteid") String noteid){
		int nid;
		try
		{
			nid = Integer.parseInt(noteid);
		}
		catch(NumberFormatException e)
		{
			throw new RestException(Response.Status.BAD_REQUEST, "Invalid note id");
		}
		NotesBO.deleteNote(userid, nid);
	}
	
	@PUT
	@Path("/{noteid}")
	public void updateNote(@PathParam("userid") int userid, @PathParam("noteid") String noteid, Notes updatedNote) {
		int nid;
		try
		{
			nid = Integer.parseInt(noteid);
		}
		catch(NumberFormatException e)
		{
			throw new RestException(Response.Status.BAD_REQUEST, "Invalid note id");
		}
		if(updatedNote==null || updatedNote.getTitle() == null )
			throw new RestException(Response.Status.BAD_REQUEST, "Note Data Missing");
		NotesBO.updateUserNote(userid, nid, updatedNote);
	}

	

}
