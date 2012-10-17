package com.cgsolutions.registration.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:com/cgsolutions/security/service/services.xml", "classpath:com/cgsolutions/security/service/access.xml", "classpath:com/cgsolutions/registration/service/services.xml"})
@Transactional
public class RoomManagerTest {
	@Autowired
	private RoomManager roomManager;
	@Autowired
	private ChildManager childManager;
	
	@Test
	public void testSave(){
		Room room = new Room();
		room.setName("Dolphin");
		
		roomManager.save(room);
		
		Child child = new Child();
		child.setFirstName("Test");
		child.setSurname("TEST");
		
		childManager.saveChild(child);
		
		room.addChild(child);
		
		roomManager.save(room);
	}
	
	@Test
	public void testFindAllActive(){
		roomManager.findAllActive();
		
		Assert.assertTrue(roomManager.find(124).getCosts().size() > 0);
	}
	
	@Test
	public void testFindAll(){
		roomManager.findAll();
	}
}
