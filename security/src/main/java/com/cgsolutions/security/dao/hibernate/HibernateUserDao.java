package com.cgsolutions.security.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cgsolutions.security.dao.UserDao;
import com.cgsolutions.security.domain.User;

@Repository
public class HibernateUserDao extends HibernateDaoSupport implements UserDao{
	@Autowired
    public void anyMethodName(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }
	
	public User find(String userId) {
		return (User)getSession().get(User.class, userId);
	}
	
	public void save(User user){
		getSession().saveOrUpdate(user);
	}
	
	public List<User> findAll(){
		return getSession().createQuery("from User order by userId").list();
	}
	
	public String getSchoolName(){
		return (String)getSession().createSQLQuery("select name from school").uniqueResult();
	}
}
