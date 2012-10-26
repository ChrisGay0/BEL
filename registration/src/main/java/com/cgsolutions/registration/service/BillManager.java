package com.cgsolutions.registration.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.TermBill;
import com.cgsolutions.registration.domain.enums.TypeOfAttendance;

@Service
public class BillManager {
	@Autowired
	private AttendanceManager attendanceManager;
	
	public List<TermBill> findBillsForChild(Child child){
		List<Attendance> attendances = attendanceManager.findAttendancesForChild(child);
		List<TermBill> bills = new ArrayList<TermBill>();
		Map<Integer, List<Attendance>> attendancesForTerm = new HashMap<Integer, List<Attendance>>();
		
		if(!CollectionUtils.isEmpty(attendances)){
			for(Attendance attendance: attendances){
				if(attendancesForTerm.get(attendance.getTerm().getId()) != null){
					List<Attendance> attendanceList = attendancesForTerm.get(attendance.getTerm().getId());
					attendanceList.add(attendance);
					
					attendancesForTerm.put(attendance.getTerm().getId(), attendanceList);
				}
				else{
					List<Attendance> attendanceList = new ArrayList<Attendance>();
					attendanceList.add(attendance);
					
					attendancesForTerm.put(attendance.getTerm().getId(), attendanceList);
				}
			}
			
			for(Integer key: attendancesForTerm.keySet()){
				TermBill bill = new TermBill();
				for(Attendance termAttendance: attendancesForTerm.get(key)){
					bill.setTerm(termAttendance.getTerm());
					bill.setRoom(termAttendance.getRoom());
					bill.setSessionsCost(termAttendance.getSessionCost());
					bill.setLunchesCost(termAttendance.getLunchCost());
					if(termAttendance.getTypeOfAttendance().toString().equals(TypeOfAttendance.MORNING.toString()) || termAttendance.getTypeOfAttendance().toString().equals(TypeOfAttendance.AFTERNOON.toString())){
						bill.setSessions(bill.getSessions() + 1);
					}
					else if(termAttendance.getTypeOfAttendance().toString().equals(TypeOfAttendance.MORNINGWITHLUNCH.toString()) || termAttendance.getTypeOfAttendance().toString().equals(TypeOfAttendance.AFTERNOONWITHLUNCH.toString())){
						bill.setSessions(bill.getSessions() + 1);
						bill.setLunches(bill.getLunches() + 1);
					}
					else{
						bill.setSessions(bill.getSessions() + 2);
						bill.setLunches(bill.getLunches() + 1);
					}
				}
				
				bills.add(bill);
			}
		}
		
		return bills;
	}
}