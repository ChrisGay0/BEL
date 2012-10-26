package com.cgsolutions.web.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cgsolutions.registration.domain.Term;

public class TermEditForm {
	private Term term;
	private List<String> newExclusionDates = new ArrayList<String>();
	
	public TermEditForm(){
		for(int i = 0; i < 5; i++){
			this.newExclusionDates.add(null);
		}
	}
	
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public List<String> getNewExclusionDates() {
		return newExclusionDates;
	}
	public void setNewExclusionDates(List<String> newExclusionDates) {
		this.newExclusionDates = newExclusionDates;
	}
}