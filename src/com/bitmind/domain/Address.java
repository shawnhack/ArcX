package com.bitmind.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.bitmind.util.PriceFormatter;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String address;
	private BigDecimal balance;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getBalanceView() {
		return PriceFormatter.toBtc(balance);
	}

}
