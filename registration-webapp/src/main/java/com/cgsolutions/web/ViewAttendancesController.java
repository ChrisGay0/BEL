package com.cgsolutions.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.service.AttendanceManager;

@Controller
@RequestMapping("viewAttendances.htm")
public class ViewAttendancesController {
	@Autowired
	private AttendanceManager attendanceManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showMenu(Model model, HttpServletRequest request){
		if(StringUtils.hasText(request.getParameter("termId"))){
			model.addAttribute("attendances", attendanceManager.findForTerm(Integer.parseInt(request.getParameter("termId"))));
		}

		return "viewAttendances";
	}
}
