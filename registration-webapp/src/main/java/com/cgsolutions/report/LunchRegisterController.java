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

import com.cgsolutions.registration.service.AttendanceManager;
import com.cgsolutions.security.utility.MyDateUtils;

public class LunchRegisterController extends MultiActionController {
	private AttendanceManager attendanceManager;
	
	public ModelAndView printLunchRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<RegisterReportBean> datasource = new ArrayList<RegisterReportBean>();
		int daysToPrint = Integer.parseInt(request.getParameter("daysToPrint"));
		Date weekStart = MyDateUtils.getDateFromString(request.getParameter("day"), "dd MMM yyyy");
		for(int i = 0; i < daysToPrint; i++){
			RegisterReportBean bean = new RegisterReportBean();
			bean.setDay(MyDateUtils.incrementByDays(weekStart, i));
			bean.setAttendances(attendanceManager.findChildrenForLunch(bean.getDay()));
			if(!CollectionUtils.isEmpty(bean.getAttendances())){
				datasource.add(bean);
			}
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("dataSource", datasource);
		
		return new ModelAndView("LunchRegisterCompile", model);
	}

	public void setAttendanceManager(AttendanceManager attendanceManager) {
		this.attendanceManager = attendanceManager;
	}
}
