package com.cgsolutions.registration.dao;

import java.util.List;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.ChildSearchBean;

public interface ChildDao {
	void saveChild(Child child);
	void deleteChild(Child child);
	Child findChild(int id);
	List<Child> searchForChildren(ChildSearchBean searchBean);
	List<Child> findActiveChildrenForRoom(int roomId);
}
