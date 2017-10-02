package com.revature.dao;

import com.revature.domain.Account;
import com.revature.domain.User;

public interface Dao {
	/*
	 * 	 CRUD methods only!!
	 */
		
		//Create
		public int createUser(User user);
		//public int createAccount(Account acc);
		public void deposit(Account account, User user, double amount);
		public void withdraw(Account account, User user, double amount);
		public double getBalance(Account account, User user); 

		
		//READ
		public User getUserByUsernamePassword(String userName, String password); 
		//public List<FlashCard> getAllFlashCard();
		//public FlashCard getFlashCardByid(int id);
		//public FlashCard getFlashCardByQuestion(String question);
		
		
		//UPDATE
		//public int updateFlashCard(FlashCard fc);
		
		//DELETE
		//public int deleteFlashCard(FlashCard fc);
}
