package com.cgsolutions.registration.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cgsolutions.registration.dao.AttendanceDao;
import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Term;
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
}