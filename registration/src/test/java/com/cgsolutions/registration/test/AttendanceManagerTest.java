package com.cgsolutions.registration.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AssertThrows;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.domain.SessionCost;
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.domain.enums.TypeOfAttendance;
import com.cgsolutions.registration.service.AttendanceManager;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.RoomManager;
import com.cgsolutions.registration.service.TermManager;
import com.cgsolutions.security.utility.MyDateUtils;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:com/cgsolutions/security/service/services.xml", "classpath:com/cgsolutions/security/service/access.xml", "classpath:com/cgsolutions/registration/service/services.xml"})
@Transactional
public class AttendanceManagerTest {
	@Autowired
	private AttendanceManager attendanceManager;
	@Autowired
	private TermManager termManager;
	@Autowired
	private RoomManager roomManager;
	@Autowired
	private ChildManager childManager;
	
	@Test
	public void findForTerm(){
		attendanceManager.findForTerm(1);
	}
	
	@Test
	public void findFutureAttendances(){
		attendanceManager.findFutureAttendancesForChild(setupChildData());
	}
	
	@Test
	public void findAttendancesForRoomAndDay(){
		attendanceManager.findAttendancesForDayAndRoom(13, new Date());
	}
	@Test
	public void deleteForTerm(){
		Term term = new Term();
		term.setId(99999);
		
		attendanceManager.deleteAttendancesForTerm(term);
	}
	
	@Test
	public void deleteAttendances(){
		Child child = setupChildData();
		
		attendanceManager.deleteAttendancesForCurrentTerm(child);
	}
	
	@Test
	public void save(){
		Attendance attendance = new Attendance();
		attendanceManager.save(attendance);
		assertTrue(attendance.getId() != 0);
	}
	
	@Test
	public void generateAttendances(){
		setupChildData();
		Term term = new Term();
		term.setStartDate(MyDateUtils.incrementByDays(new Date(), 1));
		term.setEndDate(MyDateUtils.incrementByDays(new Date(), 10));
		
		termManager.save(term);
		List<Attendance> attendances = attendanceManager.generateAttendancesForTerm(term);
		
		for(Attendance attendance: attendances){
			System.out.println("***********" + attendance.getTypeOfAttendance().getDescription());
			System.out.println("***********" + attendance.getAttendanceDate());
		}
		
	}
	
	@Test
	public void testDeleteFutureAttendances(){
		Child child = setupChildData();
		
		attendanceManager.deleteFutureAttendancesForChild(child);
	}
	
	@Test
	public void redoAttendancesForChild(){
		Child child = setupChildData();
		Term term = new Term();
		term.setStartDate(MyDateUtils.incrementByDays(new Date(), 1));
		term.setEndDate(MyDateUtils.incrementByDays(new Date(), 10));
		
		termManager.save(term);
		attendanceManager.generateAttendancesForTerm(term);
		
		attendanceManager.redoAttendancesForChild(child);
	}
	
	private Child setupChildData(){
		Room room = new Room();
		room.setActive(true);
		room.setName("Dolphin");
		
		List<SessionCost> costs = new ArrayList<SessionCost>();
		SessionCost cost = new SessionCost();
		cost.setCost(10F);
		cost.setLunchCost(2F);
		cost.setChildAgeUnder(5);
		cost.setRoom(room);
		
		costs.add(cost);
		room.setCosts(costs);
		
		roomManager.save(room);
		
		Child child = new Child();
		child.setFirstName("Chris");
		child.setMondayAttendance(TypeOfAttendance.AFTERNOON);
		child.setTuesdayAttendance(TypeOfAttendance.FULL);
		child.setWednesdayAttendance(TypeOfAttendance.MORNING);
		child.setDateOfBirth(new Date());
		child.setRoom(room);
		
		childManager.saveChild(child);
		
		return child;
	}
}
