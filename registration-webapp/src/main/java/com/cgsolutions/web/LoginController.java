package com.cgsolutions.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgsolutions.security.domain.User;
import com.cgsolutions.security.service.UserManager;

@Controller
@RequestMapping("/login.htm")
public class LoginController {
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String validate(HttpServletRequest request){
		User user = userManager.find(request.getParameter("userId"));
		if(user.validatePassword(request.getParameter("password"))){
			request.getSession().setAttribute("validUser", user);
			
			return "redirect:menu.htm";
		}
		else{
			return login();
		}
	}
}
