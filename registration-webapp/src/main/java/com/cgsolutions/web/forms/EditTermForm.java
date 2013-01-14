package com.cgsolutions.web.forms;

import java.util.ArrayList;
import java.util.List;

import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Term;

public class EditTermForm {
	private List<Attendance> attendances;
	private Term term;
	private Child child;
	
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
}