package com.cgsolutions.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.staticmock.MockStaticEntityMethods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;
import com.cgsolutions.web.forms.BulkRoomChangeForm;

@Controller
@RequestMapping("/bulkRoomChange.htm")
@SessionAttributes("formObject")
public class BulkRoomChange {
	@Autowired
	private ChildManager childManager;
	@Autowired
	private RoomManager roomManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		model.addAttribute("rooms", roomManager.findAll());
		model.addAttribute("childrenMoved", request.getParameter("childrenMoved"));
		
		return "bulkRoomChange";
	}
	
	@RequestMapping(method=RequestMethod.GET, params="action=showChildren")
	public String showChildren(Model model, HttpServletRequest request){
		model.addAttribute("rooms", roomManager.findAll());
		BulkRoomChangeForm form = new BulkRoomChangeForm();
		form.setChildren(childManager.findActiveChildrenForRoom(Integer.parseInt(request.getParameter("roomId"))));
		form.selectAllChildren();
		model.addAttribute("roomId", request.getParameter("roomId"));
		
		model.addAttribute("formObject", form);
		
		return "bulkRoomChange";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String bulkChange(SessionStatus status, @ModelAttribute("formObject")BulkRoomChangeForm form, HttpServletRequest request){
		Room newRoom = roomManager.find(Integer.parseInt(request.getParameter("newRoomId")));
		int moved = childManager.bulkRoomChange(form.getChildren(), newRoom);
		
		status.setComplete();
		return "redirect:bulkRoomChange.htm?childrenMoved=" + moved;
	}
}
