package com.cgsolutions.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.service.AttendanceManager;
import com.cgsolutions.registration.service.RoomManager;
import com.cgsolutions.security.utility.MyDateUtils;

public class RegisterController extends MultiActionController {
	private RoomManager roomManager;
	private AttendanceManager attendanceManager;
	
	public ModelAndView printRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<RegisterReportBean> datasource = new ArrayList<RegisterReportBean>();
		int daysToPrint = Integer.parseInt(request.getParameter("daysToPrint"));
		Date weekStart = MyDateUtils.getDateFromString(request.getParameter("day"), "dd MMM yyyy");
		for(int i = 0; i < daysToPrint; i++){
			for(String roomId : request.getParameter("roomIds").split(",")){
				RegisterReportBean bean = new RegisterReportBean();
				bean.setRoom(roomManager.find(Integer.parseInt(roomId)));
				bean.setDay(MyDateUtils.incrementByDays(weekStart, i));
				bean.setAttendances(attendanceManager.findAttendancesForDayAndRoom(bean.getRoom().getId(), bean.getDay()));
				if(!CollectionUtils.isEmpty(bean.getAttendances())){
					datasource.add(bean);
				}
			}
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("dataSource", datasource);
		
		return new ModelAndView("RegisterCompile", model);
	}

	public void setRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	public void setAttendanceManager(AttendanceManager attendanceManager) {
		this.attendanceManager = attendanceManager;
	}
}