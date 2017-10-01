package com.revature.domain;

public class Account {
//	BA_ID
//	BA_BALANCE
//	U_ID
	private int baid; //ba_id
	private double balance; //ba_balance
	private int uid; // user_id
	
	
	
	
	public Account() {
		super();
	}
	
	
	
	public Account(double balance, int uid) {
		super();
		this.balance = balance;
		this.uid = uid;
	}



	public Account(int baid, double balance, int uid) {
		super();
		this.baid = baid;
		this.balance = balance;
		this.uid = uid;
	}
	public int getBaid() {
		return baid;
	}
	public void setBaid(int baid) {
		this.baid = baid;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Account [baid=" + baid + ", balance=" + balance + ", uid=" + uid + "]";
	}
	
	
}
