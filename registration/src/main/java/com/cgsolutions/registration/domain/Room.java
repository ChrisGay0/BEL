package com.cgsolutions.registration.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "room")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Version
	private Date lastUpdate;
	private String name;
	@OneToMany(targetEntity=Child.class)
	@JoinColumn(name="roomId")
	private List<Child> children;
	private boolean active = true;
	@OneToMany(mappedBy="room", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<SessionCost> costs;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Child> getChildren() {
		return children;
	}
	public void setChildren(List<Child> children) {
		this.children = children;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public void addChild(Child child){
		if(CollectionUtils.isEmpty(this.children)){
			this.children = new ArrayList<Child>();
		}
		
		this.children.add(child);
	}
	public List<SessionCost> getCosts() {
		return costs;
	}
	public void setCosts(List<SessionCost> costs) {
		this.costs = costs;
	}
	public Float getSessionCostForAge(int age){
		for(SessionCost sessionCost: this.costs){
			if(age < sessionCost.getChildAgeUnder()){
				return sessionCost.getCost();
			}
		}
		
		throw new IllegalStateException("No session costs for a child aged " + age + " for room " + this.getName());
	}
	
	public Float getLunchCostForAge(int age){
		for(SessionCost sessionCost: this.costs){
			if(age < sessionCost.getChildAgeUnder()){
				return sessionCost.getLunchCost();
			}
		}
		
		throw new IllegalStateException("No lunch costs for a child aged " + age + " for room " + this.getName());
	}
}
