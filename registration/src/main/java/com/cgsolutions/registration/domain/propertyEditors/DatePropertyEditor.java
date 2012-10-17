package com.cgsolutions.registration.domain.propertyEditors;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyEditor extends PropertyEditorSupport {
	public void setAsText(String value) {
		 try {
			 setValue(new SimpleDateFormat("dd MMM yyyy").parse(value));
		 } catch(ParseException e) {
			 setValue(null);
	     }
	}

	public String getAsText() {
		
	    return getValue() != null ? new SimpleDateFormat("dd MMM yyyy").format((Date) getValue()) : null;
	}        
}
