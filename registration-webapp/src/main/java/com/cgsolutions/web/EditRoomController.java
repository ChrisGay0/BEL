package com.cgsolutions.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.service.RoomManager;

@Controller
@RequestMapping("/editRoom.htm")
@SessionAttributes("room")
public class EditRoomController {
	@Autowired
	private RoomManager roomManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		model.addAttribute("room", roomManager.find(Integer.parseInt(request.getParameter("roomId"))));
		return "editRoom";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveRoom(@ModelAttribute("room")Room room){
		roomManager.save(room);
		
		return "redirect:editRoom.htm?roomId=" + room.getId();
	}
}
