package com.cgsolutions.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.service.AttendanceManager;
import com.cgsolutions.security.utility.MyDateUtils;

@Controller
@RequestMapping("showHours.htm")
public class ShowHoursController {
	@Autowired
	private AttendanceManager attendanceManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		
		if(StringUtils.hasText(request.getParameter("dateFrom"))){
			Date date1 = MyDateUtils.getDateFromString(request.getParameter("dateFrom"), "dd MMM yyyy");
			Date date2 = MyDateUtils.getDateFromString(request.getParameter("dateTo"), "dd MMM yyyy");
			
			model.addAttribute("resultsMap", attendanceManager.getTotalHours(date1, date2));
			model.addAttribute("dateFrom", request.getParameter("dateFrom"));
			model.addAttribute("dateTo", request.getParameter("dateTo"));
		}
		
		return "showHours";
	}
}
