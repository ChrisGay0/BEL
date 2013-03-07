package com.cgsolutions.registration.domain;

import org.springframework.util.StringUtils;

import com.cgsolutions.security.utility.MyDateUtils;

public class WelcomeLetter {
	private School school;
	
	public WelcomeLetter(School school){
		this.school = school;
	}
	
	public String getSectionOneText(Child child){
		return"I am pleased to inform you that " + child.getFirstName() + " has been allocated the following sessions in the " + child.getRoom().getName() +
				" Room at Burwell Early Learners term time only commencing on <b>" + MyDateUtils.getStringFromDate(child.getStartDate(), "EEEEE dd MMMMM yyyy") + "</b>. " + 
				"On your child's first day please arrive at 9.15am, then 9.00am thereafter and for the afternoon sessions 12.45pm, then 12.30pm thereafter.";
	}
	
	public String getSectionTwoText(Child child){
		return "If you wish to come for a 'Stay and Play' session before your child starts please contact the office to make a mutually convenient date and time. " +
				"You and your child can stay for approximately 1 hour so you can familiarise yourselves with the staff and room. Please bring a healthy snack for your child to eat during this time. " +
				"<br/>You will be invoiced on your childs first day at Burwell Early Learners (BEL) for the fees plus a £" + this.school.getDepositAmount() + " deposit which is refunded when your child leaves BEL " +
				"and a £" + this.school.getRegistrationFee() + " non-refundable registration fee, if not previously paid.";
	}
	
	public String getSectionThreeText(Child child){
		return "Please bring with you:<br>" +
				"1. A spare nappy in a clearly named bag (if applicable). Also a change of clothes. If wet, please provide wellington boots as the outside can become muddy. These items are to be taken home at the end of each session. All items should be named<br>" +
				"2. Completed information sheets.<br>" +
				"3. Childs information booklet.";
	}
	
	public String getSectionFourText(Child child){
		return "";
	}
	
	public String getSectionFiveText(Child child){
		return "For more information please refer to the enclosed information booklet or contact the ofiice on 01638 744065 from 9.00am - 12.30pm Monday, 9.00am - 3.30pm Tuesday to Friday.";
	}
	
	public String getSectionSixText(Child child){
		return "<b>IF YOU DO NOT WISH TO TAKE UP THIS PLACE PLEASE CONTACT ME IMMEDIATELY</b>";
	}
	
	public String getFooter(){
		String footer = "";
		if(StringUtils.hasText(school.getAddressLine1())){
			footer += school.getAddressLine1();
		}
		if(StringUtils.hasText(school.getAddressLine2())){
			footer += ", " + school.getAddressLine2();
		}
		if(StringUtils.hasText(school.getAddressLine3())){
			footer += ", " + school.getAddressLine3();
		}
		if(StringUtils.hasText(school.getCounty())){
			footer += ", " + school.getCounty();
		}
		if(StringUtils.hasText(school.getPostCode())){
			footer += " " + school.getPostCode() + ".";
		}
		 
		footer += "\nTel: " + school.getContactNumber();
		if(school.getEmail() != null){
			footer += "    Email: " + school.getEmail();
		}
		
		if(StringUtils.hasText(school.getCharityNumber())){
			footer += "\nCharity Registration Number " + school.getCharityNumber();
		}
		
		return footer;
	}
}
