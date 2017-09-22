package com.dbcompare.services;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Path("/TestService")
public class TestService {
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsers() throws JsonGenerationException, JsonMappingException, IOException {
		User user = new User();
		user.setDept("Mech");
		user.setName("Pramod");
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(user);
		
	}
}
