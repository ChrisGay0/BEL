package com.cgsolutions.registration.dao;

import java.util.List;

import com.cgsolutions.registration.domain.Room;

public interface RoomDao {
	void save(Room room);
	Room find(int roomId);
	List<Room> findAllActive();
	List<Room> findAll();
	void flush();
}
