package com.cgsolutions.registration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cgsolutions.registration.dao.ChildDao;
import com.cgsolutions.registration.domain.AdditionalSetting;
import com.cgsolutions.registration.domain.Authorisation;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.ChildSearchBean;
import com.cgsolutions.registration.domain.Contact;
import com.cgsolutions.registration.domain.Intolerance;
import com.cgsolutions.registration.domain.Room;

@Service
public class ChildManager {
	@Autowired
	private ChildDao childDao;
	
	public void createChild(Child child){
		childDao.saveChild(child);
	}
	
	public void saveChild(Child child){
		if(child.getId() != 0 ){
			deleteSelectedItems(child);
		
			childDao.mergeChild(child);
		}
		else{
			childDao.saveChild(child);
		}
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
	
	public void deleteSelectedItems(Child child){
		if(!CollectionUtils.isEmpty(child.getIntolerances())){
			for(Intolerance intolerance: new ArrayList<Intolerance>(child.getIntolerances())){
				if(intolerance.isSelected()){
					child.getIntolerances().remove(intolerance);
				}
			}
		}
		
		if(!CollectionUtils.isEmpty(child.getAuthorisations())){
			for(Authorisation authorisation: new ArrayList<Authorisation>(child.getAuthorisations())){
				if(authorisation.isSelected()){
					child.getAuthorisations().remove(authorisation);
				}
			}
		}
		
		if(!CollectionUtils.isEmpty(child.getContacts())){
			for(Contact contact: new ArrayList<Contact>(child.getContacts())){
				if(contact.isSelected()){
					child.getContacts().remove(contact);
				}
			}
		}
		
		if(!CollectionUtils.isEmpty(child.getAdditionalSettings())){
			for(AdditionalSetting setting: new ArrayList<AdditionalSetting>(child.getAdditionalSettings())){
				if(setting.isSelected()){
					child.getAdditionalSettings().remove(setting);
				}
			}
		}
	}
	
	public int bulkRoomChange(List<Child> children, Room newRoom){
		int childrenMoved = 0;
		if(!CollectionUtils.isEmpty(children)){
			for(Child child: children){
				if(child.isSelected()){
					//make sure we have the latest child object for hibernate
					child = findChild(child.getId());
					child.setRoom(newRoom);
					saveChild(child);
					
					childrenMoved++;
				}
			}
		}
		
		return childrenMoved;
	}
}