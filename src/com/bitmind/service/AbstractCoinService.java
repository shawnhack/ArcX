package com.bitmind.service;

import java.math.BigDecimal;

import org.joda.money.format.MoneyFormatterBuilder;

import com.bitmind.domain.Coin;
import com.bitmind.web.CoinReader;

public abstract class AbstractCoinService {

	CoinReader coinReader;

	abstract CoinReader getReader();

	abstract Coin getNewCoin();

	public abstract BigDecimal getAddressBalance(String addressString);

	public Coin buildCoin() {
		Coin coin = getNewCoin();
		String displayPrice = new MoneyFormatterBuilder().toFormatter().print(
				getReader().getLastPrice());
		coin.setPrice(displayPrice);

		return coin;
	};
}
