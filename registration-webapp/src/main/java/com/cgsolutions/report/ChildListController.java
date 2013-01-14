package com.cgsolutions.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;

public class ChildListController extends MultiActionController {
	private RoomManager roomManager;
	private ChildManager childManager;
	
	public ModelAndView handleReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Room> rooms = roomManager.findAllActive();
		for(Room room: rooms){
			room.setChildren(childManager.findActiveChildrenForRoom(room.getId()));
		}
		model.put("reportTitle", "Current Children");
		model.put("dataSource", rooms);
		
		return new ModelAndView("ChildListCompile", model);
	}

	public void setRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	public void setChildManager(ChildManager childManager) {
		this.childManager = childManager;
	}
}