package com.cgsolutions.registration.dao;

import java.util.Date;
import java.util.List;

import com.cgsolutions.registration.domain.Attendance;

public interface AttendanceDao {
	void save(Attendance attendance);
	List<Attendance> findForTerm(int termId);
	List<Attendance> findAttendancesForDayAndRoom(int roomId, Date day);
}
