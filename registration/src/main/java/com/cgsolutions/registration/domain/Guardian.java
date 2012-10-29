package com.cgsolutions.registration.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="guardian")
public class Guardian {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column(nullable=true)
	private int childId;
	private String firstName;
	private String surname;
	private String title;
	private Date dateOfBirth;
	private String relation;
	private String contactNumber1;
	private String contactNumber2;
	private boolean allowedToCollect;
	private boolean sameAddressAsChild;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String county;
	private String postCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChildId() {
		return childId;
	}
	public void setChildId(int childId) {
		this.childId = childId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getContactNumber1() {
		return contactNumber1;
	}
	public void setContactNumber1(String contactNumber1) {
		this.contactNumber1 = contactNumber1;
	}
	public String getContactNumber2() {
		return contactNumber2;
	}
	public void setContactNumber2(String contactNumber2) {
		this.contactNumber2 = contactNumber2;
	}
	public boolean isAllowedToCollect() {
		return allowedToCollect;
	}
	public void setAllowedToCollect(boolean allowedToCollect) {
		this.allowedToCollect = allowedToCollect;
	}
	public boolean isSameAddressAsChild() {
		return sameAddressAsChild;
	}
	public void setSameAddressAsChild(boolean sameAddressAsChild) {
		this.sameAddressAsChild = sameAddressAsChild;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}