package com.cgsolutions.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsolutions.registration.dao.SchoolDao;
import com.cgsolutions.registration.domain.School;

@Service
public class SchoolManager {
	@Autowired
	private SchoolDao schoolDao;
	
	public School find() {
		return schoolDao.find();
	}
	
	
}
