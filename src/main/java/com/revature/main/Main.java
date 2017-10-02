package com.revature.main;

import java.util.Scanner;

import com.revature.domain.Account;
import com.revature.domain.User;
import com.revature.service.Service;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		User user = new User();
		Account account = new Account();
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
		System.out.println("3: Exit");

		int decision = scan.nextInt();
		scan.nextLine();

		switch (decision) {

		case 1:
			newCostomer(user, service, scan);
			break;
		case 2:	
			logIn(user, account, service, scan);
			break;
		case 3:
			break;

		}
		scan.close();
		
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
	}
	
	public static void logIn(User user, Account account, Service service, Scanner scan) {
		
		System.out.println("Enter Your User Name");
		String userName = scan.nextLine();
		System.out.println("Enter Your Password");
		String password = scan.nextLine();
		user.setUserName(userName);
		user.setPassword(password);
		System.out.println("***********************************************************************************");
		System.out.println("This is your information " + service.getUserByUsernamePassword(userName,password));
		System.out.println("***********************************************************************************");
		System.out.println("What would you like to do");
		System.out.println("1: Deposit");
		System.out.println("2: Withdraw");
		System.out.println("3: View Balance");
		System.out.println("4: Exit");
		user = service.getUserByUsernamePassword(userName, password); //acquired user_id
		int decision = scan.nextInt();
		switch (decision) {

		case 1:  //deposite
			
			System.out.println("Input the amount that you want to deposite: ");
			int amount = scan.nextInt();
			System.out.println("You input amount: " + amount);
			service.deposit(account, user, amount);
			break;
		case 2:	//withdraw
			System.out.println("Input the amount that you want to withdraw: ");
			int amount = scan.nextInt();
			System.out.println("You input amount: " + amount);
			service.withdraw(account, user, amount);
			break;
		case 3:
			System.out.println("This is your balance: ");
			//service.getBalance(account, user);
			System.out.println(service.getBalance(account, user));
			break;
		case 4:
			break;

		}
	}
}
