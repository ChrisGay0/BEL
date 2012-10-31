package com.cgsolutions.registration.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cgsolutions.registration.dao.SchoolDao;
import com.cgsolutions.registration.domain.School;

@Repository
public class HibernateSchoolDao extends HibernateDaoSupport implements SchoolDao{
	@Autowired
    public void anyMethodName(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }
	public School find() {
		try{
			return (School)getSession().createQuery("from School").list().get(0);
		}
		catch(EmptyResultDataAccessException e){
			School school = new School();
			save(school);
			
			return school;
		}
	}
	
	public void save(School school){
		getSession().saveOrUpdate(school);
	}

}
