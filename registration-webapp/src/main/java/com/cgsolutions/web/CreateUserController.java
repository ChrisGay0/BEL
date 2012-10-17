package com.cgsolutions.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.security.domain.User;
import com.cgsolutions.security.service.UserManager;

@Controller
@RequestMapping("/createUser.htm")
public class CreateUserController {
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model){
		model.addAttribute("newUser", new User());
		
		return "createUser";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createTerm(@ModelAttribute("newUser")User user){
		userManager.save(user);
		
		return "redirect:editUser.htm?userId=" + user.getUserId();
	}
}
