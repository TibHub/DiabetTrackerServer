package com.diabettracker.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.sun.ws.rest.api.representation.FormURLEncodedProperties;

@Path("/authorization")
public interface IAuthorizationService {

	@POST
	public String doPostAuthCode(FormURLEncodedProperties p);
	
	@GET
	public String doGet();

}
