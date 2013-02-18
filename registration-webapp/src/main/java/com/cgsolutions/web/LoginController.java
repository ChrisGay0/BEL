package com.cgsolutions.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgsolutions.registration.service.SchoolManager;
import com.cgsolutions.security.domain.User;
import com.cgsolutions.security.service.UserManager;

@Controller
@RequestMapping("/login.htm")
public class LoginController {
	@Autowired
	private UserManager userManager;
	@Autowired
	private SchoolManager schoolManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String login(HttpServletRequest request){
		//generate the system user if this is the first time the system has been loaded
		if(userManager.find("SYSTEM") == null){
			User user = new User();
			user.setUserId("SYSTEM");
			user.setFirstName("System");
			user.setSurname("Default");
			user.setPassword("database");
						
			userManager.save(user);
		}
		request.getSession().setAttribute("schoolName", schoolManager.find().getName());
		
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String validate(HttpServletRequest request){
		User user = userManager.find(request.getParameter("userId"));
		if(user != null && user.validatePassword(request.getParameter("password"))){
			request.getSession().setAttribute("validUser", user);
			request.getSession().setAttribute("schoolName", schoolManager.find().getName());
			
			return "redirect:menu.htm";
		}
		else{
			return login(request);
		}
	}
}
