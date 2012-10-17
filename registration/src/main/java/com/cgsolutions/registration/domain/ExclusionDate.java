package com.cgsolutions.registration.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "exclusion_date")
public class ExclusionDate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date exclusionDate;
	private int termId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getExclusionDate() {
		return exclusionDate;
	}
	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}
	public int getTermId() {
		return termId;
	}
	public void setTermId(int termId) {
		this.termId = termId;
	}
	
}
