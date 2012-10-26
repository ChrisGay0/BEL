package com.cgsolutions.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.service.AttendanceManager;
import com.cgsolutions.registration.service.ChildManager;

@Controller
@RequestMapping("viewAttendances.htm")
public class ViewAttendancesController {
	@Autowired
	private AttendanceManager attendanceManager;
	@Autowired
	private ChildManager childManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showAttendances(Model model, HttpServletRequest request){
		if(StringUtils.hasText(request.getParameter("termId"))){
			model.addAttribute("attendances", attendanceManager.findForTerm(Integer.parseInt(request.getParameter("termId"))));
		}
		if(StringUtils.hasText(request.getParameter("childId"))){
			Child child = childManager.findChild(Integer.parseInt(request.getParameter("childId")));
			model.addAttribute("attendances", attendanceManager.findFutureAttendancesForChild(child));
		}
		
		model.addAttribute("hideHeader", request.getParameter("hideHeader"));

		return "viewAttendances";
	}
}
