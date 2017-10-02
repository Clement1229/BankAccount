package com.revature.service;


import com.revature.dao.Dao;
import com.revature.dao.DaoImpl;
import com.revature.domain.Account;
import com.revature.domain.User;

public class Service {
	
	Dao dao = new DaoImpl();
	
	public void createUser(User user) {
		dao.createUser(user);
	}
	public User getUserByUsernamePassword(String userName, String password) {
		return dao.getUserByUsernamePassword(userName,password);
	}
	public void deposit(Account account, User user, double amount) {
		dao.deposit(account, user, amount);
	}
	public void withdraw(Account account, double amount) {
		dao.withdraw(account, amount);
	}
	public double getBalance(Account account, User user) {
		return dao.getBalance(account, user);
	}
}
