package com.rest.jersey.converterclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

/**
 * Servlet implementation class RESTUnitConverterClient
 */
@WebServlet("/restUnitConverterClient")
public class RESTUnitConverterClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String webServiceURI = "https://unitconvertrestapi.azurewebsites.net";
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// read from HTML Fields
		String cm = request.getParameter("cm");
        String unit = request.getParameter("unit");
                
     // Call the RESTful service
        ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        URI serviceURI = UriBuilder.fromUri(webServiceURI).build();
        WebTarget webTarget = client.target(serviceURI);

        String jsonResponse = webTarget.path("convertto")
            .queryParam("cm", cm)
            .queryParam("unit", unit)
            .request()
            .accept(MediaType.APPLICATION_JSON)
            .get(String.class);
        
      //Display the converted value
     // Display the response in the browser
        
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String htmlResponse = "<html>";
        htmlResponse += "<h2>Result: " + jsonResponse +"</h2>";
        htmlResponse += "</html>";
        writer.println(htmlResponse);
        
        writer.println("<h1>Converted Result</h1>");
        writer.println("<p>" + htmlResponse + "</p>");
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RESTUnitConverterClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
