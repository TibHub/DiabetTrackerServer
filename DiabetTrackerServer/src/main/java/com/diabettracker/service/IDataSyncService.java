package com.diabettracker.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.diabettracker.process.DataSyncContainer;

@Path("/dataSync")
public interface IDataSyncService {

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces({ MediaType.APPLICATION_JSON })
	public DataSyncContainer doPostAuthCode(@FormParam("clientId") String clientId);

}
