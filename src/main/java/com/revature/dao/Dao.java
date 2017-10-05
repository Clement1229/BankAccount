package com.revature.dao;

import java.util.List;

import com.revature.domain.Account;
import com.revature.domain.Transaction;
import com.revature.domain.User;

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
		public List<Transaction> viewTransactionHistory(Account account);
		
		//UPDATE
		public void deposit(Account account, User user, double amount);
		public void withdraw(Account account, User user, double amount);
		
		//DELETE
}
