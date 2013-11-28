package com.cgsolutions.registration.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Term;

public interface AttendanceDao {
	void save(Attendance attendance);
	List<Attendance> findForTerm(int termId);
	List<Attendance> findAttendancesForDayAndRoom(int roomId, Date day);
	void deleteFutureAttendancesForChild(Child child);
	List<Attendance> findFutureAttendancesForChild(Child child);
	void deleteAttendancesForTerm(Term term);
	List<Attendance> findAttendancesForChild(Child child);
	int deleteAttendances(Child child, Term term);
	List<Child> findChildrenForTerm(Term term);
	List<Attendance> findAttendancesForChildInTerm(Child child, Term term);
	void deleteAttendance(Attendance attendance);
	List<Attendance> findAttendancesForChild(Child child, Date termStartsBefore);
	Map<String, String> getTotalHours(Date date1, Date date2);
	List<Attendance> findChildrenForLunch(Date date);
}
