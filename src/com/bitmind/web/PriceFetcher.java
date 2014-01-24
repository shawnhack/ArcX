package com.bitmind.web;

import org.joda.money.Money;

public interface PriceFetcher {

	public Money getLastPrice();
}
