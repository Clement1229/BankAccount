package com.revature.service;


import com.revature.dao.Dao;
import com.revature.dao.DaoImpl;
import com.revature.pojos.Account;
import com.revature.pojos.User;

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
	public void withdraw(Account account, User user, double amount) {
		dao.withdraw(account, user, amount);
	}
	public double getBalance(Account account, User user) {
		return dao.getBalance(account, user);
	}
}
