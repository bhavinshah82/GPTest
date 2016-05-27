package org.bshah.gp.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Column(unique=true, nullable = false)
	private String emailId;
	@Column(nullable = false)
	private String password;
	private Date creationTime;
	private Date lastUpdateTime;

	@OneToMany(mappedBy = "user")
	private Collection<Notes> notes = new ArrayList<Notes>();

	public User()
	{
	}

	public User(String emailId, String password, Date creationTime, Date lastUpdateTime)
	{
		super();
		this.emailId = emailId;
		this.password = password;
		this.creationTime = creationTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
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
}
