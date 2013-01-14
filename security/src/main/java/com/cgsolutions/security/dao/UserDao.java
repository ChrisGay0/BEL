package com.cgsolutions.security.dao;

import java.util.List;

import com.cgsolutions.security.domain.User;

public interface UserDao {
	User find(String userId);
	void save(User user);
	List<User> findAll();
	String getSchoolName();
}
