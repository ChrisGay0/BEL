package com.cgsolutions.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.security.domain.User;
import com.cgsolutions.security.service.UserManager;
import com.cgsolutions.security.thread.LoggedInUser;

@Controller
@RequestMapping("/changePassword.htm")
public class ChangePasswordController {
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		model.addAttribute("user", LoggedInUser.find());
		model.addAttribute("changed", request.getParameter("changed"));
		
		return "changePassword";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String changePassword(HttpServletRequest request){
		User user = userManager.find(LoggedInUser.find().getUserId());
		boolean validPassword = user.validatePassword(request.getParameter("currentPassword"));
		if(validPassword){
			user.setPassword(request.getParameter("newPassword"));
		}
		
		return "redirect:changePassword.htm?changed=" + validPassword;
	}
}
