package com.cgsolutions.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/chooseLunchRegister.htm")
public class ChooseLunchRegisterController {
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model){
		return "chooseLunchRegister";
	}
}
