package com.revature.main;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.revature.domain.Account;
import com.revature.domain.User;
import com.revature.exception.InvalidCredentialsException;
import com.revature.logger.Log;
import com.revature.service.Service;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		User user = new User();
		Account account = new Account();
		Service service = new Service();
	    Log log = new Log();
	    log.printWarCraft();
		
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
		
		System.out.println("My Lord?");
		System.out.println("Your Command?");
		System.out.println();
		System.out.println("1: Add a new Character");
		System.out.println("2: Ready to work");
		System.out.println("3: That's it. I'm dead.");

		int decision = scan.nextInt();
		scan.nextLine();

		switch (decision) {

		case 1:
			newCostomer(user, account, service, scan);
			break;
		case 2:	
			logIn(user, account, service, scan);
			break;
		case 3:
			break;

		}
		scan.close();
		
	}
	public static void newCostomer(User user, Account account, Service service, Scanner scan) {
		System.out.println("Enter First Name:");
		String firstName = scan.nextLine();
		System.out.println("Enter Last Name:");
		String lastName = scan.nextLine();
		System.out.println("Create an User Name:");
		String userName = scan.nextLine();
		System.out.println("Create a password:");
		String password = scan.nextLine();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(password);
		service.createUser(user);
		System.out.println("Ready to work.");
		logIn(user, account, service, scan);
	}
	
	public static void logIn(User user, Account account, Service service, Scanner scan) {
		
		System.out.println("Enter Your Username");
		String userName = scan.nextLine();
		System.out.println("Enter Your Password");
		String password = scan.nextLine();
		user.setUserName(userName);
		user.setPassword(password);
		System.out.println("*************************************************************************************************************");
		System.out.println("Yes, My Lord: " + service.getUserByUsernamePassword(userName,password));
		System.out.println("*************************************************************************************************************");
		System.out.println("Job's done.");
		for(int i = 0; i < 3; i++) {System.out.println();}
		boolean exit = false;
		do {
			System.out.println("Ready to serve.");
			System.out.println("1: Deposit");
			System.out.println("2: Withdraw");
			System.out.println("3: View Balance");
			System.out.println("4: View Transaction history");
			System.out.println("5: Exit");
			user = service.getUserByUsernamePassword(userName, password); //acquired user_id
			account = service.getAccountByUid(user.getUid());
			int decision = scan.nextInt();
			
			
				switch (decision) {
		
				case 1:  //deposite
					
					System.out.println("Input the amount that you want to deposite: ");
//					try {
						int dAmount = scan.nextInt();
						System.out.println("You input amount: " + dAmount);
						service.deposit(account, user, dAmount);
//					}catch(InputMismatchException e){
//						System.out.println("Invalid input");
//					}
					System.out.println();
					break;
				case 2:	//withdraw
					System.out.println("Input the amount that you want to withdraw: ");
					int wAmount = scan.nextInt();
					System.out.println("You input amount: " + wAmount);
					service.withdraw(account, user, wAmount);
					System.out.println();
					break;
				case 3:
					System.out.println("This is your balance: ");
					//service.getBalance(account, user);
					System.out.println(service.getBalance(account, user));
					System.out.println();
					break;
				case 4:
					System.out.println("Transaction History");
					System.out.println(service.viewTransactionHistory(account));
					System.out.println();
					break;
				case 5:
					exit = true;
					System.out.println("Leave me alone." );
					break;
				}
			
		}while(!exit);
	}
}
