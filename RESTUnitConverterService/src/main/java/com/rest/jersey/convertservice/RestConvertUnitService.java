package com.rest.jersey.convertservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/convertto")
@Consumes(MediaType.APPLICATION_JSON)

public class RestConvertUnitService {
	@GET //Invocation method
	@Produces(MediaType.APPLICATION_JSON) //Output data type

	public Response Converter(@QueryParam("cm") double cm, @QueryParam("unit") String unit) 	throws JSONException {
		JSONObject json = new JSONObject();
		double c;
	    switch(unit) {
	    
	        case "m":
	            c = cm / 100;
	            break;
	            
	        case "km":
	            c = cm / 100000;
	            break;

	        default:
        		return Response.status(400).entity("400 Invalid Operator").build();	
	    }
	    
		json.put("cm", cm);
		json.put("unit", unit);
		json.put("c", c);
		String result = ""+json;
		return Response.status(200).entity(result).build();	
	}
}
