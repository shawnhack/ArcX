package com.bitmind.domain;

import java.io.Serializable;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.bitmind.dao.entity.Address;
import com.bitmind.util.PriceConverter;

public class WalletCalculator implements Serializable {

	private static final long serialVersionUID = 1L;
	private Money totalWorth;
	private long totalBalance;
	private AssetType type;

	public void calculateWorth(List<Address> addresses, Money lastPrice,
			AssetType type) {
		totalWorth = Money.zero(CurrencyUnit.USD);
		totalBalance = 0;
		this.type = type;

		if (addresses != null && lastPrice != null) {
			for (Address address : addresses) {
				long balance = address.getBalance();
				totalBalance = totalBalance + balance;
				Money worth = PriceConverter.getWorth(balance, lastPrice);
				totalWorth = totalWorth.plus(worth);
				address.setWorth(PriceConverter.getDisplayPrice(worth));
			}
		}

	}

	public String getTotalWorth() {
		String worth = "";

		if (totalWorth != null) {
			worth = PriceConverter.getDisplayPrice(totalWorth);
		}

		return worth;
	}

	public Money getTotalWorthValue() {
		return totalWorth;
	}

	public String getTotalBalance() {
		String balance = "";

		if (type != null) {
			balance = PriceConverter.getDisplayBalance(totalBalance,
					type.getSymbol());
		}
		return balance;
	}
}
