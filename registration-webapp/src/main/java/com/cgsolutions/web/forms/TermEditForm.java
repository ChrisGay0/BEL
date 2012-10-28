package com.cgsolutions.web.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.AutoPopulatingList;

import com.cgsolutions.registration.domain.ExclusionDate;
import com.cgsolutions.registration.domain.MedicalInfo;
import com.cgsolutions.registration.domain.Term;

public class TermEditForm {
	private Term term;
	private List<ExclusionDate> newDates = new AutoPopulatingList<ExclusionDate>(ExclusionDate.class);
	
	public TermEditForm(){
		newDates.add(new ExclusionDate());
	}
	
	public List<ExclusionDate> getAddedDates(){
		List<ExclusionDate> returnList = new ArrayList<ExclusionDate>();
		for(ExclusionDate date: this.newDates){
			if(date.getExclusionDate() != null){
				date.setTermId(term.getId());
				returnList.add(date);
			}
		}
		
		return returnList;
	}
	
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}

	public List<ExclusionDate> getNewDates() {
		return newDates;
	}

	public void setNewDates(List<ExclusionDate> newDates) {
		this.newDates = newDates;
	}
}