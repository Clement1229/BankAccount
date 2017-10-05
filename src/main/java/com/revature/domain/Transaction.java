package com.revature.domain;

public class Transaction {
	 //BTT_ID, tx_amount, tx_timestamp
	
	private String type;
	private double txAmount;
	private String txTimestamp;
	
	
	public Transaction() {
		super();
	}

	public Transaction(String type, double txAmount, String txTimestamp) {
		super();
		this.type = type;
		this.txAmount = txAmount;
		this.txTimestamp = txTimestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getTxAmount() {
		return txAmount;
	}

	public void setTxAmount(double txAmount) {
		this.txAmount = txAmount;
	}

	public String getTxTimestamp() {
		return txTimestamp;
	}

	public void setTxTimestamp(String txTimestamp) {
		this.txTimestamp = txTimestamp;
	}

	@Override
	public String toString() {
		return type + "\t" + "$"+ txAmount +"\t\t" + txTimestamp +"\n";
	}

	
}
