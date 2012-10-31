package com.cgsolutions.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgsolutions.security.dao.UserDao;
import com.cgsolutions.security.domain.User;

@Service
public class UserManager {
	@Autowired
	private UserDao userDao;
	
	public User find(String userId){
		return userDao.find(userId);
	}
	
	public void save(User user){
		userDao.save(user);
	}
	
	public List<User> findAll(){
		return userDao.findAll();
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}