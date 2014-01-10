package com.bitmind.web;

import org.joda.money.Money;

public interface PriceReader {

	public Money getLastPrice();
}
