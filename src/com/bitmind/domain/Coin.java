package com.bitmind.domain;


public class Coin implements Asset {

	private String balance;
	private String worth;

	@Override
	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Override
	public String getWorth() {
		return worth;
	}

	public void setWorth(String worth) {
		this.worth = worth;
	}

	@Override
	public String getPrice() {
		// TODO Auto-generated method stub
		return null;
	}

}
