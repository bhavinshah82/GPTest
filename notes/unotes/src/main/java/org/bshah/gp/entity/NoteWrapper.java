package org.bshah.gp.entity;

import java.util.Date;

/*
 * Use note wrapper to mask the user details from note entity object
 */
public class NoteWrapper 
{
	private int noteId;
	private String title;
	private String note;
	private Date creationTime;
	private Date lastUpdateTime;
	
	public NoteWrapper()
	{}
	
	public NoteWrapper(Notes notes)
	{
		if(notes==null)
			return;
		noteId=notes.getNoteId();
		title=notes.getTitle();
		note=notes.getNote();
		creationTime=notes.getCreationTime();
		lastUpdateTime=notes.getLastUpdateTime();
	}
	
	
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
