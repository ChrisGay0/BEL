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
import javax.persistence.Transient;
import javax.persistence.Version;

import com.cgsolutions.registration.domain.enums.TypeOfAttendance;

@Entity
@Table(name = "attendance")
public class Attendance {
	public Attendance(){
		
	}
	
	public Attendance(Term term, Child child, Room room, TypeOfAttendance typeOfAttendance, Date attendanceDate){
		this.term = term;
		this.child = child;
		this.room = room;
		this.typeOfAttendance = typeOfAttendance;
		this.attendanceDate = attendanceDate;
		this.sessionCost = room.getSessionCostForAge(child.getChildsAge(term.getStartDateOfFullTerm()));
		this.lunchCost = room.getLunchCostForAge(child.getChildsAge(term.getStartDateOfFullTerm()));
		this.fundedLunchesPerWeek = child.getFundedLunches();
		this.fundedSessionsPerWeek = child.getFundedSessions();
		this.weeksInTerm = (int)term.getWeeksInTerm();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Version
	private Date lastUpdate;
	@ManyToOne
	@JoinColumn(name="childId", nullable=true)
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
	private int fundedSessionsPerWeek;
	private int fundedLunchesPerWeek;
	private int weeksInTerm;
	private boolean chargeableExclusionDate;
	@Transient
	private boolean selected;
	
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

	public boolean isChargeableExclusionDate() {
		return chargeableExclusionDate;
	}

	public void setChargeableExclusionDate(boolean chargeableExclusionDate) {
		this.chargeableExclusionDate = chargeableExclusionDate;
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

	public int getFundedSessionsPerWeek() {
		return fundedSessionsPerWeek;
	}

	public void setFundedSessionsPerWeek(int fundedSessionsPerWeek) {
		this.fundedSessionsPerWeek = fundedSessionsPerWeek;
	}

	public int getFundedLunchesPerWeek() {
		return fundedLunchesPerWeek;
	}

	public void setFundedLunchesPerWeek(int fundedLunchesPerWeek) {
		this.fundedLunchesPerWeek = fundedLunchesPerWeek;
	}

	public int getWeeksInTerm() {
		return weeksInTerm;
	}

	public void setWeeksInTerm(int weeksInTerm) {
		this.weeksInTerm = weeksInTerm;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public float getBillAmount(){
		float billAmount = 0;
		if(this.getTypeOfAttendance().equals(TypeOfAttendance.FULL)){
			billAmount += this.getRoom().getSessionCostForAge(this.getChild().getChildsAge(term.getStartDateOfFullTerm())) * 2;
			billAmount += this.getRoom().getLunchCostForAge(this.getChild().getChildsAge(term.getStartDateOfFullTerm()));
		}
		else if(this.getTypeOfAttendance().equals(TypeOfAttendance.MORNING) || this.getTypeOfAttendance().equals(TypeOfAttendance.AFTERNOON)){
			billAmount += this.getRoom().getSessionCostForAge(this.getChild().getChildsAge(term.getStartDateOfFullTerm()));
		}
		else if (this.getTypeOfAttendance().equals(TypeOfAttendance.MORNINGWITHLUNCH) || this.getTypeOfAttendance().equals(TypeOfAttendance.AFTERNOONWITHLUNCH)){
			billAmount += this.getRoom().getSessionCostForAge(this.getChild().getChildsAge(term.getStartDateOfFullTerm()));
			billAmount += this.getRoom().getLunchCostForAge(this.getChild().getChildsAge(term.getStartDateOfFullTerm()));
		}
		
		return billAmount;
	}
}