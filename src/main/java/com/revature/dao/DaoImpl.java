package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Account;
import com.revature.domain.Transaction;
import com.revature.domain.User;
import com.revature.util.ConnectionUtil;

public class DaoImpl implements Dao {
	
//	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	private String username = "banking_db";
//	private String password = "p4ssw0rd";
	
	// U_FN, U_LN, U_USERNAME, U_PASSWORD
	public int createUser(User user) {
		int status = 0;

		try(Connection conn = ConnectionUtil.getConnection();) {
			String sql = "INSERT INTO bank_user (U_FN, U_LN, U_USERNAME, U_PASSWORD) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			
			status = ps.executeUpdate(); // automatically commit;
			user = getUserByUsernamePassword( user.getUserName(), user.getPassword());
			
			
			String sql2= "INSERT INTO bank_account (U_ID) values(?)"; 
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			//System.out.println("uid: " + user.getUid());
			ps2.setInt(1, user.getUid());
			ps2.executeUpdate();
			//System.out.println("Status: " + status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return status;


		
	}

//	public int createAccount(Account acc) {
//		return 0;
//	}

	@Override
	public User getUserByUsernamePassword(String userName, String password) {
	
		User user = null;
		try (Connection conn = ConnectionUtil.getConnection();) {

			String sql = "SELECT U_ID, U_FN, U_LN FROM BANK_USER WHERE U_USERNAME = ? AND U_PASSWORD = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt(1),rs.getString(2),rs.getString(3), userName,password);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void deposit(Account account, User user,double amount) {

		try(Connection conn = ConnectionUtil.getConnection();) {
			String sql = "UPDATE bank_account SET BA_BALANCE = ? where u_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

//			System.out.println("original balance:" + account.getBalance());
			double totalAmount = account.getBalance() + amount;
			ps.setDouble(1, totalAmount);
//			System.out.println("uid: " + user.getUid());
			ps.setDouble(2, user.getUid());
			storeTransaction(account, amount, 1); //1-deposit, 2-withdraw
			ps.executeUpdate(); // automatically commit;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void withdraw(Account account, User user, double amount) {
		if(amount > getBalance(account,user)) {
			System.out.println("Insufficient Gold");
			return;
		}
		try(Connection conn = ConnectionUtil.getConnection();) {
			String sql = "UPDATE bank_account SET BA_BALANCE = ? where u_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			double totalAmount = account.getBalance() - amount;
			ps.setDouble(1, totalAmount);
//			System.out.println("uid: " + user.getUid());
			ps.setDouble(2, user.getUid());
			storeTransaction(account, amount, 2); //1-deposit, 2-withdraw
			ps.executeUpdate(); // automatically commit;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double getBalance(Account account, User user) {
		try(Connection conn = ConnectionUtil.getConnection();) {
			String sql = "SELECT ba_balance FROM BANK_ACCOUNT WHERE U_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUid());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				account.setBalance(rs.getInt(1));
			}

			ps.executeUpdate(); // automatically commit;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account.getBalance();
	}

	public void storeTransaction(Account account, double amount, int type) {
		System.out.println("Storing Transaction");
		try(Connection conn = ConnectionUtil.getConnection();) {
			CallableStatement cs = conn.prepareCall("{call store_transaction(?,?, ?)}");	
			cs.setInt(1, account.getBaid());
			cs.setInt(2, type);	// 1- deposit,   2- withdraw
			cs.setDouble(3, amount);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Account getAccountByUid(int uid) {
		Account account = null;
		try (Connection conn = ConnectionUtil.getConnection();) {

			String sql = "SELECT BA_ID, BA_BALANCE, U_ID FROM BANK_ACCOUNT WHERE U_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				account = new Account(rs.getInt(1),rs.getDouble(2),rs.getInt(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public List<Transaction> viewTransactionHistory(Account account) {
		
		List<Transaction> transactions = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection();) {

			String sql = "SELECT (SELECT BTT_TYPE FROM BANK_TX_TYPE WHERE BANK_TX.BTT_ID = BANK_TX_TYPE.BTT_ID ), TX_AMOUNT, TX_TIMESTAMP FROM BANK_TX WHERE BA_ID = ?";
			//String sql = "SELECT BTT_TYPE, TX_AMOUNT, TX_TIMESTAMP FROM BANK_TX_TYPE, BANK_TX WHERE BA_ID = ? AND BANK_TX.BTT_ID =  BANK_TX_TYPE.BTT_ID;";
			//String sql = "SELECT BTT_ID, TX_AMOUNT, TX_TIMESTAMP FROM BANK_TX WHERE BA_ID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("baid: " + account.getBaid());
			ps.setInt(1, account.getBaid());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				transactions.add(new Transaction(rs.getString(1), rs.getDouble(2), rs.getString(3)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
		
	}
	
	


}
