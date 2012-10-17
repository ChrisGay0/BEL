package com.cgsolutions.registration.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "child_bill")
public class ChildBill {
	public ChildBill(){
		
	}
	
	public ChildBill(int childId){
		this.childId = childId;
		this.attendances = new ArrayList<Attendance>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Version
	private Date lastUpdate;
	private int childId;
	@OneToMany(targetEntity=Attendance.class)
	@JoinColumn(name="billId")
	private List<Attendance> attendances;
	private float balancePaid;
	private float billAmount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public int getChildId() {
		return childId;
	}
	public void setChildId(int childId) {
		this.childId = childId;
	}
	public List<Attendance> getAttendances() {
		return attendances;
	}
	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}
	public float getBalancePaid() {
		return balancePaid;
	}
	public void setBalancePaid(float balancePaid) {
		this.balancePaid = balancePaid;
	}
	public float getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}
	
	public String getTermName(){
		return this.attendances.get(0).getTerm().getTermName();
	}
}