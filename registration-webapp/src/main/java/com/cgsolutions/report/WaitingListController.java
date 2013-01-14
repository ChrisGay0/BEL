package com.cgsolutions.report;

import java.util.ArrayList;
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

public class WaitingListController extends MultiActionController {
	private RoomManager roomManager;
	private ChildManager childManager;
	
	public ModelAndView handleReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Room room = roomManager.find(Integer.parseInt(request.getParameter("roomId")));

		model.put("dataSource", childManager.findChildrenOnWaitingList(room.getId()));
		model.put("reportTitle", "Waiting List for " + room.getName() + " room");
		
		return new ModelAndView("WaitingListCompile", model);
	}

	public void setRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	public void setChildManager(ChildManager childManager) {
		this.childManager = childManager;
	}
}