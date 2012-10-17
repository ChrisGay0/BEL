package com.cgsolutions.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsolutions.registration.dao.ChildDao;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.ChildSearchBean;

@Service
public class ChildManager {
	@Autowired
	private ChildDao childDao;
	
	public void createChild(Child child){
		childDao.saveChild(child);
	}
	
	public void saveChild(Child child){
		childDao.saveChild(child);
	}
	
	public void deleteChild(Child child){
		childDao.deleteChild(child);
	}
	
	public Child findChild(int id) {
		return childDao.findChild(id);
	}
	
	public List<Child> findActiveChildrenForRoom(int roomId){
		return childDao.findActiveChildrenForRoom(roomId);
	}

	public List<Child> searchForChildren(ChildSearchBean searchBean) {
		return childDao.searchForChildren(searchBean);
	}
}