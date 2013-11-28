package com.cgsolutions.report;

import com.cgsolutions.registration.domain.Child;

public class InvoiceBean {
	private Child child;
	private int totalSessions;
	private float sessionCost;
	private int totalLunches;
	private float lunchCost;
	private float currentBalance;
	
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	public int getTotalSessions() {
		return totalSessions;
	}
	public void setTotalSessions(int totalSessions) {
		this.totalSessions = totalSessions;
	}
	public float getSessionCost() {
		return sessionCost;
	}
	public void setSessionCost(float sessionCost) {
		this.sessionCost = sessionCost;
	}
	public int getTotalLunches() {
		return totalLunches;
	}
	public void setTotalLunches(int totalLunches) {
		this.totalLunches = totalLunches;
	}
	public float getLunchCost() {
		return lunchCost;
	}
	public void setLunchCost(float lunchCost) {
		this.lunchCost = lunchCost;
	}
	public float getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}
	public float getTotalSessionCost(){
		return this.sessionCost * this.totalSessions;
	}
	public float getTotalLunchCost(){
		return this.lunchCost * this.totalLunches;
	}
	public float getTotalCost(){
		float total = this.currentBalance + getTotalLunchCost() + getTotalSessionCost();
		if(!child.isRegistrationFeePaid()){
			total += child.getRegistrationFee();
		}
		if(child.getDepositPaid() == null || child.getDepositPaid() < child.getDepositRequired()){
			if(child.getDepositPaid() == null){
				total += child.getDepositRequired();
			}
			else{
				total += (child.getDepositRequired() - child.getDepositPaid());
			}
		}
		if(child.getDepositRefunded() != null){
			total -= child.getDepositRefunded();
		}
		
		return total;
	}
}