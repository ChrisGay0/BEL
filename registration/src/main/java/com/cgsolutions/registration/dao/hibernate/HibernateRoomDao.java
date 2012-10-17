package com.cgsolutions.registration.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cgsolutions.registration.dao.RoomDao;
import com.cgsolutions.registration.domain.Room;

@Repository
public class HibernateRoomDao extends HibernateDaoSupport implements RoomDao{
	@Autowired
    public void anyMethodName(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }
	
	public void save(Room room) {
		getSession().saveOrUpdate(room);
	}

	public Room find(int roomId) {
		return (Room)getSession().get(Room.class, roomId);
	}

	public List<Room> findAllActive() {
		return getSession().createQuery("from Room where active = true").list();
	}

	public List<Room> findAll() {
		return getSession().createQuery("from Room").list();
	}

	public void flush() {
		getSession().flush();
	}

}
