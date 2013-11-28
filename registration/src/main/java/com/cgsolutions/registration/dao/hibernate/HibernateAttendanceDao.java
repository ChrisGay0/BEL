package com.cgsolutions.registration.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cgsolutions.registration.dao.AttendanceDao;
import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.domain.enums.TypeOfAttendance;
import com.cgsolutions.security.utility.MyDateUtils;

@Repository
public class HibernateAttendanceDao extends HibernateDaoSupport implements AttendanceDao{
	@Autowired
    public void anyMethodName(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

	public void save(Attendance attendance) {
		getSession().save(attendance);
	}

	public List<Attendance> findForTerm(int termId) {
		Query query = getSession().createQuery("from Attendance where termId = :termId");
		query.setParameter("termId", termId);
		
		return query.list();
	}
	
	public List<Attendance> findAttendancesForDayAndRoom(int roomId, Date day){
		Query query = getSession().createQuery("from Attendance where room.id = :roomId and attendanceDate = :attendanceDate order by child.surname desc");
		query.setParameter("roomId", roomId);
		query.setParameter("attendanceDate", MyDateUtils.getStartOfDay(day));
		
		return query.list();
	}
	
	public List<Attendance> findChildrenForLunch(Date date){
		Query query = getSession().createQuery("from Attendance where attendanceDate = :attendanceDate and typeOfAttendance in (:full,:morning,:afternoon) order by child.surname desc");
		query.setParameter("attendanceDate", date);
		query.setParameter("full", TypeOfAttendance.FULL);
		query.setParameter("morning", TypeOfAttendance.MORNINGWITHLUNCH);
		query.setParameter("afternoon", TypeOfAttendance.AFTERNOONWITHLUNCH);
		
		return query.list();
	}
	
	public void deleteFutureAttendancesForChild(Child child){
		Query query = getSession().createQuery("delete from Attendance a where child.id = :childId and term.id in (select t.id from Term t where t.startDate > :todaysDate)");
		query.setParameter("childId", child.getId());
		query.setParameter("todaysDate", new Date());
		
		query.executeUpdate();
	}
	
	public void deleteAttendancesForTerm(Term term){
		getSession().createQuery("delete from Attendance where term.id = " + term.getId()).executeUpdate();
	}
	
	public List<Attendance> findFutureAttendancesForChild(Child child){
		Query query = getSession().createQuery("from Attendance a where child.id = :childId and term.id in (select t.id from Term t where t.startDate > :todaysDate)");
		query.setParameter("childId", child.getId());
		query.setParameter("todaysDate", new Date());
		
		return query.list();
	}
	
	public List<Attendance> findAttendancesForChild(Child child){
		Query query = getSession().createQuery("from Attendance a where child.id = :childId");
		query.setParameter("childId", child.getId());
		
		return query.list();
	}
	
	public List<Attendance> findAttendancesForChild(Child child, Date termStartsBefore){
		Query query = getSession().createQuery("from Attendance a where child.id = :childId and term.startDate < :startDate");
		query.setParameter("childId", child.getId());
		query.setParameter("startDate", termStartsBefore);
		
		return query.list();
	}
	
	public int deleteAttendances(Child child, Term term){
		Query query = getSession().createQuery("delete from Attendance where child.id = :childId and term.id = :termId");
		query.setParameter("childId", child.getId());
		query.setParameter("termId", term.getId());
		
		return query.executeUpdate();
	}
	
	public List<Child> findChildrenForTerm(Term term){
		Query query = getSession().createQuery("select distinct(a.child) from Attendance a where a.term.id = " + term.getId());
		
		return query.list();
	}
	
	public List<Attendance> findAttendancesForChildInTerm(Child child, Term term){
		Query query = getSession().createQuery("from Attendance where child.id = :childId and term.id = :termId");
		query.setParameter("childId", child.getId());
		query.setParameter("termId", term.getId());
		
		return query.list();
	}
	
	public void deleteAttendance(Attendance attendance){
		Query query = getSession().createQuery("delete from Attendance where id = :attendanceId");
		query.setParameter("attendanceId", attendance.getId());
		
		query.executeUpdate();
	}
	
	public Map<String, String> getTotalHours(Date date1, Date date2){
		Map<String, String> hoursMap = new HashMap<String, String>();
		int minutes = 0;
		int fundedMinutes =  0;
		Query query = getSession().createQuery("from Attendance where attendanceDate >= :date1 and attendanceDate <= :date2");
		
		query.setParameter("date1", MyDateUtils.setTimeToMidnight(date1));
		query.setParameter("date2", MyDateUtils.setTimeToOneMinuteToMidnight(date2));
		
		List<Attendance> attendances = query.list();
		if(!CollectionUtils.isEmpty(attendances)){
			for(Attendance attendance: attendances){
				if(attendance.getTypeOfAttendance().getDescription().equals(TypeOfAttendance.MORNING.getDescription()) || attendance.getTypeOfAttendance().getDescription().equals(TypeOfAttendance.AFTERNOON.getDescription())){
					minutes += 180;
				}
				else if(attendance.getTypeOfAttendance().getDescription().equals(TypeOfAttendance.AFTERNOONWITHLUNCH.getDescription()) || attendance.getTypeOfAttendance().getDescription().equals(TypeOfAttendance.MORNINGWITHLUNCH.getDescription())){
					minutes += 210;
				}
				else{
					minutes += 390;
				}
				
				fundedMinutes += attendance.getChild().getFundedLunches() * 30;
				fundedMinutes += (attendance.getChild().getFundedSessions() * 3) * 60;
			}
		}

		hoursMap.put("totalHours", convertToStringTime(minutes));
		hoursMap.put("fundedHours", convertToStringTime(fundedMinutes));
		
		return hoursMap;
	}
	
	private String convertToStringTime(int mins){
		long absMinutes = Math.abs(mins);
		long hours = absMinutes / 60;
		long minutes = absMinutes % 60;

		String signStr = mins < 0 ? "-" : "";
		String hoursStr;
        String minutesStr;
              
        if (minutes < 10) {
        	minutesStr = "0" + minutes;
        } else {
        	if (minutes == 0) {
        		minutesStr = "00";
        	} else {
        		minutesStr = ""+ minutes;
        	}
        }
        if (hours < 10) {
        	hoursStr = "0" + hours;
        } else {
        	if (hours == 0) {
        		hoursStr = "00";
        	} else {
        		hoursStr = ""+ hours;
        	}
        }
              
        return signStr + hoursStr + ":" + minutesStr;
	}
}