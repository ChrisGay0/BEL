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

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Intolerance;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.enums.Ethnicity;
import com.cgsolutions.registration.domain.enums.TypeOfAttendance;
import com.cgsolutions.registration.domain.propertyEditors.DatePropertyEditor;
import com.cgsolutions.registration.domain.propertyEditors.RoomPropertyEditor;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;
import com.cgsolutions.web.forms.ChildEditForm;

@Controller
@RequestMapping("/editChild.htm")
@SessionAttributes("formObject")
public class EditChildController {
	@Autowired
	private ChildManager childManager;
	@Autowired
	private RoomManager roomManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		Child child = childManager.findChild(Integer.parseInt(request.getParameter("childId")));
		ChildEditForm form = new ChildEditForm();
		form.setChild(child);
		
		model.addAttribute("formObject", form);
		model.addAttribute("rooms", roomManager.findAllActive());
		model.addAttribute("typeOfAttendances", TypeOfAttendance.values());
		model.addAttribute("ethnicityList", Ethnicity.values());
		
		return "editChild";
	}
	
	@RequestMapping(params="action=Save", method=RequestMethod.POST)
	public String saveChild(@ModelAttribute("formObject")ChildEditForm form){
		childManager.saveChild(form.getChildWithNewData());
		
		return "redirect:editChild.htm?childId=" + form.getChild().getId();
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
		binder.registerCustomEditor(Room.class, new RoomPropertyEditor(roomManager));
	}
}
