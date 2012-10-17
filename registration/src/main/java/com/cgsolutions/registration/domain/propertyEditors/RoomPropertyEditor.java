package com.cgsolutions.registration.domain.propertyEditors;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import com.cgsolutions.registration.domain.Room;
import com.cgsolutions.registration.service.RoomManager;

public class RoomPropertyEditor extends PropertyEditorSupport {
	public RoomPropertyEditor(RoomManager roomManager){
		this.roomManager = roomManager;
	}
	private RoomManager roomManager;
	
	public void setAsText(String value) {
		if(StringUtils.hasText(value)){
			setValue(roomManager.find(Integer.parseInt(value)));
		}
	}

	public String getAsText() {
	    return getValue() != null ? ((Room)getValue()).getName() : null;
	}       
}