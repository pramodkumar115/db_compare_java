package com.dbcompare.services;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.dbcompare.domain.Configuration;
import com.dbcompare.domain.DBCompareResult;

@Path("/dbcompare")
public class DBCompareService {
	@POST
	@Path("/difference")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDBDiff(String json) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper map = new ObjectMapper();
		Configuration config = map.readValue(json, Configuration.class);
		DBCompareHelper helper = new DBCompareHelper();
		DBCompareResult result = helper.compare(config);
		
		return map.writeValueAsString(result);
	}

	@GET
	@Path("/difference")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDBDiff() throws JsonGenerationException, JsonMappingException, IOException {
		
		return ("Hello world");
	}
}
