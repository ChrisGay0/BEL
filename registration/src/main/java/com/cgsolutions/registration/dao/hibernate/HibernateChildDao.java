package com.cgsolutions.registration.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cgsolutions.registration.dao.ChildDao;
import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.ChildSearchBean;

@Repository
public class HibernateChildDao extends HibernateDaoSupport implements ChildDao{
	@Autowired
    public void anyMethodName(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

	public void saveChild(Child child) {
		getHibernateTemplate().saveOrUpdate(child);
	}

	public void mergeChild(Child child){
		getHibernateTemplate().merge(child);
	}
	
	public void deleteChild(Child child) {
		getHibernateTemplate().delete(child);
	}

	public Child findChild(int id) {
		return getHibernateTemplate().get(Child.class, id);
	}

	public List<Child> searchForChildren(ChildSearchBean searchBean) {
		String hql = "from Child where 1 = 1 ";
		if(StringUtils.hasText(searchBean.getFirstName())){
			hql+= "and firstName like '" + searchBean.getFirstName() + "%' ";
		}
		if(StringUtils.hasText(searchBean.getSurname())){
			hql+= "and surname like '" + searchBean.getSurname() + "%' ";
		}
		if(searchBean.getRoom() != null){
			hql += "and room.id = " + searchBean.getRoom().getId() + " ";
		}
		if(!searchBean.isIncludeLeft()){
			hql += "and leftSchool = false";
		}
		
		return getHibernateTemplate().find(hql);
	}

	public List<Child> findActiveChildrenForRoom(int roomId) {
		Query query = getSession().createQuery("from Child where room.id = :roomId and leftSchool = false");
		query.setParameter("roomId", roomId);
		
		return query.list();
	}
	
	public List<Child> findChildrenNeedingWelcomeLetters(){
		Query query = getSession().createQuery("from Child where leftSchool = false and welcomeLetterPrinted = false and startDate is not null");
		
		return query.list();
	}
	
	public List<Child> findChildrenCurrentlyAttending(){
		Query query = getSession().createQuery("from Child where leftSchool = false and startDate is not null");
		
		return query.list();
	}
	
	public List<Child> findChildrenOnWaitingList(int roomId){
		Query query = getSession().createQuery("from Child where startDate is null and room.id = :roomId order by registeredDate, requestedStartDate");
		query.setParameter("roomId", roomId);
		
		return query.list();
	}
}