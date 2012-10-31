package com.cgsolutions.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cgsolutions.registration.domain.School;
import com.cgsolutions.registration.service.SchoolManager;
import com.cgsolutions.web.forms.TermEditForm;

@Controller
@RequestMapping("/editSchool.htm")
@SessionAttributes("school")
public class EditSchoolController {
	@Autowired
	private SchoolManager schoolManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		model.addAttribute("school", schoolManager.find());
		
		return "editSchool";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveSchool(@ModelAttribute("school")School school){
		schoolManager.save(school);
		
		return "redirect:editSchool.htm";
	}
}
