package com.cgsolutions.registration.domain;

import com.cgsolutions.security.utility.MyDateUtils;

public class WelcomeLetter {
	public String getSectionOneText(Child child){
		return"I am pleased to inform you that " + child.getFirstName() + " has been allocated the following sessions in the " + child.getRoom().getName() +
				" Room at Burwell Early Learners term time only commencing on <strong>" + MyDateUtils.getStringFromDate(child.getStartDate(), "EEEEE dd MMMMM yyyy") + "</strong>. " + 
				"On your child's first day please arrive at 9.15am, then 9.00am thereafter and for the afternoon sessions 12.45pm, then 12.30pm thereafter.";
	}
	
	public String getSectionTwoText(Child child){
		return "If you wish to come for a 'Stay and Play' session before your child starts please contact the office to make a mutually convenient date and time. " +
				"You and your child can stay for approximately 1 hour so you can familiarise yourselves with the staff and room. Please bring a healthy snack for your child to eat during this time. " +
				"<br/>You will be invoiced on your childs first day at Burwell Early Learners (BEL) for the fees plus a £40 deposit which is refunded when you are eligible for " +
				"government funding and a £10 non-refundable registration fee, if not previously paid.";
	}
	
	public String getSectionThreeText(Child child){
		return "Please bring with you:<br>" +
				"1. A spare nappy in a clearly named bag (if applicable). Also a change of clothes. If wet, please provide wellington boots as the outside can become muddy. These items are to be taken home at the end of each session.<br>" +
				"2. Completed information sheets.<br>" +
				"3. All About Me Booklet.";
	}
	
	public String getSectionFourText(Child child){
		return "To ensure that your child settles at BEL as quickly as possible, some parents may wish to stay for a short period at the start of the session. Please speak to Room Supervisor to arrange this.";
	}
	
	public String getSectionFiveText(Child child){
		return "For more information please refer to the enclosed information booklet or contact the ofiice on 01638 744065 from 9.00am - 12.30pm Monday, 9.00am - 3.30pm Tuesday to Friday.";
	}
	
	public String getSectionSixText(Child child){
		return "<strong>IF YOU DO NOT WISH TO TAKE UP THIS PLACE PLEASE CONTACT ME IMMEDIATELY</strong>";
	}
}
