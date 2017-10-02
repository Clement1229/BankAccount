package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.domain.Account;
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
			String sql2= "INSERT INTO bank_account (U_ID) values(?)"; 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			
			status = ps.executeUpdate(); // automatically commit;
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
			double totalAmount = account.getBalance() + amount;
			ps.setDouble(1, totalAmount);
			System.out.println("uid: " + user.getUid());
			ps.setDouble(2, user.getUid());
			
			ps.executeUpdate(); // automatically commit;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void withdraw(Account account, double amount) {
		// TODO Auto-generated method stub
		
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
	
	/*public int getUidNBy(Account account, User user) {
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
		
	}*/


}
