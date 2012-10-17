package com.cgsolutions.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsolutions.registration.dao.ChildBillDao;
import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.ChildBill;
import com.cgsolutions.registration.domain.enums.TypeOfAttendance;

@Service
public class ChildBillManager {
	@Autowired
	private ChildBillDao childBillDao;
	
	public void save(ChildBill bill){
		float billAmount = 0;
		for(Attendance attendance: bill.getAttendances()){
			if(attendance.getTypeOfAttendance().equals(TypeOfAttendance.FULL)){
				billAmount += attendance.getRoom().getSessionCostForAge(attendance.getChild().getChildsAge()) * 2;
				billAmount += attendance.getRoom().getLunchCostForAge(attendance.getChild().getChildsAge());
			}
			else if(attendance.getTypeOfAttendance().equals(TypeOfAttendance.MORNING) || attendance.getTypeOfAttendance().equals(TypeOfAttendance.AFTERNOON)){
				billAmount += attendance.getRoom().getSessionCostForAge(attendance.getChild().getChildsAge());
			}
			else if (attendance.getTypeOfAttendance().equals(TypeOfAttendance.MORNINGWITHLUNCH) || attendance.getTypeOfAttendance().equals(TypeOfAttendance.AFTERNOONWITHLUNCH)){
				billAmount += attendance.getRoom().getSessionCostForAge(attendance.getChild().getChildsAge());
				billAmount += attendance.getRoom().getLunchCostForAge(attendance.getChild().getChildsAge());
			}
		}
		bill.setBillAmount(billAmount);
		childBillDao.save(bill);
	}
	
	public List<ChildBill> findOutstanding(int childId){
		return childBillDao.findOutstanding(childId);
	}
}
