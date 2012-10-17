package com.cgsolutions.registration.dao;

import java.util.List;

import com.cgsolutions.registration.domain.Term;

public interface TermDao {
	void save(Term term);
	Term find(int termId);
	Term findCurrentTerm();
	List<Term> findAllTerms();
}
