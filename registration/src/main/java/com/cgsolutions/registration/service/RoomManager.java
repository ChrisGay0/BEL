package com.cgsolutions.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsolutions.registration.dao.RoomDao;
import com.cgsolutions.registration.domain.Room;

@Service
public class RoomManager {
	@Autowired
	private RoomDao roomDao;
	
	public void save(Room room){
		roomDao.save(room);
		roomDao.flush();
	}
	
	public Room find(int roomId){
		return roomDao.find(roomId);
	}
	
	public List<Room> findAllActive(){
		return roomDao.findAllActive();
	}
	
	public List<Room> findAll() {
		return roomDao.findAll();
	}
}
