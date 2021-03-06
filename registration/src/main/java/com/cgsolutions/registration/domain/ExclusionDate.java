package com.cgsolutions.registration.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "exclusion_date")
public class ExclusionDate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Version
	private Date lastUpdate;
	private Date exclusionDate;
	private String reason;
	private int termId;
	@Transient
	private boolean selected;
	private boolean chargeable;
	
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
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isChargeable() {
		return chargeable;
	}
	public void setChargeable(boolean chargeable) {
		this.chargeable = chargeable;
	}
}
