package com.revature.dao;

import com.revature.pojos.Account;
import com.revature.pojos.User;

public interface Dao {
	/*
	 * 	 CRUD methods only!!
	 */
		
		//Create
		public int createUser(User user);
		public void storeTransaction(Account account, double amount, int type);
		//READ
		public User getUserByUsernamePassword(String userName, String password); 
		public double getBalance(Account account, User user);
		public Account getAccountByUid(int uid);
		
		
		//UPDATE
		public void deposit(Account account, User user, double amount);
		public void withdraw(Account account, User user, double amount);
		
		//DELETE
}
