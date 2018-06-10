/**
 * 
 */
package com.api.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author RAM
 *
 */
public class ApiFilter implements Filter{
	public void destroy() {}
	  
	  public void doFilter(ServletRequest reques, ServletResponse respons, FilterChain chain)
	    throws IOException, ServletException
	  {
	    HttpServletRequest request = (HttpServletRequest)reques;
	    HttpServletResponse response = (HttpServletResponse)respons;
	    
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	    response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	    response.addHeader("Access-Control-Max-Age", "1");
	    
	    chain.doFilter(request, response);
	  }
	  
	  public void init(FilterConfig fConfig)
	    throws ServletException
	  {}
}
