package com.cgsolutions.registration.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cgsolutions.registration.dao.AttendanceDao;
import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.security.utility.MyDateUtils;

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
	
	public void save(Attendance attendance){
		attendanceDao.save(attendance);
	}
	
	public void save(List<Attendance> attendances){
		if(!CollectionUtils.isEmpty(attendances)){
			for(Attendance attendance: attendances){
				save(attendance);
			}
		}
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
				generateAttendancesForTerm(term, child, room, attendanceDates, newAttendances);
			}
		}
		
		term.setLockTerm(true);
		termManager.save(term);
		
		return newAttendances;
	}
	
	public List<Attendance> findChildrenForLunch(Date date){
		return attendanceDao.findChildrenForLunch(date);
	}
	public List<Attendance> redoAttendancesForChild(Child child){
		deleteFutureAttendancesForChild(child);
		List<Attendance> newAttendances = new ArrayList<Attendance>();
		
		//Find only future terms which have had attendances generated
		for(Term term: termManager.findAllFutureTerms(true)){
			generateAttendancesForTerm(term, child, child.getRoom(), term.getWeekdayTermDates(), newAttendances);
		}
		
		return newAttendances;
	}
	
	public void deleteFutureAttendancesForChild(Child child){
		attendanceDao.deleteFutureAttendancesForChild(child);
	}
	
	public int deleteAttendancesForCurrentTerm(Child child){
		Term term = termManager.findCurrentTerm();
		if(term != null){
			return attendanceDao.deleteAttendances(child, term);
		}
		else{
			return 0;
		}
	}
	
	public void redoAttendancesForTerm(Term term){
		deleteAttendancesForTerm(term);
		generateAttendancesForTerm(term);
	}
	
	private void generateAttendancesForTerm(Term term, Child child, Room room, List<Date> attendanceDates, List<Attendance> newAttendances){
		if(child.getStartDate() != null){
			//ChildBill bill = new ChildBill(child.getId());
			
			for(Date date: attendanceDates){
				if(term.getEndDate().after(child.getStartDate())){
					if(child.getTypeOfAttendance(date) != null){
						if(!term.isDateInExclusionsList(date) ){
							Attendance attendance = new Attendance(term, child, room, child.getTypeOfAttendance(date), date);
							save(attendance);
							newAttendances.add(attendance);
							//bill.getAttendances().add(attendance);
							//attendance.setAttendanceCost(attendance.getBillAmount());
						}
						else if(term.isExclusionsDateChargeable(date)){
							Attendance attendance = new Attendance(term, child, room, child.getTypeOfAttendance(date), date);
							attendance.setChargeableExclusionDate(true);
							save(attendance);
							newAttendances.add(attendance);
							//bill.getAttendances().add(attendance);
							//attendance.setAttendanceCost(attendance.getBillAmount());
						}
					}
				}
				else{
					break;
				}
			}
			//if(!CollectionUtils.isEmpty(bill.getAttendances())){
				//childBillManager.save(bill);
			//}
		}
	}
	
	public List<Attendance> findFutureAttendancesForChild(Child child){
		return attendanceDao.findFutureAttendancesForChild(child);
	}
	
	public List<Attendance> findAttendancesForChild(Child child){
		return attendanceDao.findAttendancesForChild(child);
	}
	
	public List<Child> findChildrenForTerm(Term term){
		return attendanceDao.findChildrenForTerm(term);
	}
	
	public void deleteAttendancesForTerm(Term term){
		attendanceDao.deleteAttendancesForTerm(term);
	}
	
	public List<Attendance> findAttendancesForChildInTerm(Child child, Term term){
		return attendanceDao.findAttendancesForChildInTerm(child, term);
	}
	
	public void deleteAttendance(Attendance attendance){
		attendanceDao.deleteAttendance(attendance);
	}
	
	public void deleteAttendances(List<Attendance> attendances){
		for(Attendance attendance: attendances){
			deleteAttendance(attendance);
		}
	}
	
	public List<Attendance> findAttendancesForChild(Child child, Date termStartsBefore){
		return attendanceDao.findAttendancesForChild(child, termStartsBefore);
	}
	
	public Map<String, String> getTotalHours(Date date1, Date date2){
		return attendanceDao.getTotalHours(date1, date2);
	}
}
