package com.bitmind.web;

import org.joda.money.Money;

import com.bitmind.domain.Address;

public interface CoinReader {

	public Address readAddress(String addressString);

	public Money getLastPrice();

	public long getMintedCount();

}