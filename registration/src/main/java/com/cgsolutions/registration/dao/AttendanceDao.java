package com.cgsolutions.registration.dao;

import java.util.Date;
import java.util.List;

import com.cgsolutions.registration.domain.Attendance;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.Term;

public interface AttendanceDao {
	void save(Attendance attendance);
	List<Attendance> findForTerm(int termId);
	List<Attendance> findAttendancesForDayAndRoom(int roomId, Date day);
	void deleteFutureAttendancesForChild(Child child);
	List<Attendance> findFtureAttendancesForChild(Child child);
	void deleteAttendancesForTerm(Term term);
}
