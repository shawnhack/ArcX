package com.bitmind.web.ltc;

import com.bitmind.web.BlockAddress;

public class LtcAddress implements BlockAddress {

	private long balance;
	private String address;

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getHash() {
		return null;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public long getTotalReceived() {
		return 0;
	}

	@Override
	public long getTotalSent() {
		return 0;
	}

	@Override
	public long getBalance() {
		return balance;
	}

}
