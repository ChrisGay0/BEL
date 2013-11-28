package com.cgsolutions.registration.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.ChildSearchBean;
import com.cgsolutions.registration.domain.Intolerance;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.enums.TypeOfAttendance;
import com.cgsolutions.registration.service.ChildManager;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:com/cgsolutions/security/service/services.xml", "classpath:com/cgsolutions/security/service/access.xml", "classpath:com/cgsolutions/registration/service/services.xml"})
@Transactional
public class ChildManagerTest {
	@Autowired
	private ChildManager childManager;
	
	@Test
	public void createChild() {
		childManager.createChild(generateChild());
	}
	
	@Test
	public void testFindChildrenOnWaitingList(){
		childManager.findChildrenOnWaitingList(1, null);
	}
	
	@Test
	public void testFindAactiveChildrenWithAlergies(){
		childManager.findActiveChildrenWithAllergiesForRoom(1);
	}
	
	@Test
	public void deleteChild(){
		Child child = generateChild();
		childManager.createChild(child);
		
		assertNotNull(childManager.findChild(child.getId()));
		
		childManager.deleteChild(child);
		
		assertNull(childManager.findChild(child.getId()));
	}
	
	@Test
	public void testDeletingLists(){
		Child child = generateChild();
		childManager.createChild(child);
		
		Intolerance intolerance = new Intolerance();
		intolerance.setIntolerance("TEST");
		intolerance.setChildId(child.getId());
		List<Intolerance> intolerances = new ArrayList<Intolerance>();
		intolerances.add(intolerance);
		
		child.setIntolerances(intolerances);
		
		childManager.saveChild(child);
		
		assertNotNull(child.getIntolerances());
		
		child.getIntolerances().get(0).setSelected(true);
		
		childManager.saveChild(child);
		
		assertTrue(CollectionUtils.isEmpty(child.getIntolerances()));
	}
	@Test
	public void findChild(){
		childManager.findChild(2);
	}
	
	@Test
	public void testSearch(){
		ChildSearchBean searchBean = new ChildSearchBean();
		searchBean.setFirstName("A");
		searchBean.setSurname("b");
		searchBean.setRoom(new Room());
		searchBean.getRoom().setId(1);
		searchBean.setIncludeLeft(false);
		
		childManager.searchForChildren(searchBean);
	}
	
	
	@Test
	public void findForRoom(){
		childManager.findActiveChildrenForRoom(1);
	}
	
	@Test
	public void findChildrenNeedingWelcomeLetters(){
		childManager.findChildrenNeedingWelcomeLetters();
	}
	
	@Test
	public void findChildrenCurrentlyAttending(){
		childManager.findChildrenCurrentlyAttending();
	}
	
	private Child generateChild(){
		Child child = new Child();
		child.setFirstName("Grace");
		child.setSurname("Ambrose");
		child.setMondayAttendance(TypeOfAttendance.FULL);
		
		return child;
	}
}