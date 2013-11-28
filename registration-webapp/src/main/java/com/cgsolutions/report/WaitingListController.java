package com.cgsolutions.report;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;
import com.cgsolutions.registration.service.TermManager;

public class WaitingListController extends MultiActionController {
	private RoomManager roomManager;
	private ChildManager childManager;
	private TermManager termManager;
	
	public ModelAndView handleReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Integer roomId = null;
		if(StringUtils.hasText(request.getParameter("roomId"))){
			Room room = roomManager.find(Integer.parseInt(request.getParameter("roomId")));
			model.put("reportTitle", "Waiting List for " + room.getName() + " room");
			roomId = room.getId();
		}
		else{
			model.put("reportTitle", "Waiting List for all rooms");
		}
		Term term = null;
		if(StringUtils.hasText(request.getParameter("termId"))){
			term = termManager.find(Integer.parseInt(request.getParameter("termId")));
		}
		model.put("dataSource", childManager.findChildrenOnWaitingList(roomId, term));
		
		
		return new ModelAndView("WaitingListCompile", model);
	}

	public void setRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	public void setChildManager(ChildManager childManager) {
		this.childManager = childManager;
	}

	public void setTermManager(TermManager termManager) {
		this.termManager = termManager;
	}
}