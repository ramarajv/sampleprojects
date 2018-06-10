/**
 * 
 */
package com.track.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.track.api.domain.UserProcess;
import com.track.api.domain.interfaces.Userable;
import com.track.api.util.SpringBeans;

/**
 * @author RAM
 *
 */
@Controller
@RequestMapping("/logintest")
public class VehicleController {
	
	
	@RequestMapping(value = "/checkuser", consumes = "application/json", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public String checkUser(HttpServletRequest request)
	{
			
			String checkUser = "success";
			try
			{
				 Userable userprocess = (UserProcess) SpringBeans.getBeanFromBeanFactory(request, "userprocess");
				 String status = userprocess.addUser();
				
			}
			catch (Exception e)
			{
				checkUser = "fail";
				e.printStackTrace();
			}
			return checkUser;
			
		}

}
