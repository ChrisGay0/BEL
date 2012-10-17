package com.cgsolutions.registration.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.springframework.util.CollectionUtils;

import com.cgsolutions.security.utility.MyDateUtils;

@Entity
@Table(name = "term")
public class Term {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date lastUpdate;
	private String termName;
	private Date startDate;
	private Date endDate;
	private boolean lockTerm;
	@OneToMany(targetEntity=ExclusionDate.class, cascade=CascadeType.ALL)
	@JoinColumn(name="termId")
	private List<ExclusionDate> exclusionDates;
	@Transient
	private List<Date> newExclusionDates;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<ExclusionDate> getExclusionDates() {
		return exclusionDates;
	}
	public void setExclusionDates(List<ExclusionDate> exclusionDates) {
		this.exclusionDates = exclusionDates;
	}
	public List<Date> getNewExclusionDates() {
		return newExclusionDates;
	}
	public void setNewExclusionDates(List<Date> newExclusionDates) {
		this.newExclusionDates = newExclusionDates;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public boolean isLockTerm() {
		return lockTerm;
	}
	public void setLockTerm(boolean lockTerm) {
		this.lockTerm = lockTerm;
	}
	public void addExclusionDate(Date date){
		if(CollectionUtils.isEmpty(this.exclusionDates)){
			this.exclusionDates = new ArrayList<ExclusionDate>();
		}
		ExclusionDate exclusionDate = new ExclusionDate();
		exclusionDate.setExclusionDate(date);
		exclusionDate.setTermId(this.id);
		
		this.exclusionDates.add(exclusionDate);
	}
	
	public List<Date> getWeekdayTermDates(){
		Date proccessedDate = this.startDate;
		List<Date> datesInTerm = new ArrayList<Date>();
		
		while(this.endDate.after(proccessedDate)){
			if(MyDateUtils.isItAMonday(proccessedDate) || MyDateUtils.isItATuesday(proccessedDate) || MyDateUtils.isItAWednesday(proccessedDate) || MyDateUtils.isItAThursday(proccessedDate) || MyDateUtils.isItAFriday(proccessedDate)){
				datesInTerm.add(proccessedDate);
			}
			proccessedDate = MyDateUtils.incrementByDays(proccessedDate, 1);
		}
		
		if(MyDateUtils.isItAMonday(this.endDate) || MyDateUtils.isItATuesday(this.endDate) || MyDateUtils.isItAWednesday(this.endDate) || MyDateUtils.isItAThursday(this.endDate) || MyDateUtils.isItAFriday(this.endDate)){
			datesInTerm.add(this.endDate);
		}
		
		return datesInTerm;
	}
	
	public boolean isDateInExclusionsList(Date date){
		if(!CollectionUtils.isEmpty(this.exclusionDates)){
			for(ExclusionDate exclusionDate: this.exclusionDates){
				if(MyDateUtils.getStringFromDate(date, "dd MMM yyyy").equals(MyDateUtils.getStringFromDate(exclusionDate.getExclusionDate(), "dd MMM yyyy"))){
					return true;
				}
			}
		}
		
		return false;
		
	}
}