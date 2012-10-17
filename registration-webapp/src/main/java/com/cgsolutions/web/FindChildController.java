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

import com.cgsolutions.registration.domain.ChildSearchBean;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.propertyEditors.DatePropertyEditor;
import com.cgsolutions.registration.domain.propertyEditors.RoomPropertyEditor;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;

@Controller
@RequestMapping("/findChild.htm")
public class FindChildController {
	@Autowired
	private ChildManager childManager;
	@Autowired
	private RoomManager roomManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		model.addAttribute("children", childManager.searchForChildren(new ChildSearchBean()));
		model.addAttribute("searchBean", new ChildSearchBean());
		model.addAttribute("rooms", roomManager.findAll());
		
		return "findChildren";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String findChild(@ModelAttribute("searchBean") ChildSearchBean childSearchBean, Model model){
		model.addAttribute("children", childManager.searchForChildren(childSearchBean));
		model.addAttribute("searchBean", childSearchBean);
		model.addAttribute("rooms", roomManager.findAll());
		
		return "findChildren";
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Room.class, new RoomPropertyEditor(roomManager));
	}
}
