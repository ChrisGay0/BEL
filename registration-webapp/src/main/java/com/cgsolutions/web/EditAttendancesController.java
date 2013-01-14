package com.cgsolutions.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.service.AttendanceManager;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.TermManager;
import com.cgsolutions.web.forms.EditTermForm;

@Controller
@RequestMapping("/editAttendances.htm")
public class EditAttendancesController {
	@Autowired
	private AttendanceManager attendanceManager;
	@Autowired
	private ChildManager childManager;
	@Autowired
	private TermManager termManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showAttendances(Model model, HttpServletRequest request){
		Child child = childManager.findChild(Integer.parseInt(request.getParameter("childId")));
		Term term = termManager.findCurrentTerm();
		EditTermForm form = new EditTermForm();
		form.setAttendances(attendanceManager.findAttendancesForChildInTerm(child, term));
		form.setTerm(term);
		form.setChild(child);
		
		model.addAttribute("form", form);
		
		return "editAttendances";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String deleteAttendances(@ModelAttribute("form")EditTermForm form){
		attendanceManager.deleteAttendances(form.getSelectedAttendances());
		
		return "redirect:editAttendances.htm?childId=" + form.getChild().getId();
	}
}
