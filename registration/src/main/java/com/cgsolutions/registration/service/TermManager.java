package com.cgsolutions.registration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cgsolutions.registration.dao.TermDao;
import com.cgsolutions.registration.domain.ExclusionDate;
import com.cgsolutions.registration.domain.Intolerance;
import com.cgsolutions.registration.domain.Term;

@Service
public class TermManager {
	@Autowired
	private TermDao termDao;
	
	public void save(Term term){
		if(!CollectionUtils.isEmpty(term.getExclusionDates())){
			for(ExclusionDate exclusionDate: new ArrayList<ExclusionDate>(term.getExclusionDates())){
				if(exclusionDate.isSelected()){
					term.getExclusionDates().remove(exclusionDate);
				}
			}
		}
		
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
	public List<Term> findAllFutureTerms(boolean attendancesGenerated){
		return termDao.findAllFutureTerms(attendancesGenerated);
	}
}
