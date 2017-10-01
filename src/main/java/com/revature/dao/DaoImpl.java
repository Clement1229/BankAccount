package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
