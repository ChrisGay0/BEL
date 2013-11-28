package com.cgsolutions.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;
import com.cgsolutions.registration.service.TermManager;
import com.cgsolutions.security.utility.MyDateUtils;

@Controller
@RequestMapping("/waitingList.htm")
public class ChildWaitingListController {
	@Autowired
	private RoomManager roomManager;
	@Autowired
	private ChildManager childManager;
	@Autowired
	private TermManager termManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		Map<String, List<Child>> rooms = new HashMap<String, List<Child>>();
		for(Room room: roomManager.findAllActive()){
			Term term = null;
			if(StringUtils.hasText(request.getParameter("termId"))){
				term = termManager.find(Integer.parseInt(request.getParameter("termId")));
			}
			rooms.put(room.getName(), childManager.findChildrenOnWaitingList(room.getId(), term));
		}
		
		model.addAttribute("rooms", roomManager.findAllActive());
		model.addAttribute("roomMap", rooms);
		model.addAttribute("terms", termManager.findTermsSince(MyDateUtils.decrementByYears(new Date(), 2)));
		model.addAttribute("termId", request.getParameter("termId"));
		
		return "waitingList";
	}
}
