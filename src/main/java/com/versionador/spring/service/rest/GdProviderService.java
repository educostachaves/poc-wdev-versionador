package com.versionador.spring.service.rest;

import com.versionador.spring.model.GdResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/gds/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GdProviderService {
	
	@GET
	@Path("/fetchGdById/{id}")
	public GdResponse fetchGdById(@PathParam("id") int id);
	
	@GET
	@Path("/fetchAllGds/")
	public GdResponse fetchAllGds();
	
 
}

