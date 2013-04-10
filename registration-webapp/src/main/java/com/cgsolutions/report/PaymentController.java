package com.cgsolutions.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cgsolutions.registration.domain.Payment;
import com.cgsolutions.registration.service.PaymentManager;
import com.cgsolutions.security.utility.MyDateUtils;

public class PaymentController  extends MultiActionController {
	private PaymentManager paymentManager;
	
	public ModelAndView printReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date dateFrom = MyDateUtils.getDateFromString(request.getParameter("dateFrom"), "dd MMM yyyy");
		Date dateTo = MyDateUtils.getDateFromString(request.getParameter("dateTo"), "dd MMM yyyy");
		
		List<Payment> payments = paymentManager.findPayments(dateFrom, dateTo);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("dataSource", payments);
		model.put("dateFrom", dateFrom);
		model.put("dateTo", dateTo);
		model.put("total", Payment.getTotal(payments));
		model.put("domain", request.getLocalAddr());
		
		return new ModelAndView("PaymentCompile", model);
	}

	public void setPaymentManager(PaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}
}
