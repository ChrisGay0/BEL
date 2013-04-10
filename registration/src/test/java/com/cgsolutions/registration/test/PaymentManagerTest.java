package com.cgsolutions.registration.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cgsolutions.registration.service.PaymentManager;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:com/cgsolutions/security/service/services.xml", "classpath:com/cgsolutions/security/service/access.xml", "classpath:com/cgsolutions/registration/service/services.xml"})
@Transactional
public class PaymentManagerTest {
	@Autowired
	private PaymentManager paymentManager;
	
	@Test
	public void testFindPayments(){
		paymentManager.findPayments(new Date(), new Date());
	}
}
