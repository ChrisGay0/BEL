package com.cgsolutions.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.domain.propertyEditors.DatePropertyEditor;
import com.cgsolutions.registration.service.TermManager;

@Controller
@RequestMapping("/editTerm.htm")
@SessionAttributes("term")
public class EditTermController {
	@Autowired
	private TermManager termManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		model.addAttribute("term", termManager.find(Integer.parseInt(request.getParameter("termId"))));
		
		return "editTerm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveTerm(@ModelAttribute("term")Term term){
		termManager.save(term);
		
		return "redirect:editTerm.htm?termId=" + term.getId();
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
	}
}
