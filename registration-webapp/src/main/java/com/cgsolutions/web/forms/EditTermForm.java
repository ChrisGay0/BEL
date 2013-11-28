package com.cgsolutions.web.forms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.AutoPopulatingList;

import com.cgsolutions.registration.domain.AdditionalSetting;
import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Term;

public class EditTermForm {
	private List<Attendance> attendances;
	private Term term;
	private Child child;
	private List<Attendance> newAttendances = new AutoPopulatingList<Attendance>(Attendance.class);
	
	public EditTermForm(){
		this.newAttendances.add(new Attendance());
	}
	public List<Attendance> getAttendances() {
		return attendances;
	}
	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	
	public List<Attendance> getSelectedAttendances(){
		List<Attendance> returnList = new ArrayList<Attendance>();
		
		for(Attendance attendance: this.attendances){
			if(attendance.isSelected()){
				returnList.add(attendance);
			}
		}
		
		return returnList;
	}
	public List<Attendance> getNewAttendances() {
		return newAttendances;
	}
	public void setNewAttendances(List<Attendance> newAttendances) {
		this.newAttendances = newAttendances;
	}
	public List<Attendance> getAttendancesToAdd(){
		List<Attendance> saveList = new ArrayList<Attendance>();
		for(Attendance attendance: this.newAttendances){
			if(attendance.getAttendanceDate() != null){
				attendance.setTerm(this.term);
				attendance.setChild(this.child);
				attendance.setSessionCost(attendance.getRoom().getSessionCostForAge(child.getChildsAge(term.getStartDateOfFullTerm())));
				attendance.setLunchCost(attendance.getRoom().getLunchCostForAge(child.getChildsAge(term.getStartDateOfFullTerm())));
				attendance.setFundedLunchesPerWeek(child.getFundedLunches());
				attendance.setFundedSessionsPerWeek(child.getFundedSessions());
				attendance.setWeeksInTerm((int)term.getWeeksInTerm());
				
				saveList.add(attendance);
			}
		}
		
		return saveList;
	}
}