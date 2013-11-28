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
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.security.utility.MyDateUtils;

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
		if(searchBean.getIncludeLeft() != null){
			hql += "and leftSchool = " + searchBean.getIncludeLeft();
		}
		if(searchBean.getOnWaitingList() != null){
			if(searchBean.getOnWaitingList()){
				hql += " and startDate is null";
			}
			else{
				hql += " and startDate is not null";
			}
		}
		if(searchBean.getNonStarter() != null){
			hql += " and nonStarter = " + searchBean.getNonStarter();
		}
		
		return getHibernateTemplate().find(hql);
	}

	public List<Child> findActiveChildrenForRoom(int roomId) {
		Query query = getSession().createQuery("from Child where room.id = :roomId and leftSchool = false and startDate is not null and nonStarter = false");
		query.setParameter("roomId", roomId);
		
		return query.list();
	}
	
	public List<Child> findActiveChildrenWithAllergiesForRoom(int roomId) {
		Query query = getSession().createQuery("from Child child where room.id = :roomId and leftSchool = false and startDate is not null and nonStarter = false " +
				"and (exists(select med.id from MedicalInfo med where med.childId = child.id) or exists(select intol.id from Intolerance intol where intol.childId = child.id))");
		query.setParameter("roomId", roomId);
		
		return query.list();
	}
	
	public List<Child> findChildrenNeedingWelcomeLetters(){
		Query query = getSession().createQuery("from Child where leftSchool = false and welcomeLetterPrinted = false  and startDate is not null and nonStarter = false");
		
		return query.list();
	}
	
	public List<Child> findChildrenCurrentlyAttending(){
		Query query = getSession().createQuery("from Child where leftSchool = false  and startDate is not null and nonStarter = false order by surname asc");
		
		return query.list();
	}
	
	public List<Child> findChildrenOnWaitingList(Integer roomId, Term term){
		String hql = "from Child where startDate is null ";
		if(roomId != null){
			hql  += " and room.id = :roomId ";
		}
		if(term != null){
			hql  += " and requestedStartDate >= :termStart and requestedStartDate <=:termEnd ";
		}
		hql += " order by registeredDate, requestedStartDate";
		Query query = getSession().createQuery(hql);
		if(roomId != null){
			query.setParameter("roomId", roomId);
		}
		if(term != null){
			query.setParameter("termStart", term.getStartDate());
			query.setParameter("termEnd", term.getEndDate());
		}
		
		return query.list();
	}
	
}