package com.cgsolutions.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.service.RoomManager;

@Controller
@RequestMapping("/chooseRegister.htm")
public class ChooseRegisterController {
	@Autowired
	private RoomManager roomManager;

	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model){
		model.addAttribute("rooms", roomManager.findAllActive());
		
		return "chooseRegister";
	}
}