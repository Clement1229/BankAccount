package com.revature.dao;

import com.revature.domain.Account;
import com.revature.domain.User;

public interface Dao {
	/*
	 * 	 CRUD methods only!!
	 */
		
		//Create
		public int createUser(User user);
		public int createAccount(Account acc);
		
		
		//READ
		public User getBankUserByUsernamePassword(String userName, String password); 
		//public List<FlashCard> getAllFlashCard();
		//public FlashCard getFlashCardByid(int id);
		//public FlashCard getFlashCardByQuestion(String question);
		
		
		//UPDATE
		//public int updateFlashCard(FlashCard fc);
		
		//DELETE
		//public int deleteFlashCard(FlashCard fc);
}
