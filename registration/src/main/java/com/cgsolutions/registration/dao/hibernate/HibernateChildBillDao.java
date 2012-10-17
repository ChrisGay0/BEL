package com.cgsolutions.registration.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cgsolutions.registration.dao.ChildBillDao;
import com.cgsolutions.registration.domain.ChildBill;

@Repository
public class HibernateChildBillDao extends HibernateDaoSupport implements ChildBillDao{
	@Autowired
    public void anyMethodName(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }
	
	public void save(ChildBill bill) {
		getSession().saveOrUpdate(bill);
	}
	
	public List<ChildBill> findOutstanding(int childId){
		return getSession().createQuery("from ChildBill where childId = " + childId + " and balancePaid <> billAmount").list();
	}
}