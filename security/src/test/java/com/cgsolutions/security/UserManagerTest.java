package com.cgsolutions.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cgsolutions.security.domain.User;
import com.cgsolutions.security.service.UserManager;

@RunWith(SpringJUnit4ClassRunner .class)  
@ContextConfiguration(locations={"classpath:com/cgsolutions/security/service/services.xml", "classpath:com/cgsolutions/security/service/access.xml"})
@Transactional
public class UserManagerTest {
	@Autowired
	private UserManager userManager;
	
	@Test
	public void testFind(){
		User user = userManager.find("CG00907");
		
		System.out.println(user.getFirstName());
	}
	
	@Test
	public void testFindAll(){
		userManager.findAll();
	}
	@Test
	public void testSave(){
		User user = new User();
		user.setFirstName("A");
		user.setSurname("B");
		user.setUserId("C");
		user.setPassword("D");
		
		userManager.save(user);
	}
}
