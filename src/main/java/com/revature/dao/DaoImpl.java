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
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			
			
			status = ps.executeUpdate(); // automatically commit;
			System.out.println("Status: " + status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;


		
	}

	public int createAccount(Account acc) {
		return 0;
	}

	@Override
	public User getBankUserByUsernamePassword(String userName, String password) {
	
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

}
