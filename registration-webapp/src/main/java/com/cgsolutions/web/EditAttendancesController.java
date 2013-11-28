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

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.domain.enums.TypeOfAttendance;
import com.cgsolutions.registration.domain.propertyEditors.DatePropertyEditor;
import com.cgsolutions.registration.domain.propertyEditors.RoomPropertyEditor;
import com.cgsolutions.registration.service.AttendanceManager;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;
import com.cgsolutions.registration.service.TermManager;
import com.cgsolutions.web.forms.EditTermForm;

@Controller
@RequestMapping("/editAttendances.htm")
@SessionAttributes("form")
public class EditAttendancesController {
	@Autowired
	private AttendanceManager attendanceManager;
	@Autowired
	private ChildManager childManager;
	@Autowired
	private TermManager termManager;
	@Autowired
	private RoomManager roomManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showAttendances(Model model, HttpServletRequest request){
		Child child = childManager.findChild(Integer.parseInt(request.getParameter("childId")));
		Term term = termManager.findCurrentTerm();
		EditTermForm form = new EditTermForm();
		form.setAttendances(attendanceManager.findAttendancesForChildInTerm(child, term));
		form.setTerm(term);
		form.setChild(child);
		
		model.addAttribute("attendanceTypes", TypeOfAttendance.values());
		model.addAttribute("rooms", roomManager.findAllActive());
		model.addAttribute("form", form);
		
		return "editAttendances";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String deleteAttendances(@ModelAttribute("form")EditTermForm form, HttpServletRequest request){
		if(StringUtils.hasText(request.getParameter("addAttendances"))){
			attendanceManager.save(form.getAttendancesToAdd());
		}
		else{
			attendanceManager.deleteAttendances(form.getSelectedAttendances());
		}
		
		return "redirect:editAttendances.htm?childId=" + form.getChild().getId();
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
		binder.registerCustomEditor(Room.class, new RoomPropertyEditor(roomManager));
	}
}
