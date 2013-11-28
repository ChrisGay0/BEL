package com.cgsolutions.registration.domain;

public class ChildSearchBean {
	private String firstName;
	private String surname;
	private Room room;
	private Boolean includeLeft = false;
	private Boolean onWaitingList = false;
	private Boolean nonStarter = false;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public void setIncludeLeft(Boolean includeLeft) {
		this.includeLeft = includeLeft;
	}
	public void setOnWaitingList(Boolean onWaitingList) {
		this.onWaitingList = onWaitingList;
	}
	public Boolean getNonStarter() {
		return nonStarter;
	}
	public void setNonStarter(Boolean nonStarter) {
		this.nonStarter = nonStarter;
	}
	public Boolean getIncludeLeft() {
		return includeLeft;
	}
	public Boolean getOnWaitingList() {
		return onWaitingList;
	}
	
}