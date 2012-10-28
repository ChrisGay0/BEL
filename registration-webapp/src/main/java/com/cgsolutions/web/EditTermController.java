package com.cgsolutions.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.domain.propertyEditors.DatePropertyEditor;
import com.cgsolutions.registration.service.TermManager;
import com.cgsolutions.security.utility.MyDateUtils;
import com.cgsolutions.web.forms.TermEditForm;

@Controller
@RequestMapping("/editTerm.htm")
@SessionAttributes("formObject")
public class EditTermController {
	@Autowired
	private TermManager termManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		TermEditForm form = new TermEditForm();
		form.setTerm(termManager.find(Integer.parseInt(request.getParameter("termId"))));
		
		model.addAttribute("formObject", form);
		
		return "editTerm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveTerm(@ModelAttribute("formObject")TermEditForm form){
		form.getTerm().getExclusionDates().addAll(form.getAddedDates());
		termManager.save(form.getTerm());
		
		return "redirect:editTerm.htm?termId=" + form.getTerm().getId();
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
	}
}
