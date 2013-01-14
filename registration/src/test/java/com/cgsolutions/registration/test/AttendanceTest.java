package com.cgsolutions.registration.test;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.security.utility.MyDateUtils;

@RunWith(JUnit4ClassRunner.class)  
public class AttendanceTest {
	@Test
	public void testWeeksInTerm(){
		Term term = new Term();
		term.setStartDate(new Date());
		term.setEndDate(MyDateUtils.incrementByDays(new Date(), 7));
		
		Assert.assertEquals(term.getWeeksInTerm(), 1.0);
		
		term.setEndDate(MyDateUtils.incrementByDays(new Date(), 8));
		
		Assert.assertEquals(term.getWeeksInTerm(), 2.0);
		
		term.setEndDate(MyDateUtils.incrementByDays(new Date(), 13));
		
		Assert.assertEquals(term.getWeeksInTerm(), 2.0);
		
		term.setEndDate(MyDateUtils.incrementByDays(new Date(), 14));
		
		Assert.assertEquals(term.getWeeksInTerm(), 2.0);
		
		term.setEndDate(MyDateUtils.incrementByDays(new Date(), 15));
		
		Assert.assertEquals(term.getWeeksInTerm(), 3.0);
	}
}
