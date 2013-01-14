package com.cgsolutions.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;

@Controller
@RequestMapping("/waitingList.htm")
public class ChildWaitingListController {
	@Autowired
	private RoomManager roomManager;
	@Autowired
	private ChildManager childManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model){
		Map<String, List<Child>> rooms = new HashMap<String, List<Child>>();
		for(Room room: roomManager.findAllActive()){
			rooms.put(room.getName(), childManager.findChildrenOnWaitingList(room.getId()));
		}
		
		model.addAttribute("rooms", roomManager.findAllActive());
		model.addAttribute("roomMap", rooms);
		
		return "waitingList";
	}
}
