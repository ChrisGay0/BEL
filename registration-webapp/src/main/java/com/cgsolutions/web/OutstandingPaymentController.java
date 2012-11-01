package com.cgsolutions.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.service.BillManager;
import com.cgsolutions.registration.service.ChildManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("outstandingPayments.htm")
public class OutstandingPaymentController {
	@Autowired
	private ChildManager childManager;
	@Autowired 
	private BillManager billManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		List<Child> children = childManager.findChildrenCurrentlyAttending();
		List<Child> outstanding = new ArrayList<Child>();
		
		if(!CollectionUtils.isEmpty(children)){
			for(Child child: children){
				child.setBills(billManager.findBillsForChild(child));
				if(Float.parseFloat(child.getCurrentBalance()) < 0){
					outstanding.add(child);
				}
			}
		}
		
		model.addAttribute("outstandingChildren", outstanding);
		
		return "outstandingPayments";
	}
}
