package com.revature.service;


import com.revature.dao.Dao;
import com.revature.dao.DaoImpl;
import com.revature.domain.User;

public class Service {
	
	Dao dao = new DaoImpl();
	
	public void createUser(User user) {
		dao.createUser(user);
	}
	public User getBankUserByUsernamePassword(String userName, String password) {
		return dao.getBankUserByUsernamePassword(userName,password);
	}
}
