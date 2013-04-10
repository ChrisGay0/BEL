package com.cgsolutions.registration.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.util.CollectionUtils;

import com.cgsolutions.registration.domain.enums.PaymentType;

@Entity
@Table(name="payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Version
	private Date lastUpdate;
	@ManyToOne
	@JoinColumn(name="childId", nullable=true)
	private Child child;
	private Date datePaid;
	private Float amount;
	private String comments;
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	@Transient
	private boolean selected;
	
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
	
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}
	public Date getDatePaid() {
		return datePaid;
	}
	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public static float getTotal(List<Payment> payments){
		float total = 0;
		if(!CollectionUtils.isEmpty(payments)){
			for(Payment payment: payments){
				total += payment.getAmount();
			}
		}
		
		return total;
	}
}
