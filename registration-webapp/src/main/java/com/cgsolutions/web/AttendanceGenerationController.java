package com.cgsolutions.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.service.AttendanceManager;
import com.cgsolutions.registration.service.TermManager;

@Controller
@RequestMapping("/generateAttendances.htm")
public class AttendanceGenerationController {
	@Autowired
	private AttendanceManager attendanceManager;
	@Autowired
	private TermManager termManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String generateAttendances(HttpServletRequest request, Model model){
		attendanceManager.generateAttendancesForTerm(termManager.find(Integer.parseInt(request.getParameter("termId"))));
		
		return "redirect:viewAttendances.htm?termId=" + request.getParameter("termId");
	}
}
