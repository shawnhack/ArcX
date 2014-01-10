package com.bitmind.web.btc.blockchain;

import com.google.gson.annotations.SerializedName;

public class BlockchainAddress {

	@SerializedName("hash160")
	private String hash;
	private String address;
	private int transaction;
	@SerializedName("total_received")
	private long totalReceived;
	@SerializedName("total_sent")
	private long totalSent;
	@SerializedName("final_balance")
	private long balance;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTransaction() {
		return transaction;
	}

	public void setTransaction(int transaction) {
		this.transaction = transaction;
	}

	public long getTotalReceived() {
		return totalReceived;
	}

	public void setTotalReceived(long totalReceived) {
		this.totalReceived = totalReceived;
	}

	public long getTotalSent() {
		return totalSent;
	}

	public void setTotalSent(long totalSent) {
		this.totalSent = totalSent;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

}
