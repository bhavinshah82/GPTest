package org.bshah.gp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notes")

public class Notes 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int noteId;
	@Column(nullable = false, length = 50)
	private String title;
	@Column(nullable = false, length = 1000)
	private String note;
	private Date creationTime;
	private Date lastUpdateTime;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public Notes()
	{
	}

	public Notes(String title, String note, Date creationTime, Date lastUpdateTime, User user)
	{
		super();
		this.title = title;
		this.note = note;
		this.creationTime = creationTime;
		this.lastUpdateTime = lastUpdateTime;
		this.user = user;
	}

	public int getNoteId()
	{
		return noteId;
	}

	public void setNoteId(int noteId)
	{
		this.noteId = noteId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public Date getCreationTime()
	{
		return creationTime;
	}

	public void setCreationTime(Date creationTime)
	{
		this.creationTime = creationTime;
	}

	public Date getLastUpdateTime()
	{
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime)
	{
		this.lastUpdateTime = lastUpdateTime;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

}
