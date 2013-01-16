package com.cgsolutions.web.forms;

import java.util.ArrayList;
import java.util.List;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Payment;

public class OutstandingPaymentsForm {
	private List<Child> children;
	private List<Payment> newPayments = new ArrayList<Payment>();
	
	public List<Child> getChildren() {
		return children;
	}
	public void setChildren(List<Child> children) {
		this.children = children;
		for(Child child: children){
			newPayments.add(new Payment());
		}
	}
	public List<Payment> getNewPayments() {
		return newPayments;
	}
	public void setNewPayments(List<Payment> newPayments) {
		this.newPayments = newPayments;
	}
}
