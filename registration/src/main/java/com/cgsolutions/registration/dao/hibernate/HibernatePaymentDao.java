package com.cgsolutions.registration.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cgsolutions.registration.dao.PaymentDao;
import com.cgsolutions.registration.domain.Payment;
import com.cgsolutions.security.utility.MyDateUtils;
@Repository
public class HibernatePaymentDao extends HibernateDaoSupport implements PaymentDao {
	@Autowired
    public void anyMethodName(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }
	
	public List<Payment> findPayments(Date dateFrom, Date toDate) {
		Query query = getSession().createQuery("from Payment where datePaid >= :dateFrom and datePaid <= :toDate");
		query.setParameter("dateFrom", MyDateUtils.setTimeToMidnight(dateFrom));
		query.setParameter("toDate", MyDateUtils.setTimeToOneMinuteToMidnight(toDate));
		
		return query.list();
	}
}
