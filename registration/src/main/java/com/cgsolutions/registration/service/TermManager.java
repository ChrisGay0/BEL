package com.cgsolutions.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsolutions.registration.dao.TermDao;
import com.cgsolutions.registration.domain.Term;

@Service
public class TermManager {
	@Autowired
	private TermDao termDao;
	
	public void save(Term term){
		termDao.save(term);
	}
	public Term find(int termId){
		return termDao.find(termId);
	}
	public Term findCurrentTerm(){
		return termDao.findCurrentTerm();
	}
	public List<Term> findAllTerms(){
		return termDao.findAllTerms();
	}
}
