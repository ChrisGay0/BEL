package com.cgsolutions.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.MedicalInfo;
import com.cgsolutions.registration.service.ChildManager;

@Controller
@RequestMapping("/addMedicalInfo.htm")
@SessionAttributes("newInfo")
public class AddMedicalInfoController {
	@Autowired
	private ChildManager childManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		model.addAttribute("childId", request.getParameter("childId"));
		model.addAttribute("newInfo", new MedicalInfo());
		
		return "createMedicalInfo";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveChild(@ModelAttribute("newInfo")MedicalInfo medicalInfo, HttpServletRequest request){
		Child child = childManager.findChild(Integer.parseInt(request.getParameter("childId")));
		if(!CollectionUtils.isEmpty(child.getMedicalInfo())){
			child.setMedicalInfo(new ArrayList<MedicalInfo>());
		}
		medicalInfo.setChildId(child.getId());
		child.getMedicalInfo().add(medicalInfo);
		
		childManager.saveChild(child);
		
		return "redirect:editChild.htm?childId=" + child.getId();
	}
	
}
