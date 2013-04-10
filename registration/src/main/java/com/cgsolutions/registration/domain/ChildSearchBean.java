package com.cgsolutions.registration.domain;

public class ChildSearchBean {
	private String firstName;
	private String surname;
	private Room room;
	private boolean includeLeft;
	private boolean onWaitingList;
	
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
	public boolean isIncludeLeft() {
		return includeLeft;
	}
	public void setIncludeLeft(boolean includeLeft) {
		this.includeLeft = includeLeft;
	}
	public boolean isOnWaitingList() {
		return onWaitingList;
	}
	public void setOnWaitingList(boolean onWaitingList) {
		this.onWaitingList = onWaitingList;
	}
}