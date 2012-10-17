package com.cgsolutions.registration.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.security.utility.MyDateUtils;

@RunWith(JUnit4ClassRunner.class)  
public class ChildTest {

	@Test
	public void testGetAge(){
		Child child = new Child();
		child.setDateOfBirth(MyDateUtils.getDateFromString("30 07 1984", "dd MM yyyy"));
		
		//Assert.assertEquals(27, child.getChildsAge());
	}
	
	@Test
	public void testGetAge2(){
		Child child = new Child();
		child.setDateOfBirth(MyDateUtils.getDateFromString("18 07 1984", "dd MM yyyy"));
		
		//Assert.assertEquals(28, child.getChildsAge());
	}
	
	@Test
	public void testGetAge3(){
		Child child = new Child();
		child.setDateOfBirth(MyDateUtils.getDateFromString("19 07 1984", "dd MM yyyy"));
		
	//	Assert.assertEquals(27, child.getChildsAge());
	}
}
