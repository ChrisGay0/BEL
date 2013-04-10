package com.cgsolutions.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Payment;
import com.cgsolutions.registration.domain.enums.PaymentType;
import com.cgsolutions.registration.service.BillManager;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.web.forms.ChildEditForm;
import com.cgsolutions.web.forms.OutstandingPaymentsForm;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("outstandingPayments.htm")
@SessionAttributes("formObject")
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
				if(Float.parseFloat(child.getCurrentBalance()) > 0){
					outstanding.add(child);
				}
				
			}
		}
		
		OutstandingPaymentsForm formObject = new OutstandingPaymentsForm();
		formObject.setChildren(outstanding);
		
		model.addAttribute("paymentList", PaymentType.values());
		model.addAttribute("formObject", formObject);
		
		return "outstandingPayments";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@ModelAttribute("formObject")OutstandingPaymentsForm formObject, HttpServletRequest request){
		List<Child> saveList = new ArrayList<Child>();
		for(int i = 0; i < formObject.getNewPayments().size(); i++){
			Payment newPayment = formObject.getNewPayments().get(i);
			Child child = childManager.findChild(formObject.getChildren().get(i).getId());
			child.setRegistrationFeePaid(formObject.getChildren().get(i).isRegistrationFeePaid());
			child.setDepositPaid(formObject.getChildren().get(i).getDepositPaid());
			if(newPayment.getAmount() != null){
				newPayment.setChild(child);
				newPayment.setDatePaid(new Date());
				child.getPayments().add(newPayment);
			}
			saveList.add(child);
		}
		
		childManager.saveChildren(saveList);
			
		return "redirect:outstandingPayments.htm";
	}
}
