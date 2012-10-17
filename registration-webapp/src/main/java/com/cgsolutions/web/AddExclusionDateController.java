package com.cgsolutions.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@RequestMapping("/addExclusionDates.htm")
@SessionAttributes("term")
public class AddExclusionDateController {
	@Autowired
	private TermManager termManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		Term term = termManager.find(Integer.parseInt(request.getParameter("termId")));
		//Should really take this into a form object with AutoPopulating list
		List<Date> newDates = new ArrayList<Date>();
		newDates.add(null);
		newDates.add(null);
		newDates.add(null);
		newDates.add(null);
		newDates.add(null);
		
		term.getExclusionDates().toString();
		term.setNewExclusionDates(newDates);
		model.addAttribute("term", term);
		
		return "addDates";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveTermWithDates(@ModelAttribute("term")Term term){
		for(Date date: term.getNewExclusionDates()){
			if(date != null){
				term.addExclusionDate(date);
			}
		}
		
		termManager.save(term);
		
		return "redirect:editTerm.htm?termId=" + term.getId();
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
	}
}
