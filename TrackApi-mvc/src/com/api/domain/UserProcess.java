/**
 * 
 */
package com.api.domain;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.api.domain.interfaces.Userable;
import com.api.model.UserMaster;
import com.api.service.IUserService;

/**
 * @author RAM
 *
 */
public class UserProcess implements Userable{

    IUserService userimpl;
    
	public IUserService getUserimpl() {
		return userimpl;
	}


	public void setUserimpl(IUserService userimpl) {
		this.userimpl = userimpl;
	}


	@Override
	public String addUser(HttpServletRequest request) {
		
		//WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
		//IUserService someBean = (IUserService) ctx.getBean("userservice");
		
		UserMaster user= new UserMaster();
		user.setUserName("Ram");
		user.setFullName("Raj");
		user.setPassword("ram");
		user.setEmailAddress("ram@gmail.com");
		user.setAlterContactNumber("987456");
		user.setContactNumber("123456789");
		user.setDistributor("distribu");
		user.setRole("rolr");
		user.setCreatedDate(new Date());
		user.setModifiedDate(new Date());
		user.setStatus("A");
		user.setCreatedDate(new Date());
		user.setAddress("addr");
		user.setCreatedBy("admin");
		
		String status = userimpl.createUserMaster(user);
		return status;
		
	}

}
