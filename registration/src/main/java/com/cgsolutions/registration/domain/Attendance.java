package com.cgsolutions.registration.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.cgsolutions.registration.domain.enums.TypeOfAttendance;

@Entity
@Table(name = "attendance")
public class Attendance {
	public Attendance(){
		
	}
	
	public Attendance(Term term, Child child, Room room, TypeOfAttendance typeOfAttendance, Date attendanceDate, ChildBill bill){
		this.term = term;
		this.child = child;
		this.room = room;
		this.typeOfAttendance = typeOfAttendance;
		this.attendanceDate = attendanceDate;
		this.sessionCost = room.getSessionCostForAge(child.getChildsAge());
		this.lunchCost = room.getLunchCostForAge(child.getChildsAge());
		this.bill = bill;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Version
	private Date lastUpdate;
	@ManyToOne
	@JoinColumn(name="childId")
	private Child child;
	@ManyToOne
	@JoinColumn(name="termId")
	private Term term;
	@ManyToOne
	@JoinColumn(name="roomId")
	private Room room;
	private Date attendanceDate;
	@Enumerated(EnumType.STRING)
	private TypeOfAttendance typeOfAttendance;
	private Float sessionCost;
	private Float lunchCost;
	@ManyToOne(targetEntity=ChildBill.class)
	@JoinColumn(name="billId")
	private ChildBill bill;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public Float getSessionCost() {
		return sessionCost;
	}

	public void setSessionCost(Float sessionCost) {
		this.sessionCost = sessionCost;
	}

	public Float getLunchCost() {
		return lunchCost;
	}

	public void setLunchCost(Float lunchCost) {
		this.lunchCost = lunchCost;
	}

	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public TypeOfAttendance getTypeOfAttendance() {
		return typeOfAttendance;
	}
	public void setTypeOfAttendance(TypeOfAttendance typeOfAttendance) {
		this.typeOfAttendance = typeOfAttendance;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public ChildBill getBill() {
		return bill;
	}

	public void setBill(ChildBill bill) {
		this.bill = bill;
	}
}