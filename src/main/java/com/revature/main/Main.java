package com.revature.main;

import java.util.Scanner;

import com.revature.dao.Dao;
import com.revature.dao.DaoImpl;
import com.revature.domain.User;
import com.revature.service.Service;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		User user = new User();
		Service service = new Service();

//		A user can login
//		A user can deposit to their account
//		A user can withdraw
//		A user can view balance
//		A user can execute multiple transactions in one session
//		A user can logout (exit program)
//		A user's data is persistent at all times
//		A bank has many users
//		A user has a username, password and balance
//		A user can not overdraft

		Scanner scan = new Scanner(System.in);

		System.out.println("What would you like to do");
		System.out.println("1: Become a new Customer");
		System.out.println("2: Log In");

		int decision = scan.nextInt();
		scan.nextLine();

		switch (decision) {

		case 1:
			newCostomer(user, service, scan);
			break;
		case 2:	
			logIn(user, service, scan);
		case 5:
			break;

		}
		scan.close();
		
		
		
		// createUser() test
//		User user = new User("Momo","Chen","hahamoyoyo","p4ssw0rd");
//		Dao dao = new DaoImpl();
//		System.out.println("Saving the new user:" + dao.createUser(user));
		
	}
	public static void newCostomer(User user, Service service, Scanner scan) {
		System.out.println("Enter First Name");
		String firstName = scan.nextLine();
		System.out.println("Enter Last Name");
		String lastName = scan.nextLine();
		System.out.println("Create an User Name");
		String userName = scan.nextLine();
		System.out.println("Create a password");
		String password = scan.nextLine();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(password);
		service.createUser(user);
		//break;
	}
	
	public static void logIn(User user, Service service, Scanner scan) {
		
		System.out.println("Enter Your User Name");
		System.out.println("Enter Your Password");
		
		System.out.println("What would you like to do");
		System.out.println("1: Deposit");
		System.out.println("2: Withdraw");
		System.out.println("3: View Balance");
		System.out.println("4: Exit");
	}
}
