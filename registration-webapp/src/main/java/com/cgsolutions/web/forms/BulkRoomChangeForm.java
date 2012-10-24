package com.cgsolutions.web.forms;

import com.cgsolutions.registration.domain.Child;

import java.util.List;

import org.springframework.util.CollectionUtils;

public class BulkRoomChangeForm {
	private List<Child> children;

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}
	
	public void selectAllChildren(){
		if(!CollectionUtils.isEmpty(this.children)){
			for(Child child: this.children){
				child.setSelected(true);
			}
		}
	}
}