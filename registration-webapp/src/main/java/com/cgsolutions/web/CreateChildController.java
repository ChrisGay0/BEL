package com.cgsolutions.web;

import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.enums.Ethnicity;
import com.cgsolutions.registration.domain.propertyEditors.DatePropertyEditor;
import com.cgsolutions.registration.domain.propertyEditors.RoomPropertyEditor;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;

@Controller
@RequestMapping("/createChild.htm")
public class CreateChildController {
	@Autowired
	private ChildManager childManager;
	@Autowired
	private RoomManager roomManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model){
		model.addAttribute("newChild", new Child());
		model.addAttribute("rooms", roomManager.findAllActive());
		model.addAttribute("ethnicityList", Ethnicity.values());
		
		return "createChild";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createChild(@ModelAttribute("newChild")Child child){
		childManager.createChild(child);
		
		return "redirect:editChild.htm?childId=" + child.getId();
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
		binder.registerCustomEditor(Room.class, new RoomPropertyEditor(roomManager));
	}
}