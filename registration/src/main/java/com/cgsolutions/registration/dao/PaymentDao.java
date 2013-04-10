package com.cgsolutions.registration.dao;

import java.util.Date;
import java.util.List;

import com.cgsolutions.registration.domain.Payment;

public interface PaymentDao {
	List<Payment> findPayments(Date dateFrom, Date toDate);
}
