package com.cgsolutions.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Guardian;
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.domain.propertyEditors.DatePropertyEditor;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.TermManager;

@Controller
@RequestMapping("/addGuardian.htm")
@SessionAttributes("newGuardian")
public class AddGuardianController {
	@Autowired
	private ChildManager childManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		model.addAttribute("childId", request.getParameter("childId"));
		model.addAttribute("newGuardian", new Guardian());
		
		return "addGuardian";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveTermWithDates(@ModelAttribute("newGuardian")Guardian guardian, HttpServletRequest request){
		Child child = childManager.findChild(Integer.parseInt(request.getParameter("childId")));
		if(CollectionUtils.isEmpty(child.getGuardians())){
			child.setGuardians(new ArrayList<Guardian>());
		}
		guardian.setChildId(child.getId());
		child.getGuardians().add(guardian);
		
		childManager.saveChild(child);
		
		return "redirect:editChild.htm?childId=" + child.getId();
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
	}
}
