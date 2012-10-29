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
@Table(name="medical_info")
public class MedicalInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String medicalCondition;
	private String notes;
	@Version
	private Date lastUpdate;
	@Column(nullable=true)
	private int childId;
	@Transient
	private boolean selected;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMedicalCondition() {
		return medicalCondition;
	}
	public void setMedicalCondition(String medicalCondition) {
		this.medicalCondition = medicalCondition;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public int getChildId() {
		return childId;
	}
	public void setChildId(int childId) {
		this.childId = childId;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
