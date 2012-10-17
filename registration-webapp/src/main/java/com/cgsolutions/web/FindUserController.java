package com.cgsolutions.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.security.service.UserManager;

@Controller
@RequestMapping("/findUser.htm")
public class FindUserController {
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showMenu(Model model){
		model.addAttribute("users", userManager.findAll());
		
		return "findUser";
	}
}
