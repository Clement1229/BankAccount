package com.revature.service;


import java.util.List;

import com.revature.dao.Dao;
import com.revature.dao.DaoImpl;
import com.revature.domain.Account;
import com.revature.domain.Transaction;
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
	public void withdraw(Account account, User user, double amount) {
		dao.withdraw(account, user, amount);
	}
	public double getBalance(Account account, User user) {
		return dao.getBalance(account, user);
	}
	public Account getAccountByUid(int uid) {
		return dao.getAccountByUid(uid);
	}
	public List<Transaction> viewTransactionHistory(Account account){
		return dao.viewTransactionHistory(account);
	}
}
