package com.cgsolutions.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgsolutions.registration.domain.Payment;
import com.cgsolutions.registration.domain.enums.PaymentType;
import com.cgsolutions.registration.service.PaymentManager;
import com.cgsolutions.security.utility.MyDateUtils;

@Controller
@RequestMapping("/findPayments.htm")
public class FindPaymentsController {
	@Autowired
	private PaymentManager paymentManager;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm(Model model, HttpServletRequest request){
		return "findPayments";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String findChild(Model model, HttpServletRequest request){
		Date dateFrom = MyDateUtils.getDateFromString(request.getParameter("dateFrom"), "dd MMM yyyy");
		Date dateTo = MyDateUtils.getDateFromString(request.getParameter("dateTo"), "dd MMM yyyy");
		
		List<Payment> payments = paymentManager.findPayments(dateFrom, dateTo);
		Map<String, List<Payment>> paymentMap = new HashMap<String, List<Payment>>();
		Map<String, Float> totals = new HashMap<String, Float>();
		
		if(!CollectionUtils.isEmpty(payments)){
			for(PaymentType paymentType: PaymentType.values()){
				List<Payment> paymentList = new ArrayList<Payment>();
				for(Payment payment: payments){
					if(payment.getPaymentType().toString().equals(paymentType.toString())){
						paymentList.add(payment);
					}
				}
				
				paymentMap.put(paymentType.toString(), paymentList);
				totals.put(paymentType.toString(), Payment.getTotal(paymentList));
			}
		}
		
		float total = 0;
		for(Float amount: totals.values()){
			total += amount;
		}
		model.addAttribute("paymentMap", paymentMap);
		model.addAttribute("paymentTypes", PaymentType.values());
		model.addAttribute("totalMap", totals);
		model.addAttribute("dateFrom", request.getParameter("dateFrom"));
		model.addAttribute("dateTo", request.getParameter("dateTo"));
		model.addAttribute("total", total);
		
		return "findPayments";
	}
}
