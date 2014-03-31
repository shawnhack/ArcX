package com.bitmind.web.btc;

import com.bitmind.web.BlockAddress;
import com.google.gson.annotations.SerializedName;

public class BlockchainAddress implements BlockAddress {

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

	/* (non-Javadoc)
	 * @see com.bitmind.web.btc.block.BlockAddress#getHash()
	 */
	@Override
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	/* (non-Javadoc)
	 * @see com.bitmind.web.btc.block.BlockAddress#getAddress()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see com.bitmind.web.btc.block.BlockAddress#getTotalReceived()
	 */
	@Override
	public long getTotalReceived() {
		return totalReceived;
	}

	public void setTotalReceived(long totalReceived) {
		this.totalReceived = totalReceived;
	}

	/* (non-Javadoc)
	 * @see com.bitmind.web.btc.block.BlockAddress#getTotalSent()
	 */
	@Override
	public long getTotalSent() {
		return totalSent;
	}

	public void setTotalSent(long totalSent) {
		this.totalSent = totalSent;
	}

	/* (non-Javadoc)
	 * @see com.bitmind.web.btc.block.BlockAddress#getBalance()
	 */
	@Override
	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

}
