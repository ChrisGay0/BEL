package com.cgsolutions.registration.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cgsolutions.registration.dao.PaymentDao;
import com.cgsolutions.registration.domain.Payment;

@Repository
public class PaymentManager {
	@Autowired
	private PaymentDao paymentDao;
	
	public List<Payment> findPayments(Date dateFrom, Date toDate){
		return paymentDao.findPayments(dateFrom, toDate);
	}
}
