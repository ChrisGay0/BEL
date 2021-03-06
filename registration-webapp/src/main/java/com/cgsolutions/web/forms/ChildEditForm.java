package com.cgsolutions.web.forms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.AutoPopulatingList;
import org.springframework.util.StringUtils;

import com.cgsolutions.registration.domain.AdditionalSetting;
import com.cgsolutions.registration.domain.Authorisation;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Contact;
import com.cgsolutions.registration.domain.Intolerance;
import com.cgsolutions.registration.domain.MedicalInfo;
import com.cgsolutions.registration.domain.Payment;

public class ChildEditForm {
	private Child child;
	private List<Intolerance> newIntolerances = new AutoPopulatingList<Intolerance>(Intolerance.class);
	private List<Authorisation> newAuthorisations = new AutoPopulatingList<Authorisation>(Authorisation.class);
	private List<Contact> newContacts = new AutoPopulatingList<Contact>(Contact.class);
	private List<AdditionalSetting> newSettings = new AutoPopulatingList<AdditionalSetting>(AdditionalSetting.class);
	private List<MedicalInfo> newMedicalInfos = new AutoPopulatingList<MedicalInfo>(MedicalInfo.class);
	private List<Payment> newPayments= new AutoPopulatingList<Payment>(Payment.class);
	
	public ChildEditForm(){
		newIntolerances.add(new Intolerance());
		newAuthorisations.add(new Authorisation());
		newContacts.add(new Contact());
		newSettings.add(new AdditionalSetting());
		newMedicalInfos.add(new MedicalInfo());
		newPayments.add(new Payment());
	}

	public Child getChild() {
		return child;
	}
	
	public Child getChildWithNewData(){
		this.child.getIntolerances().addAll(getAddedIntolerances());
		this.child.getAuthorisations().addAll(getAddedAuthorisations());
		this.child.getContacts().addAll(getAddedContacts());
		this.child.getAdditionalSettings().addAll(getAddedSettings());
		this.child.getMedicalInfo().addAll(getAddedMedicalInfos());
		this.child.getPayments().addAll(getAddedPayments());
		
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
	
	private List<Payment> getAddedPayments(){
		List<Payment> returnList = new ArrayList<Payment>();
		for(Payment payment: this.newPayments){
			if(payment.getAmount() != null){
				payment.setChildId(this.child.getId());
				returnList.add(payment);
			}
		}
		
		return returnList;
	}
	
	private List<MedicalInfo> getAddedMedicalInfos(){
		List<MedicalInfo> returnList = new ArrayList<MedicalInfo>();
		for(MedicalInfo medicalInfo: this.newMedicalInfos){
			if(StringUtils.hasText(medicalInfo.getMedicalCondition())){
				medicalInfo.setChildId(this.child.getId());
				returnList.add(medicalInfo);
			}
		}
		
		return returnList;
	}
	
	private List<AdditionalSetting> getAddedSettings(){
		List<AdditionalSetting> returnList = new ArrayList<AdditionalSetting>();
		for(AdditionalSetting setting: this.newSettings){
			if(StringUtils.hasText(setting.getName())){
				setting.setChildId(this.child.getId());
				returnList.add(setting);
			}
		}
		
		return returnList;
	}
	
	private List<Authorisation> getAddedAuthorisations(){
		List<Authorisation> returnList = new ArrayList<Authorisation>();
		for(Authorisation authorisation: this.newAuthorisations){
			if(StringUtils.hasText(authorisation.getActivity())){
				authorisation.setChildId(this.child.getId());
				returnList.add(authorisation);
			}
		}
		
		return returnList;
	}
	
	private List<Contact> getAddedContacts(){
		List<Contact> returnList = new ArrayList<Contact>();
		for(Contact contact: this.newContacts){
			if(StringUtils.hasText(contact.getFirstName())){
				contact.setChildId(this.child.getId());
				returnList.add(contact);
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

	public List<Authorisation> getNewAuthorisations() {
		return newAuthorisations;
	}

	public void setNewAuthorisations(List<Authorisation> newAuthorisations) {
		this.newAuthorisations = newAuthorisations;
	}

	public List<Contact> getNewContacts() {
		return newContacts;
	}

	public List<MedicalInfo> getNewMedicalInfos() {
		return newMedicalInfos;
	}

	public void setNewMedicalInfos(List<MedicalInfo> newMedicalInfos) {
		this.newMedicalInfos = newMedicalInfos;
	}

	public List<AdditionalSetting> getNewSettings() {
		return newSettings;
	}

	public void setNewSettings(List<AdditionalSetting> newSettings) {
		this.newSettings = newSettings;
	}

	public void setNewContacts(List<Contact> newContacts) {
		this.newContacts = newContacts;
	}

	public List<Payment> getNewPayments() {
		return newPayments;
	}

	public void setNewPayments(List<Payment> newPayments) {
		this.newPayments = newPayments;
	}
}