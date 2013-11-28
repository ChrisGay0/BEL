package com.cgsolutions.registration.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cgsolutions.registration.dao.TermDao;
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.security.utility.MyDateUtils;

@Repository
public class HibernateTermDao extends HibernateDaoSupport implements TermDao {
	@Autowired
    public void anyMethodName(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

	public void save(Term term) {
		getSession().saveOrUpdate(term);
	}
	
	public Term find(int termId){
		return (Term)getSession().get(Term.class, termId);
	}
	
	public Term findCurrentTerm(){
		Query query = getSession().createQuery("from Term where startDate <= :start and endDate >= :end");
		query.setParameter("start", MyDateUtils.setTimeToMidnight(new Date()));
		query.setParameter("end", MyDateUtils.setTimeToMidnight(new Date()));
		
		return (Term)query.uniqueResult();
	}
	
	public List<Term> findAllTerms(){
		return getSession().createQuery("from Term order by id desc").list();
	}
	
	public List<Term> findAllFutureTerms(boolean attendancesGenerated){
		Query query = getSession().createQuery("from Term where startDate > :todaysDate and lockTerm = :lockTerm");
		query.setParameter("lockTerm", attendancesGenerated);
		query.setParameter("todaysDate", new Date());
		
		return query.list();
	}
	
	public List<Term> findTermsSince(Date dateFrom){
		Query query = getSession().createQuery("from Term where startDate >= :dateFrom");
		query.setParameter("dateFrom", dateFrom);
		
		return query.list();
	}
}
