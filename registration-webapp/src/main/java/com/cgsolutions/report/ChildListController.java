package com.cgsolutions.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;

public class ChildListController extends MultiActionController {
	private RoomManager roomManager;
	private ChildManager childManager;
	
	public ModelAndView handleReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Room> rooms = new ArrayList<Room>();
		for(String roomId: request.getParameter("rooms").split(",")){
			Room room = roomManager.find(Integer.parseInt(roomId));
			if(StringUtils.hasText(request.getParameter("allergiesOnly"))){
				room.setChildren(childManager.findActiveChildrenWithAllergiesForRoom(room.getId()));
			}
			else{
				room.setChildren(childManager.findActiveChildrenForRoom(room.getId()));
			}
			rooms.add(room);
		}
		
		model.put("reportTitle", "Current Children");
		model.put("dataSource", rooms);
		model.put("includeDays", request.getParameter("includeDays"));
		model.put("includeGuardians", request.getParameter("includeGuardians"));
		model.put("includeContacts", request.getParameter("includeContacts"));
		model.put("includeMedical", request.getParameter("includeMedical"));
		model.put("includeAddress", request.getParameter("includeAddress"));
		
		return new ModelAndView("ChildListCompile", model);
	}

	public ModelAndView handleFullReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Room> rooms = roomManager.findAllActive();
		
		for(Child child: rooms.get(0).getChildren()){
			child.getId();
		}
		model.put("dataSource", rooms);
		return new ModelAndView("FullChildListCompile", model);
	}
	
	public ModelAndView handleShortReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Room> rooms = roomManager.findAllActive();
		
		for(Room room: new ArrayList<Room>(rooms)){
			if(CollectionUtils.isEmpty(room.getChildren())){
				rooms.remove(room);
			}
		}
		model.put("dataSource", rooms);
		return new ModelAndView("ShortChildListCompile", model);
	}
	
	public void setRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	public void setChildManager(ChildManager childManager) {
		this.childManager = childManager;
	}
}