package com.revature.main;

import com.revature.dao.Dao;
import com.revature.dao.DaoImpl;
import com.revature.domain.User;
import com.revature.service.Service;

public class Main {
	
	
	
	public static void main(String[] args) {
		//User user = new User();
		Service service = new Service();

		//test
		User user = new User("Clement","Lin","Clement1229","p4ssw0rd");
		Dao dao = new DaoImpl();
		System.out.println("Saving the new user:" + dao.createUser(user));
		
	}
}
