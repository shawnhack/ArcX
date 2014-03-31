package com.bitmind.web;

public interface BlockAddress {

	public abstract String getHash();

	public abstract String getAddress();

	public abstract long getTotalReceived();

	public abstract long getTotalSent();

	public abstract long getBalance();

}