package com.cgsolutions.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.domain.propertyEditors.DatePropertyEditor;
import com.cgsolutions.registration.service.TermManager;

@Controller
@RequestMapping("/createTerm.htm")
public class CreateTermController {
	@Autowired
	private TermManager termManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model){
		model.addAttribute("newTerm", new Term());
		
		return "createTerm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createTerm(@ModelAttribute("newTerm")Term term){
		termManager.save(term);
		
		return "redirect:editTerm.htm?termId=" + term.getId();
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
	}
}