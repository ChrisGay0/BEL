package com.cgsolutions.web.forms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.AutoPopulatingList;
import org.springframework.util.StringUtils;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Intolerance;

public class ChildEditForm {
	private Child child;
	private List<Intolerance> newIntolerances = new AutoPopulatingList<Intolerance>(Intolerance.class);
	
	public ChildEditForm(){
		newIntolerances.add(new Intolerance());
	}

	public Child getChild() {
		return child;
	}
	
	public Child getChildWithNewData(){
		this.child.getIntolerances().addAll(getAddedIntolerances());
		return child;
	}
	
	private List<Intolerance> getAddedIntolerances(){
		List<Intolerance> returnList = new ArrayList<Intolerance>();
		for(Intolerance intolerance: this.newIntolerances){
			if(StringUtils.hasText(intolerance.getIntolerance())){
				intolerance.setChildId(this.child.getId());
				returnList.add(intolerance);
			}
		}
		
		return returnList;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	public void setNewIntolerances(List<Intolerance> newIntolerances) {
		this.newIntolerances = newIntolerances;
	}
	public List<Intolerance> getNewIntolerances() {
		return newIntolerances;
	}
}