package com.cgsolutions.registration.domain;

public class TermBill {
	private Term term;
	private Room room;
	private int sessions;
	private int lunches;
	private float lunchesCost;
	private float sessionsCost;

	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public int getSessions() {
		return sessions;
	}
	public void setSessions(int sessions) {
		this.sessions = sessions;
	}
	public int getLunches() {
		return lunches;
	}
	public void setLunches(int lunches) {
		this.lunches = lunches;
	}
	public float getLunchesCost() {
		return lunchesCost;
	}
	public void setLunchesCost(float lunchesCost) {
		this.lunchesCost = lunchesCost;
	}
	public float getSessionsCost() {
		return sessionsCost;
	}
	public void setSessionsCost(float sessionsCost) {
		this.sessionsCost = sessionsCost;
	}
	public float getTotalLunchesCost(){
		return this.getLunchesCost() * this.lunches;
	}
	public float getTotalSessionsCost(){
		return this.getSessionsCost() * this.sessions;
	}
	public float getTotalCost(){
		return getTotalSessionsCost() + getTotalLunchesCost();
	}
}
