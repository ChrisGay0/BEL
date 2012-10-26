package com.cgsolutions.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.SessionCost;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;

@Controller
@RequestMapping("/createRoom.htm")
public class CreateRoomController {
	@Autowired
	private RoomManager roomManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model){
		Room room = new Room();
		addBlankSessionCosts(room);
		
		model.addAttribute("newRoom", room);
		return "createRoom";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createRoom(@ModelAttribute("newRoom")Room room){
		addRoomToCosts(room);
		roomManager.save(room);
		
		return "redirect:editRoom.htm?roomId=" + room.getId();
	}
	
	private void addBlankSessionCosts(Room room){
		List<SessionCost> costs = new ArrayList<SessionCost>();
		for(int i = 0; i < 5; i++){
			SessionCost sessionCost = new SessionCost();
			if(i == 0){
				sessionCost.setChildAgeUnder(5);
			}
			costs.add(sessionCost);
		}
		
		room.setCosts(costs);
	}
	
	private void addRoomToCosts(Room room){
		for(SessionCost cost: room.getCosts()){
			cost.setRoom(room);
		}
	}
}
