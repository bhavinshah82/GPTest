package org.bshah.gp.utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RestExceptionMapper implements ExceptionMapper<RestException>
{

	@Override
	public Response toResponse(RestException re) {
		return Response.status(re.getResponse().getStatus()).build();
	}

}
