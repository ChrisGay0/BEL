package com.cgsolutions.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.service.TermManager;

@Controller
@RequestMapping("/findTerm.htm")
public class FindTermController {
	@Autowired
	private TermManager termManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showMenu(Model model){
		model.addAttribute("terms", termManager.findAllTerms());
		
		return "findTerm";
	}
}
