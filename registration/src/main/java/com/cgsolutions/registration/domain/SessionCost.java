package com.cgsolutions.registration.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Target;

@Entity
@Table(name="session_cost")
public class SessionCost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int childAgeUnder;
	private Float cost;
	@ManyToOne
	private Room room;
	private Float lunchCost;
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChildAgeUnder() {
		return childAgeUnder;
	}
	public void setChildAgeUnder(int childAgeUnder) {
		this.childAgeUnder = childAgeUnder;
	}
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
	public Float getLunchCost() {
		return lunchCost;
	}
	public void setLunchCost(Float lunchCost) {
		this.lunchCost = lunchCost;
	}
}
