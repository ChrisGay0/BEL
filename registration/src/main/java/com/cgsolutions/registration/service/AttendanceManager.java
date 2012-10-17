package com.cgsolutions.registration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsolutions.registration.dao.AttendanceDao;
import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.ChildBill;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.Term;

@Service
public class AttendanceManager {
	@Autowired
	private AttendanceDao attendanceDao;
	@Autowired
	private RoomManager roomManager;
	@Autowired
	private ChildManager childManager;
	@Autowired
	private TermManager termManager;
	@Autowired
	private ChildBillManager childBillManager;
	
	public void save(Attendance attendance){
		attendanceDao.save(attendance);
	}
	
	public List<Attendance> findForTerm(int termId){
		return attendanceDao.findForTerm(termId);
	}
	
	public List<Attendance> findAttendancesForDayAndRoom(int roomId, Date day){
		return attendanceDao.findAttendancesForDayAndRoom(roomId, day);
	}
	
	public List<Attendance> generateAttendancesForTerm(Term term){
		List<Attendance> newAttendances = new ArrayList<Attendance>();
		List<Date> attendanceDates = term.getWeekdayTermDates();
		
		for(Room room: roomManager.findAllActive()){
			for(Child child: childManager.findActiveChildrenForRoom(room.getId())){
				ChildBill bill = new ChildBill(child.getId());
				for(Date date: attendanceDates){
					if(child.getTypeOfAttendance(date) != null){
						if(!term.isDateInExclusionsList(date)){
							Attendance attendance = new Attendance(term, child, room, child.getTypeOfAttendance(date), date, bill);
							save(attendance);
							newAttendances.add(attendance);
							bill.getAttendances().add(attendance);
						}
					}
				}
				
				childBillManager.save(bill);
			}
		}
		
		term.setLockTerm(true);
		termManager.save(term);
		
		return newAttendances;
	}
}
