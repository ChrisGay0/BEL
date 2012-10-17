package com.cgsolutions.report;

import java.util.Date;
import java.util.List;

import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Room;

public class RegisterReportBean {
	private Room room;
	private List<Attendance> attendances;
	private Date day;
	
	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
