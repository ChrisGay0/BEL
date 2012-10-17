package com.cgsolutions.registration.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.service.TermManager;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:com/cgsolutions/security/service/services.xml", "classpath:com/cgsolutions/security/service/access.xml", "classpath:com/cgsolutions/registration/service/services.xml"})
@Transactional
public class TermManagerTest {
	@Autowired
	private TermManager termManager;
	
	@Test
	public void testCreate(){
		Term term = new Term();
		term.setStartDate(new Date());
		term.setEndDate(new Date());
		
		termManager.save(term);
		
		term.addExclusionDate(new Date());
		
		termManager.save(term);
		
	}
	
	@Test
	public void testFindCurrentTerm(){
		termManager.findCurrentTerm();
	}
	@Test
	public void testFind(){
		termManager.find(1);
	}
	@Test
	public void testFindAll(){
		termManager.findAllTerms();
	}
}
