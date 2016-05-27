package org.bshah.gp.utils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.hibernate.HibernateException;

public class RestException extends WebApplicationException
{

	private static final long serialVersionUID = 1L;

	public RestException() 
	{
		super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.build());
	}

	public RestException(String message) 
	{
		super(Response.status(Response.Status.NOT_FOUND).entity(message)
				.type(MediaType.TEXT_PLAIN).build());
	}

	public RestException(Response.Status status, String message) 
	{
		super(Response.status(status).entity(message)
				.type(MediaType.TEXT_PLAIN).build());
	}

}
