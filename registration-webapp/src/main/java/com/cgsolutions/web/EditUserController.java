package com.cgsolutions.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cgsolutions.security.domain.User;
import com.cgsolutions.security.service.UserManager;

@Controller
@RequestMapping("/editUser.htm")
@SessionAttributes("user")
public class EditUserController {
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		model.addAttribute("user", userManager.find(request.getParameter("userId")));
		
		return "editUser";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveTerm(@ModelAttribute("user")User user){
		userManager.save(user);
		
		return "redirect:editUser.htm?userId=" + user.getUserId();
	}
}
