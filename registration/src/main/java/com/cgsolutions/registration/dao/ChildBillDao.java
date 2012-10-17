package com.cgsolutions.registration.dao;

import java.util.List;

import com.cgsolutions.registration.domain.ChildBill;

public interface ChildBillDao {
	void save(ChildBill bill);
	List<ChildBill> findOutstanding(int childId);
}
