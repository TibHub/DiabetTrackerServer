package com.diabettracker.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.diabettracker.model.Calory;

@Path("/authorization")
public interface IAuthorizationService {

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String doPostAuthCode(@FormParam("code") String code);

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Calory> doGet();

}
