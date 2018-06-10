/**
 * 
 */
package com.api.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.api.domain.interfaces.Userable;

/**
 * @author RAM
 *
 */
@Path("/user")
public class UserController {
	
    Userable userprocess;	
    WebApplicationContext ctx;
	
	@GET
	  @Path("/check")
	  public Response trackings(@Context HttpHeaders headers, @Context HttpServletRequest request)
	  {
	    String jsonResponse = null;
	    try
	    {
	    	WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
	    	//userprocess = (UserProcess) SpringBeans.getBeanFromBeanFactory(request, "userprocess");
	    	Userable someBean = (Userable) ctx.getBean("userprocess");
	    	
	    	String status = someBean.addUser(request);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      System.out.println(e.getMessage());
	    }
	    return Response.status(200).entity(jsonResponse).build();
	  }
}
